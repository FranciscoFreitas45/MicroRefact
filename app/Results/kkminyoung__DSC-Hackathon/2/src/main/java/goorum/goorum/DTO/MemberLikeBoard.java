package goorum.goorum.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.io.Serializable;
public class MemberLikeBoard implements Serializable{

 private  long id;

 private  long memberId;

 private  long boardId;


}