package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class PostEditRequest {

@JsonProperty("title")
 private  String title;

@JsonProperty("post_text")
 private  String postText;


}