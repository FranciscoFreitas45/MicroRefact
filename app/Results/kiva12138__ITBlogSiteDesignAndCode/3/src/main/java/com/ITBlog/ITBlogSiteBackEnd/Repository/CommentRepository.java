package com.ITBlog.ITBlogSiteBackEnd.Repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ITBlog.ITBlogSiteBackEnd.Entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{


@Query(value = "SELECT comment from Comment comment WHERE comment.blogId=:blogId")
public List<Comment> getComByBlogId(long blogId)
;

@Query(value = "SELECT comment from Comment comment WHERE comment.authorId=:authorId")
public List<Comment> getComByAuthorId(long authorId)
;

@Modifying
@Query(value = "UPDATE Comment comment SET comment.content=:content WHERE comment.commentId=:commentId")
public int updateContent(String content,long commentId)
;

public Comment findByCommentId(long commentId)
;

}