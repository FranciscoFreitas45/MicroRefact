package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.WorkspaceService;
public class WorkspaceServiceImpl implements WorkspaceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Workspace findByNameAndOwner(String name,String userAccount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByNameAndOwner"))
    .queryParam("name",name)
    .queryParam("userAccount",userAccount)
;  Workspace aux = restTemplate.getForObject(builder.toUriString(), Workspace.class);

 return aux;
}


public void createWorkspace(String workspaceName,String username,String origin){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createWorkspace"))
    .queryParam("workspaceName",workspaceName)
    .queryParam("username",username)
    .queryParam("origin",origin)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateDemo(String demoWorkspaceName,String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateDemo"))
    .queryParam("demoWorkspaceName",demoWorkspaceName)
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


public void delete(String workspaceName,String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("workspaceName",workspaceName)
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


}