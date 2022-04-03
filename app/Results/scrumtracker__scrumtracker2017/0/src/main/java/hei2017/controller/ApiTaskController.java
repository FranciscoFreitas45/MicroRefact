package hei2017.controller;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Story;
import hei2017.entity.Task;
import hei2017.enumeration.StoryStatus;
import hei2017.json.JsonViews;
import hei2017.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import hei2017.Interface.ProjectService;
import hei2017.Interface.SprintService;
import hei2017.Interface.UserService;
@EnableWebMvc
@RestController
public class ApiTaskController {

 private  Logger LOGGER;

@Inject
 private ProjectService projectService;

@Inject
 private SprintService sprintService;

@Inject
 private StoryService storyService;

@Inject
 private TaskService taskService;

@Inject
 private UserService userService;


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/task/update/{idTask}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Task> updateTask(Long idTask,Task task){
    LOGGER.debug("ApiController - updateTask");
    taskService.updateTask(idTask, task);
    LOGGER.debug("ApiController - idTask - Task maj");
    return new ResponseEntity<Task>(task, HttpStatus.ACCEPTED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/task/add", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Task> sendStory(Task task){
    LOGGER.debug("ApiController - sendTask");
    task = taskService.save(task);
    LOGGER.debug("ApiController - sendTask - Task créé");
    return new ResponseEntity<Task>(task, HttpStatus.CREATED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/task/{idTask}/update/status", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Task> updateTaskStatus(Long idTask,String status){
    LOGGER.debug("ApiController - updateTaskStatus");
    Task task = null;
    if (taskService.exists(idTask)) {
        task = taskService.findOneById(idTask);
        switch(status) {
            case "todo":
                task.setStatus(StoryStatus.TODO);
                break;
            case "doing":
                task.setStatus(StoryStatus.DOING);
                break;
            case "done":
                task.setStatus(StoryStatus.DONE);
                break;
            default:
                return new ResponseEntity<Task>(task, HttpStatus.NO_CONTENT);
        }
        // TODO REVENIR ICI
        task = taskService.save(task);
        return new ResponseEntity<Task>(task, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/task", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Task> showTasks(){
    LOGGER.debug("ApiController - showTasks");
    return taskService.findAll();
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/task/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
public ResponseEntity<Task> deleteTask(Long id){
    LOGGER.debug("ApiController - deleteTask");
    Task task = null;
    if (taskService.exists(id)) {
        task = taskService.findOneById(id);
        taskService.deleteOneById(task.getId());
        return new ResponseEntity<Task>(task, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Task.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/task/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Task> showTasksDetails(){
    LOGGER.debug("ApiController - showTasksDetails");
    return taskService.findAllWithAll();
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/task/story/{idStory}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public Set<Task> showTasksAssociatedToThisStory(Long idStory){
    LOGGER.debug("ApiController - showTasksAssociatedToThisStory");
    Set<Task> tasks = taskService.findByTaskStory(idStory);
    return tasks;
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/task/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Task> showTask(Long id){
    LOGGER.debug("ApiController - showTask");
    Task task = null;
    if (taskService.exists(id)) {
        task = taskService.findOneById(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
    return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Task.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/task/{id}/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Task> showTaskWithAll(Long id){
    LOGGER.debug("ApiController - showTaskWithAll");
    Task task = null;
    if (taskService.exists(id)) {
        task = taskService.findOneByIdWithAll(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
    return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/task/add/story/{idStory}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Task> sendTaskWithStoryId(Long idStory,Task task){
    LOGGER.debug("ApiController - sendTaskWithStoryId");
    taskService.save(task);
    Story story = storyService.findOneByIdWithAll(idStory);
    if (null == story) {
        LOGGER.debug("ApiController - sendTaskWithStoryId - Story inexistante");
        return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
    }
    if (task.getStatus() == null)
        task.setStatus(StoryStatus.TODO);
    story.addTask(task);
    storyService.save(story);
    task.setTaskStory(story);
    taskService.save(task);
    LOGGER.debug("ApiController - sendTaskWithStoryId - Task créée");
    return new ResponseEntity<Task>(task, HttpStatus.CREATED);
}


}