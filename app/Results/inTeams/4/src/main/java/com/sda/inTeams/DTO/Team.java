package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.User.User;
import lombok;
import javax.persistence;
import java.util.Set;
import java.util.UUID;
import com.sda.inTeams.Request.ProjectRequest;
import com.sda.inTeams.Request.Impl.ProjectRequestImpl;
import com.sda.inTeams.DTO.Project;
public class Team implements Indexable{

 private Long id;

 private  String uniqueRegisterId;

 private  String name;

 private  User teamOwner;

 private  Set<User> members;

 private  Set<Project> projects;


}