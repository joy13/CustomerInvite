package com.intercom.invite.model;

import lombok.Value;
import com.fasterxml.jackson.annotation.JsonProperty;

@Value
public class Customer {
    @JsonProperty("user_id")
    private int userId;
    private String name;
    private String latitude;
    private String longitude;
    
}
