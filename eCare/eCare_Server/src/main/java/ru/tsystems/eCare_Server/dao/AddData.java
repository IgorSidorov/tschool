package ru.tsystems.eCare_Server.dao;

import java.sql.SQLException;
import java.util.*;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import org.apache.log4j.Logger;
import ru.tsystems.eCare_Common.entities.*;

public class AddData {

    private static final Logger logger = Logger.getLogger(AddData.class);

    public static void main(String[] args) throws SQLException {

        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        EmployeeDao ed = new EmployeeDao();
        ClientDao cd = new ClientDao();
        ContractDao ctrd = new ContractDao();
        TariffDao td = new TariffDao();
        TarriffOptionDao tod = new TarriffOptionDao();

        try {
            for (int i = 0; i < 6; i++) {
                transact.begin();
                System.out.println("trans+"+i);
                TariffOption to = new TariffOption();
                to.setName("ze19");
                to.setPrice(50);
                to.setConnectionCost(50);

                Tariff tariff = new Tariff();
                tariff.setName("dial8");
                tariff.setPrice(20);
                TariffOption[] arrOption = new TariffOption[]{tod.findByPK(1), tod.findByPK(3), tod.findByPK(6)};
                List<TariffOption> optionList = Arrays.asList(arrOption);
                tariff.setTariffOption(optionList);

                Client client = new Client();
                client.setFirstName("Ivan1");
                client.setLastName("Ivanov");
                client.setBirthDate(new Date(87, 10, 8));
                client.setEmail("qwerty@list.ru");
                client.setPassportData("1908 956687");
                Contract[] arrContract = new Contract[]{ctrd.findByPK(1), ctrd.findByPK(2)};
                List<Contract> contractList = Arrays.asList(arrContract);
                client.setContractList(contractList);

                Contract contract = new Contract();
                contract.setNumber("+79215121510");
                contract.setTariff(tariff);
                contract.setTariffOption(optionList);
                contract.setActive(true);
                contract.setBlockedEmployee(true);
                contract.setClient(client);


                Employee emp = new Employee();

                emp.setLogin("adminqo");
                emp.setPassword("123456");

                try {

                    tod.create(to);
                    td.create(tariff);
                    ctrd.create(contract);
                    cd.create(client);
                    ed.create(emp);

                } catch (SQLException ex) {
                    logger.error("Error while creating a dao", ex);
                    ex.printStackTrace();
                }

                transact.commit();
            }

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