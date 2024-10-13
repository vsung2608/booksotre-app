package com.booksotre.controller;

import java.net.URL;
import java.util.*;

import com.booksotre.model.BookModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.chart.AreaChart;


public class AdminController implements Initializable {

    @FXML
    private AnchorPane HoaDonForm;

    @FXML
    private AnchorPane QlyHTKForm;

//    @FXML
//    private TableView<OrderDetail> bangHoaDon;
//
//    @FXML
//    private GridPane OChuaSP;
//
//    @FXML
//    private TableView<Product> danhSachSP;
//
//    @FXML
//    private TableColumn<Product, Double> cotGiaSP;
//
//    @FXML
//    private TableColumn<Product, String> cotLoaiSP;
//
//    @FXML
//    private TableColumn<Product, String> cotMaSP;
//
//    @FXML
//    private TableColumn<Product, java.util.Date> cotNgayNhapSP;
//
//    @FXML
//    private TableColumn<Product, Integer> cotSoLuongSP;
//
//    @FXML
//    private TableColumn<Product, String> cotTenSP;
//
//    @FXML
//    private TableColumn<Product, String> cotTrangThai;

    @FXML
    private TextField mn_maKH;

    @FXML
    private AnchorPane giaoDienChinh;

    @FXML
    private ImageView hinhSP;

    @FXML
    private TextField htk_giaSP;

    @FXML
    private ComboBox<?> htk_loaiSP;

    @FXML
    private TextField htk_luongSP;

    @FXML
    private TextField htk_maSP;

    @FXML
    private TextField htk_tenSP;

    @FXML
    private ComboBox<?> htk_ttSP;

    @FXML
    private AnchorPane menuForm;

//    @FXML
//    private TableColumn<OrderDetail, Integer> mn_cotSoLuong;
//
//    @FXML
//    private TableColumn<OrderDetail, Integer> mn_cotGia;
//
//    @FXML
//    private TableColumn<OrderDetail, Integer> mn_cotMaSP;

    @FXML
    private TextField mn_soLuongSP;

    @FXML
    private Button nutCaiLai;

    @FXML
    private Button nutDangXuat;

    @FXML
    private Button nutGan;

    @FXML
    private Button nutHangTK;

    @FXML
    private Button nutXDSHoaDon;

    @FXML
    private Button nutMenu;

    @FXML
    private Button nutSua;

    @FXML
    private Button nutThanhToan;

    @FXML
    private Button nutThem;

    @FXML
    private Button nutTrangChu;

    @FXML
    private Button nutXoa;

    @FXML
    private Button nutXoaHoaDon;

    @FXML
    private Button nutXuatHoaDon;

    @FXML
    private Label tenNguoiDung;

    @FXML
    private Label tongTien;

    @FXML
    private AnchorPane trangChuForm;

    @FXML
    private ScrollPane thanhCuonSP;

//    @FXML
//    private TableView<Order> od_danhSachHoaDon;
//
//    @FXML
//    private TableColumn<Order, Integer> od_maHoaDon;
//
//    @FXML
//    private TableColumn<Order, Integer> od_maKhachHang;
//
//    @FXML
//    private TableColumn<Order, Integer> od_maNhanVien;
//
//    @FXML
//    private TableColumn<Order, String> od_ngayLap;
//
//    @FXML
//    private TableColumn<Order, Double> od_tongTien;

    @FXML
    private Label h_soLuongKH;

    @FXML
    private Label h_thuNhapTrongNgay;

    @FXML
    private Label h_tongThuNhap;

    @FXML
    private Label h_soLuongDonHang;

    @FXML
    private AreaChart<?, ?> h_bieuDoThuNhap;

    @FXML
    private AreaChart<?, ?> h_bieuDoLuongKH;

    private Alert alert;
    private String[] loaiCafe = {"Cà phê nguyên hạt", "Cà phê xay", "Cà phê hạt xanh"};
    private String[] ttCafe = {"Còn hàng", "Hết hàng"};
    private Image img;
    private double tongTienPhaiTra;
    private double thuNhapTrongNgay = 0;
    private double tongThuNhap = 0;

    public void sLoaiCafe() {

    }

    public void sttCafe() {

    }

    public void sDsSP() {

    }

    public void sDsHD() {

    }

    public ObservableList<BookModel> layDuLieuSanPham() {
        return null;
    }

    public void hienThiOChuaSanPham() {

    }

    public void hienThiDanhSachHD() {

    }

    public void hienThiTenNguoiDung() {

    }

    public void layTongTienHoaDon() {

    }

    public void hienThiTongTien() {

    }

    public void hienThiTTTrangChu() {

    }

    public void hienThiBieuDoThuNhap() {

    }

    public void hienThiBieuDoKhachHang() {

    }

    public void chonDuLieuHangHoa() {

    }

    public void dangXuat() {

    }

    public void nutThemSP() {

    }

    public void nutSuaSP() {

    }

    public void nutXoaSP() {

    }

    public void nutGanHinh() {

    }

    public void nutCaiLai() {

    }

    public void nutThanhToan() {

    }

    public void nutXoaTTDonHang() {

    }

    public void chuyenManHinh(ActionEvent e) {

        if (e.getSource() == nutTrangChu) {
            trangChuForm.setVisible(true);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            hienThiTTTrangChu();
            hienThiBieuDoThuNhap();
            hienThiBieuDoKhachHang();
        } else if (e.getSource() == nutHangTK) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(true);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            sLoaiCafe();
            sttCafe();
            sDsSP();
        } else if (e.getSource() == nutMenu) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(true);
            HoaDonForm.setVisible(false);

            hienThiOChuaSanPham();
            hienThiTongTien();
            sDsHD();
        } else if (e.getSource() == nutXDSHoaDon) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(true);

            hienThiDanhSachHD();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hienThiTenNguoiDung();
        sLoaiCafe();
        sttCafe();
        sDsSP();

        hienThiOChuaSanPham();
        hienThiTongTien();
        sDsHD();
        hienThiDanhSachHD();
        hienThiTTTrangChu();
        hienThiBieuDoThuNhap();
        hienThiBieuDoKhachHang();
    }
}

