package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkspaceServiceController {

 private WorkspaceService workspaceservice;


@PutMapping
("/updateTime")
public void updateTime(@RequestParam(name = "workspaceName") String workspaceName,@RequestParam(name = "username") String username){
workspaceservice.updateTime(workspaceName,username);
}


@PutMapping
("/createWorkspaceWithTags")
public void createWorkspaceWithTags(@RequestParam(name = "workspaceName") String workspaceName,@RequestParam(name = "description") String description,@RequestParam(name = "username") String username,@RequestParam(name = "origin") String origin,@RequestParam(name = "tags") String tags){
workspaceservice.createWorkspaceWithTags(workspaceName,description,username,origin,tags);
}


@GetMapping
("/findByNameAndOwner")
public Workspace findByNameAndOwner(@RequestParam(name = "name") String name,@RequestParam(name = "userAccount") String userAccount){
  return workspaceservice.findByNameAndOwner(name,userAccount);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "workspace") Workspace workspace){
workspaceservice.save(workspace);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "workspaceName") String workspaceName,@RequestParam(name = "username") String username){
workspaceservice.delete(workspaceName,username);
}


@PutMapping
("/updateDownloads")
public void updateDownloads(@RequestParam(name = "workspaceName") String workspaceName,@RequestParam(name = "username") String username){
workspaceservice.updateDownloads(workspaceName,username);
}


@PutMapping
("/createWorkspace")
public void createWorkspace(@RequestParam(name = "workspaceName") String workspaceName,@RequestParam(name = "username") String username,@RequestParam(name = "origin") String origin){
workspaceservice.createWorkspace(workspaceName,username,origin);
}


@PutMapping
("/updateDemo")
public void updateDemo(@RequestParam(name = "demoWorkspaceName") String demoWorkspaceName,@RequestParam(name = "username") String username){
workspaceservice.updateDemo(demoWorkspaceName,username);
}


}