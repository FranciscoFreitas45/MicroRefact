package main.model.request.others;
 import lombok.Data;
import java.util.Set;
@Data
public class PostRequest {

 private  long timestamp;

 private  int active;

 private  String title;

 private  Set<String> tags;

 private  String text;


}