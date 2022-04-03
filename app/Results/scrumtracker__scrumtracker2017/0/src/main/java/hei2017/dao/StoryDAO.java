package hei2017.dao;
 import hei2017.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;
public interface StoryDAO extends JpaRepository<Story, Long>{


public Story findOneById(Long id)
;

public Story findOneByNom(String nom)
;

public long count()
;

public Story findByStoryTasksId(Long id)
;

public Set<Story> findByStorySprintId(Long idSprint)
;

public Set<Story> getSprintStories(Long id);

public void setSprintStories(Long id,Set<Story> sprintStories);

public Sprint addStory(Long id,Story story);

public void setStorySprint(Long id,Sprint storySprint);

public void setDescription(Long id,String description);

public void setDateCreation(Long id,Timestamp dateCreation);

public void setStatus(Long id,StoryStatus status);

public void setPoints(Long id,Integer points);

}