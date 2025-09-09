package Signin;

import Customer.Customer;
import Customer.ManageCustomer;
import Return.ManageRentCount;
import Return.RentCount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class SignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField phoneText;
    @FXML
    private Button createAccountBt;
    @FXML
    private Label message1;
    @FXML
    private Label message2;
    @FXML
    private Label message3;
    @FXML
    private Label message4;
    @FXML
    private Label message5;
    @FXML
    private Button returnHome;
    @FXML
    private Label message;

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    public void setOnCreateAccount() {
        boolean isBlanked = true;
        boolean isValid = true;

        for (int i = 0; i < ManageCustomer.userList.size(); i ++) {
            if (usernameText.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getUsername())) {
                message1.setVisible(true);
                isValid = false;
                message1.setText("Username has been used!");
            }
            else {
                isValid = true;
            }
        }
        if (usernameText.getText().equals("")) {
            message1.setText("This area cannot be left blank");
            message1.setVisible(true);
        }
        else {
            message1.setVisible(false);
        }
        if (passwordText.getText().equals("")) {
            message2.setText("This area cannot be left blank");
            message2.setVisible(true);
        } else {
            message2.setVisible(false);
        }
        if (nameText.getText().equals("")) {
            message3.setText("This area cannot be left blank");
            message3.setVisible(true);
        } else {
            message3.setVisible(false);
        }
        if (addressText.getText().equals("")) {
            message4.setText("This area cannot be left blank");
            message4.setVisible(true);
        } else {
            message4.setVisible(false);
        }
        if (phoneText.getText().equals("")) {
            message5.setText("This area cannot be left blank");
            message5.setVisible(true);
        } else {
            message5.setVisible(false);
        }

        if (!usernameText.getText().equals("")
                && isValid
                && !passwordText.getText().equals("")
                && !nameText.getText().equals("")
                && !addressText.getText().equals("")
                && !phoneText.getText().equals("")) {
            isBlanked = false;
        }

        if (!isBlanked) {
            ArrayList<String> listOfRentals = new ArrayList<>();
            Customer newCustomer = new Customer(ManageCustomer.createNewCustomerID(),
                                                    nameText.getText(),
                                                    addressText.getText(),
                                                    phoneText.getText(),
                                                    0,
                                                    "Guest",
                                                    usernameText.getText(),
                                                    passwordText.getText(),
                                                    listOfRentals);

            RentCount newRentCount = new RentCount(ManageCustomer.createNewCustomerID(),0,0);

            if (ManageCustomer.addUser(newCustomer) && ManageRentCount.addRentCount(newRentCount)) {
                message.setText("Your account has been created successfully!");
                ManageCustomer.saveFile();
                ManageCustomer.displayAllUser();
            } else {
                message.setText("Unique code should not be duplicated!");
            }
        } else {
            message.setText("Cannot create new account!");
        }
    }

}
