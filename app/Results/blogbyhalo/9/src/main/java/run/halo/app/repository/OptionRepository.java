package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import run.halo.app.model.entity.Option;
import run.halo.app.repository.base.BaseRepository;
public interface OptionRepository extends BaseRepository<Option, Integer>, JpaSpecificationExecutor<Option>{


public Optional<Option> findByKey(String key)
;

public void deleteByKey(String key)
;

}