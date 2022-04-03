package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse extends AbstractResponse{

@JsonProperty("id")
 private  long id;

@JsonProperty("time")
 private  long time;

@JsonProperty("author")
 private  PersonResponse author;

@JsonProperty("title")
 private  String title;

@JsonProperty("post_text")
 private  String postText;

@JsonProperty("is_blocked")
 private  boolean isBlocked;

@JsonProperty("likes")
 private  int likes;

@JsonProperty("comments")
 private  List<CommentResponse> comments;


}