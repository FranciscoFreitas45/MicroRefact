package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Tag;
import run.halo.app.repository.base.BaseRepository;
public interface TagRepository extends BaseRepository<Tag, Integer>{


public long countByNameOrSlug(String name,String slug)
;

public Optional<Tag> getByName(String name)
;

public Optional<Tag> getBySlug(String slug)
;

}