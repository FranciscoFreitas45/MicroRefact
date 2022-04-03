package com.example.smartkitchenbackend.repositories.role;
 import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleJPARepository extends JpaRepository<Role, Long>{


public Optional<Role> findByName(RoleName roleName)
;

}