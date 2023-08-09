package com.example.casestudy;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class ServerController {
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
    private String URL = "jdbc:mysql://"+ localhost+"/"+dbname;
    String message;
    @FXML
    public void AddMessage() throws SQLException {
        this.ChatInput.setText(" ");
        this.Message = this.Item.getText();
        this.message = "";
        //Connected to Database
        Connection connection = DriverManager.getConnection(URL,username,password);
        String query = "insert into MessageDatabase(Message) values (?)";
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,"Client 1 :" + this.Message);
        int row = preparedStatement.executeUpdate();
        if (row != 0){
            Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
            alertConfirm.setHeaderText("thanh cong");
            alertConfirm.setContentText("tin nhan da duoc gui");
            alertConfirm.show();
            this.Item.setText("");
        }else{
            System.out.println("loi");
        }
        String queryOut = "Select Message from MessageDatabase";
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryOut);

        while (resultSet.next()){
            this.message += resultSet.getString("Message")+"\n";
        }
        for (int i = 0 ; i < this.message.length() ; i ++){
            ChatInput.setText(message);
        }



    }

}