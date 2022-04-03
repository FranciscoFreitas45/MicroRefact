package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


public Optional<Task> findByCommentsContaining(Comment comment)
;

@Query(value = "Select * from task u  where u.user_id = ?1", nativeQuery = true)
public List<Task> findAllByUserResponsible(User user)
;

public List<Task> findAllByProject(Project project)
;

}