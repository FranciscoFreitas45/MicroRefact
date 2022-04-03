package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class IsFriendsRequest {

@JsonProperty("user_ids")
 private List<Long> userIds;


}