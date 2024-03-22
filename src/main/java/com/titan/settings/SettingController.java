package com.titan.settings;

import com.titan.settings.requests.EditSettingsRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    private static final Logger log = LoggerFactory.getLogger(SettingController.class);

    @GetMapping
    public ResponseEntity<List<SettingEntity>> getSettings() {
        return ResponseEntity.ok(settingService.getSettings());
    }

    @PutMapping("/edit")
    public ResponseEntity<SettingEntity> editSettings(
            @RequestBody EditSettingsRequest request
    ) {
        return ResponseEntity.ok(settingService.editSettings(request));
    }
}
