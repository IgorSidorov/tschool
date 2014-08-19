package ru.tsystems.eCare_Server.dao;


import ru.tsystems.eCare_Common.entities.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Hashmap - key its a login, value - password.
 */
public class EmployeeDao extends GenericDaoHiberImpl<Employee, Long> {

    private Map<String, String> authorizeData = new HashMap<String, String>();

    public EmployeeDao() {
        super(Employee.class);
    }

    public void getActualLogins() {
        List<Employee> empData = getEm().createQuery("select x from Employee x").getResultList();
        for (Employee data : empData) {
            authorizeData.put(data.getLogin(), data.getPassword());
        }
    }

    public Map<String, String> getAuthorizeData() {
        return authorizeData;
    }


}
