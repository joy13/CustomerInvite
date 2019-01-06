package com.intercom.invite.model;

import lombok.Value;

@Value
public class Customer {
    private int userId;
    private String name;
    private String latitude;
    private String longitude;
}
