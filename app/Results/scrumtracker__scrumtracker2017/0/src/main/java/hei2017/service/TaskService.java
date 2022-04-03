package hei2017.service;
 import hei2017.entity.Story;
import hei2017.entity.Task;
import java.util.List;
import java.util.Set;
public interface TaskService {


public long count()
;

public Task save(Task task)
;

public void delete(Task task)
;

public List<Task> findAll()
;

public void deleteOneById(Long id)
;

public Task updateTask(Long idTask,Task task)
;

public Set<Task> findByTaskStory(Long idStory)
;

public Task findOneById(Long id)
;

public List<Story> findBySprintStoryIdWithTasks(Long idSprint)
;

public Task findOneByNom(String nom)
;

public Boolean exists(String nom)
;

public Task findOneByIdWithAll(Long id)
;

public List<Task> findAllWithAll()
;

}