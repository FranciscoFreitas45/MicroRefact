package main.model.request.postids;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class PostIdRequest {

@JsonProperty("post_id")
 private  int postId;


}