import org.sdrc.childinfo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class pageController {

@Autowired
 private  DocumentService documentService;


@RequestMapping(value = "/resource", method = RequestMethod.GET)
public ModelAndView getAllResources(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("resourcesList", documentService.searchAllResources(3));
    modelAndView.setViewName("resource");
    return modelAndView;
}


@RequestMapping(value = "/sops", method = RequestMethod.GET)
public ModelAndView getAllSops(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("resourcesList", documentService.searchAllSop(0));
    modelAndView.setViewName("sops");
    return modelAndView;
}


@RequestMapping(value = "/tools", method = RequestMethod.GET)
public ModelAndView getAllAssessmentTools(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("assessmentToolsList", documentService.searchAllAssessmentTools(0));
    modelAndView.addObject("userGuidesList", documentService.searchAllUserGuides(0));
    modelAndView.setViewName("tools");
    return modelAndView;
}


}