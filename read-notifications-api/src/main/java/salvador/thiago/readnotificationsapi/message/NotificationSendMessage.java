package salvador.thiago.readnotificationsapi.message;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import salvador.thiago.readnotificationsapi.dto.NotificationResponseDto;

@Component
public class NotificationSendMessage {
    @Value("${readNotification.rabbitmq.exchange}")
    String exchange;

    public final RabbitTemplate rabbitTemplate;


    @Autowired
    public NotificationSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(NotificationResponseDto notificationResponseDto) {
        rabbitTemplate.convertAndSend(exchange,notificationResponseDto.getType().toLowerCase(),"Vamoooo");
    }
}
