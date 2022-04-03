package com.sda.inTeams.service;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.DTO.Comment;
import com.sda.inTeams.DTO.Team;
import com.sda.inTeams.DTO.User;
import com.sda.inTeams.DTO.RegisterDto;
import com.sda.inTeams.DTO.RegisterTeamDTO;
import com.sda.inTeams.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.CommentRepository;
import com.sda.inTeams.Interface.TeamService;
import com.sda.inTeams.DTO.RegisterDto;

@Service
@RequiredArgsConstructor
public class UserService implements DatabaseManageable<User>{

 private  UserRepository userRepository;

 private  TeamRepository teamRepository;

 private  CommentRepository commentRepository;

 private  TeamService teamService;

 private  AccountRoleRepository accountRoleRepository;

 private  PasswordEncoder passwordEncoder;


public User add(User user){
    if (!Objects.isNull(user)) {
        return saveToDatabase(user);
    } else {
        throw new InvalidOperation("Cannot add user - Object is null!");
    }
}


public List<User> getAllMembersOfTeam(long teamId){
    Team team = teamRepository.findById(teamId).orElseThrow(() -> new InvalidOperation("Team not found!"));
    return userRepository.findAllByTeamsContaining(team);
}


public List<User> getAll(){
    return userRepository.findAll();
}


public Optional<User> getById(long userId){
    return userRepository.findById(userId);
}


public User createFromRegister(RegisterDto registerDTO){
    return add(User.builder().username(registerDTO.getUsername()).nonHashedPassword(registerDTO.getPassword()).password(passwordEncoder.encode(registerDTO.getPassword())).firstName(registerDTO.getFirstName()).lastName(registerDTO.getLastName()).roles(new HashSet<>(List.of(accountRoleRepository.findByName("ROLE_USER").orElseThrow(() -> new InvalidOperation("Role not found!"))))).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).build());
}


public List<User> getUsersOfTeam(Team team){
    return userRepository.findAllByTeamsContaining(team);
}


public User getByInvitationCodeOrThrow(String invitationCode){
    return userRepository.findByUniqueInvitationId(invitationCode).orElseThrow(() -> new InvalidOperation("User not found!"));
}


public User getByIdOrThrow(long userId){
    return userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User id:" + userId + " not found!"));
}


public void delete(long userId){
    User user = getByIdOrThrow(userId);
    if (!teamRepository.findAllByTeamOwner(user).isEmpty()) {
        throw new InvalidOperation("Cannot remove user id:" + user.getId() + " - User is still owner of a Team");
    }
    List<Team> userTeams = teamRepository.findAllByMembersContaining(user);
    for (Team userTeam : userTeams) {
        teamService.removeUserFromTeam(userTeam.getId(), userId);
    }
    user.setTeams(new HashSet<>());
    List<Comment> userComments = commentRepository.findAllByCreator(user);
    for (Comment userComment : userComments) {
        userComment.setCreator(null);
    }
    user.setCommentsCreated(new HashSet<>());
    userRepository.delete(user);
}


public User saveToDatabase(User user){
    return userRepository.save(user);
}


}