package Signin;

import Customer.ManageCustomer;
import Customer.ManageCustomer;
import Item.ManageItem;
import Return.ManageRentCount;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("HOME");
            Image icon = new Image("store.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ManageItem.readFile();
        ManageCustomer.readFile();
        ManageRentCount.readFile();
        ManageCustomer.displayAllUser();

        launch(args);

    }
}