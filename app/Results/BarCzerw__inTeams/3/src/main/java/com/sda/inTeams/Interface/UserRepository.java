package com.sda.inTeams.Interface;
public interface UserRepository {

   public List<User> findAllByTeamsContaining(Team team);
   public Object saveAll(Object Object);
   public Object save(Object Object);
   public Optional<User> findByUsername(String username);
}