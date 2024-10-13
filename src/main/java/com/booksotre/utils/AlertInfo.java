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
