package salvador.thiago.readnotificationsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import salvador.thiago.readnotificationsapi.entity.Notification;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("subscription")
    private String subscription;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static NotificationResponseDto toResponseDto(Notification notification) {
        return new ModelMapper().map(notification, NotificationResponseDto.class);
    }
}
