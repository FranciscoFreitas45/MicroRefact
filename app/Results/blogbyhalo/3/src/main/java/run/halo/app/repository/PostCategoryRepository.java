package run.halo.app.repository;
 import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.PostCategory;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.projection.CategoryPostCountProjection;
import run.halo.app.repository.base.BaseRepository;
public interface PostCategoryRepository extends BaseRepository<PostCategory, Integer>{


@NonNull
@Query("select postCategory.categoryId from PostCategory postCategory where postCategory" + ".postId = ?1")
public Set<Integer> findAllCategoryIdsByPostId(Integer postId)
;

@NonNull
public List<PostCategory> deleteByPostId(Integer postId)
;

@NonNull
public List<PostCategory> deleteByCategoryId(Integer categoryId)
;

@NonNull
public List<PostCategory> findAllByPostIdIn(Collection<Integer> postIds)
;

@NonNull
public List<PostCategory> findAllByCategoryId(Integer categoryId)
;

@Query("select new run.halo.app.model.projection.CategoryPostCountProjection(count(pc.postId)" + ", pc.categoryId) from PostCategory pc group by pc.categoryId")
@NonNull
public List<CategoryPostCountProjection> findPostCount()
;

@NonNull
@Query("select postCategory.postId from PostCategory postCategory, Post post where" + " postCategory.categoryId = ?1 and post.id = postCategory.postId and post.status in (?2)")
public Set<Integer> findAllPostIdsByCategoryId(Integer categoryId,Set<PostStatus> status)
;

@NonNull
public List<PostCategory> findAllByPostId(Integer postId)
;

@Query("select pc from PostCategory pc where pc.categoryId in (?1)")
@NonNull
public List<PostCategory> findAllByCategoryIdList(List<Integer> categoryIdList)
;

}