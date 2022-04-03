package main.repository;
 import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{


@Query(value = "select count(*) from posts where posts.is_active = 1 and " + "posts.moderation_status = 'ACCEPTED' and posts.time < " + "current_time() and (posts.text like concat('%',:query,'%') or " + "posts.title like concat('%',:query,'%'))", nativeQuery = true)
public int getCountOfPostsByQuery(String query)
;

@Query(value = "select count(*) from posts where is_active = 0 and " + "user_id = :query", nativeQuery = true)
public int getCountOfMyInactivePosts(int id)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "posts.moderation_status = 'DECLINED' and (moderator_id = :query " + "or moderator_id = 0)", nativeQuery = true)
public int getCountOfDeclinedPosts(int id)
;

@Query(value = "select * from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and user_id = :query", nativeQuery = true)
public List<Post> getMyPublishedPosts(int id,Pageable pageable)
;

@Query(value = "select * from posts where is_active = 1 and " + "posts.moderation_status = 'ACCEPTED' and (moderator_id = :query " + "or moderator_id = 0)", nativeQuery = true)
public List<Post> getAcceptedPosts(int id,Pageable pageable)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "posts.moderation_status = 'NEW' and (moderator_id = :query or " + "moderator_id = 0)", nativeQuery = true)
public int getCountOfNewPosts(int id)
;

@Query(value = "select sum(view_count) from posts where user_id = :query " + "and is_active = 1 and moderation_status = 'ACCEPTED' and time < " + "current_time()", nativeQuery = true)
public int getViewsCountOfUsersPosts(int userId)
;

@Query(value = "select count(*) from posts join (select value, post_id " + "from post_votes) as temp_votes on posts.id = temp_votes.post_id " + "where is_active = 1 and moderation_status = 'ACCEPTED' and time " + "< current_time() and value = 1", nativeQuery = true)
public int getLikesCount()
;

@Query(value = "select * from posts where id = :query and " + "is_active = 1 and moderation_status = 'ACCEPTED' and " + "time <= current_time()", nativeQuery = true)
public Post getPost(int id)
;

@Query(value = "select * from posts where is_active = 1 and " + "posts.moderation_status = 'NEW' and (moderator_id = :query or " + "moderator_id = 0)", nativeQuery = true)
public List<Post> getNewPosts(int id,Pageable pageable)
;

@Query(value = "select * from posts where is_active = 1 and " + "moderation_status = 'DECLINED' and user_id = :query", nativeQuery = true)
public List<Post> getMyDeclinedPosts(int id,Pageable pageable)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public int getPostsCount()
;

@Query(value = "select count(*) from (select posts.id, posts.is_active, " + "posts.moderation_status, posts.moderator_id, posts.text, " + "posts.time, posts.title, posts.view_count, posts.user_id from " + "posts join (select tag2post.tag_id, tag2post.post_id, tags.name " + "as tag_name from tag2post join tags on tag2post.tag_id = tags.id) " + "as temp on posts.id = temp.post_id where temp.tag_name = :query) " + "as new_table where new_table.is_active = 1 and " + "new_table.moderation_status = 'ACCEPTED' and new_table.time < " + "current_time()", nativeQuery = true)
public int getCountOfPostsByTag(String tag)
;

@Query(value = "select * from posts where is_active = 1 and " + "moderation_status = 'NEW' and user_id = :query", nativeQuery = true)
public List<Post> getMyPendingPosts(int id,Pageable pageable)
;

@Query(value = "SELECT posts.id, posts.is_active, posts.moderation_status, " + "posts.moderator_id, posts.text, posts.time, posts.title, " + "posts.view_count, posts.user_id FROM posts left join " + "(SELECT post_id, count(id) as comments from post_comments " + "group by post_id order by count(id) desc) as comments " + "on posts.id = comments.post_id where posts.is_active = 1 " + "and posts.moderation_status = 'ACCEPTED' and posts.time < " + "current_time() order by comments desc", countQuery = "SELECT count(*) Posts", nativeQuery = true)
public Page<Post> findPostsSortedByCommentsCount(Pageable pageable)
;

@Query(value = "select sum(view_count) from posts where is_active = 1 " + "and moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public int getViewsCount()
;

@Query(value = "SELECT * FROM posts where posts.is_active = 1 and " + "posts.moderation_status = 'ACCEPTED' and posts.time < " + "current_time() and (posts.text like concat('%',:query,'%') " + "or posts.title like concat('%',:query,'%'))", nativeQuery = true)
public List<Post> searchForPostsByQuery(String query,Pageable pageable)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public int getActivePosts()
;

@Query(value = "select count(*) from (select * from posts where " + "moderation_status = 'NEW' and moderator_id = :query or " + "moderator_id = 0) as new_posts", nativeQuery = true)
public int getCountOfPostsForModeration(int moderatorId)
;

@Query(value = "select count(*) from posts join (select value, post_id " + "from post_votes) as temp_votes on posts.id = temp_votes.post_id " + "where user_id = :query and is_active = 1 and moderation_status " + "= 'ACCEPTED' and time < current_time() and value = 1", nativeQuery = true)
public int getLikesCountOfUsersPosts(int userId)
;

@Query(value = "select min(time) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public Date getFirstPost()
;

@Query(value = "SELECT * FROM posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time() " + "order by time desc", nativeQuery = true)
public Page<Post> findRecentPosts(Pageable pageable)
;

@Query(value = "select min(time) from posts where user_id = :query and " + "is_active = 1 and moderation_status = 'ACCEPTED' and time < " + "current_time()", nativeQuery = true)
public Date getFirstPostOfUser(int userId)
;

@Query(value = "select substr(time, 1, 10) as day from posts where " + "substr(time, 1, 4) like :query", nativeQuery = true)
public List<String> getPostsForTheYear(String year)
;

@Query(value = "select count(*) from posts where user_id = :query and " + "is_active = 1 and moderation_status = 'ACCEPTED' and time " + "< current_time()", nativeQuery = true)
public int getPostsCountOfUser(int userId)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time > :dateBefore and time < " + ":dateAfter and time < current_time()", nativeQuery = true)
public int getCountOfPostsByDate(String dateBefore,String dateAfter)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time()", nativeQuery = true)
public int findCountOfPosts()
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'NEW' and user_id = :query", nativeQuery = true)
public int getCountOfMyPendingPosts(int id)
;

@Query(value = "select count(*) from posts join (select value, post_id " + "from post_votes) as temp_votes on posts.id = temp_votes.post_id " + "where is_active = 1 and moderation_status = 'ACCEPTED' and time " + "< current_time() and value = -1", nativeQuery = true)
public int getDisLikesCount()
;

@Query(value = "select * from posts where is_active = 0 and user_id = :query", nativeQuery = true)
public List<Post> getMyInactivePosts(int id,Pageable pageable)
;

@Query(value = "SELECT * FROM posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time < current_time() " + "order by time", nativeQuery = true)
public Page<Post> findEarlyPosts(Pageable pageable)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "posts.moderation_status = 'ACCEPTED' and (moderator_id = :query " + "or moderator_id = 0)", nativeQuery = true)
public int getCountOfAcceptedPosts(int id)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and user_id = :query", nativeQuery = true)
public int getCountOfMyPublishedPosts(int id)
;

@Query(value = "select * from posts where is_active = 1 and " + "posts.moderation_status = 'DECLINED' and (moderator_id = :query " + "or moderator_id = 0)", nativeQuery = true)
public List<Post> getDeclinedPosts(int id,Pageable pageable)
;

@Query(value = "select posts.id, posts.is_active, posts.moderation_status, " + "posts.moderator_id, posts.text, posts.time, posts.title, " + "posts.view_count, posts.user_id from posts join (select " + "tag2post.tag_id, tag2post.post_id, tags.name as tag_name from " + "tag2post join tags on tag2post.tag_id = tags.id) as temp on " + "posts.id = temp.post_id where posts.is_active = 1 and " + "posts.moderation_status = 'ACCEPTED' and posts.time < " + "current_time() and temp.tag_name = :query", nativeQuery = true)
public List<Post> getPostsByTag(String tag,Pageable pageable)
;

@Query(value = "select count(*) from posts join (select value, post_id " + "from post_votes) as temp_votes on posts.id = temp_votes.post_id " + "where user_id = :query and is_active = 1 and moderation_status " + "= 'ACCEPTED' and time < current_time() and value = -1", nativeQuery = true)
public int getDisLikesCountOfUsersPosts(int userId)
;

@Query(value = "select * from posts where is_active = 1 and " + "moderation_status = 'ACCEPTED' and time > :dateBefore " + " and time < :dateAfter and time < current_time()", nativeQuery = true)
public List<Post> getPostsByDate(String dateBefore,String dateAfter,Pageable pageable)
;

@Query(value = "SELECT posts.id, " + "posts.is_active, posts.moderation_status, posts.moderator_id, " + "posts.text, posts.time, posts.title, posts.view_count, " + "posts.user_id FROM posts left join (SELECT post_id, " + "count(value) as likes from post_votes where value = 1 " + "group by post_id order by count(value) desc) as likes " + "on posts.id = likes.post_id where posts.is_active = 1 " + "and posts.moderation_status = 'ACCEPTED' " + "and posts.time < current_time() order by likes desc", countQuery = "SELECT count(*) Posts", nativeQuery = true)
public Page<Post> findPostsSortedByLikesCount(Pageable pageable)
;

@Query(value = "select count(*) from posts where is_active = 1 and " + "moderation_status = 'DECLINED' and user_id = :query", nativeQuery = true)
public int getCountOfMyDeclinedPosts(int id)
;

public void addPostComment(int id,PostComment postComment);

}