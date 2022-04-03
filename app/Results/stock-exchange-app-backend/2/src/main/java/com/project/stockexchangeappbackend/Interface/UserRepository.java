package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.User;
public interface UserRepository {

   public Optional<User> findById(Long id);
}