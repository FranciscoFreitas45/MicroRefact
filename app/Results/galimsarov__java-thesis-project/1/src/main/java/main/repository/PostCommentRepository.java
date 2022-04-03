package main.repository;
 import main.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Integer>{


@Query(value = "select id from post_comments where time = :query", nativeQuery = true)
public int findIdByTime(Date time)
;

}