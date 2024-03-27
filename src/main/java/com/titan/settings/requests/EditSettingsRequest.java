package com.titan.settings.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditSettingsRequest {

    private String companyName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String cityName;

    private Integer timerLockScreen;

    private Double cashContent;
    private String cardReader;

    private Integer taxesToGo;
    private Integer taxesIn;
}
