package es.us.isa.ideas.app.controllers;
 import es.us.isa.ideas.app.controllers.FileController.initRepoLab;
import es.us.isa.ideas.app.entities.Workspace;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import es.us.isa.ideas.app.security.Credentials;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountService;
import es.us.isa.ideas.app.services.ResearcherService;
import es.us.isa.ideas.app.services.WorkspaceService;
import es.us.isa.ideas.repo.IdeasRepo;
import es.us.isa.ideas.repo.exception.AuthenticationException;
import es.us.isa.ideas.repo.impl.fs.FSFacade;
import es.us.isa.ideas.repo.impl.fs.FSWorkspace;
import es.us.isa.ideas.utilities.AppResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.ResearcherService;
import es.us.isa.ideas.app.Interface.WorkspaceService;
@Controller
@RequestMapping("/demo")
public class DemoController extends AbstractController{

@Autowired
 private UserAccountService userAccountService;

@Autowired
 private ResearcherService researcherService;

@Autowired
 private WorkspaceService workspaceService;

 private  Logger logger;

 private  String DEMO_MASTER;

public DemoController() {
}
@RequestMapping("/*")
public ModelAndView generateDemoUser(HttpServletRequest request){
    Collection<UserAccount> allUsers;
    Integer actual = 0;
    Integer number = 0;
    System.out.println("[INFO] Demo controller start.");
    System.out.println("[INFO] Initiating demo user generation.");
    System.out.println("[INFO] Browsing users.");
    allUsers = userAccountService.findAll();
    for (UserAccount usr : allUsers) {
        if (usr != null) {
            if (usr.getUsername() != null) {
                if (usr.getUsername().startsWith("demo")) {
                    logger.log(Level.INFO, "[INFO] username: {0}", usr.getUsername());
                    String sNumber = usr.getUsername().split("demo")[1];
                    logger.log(Level.INFO, "[INFO] userNumber: {0}", sNumber);
                    try {
                        number = new Integer(sNumber);
                    } catch (NumberFormatException e) {
                        number = 0;
                    }
                    logger.log(Level.INFO, "[INFO] Number: {0}", number);
                    if (number > actual) {
                        actual = number;
                        logger.log(Level.INFO, "[INFO] actual: {0}", actual);
                    }
                }
            } else {
                logger.log(Level.INFO, "[INFO] User name is null, ID:{0}", usr.getId());
            }
        } else {
            logger.log(Level.INFO, "[INFO] NULL found:{0}", usr);
        }
    }
    String pass = "demo" + (actual + 1);
    String user = "demo" + (actual + 1);
    logger.log(Level.INFO, "[INFO] PASSWORD: {0}", pass);
    userAccountService.create(null, user, pass, "");
    Credentials credentials = new Credentials();
    credentials.setJ_username(user);
    credentials.setPassword(pass);
    logger.log(Level.INFO, "[INFO] END");
    ModelAndView result = new ModelAndView("security/login");
    result.addObject("credentials", credentials);
    result.addObject("showError", false);
    String originalUrl = "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    result.addObject("originalRequestUrl", originalUrl);
    return result;
}


@RequestMapping(value = "/{demoWorkspaceName}", method = RequestMethod.DELETE)
@ResponseBody
public boolean deleteDemoWorkspace(String demoWorkspaceName){
    initRepoLab();
    boolean success = true;
    // Only delete demoWS if current user is the original creator
    boolean isCurrentUserCreator = false;
    String username = LoginService.getPrincipal().getUsername();
    Workspace demoWS = workspaceService.findByNameAndOwner(demoWorkspaceName, DEMO_MASTER);
    if (demoWS.getOrigin() != null) {
        String demoCreator = demoWS.getOrigin().getOwner().getUserAccount().getUsername();
        isCurrentUserCreator = username.equals(demoCreator);
    }
    if (isCurrentUserCreator) {
        try {
            FSFacade.deleteWorkspace(demoWorkspaceName, DEMO_MASTER);
        } catch (Exception e) {
            success = false;
            logger.log(Level.SEVERE, null, e);
        }
    } else {
        success = false;
    }
    if (success) {
        workspaceService.delete(demoWorkspaceName, DEMO_MASTER);
    }
    return success;
}


@RequestMapping(value = "/{demoWorkspaceName}/import", method = RequestMethod.GET)
@ResponseBody
public AppResponse importDemoWorkspace(String demoWorkspaceName){
    AppResponse response = new AppResponse();
    String username = LoginService.getPrincipal().getUsername();
    FSWorkspace demoWS = new FSWorkspace(demoWorkspaceName, DEMO_MASTER);
    if (demoWorkspaceName == null) {
        try {
            demoWorkspaceName = FSFacade.getSelectedWorkspace(username);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    FSWorkspace newWS = new FSWorkspace(demoWorkspaceName, username);
    Workspace demo = workspaceService.findByNameAndOwner(demoWorkspaceName, username);
    if (demo != null) {
        // Al ser una clonación debe aumentar el número de descargas de la demo
        int downloads = demo.getDownloads() + 1;
        demo.setDownloads(downloads);
    }
    boolean demoExists = Boolean.TRUE;
    // TODO: Cambiar comprobacion una vez este refactorizado. No se deberia trabajar a nivel de cadenas, sino de objetos serializables (para devolver JSON)
    try {
        demoExists = FSFacade.getWorkspaces(DEMO_MASTER).contains("\"" + demoWorkspaceName + "\"");
    } catch (Exception e) {
        logger.log(Level.SEVERE, null, e);
        demoExists = Boolean.FALSE;
        ;
    }
    if (demoExists) {
        try {
            IdeasRepo.get().getRepo().delete(newWS);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not remove workspace before importing.", e);
        }
        try {
            IdeasRepo.get().getRepo().move(demoWS, newWS, true);
            response.setStatus(AppResponse.Status.OK);
            response.setMessage("Demo workspace created with name: " + demoWorkspaceName);
            Workspace ws = workspaceService.findByNameAndOwner(demoWorkspaceName, DEMO_MASTER);
            String origin = null;
            if (ws.getOrigin() != null) {
                origin = String.valueOf(ws.getOrigin().getId());
            }
            workspaceService.createWorkspace(demoWorkspaceName, username, origin);
        } catch (AuthenticationException e) {
            logger.log(Level.SEVERE, "### Error creating demo WS for " + username, e);
            response.setStatus(AppResponse.Status.ERROR);
            response.setMessage(e.getMessage());
        }
    } else {
        logger.log(Level.SEVERE, "### Error creating demo WS for {0}", username);
        response.setStatus(AppResponse.Status.OK_PROBLEMS);
        response.setMessage("There is no Demo named " + demoWorkspaceName);
    }
    return response;
}


@RequestMapping(value = "/{demoWorkspaceName}", method = RequestMethod.PUT)
@ResponseBody
public AppResponse updateDemoWorkspace(String demoWorkspaceName){
    AppResponse response = new AppResponse();
    String username = LoginService.getPrincipal().getUsername();
    FSWorkspace demoWS = new FSWorkspace(demoWorkspaceName, DEMO_MASTER);
    Workspace wsDemo = workspaceService.findByNameAndOwner(demoWorkspaceName, DEMO_MASTER);
    Workspace wsOrigin = wsDemo.getOrigin();
    FSWorkspace userWS = new FSWorkspace(wsOrigin.getName(), username);
    boolean demoExists = Boolean.TRUE;
    try {
        demoExists = FSFacade.getWorkspaces(DEMO_MASTER).contains("\"" + demoWorkspaceName + "\"");
    } catch (Exception e) {
        logger.log(Level.SEVERE, null, e);
        demoExists = Boolean.FALSE;
    }
    if (demoExists) {
        boolean success = Boolean.TRUE;
        try {
            IdeasRepo.get().getRepo().move(userWS, demoWS, true);
        } catch (AuthenticationException e) {
            success = Boolean.FALSE;
            logger.log(Level.SEVERE, "### Error updating WS for DemoMaster from " + username, e);
        }
        if (success) {
            workspaceService.updateDemo(demoWorkspaceName, DEMO_MASTER);
        }
    } else {
        logger.log(Level.INFO, "[INFO] The ws already exists.");
        logger.log(Level.INFO, "Demo {0} do not exists!", demoWorkspaceName);
        System.out.println("Demo " + demoWorkspaceName + " do not exists!");
        response.setStatus(AppResponse.Status.OK_PROBLEMS);
        response.setMessage("There is no Demo named " + demoWorkspaceName);
    }
    return response;
}


}