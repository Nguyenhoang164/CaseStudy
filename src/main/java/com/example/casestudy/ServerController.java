package com.example.casestudy;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.*;

public class ServerController  {
    @FXML
    private TextField Item;
    @FXML
    private Button Add;
    @FXML
    private Label ChatInput;
    String Message;
    private String localhost = "localhost";
    private String dbname = "Message_data";
    private String username = "root";
    private String password = "l";
    private String URL = "jdbc:mysql://" + localhost + "/" + dbname;
    String message;
    InetAddress inetAddress;
    int Port;

        @FXML
    public void AddMessage() throws SQLException {
        this.ChatInput.setText(" ");
        this.Message = this.Item.getText();
        this.message = "";
        //Connected to Database
        Connection connection = DriverManager.getConnection(URL, username, password);
        String query = "insert into MessageDatabase(Message) values (?)";
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Server :" + this.Message);
        int row = preparedStatement.executeUpdate();
        if (row != 0) {
            Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
            alertConfirm.setHeaderText("thanh cong");
            alertConfirm.setContentText("tin nhan da duoc gui");
            alertConfirm.show();
            this.Item.setText("");
        } else {
            System.out.println("loi");
        }

            String queryOut = "Select Message from MessageDatabase";
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryOut);

            while (resultSet.next()) {
                this.message += resultSet.getString("Message") + "\n";

            }
            for (int y = 0; y < this.message.length(); y++) {
                this.ChatInput.setText(message);
            }

        }
//    @Override
//    public void run() {
//        do{
//            try {
//                DatagramSocket datagramSocket = new DatagramSocket(Port);
//                byte[] data = new byte[1024];
//                DatagramPacket datagramPacketRead = new DatagramPacket(data, data.length);
//                datagramSocket.receive(datagramPacketRead);
//                this.inetAddress = datagramPacketRead.getAddress();
//                this.Port = datagramPacketRead.getPort();
//                message = new String(datagramPacketRead.getData());
//
//                System.out.println("tin nhan gui den" + message);
//
//                this.message = "";
//                String queryOut = "Select Message from MessageDatabase";
//                Statement statement = null;
//                Connection connection = DriverManager.getConnection(URL, username, password);
//                statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(queryOut);
//
//                while (resultSet.next()) {
//                    this.message += resultSet.getString("Message") + "\n";
//                }
//
//                for (int y = 0; y < this.message.length(); y++) {
//                    this.ChatInput.setText(message);
//                }
//            } catch (SQLException | IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }while (message.isEmpty());
//    }
//    @FXML
//    public void AddMessage() throws IOException, SQLException {
//        this.ChatInput.setText(" ");
//        this.Message = this.Item.getText();
//        byte[] Data;
//        DatagramSocket datagramSocket = new DatagramSocket();
//        Data = Message.getBytes();
//        DatagramPacket datagramPacketSend = new DatagramPacket(Data, Data.length, inetAddress, Port);
//        datagramSocket.send(datagramPacketSend);
//        datagramSocket.close();
//        Connection connection = DriverManager.getConnection(URL, username, password);
//        String query = "insert into MessageDatabase(Message) values (?)";
//        PreparedStatement preparedStatement = null;
//        preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, "Client :" + this.Message);
//        int row = preparedStatement.executeUpdate();
//        if (row != 0) {
//            Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
//            alertConfirm.setHeaderText("thanh cong");
//            alertConfirm.setContentText("tin nhan da duoc gui");
//            alertConfirm.show();
//            this.Item.setText("");
//        } else {
//            System.out.println("loi");
//        }
//    }


}
