package com.sda.inTeams.repository;
 import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{


public List<Comment> findAllByTask(Task task)
;

@Query(value = "Select * from comment u  where u.user_id = ?1", nativeQuery = true)
public List<Comment> findAllByCreator(User user)
;

}