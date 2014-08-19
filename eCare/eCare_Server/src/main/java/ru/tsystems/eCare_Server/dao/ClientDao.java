package ru.tsystems.eCare_Server.dao;

import ru.tsystems.eCare_Common.entities.Client;
import javax.persistence.Query;
import java.util.List;

public class ClientDao extends GenericDaoHiberImpl<Client, Long> {

    public ClientDao() {
        super(Client.class);
    }

    /**
     * Cheks availability of the Client login.
     * @return - true, if such login is not using yet.
     */
    boolean checkAvailableLogin(String clientLogin) {

        Query query = getEm().createQuery("select count(*) from" + getType().getSimpleName()
                + "Client c where c.login = :clientLogin").setParameter("clientLogin", clientLogin);

        Long count = (Long) query.getSingleResult();
        return count < 1;
    }

    public Client registrationData(Client c) {

        Query query = getEm().createQuery("select c from Client c "
                + "where c.firstName =:firstName and c.lastName =:lastName and "
                + "c.birthDate =:birthDate and c.passportData =:passportData and c.email =:email", Client.class);
        query.setParameter("firstName", c.getFirstName());
        query.setParameter("lastName", c.getLastName());
        query.setParameter("birthDate", c.getBirthDate());
        query.setParameter("passportData", c.getPassportData());
        query.setParameter("email", c.getEmail());
        List<Client> clientList = query.getResultList();
        if (clientList.isEmpty()) {
            return null;
        } else {
            return clientList.get(0);
        }
    }
    
    public List <Client> findByContract (String number) {
         Query query = getEm().createQuery("select c from Client c "
                + "where c.contractList.contains(number) =:true ", Client.class);
                List<Client> clientList = query.getResultList();
        return clientList;
    }
}
