package com.sda.inTeams.service;
 import com.sda.inTeams.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.Interface.TaskRepository;
import com.sda.inTeams.Interface.CommentRepository;
@Service
@RequiredArgsConstructor
public class DatabaseService {

 private  UserRepository userRepository;

 private  TeamRepository teamRepository;

 private  ProjectRepository projectRepository;

 private  TaskRepository taskRepository;

 private  CommentRepository commentRepository;


public long getUsersCount(){
    return userRepository.count();
}


public long getProjectsCount(){
    return projectRepository.count();
}


public long getCommentsCount(){
    return commentRepository.count();
}


public DatabaseInfo getDatabaseInfo(){
    return DatabaseInfo.builder().usersCount(getUsersCount()).teamsCount(getTeamsCount()).projectsCount(getProjectsCount()).tasksCount(getTasksCount()).commentsCount(getCommentsCount()).build();
}


public long getTeamsCount(){
    return teamRepository.count();
}


public long getTasksCount(){
    return taskRepository.count();
}


}