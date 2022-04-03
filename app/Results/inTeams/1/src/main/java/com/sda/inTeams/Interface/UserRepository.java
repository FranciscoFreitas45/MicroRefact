package com.sda.inTeams.Interface;
import com.sda.inTeams.DTO.*;
import java.util.*;
public interface UserRepository {

   public Object findAll(Object Object);
   public Object findById(Object Object);
   public Object delete(Object Object);
   public Object save(Object Object);
   public List<User> findAllByTeamsContaining(Team team);
   public Optional<User> findByUniqueInvitationId(String uniqueInvitationId);
   public Optional<User> findByUsername (String username);
}