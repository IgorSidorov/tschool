package ru.tsystems.eCare_Common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 7322705805494879474L;
    @Id
    @GeneratedValue
    private long clientId;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(nullable = false)
    private String passportData;
    @OneToMany
    private List<Contract> contractList;
    @Column
    private String email;

    public Client() {
    }

    public Client(String firstName, String lastName, Date birthDate, String passportData, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.passportData = passportData;
        this.email = email;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long passengerId) {
        this.clientId = passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        if (!passportData.equals(client.passportData)) {
            return false;
        }
        if (!birthDate.equals(client.birthDate)) {
            return false;
        }
        if (!firstName.equals(client.firstName)) {
            return false;
        }
        if (!lastName.equals(client.lastName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("clientId=").append(clientId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate).append('\'');
        sb.append(", passportData=").append(getPassportData()).append('\'');
        sb.append(", e-mail: ").append(getEmail()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return the passportData
     */
    public String getPassportData() {
        return passportData;
    }

    /**
     * @param passportData the passportData to set
     */
    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
