package com.sda.inTeams.Interface;
public interface ProjectRepository {

   public Object saveAll(Object Object);
   public List<Project> findAllByProjectOwner(Team team);
}