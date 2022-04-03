package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Builder
@RequiredArgsConstructor
public class MessageIdResponse extends AbstractResponse{

@JsonProperty("message_id")
 private  long messageId;


}