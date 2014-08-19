package ru.tsystems.eCare_Server.controller;


import ru.tsystems.eCare_Server.GUI.ServerForm;

import javax.swing.*;

/**
 * Enter point of the server.
 * Starts server frame.
 */
public class Server {
    public static void main(String[] args) {
        final ServerSocketImpl server = new ServerSocketImpl();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ServerForm(server);
            }
        });
    }
}
