package com.tech.repositories;
 import com.tech.models.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IUserRolesRepository extends JpaRepository<UserRole, Long>{


public List<UserRole> findByRole(String role)
;

public UserRole findByUserID(Long userid)
;

@Modifying
@Query("update UserRole u set u.user_role_role = ?1 where u.user_role_userid = ?2")
public void setUserRoleById(String role,Long userId)
;

}