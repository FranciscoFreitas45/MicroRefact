package com.sda.inTeams.service;
 import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class DatabaseInfo {

 private  long usersCount;

 private  long teamsCount;

 private  long projectsCount;

 private  long tasksCount;

 private  long commentsCount;


}