package goorum.goorum.domain;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boardlist implements Serializable,Article{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "board_id")
 private  long boardId;

@Column(name = "category")
 private  String category;

@Column(name = "sector")
 private  String sector;

@Column(name = "company")
 private  String company;

@Column(name = "title")
 private  String title;

@Column(name = "content")
 private  String content;

@Column(name = "profile_photo")
 private  String profile;

@Column(name = "writer_id")
 private  long writerId;

@Column(name = "writer_nick")
 private  String writerNickname;

@Column(name = "date")
 private  String date;

@Column(name = "likes")
 private  long likes;

@Column(name = "replies")
 private  long replies;


}