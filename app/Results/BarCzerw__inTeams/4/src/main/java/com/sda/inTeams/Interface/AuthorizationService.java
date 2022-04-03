package com.sda.inTeams.Interface;
public interface AuthorizationService {

   public Optional<User> getUserCredentials(Principal principal);
   public boolean isUserEligibleToSeeTeamDetails(Principal principal,Team team);
   public Object isUserEligibleToManageTeam(Principal principal,Team team);
}