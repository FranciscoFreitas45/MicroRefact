package com.sda.inTeams.Interface;
public interface TaskRepository {

   public Object saveAll(Object Object);
   public List<Task> findAllByProject(Project project);
   public List<Task> findAllByUserResponsible(User user);
}