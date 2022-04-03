package goorum.goorum.DTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.io.Serializable;
public class Boardlist implements Serializable,Article{

 private  long boardId;

 private  String category;

 private  String sector;

 private  String company;

 private  String title;

 private  String content;

 private  String profile;

 private  long writerId;

 private  String writerNickname;

 private  String date;

 private  long likes;

 private  long replies;


}