package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


public Optional<Task> findByCommentsContaining(Comment comment)
;

public List<Task> findAllByUserResponsible(User user)
;

public List<Task> findAllByProject(Project project)
;

}