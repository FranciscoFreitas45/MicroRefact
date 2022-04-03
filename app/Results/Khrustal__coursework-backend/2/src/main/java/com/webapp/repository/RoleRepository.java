package com.webapp.repository;
 import java.util.Optional;
import com.webapp.models.ERole;
import com.webapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{


public Optional<Role> findByName(ERole name)
;

}