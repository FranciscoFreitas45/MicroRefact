package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.User.User;
import lombok;
import javax.persistence;
import java.util.Set;
import com.sda.inTeams.Request.UserRequest;
import com.sda.inTeams.Request.Impl.UserRequestImpl;
import com.sda.inTeams.DTO.User;
public class Task implements Indexable{

 private Long id;

 private  String description;

 private  TaskStatus status;

 private  Project project;

 private  User userResponsible;

 private  Set<Comment> comments;


}