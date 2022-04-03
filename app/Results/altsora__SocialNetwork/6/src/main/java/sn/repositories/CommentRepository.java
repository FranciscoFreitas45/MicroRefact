package sn.repositories;
 import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.Comment;
import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

 private String COMMENT_TIME;


@Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
public List<Comment> findAllCommentsByPostId(long postId,Sort sort)
;

}