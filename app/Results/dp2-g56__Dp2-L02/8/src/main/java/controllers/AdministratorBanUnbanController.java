package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AdminService;
import domain.Actor;
@Controller
@RequestMapping("/suspicious/administrator")
public class AdministratorBanUnbanController extends AbstractController{

@Autowired
 private  AdminService adminService;

@Autowired
 private  ActorService actorService;

public AdministratorBanUnbanController() {
    super();
}
@RequestMapping(value = "/unban", method = RequestMethod.GET)
public ModelAndView unban(int actorId){
    ModelAndView result;
    Actor actor;
    actor = this.actorService.findOne(actorId);
    this.adminService.unBanSuspiciousActor(actor);
    result = new ModelAndView("redirect:list.do");
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView suspicious(){
    ModelAndView result;
    List<Actor> actors = new ArrayList<Actor>();
    actors = this.actorService.findAllExceptAdmin();
    result = new ModelAndView("suspicious/administrator/list");
    result.addObject("actors", actors);
    return result;
}


@RequestMapping(value = "/ban", method = RequestMethod.GET)
public ModelAndView ban(int actorId){
    ModelAndView result;
    Actor actor;
    actor = this.actorService.findOne(actorId);
    this.adminService.banSuspiciousActor(actor);
    result = new ModelAndView("redirect:list.do");
    return result;
}


}