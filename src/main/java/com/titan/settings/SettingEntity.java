package com.titan.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

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

    @Override
    public String toString() {
        return String.format("SettingEntity=[id=%d, companyName=%s, streetName=%s, streetNumber=%s, postalCode=%s, cityName=%s, timerLockScreen=%d, cashContent=%s, cardReader=%s, taxesToGo=%d, taxesIn=%d]",
                getId(), getCompanyName(), getStreetName(), getStreetNumber(), getPostalCode(), getCityName(), getTimerLockScreen(), getCashContent(), getCardReader(), getTaxesToGo(), getTaxesIn());
    }

}
