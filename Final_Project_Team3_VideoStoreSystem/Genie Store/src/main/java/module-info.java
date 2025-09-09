module com.example.geniestore {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens Signin to javafx.fxml;
    exports Signin;
    exports Customer;
    opens Customer to javafx.fxml;
}