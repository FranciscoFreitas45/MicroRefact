package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Project.Project;
import lombok.*;
import java.util.Set;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Indexable{

 private Long id;

 private  String uniqueRegisterId;

 private  String name;

 private  User teamOwner;

 private  Set<User> members;

 private  Set<Project> projects;


}