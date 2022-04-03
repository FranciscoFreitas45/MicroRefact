package hei2017.controller;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
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
import hei2017.Interface.StoryService;
import hei2017.Interface.TaskService;
import hei2017.Interface.UserService;
@EnableWebMvc
@RestController
public class ApiSprintController {

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


@JsonView(JsonViews.Sprint.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/sprint/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Sprint> showSprintsDetails(){
    LOGGER.debug("ApiController - showSprintsDetails");
    return sprintService.findAllWithAll();
}


@JsonView(JsonViews.Sprint.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/sprint/{id}/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Sprint> showSprintWithAll(Long id){
    LOGGER.debug("ApiController - showSprint");
    Sprint sprint = null;
    if (sprintService.exists(id)) {
        sprint = sprintService.findOneByIdWithAll(id);
        return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
    }
    return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/sprint/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Sprint> showSprint(Long id){
    LOGGER.debug("ApiController - showSprint");
    Sprint sprint = null;
    if (sprintService.exists(id)) {
        sprint = sprintService.findOneById(id);
        return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
    }
    return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/sprint/project/{idProject}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public Set<Sprint> showSprintsAssociatedToThisProject(Long idProject){
    LOGGER.debug("ApiController - showSprintsAssociatedToThisProject");
    Set<Sprint> sprints = sprintService.findBySprintProject(idProject);
    return sprints;
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/sprint", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Sprint> showSprints(){
    LOGGER.debug("ApiController - showSprints");
    return sprintService.findAll();
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/sprint/add/project/{idProject}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Sprint> sendSprintLinkedToProject(Sprint sprint,Long idProject){
    LOGGER.debug("ApiController - sendSprintLinkedToProject");
    Project project = projectService.findOneById(idProject);
    if (project == null)
        return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
    sprint.setSprintProject(project);
    sprintService.save(sprint);
    LOGGER.debug("ApiController - sendSprintLinkedToProject - Sprint créé et lié à un projet");
    return new ResponseEntity<Sprint>(sprint, HttpStatus.CREATED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/sprint/add", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Sprint> sendSprint(Sprint sprint){
    LOGGER.debug("ApiController - sendSprint");
    sprint = sprintService.save(sprint);
    LOGGER.debug("ApiController - sendSprint - Sprint créé");
    return new ResponseEntity<Sprint>(sprint, HttpStatus.CREATED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/sprint/update/{idSprint}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Sprint> updateSprint(Long idSprint,Sprint sprint){
    LOGGER.debug("ApiController - updateSprint");
    sprintService.updateSprint(idSprint, sprint);
    LOGGER.debug("ApiController - updateSprint - Project maj");
    return new ResponseEntity<Sprint>(sprint, HttpStatus.ACCEPTED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/sprint/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
public ResponseEntity<Sprint> deleteSprint(Long id){
    LOGGER.debug("ApiController - deleteSprint");
    Sprint sprint = null;
    if (sprintService.exists(id)) {
        sprint = sprintService.findOneById(id);
        sprintService.deleteOneById(sprint.getId());
        return new ResponseEntity<Sprint>(sprint, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/sprint/{idSprint}/story/{idStory}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Sprint> linkSprintIdWithStoryId(Long idSprint,Long idStory){
    LOGGER.debug("ApiController - linkSprintIdWithStoryId");
    Story story = storyService.findOneByIdWithAll(idStory);
    Sprint sprint = sprintService.findOneByIdWithAll(idSprint);
    if (null == sprint || null == story) {
        return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
    }
    story.setStorySprint(sprint);
    story = storyService.save(story);
    sprint = sprintService.findOneByIdWithAll(idSprint);
    return new ResponseEntity<Sprint>(sprint, HttpStatus.CREATED);
}


}