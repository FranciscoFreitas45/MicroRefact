package com.sda.inTeams.repository;
 import com.sda.inTeams.model.User.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long>{


public Optional<AccountRole> findByName(String name)
;

}