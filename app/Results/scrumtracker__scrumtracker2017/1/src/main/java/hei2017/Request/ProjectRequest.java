package hei2017.Request;
import hei2017.DTO.Project;
public interface ProjectRequest {

   public void setSprintProject(Project sprintProject,Long id);
   public Project getSprintProject(Long id);
}