package sn.api.response;
 import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PostListResponse extends AbstractResponse{

 private List<PostResponse> posts;


}