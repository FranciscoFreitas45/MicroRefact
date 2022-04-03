package sn.DTO;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
public class CommentResponse extends AbstractResponse{

 private  long id;

 private  long postId;

 private  long authorId;

 private  String commentText;

 private  Long parentId;

 private  long time;

 private  boolean isBlocked;


}