package hei2017.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.dao.SprintDAO;
import hei2017.entity.Sprint;
@Service
public class SprintProjectService {

@Autowired
 private SprintDAO sprintdao;


public Set<Sprint> getProjectSprints(Long id){
return sprintdao.getProjectSprints(id);
}


public void setProjectSprints(Long id,Set<Sprint> projectSprints){
sprintdao.setProjectSprints(id,projectSprints);
}


public void addSprint(Long id,Sprint sprint){
sprintdao.addSprint(id,sprint);
}


}