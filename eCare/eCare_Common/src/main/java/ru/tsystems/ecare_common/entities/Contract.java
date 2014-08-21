package ru.tsystems.eCare_Common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Contract implements Serializable {

    private static final long serialVersionUID = 582020387629534654L;
    @Id
    @GeneratedValue
    private int contractId;
    @Column(unique = true, nullable = false)
    private String number;
    @OneToOne
    private Tariff tariff;
    @OneToMany
    private List <TariffOption> tariffOption;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private boolean blockedEmployee;

    
    /**
     * @return the contractId
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * @param contractId the contractId to set
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the tariff
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * @param tariff the tariff to set
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active or block on this contract
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
            @Override
    public String toString() {
        return "Contract{" +
                ", number=" + getNumber()+
                " tariff"+getTariff() +
                "tariff options{ "+ getTariffOption()+
                " active="+isActive()+
                '}';
    }

    /**
     * @return the tariffOption
     */
    public List <TariffOption> getTariffOption() {
        return tariffOption;
    }

    /**
     * @param tariffOption the tariffOption to set
     */
    public void setTariffOption(List <TariffOption> tariffOption) {
        this.tariffOption = tariffOption;
    }

    /**
     * @return the blockedEmployee
     */
    public boolean isBlockedEmployee() {
        return blockedEmployee;
    }

    /**
     * @param blockedEmployee the blockedEmployee to set
     */
    public void setBlockedEmployee(boolean blockedEmployee) {
        this.blockedEmployee = blockedEmployee;
    }
}
