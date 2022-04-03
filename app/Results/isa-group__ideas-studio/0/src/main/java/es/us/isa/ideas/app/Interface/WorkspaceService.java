package es.us.isa.ideas.app.Interface;
public interface WorkspaceService {

   public Workspace findByNameAndOwner(String name,String userAccount);
   public void createWorkspace(String workspaceName,String username,String origin);
   public void updateDemo(String demoWorkspaceName,String username);
   public void delete(String workspaceName,String username);
}