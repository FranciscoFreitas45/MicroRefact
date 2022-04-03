package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{


public List<Comment> findAllByTask(Task task)
;

public List<Comment> findAllByCreator(User user)
;

}