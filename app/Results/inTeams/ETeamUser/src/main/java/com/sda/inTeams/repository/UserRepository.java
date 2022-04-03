package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public Optional<User> findByUsername(String username)
;

public Optional<User> findByUniqueInvitationId(String uniqueInvitationId)
;

public List<User> findAllByTeamsContaining(Team team)
;

public Optional<User> findByFirstNameAndLastName(String firstName,String lastName)
;

}