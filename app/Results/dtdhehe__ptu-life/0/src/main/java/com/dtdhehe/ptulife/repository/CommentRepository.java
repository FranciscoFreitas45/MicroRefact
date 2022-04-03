package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, String>{


public List<Comment> queryByPostIdAndPidIsNull(String postId,Sort sort)
;

public List<Comment> queryByPidAndPidIsNotNull(String id,Sort sort)
;

public List<Comment> queryByPostId(String pid)
;

}