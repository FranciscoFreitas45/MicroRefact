package run.halo.app.repository.base;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
@NoRepositoryBean
public interface BaseRepository extends JpaRepository<D, I>{


public long deleteByIdIn(Collection<I> ids)
;

@NonNull
public Page<D> findAllByIdIn(Collection<I> ids,Pageable pageable)
;

}