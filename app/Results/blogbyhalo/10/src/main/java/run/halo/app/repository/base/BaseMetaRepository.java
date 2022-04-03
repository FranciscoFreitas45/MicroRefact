package run.halo.app.repository.base;
 import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.BaseMeta;
@NoRepositoryBean
public interface BaseMetaRepository extends BaseRepository<M, Long>, JpaSpecificationExecutor<M>{


@NonNull
public List<M> deleteByPostId(Integer postId)
;

@NonNull
public List<M> findAllByPostIdIn(Set<Integer> postIds)
;

@NonNull
public List<M> findAllByPostId(Integer postId)
;

}