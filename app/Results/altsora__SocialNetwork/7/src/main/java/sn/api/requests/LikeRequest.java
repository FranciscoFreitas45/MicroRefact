package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sn.model.enums.LikeType;
@Getter
@Setter
@AllArgsConstructor
public class LikeRequest {

@JsonProperty("item_id")
 private  long itemId;

 private  LikeType type;


}