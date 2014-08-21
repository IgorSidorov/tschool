package ru.tsystems.eCare_Client.main;

import org.apache.log4j.Logger;
import ru.tsystems.eCare_Common.criteria.*;
import ru.tsystems.eCare_Common.entities.*;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * This class implements singleton pattern with private constructor and public
 * getInstance method.
 */
public class ClientSocket {

    private static final Logger logger = Logger.getLogger(ClientSocket.class);
    private static volatile ClientSocket instance;

    private ClientSocket() {
    }

    // double-braces method of implementing singleton, with synchronized block in inner braces.
    public static ClientSocket getInstance() {
        if (instance == null) {
            synchronized (ClientSocket.class) {
                if (instance == null) {
                    instance = new ClientSocket();
                }
            }
        }
        return instance;
    }

    public Response getResponse(Request request) {
        Response response = null;
        try {
            Socket clientSock = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(clientSock.getOutputStream()));
            out.writeObject(request);
            out.flush();

            ObjectInputStream in = new ObjectInputStream(clientSock.getInputStream());
            response = (Response) in.readObject();

        } catch (IOException e) {
            logger.error((e.getMessage()));
            JOptionPane.showMessageDialog(null, "Server is not available!");
        } catch (ClassNotFoundException e) {
            logger.error((e.getMessage()));
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    public List<Contract> getAllContract() {
        Response response = ClientSocket.getInstance().getResponse(new Request("Get all contracts"));

        List<Contract> resultList = null;
        if (!response.getIsProblem()) {
            resultList = (List<Contract>) response.getRespBody();
        } else {
        }// TODO error
        return resultList;
    }

    public String createContract(Contract contract) {
        Response response = ClientSocket.getInstance().getResponse(new Request("Add new contract", contract));
        return (String) response.getRespBody();
    }

    public boolean checkLoginAvailable(Employee e) {        // release

        Response response = ClientSocket.getInstance().getResponse(new Request("Check authorization", e));
        return (Boolean) response.getRespBody();
    }
//
//    public String createTariff(Tariff tariff) {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Create tariff", tariff));
//        return (String) response.getRespBody();
//    }
//
//    public String createTariffOption(TariffOption tariffOption) {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Create tariffOption", tariffOption));
//        String result = "Success!";
//        if (response.getIsProblem()) {
//            result = response.getTitle();
//        }
//        return result;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<Tariff> getAllTariff() {      // release
//        Response response = ClientSocket.getInstance().getResponse(new Request("Get all Tariff"));
//
//        List<Tariff> resultList = null;
//        if (!response.getIsProblem()) {
//            resultList = (List<Tariff>) response.getRespBody();
//        } else {
//            // TODO error
//        }
//        return resultList;
//    }
//
//    public Response selectTariff(long contractId, Client client) {
//
//        return ClientSocket.getInstance().getResponse(new Request(("Select tariff"), new SampleObject<Long, Client>(contractId, client)));
//    }
//
//    public Response selectTariffOption(long contractId, Tariff tariff) {
//
//        return ClientSocket.getInstance().getResponse(new Request(("Select tariffOption"), new SampleObject<Long, Tariff>(contractId, tariff)));
//    }
//
//    public Tariff getTariffByName(String name) {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Get tarif by name", name));
//
//        Tariff resultStation = null;
//        if (!response.getIsProblem()) {
//            resultStation = (Tariff) response.getRespBody();
//        } else {
//            // TODO error
//        }
//        return resultStation;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<TariffOption> getAllTariffOption() {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Get all Tariff Option"));
//
//        List<TariffOption> resultList = null;
//        if (!response.getIsProblem()) {
//            resultList = (List<TariffOption>) response.getRespBody();
//        } else {
//            // TODO error
//        }
//        return resultList;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<Client> getAllClients() {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Get all clients lst"));
//
//        List<Client> resultList = null;
//        if (!response.getIsProblem()) {
//            resultList = (List<Client>) response.getRespBody();
//        } else {
//            // TODO error
//        }
//        return resultList;
//    }
//
//    public Contract getContractByNumber(Long number) {
//        Response response = ClientSocket.getInstance().getResponse(new Request("Get contract by number", number));
//        return (Contract) response.getRespBody();
//    }
}
