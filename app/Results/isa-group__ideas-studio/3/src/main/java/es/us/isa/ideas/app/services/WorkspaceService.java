package es.us.isa.ideas.app.services;
 import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.entities.Tag;
import es.us.isa.ideas.app.entities.Workspace;
import es.us.isa.ideas.app.repositories.ResearcherRepository;
import es.us.isa.ideas.app.repositories.TagRepository;
import es.us.isa.ideas.app.repositories.WorkspaceRepository;
import es.us.isa.ideas.app.security.LoginService;
import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import es.us.isa.ideas.app.Interface.UserAccountRepository;
import es.us.isa.ideas.app.DTO.UserAccount;
@Service
@Transactional(readOnly = false)
public class WorkspaceService extends BusinessService<Workspace>{

@Inject
 private  WorkspaceRepository workspaceRepository;

@Inject
 private  TagRepository tagRepository;

@Inject
 private  ResearcherRepository researcherRepository;

@Inject
 private  UserAccountRepository uar;

 private  String DEMO_MASTER;


public void updateVersion(Workspace w){
    int wsVersion = w.getWsVersion() + 1;
    w.setWsVersion(wsVersion);
    workspaceRepository.saveAndFlush(w);
}


public Collection<Workspace> findByTag(Tag tag){
    assert tag != null;
    Collection<Workspace> result;
    String id = String.valueOf(tag.getId());
    result = workspaceRepository.findByTag(id);
    assert result != null;
    return result;
}


@Override
public JpaRepository<Workspace,Integer> getRepository(){
    return workspaceRepository;
}


public void save(Workspace workspace){
    Assert.notNull(workspace);
    workspaceRepository.save(workspace);
}


public List<Workspace> findAll(){
    return workspaceRepository.findAll();
}


public void delete(String workspaceName,String username){
    Assert.notNull(workspaceName);
    Assert.notNull(username);
    deleteReferences(workspaceName, username);
    Workspace ws = workspaceRepository.findByName(workspaceName, username);
    workspaceRepository.delete(ws);
}


public Collection<Workspace> findByTagName(String tagName){
    assert tagName != null;
    Collection<Workspace> result;
    result = workspaceRepository.findByTagName(tagName);
    assert result != null;
    return result;
}


public Collection<Workspace> findByDemoPrincipal(){
    Collection<Workspace> result = new ArrayList();
    Collection<Workspace> own;
    Collection<Workspace> demos;
    own = findByPrincipal();
    demos = findByDemo();
    for (Workspace demo : demos) {
        if (demo.getOrigin() != null && own.contains(demo.getOrigin()))
            result.add(demo);
    }
    return result;
}


public void updateDemo(String demoWorkspaceName,String username){
    Assert.notNull(demoWorkspaceName);
    Assert.notNull(username);
    Workspace demoWS = workspaceRepository.findByName(demoWorkspaceName, DEMO_MASTER);
    Workspace originWS = demoWS.getOrigin();
    demoWS.setLastMod(Calendar.getInstance().getTime());
    demoWS.setDescription(originWS.getDescription());
    updateVersion(originWS);
    demoWS.setWsVersion(originWS.getWsVersion());
    workspaceRepository.saveAndFlush(demoWS);
}


public Collection<Workspace> findByTags(String[] filter){
    assert filter != null;
    Collection<Workspace> result;
    if (filter.length == 0) {
        result = workspaceRepository.findAll();
    } else {
        result = new ArrayList<Workspace>();
        for (String tag : filter) {
            if (result.isEmpty()) {
                result = workspaceRepository.findByTagName(tag);
            } else {
                result.retainAll(workspaceRepository.findByTagName(tag));
            }
        }
    }
    assert result != null;
    return result;
}


public Collection<Workspace> findByDemo(){
    Collection<Workspace> result;
    result = findByOwner(DEMO_MASTER);
    assert result != null;
    return result;
}


public void saveOrUpdate(String workspaceJSON){
    boolean success = true;
    Assert.notNull(workspaceJSON);
    Workspace ws = new Workspace();
    ObjectMapper mapper = new ObjectMapper();
    try {
        ws = mapper.readValue(workspaceJSON, Workspace.class);
        ws.setLastMod(Calendar.getInstance().getTime());
        System.out.println(ws);
    } catch (JsonGenerationException e) {
        success = false;
    } catch (JsonMappingException e) {
        success = false;
    } catch (IOException e) {
        success = false;
    }
    if (success) {
        workspaceRepository.saveAndFlush(ws);
    }
}


public Collection<Workspace> findByOwner(String userAccount){
    assert userAccount != null;
    Collection<Workspace> result;
    result = workspaceRepository.findByOwner(userAccount);
    assert result != null;
    return result;
}


public void updateTime(String workspaceName,String username){
    try {
        Workspace ws = findByNameAndOwner(workspaceName, username);
        ws.setLastMod(Calendar.getInstance().getTime());
        workspaceRepository.saveAndFlush(ws);
    } catch (Exception e) {
        Logger.getLogger(WorkspaceService.class.getName()).log(Level.SEVERE, "Problems while updating time in workspace {0}", workspaceName);
    }
}


public Collection<Workspace> findByPrincipal(){
    Collection<Workspace> result;
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    if (userAccount != null) {
        result = findByOwner(userAccount.getUsername());
        assert result != null;
    } else
        result = Collections.EMPTY_LIST;
    return result;
}


public void createWorkspaceWithTags(String workspaceName,String description,String username,String origin,String tags){
    Assert.notNull(workspaceName);
    Assert.notNull(username);
    if (checkNameAvailability(workspaceName, username)) {
        Workspace ws = new Workspace();
        ws.setDownloads(0);
        ws.setDescription(description);
        ws.setLastMod(Calendar.getInstance().getTime());
        ws.setLaunches(0);
        ws.setWsVersion(1);
        ws.setName(workspaceName);
        UserAccount ua = uar.findByUsername(username);
        Researcher rese = researcherRepository.findByUserAccountId(ua.getId());
        ws.setOwner(rese);
        String[] array = tags.split("\\s+");
        Collection<Tag> wsTags = new ArrayList<>();
        for (String a : array) {
            if (a.length() > 1) {
                if (tagRepository.findByName(a) != null)
                    wsTags.add(tagRepository.findByName(a));
                else {
                    Tag newTag = new Tag();
                    newTag.setName(a);
                    wsTags.add(tagRepository.save(newTag));
                }
            }
        }
        ws.setWorkspaceTags(wsTags);
        workspaceRepository.saveAndFlush(ws);
    }
}


public Workspace findByNameOfPrincipal(String name){
    Workspace result;
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    assert userAccount != null;
    result = findByNameAndOwner(name, userAccount.getUsername());
    assert result != null;
    return result;
}


public Workspace findByNameAndOwner(String name,String userAccount){
    assert userAccount != null;
    Workspace result;
    result = workspaceRepository.findByName(name, userAccount);
    assert result != null;
    return result;
}


public void deleteReferences(String workspaceName,String username){
    Assert.notNull(workspaceName);
    Assert.notNull(username);
    Collection<Workspace> demos = findByDemoPrincipal();
    for (Workspace demo : demos) {
        if (demo.getOrigin() != null && demo.getOrigin().getName().equals(workspaceName)) {
            demo.setOrigin(null);
            workspaceRepository.saveAndFlush(demo);
        }
    }
}


public void updateLaunches(String workspaceName,String username){
    Assert.notNull(workspaceName);
    Assert.notNull(username);
    Workspace ws = workspaceRepository.findByName(workspaceName, username);
    int l = ws.getLaunches();
    ws.setLaunches(l + 1);
    if (ws.getOrigin() != null && username.equals(DEMO_MASTER))
        updateLaunches(ws.getOrigin().getName(), ws.getOrigin().getOwner().getUserAccount().getUsername());
    workspaceRepository.saveAndFlush(ws);
}


public boolean checkNameAvailability(String workspaceName,String username){
    return (workspaceRepository.findByName(workspaceName, username) == null);
}


public void updateDownloas(Workspace w){
    int d = w.getDownloads() + 1;
    w.setDownloads(d);
    if (w.getOrigin() != null) {
        updateDownloas(w.getOrigin());
    }
    workspaceRepository.saveAndFlush(w);
}


public void createWorkspace(String workspaceName,String username,String origin){
    Assert.notNull(workspaceName);
    Assert.notNull(username);
    if (checkNameAvailability(workspaceName, username)) {
        Workspace ws = new Workspace();
        ws.setDownloads(0);
        ws.setDescription("");
        ws.setLastMod(Calendar.getInstance().getTime());
        ws.setLaunches(0);
        ws.setWsVersion(1);
        ws.setName(workspaceName);
        UserAccount ua = uar.findByUsername(username);
        Researcher rese = researcherRepository.findByUserAccountId(ua.getId());
        ws.setOwner(rese);
        if (origin != null) {
            ws.setOrigin(workspaceRepository.findById(Integer.valueOf(origin)));
            ws.setDescription(workspaceRepository.findById(Integer.valueOf(origin)).getDescription());
            if (username.startsWith("demo"))
                updateLaunches(workspaceName, DEMO_MASTER);
        }
        workspaceRepository.saveAndFlush(ws);
    }
}


public void updateDownloads(String workspaceName,String username){
    if (username.equals(DEMO_MASTER) || !username.startsWith("demo")) {
        Workspace ws = findByNameAndOwner(workspaceName, username);
        int d = ws.getDownloads() + 1;
        ws.setDownloads(d);
        if (ws.getOrigin() != null) {
            updateDownloads(ws.getOrigin().getName(), ws.getOrigin().getOwner().getUserAccount().getUsername());
        }
        workspaceRepository.saveAndFlush(ws);
    }
}


public Collection<Workspace> findByOtherDemos(){
    Collection<Workspace> result = new ArrayList();
    Collection<Workspace> own;
    Collection<Workspace> demos;
    own = findByPrincipal();
    demos = findByDemo();
    for (Workspace demo : demos) {
        if (demo.getOrigin() != null && !own.contains(demo.getOrigin()))
            result.add(demo.getOrigin());
    }
    return result;
}


}