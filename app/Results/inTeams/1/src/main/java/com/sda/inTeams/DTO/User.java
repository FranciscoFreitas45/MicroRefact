package com.sda.inTeams.DTO;
import com.sda.inTeams.model.Indexable;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import com.sda.inTeams.model.User.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails,Indexable{

 private Long id;

 private  String username;

 private  String nonHashedPassword;

 private  String password;

 private  String firstName;

 private  String lastName;

 private  String uniqueInvitationId;

 private  boolean accountNonExpired;

 private  boolean accountNonLocked;

 private  boolean credentialsNonExpired;

 private  boolean enabled;

 private  Set<AccountRole> roles;

 private  Set<Team> teamsOwned;

 private  Set<Team> teams;

 private  Set<Task> taskResponsibleFor;

 private  Set<Comment> commentsCreated;


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return roles.stream().map(accountRole -> new SimpleGrantedAuthority(accountRole.getName())).collect(Collectors.toList());
}


}