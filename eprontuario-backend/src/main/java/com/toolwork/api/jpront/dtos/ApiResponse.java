package com.toolwork.api.jpront.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String httpCode;
    private String httpMessage;
}
