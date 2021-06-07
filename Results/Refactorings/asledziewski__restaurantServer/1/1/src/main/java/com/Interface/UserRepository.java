package com.Interface;

import java.util.Optional;

public interface UserRepository {

   public Object findAll();
   public Object findById(Object Object);
   public Object save(Object Object);
   public Object deleteById(Object Object);


   Optional<Object> findByMail(String s);
}