package hei2017.controller;
 import hei2017.entity.Sprint;
import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hei2017.Interface.SprintService;
@Controller
public class HomeController {

@Inject
 private ProjectService projectService;

@Inject
 private SprintService sprintService;


@RequestMapping({ "/", "/home" })
public String goIndex(Model model,HttpServletRequest request,HttpServletResponse response){
    model.addAttribute("isHomePage", true);
    model.addAttribute("projects", projectService.findAll());
    model.addAttribute("sprints", sprintService.findAll());
    return "home";
}


}