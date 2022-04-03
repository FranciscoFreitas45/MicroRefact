package hei2017.controller;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Project;
import hei2017.entity.Task;
import hei2017.entity.User;
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
import hei2017.Interface.ProjectService;
import hei2017.Interface.SprintService;
import hei2017.Interface.StoryService;
import hei2017.Interface.TaskService;
import hei2017.DTO.User;
@EnableWebMvc
@RestController
public class ApiUserController {

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
@RequestMapping(value = "/api/user/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<User> showUser(Long id){
    LOGGER.debug("ApiController - showUser");
    if (userService.exists(id)) {
        User user = userService.findOneById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.User.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/user/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<User> showUsersDetails(){
    LOGGER.debug("ApiController - showUsersDetails");
    return userService.findAllWithAll();
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/user/add", method = RequestMethod.POST, produces = "application/json")
public ResponseEntity<User> sendUser(User user){
    LOGGER.debug("ApiController - sendUser");
    if (!userService.exists(user.getEmail())) {
        userService.save(user);
        LOGGER.debug("ApiController - sendUser - User créé");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    } else {
        LOGGER.debug("ApiController - sendUser - User déjà existant");
        return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
    }
}


@JsonView(JsonViews.Basique.class)
@RequestMapping(value = "/api/user/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
public ResponseEntity<User> deleteUser(Long id){
    LOGGER.debug("ApiController - deleteUser");
    User user = null;
    if (userService.exists(id)) {
        user = userService.findOneById(id);
        userService.deleteOneById(id);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
}


@JsonView(JsonViews.Basique.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/user", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<User> showUsers(){
    LOGGER.debug("ApiController - showUsers");
    return userService.findAll();
}


@JsonView(JsonViews.User.class)
@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/user/{id}/details", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public ResponseEntity<User> showUserWithAll(Long id){
    LOGGER.debug("ApiController - showUserWithAll");
    User user = null;
    if (userService.exists(id)) {
        user = userService.findOneByIdWithAll(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
}


}