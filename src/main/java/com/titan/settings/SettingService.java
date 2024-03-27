package com.titan.settings;

import com.titan.settings.requests.EditSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;
    private static final Logger log = LoggerFactory.getLogger(SettingService.class);

    public List<SettingEntity> getSettings() {
        return settingRepository.findAll();
    }

    public SettingEntity editSettings(EditSettingsRequest request) {
        var findSetting = settingRepository.findById(1L);
        return findSetting.map(setting -> editSetting(setting, request)).orElseGet(() -> addSettings(request));
    }

    private SettingEntity addSettings(EditSettingsRequest request) {
        return settingRepository.save(
                new SettingEntity(
                        1L,
                        request.getCompanyName(),
                        request.getStreetName(),
                        request.getStreetNumber(),
                        request.getPostalCode(),
                        request.getCityName(),
                        request.getTimerLockScreen(),
                        request.getCashContent(),
                        request.getCardReader(),
                        request.getTaxesToGo(),
                        request.getTaxesIn()
                )
        );
    }

    private SettingEntity editSetting(SettingEntity setting,EditSettingsRequest request) {
        Optional.ofNullable(request.getCompanyName())
                .filter(
                        name -> !name.isEmpty()  || !name.contentEquals(setting.getCompanyName())
                ).ifPresent(setting::setCompanyName);
        Optional.ofNullable(request.getCityName())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(setting.getCityName())
                ).ifPresent(setting::setCityName);
        Optional.ofNullable(request.getStreetName())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(setting.getStreetName())
                ).ifPresent(setting::setStreetName);
        Optional.ofNullable(request.getPostalCode())
                .filter(
                        code -> !code.isEmpty() || !code.contentEquals(setting.getPostalCode())
                ).ifPresent(setting::setPostalCode);
        Optional.ofNullable(request.getStreetNumber())
                .filter(
                        number -> !number.isEmpty() || !number.contentEquals(setting.getStreetNumber())
                ).ifPresent(setting::setStreetNumber);

        Optional.ofNullable(request.getTimerLockScreen())
                .filter(
                        timer -> timer > 0 || !timer.equals(setting.getTimerLockScreen())
                ).ifPresent(setting::setTimerLockScreen);

        Optional.ofNullable(request.getCashContent())
                .filter(
                        cash -> !cash.equals(setting.getCashContent())
                ).ifPresent(setting::setCashContent);

        Optional.ofNullable(request.getCardReader())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(setting.getCardReader())
                ).ifPresent(setting::setCardReader);

        Optional.ofNullable(request.getTaxesToGo())
                .filter(
                        togo -> togo > 0 && !togo.equals(setting.getTaxesToGo())
                ).ifPresent(setting::setTaxesToGo);

        Optional.ofNullable(request.getTaxesIn())
                .filter(
                        taxes -> taxes > 0 && !taxes.equals(setting.getTaxesIn())
                ).ifPresent(setting::setTaxesIn);

        settingRepository.save(setting);
        return setting;
    }
}
