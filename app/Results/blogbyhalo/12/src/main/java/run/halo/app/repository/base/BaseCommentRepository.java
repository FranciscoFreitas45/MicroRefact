package run.halo.app.repository.base;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.annotation.SensitiveConceal;
import run.halo.app.model.entity.BaseComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.projection.CommentChildrenCountProjection;
import run.halo.app.model.projection.CommentCountProjection;
@NoRepositoryBean
public interface BaseCommentRepository extends JpaSpecificationExecutor<COMMENT>, BaseRepository<COMMENT, Long>{


@NonNull
public List<COMMENT> deleteByPostId(Integer postId)
;

@NonNull
@SensitiveConceal
public Page<COMMENT> findAllByPostIdAndStatus(Integer postId,CommentStatus status,Pageable pageable)
;

@NonNull
@SensitiveConceal
public List<COMMENT> findAllByStatusAndParentIdIn(CommentStatus status,Collection<Long> parentIds)
;

@NonNull
@SensitiveConceal
public Page<COMMENT> findAllByStatus(CommentStatus status,Pageable pageable)
;

public long countByStatus(CommentStatus status)
;

@NonNull
@SensitiveConceal
public List<COMMENT> findAllByPostIdIn(Collection<Integer> postIds)
;

@NonNull
@SensitiveConceal
public List<COMMENT> findAllByPostId(Integer postId)
;

@Query("select new run.halo.app.model.projection.CommentChildrenCountProjection(count(comment" + ".id), comment.parentId) " + "from BaseComment comment " + "where comment.parentId in ?1 " + "and comment.status = ?2 " + "group by comment.parentId")
@NonNull
public List<CommentChildrenCountProjection> findDirectChildrenCount(Collection<Long> commentIds,CommentStatus status)
;

@NonNull
public List<COMMENT> deleteByParentId(Long id)
;

@NonNull
@SensitiveConceal
public Page<COMMENT> findAllByPostIdAndStatusAndParentId(Integer postId,CommentStatus status,Long parentId,Pageable pageable)
;

@SensitiveConceal
public List<COMMENT> findAllByParentIdIn(Collection<Long> parentIds)
;

@Query("select new run.halo.app.model.projection.CommentCountProjection(count(comment.id), " + "comment.postId) " + "from BaseComment comment " + "where comment.status = ?1 " + "and comment.postId in ?2 " + "group by comment.postId")
@NonNull
public List<CommentCountProjection> countByStatusAndPostIds(CommentStatus status,Collection<Integer> postIds)
;

@Query("select new run.halo.app.model.projection.CommentCountProjection(count(comment.id), " + "comment.postId) " + "from BaseComment comment " + "where comment.postId in ?1 " + "group by comment.postId")
@NonNull
public List<CommentCountProjection> countByPostIds(Collection<Integer> postIds)
;

@NonNull
@SensitiveConceal
public List<COMMENT> findAllByPostIdAndParentId(Integer postId,Long parentId)
;

public long countByPostId(Integer postId)
;

}