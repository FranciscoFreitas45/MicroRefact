package run.halo.app.repository;
 import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Menu;
import run.halo.app.repository.base.BaseRepository;
public interface MenuRepository extends BaseRepository<Menu, Integer>{


public List<Menu> findByParentId(Integer id)
;

public boolean existsByName(String name)
;

public List<Menu> findByTeam(String team,Sort sort)
;

public boolean existsByIdNotAndName(Integer id,String name)
;

@Query(value = "select distinct a.team from Menu a")
public List<String> findAllTeams()
;

}