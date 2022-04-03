package com.sda.inTeams.service;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import com.sda.inTeams.model.dto.RegisterTeamDTO;
import com.sda.inTeams.model.dto.RegisterUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sda.inTeams.Interface.UserService;
@Service
@RequiredArgsConstructor
public class RegisterService {

 private  TeamService teamService;

 private  UserService userService;


public Team registerNewTeamWithUser(RegisterTeamDTO registerDTO){
    User user = userService.createFromRegister(registerDTO);
    Team team = teamService.createFromRegister(registerDTO);
    team.setTeamOwner(user);
    return teamService.addUserToTeam(team, user);
}


public User registerNewUserToExistingTeam(RegisterUserDTO registerUserDTO){
    User user = userService.createFromRegister(registerUserDTO);
    Team team = teamService.getByIdOrThrow(registerUserDTO.getTeamId());
    teamService.addUserToTeam(team, user);
    return userService.getByIdOrThrow(user.getId());
}


public List<Team> getAllAvailableTeams(){
    return teamService.getAll();
}


}