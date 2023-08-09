package com.example.casestudy;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    private String localhost = "localhost:3306";
    private String dbname = "Message_data";
    private String username = "root";
    private String password = "l";
    private String URLConnected =  "jdbc:mysql://"+ localhost+"/"+dbname;

    public Connection connection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URLConnected,username,password);
            System.out.println("connection to database success");
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        return connection;
    }
    public void ConnectClient(){
        DatagramSocket ServerSocket = null;
        DatagramPacket ServerPacketSend = null;
        DatagramPacket ServerPacketRead = null;
        byte [] data = null;
        int ServerPort = 8080;
        try{
            data = new byte[66535];
            ServerSocket = new DatagramSocket(ServerPort);
            ServerPacketRead = new DatagramPacket(data, data.length);
            ServerSocket.receive(ServerPacketRead);
            String message = new String(ServerPacketRead.getData(),0,ServerPacketRead.getData().length);
            System.out.println("nhan du lieu thanh cong ");


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        Connection connection = server.connection();
    }
}
