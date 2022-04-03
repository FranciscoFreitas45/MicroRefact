package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Indexable{

 private Long id;

 private  String text;

 private  User creator;

 private  CommentType type;

 private  Task task;


}