package ru.tsystems.eCare_Common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class TariffOption implements Serializable {

    private static final long serialVersionUID = 582203876239534654L;
    @Id
    @GeneratedValue
    private int tariffOptionId;
    @Column
    private String name;
    @Column
    private int price;
    @Column
    private int connectionCost;
    @ManyToMany(mappedBy = "tariffOption")
    private List <Contract> contract;

    /**
     * @return the tariffOptionId
     */
    
    public int getTariffOptionId() {
        return tariffOptionId;
    }

    /**
     * @param tariffOptionId the tariffOptionId to set
     */
    public void setTariffOptionId(int tariffOptionId) {
        this.tariffOptionId = tariffOptionId;
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
     * @return the connectionCost
     */
    public int getConnectionCost() {
        return connectionCost;
    }

    /**
     * @param connectionCost the connectionCost to set
     */
    public void setConnectionCost(int connectionCost) {
        this.connectionCost = connectionCost;
    }

    /**
     * @return the contract
     */
    public List <Contract> getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(List <Contract> contract) {
        this.contract = contract;
    }
    
}
