package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sn.model.enums.FriendshipStatusCode;
@Getter
@Setter
@RequiredArgsConstructor
public class IsFriendResponse extends AbstractResponse{

@JsonProperty("user_id")
 private  long id;

@JsonProperty("status")
 private  FriendshipStatusCode statusCode;


}