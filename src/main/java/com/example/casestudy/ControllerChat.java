package com.example.casestudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class ControllerChat implements Initializable {
    @FXML
    private VBox VboxMain;
    @FXML
    private Label TileLbl;
    @FXML
    private ListView<String> listBoxMain;
    @FXML
    private TextField Item;
    @FXML
    private Button Add;
    final ObservableList<String> list = FXCollections.observableArrayList();;
    int ClientPort = 8080;
    String ClientIP = "localhost";
    String Message;
    @FXML
    public void ConnectToServer() throws IOException {
        DatagramSocket ClientSocket = null;
        DatagramPacket ClientSendMessage = null;
        DatagramPacket ClientReadMessage = null;
        byte [] ClientSend = null;
        while (true){
            //push Message to Server
            ClientSocket = new DatagramSocket();
            ClientSend = Message.getBytes();
            InetAddress inetAddress = InetAddress.getByName(ClientIP);
            ClientSendMessage = new DatagramPacket(ClientSend,ClientSend.length,inetAddress,ClientPort);
            ClientSocket.send(ClientSendMessage);

        }

    }
    @FXML
    public void AddMessage(){
        this.Message = this.Item.getText();

        list.add("Client 1 : " + Message);
        Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
        alertConfirm.setHeaderText("thanh cong");
        alertConfirm.setContentText("tin nhan da duoc gui");
        alertConfirm.show();
        this.Item.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listBoxMain.setItems(list);
    }
}