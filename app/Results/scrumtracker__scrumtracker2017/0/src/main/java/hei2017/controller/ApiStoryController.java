package hei2017.controller;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
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
public class ApiStoryController {

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
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/{id}/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Story> showStoryWithAll(Long id){
    LOGGER.debug("ApiController - showStoryWithAll");
    Story story = null;
    if (storyService.exists(id)) {
        story = storyService.findOneByIdWithAll(id);
        return new ResponseEntity<Story>(story, HttpStatus.OK);
    }
    return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/sprint/{idSprint}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public Set<Story> showStoriesAssociatedToThisSprint(Long idSprint){
    LOGGER.debug("ApiController - showStoriesAssociatedToThisSprint");
    Set<Story> stories = storyService.findByStorySprint(idSprint);
    return stories;
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/sprint/{idSprint}/tasks", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public Set<Story> showStoriesAssociatedToThisSprintWithTasks(Long idSprint){
    LOGGER.debug("ApiController - showStoriesAssociatedToThisSprintWithTasks");
    Set<Story> stories = storyService.findByStorySprintWithTask(idSprint);
    return stories;
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/story/add", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Story> sendStory(Story story){
    LOGGER.debug("ApiController - sendStory");
    story = storyService.save(story);
    LOGGER.debug("ApiController - sendStory - Story créé");
    return new ResponseEntity<Story>(story, HttpStatus.CREATED);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/story/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
public ResponseEntity<Story> deleteStory(Long id){
    LOGGER.debug("ApiController - deleteStory");
    Story story = null;
    if (storyService.exists(id)) {
        story = storyService.findOneById(id);
        storyService.deleteOneById(story.getId());
        return new ResponseEntity<Story>(story, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/story/update/{idStory}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Story> updateStory(Long idStory,Story story,Long idSprint){
    LOGGER.debug("ApiController - updateStory");
    if (idSprint != null) {
        Sprint sprint = sprintService.findOneById(idSprint);
        if (null == sprint) {
            LOGGER.debug("ApiController - updateStory - Sprint inexistant");
            return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
        }
        story.setStorySprint(sprint);
    }
    storyService.updateStory(idStory, story);
    LOGGER.debug("ApiController - updateStory - Story maj");
    return new ResponseEntity<Story>(story, HttpStatus.ACCEPTED);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Story> showStories(){
    LOGGER.debug("ApiController - showStories");
    return storyService.findAll();
}


@JsonView(JsonViews.Story.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Story> showStoriesDetails(){
    LOGGER.debug("ApiController - showStoriesDetails");
    return storyService.findAllWithAll();
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/story/add/sprint/{idSprint}", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Story> sendStoryWithSprintId(Long idSprint,Story story){
    LOGGER.debug("ApiController - sendStoryWithSprintId");
    Sprint sprint = sprintService.findOneById(idSprint);
    if (null == sprint) {
        LOGGER.debug("ApiController - sendStoryWithSprintId - Sprint inexistant");
        return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
    }
    if (story.getStatus() == null)
        story.setStatus(StoryStatus.TODO);
    story = storyService.save(story);
    story.setStorySprint(sprint);
    story = storyService.save(story);
    LOGGER.debug("ApiController - sendStoryWithSprintId - Story créé");
    return new ResponseEntity<Story>(story, HttpStatus.CREATED);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<Story> showStory(Long id){
    LOGGER.debug("ApiController - showStory");
    Story story = null;
    if (storyService.exists(id)) {
        story = storyService.findOneById(id);
        return new ResponseEntity<Story>(story, HttpStatus.OK);
    }
    return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/story/sprint/null", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Story> showStoriesWithNoSprint(){
    LOGGER.debug("ApiStoryController - showStoriesWithNoSprint");
    List<Story> stories = storyService.findAllWithoutSprint();
    return stories;
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/story/{idStory}/detachFromSprint", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<Story> detachStoryFromSprint(Long idStory){
    LOGGER.debug("ApiStoryController - detachStoryFromSprint");
    Story story = null;
    if (storyService.exists(idStory)) {
        story = storyService.findOneById(idStory);
        story.setStorySprint(null);
        story = storyService.save(story);
        return new ResponseEntity<Story>(story, HttpStatus.OK);
    }
    return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
}


}