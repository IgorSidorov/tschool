package ru.tsystems.eCare_Server.dao;

import ru.tsystems.eCare_Common.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddData {

    public static void main(String[] args) throws SQLException, ParseException {
        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        EmployeeDao ed = new EmployeeDao();
        ClientDao cd = new ClientDao();
        ContractDao ctrd = new ContractDao();
        TariffDao td = new TariffDao();
        TarriffOptionDao tod = new TarriffOptionDao();


        try {
            transact.begin();

            TariffOption to = new TariffOption();
            to.setTariffOptionId(1);
            to.setName("skazka");
            to.setPrice(3);
            to.setConnectionCost(50);

//            Employee emp = new Employee();
            Employee emp1 = new Employee("qwerty", "1234");

            try {

                ed.create(emp1);
                tod.update(to);

            } catch (SQLException ex) {
//             logger.error("Error while creating a dao", e);
                ex.printStackTrace();
            }

            transact.commit();

        } catch (RollbackException exc) {
            System.out.println("There is some error" + exc.toString());
            if (transact.isActive()) {
                transact.rollback();
            }
        } finally {
            em.close();
        }
    }
}
