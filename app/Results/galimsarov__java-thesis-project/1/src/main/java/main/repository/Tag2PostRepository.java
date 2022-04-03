package main.repository;
 import main.model.TagToPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface Tag2PostRepository extends JpaRepository<TagToPost, Integer>{


@Query(value = "select tag2post.id, post_id, tag_id from tag2post join posts " + "on tag2post.post_id = posts.id where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public List<TagToPost> findActiveTagToPosts()
;

}