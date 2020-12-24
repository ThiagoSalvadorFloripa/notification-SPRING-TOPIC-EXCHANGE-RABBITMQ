package salvador.thiago.readnotificationsapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salvador.thiago.readnotificationsapi.dto.NotificationPayloadDto;
import salvador.thiago.readnotificationsapi.dto.NotificationResponseDto;
import salvador.thiago.readnotificationsapi.service.NotificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationPostController {

    @Autowired
    private NotificationService service;

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<NotificationResponseDto> save(@Valid @RequestBody NotificationPayloadDto payloadDto) {
        var response = service.create(payloadDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
