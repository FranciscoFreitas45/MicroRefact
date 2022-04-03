package run.halo.app.repository.base;
 import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.BasePost;
import run.halo.app.model.enums.PostStatus;
public interface BasePostRepository extends BaseRepository<POST, Integer>{


@NonNull
public List<POST> findAllByStatus(PostStatus status,Sort sort)
;

@Modifying
@Query("update BasePost p set p.formatContent = :formatContent where p.id = :postId")
public int updateFormatContent(String formatContent,Integer postId)
;

public long countByStatus(PostStatus status)
;

@NonNull
public Optional<POST> getBySlugAndStatus(String slug,PostStatus status)
;

@Modifying
@Query("update BasePost p set p.originalContent = :content where p.id = :postId")
public int updateOriginalContent(String content,Integer postId)
;

@Query("select sum(p.likes) from BasePost p")
public Long countLike()
;

@NonNull
public Optional<POST> getByIdAndStatus(Integer id,PostStatus status)
;

@NonNull
public Page<POST> findAllByStatusAndEditTimeBefore(PostStatus status,Date editTime,Pageable pageable)
;

@Modifying
@Query("update BasePost p set p.visits = p.visits + :visits where p.id = :postId")
public int updateVisit(long visits,Integer postId)
;

public boolean existsBySlug(String slug)
;

@Modifying
@Query("update BasePost p set p.likes = p.likes + :likes where p.id = :postId")
public int updateLikes(long likes,Integer postId)
;

@NonNull
public Page<POST> findAllByStatusAndCreateTimeAfter(PostStatus status,Date createTime,Pageable pageable)
;

@Query("select sum(p.visits) from BasePost p")
public Long countVisit()
;

@Modifying
@Query("update BasePost p set p.status = :status where p.id = :postId")
public int updateStatus(PostStatus status,Integer postId)
;

@NonNull
public Page<POST> findAllByStatusAndEditTimeAfter(PostStatus status,Date editTime,Pageable pageable)
;

public Optional<POST> getBySlug(String slug)
;

public boolean existsByIdNotAndSlug(Integer id,String slug)
;

@NonNull
public Page<POST> findAllByStatusAndCreateTimeBefore(PostStatus status,Date createTime,Pageable pageable)
;

@NonNull
public Page<POST> findAllByStatusAndVisitsBefore(PostStatus status,Long visits,Pageable pageable)
;

@NonNull
public Page<POST> findAllByStatusAndVisitsAfter(PostStatus status,Long visits,Pageable pageable)
;

}