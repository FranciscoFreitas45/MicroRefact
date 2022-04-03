package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.repository.base.BasePostRepository;
public interface SheetRepository extends BasePostRepository<Sheet>{


@Override
@Query("select sum(p.visits) from Sheet p")
public Long countVisit()
;

@NonNull
@Override
public Optional<Sheet> getBySlugAndStatus(String slug,PostStatus status)
;

@Override
@Query("select sum(p.likes) from Sheet p")
public Long countLike()
;

}