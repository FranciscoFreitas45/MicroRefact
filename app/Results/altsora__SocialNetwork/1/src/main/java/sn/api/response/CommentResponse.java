package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse extends AbstractResponse{

@JsonProperty("id")
 private  long id;

@JsonProperty("post_id")
 private  long postId;

@JsonProperty("author_id")
 private  long authorId;

@JsonProperty("comment_text")
 private  String commentText;

@JsonProperty("parent_id")
 private  Long parentId;

@JsonProperty("time")
 private  long time;

@JsonProperty("is_blocked")
 private  boolean isBlocked;


}