package hei2017.service;
 import hei2017.entity.Story;
import java.util.List;
import java.util.Set;
public interface StoryService {


public Set<Story> findByStorySprint(Long idSprint)
;

public List<Story> findAllWithoutSprint()
;

public Story updateStory(Long id,Story story)
;

public Long count()
;

public Story save(Story story)
;

public Set<Story> findByStorySprintWithTask(Long idSprint)
;

public void delete(Story story)
;

public List<Story> findAll()
;

public void deleteOneById(Long id)
;

public Story findOneById(Long id)
;

public Story findOneByNom(String nom)
;

public Boolean exists(String nom)
;

public Story findOneByIdWithAll(Long id)
;

public List<Story> findAllWithAll()
;

}