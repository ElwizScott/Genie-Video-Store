package Signin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSignInController {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "123";
    @FXML
    private TextField passwordText;
    @FXML
    private Button signInBt;
    @FXML
    private Button accessBt;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordHidden;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label errorMessage;

    public void changeVisibility(ActionEvent event) {
        if (checkBox.isSelected()) {
            passwordText.setText(passwordHidden.getText());
            passwordText.setVisible(true);
            passwordHidden.setVisible(false);
            return;
        }
        passwordHidden.setText(passwordText.getText());
        passwordHidden.setVisible(true);
        passwordText.setVisible(false);
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    public void login() {
        if (usernameText.getText().equals(ADMIN_USERNAME)) {
            if (passwordHidden.getText().equals(ADMIN_PASSWORD) || passwordText.getText().equals(ADMIN_PASSWORD)) {
                accessBt.setVisible(true);
                signInBt.setVisible(false);
            } else {
                errorMessage.setText("Invalid Password!");
            }
        } else {
            errorMessage.setText("Invalid Username!");
        }
    }

    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-menu-item.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Admin Menu");
        stage.setScene(scene);
        stage.show();
    }

}
