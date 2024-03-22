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
        var sets = settingRepository.findAll();
        return settingRepository.findAll();
    }

    public SettingEntity editSettings(EditSettingsRequest request) {
        var findSetting = settingRepository.findById(1L);
        var setting = findSetting.isPresent() ? editSetting(findSetting.get(), request) : addSettings(request);
        return setting;
    }

    private SettingEntity addSettings(EditSettingsRequest request) {
        var createdSettings = settingRepository.save(
                new SettingEntity(
                        1L,
                        request.getCompanyName(),
                        request.getStreetName(),
                        request.getStreetNumber(),
                        request.getPostalCode(),
                        request.getCityName(),
                        request.getCustomColorTheme(),
                        request.getPrimaryColor(),
                        request.getAccentColor(),
                        request.getWarnColor(),
                        request.getTimerLockScreen()
                )
        );
        return createdSettings;
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

        Optional.of(request.getCustomColorTheme())
                .filter(
                        theme -> !theme.equals(setting.getCustomColorTheme())
                ).ifPresent(setting::setCustomColorTheme);
        Optional.of(request.getPrimaryColor())
                .filter(
                        prim -> !prim.isEmpty() || !prim.contentEquals(setting.getPrimaryColor())
                ).ifPresent(setting::setPrimaryColor);
        Optional.ofNullable(request.getAccentColor())
                .filter(
                        acc -> !acc.isEmpty() || !acc.contentEquals(setting.getAccentColor())
                ).ifPresent(setting::setAccentColor);
        Optional.ofNullable(request.getWarnColor())
                .filter(
                        warn -> !warn.isEmpty() || !warn.contentEquals(setting.getWarnColor())
                ).ifPresent(setting::setWarnColor);
        Optional.ofNullable(request.getTimerLockScreen())
                .filter(
                        timer -> timer > 0 || !timer.equals(setting.getTimerLockScreen())
                ).ifPresent(setting::setTimerLockScreen);

        settingRepository.save(setting);
        return setting;
    }
}
