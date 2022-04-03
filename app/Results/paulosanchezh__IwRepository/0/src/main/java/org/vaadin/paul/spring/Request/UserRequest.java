package org.vaadin.paul.spring.Request;
import org.vaadin.paul.spring.DTO.User;
public interface UserRequest {

   public User getPaciente(int id);
   public void setPaciente(User user,int id);
}