package hei2017.service.Impl;
 import hei2017.dao;
import hei2017.entity;
import hei2017.enumeration.StoryStatus;
import hei2017.service.MainService;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import hei2017.Interface.ProjectDAO;
import hei2017.Interface.SprintDAO;
import hei2017.Interface.UserDAO;
import hei2017.Interface.StoryDAO;
import hei2017.Interface.TaskDAO;
import hei2017.DTO.Sprint;
import hei2017.DTO.Story;
import hei2017.DTO.Task;
@Named
@Transactional
public class MainServiceImpl implements MainService{

@Inject
 private ProjectDAO projectDAO;

@Inject
 private SprintDAO sprintDAO;

@Inject
 private UserDAO userDAO;

@Inject
 private StoryDAO storyDAO;

@Inject
 private TaskDAO taskDAO;

 private String[] adj;

 private String[] noms;

 private String[] desc;


@Override
public void wipe(){
    List<User> users = userDAO.findAll();
    for (User user : users) {
        // user.setUserTasks(null);
        // userDAO.save(user);
        userDAO.delete(user);
    }
    List<Task> tasks = taskDAO.findAll();
    for (Task task : tasks) {
        // task.setTaskStory(null);
        // task.setTaskUsers(null);
        // taskDAO.save(task);
        taskDAO.delete(task);
    }
    List<Story> stories = storyDAO.findAll();
    for (Story story : stories) {
        // story.setStoryTasks(null);
        // storyDAO.save(story);
        storyDAO.delete(story);
    }
    List<Project> projects = projectDAO.findAll();
    for (Project project : projects) {
        // project.setProjectSprints(null);
        // projectDAO.save(project);
        projectDAO.delete(project);
    }
    List<Sprint> sprints = sprintDAO.findAll();
    for (Sprint sprint : sprints) {
        // sprint.setSprintProject(null);
        // sprint.setSprintStories(null);
        // sprintDAO.save(sprint);
        sprintDAO.delete(sprint);
    }
}


public String giveDescription(){
    return desc[ThreadLocalRandom.current().nextInt(0, desc.length)];
}


@Override
public void populate(){
    int nbProject = ThreadLocalRandom.current().nextInt(1, 5);
    for (int h = 0; h < nbProject; h++) {
        Project projet = new Project();
        projet.setNom("Project " + giveName());
        projet.setDescription(giveDescription());
        projet.setDateCreation(new Timestamp(System.currentTimeMillis()));
        projet = projectDAO.save(projet);
        int nbSprint = ThreadLocalRandom.current().nextInt(2, 6);
        for (int i = 0; i < nbSprint; i++) {
            Sprint sprint = new Sprint();
            sprint.setNom("Sprint " + giveName());
            sprint.setDescription(giveDescription());
            sprint.setDateCreation(new Timestamp(System.currentTimeMillis()));
            sprint.setDateDebut(new Timestamp(System.currentTimeMillis()));
            Timestamp ts = new Timestamp(System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(60000, 3600000));
            sprint.setDateFin(ts);
            sprint.setSprintProject(projet);
            sprint = sprintDAO.save(sprint);
            int nbStory = ThreadLocalRandom.current().nextInt(2, 10);
            for (int j = 0; j < nbStory; j++) {
                Story story = new Story();
                story.setNom("Story " + giveName());
                story.setDescription(giveDescription());
                story.setDateCreation(new Timestamp(System.currentTimeMillis()));
                story.setStatus(StoryStatus.TODO);
                if (j % 2 == 0)
                    story.setStatus(StoryStatus.DOING);
                if (j % 5 == 0)
                    story.setStatus(StoryStatus.DONE);
                story.setPoints(ThreadLocalRandom.current().nextInt(1, 99));
                story.setStorySprint(sprint);
                story = storyDAO.save(story);
                int nbTask = ThreadLocalRandom.current().nextInt(3, 10);
                for (int k = 0; k < nbTask; k++) {
                    Task task = new Task();
                    task.setNom("Task " + giveName());
                    task.setDescription(giveDescription());
                    task.setTaskStory(story);
                    task.setStatus(StoryStatus.TODO);
                    if (k % 2 == 0)
                        task.setStatus(StoryStatus.DOING);
                    if (k % 5 == 0)
                        task.setStatus(StoryStatus.DONE);
                    taskDAO.save(task);
                }
            }
        }
    }
}


public String giveName(){
    return adj[ThreadLocalRandom.current().nextInt(0, adj.length)] + " " + noms[ThreadLocalRandom.current().nextInt(0, noms.length)];
}


}