package ru.tsystems.eCare_Server.dao;

import ru.tsystems.eCare_Common.entities.*;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class TariffDao extends GenericDaoHiberImpl<Tariff, Long> {

    public TariffDao() {
        super(Tariff.class);
    }

    public List<Tariff> getAllTariffs() {
        return em.createQuery("select st from Tariff st", Tariff.class).getResultList();
    }

    public void insert(Tariff tariff) throws SQLException {

        create(tariff);

    }

    public void buyTariff(Client cl, Contract con) {
    }

    public void deleteTariff (Tariff tariff) throws SQLException {

        delete(tariff);

    }
    
    public void setAvailableTariffOptions () {
        
    }
}
