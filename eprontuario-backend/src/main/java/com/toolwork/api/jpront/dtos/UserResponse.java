package com.toolwork.api.jpront.dtos;

import java.time.LocalDate;

import com.toolwork.api.jpront.domains.Role;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String fullName;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate lastUpdateDate;
    private String lastUpdatedBy;
    private Role role;
    private boolean userActive;
}
