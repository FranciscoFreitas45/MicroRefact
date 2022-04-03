package com.sda.inTeams.Interface;
public interface TeamRepository {

   public Object findById(Object Object);
   public Optional<Team> findByProjectsContaining(Project project);
   public Object orElseThrow(Object Object);
   public Object save(Object Object);
}