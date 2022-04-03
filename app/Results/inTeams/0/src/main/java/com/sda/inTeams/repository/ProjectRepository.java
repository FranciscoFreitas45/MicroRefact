package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.DTO.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

@Query(value = "Select * from project u  where u.team_id = ?1", nativeQuery = true)
public List<Project> findAllByProjectOwner(Team team)
;



public Optional<Project> findByTasksContaining(Task task)
;

}