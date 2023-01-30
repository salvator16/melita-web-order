package com.melita.weborder.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    Internet("Internet"),
    Tv("Television"),
    Telephony("Telephony"),
    Mobile("Mobile");

    @JsonValue
    private final String type;

}
