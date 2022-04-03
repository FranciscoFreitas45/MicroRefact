package es.us.isa.ideas.app.controllers;
 import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import es.us.isa.ideas.app.dto.UserDto;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountService;
import es.us.isa.ideas.app.services.ConfirmationService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.app.services.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.Interface.LoginService;
@Controller
@RequestMapping("/researcher")
public class ResearcherController extends AbstractController{

@Autowired
 private ResearcherService researcherService;

@Autowired
 private ConfirmationService registrationConfirmationService;

@Autowired
 private UserAccountService userAccountService;

@Autowired
 private SocialNetworkConfigurationService socialNetworkConfigurationService;

@Autowired
 private LoginService loginService;


@RequestMapping(value = "/create", method = RequestMethod.POST)
public void saveNew(Researcher researcher){
    try {
        beginTransaction();
        researcherService.saveNew(researcher);
        commitTransaction();
    } catch (Throwable oops) {
        rollbackTransaction();
        System.out.println("new researcher oops");
        throw oops;
    }
}


@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
@ResponseBody
public Researcher edit(String id){
    UserAccount user;
    boolean existingUser = SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserAccount;
    if (id == null)
        if (existingUser)
            user = LoginService.getPrincipal();
        else
            user = new UserAccount();
    else
        user = userAccountService.findById(Integer.valueOf(id));
    Researcher researcher;
    if (existingUser)
        researcher = researcherService.findByUserAccount(user);
    else {
        researcher = new Researcher();
        researcher.setUserAccount(user);
    }
    return researcher;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST)
@ResponseBody
public void save(Researcher researcher){
    try {
        beginTransaction();
        researcherService.save(researcher);
        commitTransaction();
    } catch (Throwable oops) {
        rollbackTransaction();
        System.out.println("researcher oops");
        throw oops;
    }
}


@RequestMapping("/list")
@ResponseBody
public Collection<Researcher> list(){
    Collection<Researcher> researchers = researcherService.findAll();
    return researchers;
}


@RequestMapping(value = "/principaluser", method = RequestMethod.GET)
@ResponseBody
public UserDto principaluser(){
    // UserAccount principal = LoginService.getPrincipal();
    Researcher researcher = researcherService.findByPrincipal();
    return new UserDto(researcher);
}


@RequestMapping(value = "/delete")
public ModelAndView delete(int researcherId){
    ModelAndView result = null;
    Researcher researcher = researcherService.findById(researcherId);
    if (researcher != null) {
        try {
            beginTransaction();
            researcherService.delete(researcher);
            commitTransaction();
            // TODO: redirect depending on the role.
            result = new ModelAndView("redirect:list");
        } catch (Throwable oops) {
            rollbackTransaction();
        // result = createModelAndView(researcher,
        // "researcher.commit.error");
        }
    }
    return result;
}


}