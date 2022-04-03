package com.sda.inTeams.Interface;
public interface TeamRepository {

   public Optional<Team> findByProjectsContaining(Project project);
   public Object orElseThrow(Object Object);
}