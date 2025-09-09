package Signin;

import Customer.Customer;
import Customer.ManageCustomer;
import Item.Item;
import Item.ManageItem;
import Item.MyListener;
import Return.ManageRentCount;
import Return.RentCount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    //----- Home tab -----
    @FXML
    private ImageView ivStore;
    @FXML
    private ImageView itemIV;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label itemID;
    @FXML
    private Label rentalFee;
    @FXML
    private Label title;
    @FXML
    private Label rentalType;
    @FXML
    private Label loanType;
    @FXML
    private Label quantity;
    String currentID;



    private Image game = new Image("game1.png");
    private Image dvd = new Image("dvd1.png");
    private Image record = new Image("record1.png");
    private Image store = new Image("store.png");
    @FXML
    private Label rentMessage;

    public void loadAllItem() {
        ivStore.setImage(store);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rentalList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                currentID = String.valueOf(rentalList.getSelectionModel().getSelectedItem());

                labelID.setText(currentID);
                loadItemInfo();

            }
        });

        if (ManageItem.items.size() > 0) {
            setChosenItem(ManageItem.items.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Item item) {
                    setChosenItem(item);
                }
            };
        }
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < ManageItem.items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(ManageItem.items.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRentBT() {
        if (listOfRentals.getItems().size() == 2 && customerType.getText().equalsIgnoreCase("Guest")) {
            rentMessage.setText("Only Regular and VIP customer can borrow more than 2 items at a time");
        }
        if (quantity.getText().equals("0")) {
            rentMessage.setText("Item is not available!");
        }
        else {
            listOfRentals.getItems().add(itemID.getText());
            rentalList.getItems().add(itemID.getText());
            int num = Integer.parseInt(numberOfRentals.getText()) + 1;
            numberOfRentals.setText(String.valueOf(num));
            int remaining = Integer.parseInt(quantity.getText()) - 1;
            quantity.setText(String.valueOf(remaining));
            rentMessage.setText("Item has been added to your Rental List");
            if (customerType.getText().equals("VIP")) {
                if (rewardPoint.getText().equals("100")) {
                    rentMessage.setText("You can rent this item for free!!");
                    rewardPoint.setText("0");
                } else {
                    int rewardCount = Integer.parseInt(rewardPoint.getText()) + 10;
                    rewardPoint.setText(String.valueOf(rewardCount));
                }
            }
            //update item in list
            for (int i = 0; i < ManageItem.items.size(); i++) {
                if (itemID.getText().equalsIgnoreCase(ManageItem.items.get(i).getId())) {
                    ManageItem.items.get(i).setNumOfCopies(Integer.parseInt(quantity.getText()));
                }
            }
            ManageItem.saveFile();
            updateProfile();
            loadProfile();
        }
    }

    //----- Item List -----
    @FXML
    private Label labelID;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelRentalFee;
    @FXML
    private Label labelRentType;
    @FXML
    private Label labelLoanType;
    @FXML
    private ListView rentalList;
    @FXML
    private Label labelQuantity;
    @FXML
    private ImageView ivItem;

    public void loadItemInfo() {
        for (int i = 0; i < ManageItem.items.size(); i++) {
            if (labelID.getText().equalsIgnoreCase(ManageItem.items.get(i).getId())) {
                labelTitle.setText(ManageItem.items.get(i).getTitle());
                labelLoanType.setText(ManageItem.items.get(i).getLoanType());
                labelRentalFee.setText("$" + ManageItem.items.get(i).getFee());
                labelRentType.setText(ManageItem.items.get(i).getRentalType());
                if (labelRentType.getText().equals("Game")) {
                    ivItem.setImage(game);
                }
                if (labelRentType.getText().equals("DVD")) {
                    ivItem.setImage(dvd);
                }
                if (labelRentType.getText().equals("Record")) {
                    ivItem.setImage(record);
                }
                labelQuantity.setText(String.valueOf(ManageItem.items.get(i).getQuantities()));
            }
        }
    }

    public void returnItem() {
        if (Integer.parseInt(numberOfRentals.getText()) > 0) {
            int num = Integer.parseInt(numberOfRentals.getText()) - 1;
            numberOfRentals.setText(String.valueOf(num));
        }

        int count = Integer.parseInt(returnCount.getText()) + 1;
        returnCount.setText(String.valueOf(count));
        for (int i = 0; i < ManageItem.items.size(); i++) {
            if (labelID.getText().equalsIgnoreCase(ManageItem.items.get(i).getId())) {
                int numOfCopies = ManageItem.items.get(i).getQuantities() + 1;
                ManageItem.items.get(i).setNumOfCopies(numOfCopies);
            }
        }
        listOfRentals.getItems().remove(labelID.getText());
        rentalList.getItems().remove(labelID.getText());
        ManageItem.saveFile();
        updateProfile();
        loadProfile();
        loadAllItem();
    }



    //----- Profile Tab -----
    @FXML
    private Label returnCount;
    @FXML
    private Label username;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private Label numberOfRentals;
    @FXML
    private ListView<String> listOfRentals;
    @FXML
    private Label ID;
    @FXML
    private Label customerType;
    @FXML
    private ImageView guestS;
    @FXML
    private ImageView regularS;
    @FXML
    private ImageView VIPS;
    @FXML
    private Label rewardPoint;
    @FXML
    private Label updateMessage;
    private MyListener myListener;

    public void displayUsername(String str) {
        username.setText(str);
    }

    public void loadProfile() {
        listOfRentals.getItems().clear();
        rentalList.getItems().clear();
        for(int i = 0; i < ManageCustomer.userList.size(); i++){
            if(username.getText().equalsIgnoreCase(ManageCustomer.userList.get(i).getUsername())){
                ID.setText(ManageCustomer.userList.get(i).getId());
                name.setText(ManageCustomer.userList.get(i).getName());
                address.setText(ManageCustomer.userList.get(i).getAddress());
                phone.setText(ManageCustomer.userList.get(i).getPhone());
                numberOfRentals.setText(String.valueOf(ManageCustomer.userList.get(i).getNumOfRentals()));
                customerType.setText(ManageCustomer.userList.get(i).getCustomerType());
                password.setText(ManageCustomer.userList.get(i).getPassword());
                if(Integer.parseInt(numberOfRentals.getText()) > 0) {
                    listOfRentals.getItems().addAll(ManageCustomer.userList.get(i).getListRental());
                    rentalList.getItems().addAll(ManageCustomer.userList.get(i).getListRental());
                } else {
                    listOfRentals.getItems().clear();
                    rentalList.getItems().clear();
                }
            }
        }
        for (int i = 0; i < ManageRentCount.countList.size(); i++) {
            if (ID.getText().equalsIgnoreCase(ManageRentCount.countList.get(i).getCustomerId())) {
                returnCount.setText(String.valueOf(ManageRentCount.countList.get(i).getReturnCount()));
                rewardPoint.setText(String.valueOf(ManageRentCount.countList.get(i).getRewardPoint()));
            }
        }
        if (Integer.parseInt(returnCount.getText()) >= 3 && Integer.parseInt(returnCount.getText()) < 5 ) {
            customerType.setText("Regular");
        }
        if (Integer.parseInt(returnCount.getText()) >= 5) {
            customerType.setText("VIP");
        }
        if (customerType.getText().equalsIgnoreCase("Guest")) {
            guestS.setVisible(true);
            regularS.setVisible(false);
            VIPS.setVisible(false);
        } else if (customerType.getText().equalsIgnoreCase("Regular")) {
            guestS.setVisible(false);
            regularS.setVisible(true);
            VIPS.setVisible(false);
        } else {
            guestS.setVisible(false);
            regularS.setVisible(false);
            VIPS.setVisible(true);
        }
    }

    public void updateProfile() {
        ObservableList<String> rentalList = FXCollections.observableArrayList();
        if (listOfRentals.getItems().size() > 0) {
            listOfRentals.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listOfRentals.getSelectionModel().selectAll();
            rentalList = listOfRentals.getSelectionModel().getSelectedItems();
        }
        ArrayList<String> RL = new ArrayList<>();
        for (String str : rentalList) {
            RL.add(str);
        }
        Customer updateCustomer = new Customer(
                ID.getText(),
                name.getText(),
                address.getText(),
                phone.getText(),
                Integer.parseInt(numberOfRentals.getText()),
                customerType.getText(),
                username.getText(),
                password.getText(),
                RL
        );
        if (ManageCustomer.updateUser(updateCustomer)) {
            updateMessage.setText("Profile is updated!");
        }
        RentCount updateRentCount = new RentCount(
                ID.getText(),
                Integer.parseInt(returnCount.getText()),
                Integer.parseInt(rewardPoint.getText())
        );
        if (ManageRentCount.updateRentCount(updateRentCount)) {
            updateMessage.setText("Profile is updated!");
        }
    }

    private void setChosenItem(Item item) {
        itemID.setText(item.getId());
        rentalFee.setText("$" + String.valueOf(item.getFee()));
        title.setText(item.getTitle());
        rentalType.setText(item.getRentalType());
        loanType.setText(item.getLoanType());
        quantity.setText(String.valueOf(item.getQuantities()));
        if (rentalType.getText().equalsIgnoreCase("Game")) {
            itemIV.setImage(game);
        }
        if (rentalType.getText().equalsIgnoreCase("DVD")) {
            itemIV.setImage(dvd);
        }
        if (rentalType.getText().equalsIgnoreCase("Record")) {
            itemIV.setImage(record);
        }
    }





}
