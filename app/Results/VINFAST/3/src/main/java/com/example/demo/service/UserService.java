package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.demo.entity.Users;
public interface UserService {


public boolean checknull(String username)
;

public List<Users> saveAll(List<Users> entities)
;

public void deleteAll(List<Users> entities)
;

public long count()
;

public Users save(Users entity)
;

public void deleteAllById(List<String> ids)
;

public void delete(Users entity)
;

public Page<Users> findAll(Pageable pageable)
;

public boolean checkLogin(String username,String password)
;

public boolean existsById(String id)
;

public List<Users> findAllById(List<String> ids)
;

public Users findByUsername(String usersname)
;

public Optional<Users> findById(String id)
;

public void deleteById(String id)
;

}