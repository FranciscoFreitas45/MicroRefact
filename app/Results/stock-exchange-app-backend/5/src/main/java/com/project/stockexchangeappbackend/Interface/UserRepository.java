package com.project.stockexchangeappbackend.Interface;
public interface UserRepository {

   public Optional<User> findByEmailIgnoreCase(String email);
   public S save(S s);
   public Optional<User> findById(Long id);
   public Page<User> findAll(Specification<User> specification,Pageable pageable);
   public Long countByRole(Role role);
   public Object equals(Object Object);
}