package com.example.neptunclone.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class dbHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(dbHandler.class);
    public static final String IP = "127.0.0.1";
    public static final String DB = "test";
    public static final String DB_URL = "jdbc:h2:tcp://" + IP + "/~/" + DB + "";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWORD = "";
    public static Connection connection;

    public static Connection getConnection(){
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return connection;
        }catch (Exception e){
            LOGGER.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public static boolean isConnected() {
        boolean connected = false;
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(IP,8082);
        try {
            socket.connect(address, 3000);
            connected = true;
        } catch (IOException e) {
            LOGGER.error(String.valueOf(e));
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.error(String.valueOf(e));
            }
        }
        return connected;
    }

    public static void setConnection(Connection mockConnection) {
    }
}
