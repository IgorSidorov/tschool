package ru.tsystems.eCare_Server.dao;

import ru.tsystems.eCare_Common.entities.*;

import java.sql.SQLException;
import java.util.List;

public class TarriffOptionDao extends GenericDaoHiberImpl<TariffOption, Integer> {

    public TarriffOptionDao() {
        super(TariffOption.class);
    }

    public List<TariffOption> findByContract(Contract c, String number) throws SQLException {
        return getEm().createQuery("select to.name from TariffOption to "
                + "where to.name =:c.tariff.tariffOption"
                + "and c.tariff=c.number", TariffOption.class).setParameter("number", number).getResultList();
    }

    public void buyTariff(Client cl, Contract con) {
    }

    public TariffOption findByName(String name) {
        TariffOption tariffOption = em.createQuery("select to from TariffOption to "
                + "where to.name =:name", TariffOption.class).setParameter("name", name).getSingleResult();
        return tariffOption;
    }

    public void addRule() {
        
        
        
    }
}
