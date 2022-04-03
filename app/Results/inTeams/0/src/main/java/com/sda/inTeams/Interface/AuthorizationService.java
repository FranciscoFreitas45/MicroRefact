package com.sda.inTeams.Interface;
import java.util.*;
import com.sda.inTeams.model.Comment.*;
import com.sda.inTeams.model.Project.*;
import com.sda.inTeams.model.Task.*;
import com.sda.inTeams.DTO.*;
import java.security.Principal;

public interface AuthorizationService {

   public boolean isUserEligibleToSeeProjectDetails(Principal principal,Project project);
   boolean isUserEligibleToEditComment(Principal principal,Comment commentToEdit );
   boolean isUserAdmin(Principal principal);
   Optional<User> getUserCredentials(Principal principal);
   boolean isUserEligibleToSeeTaskDetails(Principal principal, Task task); 
   public boolean isUserEligibleToDeleteComment(Principal principal, Comment comment);
}