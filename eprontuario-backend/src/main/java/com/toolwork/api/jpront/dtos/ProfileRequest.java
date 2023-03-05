package com.toolwork.api.jpront.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private Long profileId;
    private String profileDescription;
}
