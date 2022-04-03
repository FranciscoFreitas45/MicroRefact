package com.sda.inTeams.Interface;
import java.util.*;
import com.sda.inTeams.model.Project.*;
import com.sda.inTeams.DTO.*;

public interface TeamRepository {

   public Optional<Team> findById(Object Object);
   public Optional<Team> findByProjectsContaining(Project project);
   public Object orElseThrow(Object Object);
   public Object save(Object Object);
}