package hei2017.service;
 import hei2017.entity.Sprint;
import java.util.List;
import java.util.Set;
public interface SprintService {


public long countAll()
;

public Sprint findOneById(Long id)
;

public Set<Sprint> findBySprintProject(Long idProject)
;

public Sprint save(Sprint sprint)
;

public Boolean exists(String nom)
;

public Sprint findOneByIdWithAll(Long id)
;

public List<Sprint> findAllWithAll()
;

public void delete(Sprint sprint)
;

public List<Sprint> findAll()
;

public void deleteOneById(Long id)
;

public List<Sprint> findByProjectSprintIdWithStories(Long idProject)
;

public Sprint updateSprint(Long idSprint,Sprint sprint)
;

}