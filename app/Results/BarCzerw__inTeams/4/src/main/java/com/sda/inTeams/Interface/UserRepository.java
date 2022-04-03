package com.sda.inTeams.Interface;
public interface UserRepository {

   public Object findById(Object Object);
   public Object save(Object Object);
   public List<User> findAllByTeamsContaining(Team team);
   public Object saveAll(Object Object);
}