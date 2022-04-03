package com.fzshuai.dao;
 import com.fzshuai.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, Long>{


public List<Comment> findByBlogIdAndParentCommentNull(Long blogId,Sort sort)
;

public List<Comment> getComments(Long id);

public void setComments(Long id,List<Comment> comments);

}