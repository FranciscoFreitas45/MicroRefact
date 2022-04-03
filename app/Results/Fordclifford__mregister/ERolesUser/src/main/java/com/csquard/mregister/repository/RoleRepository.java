package com.csquard.mregister.repository;
 import com.csquard.mregister.model.Roles;
import com.csquard.mregister.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{


public Optional<Roles> findByName(RoleName roleName)
;

}