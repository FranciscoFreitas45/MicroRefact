package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.repository.base.BasePostRepository;
public interface PostRepository extends JpaSpecificationExecutor<Post>, BasePostRepository<Post>{


@Override
@Query("select sum(p.visits) from Post p")
public Long countVisit()
;

@Override
@Query("select sum(p.likes) from Post p")
public Long countLike()
;

@Query("select post from Post post where year(post.createTime) = :year and month(post" + ".createTime) = :month and day(post.createTime) = :day and post.slug = :slug and post" + ".status = :status")
public Optional<Post> findBy(Integer year,Integer month,Integer day,String slug,PostStatus status)
;

}