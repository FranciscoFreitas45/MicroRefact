package com.sda.inTeams.Interface;
import com.sda.inTeams.DTO.*;
import java.util.*;
public interface ProjectRepository {

   public Optional<Project> findByTasksContaining(Task task);
   public Object orElseThrow(Object Object);
}