package ru.tsystems.eCare_Server.dao;

import ru.tsystems.eCare_Common.entities.Client;
import ru.tsystems.eCare_Common.entities.Contract;
import ru.tsystems.eCare_Common.entities.Tariff;
import ru.tsystems.eCare_Common.entities.TariffOption;

import javax.persistence.Query;
import java.util.*;

/**
 * Implements working with database.
 */
public class ContractDao extends GenericDaoHiberImpl<Contract, Long> {

    public ContractDao() {
        super(Contract.class);
    }

    /**
     * Getting client by phone number.
     *
     * @param contract
     * to see.
     * @return - return Client.
     */
    public Client getClientByPhoneNumber(Contract contract) {
        Query query = getEm().createQuery("select c.contractList from Client c where c.contractList.number =:number");
        query.setParameter("number", contract.getNumber());
        return (Client) query.getSingleResult();
    }

    public Tariff getTariffByPhoneNumber(String number) {

        Query dateQuery = getEm().createQuery("select c.tariff from Contract c where c.number =:number", Contract.class);
        dateQuery.setParameter("number", number);
        return (Tariff)dateQuery.getSingleResult();
    }

    public List<TariffOption> getTariffOptionByNumber(String number) {

        Query dateQuery = getEm().createQuery("select c.tariff.tariffOption from Contract c where c.number =:number", Contract.class);
        dateQuery.setParameter("number", number);
        return dateQuery.getResultList();
    }
}