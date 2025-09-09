package Signin;

import Customer.Customer;
import Customer.ManageCustomer;
import Item.ManageItem;
import Return.ManageRentCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminMenuCustomerController implements Initializable {
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-menu-item.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Admin Menu");
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    //----- Search Tab -----
    @FXML
    private TextField searchID;
    @FXML
    private TextField searchName;
    @FXML
    private Label labelID;
    @FXML
    private Label labelName;
    @FXML
    private Label labelAddress;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelNumberOfRentals;
    @FXML
    private Label labelCustomerType;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelListOfRental;
    @FXML
    private Label searchMessage;
    @FXML
    private ImageView guestS;
    @FXML
    private ImageView regularS;
    @FXML
    private ImageView VIPS;


    public void setSearchIDBT() {
        boolean isFound = false;
        String rentalList;
        for(int i = 0; i < ManageCustomer.userList.size(); i++){
            if(searchID.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getId())){
                isFound = true;
                labelID.setText(ManageCustomer.userList.get(i).getId());
                labelName.setText(ManageCustomer.userList.get(i).getName());
                labelAddress.setText(ManageCustomer.userList.get(i).getAddress());
                labelPhone.setText(ManageCustomer.userList.get(i).getPhone());
                labelNumberOfRentals.setText(String.valueOf(ManageCustomer.userList.get(i).getNumOfRentals()));
                labelCustomerType.setText(ManageCustomer.userList.get(i).getCustomerType());
                if (labelCustomerType.getText().equalsIgnoreCase("Guest")) {
                    guestS.setVisible(true);
                    regularS.setVisible(false);
                    VIPS.setVisible(false);
                } else if (labelCustomerType.getText().equalsIgnoreCase("Regular")) {
                    guestS.setVisible(false);
                    regularS.setVisible(true);
                    VIPS.setVisible(false);
                } else {
                    guestS.setVisible(false);
                    regularS.setVisible(false);
                    VIPS.setVisible(true);
                }
                labelUsername.setText(ManageCustomer.userList.get(i).getUsername());
                labelPassword.setText(ManageCustomer.userList.get(i).getPassword());
                if(Integer.parseInt(labelNumberOfRentals.getText()) > 0) {
                    rentalList = ManageCustomer.userList.get(i).getRentalListComponents();
                    labelListOfRental.setText(rentalList);
                } else {
                    labelListOfRental.setText("");
                }
            }
        }

        if (!isFound) {
            searchMessage.setText("Customer is not exist!");
            labelName.setText("");
            labelAddress.setText("");
            labelPhone.setText("");
            labelNumberOfRentals.setText("");
            labelCustomerType.setText("");
            labelUsername.setText("");
            labelPassword.setText("");
            labelListOfRental.setText("");
        } else {
            searchMessage.setText("");
            searchName.setText("");
        }
    }
    public void setSearchNameBT() {
        String rentalList = "";
        boolean isFound = false;
        for(int i = 0; i < ManageCustomer.userList.size(); i++){
            if(searchName.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getName())){
                isFound = true;
                labelID.setText(ManageCustomer.userList.get(i).getId());
                labelName.setText(ManageCustomer.userList.get(i).getName());
                labelAddress.setText(ManageCustomer.userList.get(i).getAddress());
                labelPhone.setText(ManageCustomer.userList.get(i).getPhone());
                labelNumberOfRentals.setText(String.valueOf(ManageCustomer.userList.get(i).getNumOfRentals()));
                labelCustomerType.setText(ManageCustomer.userList.get(i).getCustomerType());
                if (labelCustomerType.getText().equalsIgnoreCase("Guest")) {
                    guestS.setVisible(true);
                    regularS.setVisible(false);
                    VIPS.setVisible(false);
                } else if (labelCustomerType.getText().equalsIgnoreCase("Regular")) {
                    guestS.setVisible(false);
                    regularS.setVisible(true);
                    VIPS.setVisible(false);
                } else {
                    guestS.setVisible(false);
                    regularS.setVisible(false);
                    VIPS.setVisible(true);
                }
                labelUsername.setText(ManageCustomer.userList.get(i).getUsername());
                labelPassword.setText(ManageCustomer.userList.get(i).getPassword());
                if(Integer.parseInt(labelNumberOfRentals.getText()) > 0) {
                    rentalList = ManageCustomer.userList.get(i).getRentalListComponents();
                    labelListOfRental.setText(rentalList);
                } else {
                    labelListOfRental.setText("");
                }
                System.out.println(rentalList);
            }
        }

        if (!isFound) {
            searchMessage.setText("Customer is not exist!");
            labelName.setText("");
            labelAddress.setText("");
            labelPhone.setText("");
            labelNumberOfRentals.setText("");
            labelCustomerType.setText("");
            labelUsername.setText("");
            labelPassword.setText("");
            labelListOfRental.setText("");
        } else {
            searchMessage.setText("");
            searchID.setText("");
        }
    }

    //----- Update Tab -----
    @FXML
    private TextField updateID;
    @FXML
    private TextField updateName;
    @FXML
    private ChoiceBox<String> updateCustomerType;
    private final String[] customerTypes = {"Guest", "Regular", "VIP"};
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updatePhone;
    @FXML
    private TextField updateNumOfRentals;
    @FXML
    private TextField updateUsername;
    @FXML
    private TextField updatePassword;
    @FXML
    private ListView<String> updateListOfRentals;
    @FXML
    private Label updateMessage;
    @FXML
    private TextField itemID1;
    @FXML
    private TextField itemID2;

    public void setUpdateLoadBT() {
        boolean isFound = false;
        updateListOfRentals.getItems().clear();
        for(int i = 0; i < ManageCustomer.userList.size(); i++){
            if(updateID.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getId())){
                isFound = true;
                updateID.setText(ManageCustomer.userList.get(i).getId());
                updateName.setText(ManageCustomer.userList.get(i).getName());
                updateAddress.setText(ManageCustomer.userList.get(i).getAddress());
                updatePhone.setText(ManageCustomer.userList.get(i).getPhone());
                updateNumOfRentals.setText(String.valueOf(ManageCustomer.userList.get(i).getNumOfRentals()));
                updateCustomerType.setValue(ManageCustomer.userList.get(i).getCustomerType());
                updateUsername.setText(ManageCustomer.userList.get(i).getUsername());
                updatePassword.setText(ManageCustomer.userList.get(i).getPassword());
                if(Integer.parseInt(updateNumOfRentals.getText()) > 0) {
                    updateListOfRentals.getItems().addAll(ManageCustomer.userList.get(i).getListRental());
                } else {
                    updateListOfRentals.getItems().clear();
                }
            }
        }

        if (!isFound) {
            updateMessage.setText("Customer is not exist!");
            updateName.setText("");
            updateAddress.setText("");
            updatePhone.setText("");
            updateNumOfRentals.setText("");
            updateCustomerType.setValue(null);
            updateUsername.setText("");
            updatePassword.setText("");
            updateListOfRentals.getItems().clear();
        } else {
            updateMessage.setText("Customer can be updated now!");
        }
    }
    public void setUpdateBT() {
        ObservableList<String> rentalList = FXCollections.observableArrayList();
        if (updateListOfRentals.getItems().size() > 0) {
            updateListOfRentals.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            updateListOfRentals.getSelectionModel().selectAll();
            rentalList = updateListOfRentals.getSelectionModel().getSelectedItems();
        }
        ArrayList<String> RL = new ArrayList<>();
        for (String str : rentalList) {
            RL.add(str);
        }
        Customer updateCustomer = new Customer(
                updateID.getText(),
                updateName.getText(),
                updateAddress.getText(),
                updatePhone.getText(),
                Integer.parseInt(updateNumOfRentals.getText()),
                updateCustomerType.getValue(),
                updateUsername.getText(),
                updatePassword.getText(),
                RL
        );
        if (ManageCustomer.updateUser(updateCustomer)) {
            updateMessage.setText("Customer is updated!");
            ManageCustomer.displayAllUser();
        }
    }

    public void setAddItem() {
        if (ManageItem.checkIdFormat(itemID1.getText())) {
            updateListOfRentals.getItems().add(itemID1.getText());
            itemID2.setText("");
            int num = Integer.parseInt(updateNumOfRentals.getText()) + 1;
            updateNumOfRentals.setText(String.valueOf(num));
            updateMessage.setText("Item is added!");
        } else {
            updateMessage.setText("Invalid item ID format!");
        }
    }

    public void setRemoveItem() {
        if (ManageItem.checkIdFormat(itemID2.getText())) {
            updateListOfRentals.getItems().remove(itemID2.getText());
            itemID1.setText("");
            int num = Integer.parseInt(updateNumOfRentals.getText()) - 1;
            updateNumOfRentals.setText(String.valueOf(num));
            updateMessage.setText("Item is removed!");
        } else {
            updateMessage.setText("Invalid item ID format!");
        }

    }

    //----- Delete tab -----
    @FXML
    private TextField deleteTF;
    @FXML
    private Label deleteMessage;

    public void deleteItem () {
        if (ManageCustomer.removeUser(deleteTF.getText()) && ManageRentCount.removeRentCount(deleteTF.getText())) {
            deleteMessage.setText("Customer is deleted successfully!");
        } else {
            deleteMessage.setText("Customer is not exist!");
        }
    }

    //----- All customer Tab -----
    @FXML
    private RadioButton sortByID;
    @FXML
    private RadioButton sortByName;
    @FXML
    private TextArea allCustomers;

    public void setLoadAllCustomers() {
        allCustomers.setText("");
        for (int i = 0; i < ManageCustomer.userList.size(); i++) {
            allCustomers.appendText("ID: " + ManageCustomer.userList.get(i).getId() + "\n");
            allCustomers.appendText("Name: " + ManageCustomer.userList.get(i).getName() + "\n");
            allCustomers.appendText("Address: " + ManageCustomer.userList.get(i).getAddress() + "\n");
            allCustomers.appendText("Phone: " + ManageCustomer.userList.get(i).getPhone() + "\n");
            allCustomers.appendText("Number of Rentals: " + ManageCustomer.userList.get(i).getNumOfRentals() + "\n");
            allCustomers.appendText("Customer type: " + ManageCustomer.userList.get(i).getCustomerType() + "\n");
            allCustomers.appendText("Username: " + ManageCustomer.userList.get(i).getUsername() + "\n");
            allCustomers.appendText("Password: " + ManageCustomer.userList.get(i).getPassword() + "\n");
            allCustomers.appendText("List of Rentals: " + "\n");
            for (String item : ManageCustomer.userList.get(i).getListRental()) {
                allCustomers.appendText(item + "\n");
            }
            allCustomers.appendText("\n");
        }
    }
    public void setRadio1() {
        ManageCustomer.sort(ManageCustomer.SORT_BY_ID);
        setLoadAllCustomers();
        sortByName.setSelected(false);
    }
    public void setRadio2() {
        ManageCustomer.sort(ManageCustomer.SORT_BY_NAME);
        setLoadAllCustomers();
        sortByID.setSelected(false);
    }

    //----- Customer type -----
    @FXML
    private TextArea guestTA;
    @FXML
    private TextArea regularTA;
    @FXML
    private TextArea VIPTA;
    @FXML
    private ImageView iv1;
    @FXML
    private ImageView iv2;
    @FXML
    private ImageView iv3;
    
    public void loadAll() {
        guestTA.setText("");
        regularTA.setText("");
        VIPTA.setText("");
        iv1.setVisible(true);
        iv2.setVisible(true);
        iv3.setVisible(true);
        for (int i = 0; i < ManageCustomer.userList.size(); i++) {
            if (ManageCustomer.userList.get(i).getCustomerType().equalsIgnoreCase("Guest")) {
                guestTA.appendText("ID: " + ManageCustomer.userList.get(i).getId() + "\n");
                guestTA.appendText("Name: " + ManageCustomer.userList.get(i).getName() + "\n");
                guestTA.appendText("Address: " + ManageCustomer.userList.get(i).getAddress() + "\n");
                guestTA.appendText("Phone: " + ManageCustomer.userList.get(i).getPhone() + "\n");
                guestTA.appendText("Number of Rentals: " + ManageCustomer.userList.get(i).getNumOfRentals() + "\n");
                guestTA.appendText("Customer type: " + ManageCustomer.userList.get(i).getCustomerType() + "\n");
                guestTA.appendText("Username: " + ManageCustomer.userList.get(i).getUsername() + "\n");
                guestTA.appendText("Password: " + ManageCustomer.userList.get(i).getPassword() + "\n");
                guestTA.appendText("List of Rentals: " + "\n");
                for (String item : ManageCustomer.userList.get(i).getListRental()) {
                    guestTA.appendText(item + "\n");
                }
                guestTA.appendText("\n");
            }
            if (ManageCustomer.userList.get(i).getCustomerType().equalsIgnoreCase("Regular")) {
                regularTA.appendText("ID: " + ManageCustomer.userList.get(i).getId() + "\n");
                regularTA.appendText("Name: " + ManageCustomer.userList.get(i).getName() + "\n");
                regularTA.appendText("Address: " + ManageCustomer.userList.get(i).getAddress() + "\n");
                regularTA.appendText("Phone: " + ManageCustomer.userList.get(i).getPhone() + "\n");
                regularTA.appendText("Number of Rentals: " + ManageCustomer.userList.get(i).getNumOfRentals() + "\n");
                regularTA.appendText("Customer type: " + ManageCustomer.userList.get(i).getCustomerType() + "\n");
                regularTA.appendText("Username: " + ManageCustomer.userList.get(i).getUsername() + "\n");
                regularTA.appendText("Password: " + ManageCustomer.userList.get(i).getPassword() + "\n");
                regularTA.appendText("List of Rentals: " + "\n");
                for (String item : ManageCustomer.userList.get(i).getListRental()) {
                    regularTA.appendText(item + "\n");
                }
                regularTA.appendText("\n");
            }
            if (ManageCustomer.userList.get(i).getCustomerType().equalsIgnoreCase("VIP")) {
                VIPTA.appendText("ID: " + ManageCustomer.userList.get(i).getId() + "\n");
                VIPTA.appendText("Name: " + ManageCustomer.userList.get(i).getName() + "\n");
                VIPTA.appendText("Address: " + ManageCustomer.userList.get(i).getAddress() + "\n");
                VIPTA.appendText("Phone: " + ManageCustomer.userList.get(i).getPhone() + "\n");
                VIPTA.appendText("Number of Rentals: " + ManageCustomer.userList.get(i).getNumOfRentals() + "\n");
                VIPTA.appendText("Customer type: " + ManageCustomer.userList.get(i).getCustomerType() + "\n");
                VIPTA.appendText("Username: " + ManageCustomer.userList.get(i).getUsername() + "\n");
                VIPTA.appendText("Password: " + ManageCustomer.userList.get(i).getPassword() + "\n");
                VIPTA.appendText("List of Rentals: " + "\n");
                for (String item : ManageCustomer.userList.get(i).getListRental()) {
                    VIPTA.appendText(item + "\n");
                }
                VIPTA.appendText("\n");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCustomerType.getItems().addAll(customerTypes);
    }
}
