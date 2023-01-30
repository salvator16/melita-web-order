package com.melita.weborder.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageType {

    Internet_250Mbps("250 Mbps Internet"),
    Internet_1Gbps("1 Gbps Internet"),
    Tv_90("TV with 90 Channels"),
    Tv_140("Tv with 140 Channels"),
    Telephony_On_Net("Telephony with Free On Net"),
    Telephony_Unlimited("Telephony Unlimited Calls"),
    Mobile_Prepaid("Mobile Prepaid"),
    Mobile_Postpaid("Mobile Postpaid");

    @JsonValue
    private final String packageType;

}
