package com.booksotre.controller.admin;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import com.booksotre.service.IOrderDetailService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.OrderDetailService;
import com.booksotre.service.impl.OrderService;

public class HomeController implements Initializable {

    @FXML
    private DatePicker pickDateFrom;

    @FXML
    private DatePicker pickDateTo;

    @FXML
    private AreaChart<String, Integer> chart1;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label error_order;

    @FXML
    private Label sucess_order;

    @FXML
    private Label today_income;

    @FXML
    private Label total_income;

    @FXML
    private Label waitconfirm_order;

    private final IOrderService orderService = new OrderService();

    private final IOrderDetailService orderDetilService = new OrderDetailService();

    public void setCart() {
        total_income.setText(String.valueOf(orderService.getTotalIncome()));
        sucess_order.setText(String.valueOf(orderService.countByStatus("Hoàn thành", 0)));
        waitconfirm_order.setText(String.valueOf(orderService.countByStatus("Chờ xử lý", 0)));
        error_order.setText(String.valueOf(orderService.countByStatus("Đã hủy", 0)));
    }

    public void setChartAre() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Hoàn thành");
        String fromDate =
                (pickDateFrom.getValue() != null) ? pickDateFrom.getValue().toString() : null;
        String toDate = (pickDateTo.getValue() != null) ? pickDateTo.getValue().toString() : null;
        LinkedHashMap<String, Integer> map1 = orderService.countByDate(fromDate, toDate, "Hoàn thành");
        map1.forEach((key, value) -> series1.getData().add(new XYChart.Data<>(key, value)));

        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Đã hủy");
        LinkedHashMap<String, Integer> map2 = orderService.countByDate(fromDate, toDate, "Đã hủy");
        map2.forEach((key, value) -> series2.getData().add(new XYChart.Data<>(key, value)));

        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Chờ xác nhận");
        LinkedHashMap<String, Integer> map3 = orderService.countByDate(fromDate, toDate, "Chờ xác nhận");
        map3.forEach((key, value) -> series3.getData().add(new XYChart.Data<>(key, value)));

        chart1.getData().addAll(series1, series2, series3);
    }

    public void setChartPie() {
        LinkedHashMap<String, Integer> map = orderDetilService.getBestSeller();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        map.forEach((key, value) -> pieChartData.add(new PieChart.Data(key, value)));
        pieChart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCart();
        setChartAre();
    }
}
