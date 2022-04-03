package sn.api.response;
 import lombok;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class LikeCountResponse extends AbstractResponse{

 private  int likes;

 private  List<Long> users;


}