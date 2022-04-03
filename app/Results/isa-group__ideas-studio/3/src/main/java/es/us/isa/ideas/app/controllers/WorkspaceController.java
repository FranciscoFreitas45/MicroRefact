package es.us.isa.ideas.app.controllers;
 import es.us.isa.ideas.app.controllers.FileController.initRepoLab;
import es.us.isa.ideas.app.entities.Tag;
import es.us.isa.ideas.app.entities.Workspace;
import es.us.isa.ideas.app.repositories.ResearcherRepository;
import es.us.isa.ideas.app.repositories.WorkspaceRepository;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.app.services.TagService;
import es.us.isa.ideas.app.services.WorkspaceService;
import es.us.isa.ideas.repo.IdeasRepo;
import es.us.isa.ideas.repo.exception.AuthenticationException;
import es.us.isa.ideas.repo.exception.BadUriException;
import es.us.isa.ideas.repo.impl.fs.FSFacade;
import es.us.isa.ideas.repo.impl.fs.FSWorkspace;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import es.us.isa.ideas.app.Interface.ResearcherService;
@Controller
@RequestMapping("/workspaces")
public class WorkspaceController extends AbstractController{

@Autowired
 private TagService tagService;

@Autowired
 private WorkspaceService workspaceService;

@Autowired
 private ResearcherService researcherService;

 private  Logger logger;

 private  String DEMO_MASTER;

 private  String SAMPLE_WORKSPACE;

 protected  WorkspaceRepository workspaceRepository;

 protected  ResearcherRepository researcherRepository;


@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
@ResponseStatus(HttpStatus.OK)
public Collection<Workspace> getWorkspacesJSON(){
    initRepoLab();
    Collection<Workspace> wsc = workspaceService.findAll();
    return wsc;
}


@RequestMapping(value = "/{workspaceName}/tags", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
@ResponseStatus(HttpStatus.OK)
public Collection<Tag> getTaggedWorkspacesJSON(String workspaceName){
    Collection<Tag> list = tagService.findByWorkspaceName(workspaceName, LoginService.getPrincipal().getUsername());
    return list;
}


@RequestMapping(value = "/{workspaceName}/load", method = RequestMethod.GET)
@ResponseBody
public String loadWorkspace(String workspaceName){
    logger.log(Level.INFO, "Reading workspace: {0}", workspaceName);
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    String wsJson = "";
    try {
        String username = LoginService.getPrincipal().getUsername();
        if (workspaceName.equalsIgnoreCase(SAMPLE_WORKSPACE) && !FSFacade.getWorkspaces(username).contains(workspaceName) || "[]".equals(FSFacade.getWorkspaces(username))) {
            FSFacade.createWorkspace(SAMPLE_WORKSPACE, username);
            FSFacade.saveSelectedWorkspace(SAMPLE_WORKSPACE, username);
        }
        wsJson = FSFacade.getWorkspaceTree(workspaceName, LoginService.getPrincipal().getUsername());
        if (LoginService.getPrincipal().getUsername().startsWith("demo")) {
            workspaceService.updateLaunches(workspaceName, "DemoMaster");
        }
    } catch (Exception e) {
        logger.log(Level.WARNING, "Workspace {0} does not exist.", workspaceName);
        return wsJson;
    }
    return wsJson;
}


@RequestMapping(value = "/{workspaceName}", method = RequestMethod.PUT, consumes = "text/plain")
@ResponseBody
public void updateWorkspaceJSON(String workspaceName,String workspaceJSON){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
        workspaceJSON = new String(workspaceJSON.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    boolean success = Boolean.TRUE;
    try {
        FSFacade.saveSelectedWorkspace(workspaceName, LoginService.getPrincipal().getUsername());
    } catch (Exception e) {
        success = Boolean.FALSE;
    }
    if (success) {
        workspaceService.saveOrUpdate(workspaceJSON);
    }
}


@RequestMapping(value = "/{workspaceName}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteWorkspaceJSON(String workspaceName){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    String username = LoginService.getPrincipal().getUsername();
    boolean success = Boolean.TRUE;
    try {
        FSFacade.deleteWorkspace(workspaceName, username);
    } catch (AuthenticationException | BadUriException e) {
        success = Boolean.FALSE;
    }
    if (success) {
        workspaceService.delete(workspaceName, username);
    }
}


@RequestMapping(value = "/{workspaceName}/deleteDemo", method = RequestMethod.GET)
@ResponseBody
public String deleteSelectedWorkspaceDemo(String workspaceName){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    String res = "";
    logger.log(Level.INFO, "URI: {0}", workspaceName);
    String username = LoginService.getPrincipal().getUsername();
    FSWorkspace demoWS = new FSWorkspace(workspaceName, DEMO_MASTER);
    boolean demoExists = true;
    try {
        demoExists = FSFacade.getWorkspaces(DEMO_MASTER).contains("\"" + workspaceName + "\"");
    } catch (Exception e) {
        logger.log(Level.SEVERE, res, e);
        demoExists = false;
    }
    if (demoExists) {
        boolean success = Boolean.TRUE;
        try {
            IdeasRepo.get().getRepo().delete(demoWS);
        } catch (AuthenticationException e) {
            success = Boolean.FALSE;
            res = "[ERROR] Error deleting WS for DemoMaster from " + username;
            logger.log(Level.SEVERE, res, e);
        }
        if (success) {
            workspaceService.delete(workspaceName, DEMO_MASTER);
        }
    } else {
        res = "[INFO] The ws doesn't exist.";
        logger.log(Level.INFO, res);
    }
    return res;
}


@RequestMapping(value = "/{workspaceName}/toDemo", method = RequestMethod.GET)
@ResponseBody
public String cloneSelectedWorkspaceToDemo(String workspaceName){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    String res = "";
    System.out.println("URI: " + workspaceName);
    String username = LoginService.getPrincipal().getUsername();
    FSWorkspace userWS = new FSWorkspace(workspaceName, username);
    FSWorkspace demoWS = new FSWorkspace(workspaceName, DEMO_MASTER);
    Workspace ws = null;
    Workspace demo = null;
    boolean demoExists = Boolean.TRUE;
    try {
        demoExists = FSFacade.getWorkspaces(DEMO_MASTER).contains("\"" + workspaceName + "\"");
    } catch (Exception e) {
        logger.log(Level.SEVERE, null, e);
        demoExists = Boolean.FALSE;
    }
    if (!demoExists) {
        boolean success = Boolean.TRUE;
        try {
            IdeasRepo.get().getRepo().move(userWS, demoWS, true);
        } catch (AuthenticationException e) {
            success = Boolean.FALSE;
            res = "[ERROR] Error creating WS for DemoMaster from " + username;
            logger.log(Level.SEVERE, res, e);
        }
        if (success) {
            ws = workspaceService.findByNameOfPrincipal(workspaceName);
            demo = new Workspace();
            if (ws != null) {
                String demomaster = DEMO_MASTER;
                workspaceService.createWorkspace(workspaceName, demomaster, String.valueOf(ws.getId()));
            }
        }
    } else {
        res = "Cannot convert workspace, because \"" + workspaceName + "\" already exists.";
        logger.log(Level.INFO, res);
    }
    return res;
}


@RequestMapping(value = "/{workspaceName}", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
@ResponseStatus(HttpStatus.OK)
public Workspace getWorkspaceJSON(String workspaceName){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    Workspace ws = workspaceService.findByNameOfPrincipal(workspaceName);
    return ws;
}


@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "text/plain", "application/json" })
@ResponseBody
public void saveWorkspaceJSON(String workspaceName,String workspaceJSON){
    initRepoLab();
    try {
        workspaceName = new String(workspaceName.getBytes("iso-8859-15"), "UTF-8");
        workspaceJSON = new String(workspaceJSON.getBytes("iso-8859-15"), "UTF-8");
    } catch (Exception ex) {
        logger.log(Level.INFO, "Unsopported encoding", ex);
    }
    String username = LoginService.getPrincipal().getUsername();
    System.out.println("Persisting selected workspace:  " + workspaceName + ", username: " + username);
    boolean success = Boolean.TRUE;
    try {
        FSFacade.saveSelectedWorkspace(workspaceName, username);
    } catch (Exception e) {
        success = Boolean.FALSE;
    }
    if (success) {
        workspaceService.saveOrUpdate(workspaceJSON);
    }
}


}