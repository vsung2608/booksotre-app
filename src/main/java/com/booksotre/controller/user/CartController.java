package com.booksotre.controller.user;

import com.booksotre.model.*;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICartService;
import com.booksotre.service.IOrderDetailService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CartService;
import com.booksotre.service.impl.OrderDetailService;
import com.booksotre.service.impl.OrderService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @Setter
    private UserController usercontroller;

    @FXML
    private VBox containerCartItem;

    @FXML
    private Label total_price;

    @FXML
    private Label name;

    private final ICartService cartService = new CartService();
    private final IOrderService orderService = new OrderService();
    private final IOrderDetailService orderDetailService = new OrderDetailService();
    private final IBookService bookService = new BookService();

    private Alert alert;

    public void setData(){
        HBox.setHgrow(name, Priority.ALWAYS);
        name.setMaxWidth(Double.MAX_VALUE);
        CartModel cart = cartService.findByCustomerId(OrderTamp.customerId);
        OrderTamp.cartId = cart.getCartId();
        List<CartItemModel> listCartItem = cartService.findByCartId(cart.getCartId());
        containerCartItem.getChildren().clear();
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

    public void deleteAllBtn(){
        List<CartItemModel> listCartItem = cartService.findByCartId(OrderTamp.cartId);
        if(listCartItem == null){
            alert = AlertUnit.generateAlert(AlertInfo.CART_EMPTY);
        }else{
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_DELETE);
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
                cartService.deleteCartItem(OrderTamp.cartId);
                cartService.resetCart(OrderTamp.cartId);
                alert = AlertUnit.generateAlert(AlertInfo.DELETE_CART);
                setData();
            }
        }
    }

    public void addItem(){
        usercontroller.redirectHotBook();
    }

    public void payment(){
        CartModel cart = cartService.findByCustomerId(OrderTamp.customerId);
        List<CartItemModel> carts = cartService.findByCartId(OrderTamp.cartId);
        if(carts == null){
            alert = AlertUnit.generateAlert(AlertInfo.CART_EMPTY);
        }else{
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_PAYMENT_CART);
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
                int id = orderService.saveOrder(OrdersModel.builder()
                        .customerId(OrderTamp.customerId)
                        .employeeId(null)
                        .totalPrice(cart.getTotalPrice())
                        .totalAmount(cart.getTotalAmount())
                        .build());
                List<OrderDetailModel> orders = new ArrayList<>();
                carts.forEach(c -> {
                    OrderDetailModel o = OrderDetailModel.builder()
                            .price(c.getPrice())
                            .quantity(c.getQuantity())
                            .orderId(id)
                            .bookId(c.getBookId())
                            .build();
                    bookService.setQuantity(c.getBookId(), c.getQuantity());
                    orders.add(o);
                });
                orderDetailService.saveAll(orders);
                cartService.deleteCartItem(OrderTamp.cartId);
                cartService.resetCart(OrderTamp.cartId);
                setData();
                alert = AlertUnit.generateAlert(AlertInfo.WAIT_CONFIRM);
            }else{
                alert = AlertUnit.generateAlert(AlertInfo.ERROR_SYSTEM);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }
}
