package hei2017.dao;
 import hei2017.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;
public interface TaskDAO extends JpaRepository<Task, Long>{


public Task findOneById(Long id)
;

public Set<Task> findByTaskStoryId(Long idStory)
;

public Task findOneByNom(String nom)
;

public Set<Task> findByTaskUsersId(Long id)
;

public long count()
;

public void setTaskStory(Long id,Story taskStory);

public void setNom(Long id,String nom);

public void setDescription(Long id,String description);

public void setTempsDeCharge(Long id,Long tempsDeCharge);

public void setStatus(Long id,StoryStatus status);

}