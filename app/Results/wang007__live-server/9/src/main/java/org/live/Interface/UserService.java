package org.live.Interface;
public interface UserService {

   public List<User> findByUsername(String username);
   public Object save(Object Object);
}