package com.sda.inTeams.service;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.model.dto.RegisterTeamDTO;
import com.sda.inTeams.repository.ProjectRepository;
import com.sda.inTeams.repository.TeamRepository;
import com.sda.inTeams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.ProjectRepository;
@Service
@RequiredArgsConstructor
public class TeamService implements DatabaseManageable<Team>{

 private  TeamRepository teamRepository;

 private  UserRepository userRepository;

 private  ProjectRepository projectRepository;


public Team add(Team team){
    if (!Objects.isNull(team)) {
        return saveToDatabase(team);
    } else {
        throw new InvalidOperation("Cannot add team - Object is null!");
    }
}


public void removeProjectFromTeam(long teamId,long projectId){
    Team team = getByIdOrThrow(teamId);
    Project project = projectRepository.findById(projectId).orElseThrow();
    if (!team.getProjects().remove(project)) {
        throw new InvalidOperation("Cannot remove project id:" + projectId + " from team id:" + teamId + " - Project does not belong to Team");
    } else {
        saveToDatabase(team);
        projectRepository.delete(project);
    }
}


public List<Team> getAll(){
    return teamRepository.findAll();
}


public Team createWithOwner(Team team,long ownerId){
    User owner = userRepository.findById(ownerId).orElseThrow(() -> new InvalidOperation(""));
    team.setTeamOwner(owner);
    List<User> members = new ArrayList<>(List.of(owner));
    team.setMembers(new HashSet<>(members));
    owner.getTeamsOwned().add(team);
    owner.getTeams().add(team);
    userRepository.save(owner);
    return saveToDatabase(team);
}


public void setOwnerOfTeam(long teamId,long userId){
    Team team = getByIdOrThrow(teamId);
    User user = getUserByIdOrError(userId);
    if (!isUserOwnerOfTeam(team, user) && isUserMemberOfTeam(team, user)) {
        team.setTeamOwner(user);
        saveToDatabase(team);
    } else if (!isUserMemberOfTeam(team, user)) {
        throw new InvalidOperation("Cannot make user id:" + userId + " owner of team id:" + teamId + " - User is not a team member!");
    } else {
        throw new InvalidOperation("Cannot make user id:" + userId + " owner of team id:" + teamId + " - User is already an owner!");
    }
}


public Team createFromRegister(RegisterTeamDTO registerTeamDTO){
    return add(Team.builder().name(registerTeamDTO.getTeamName()).build());
}


public User getUserByIdOrError(long userId){
    return userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User id:" + userId + " not found!"));
}


public boolean isUserMemberOfTeam(Team team,User user){
    return team.getMembers().contains(user);
}


public Team getByIdOrThrow(long teamId){
    return teamRepository.findById(teamId).orElseThrow(() -> new InvalidOperation("Team id:" + teamId + " not found!"));
}


public void delete(long teamId){
    Team team = getByIdOrThrow(teamId);
    List<User> userList = userRepository.findAllByTeamsContaining(team);
    for (User user : userList) {
        user.getTeams().remove(team);
    }
    team.setMembers(new HashSet<>());
    // team.setProjects(new HashSet<>());
    team.setTeamOwner(null);
    userRepository.saveAll(userList);
    saveToDatabase(team);
    teamRepository.delete(team);
}


public void removeUserFromTeam(long teamId,long userId){
    Team team = getByIdOrThrow(teamId);
    User user = userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User not found!"));
    List<User> teamUsers = userRepository.findAllByTeamsContaining(team);
    if (teamUsers.contains(user) && !team.getTeamOwner().equals(user)) {
        teamUsers.remove(user);
        team.setMembers(new HashSet<>(teamUsers));
        saveToDatabase(team);
    } else if (!teamUsers.contains(user)) {
        throw new InvalidOperation("Cannot remove user id:" + userId + " from team id:" + teamId + " - User is not a member!");
    } else {
        throw new InvalidOperation("Cannot remove user id:" + userId + " from team id: " + teamId + " - User is an owner!");
    }
}


public void addProjectToTeam(long teamId,long projectId){
    Team team = getByIdOrThrow(teamId);
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new InvalidOperation("Project id:" + projectId + " not found!"));
    if (!team.getProjects().contains(project)) {
        team.getProjects().add(project);
        saveToDatabase(team);
        project.setProjectOwner(team);
        projectRepository.save(project);
    } else {
        throw new InvalidOperation("Cannot add project id:" + projectId + " to team id:" + teamId + " - Project already belongs to Team");
    }
}


public Optional<Team> getById(long teamId){
    return teamRepository.findById(teamId);
}


public List<Team> getTeamsOwnedBy(long userId){
    return teamRepository.findAllByTeamOwner(userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User not found!")));
}


public boolean isUserOwnerOfTeam(Team team,User user){
    Optional<User> userOptional = Optional.ofNullable(team.getTeamOwner());
    return userOptional.map(value -> value.equals(user)).orElse(false);
}


public Optional<Team> getTeamByName(String name){
    return teamRepository.findByName(name);
}


public List<Team> getTeamsContainingMember(long userId){
    return teamRepository.findAllByMembersContaining(userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User not found!")));
}


public Team addUserToTeam(Team team,User user){
    List<User> teamUsers = userRepository.findAllByTeamsContaining(team);
    if (!teamUsers.contains(user)) {
        teamUsers.add(user);
        team.setMembers(new HashSet<>(teamUsers));
        return saveToDatabase(team);
    } else {
        throw new InvalidOperation("Cannot add user id:" + user.getId() + " to team id:" + team.getId() + " - User is already a member");
    }
}


public Team saveToDatabase(Team team){
    return teamRepository.save(team);
}


}