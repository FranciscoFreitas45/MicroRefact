package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkspaceController {

 private Workspace workspace;

 private Workspace workspace;


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
workspace.setName(name);
}


@PutMapping
("/setDownloads")
public void setDownloads(@RequestParam(name = "downloads") Integer downloads){
workspace.setDownloads(downloads);
}


}