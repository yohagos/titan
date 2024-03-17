package com.titan.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

    private Boolean customColorTheme;
    private String primaryColor;
    private String accentColor;
    private String warnColor;

    private Integer timerLockScreen;

    @Override
    public String toString() {
        return String.format("SettingEntity=[id=%d, companyName=%s, streetName=%s, streetNumber=%s, postalCode=%s, cityName=%s, customColorTheme=%s, primaryColor=%s, accentColor=%s, warnColor=%s, timerLockScreen=%d]",
                getId(), getCompanyName(), getStreetName(), getStreetNumber(), getPostalCode(), getCityName(), getCustomColorTheme(), getPrimaryColor(), getAccentColor(), getWarnColor(), getTimerLockScreen());
    }

}
