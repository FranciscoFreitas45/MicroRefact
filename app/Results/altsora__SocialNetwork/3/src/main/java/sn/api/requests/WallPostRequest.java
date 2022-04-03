package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallPostRequest {

@JsonProperty("title")
 private  String title;

@JsonProperty("post_text")
 private  String postText;


}