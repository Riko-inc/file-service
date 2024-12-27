//package org.example.fileservice.domain.enities;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@NotNull
//public class UserSettingsEntity {
//    private enum Theme { dark, light }
//    private enum MainColor { white, black, red, green, cia }
//    private enum SecondaryColor { white, black, red, green, cian }
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name="theme", nullable = false)
//    private Theme theme = Theme.light;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name="main_color", nullable = false)
//    private MainColor mainColor = MainColor.white;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name="secondary_color", nullable = false)
//    private SecondaryColor secondaryColor = SecondaryColor.cian;
//
//    @NotNull
//    @Column(name="send_messages_to_email", nullable = false, columnDefinition = "boolean default false")
//    private boolean sendMessagesToEmail = false;
//
//    @NotNull
//    @Column(name="send_notifications", nullable = false)
//    private boolean sendNotifications = true;
//}
