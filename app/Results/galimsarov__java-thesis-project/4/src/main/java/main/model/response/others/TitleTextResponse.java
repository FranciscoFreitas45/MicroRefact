package main.model.response.others;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class TitleTextResponse extends TextResponse{

 private  String title;


}