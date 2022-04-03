package sn.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.model.PostLike;
import java.util.List;
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>{


@Query("SELECT pl FROM PostLike pl WHERE pl.person.id = :personId AND pl.post.id = :postId")
public PostLike findByPersonIdAndPostId(long personId,long postId)
;

@Query("SELECT pl FROM PostLike pl WHERE pl.post.id = :postId")
public List<PostLike> findAllByPostId(long postId)
;

@Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post.id = :postId")
public int getCount(long postId)
;

}