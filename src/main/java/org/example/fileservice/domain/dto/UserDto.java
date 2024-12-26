package org.example.fileservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fileservice.domain.enities.UserEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private LocalDateTime registrationDateTime;
    private String email;
    private boolean isActive;
    private UserEntity.Role role;
}