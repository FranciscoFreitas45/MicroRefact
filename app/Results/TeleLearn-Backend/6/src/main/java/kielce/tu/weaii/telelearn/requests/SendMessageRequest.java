package kielce.tu.weaii.telelearn.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Validated
@Getter
@EqualsAndHashCode
public class SendMessageRequest {

@NotNull(message = "Wiadomość musi posiadać nadawcę.")
 private  Long senderId;

@NotNull(message = "Wiadomość musi posiadać odbiorcę.")
 private  Long receiverId;

@NotBlank(message = "Wiadomość nie może być pusta.")
 private  String content;

public SendMessageRequest(@JsonProperty(value = "senderId") Long senderId, @JsonProperty(value = "receiverId") Long receiverId, @JsonProperty(value = "content") String content) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.content = content;
}
}