package com.sda.inTeams.Interface;
public interface TeamService {

   public List<Team> getTeamsContainingMember(long userId);
   public List<Team> getTeamsOwnedBy(long userId);
}