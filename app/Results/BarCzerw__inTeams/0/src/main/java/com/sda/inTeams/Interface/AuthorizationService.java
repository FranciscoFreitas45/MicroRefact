package com.sda.inTeams.Interface;
public interface AuthorizationService {

   public boolean isUserEligibleToSeeProjectDetails(Principal principal,Project project);
}