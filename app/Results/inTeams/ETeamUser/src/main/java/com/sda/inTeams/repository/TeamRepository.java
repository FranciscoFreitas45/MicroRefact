package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Team.Team;
import com.sda.inTeams.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{


public List<Team> findAllByMembersContaining(User member)
;

public Optional<Team> findByName(String name)
;

public List<Team> findAllByTeamOwner(User user)
;

public Optional<Team> findByProjectsContaining(Project project)
;

}