package org.vaadin.paul.spring.Interface;
public interface UserRepository {

   public User findBydni(String dni);
   public Object save(Object Object);
}