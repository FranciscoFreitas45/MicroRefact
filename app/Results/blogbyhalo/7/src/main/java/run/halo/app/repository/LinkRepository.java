package run.halo.app.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import run.halo.app.model.entity.Link;
import run.halo.app.repository.base.BaseRepository;
public interface LinkRepository extends BaseRepository<Link, Integer>{


public boolean existsByUrlAndIdNot(String url,Integer id)
;

@Query(value = "select distinct a.team from Link a")
public List<String> findAllTeams()
;

public boolean existsByNameAndIdNot(String name,Integer id)
;

}