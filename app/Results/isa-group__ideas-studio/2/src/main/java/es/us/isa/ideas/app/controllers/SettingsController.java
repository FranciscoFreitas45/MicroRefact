package es.us.isa.ideas.app.controllers;
 import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountController;
import es.us.isa.ideas.app.security.UserAccountService;
import es.us.isa.ideas.app.services.ConfirmationService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.app.services.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.social.SocialNetwork;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.Interface.LoginService;
import es.us.isa.ideas.app.Interface.UserAccountController;
import es.us.isa.ideas.app.DTO.LoginService;
import es.us.isa.ideas.app.DTO.UserAccount;
@Controller
@RequestMapping("/settings")
public class SettingsController extends AbstractController{

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

@Autowired
 private ResearcherController researcherController;

@Autowired
 private UserAccountController userAccountController;


public ModelAndView createModelAndView(){
    ModelAndView result = new ModelAndView("settings/user");
    result.addObject("services", Arrays.asList(SocialNetwork.values()));
    result.addObject("url", "settings/user");
    // result.addObject("savePersonalInformationUrl",
    // "researcher/savePersonalInformation");
    // result.addObject("deletePersonalInformationUrl",
    // "researcher/deletePersonalInformation");
    // result.addObject("saveUserAccountUrl", "researcher/saveUserAccount");
    return result;
}


@RequestMapping(value = "/user", method = RequestMethod.GET)
public ModelAndView edit(){
    ModelAndView mv;
    Researcher originalResearcher = researcherController.edit(null);
    if (null != originalResearcher) {
        Researcher clonedResearcher = createClone(originalResearcher);
        // Do not send password
        clonedResearcher.getUserAccount().setPassword("");
        mv = createModelAndView(clonedResearcher, "researcher.commit.error");
        mv.setViewName("settings/user");
        mv.addObject("url", "settings/user");
    } else {
        mv = new ModelAndView("redirect:/app/editor");
    }
    return mv;
}


public Researcher createClone(Researcher original){
    Researcher cloned = null;
    try {
        cloned = original.clone();
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
    return cloned;
}


@RequestMapping(value = "/user", method = RequestMethod.POST)
public ModelAndView save(Researcher researcher,BindingResult binding,String oldPass,String repeatPass){
    String mypass;
    boolean diffPasswords = false;
    boolean changingPass = false;
    boolean newAccount = researcher.getUserAccount() == null;
    ModelAndView mv = null;
    if (!newAccount) {
        mypass = researcher.getUserAccount().getPassword();
        changingPass = mypass.length() > 1 && repeatPass.length() > 1;
        diffPasswords = (repeatPass.length() > 1 || mypass.length() > 1) && (!mypass.equals(repeatPass));
    }
    if (null == repeatPass) {
        repeatPass = "";
    }
    if (null == oldPass) {
        oldPass = "";
    }
    boolean isSocialLogin = null != researcher.getUserAccount() && null == researcher.getUserAccount().getPassword();
    boolean notEmptyPassValidation = binding.hasFieldErrors("userAccount.password") && binding.getErrorCount() == binding.getFieldErrorCount("userAccount.password") && oldPass.length() <= 0;
    boolean isPasswordUpdate = !oldPass.equals("") || !repeatPass.equals("");
    if ((!isSocialLogin && binding.hasErrors() && !notEmptyPassValidation) || diffPasswords || isPasswordUpdate) {
        mv = createModelAndView(researcher, "researcher.commit.error");
        mv.addObject("url", "settings/user");
        // TODO
        // if (binding.hasFieldErrors(""))
        // mv.addObject("successInfo", false);
        // mv.addObject("successAccount", false);
        mv.addObject("success", false);
        if (diffPasswords) {
            mv.addObject("repeatPasswordInconsistent", true);
        } else {
            mv.addObject("repeatPasswordInconsistent", false);
        }
    } else {
        if (!newAccount) {
            UserAccount principalUser = userAccountService.findByUsername(LoginService.getPrincipal().getUsername());
            Researcher principalResearcher = researcherService.findByUserAccount(principalUser);
            researcher = updateResearcher(principalResearcher.getId(), researcher, changingPass);
            // Save UserAccount
            try {
                userAccountController.save(researcher.getUserAccount(), oldPass, repeatPass);
            } catch (Throwable oops) {
                System.out.println("SC opss: " + oops.getMessage());
                mv = edit();
                mv.addObject("badPassword", true);
                mv.addObject("success", false);
                return mv;
            }
        }
        // Save Researcher
        if (newAccount) {
            try {
                researcher.setUserAccount(new UserAccount());
                researcherController.saveNew(researcher);
                mv = edit();
                mv.addObject("success", true);
            } catch (Throwable oops) {
                mv = edit();
                mv.addObject("success", false);
                mv.addObject("repeatedEmail", true);
            }
        } else {
            researcherController.save(researcher);
            mv = edit();
            mv.addObject("success", true);
        }
    }
    return mv;
}


@RequestMapping(value = "/detail", method = RequestMethod.POST)
public ModelAndView savedetail(HttpServletRequest request,Researcher researcher,BindingResult binding){
    ModelAndView mv = null;
    String userId = request.getParameter("userId");
    boolean changingPass = researcher.getUserAccount().getPassword().length() > 1;
    boolean notEmptyPassValidation = binding.hasFieldErrors("userAccount.password") && binding.getErrorCount() == binding.getFieldErrorCount("userAccount.password") && !changingPass;
    if ((binding.hasErrors() && !notEmptyPassValidation)) {
        mv = createModelAndView(researcher, "researcher.commit.error");
        mv.setViewName("settings/detail");
        mv.addObject("url", "settings/detail");
        mv.addObject("success", false);
    } else {
        String settedPass = researcher.getUserAccount().getPassword();
        UserAccount user = userAccountService.findById(Integer.valueOf(userId));
        Researcher res = researcherService.findByUserAccount(user);
        researcher = updateResearcher(res.getId(), researcher, changingPass);
        if (changingPass) {
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String newpasshash = encoder.encodePassword(settedPass, null);
            researcher.getUserAccount().setPassword(newpasshash);
        }
        // Save Researcher
        researcherController.save(researcher);
        mv = detail(request);
        mv.addObject("success", true);
    }
    return mv;
}


public Researcher updateResearcher(int researcherId,Researcher researcher,boolean changingPass){
    Researcher currentResearcher = researcherService.findById(researcherId);
    currentResearcher.setAddress(researcher.getAddress());
    currentResearcher.setEmail(researcher.getEmail());
    currentResearcher.setName(researcher.getName());
    currentResearcher.setPhone(researcher.getPhone());
    currentResearcher.getUserAccount().setUsername(currentResearcher.getUserAccount().getUsername());
    // Do not change password here!
    return currentResearcher;
}


@RequestMapping(value = "/detail", method = RequestMethod.GET)
public ModelAndView detail(HttpServletRequest request){
    String userId = request.getParameter("userId");
    Researcher clonedResearcher = createClone(researcherController.edit(userId));
    // Do not send
    clonedResearcher.getUserAccount().setPassword("");
    // password
    ModelAndView mv = new ModelAndView("settings/detail");
    mv.addObject("researcher", clonedResearcher);
    mv.addObject("url", "settings/detail");
    return mv;
}


@RequestMapping(value = "/admin")
public ModelAndView list(){
    Collection<Researcher> researchers = researcherController.list();
    ModelAndView result = new ModelAndView("settings/admin");
    result.addObject("researchers", researchers);
    return result;
}


}