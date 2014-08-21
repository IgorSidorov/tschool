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
    private String result = "Success";
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

    public List<Client> findClientByContract(String contract) {
        try {
            return clientDao.findByContract(contract);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String changeContract(Contract contract) {
        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            try {
                contractDao.update(contract);
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

    public String createTariff(Tariff tariff) {
        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            try {
                tariffDao.create(tariff);
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

    public String createTariffOption(TariffOption tariffOption) {
        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            try {
                tariffOptionDao.create(tariffOption);
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

    public String deleteTariff(Tariff tariff) {

        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            try {
                tariffDao.delete(tariff);
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

    public String deleteTariffOption(TariffOption tariffOption) {

        EntityManager em = EmfInit.getEm();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            try {
                tariffOptionDao.delete(tariffOption);
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

    public List<Tariff> findAllTariff() {
        try {
            return tariffDao.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

public void addRule(List<TariffOption> tariffOptions) {
        try {
            tariffOptionDao.addRule();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
