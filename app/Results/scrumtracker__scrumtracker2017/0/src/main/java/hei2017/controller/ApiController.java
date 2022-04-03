package hei2017.controller;
 import hei2017.entity;
import hei2017.enumeration.StoryStatus;
import hei2017.enumeration.UniteTemps;
import hei2017.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import hei2017.Interface.ProjectService;
import hei2017.Interface.SprintService;
import hei2017.Interface.UserService;
@EnableWebMvc
@RestController
public class ApiController {

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


@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
@RequestMapping(value = "/api/*", produces = "application/json")
public String showErreur(HttpServletRequest request){
    LOGGER.error("RestAPIController - Requête vers l'api incorrecte. \n" + request.toString());
    return "Demande incorrecte";
}


}