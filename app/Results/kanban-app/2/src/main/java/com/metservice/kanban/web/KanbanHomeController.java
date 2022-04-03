package com.metservice.kanban.web;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import com.metservice.kanban.KanbanService;
@Controller
public class KanbanHomeController {

@Autowired
 private  KanbanService kanbanService;

/**
 * Default constructor for KanbanHomeController. Initialises its own KanbanService.
 */
public KanbanHomeController() {
}
@RequestMapping("/create-home")
public View createKanbanHome(){
    File home = kanbanService.getHome();
    home.mkdirs();
    // initialises the sample project's directory
    File sampleProject = new File(home, "Sample");
    sampleProject.mkdir();
    // initialises the .properties file
    File sampleProjectConfiguration = new File(sampleProject, "kanban.properties");
    IOUtils.copy(KanbanHomeController.class.getResourceAsStream("/sample-kanban.properties"), new FileOutputStream(sampleProjectConfiguration));
    return new RedirectView("/", true);
}


}