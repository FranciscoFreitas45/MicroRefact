package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import lombok.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Indexable{

 private Long id;

 private  String name;

 private  Team projectOwner;

 private  ProjectStatus status;

 private  Set<Task> tasks;


}