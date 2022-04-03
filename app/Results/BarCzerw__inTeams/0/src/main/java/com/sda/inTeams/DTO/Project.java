package com.sda.inTeams.DTO;
 import com.sda.inTeams.model.Indexable;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Team.Team;
import lombok;
import javax.persistence;
import java.util.Set;
public class Project implements Indexable{

 private Long id;

 private  String name;

 private  Team projectOwner;

 private  ProjectStatus status;

 private  Set<Task> tasks;

 private Long id;


}