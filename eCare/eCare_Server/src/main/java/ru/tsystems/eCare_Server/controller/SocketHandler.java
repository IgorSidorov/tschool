package ru.tsystems.eCare_Server.controller;


import org.apache.log4j.Logger;
import ru.tsystems.eCare_Common.criteria.*;
import ru.tsystems.eCare_Common.entities.*;
import ru.tsystems.eCare_Server.services.EmployeeService;

import java.io.*;
import java.net.Socket;


public class SocketHandler implements Runnable {

    private static final Logger logger = Logger.getLogger(SocketHandler.class);
    private Socket socket;
    private EmployeeService emplService = new EmployeeService();

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            Request request = (Request) inStream.readObject();

            Response actualResponse = this.handleRequest(request);
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(actualResponse);
            outStream.flush();
            socket.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if ((socket != null && !socket.isClosed())) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * Working with services.
     *
     * @param request - what did we get from client
     * @return -  response, which consists of answer to the client.
     */
    private Response handleRequest(Request request) {
        Response response = new Response("No task");
        String requestTitle = request.getTitle();

        if (requestTitle.equals("Check authorization")) {
            response = new Response((Serializable) emplService.checkExist((Employee) request.getReqBody()));
        } else if (requestTitle.equals("Add new contract")) {
            response = new Response((Serializable) emplService.createContract((Contract)request.getReqBody()));
        } else if (requestTitle.equals("Get all contracts")) {
            response = new Response((Serializable) emplService.getContractList());
        } 
        return response;
    }
}