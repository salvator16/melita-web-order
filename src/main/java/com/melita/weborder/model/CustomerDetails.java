package com.melita.weborder.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class CustomerDetails implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String identityNumber;
    private String phoneNumber;
    private String address;
    private String installationAddress;
    private Date installationDate;
    private String timeSlot;

}
