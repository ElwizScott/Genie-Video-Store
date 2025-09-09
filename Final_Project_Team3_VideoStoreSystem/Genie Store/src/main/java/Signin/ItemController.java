package Signin;

import Item.Item;
import Item.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {
    private Image game = new Image("game1.png");
    private Image dvd = new Image("dvd1.png");
    private Image record = new Image("record1.png");

    @FXML
    private Label fee;

    @FXML
    private ImageView iv;

    @FXML
    private Label title;
    @FXML
    private Label rentalType;
    private Item item;
    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(item);
    }

    public void setData(Item item, MyListener myListener) {
        this.item = item;
        this.myListener = myListener;
        title.setText(item.getTitle());
        fee.setText(String.valueOf(item.getFee()));
        rentalType.setText(item.getRentalType());
        if (rentalType.getText().equalsIgnoreCase("Game")) {
            iv.setImage(game);
        }
        if (rentalType.getText().equalsIgnoreCase("DVD")) {
            iv.setImage(dvd);
        }
        if (rentalType.getText().equalsIgnoreCase("Record")) {
            iv.setImage(record);
        }
    }
}
