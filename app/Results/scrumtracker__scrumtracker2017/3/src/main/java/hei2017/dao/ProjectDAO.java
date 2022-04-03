package hei2017.dao;
 import hei2017.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Set;
public interface ProjectDAO extends JpaRepository<Project, Long>{


public Project findOneById(Long id)
;

public Project findOneByNom(String nom)
;

public long count()
;

public Set<Project> findByProjectSprintsId(Long id)
;

public void setSprintProject(Long id,Project sprintProject);

public Project getSprintProject(Long id);

public void setDescription(Long id,String description);

public void setDateCreation(Long id,Timestamp dateCreation);

}