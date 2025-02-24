package com.miniproject.wtlmini.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassDto {

    private String username;

    private String oldPassword;

    private String newPassword;
}
