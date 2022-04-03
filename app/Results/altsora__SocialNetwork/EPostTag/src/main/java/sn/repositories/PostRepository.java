package sn.repositories;
 import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.Post;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

 private String POST_TIME;


@Query("SELECT p FROM Post p WHERE p.author.id = :personId")
public List<Post> findAllByPersonId(long personId,Pageable pageable)
;

@Query("SELECT count(p) FROM Post p WHERE p.author.id = :personId")
public int getTotalCountPostsByPersonId(long personId)
;

@Query(value = "SELECT * FROM posts where posts.time < " + ":dateTo and posts.time > :dateFrom and posts.text like " + "concat('%',:query,'%')", nativeQuery = true)
public List<Post> findAllByTextAndTime(String text,LocalDateTime dateFrom,LocalDateTime dateTo,Pageable pageable)
;

}