package com.yalcin.repository;
 import com.yalcin.entity.Role;
import com.yalcin.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{


public Optional<Role> findByRole(Roles role)
;

}