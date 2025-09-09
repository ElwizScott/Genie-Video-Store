package Signin;

import Item.*;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuItemController implements Initializable {

    public AdminMenuItemController() throws FileNotFoundException {}
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-menu-customer.fxml"));
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

    //----- Search tab -----
    @FXML
    private TextField searchID;
    @FXML
    private TextField searchTitle;
    @FXML
    private Label labelID;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelRentType;
    @FXML
    private Label labelLoanType;
    @FXML
    private Label labelQuantity;
    @FXML
    private Label labelRentalFee;
    @FXML
    private Label labelGenre;
    @FXML
    private Label labelStatus;
    @FXML
    private Label searchMessage;
    @FXML
    private ImageView controllerS;
    @FXML
    private ImageView recordS;
    @FXML
    private ImageView DVDS;


    public void setSearchIDBT() {
        boolean isFound = false;
        for(int i = 0; i < ManageItem.items.size(); i++){
            if(searchID.getText().equalsIgnoreCase(ManageItem.items.get(i).getId())){
                isFound = true;
                labelID.setText(ManageItem.items.get(i).getId());
                labelTitle.setText(ManageItem.items.get(i).getTitle());
                labelRentType.setText(ManageItem.items.get(i).getRentalType());
                if (labelRentType.getText().equalsIgnoreCase("Game")) {
                    controllerS.setVisible(true);
                    recordS.setVisible(false);
                    DVDS.setVisible(false);
                } else if (labelRentType.getText().equalsIgnoreCase("Record")) {
                    controllerS.setVisible(false);
                    recordS.setVisible(true);
                    DVDS.setVisible(false);
                } else {
                    controllerS.setVisible(false);
                    recordS.setVisible(false);
                    DVDS.setVisible(true);
                }
                labelLoanType.setText(ManageItem.items.get(i).getLoanType());
                labelQuantity.setText(String.valueOf(ManageItem.items.get(i).getQuantities()));
                if (labelQuantity.getText().equals("0")) {
                    labelStatus.setText("Out of stock");
                } else {
                    labelStatus.setText("Available");
                }
                labelRentalFee.setText(String.valueOf(ManageItem.items.get(i).getFee()));
                if (ManageItem.items.get(i) instanceof RecordDVD) {
                    labelGenre.setText(((RecordDVD) ManageItem.items.get(i)).getGenres());
                }
            }
        }

        if (!isFound) {
            searchMessage.setText("Item is not exist!");
            labelTitle.setText("");
            labelRentType.setText("");
            labelLoanType.setText("");
            labelQuantity.setText("");
            labelRentalFee.setText("");
            labelGenre.setText("");
            labelStatus.setText("");
        } else {
            searchMessage.setText("");
            searchTitle.setText("");
        }
    }
    public void setSearchTitleBT() {
        boolean isFound = false;
        for(int i = 0; i < ManageItem.items.size(); i++){
            if(searchTitle.getText().equalsIgnoreCase(ManageItem.items.get(i).getTitle())){
                isFound = true;
                labelID.setText(ManageItem.items.get(i).getId());
                labelTitle.setText(ManageItem.items.get(i).getTitle());
                labelRentType.setText(ManageItem.items.get(i).getRentalType());
                if (labelRentType.getText().equalsIgnoreCase("Game")) {
                    controllerS.setVisible(true);
                    recordS.setVisible(false);
                    DVDS.setVisible(false);
                } else if (labelRentType.getText().equalsIgnoreCase("Record")) {
                    controllerS.setVisible(false);
                    recordS.setVisible(true);
                    DVDS.setVisible(false);
                } else {
                    controllerS.setVisible(false);
                    recordS.setVisible(false);
                    DVDS.setVisible(true);
                }
                labelLoanType.setText(ManageItem.items.get(i).getLoanType());
                labelQuantity.setText(String.valueOf(ManageItem.items.get(i).getQuantities()));
                if (labelQuantity.getText().equals("0")) {
                    labelStatus.setText("Out of stock");
                } else {
                    labelStatus.setText("Available");
                }
                labelRentalFee.setText(String.valueOf(ManageItem.items.get(i).getFee()));
                if (ManageItem.items.get(i) instanceof RecordDVD) {
                    labelGenre.setText(((RecordDVD) ManageItem.items.get(i)).getGenres());
                }
            }
        }

        if (!isFound) {
            searchMessage.setText("Item is not exist!");
            labelTitle.setText("");
            labelRentType.setText("");
            labelLoanType.setText("");
            labelQuantity.setText("");
            labelRentalFee.setText("");
            labelGenre.setText("");
            labelStatus.setText("");
        } else {
            searchMessage.setText("");
            searchID.setText("");
        }
    }

    //----- Add tab -----
    @FXML
    private TextField IDText;
    @FXML
    private TextField titleText;
    @FXML
    private ChoiceBox<String> rentTypeChoiceBox;
    private final String[] rentalTypes = {"Record", "DVD", "Game"};
    @FXML
    private ChoiceBox<String> loanTypeChoiceBox;
    private final String[] loanTypes = {"1-week", "2-days"};
    @FXML
    private TextField quantityText;
    @FXML
    private TextField feeText;
    @FXML
    private TextField genreText;
    @FXML
    private Label message;
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
    private Label message6;
    @FXML
    private Label message7;

    public void setOnAddItemBt() {
        //Check for blank
        boolean isBlanked = true;

        if (IDText.getText().equals("")) {
            message1.setText("*Note: This area cannot be left blank*");
            message1.setVisible(true);
        } else if (!ManageItem.checkIdFormat(IDText.getText())) {
            message1.setText("*Note: Invalid ID format*");
            message1.setVisible(true);
        } else {
            message1.setVisible(false);
        }
        if (titleText.getText().equals("")) {
            message2.setVisible(true);
        } else {
            message2.setVisible(false);
        }
        if (rentTypeChoiceBox.getValue() == null) {
            message3.setVisible(true);
        } else {
            message3.setVisible(false);
        }
        if (loanTypeChoiceBox.getValue() == null) {
            message4.setVisible(true);
        } else {
            message4.setVisible(false);
        }
        if (quantityText.getText().equals("")) {
            message5.setVisible(true);
        } else {
            message5.setVisible(false);
        }
        if (feeText.getText().equals("")) {
            message6.setVisible(true);
        } else {
            message6.setVisible(false);
        }
        if (genreText.getText().equals("")) {
            message7.setVisible(true);
        } else {
            message7.setVisible(false);
        }
        if (rentTypeChoiceBox.getValue() != null && rentTypeChoiceBox.getValue().equalsIgnoreCase("Game")) {
            genreText.setText("none");
        }
        if (!IDText.getText().equals("")
                && !titleText.getText().equals("")
                && rentTypeChoiceBox.getValue() != null
                && loanTypeChoiceBox.getValue() != null
                && !quantityText.getText().equals("")
                && !feeText.getText().equals("")
                && !genreText.getText().equals("")) {
            isBlanked = false;
        }

        if (!isBlanked) {
            Item item = new Item(IDText.getText(),
                    titleText.getText(),
                    rentTypeChoiceBox.getValue(),
                    loanTypeChoiceBox.getValue(),
                    Integer.parseInt(quantityText.getText()),
                    Double.parseDouble(feeText.getText()));

            if (!rentTypeChoiceBox.getValue().equalsIgnoreCase("Game")) {
                RecordDVD recordDVD = new RecordDVD(item);
                recordDVD.setGenres(genreText.getText());
                item = recordDVD;
            }


            if (ManageItem.addItem(item)) {
                message.setText("Item is added successfully!");
            } else {
                message.setText("Unique code should not be duplicated!");
            }
        } else {
            message.setText("Item cannot be added!");
        }
    }

    //----- Update Tab -----
    @FXML
    private TextField updateID;
    @FXML
    private TextField updateTitle;
    @FXML
    private ChoiceBox<String> updateRentType;
    @FXML
    private ChoiceBox<String> updateLoanType;
    @FXML
    private TextField updateQuantity;
    @FXML
    private TextField updateRentalFee;
    @FXML
    private TextField updateGenre;
    @FXML
    private Label updateMessage;
    public void setUpdateLoadBT() {
        boolean isFound = false;
        for(int i = 0; i < ManageItem.items.size(); i++){
            if(updateID.getText().equalsIgnoreCase(ManageItem.items.get(i).getId())){
                isFound = true;
                updateTitle.setText(ManageItem.items.get(i).getTitle());
                updateRentType.setValue(ManageItem.items.get(i).getRentalType());
                updateLoanType.setValue(ManageItem.items.get(i).getLoanType());
                updateQuantity.setText(String.valueOf(ManageItem.items.get(i).getQuantities()));
                updateRentalFee.setText(String.valueOf(ManageItem.items.get(i).getFee()));
                if (ManageItem.items.get(i) instanceof RecordDVD) {
                    updateGenre.setText(((RecordDVD) ManageItem.items.get(i)).getGenres());
                }
            }
        }

        if (!isFound) {
            updateMessage.setText("Item is not exist!");
            updateTitle.setText("");
            updateRentType.setValue(null);
            updateLoanType.setValue(null);
            updateQuantity.setText("");
            updateRentalFee.setText("");
            updateGenre.setText("");
        } else {
            updateMessage.setText("Item can be updated now!");
        }
    }
    public void setUpdateBT() {
        Item updateItem = new Item(
                updateID.getText(),
                updateTitle.getText(),
                updateRentType.getValue(),
                updateLoanType.getValue(),
                Integer.parseInt(updateQuantity.getText()),
                Double.parseDouble(updateRentalFee.getText())
        );
        if (!updateRentType.getValue().equalsIgnoreCase("Game")) {
            RecordDVD recordDVD = new RecordDVD(updateItem);
            recordDVD.setGenres(updateGenre.getText());
            updateItem = recordDVD;
        }

        if (ManageItem.updateItem(updateItem)) {
            updateMessage.setText("Item is updated!");
        }
    }

    //----- Delete Tab -----
    @FXML
    private TextField deleteTF;
    @FXML
    private Label deleteMessage;

    public void deleteItem () {
        if (ManageItem.removeItem(deleteTF.getText())) {
            deleteMessage.setText("Item is deleted successfully!");
        } else {
            deleteMessage.setText("Item is not exist!");
        }
    }

    //----- All item Tab -----
    @FXML
    private RadioButton sortByID;
    @FXML
    private RadioButton sortByTitle;
    @FXML
    private TextArea allItems;

    public void setLoadAllBT() {
        allItems.setText("");
        for (int i = 0; i < ManageItem.items.size(); i++) {
            allItems.appendText("ID: " + ManageItem.items.get(i).getId() + "\n");
            allItems.appendText("Title: " + ManageItem.items.get(i).getTitle() + "\n");
            allItems.appendText("Rent type: " + ManageItem.items.get(i).getRentalType() + "\n");
            allItems.appendText("Loan type: " + ManageItem.items.get(i).getLoanType() + "\n");
            allItems.appendText("Quantity: " + ManageItem.items.get(i).getQuantities() + "\n");
            allItems.appendText("Rental fee: " + ManageItem.items.get(i).getFee() + "\n");
            if (ManageItem.items.get(i) instanceof RecordDVD) {
                allItems.appendText("Genre: " + ((RecordDVD) ManageItem.items.get(i)).getGenres() + "\n");
            }
            allItems.appendText("\n");
        }
    }
    public void setRadio1() {
        ManageItem.sort(ManageItem.SORT_BY_ID);
        setLoadAllBT();
        sortByTitle.setSelected(false);
    }
    public void setRadio2() {
        ManageItem.sort(ManageItem.SORT_BY_TITLE);
        setLoadAllBT();
        sortByID.setSelected(false);
    }

    //----- Out of stock items Tab -----
    @FXML
    private TextArea outOfStock;

    public void setCheckBt() {
        outOfStock.setText("");
        for(int i = 0; i < ManageItem.items.size(); i++) {
            if (ManageItem.items.get(i).getQuantities() == 0) {
                outOfStock.appendText("ID: " + ManageItem.items.get(i).getId() + "\n");
                outOfStock.appendText("Title: " + ManageItem.items.get(i).getTitle() + "\n");
                outOfStock.appendText("Rent type: " + ManageItem.items.get(i).getRentalType() + "\n");
                outOfStock.appendText("Loan type: " + ManageItem.items.get(i).getLoanType() + "\n");
                outOfStock.appendText("Quantity: " + ManageItem.items.get(i).getQuantities() + "\n");
                outOfStock.appendText("Rental fee: " + ManageItem.items.get(i).getFee() + "\n");
                if (ManageItem.items.get(i) instanceof RecordDVD) {
                    outOfStock.appendText("Genre: " + ((RecordDVD) ManageItem.items.get(i)).getGenres() + "\n");
                }
                outOfStock.appendText("\n");
                outOfStock.appendText("-------------------\n");
                outOfStock.appendText("\n");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rentTypeChoiceBox.getItems().addAll(rentalTypes);
        loanTypeChoiceBox.getItems().addAll(loanTypes);
        updateRentType.getItems().addAll(rentalTypes);
        updateLoanType.getItems().addAll(loanTypes);
    }


}
