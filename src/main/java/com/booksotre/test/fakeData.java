package com.booksotre.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.booksotre.model.CustomerModel;
import com.booksotre.model.EmployeeModel;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.service.impl.EmployeeService;

public class fakeData {
    public static void main(String[] args) {

        ICustomerService customerService = new CustomerService();

        List<CustomerModel> customers = new ArrayList<>();

        customers.add(CustomerModel.builder()
                .email("nguyenminhhoang1992@gmail.com")
                .password("nguyenminhhoang1992")
                .customerName("Nguyễn Minh Hoàng")
                .phone("0912345678")
                .address("Số 10, Đường Hoàng Hoa Thám, Hải Phòng")
                .gender("Nam")
                .dob(LocalDate.parse("1992-02-01"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("vuthithu2000@gmail.com")
                .password("vuthithu2000")
                .customerName("Vũ Thị Thư")
                .phone("0902345678")
                .address("Số 5, Đường Nguyễn Văn Cừ, Quảng Ninh")
                .gender("Nữ")
                .dob(LocalDate.parse("2000-03-21"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("trithuc2024@gmail.com")
                .password("trithuc2024")
                .customerName("Trí Thức")
                .phone("0934567890")
                .address("Số 25, Đường Duy Tân, Thái Nguyên")
                .gender("Nam")
                .dob(LocalDate.parse("2024-01-05"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("nguyenthuylen1990@gmail.com")
                .password("nguyenthuylen1990")
                .customerName("Nguyễn Thùy Lên")
                .phone("0912345678")
                .address("Số 50, Đường Mai Hắc Đế, Hồ Chí Minh")
                .gender("Nữ")
                .dob(LocalDate.parse("1990-04-12"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("hoanglong1987@gmail.com")
                .password("hoanglong1987")
                .customerName("Hoàng Long")
                .phone("0923456789")
                .address("Số 18, Đường Cách Mạng Tháng Tám, Đà Nẵng")
                .gender("Nam")
                .dob(LocalDate.parse("1987-09-15"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("minhtrinh2023@gmail.com")
                .password("minhtrinh2023")
                .customerName("Minh Trinh")
                .phone("0936789012")
                .address("Số 30, Đường Nguyễn Chí Thanh, Hà Nội")
                .gender("Nữ")
                .dob(LocalDate.parse("2023-07-10"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("phamthanhhai1996@gmail.com")
                .password("phamthanhhai1996")
                .customerName("Phạm Thanh Hải")
                .phone("0907654321")
                .address("Số 100, Đường Bà Triệu, Vũng Tàu")
                .gender("Nam")
                .dob(LocalDate.parse("1996-06-25"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("thuthao2025@gmail.com")
                .password("thuthao2025")
                .customerName("Thùy Thảo")
                .phone("0910987654")
                .address("Số 15, Đường Phan Đình Phùng, Đắk Lắk")
                .gender("Nữ")
                .dob(LocalDate.parse("2025-02-13"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("hoangbinh1991@gmail.com")
                .password("hoangbinh1991")
                .customerName("Hoàng Bình")
                .phone("0908765432")
                .address("Số 40, Đường Nguyễn Công Trứ, Vĩnh Long")
                .gender("Nam")
                .dob(LocalDate.parse("1991-12-28"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("lethanhvan1999@gmail.com")
                .password("lethanhvan1999")
                .customerName("Lê Thành Vân")
                .phone("0931234567")
                .address("Số 22, Đường Ngô Quyền, Tuyên Quang")
                .gender("Nữ")
                .dob(LocalDate.parse("1999-11-04"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("hoangdoanh1995@gmail.com")
                .password("hoangdoanh1995")
                .customerName("Hoàng Doanh")
                .phone("0912345670")
                .address("Số 75, Đường Ngọc Lâm, Phú Thọ")
                .gender("Nam")
                .dob(LocalDate.parse("1995-05-19"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("phanminhtruc1997@gmail.com")
                .password("phanminhtruc1997")
                .customerName("Phan Minh Trúc")
                .phone("0923456781")
                .address("Số 55, Đường Hoàng Diệu, Bình Thuận")
                .gender("Nữ")
                .dob(LocalDate.parse("1997-12-15"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("nguyenhoangviet1998@gmail.com")
                .password("nguyenhoangviet1998")
                .customerName("Nguyễn Hoàng Việt")
                .phone("0910987653")
                .address("Số 10, Đường Đinh Tiên Hoàng, Bình Định")
                .gender("Nam")
                .dob(LocalDate.parse("1998-07-03"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("hienminh2026@gmail.com")
                .password("hienminh2026")
                .customerName("Hiền Minh")
                .phone("0935432109")
                .address("Số 99, Đường Bùi Thị Xuân, Quảng Ngãi")
                .gender("Nữ")
                .dob(LocalDate.parse("2026-01-28"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("vuthithao1999@gmail.com")
                .password("vuthithao1999")
                .customerName("Vũ Thị Thảo")
                .phone("0901230987")
                .address("Số 45, Đường Ngô Sĩ Liên, Đồng Nai")
                .gender("Nữ")
                .dob(LocalDate.parse("1999-09-21"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("phuongloan2002@gmail.com")
                .password("phuongloan2002")
                .customerName("Phương Loan")
                .phone("0917654321")
                .address("Số 32, Đường Lê Duẩn, An Giang")
                .gender("Nữ")
                .dob(LocalDate.parse("2002-04-17"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("hoangkim1995@gmail.com")
                .password("hoangkim1995")
                .customerName("Hoàng Kim")
                .phone("0908765123")
                .address("Số 19, Đường Trần Quang Diệu, Ninh Bình")
                .gender("Nam")
                .dob(LocalDate.parse("1995-10-30"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("trongthao1998@gmail.com")
                .password("trongthao1998")
                .customerName("Trọng Thảo")
                .phone("0933456782")
                .address("Số 21, Đường Nguyễn Du, Đăk Nông")
                .gender("Nam")
                .dob(LocalDate.parse("1998-11-05"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("lanhthoai2010@gmail.com")
                .password("lanhthoai2010")
                .customerName("Lạnh Thoại")
                .phone("0921234567")
                .address("Số 28, Đường Ngọc Hồi, Phú Yên")
                .gender("Nữ")
                .dob(LocalDate.parse("2010-02-12"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());

        customers.add(CustomerModel.builder()
                .email("duongtrang2016@gmail.com")
                .password("duongtrang2016")
                .customerName("Dương Trang")
                .phone("0936547891")
                .address("Số 10, Đường Nguyễn Minh Châu, Thái Bình")
                .gender("Nữ")
                .dob(LocalDate.parse("2016-09-02"))
                .avatar("/assets/images/defaultAvatar.jpg")
                .build());


        // In thông tin của 10 nhân viên đã tạo
         customers.forEach(customerService::createAccount);
    }
}
