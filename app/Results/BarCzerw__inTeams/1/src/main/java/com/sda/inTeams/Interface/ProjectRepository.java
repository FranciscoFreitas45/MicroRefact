package com.sda.inTeams.Interface;
public interface ProjectRepository {

   public Optional<Project> findByTasksContaining(Task task);
   public Object orElseThrow(Object Object);
}