package com.sda.inTeams.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.repository.TaskRepository;
import com.sda.inTeams.model.Task.Task;
@Service
public class TaskUserService {

@Autowired
 private TaskRepository taskrepository;


}