package com.project.stockexchangeappbackend.Interface;
public interface UserRepository {

   public Optional<User> findById(Long id);
}