package goorum.goorum.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence;
import java.io.Serializable;
public class Replylist implements Serializable,Article{

 private  long replyId;

 private  long parent;

 private  String content;

 private  String date;

 private  long memberId;

 private  String nickname;

 private  String profilePhoto;

 private  long boardId;


}