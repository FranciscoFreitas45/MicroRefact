package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{


public List<Project> findAllByProjectOwner(Team team)
;

public Optional<Project> findByTasksContaining(Task task)
;

}