package ru.tsystems.eCare_Common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Tariff implements Serializable {

    private static final long serialVersionUID = 58220387629534654L;
    @Id
    @GeneratedValue
    private long tariffId;
    @Column
    private String name;
    @Column
    private int price;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TariffOption> tariffOption;

    /**
     * @return the tariffId
     */
    public long getTariffId() {
        return tariffId;
    }

    /**
     * @param tariffId the tariffId to set
     */
    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the tariffOption
     */
    public List<TariffOption> getTariffOption() {
        return tariffOption;
    }

    /**
     * @param tariffOption the tariffOption to set
     */
    public void setTariffOption(List<TariffOption> tariffOption) {
        this.tariffOption = tariffOption;
    }
}