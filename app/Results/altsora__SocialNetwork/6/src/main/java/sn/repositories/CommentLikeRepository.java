package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.CommentLike;
import java.util.List;
@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long>{


@Query("SELECT cl FROM CommentLike cl WHERE cl.person.id = :personId AND cl.comment.id = :commentId")
public CommentLike findByPersonIdAndCommentId(long personId,long commentId)
;

@Query("SELECT cl FROM CommentLike cl WHERE cl.comment.id = :commentId")
public List<CommentLike> findAllByCommentId(long commentId)
;

@Query("SELECT COUNT(cl) FROM CommentLike cl WHERE cl.comment.id = :commentId")
public int getCount(long commentId)
;

}