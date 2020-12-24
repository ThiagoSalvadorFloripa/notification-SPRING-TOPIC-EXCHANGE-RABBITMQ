package salvador.thiago.readnotificationsapi.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import salvador.thiago.readnotificationsapi.dto.NotificationResponseDto;

@Service
@Slf4j
public class ReceiveMessageHandler {

    public void handleMessage(NotificationResponseDto notificationResponseDto){
        log.info("HandleMessage!!!");
        log.info("recebido {}",notificationResponseDto);
    }
}
