package com.booksotre.model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {
    private Integer notificationId;
    private Integer customerId;
    private String title;
    private String content;
    private String notificationType;
    private Timestamp createAt;
}
