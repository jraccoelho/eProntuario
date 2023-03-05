package com.toolwork.api.jpront.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String userId;
    private String userPassword;
    private String requestor;
    private String userFullName;
}
