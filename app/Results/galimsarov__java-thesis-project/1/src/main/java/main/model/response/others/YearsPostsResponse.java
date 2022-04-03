package main.model.response.others;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
public class YearsPostsResponse extends PostsResponse{

 private  Set<Integer> years;


}