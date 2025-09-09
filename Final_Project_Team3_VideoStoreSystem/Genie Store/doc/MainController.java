package Signin;

import Customer.ManageCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;


public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ImageView background;
    @FXML
    private Button mainAdminSignInBt;
    @FXML
    private TextField mainPasswordText;
    @FXML
    private Button mainSignInBt;
    @FXML
    private Button mainAccessBt;
    @FXML
    private Button mainSignUpBt;
    @FXML
    private TextField mainUsernameText;
    @FXML
    private PasswordField mainPasswordHidden;
    @FXML
    private CheckBox mainCheckBox;
    @FXML
    private Label mainErrorMessage;


    public void setBackground(Image image) {
        background.setImage(image);
    }
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin-sign-in.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Admin Page");
        stage.setScene(scene);
        stage.show();
    }

    public void changeVisibility() {
        if (mainCheckBox.isSelected()) {
            mainPasswordText.setText(mainPasswordHidden.getText());
            mainPasswordText.setVisible(true);
            mainPasswordHidden.setVisible(false);
            return;
        }
        mainPasswordHidden.setText(mainPasswordText.getText());
        mainPasswordHidden.setVisible(true);
        mainPasswordText.setVisible(false);
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sign-up.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign up");
        stage.setScene(scene);
        stage.show();
    }

    public void login() {
        boolean accessible = false;
        for (int i = 0; i < ManageCustomer.userList.size(); i++) {
            if (mainUsernameText.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getUsername())) {
                if (mainPasswordHidden.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getPassword()) || mainPasswordText.getText().equals(ManageCustomer.userList.get(i).getPassword())) {
                    accessible = true;
                    mainSignInBt.setVisible(false);
                    mainAccessBt.setVisible(true);
                }
            } else {
                mainErrorMessage.setText("Invalid username or password!");
            }
            if (accessible) {
                mainErrorMessage.setText("You can access now!");
            }
        }
    }

    public void switchToCustomerMenu(ActionEvent event) throws IOException {
        String username = mainUsernameText.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-menu.fxml"));
        root = loader.load();

        CustomerMenuController customerMenuController = loader.getController();
        customerMenuController.displayUsername(username);
        customerMenuController.loadAllItem();
        customerMenuController.loadProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}