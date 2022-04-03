package es.us.isa.ideas.app.controllers;
 import es.us.isa.ideas.app.configuration.StudioConfiguration;
import es.us.isa.ideas.app.entities.Tag;
import es.us.isa.ideas.app.entities.Workspace;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.services.TagService;
import es.us.isa.ideas.app.services.WorkspaceService;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.us.isa.ideas.app.Interface.StudioConfiguration;
@Controller
@RequestMapping(value = "app/wsm")
public class WSMController {

@Autowired
 private  WorkspaceService workspaceService;

@Autowired
 private  TagService tagService;

@Autowired
 private  StudioConfiguration studioConfiguration;


@RequestMapping(value = "/workspaces/{ws_name}")
@ResponseBody
public ModelAndView getWokspace(String ws_name,HttpServletResponse response){
    ModelAndView result;
    result = new ModelAndView("app/wsm");
    Workspace workspace = workspaceService.findByNameOfPrincipal(ws_name);
    result.addObject("workspace", workspace);
    return result;
}


@RequestMapping(value = "/workspaces/{wsName}/edit", method = RequestMethod.GET)
public ModelAndView editWSForm(String name,Model model){
    ModelAndView result;
    Workspace workspace = workspaceService.findByNameOfPrincipal(name);
    result = new ModelAndView("wsm/edit", "wsForm", workspace);
    return result;
}


@RequestMapping(value = "/workspaces", method = RequestMethod.POST)
public String saveWS(Workspace ws,BindingResult result,RedirectAttributes redirectAttributes){
    Workspace workspace = workspaceService.findById(ws.getId());
    workspace.setDescription(ws.getDescription());
    workspace.setName(ws.getName());
    workspaceService.save(workspace);
    // redirectAttributes.addFlashAttribute("css", "success");
    // redirectAttributes.addFlashAttribute("msg", " updated successfully!");
    return "redirect:/app/wsm/";
}


@RequestMapping(value = "", method = RequestMethod.GET)
public ModelAndView index(Model model,String filter){
    ModelAndView result;
    if (!studioConfiguration.getAdvancedMode()) {
        return new ModelAndView("redirect:/app/editor");
    }
    result = new ModelAndView("app/wsm");
    Collection<Workspace> taggedWS = null;
    if (filter != null && filter.length() > 0) {
        String[] tags = filter.split(" ");
        taggedWS = workspaceService.findByTags(tags);
    }
    List<Workspace> workspaces = (List<Workspace>) workspaceService.findByPrincipal();
    List<Workspace> demos = (List<Workspace>) workspaceService.findByDemoPrincipal();
    List<Workspace> otherdemos = (List<Workspace>) workspaceService.findByOtherDemos();
    Collection<Tag> tagsCollection = tagService.findTagsInUse();
    if (taggedWS != null && workspaces != null) {
        workspaces.retainAll(taggedWS);
    }
    if (taggedWS != null && demos != null) {
        demos.retainAll(taggedWS);
    }
    if (taggedWS != null && otherdemos != null) {
        otherdemos.retainAll(taggedWS);
    }
    result.addObject("workspaces", workspaces);
    result.addObject("demos", demos);
    result.addObject("otherdemos", otherdemos);
    result.addObject("tags", tagsCollection);
    return result;
}


@RequestMapping(value = "/tags/{tag_name}/workspaces")
public ModelAndView getTaggedWorkspaces(String tagName){
    ModelAndView result;
    result = new ModelAndView("app/wsm");
    Collection<Workspace> workspaces = workspaceService.findByTagName(tagName);
    result.addObject("workspaces", workspaces);
    return result;
}


@RequestMapping(value = "/workspaces/{wsName}/delete", method = RequestMethod.POST)
public String deleteWS(String name,RedirectAttributes redirectAttributes){
    workspaceService.delete(name, LoginService.getPrincipal().getUsername());
    // redirectAttributes.addFlashAttribute("css", "success");
    // redirectAttributes.addFlashAttribute("msg", "User is deleted!");
    return "redirect:/app/wsm/";
}


}