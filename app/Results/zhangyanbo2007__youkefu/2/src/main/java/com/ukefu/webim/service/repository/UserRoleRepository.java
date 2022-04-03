package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Role;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.UserRole;
public interface UserRoleRepository extends JpaRepository<UserRole, String>{


public List<UserRole> findByOrgiAndRole(String orgi,Role role)
;

public List<UserRole> findByOrgiAndUser(String orgi,User user)
;

}