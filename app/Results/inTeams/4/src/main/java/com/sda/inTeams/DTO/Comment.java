package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import com.sda.inTeams.Request.UserRequest;
import com.sda.inTeams.Request.Impl.UserRequestImpl;
import com.sda.inTeams.DTO.User;
public class Comment implements Indexable{

 private Long id;

 private  String text;

 private  User creator;

 private  CommentType type;

 private  Task task;

 private Long id;

 private UserRequest userrequest = new UserRequestImpl();;


}