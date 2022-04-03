package hei2017.Request;
import hei2017.DTO.Sprint;
public interface SprintRequest {

   public Set<Sprint> getProjectSprints(Long id);
   public void setProjectSprints(Set<Sprint> projectSprints,Long id);
   public void addSprint(Sprint sprint,Long id);
}