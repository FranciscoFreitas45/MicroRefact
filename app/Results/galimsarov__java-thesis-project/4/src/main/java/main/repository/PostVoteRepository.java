package main.repository;
 import main.model.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PostVoteRepository extends JpaRepository<PostVote, Integer>{


@Query(value = "select * from post_votes where post_id = :postQuery and " + "user_id = :userQuery", nativeQuery = true)
public PostVote getPostVoteByPostAndUser(int postId,int userId)
;

}