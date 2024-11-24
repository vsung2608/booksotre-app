package com.booksotre.controller.user;

import com.booksotre.model.BookModel;
import com.booksotre.model.CartItemModel;
import com.booksotre.model.CartModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.ICartService;
import com.booksotre.service.impl.CartService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML
    private VBox containerCartItem;

    @FXML
    private Label total_price;

    @FXML
    private Label name;

    private final ICartService cartService = new CartService();

    public void setData(){
        HBox.setHgrow(name, Priority.ALWAYS);
        name.setMaxWidth(Double.MAX_VALUE);
        CartModel cart = cartService.findByCustomerId(OrderTamp.customerId);
        OrderTamp.cartId = cart.getCartId();
        List<CartItemModel> listCartItem = cartService.findByOrderId(cart.getCartId());
        for (CartItemModel item : listCartItem) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/user/CartItemFXML.fxml"));
            HBox box = null;
            try {
                box = loader.load();
                box.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(box, Priority.ALWAYS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CartItemController c = loader.getController();
            c.setData(item);
            containerCartItem.getChildren().add(box);
        }
        total_price.setText(cart.getTotalPrice().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }
}
