package com.sda.inTeams.Interface;
import com.sda.inTeams.DTO.*;
import java.util.*;
public interface TeamRepository {

   public Optional<Team> findByProjectsContaining(Project project);
   public Object orElseThrow(Object Object);
}