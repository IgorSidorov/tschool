package ru.tsystems.eCare_Server.services;

import org.apache.log4j.Logger;
import ru.tsystems.eCare_Server.dao.*;
import ru.tsystems.eCare_Common.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class);
    private EmployeeDao employeeDao = new EmployeeDao();
    private ContractDao contractDao = new ContractDao();
    private ClientDao clientDao = new ClientDao();
    private TariffDao tariffDao = new TariffDao();
    private TarriffOptionDao tariffOptionDao = new TarriffOptionDao();

    /**
     * This method allows employee to be authorized. Getting from client login
     * password, actuality database and check, if this data exist in this db.
     *
     * @param ed - login and password of the employee.
     * @return - true - if such data exist, and authorization passed, else
     * return false.
     */
    public boolean checkExist(Employee ed) {
        employeeDao.getActualLogins();
        return employeeDao.getAuthorizeData().containsKey(ed.getLogin())
                && employeeDao.getAuthorizeData().containsValue(ed.getPassword());
    }


    public String createContract(Contract contract) {
        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        String result = "Success";
        try {
            transact.begin();
            try {
                contractDao.create(contract);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                return e.getMessage();
            }

            transact.commit();
            return result;

        } catch (RollbackException e) {
            logger.error(e.getMessage());
            if (transact.isActive()) {
                transact.rollback();
            }
            return e.getMessage();
        }
    }

    public List<Contract> getContractList() {
        logger.debug("get Contracts List");
        try {
            return contractDao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Client> findClientByName(String contract) {
        try {
            return clientDao.findByContract(contract);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void changeContract(Contract c) {
    }

    public void createTariff() {
    }

    public void createTariffOption() {
    }

    public void blockedContract() {
    }

    public void addTariff() {
    }

    public void addTariffOption() {
    }

    public void deleteTariff() {
    }

    public void findAllTariff() {
    }

    public void addRule() {
    }
}
