package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sn.model.enums.StatusWallPost;
import java.util.List;
@Getter
@Builder
@RequiredArgsConstructor
public class WallPostResponse extends AbstractResponse{

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
 private  int likesCount;

@JsonProperty("comments")
 private  List<CommentResponse> comments;

@JsonProperty("type")
 private  StatusWallPost statusWallPost;


}