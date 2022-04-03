package com.netease.repositories;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.netease.domain.Admin;
public interface AdminRepository extends CrudRepository<Admin, Long>{


public Admin findByUsername(String username)
;

public Admin findByRealname(String realname)
;

public List<Admin> findAll()
;

}