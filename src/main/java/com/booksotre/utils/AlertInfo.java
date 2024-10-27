package com.booksotre.utils;

import javafx.scene.control.Alert;

import lombok.Getter;

@Getter
public enum AlertInfo {
    EMAIL_EXISTED("Thông báo lỗi", "Đã tồn tại tài khoản trên email vui lòng chọn email khác!", Alert.AlertType.ERROR),
    PASSWORD_INVALID("Thông báo lỗi", "Mật khẩu phải tối thiểu 8 ký tự!", Alert.AlertType.ERROR),
    CREAT_ACCOUNT_SUCCESS("Thông báo", "Tài khoản đã được tạo thành công!", Alert.AlertType.INFORMATION),
    LOGIN_SUCCESSFUL("Thông báo", "Đăng nhập thành công!", Alert.AlertType.INFORMATION),
    LACK_OF_INFORMATION("Thông báo lỗi", "Vui lòng điền đầy đủ thông tin!", Alert.AlertType.ERROR),
    EMAIL_PASSWORD_INVALID("Thông báo lỗi", "Vui lòng kiểm tra lại email hoạc mật khẩu", Alert.AlertType.ERROR),
    LOGOUT_SUCCESSFUL("Thông báo", "Đăng xuất tài khoản thành công", Alert.AlertType.INFORMATION),
    PRODUCT_EXIST("Thông báo", "Sách đã tồn tại", Alert.AlertType.INFORMATION),
    ADD_BOOK_SUCCESSFUL("Thông báo", "Thêm sách thành công", Alert.AlertType.INFORMATION),
    CONFIRM_UPDATE("Thông bóa xác nhận", "Bạn có chắc chắn muốn cập nhật thông tin của đối tượng này không?", Alert.AlertType.CONFIRMATION),
    UPDATE_SUCCESSFUL("Thông báo", "Cập nhật thông tin thành công", Alert.AlertType.INFORMATION),
    DELETE_SUCCESSFUL("Thông báo", "Xóa thông tin thành công", Alert.AlertType.INFORMATION),
    CANCEL("Thông báo hủy", "Đã hủy thao tác", Alert.AlertType.INFORMATION),
    CHOOSE_OBJECT("Thông báo", "VUi lòng chọn đối tượng muốn xóa", Alert.AlertType.ERROR),
    CONFIRM_DELETE("Thông báo", "Bạn có chắc muốn muốn xóa thông tin của đối tượng này không?", Alert.AlertType.CONFIRMATION),
    CHOOSE_QUANTITY("Thông báo", "VUi lòng chọn số lượng muốn thêm vào hóa đơn", Alert.AlertType.ERROR),
    NOT_ENOUGH_BOOK("Thông báo", "Số lượng sách trong kho không đủ", Alert.AlertType.ERROR),
    CONFIRM_ADD("Thông báo", "Bạn có chắc muốn muốn thêm thông tin của đối tượng này không?", Alert.AlertType.CONFIRMATION),
    OLD_PASSWORD_INCORRECT("Thông báo lỗi", "Mật khẩu cũ không chính xác!", Alert.AlertType.ERROR),
    CONFIRMNEWPASS_INCORRECT("Thông báo lỗi", "Mật khẩu xác nhận không chính xác!", Alert.AlertType.ERROR),
    ENTER_KEYWORD("Thông báo lỗi", "Vui lòng nhập từ khóa để tìm kiếm", Alert.AlertType.ERROR),
    ;

    private final String title;
    private final String message;
    private final Alert.AlertType type;

    AlertInfo(String title, String message, Alert.AlertType type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }
}
