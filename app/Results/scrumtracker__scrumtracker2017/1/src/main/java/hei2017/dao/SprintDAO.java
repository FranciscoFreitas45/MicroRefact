package hei2017.dao;
 import hei2017.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;
public interface SprintDAO extends JpaRepository<Sprint, Long>{


public Set<Sprint> findBySprintProjectId(Long idProject)
;

public Sprint findOneById(Long id)
;

public Sprint findOneByNom(String nom)
;

public long count()
;

public Sprint findBySprintStoriesId(Long id)
;

public Sprint getStorySprint(Long id);

public void setStorySprint(Long id,Sprint storySprint);

public Set<Sprint> getProjectSprints(Long id);

public void setProjectSprints(Long id,Set<Sprint> projectSprints);

public void addSprint(Long id,Sprint sprint);

public void setDescription(Long id,String description);

public void setDateCreation(Long id,Timestamp dateCreation);

public void setDateDebut(Long id,Timestamp dateDebut);

public void setDateFin(Long id,Timestamp dateFin);

public void setSprintProject(Long id,Project sprintProject);

}