package com.sda.inTeams.service;
 import com.sda.inTeams.DTO.Comment;
import com.sda.inTeams.DTO.Project;
import com.sda.inTeams.DTO.Task;
import com.sda.inTeams.DTO.Team;
import com.sda.inTeams.model.User.AccountRole;
import com.sda.inTeams.DTO.User;
import com.sda.inTeams.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.Optional;
import com.sda.inTeams.Interface.TaskRepository;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.UserRepository;
@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService{

 private  UserRepository userRepository;

 private  TaskRepository taskRepository;

 private  ProjectRepository projectRepository;

 private  TeamRepository teamRepository;

 private  AccountRoleRepository accountRoleRepository;


public Optional<User> getUserCredentials(Principal principal){
    if (principal instanceof UsernamePasswordAuthenticationToken) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        if (usernamePasswordAuthenticationToken.getPrincipal() instanceof User) {
            return Optional.of((User) usernamePasswordAuthenticationToken.getPrincipal());
        }
    }
    return Optional.empty();
}


public boolean isUserEligibleToSeeProjectDetails(Principal principal,Project project){
    if (isUserAdmin(principal)) {
        return true;
    } else {
        User user = getUserCredentials(principal).orElseThrow();
        Team team = teamRepository.findByProjectsContaining(project).orElseThrow();
        return userRepository.findAllByTeamsContaining(team).contains(user);
    }
}


public boolean isUserAdmin(Principal principal){
    Optional<User> userOptional = getUserCredentials(principal);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        AccountRole accountRole = accountRoleRepository.findByName("ROLE_ADMIN").get();
        return user.getRoles().contains(accountRole);
    }
    return false;
}


@Override
public UserDetails loadUserByUsername(String username){
    Optional<User> accountOptional = userRepository.findByUsername(username);
    if (accountOptional.isPresent()) {
        return accountOptional.get();
    }
    throw new UsernameNotFoundException("Cannot find username:" + username);
}


public boolean isUserEligibleToEditComment(Principal principal,Comment comment){
    return isUserCommentCreator(principal, comment) || isUserAdmin(principal);
}


public boolean isUserEligibleToSeeUserDetails(Principal principal,User userDetails){
    User user = getUserCredentials(principal).orElseThrow();
    return userDetails.equals(user) || isUserAdmin(principal);
}


public boolean isUserCommentCreator(Principal principal,Comment comment){
    return getUserCredentials(principal).orElseThrow().equals(comment.getCreator());
}


public boolean isUserEligibleToSeeTaskDetails(Principal principal,Task task){
    if (isUserAdmin(principal)) {
        return true;
    } else {
        User user = getUserCredentials(principal).orElseThrow();
        Project project = projectRepository.findByTasksContaining(task).orElseThrow();
        Team team = teamRepository.findByProjectsContaining(project).orElseThrow();
        return team.getMembers().contains(user);
    }
}


public boolean isUserEligibleToSeeTeamDetails(Principal principal,Team team){
    User user = getUserCredentials(principal).orElseThrow();
    return isUserAdmin(principal) || team.getMembers().contains(user);
}


public boolean isUserEligibleToDeleteComment(Principal principal,Comment comment){
    return isUserCommentCreator(principal, comment) || isUserAdmin(principal);
}


public Object isUserEligibleToManageTeam(Principal principal,Team team){
    return isUserAdmin(principal) || getUserCredentials(principal).orElseThrow().equals(team.getTeamOwner());
}


}