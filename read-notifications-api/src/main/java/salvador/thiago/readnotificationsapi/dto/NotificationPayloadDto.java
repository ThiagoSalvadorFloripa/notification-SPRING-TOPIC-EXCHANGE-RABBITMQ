package salvador.thiago.readnotificationsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import salvador.thiago.readnotificationsapi.entity.Notification;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPayloadDto {
    @JsonProperty("notification_type")
    private String type;
    @JsonProperty("subscription")
    private String subscription;

    public static Notification toModel(NotificationPayloadDto dto) {
        return new ModelMapper().map(dto, Notification.class);
    }

}
