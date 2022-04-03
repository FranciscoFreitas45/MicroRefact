package com.metservice.kanban.web;
 import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.metservice.kanban.KanbanService;
import com.metservice.kanban.model.KanbanProject;
@Controller
@RequestMapping("{projectName}/admin")
public class KanbanAdminController {

@Autowired
 private  KanbanService kanbanService;


@ModelAttribute("project")
public KanbanProject populateProject(String projectName){
    return kanbanService.getKanbanProject(projectName);
}


@RequestMapping("")
public ModelAndView admin(KanbanProject project,String projectName){
    // Build the model
    Map<String, Object> model = buildModel(projectName);
    return new ModelAndView("/admin.jsp", model);
}


@ModelAttribute("service")
public KanbanService populateService(){
    return kanbanService;
}


public void setKanbanService(KanbanService kanbanService){
    this.kanbanService = kanbanService;
}


public Map<String,Object> buildModel(String projectName){
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("projectName", projectName);
    // TODO remove; used as an example to show how to add settings, etc.
    model.put("username", "hullo");
    return model;
}


}