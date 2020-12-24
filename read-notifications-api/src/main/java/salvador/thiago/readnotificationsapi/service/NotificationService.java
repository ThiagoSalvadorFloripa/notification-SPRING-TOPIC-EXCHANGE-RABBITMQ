package salvador.thiago.readnotificationsapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salvador.thiago.readnotificationsapi.dto.NotificationPayloadDto;
import salvador.thiago.readnotificationsapi.dto.NotificationResponseDto;
import salvador.thiago.readnotificationsapi.message.NotificationSendMessage;
import salvador.thiago.readnotificationsapi.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final NotificationSendMessage notificationSendMessage;

    public NotificationResponseDto create(NotificationPayloadDto notificationDto){
        log.info("input - {}", notificationDto);

        var model = NotificationPayloadDto.toModel(notificationDto);
        model.setCreatedAt(LocalDateTime.now());

        var result = NotificationResponseDto.toResponseDto(this.repository.save(model));
        if(result != null){
            this.notificationSendMessage.sendMessage(result);
        }
        log.info("output - {}", result);
        return result;
    }


}
