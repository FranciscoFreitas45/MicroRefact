package run.halo.app.repository;
 import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Category;
import run.halo.app.repository.base.BaseRepository;
public interface CategoryRepository extends BaseRepository<Category, Integer>{


public long countByName(String name)
;

public List<Category> findByParentId(Integer id)
;

public Optional<Category> getByName(String name)
;

public Optional<Category> getBySlug(String slug)
;

public long countById(Integer id)
;

}