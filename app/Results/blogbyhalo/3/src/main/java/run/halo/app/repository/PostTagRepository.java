package run.halo.app.repository;
 import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.PostTag;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.projection.TagPostPostCountProjection;
import run.halo.app.repository.base.BaseRepository;
public interface PostTagRepository extends BaseRepository<PostTag, Integer>{


@NonNull
public List<PostTag> deleteByPostId(Integer postId)
;

@Query("select new run.halo.app.model.projection.TagPostPostCountProjection(count(pt.postId)," + " pt.tagId) from PostTag pt where pt.tagId in ?1 group by pt.tagId")
@NonNull
public List<TagPostPostCountProjection> findPostCountByTagIds(Collection<Integer> tagIds)
;

@NonNull
public List<PostTag> findAllByPostIdIn(Collection<Integer> postIds)
;

@NonNull
public List<PostTag> deleteByTagId(Integer tagId)
;

@Query("select postTag.tagId from PostTag postTag where postTag.postId = ?1")
@NonNull
public Set<Integer> findAllTagIdsByPostId(Integer postId)
;

@Query("select new run.halo.app.model.projection.TagPostPostCountProjection(count(pt.postId)," + " pt.tagId) from PostTag pt group by pt.tagId")
@NonNull
public List<TagPostPostCountProjection> findPostCount()
;

@Query("select postTag.postId from PostTag postTag,Post post where postTag.tagId = ?1 and " + "post.id = postTag.postId and post.status = ?2")
@NonNull
public Set<Integer> findAllPostIdsByTagId(Integer tagId,PostStatus status)
;

@NonNull
public List<PostTag> findAllByTagId(Integer tagId)
;

@NonNull
public List<PostTag> findAllByPostId(Integer postId)
;

}