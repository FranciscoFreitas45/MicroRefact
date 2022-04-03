package run.halo.app.repository;
 import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import run.halo.app.model.entity.Photo;
import run.halo.app.repository.base.BaseRepository;
public interface PhotoRepository extends JpaSpecificationExecutor<Photo>, BaseRepository<Photo, Integer>{


public List<Photo> findByTeam(String team,Sort sort)
;

@Query(value = "select distinct p.team from Photo p")
public List<String> findAllTeams()
;

}