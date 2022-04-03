package hei2017.controller;
 import hei2017.entity.Project;
import hei2017.enumeration.StoryStatus;
import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import hei2017.service.StoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hei2017.Interface.SprintService;
import hei2017.Interface.StoryService;
@Controller
public class ProjectController {

@Inject
 private ProjectService projectService;

@Inject
 private SprintService sprintService;

@Inject
 private StoryService storyService;


@RequestMapping("/project/{idProject}")
public String goProjects(Model model,HttpServletRequest request,HttpServletResponse response,Long idProject){
    model.addAttribute("isProjectPage", true);
    model.addAttribute("projects", projectService.findAll());
    model.addAttribute("sprints", sprintService.findAll());
    model.addAttribute("stories", storyService.findAll());
    // stories non affectées à un sprint
    model.addAttribute("storiesAlone", storyService.findAllWithoutSprint());
    // Sprints du projet
    model.addAttribute("sprintsOfProjectWithStories", sprintService.findByProjectSprintIdWithStories(idProject));
    model.addAttribute("status", StoryStatus.values());
    Project project = projectService.findOneById(idProject);
    if (null == project) {
        model.addAttribute("errorMessage", "Project #" + idProject + " does not exist.");
        model.addAttribute("isErrorPage", true);
        return "erreur";
    }
    model.addAttribute("project", project);
    model.addAttribute("currentProject", project);
    model.addAttribute("projectSprints", project.getProjectSprints());
    return "project";
}


}