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
import hei2017.Interface.StoryService;
import hei2017.Interface.TaskService;
import hei2017.Interface.UserService;
@EnableWebMvc
@RestController
public class ApiDebugController {

 private  Logger LOGGER;

@Inject
 private MainService mainService;

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


@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/debug", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public String debug(){
    mainService.populate();
    return "DB peuplée";
}


@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/test", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public List<Project> test(){
    return projectService.findAll();
}


@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/api/debug/wipe", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
public String debugWipe(){
    mainService.wipe();
    return "Reset BDD";
}


}