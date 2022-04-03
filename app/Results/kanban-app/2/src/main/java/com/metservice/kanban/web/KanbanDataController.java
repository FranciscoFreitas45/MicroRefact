package com.metservice.kanban.web;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.metservice.kanban.KanbanService;
import com.metservice.kanban.model.KanbanProjectConfiguration;
import com.metservice.kanban.model.WorkItemType;
@Controller
@RequestMapping("{projectName}")
public class KanbanDataController {

@Autowired
 private  KanbanService kanbanService;

/**
 * Default constructor for KanbanDataController. Initialises its own KanbanService.
 */
public KanbanDataController() {
}
@RequestMapping("download")
public void download(String projectName,String workItemTypeName,HttpServletResponse response){
    KanbanProjectConfiguration configuration = kanbanService.getProjectConfiguration(projectName);
    WorkItemType workItemType = configuration.getWorkItemTypes().getByName(workItemTypeName);
    // copies and saves the csv file
    File file = configuration.getDataFile(workItemType);
    response.setContentType("text/csv");
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
    IOUtils.copy(new FileInputStream(file), response.getOutputStream());
}


public void setKanbanService(KanbanService kanbanService){
    this.kanbanService = kanbanService;
}


}