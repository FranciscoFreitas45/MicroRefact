package hei2017.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.dao.ProjectDAO;
import hei2017.entity.Project;
@Service
public class ProjectSprintService {

@Autowired
 private ProjectDAO projectdao;


public void setSprintProject(Long id,Project sprintProject){
projectdao.setSprintProject(id,sprintProject);
}


public Project getSprintProject(Long id){
return projectdao.getSprintProject(id);
}


}