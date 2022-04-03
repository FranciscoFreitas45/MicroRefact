package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Builder
@RequiredArgsConstructor
public class LikeValueResponse extends AbstractResponse{

@JsonProperty("likes")
 private  boolean likeValue;


}