package hei2017.service;
 import hei2017.entity.Project;
import java.util.List;
public interface ProjectService {


public Project findOneById(Long id)
;

public Project findOneByNom(String nom)
;

public Project updateProject(Long idProject,Project project)
;

public long count()
;

public Project save(Project project)
;

public Boolean exists(String nom)
;

public Project findOneByIdWithAll(Long id)
;

public List<Project> findAllWithAll()
;

public void delete(Project project)
;

public List<Project> findAll()
;

public void deleteOneById(Long id)
;

}