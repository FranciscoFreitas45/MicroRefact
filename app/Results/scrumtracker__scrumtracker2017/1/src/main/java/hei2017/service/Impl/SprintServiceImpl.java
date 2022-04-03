package hei2017.service.Impl;
 import hei2017.dao.ProjectDAO;
import hei2017.dao.SprintDAO;
import hei2017.dao.StoryDAO;
import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.service.SprintService;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import hei2017.Interface.ProjectDAO;
import hei2017.Interface.StoryDAO;
@Named
@Transactional
public class SprintServiceImpl implements SprintService{

@Inject
 private ProjectDAO projectDAO;

@Inject
 private SprintDAO sprintDAO;

@Inject
 private StoryDAO storyDAO;


@Override
public long countAll(){
    return sprintDAO.count();
}


@Override
public Sprint findOneById(Long id){
    return sprintDAO.findOne(id);
}


@Override
public Set<Sprint> findBySprintProject(Long idProject){
    return sprintDAO.findBySprintProjectId(idProject);
}


@Override
public Sprint save(Sprint sprint){
    return sprintDAO.save(sprint);
}


@Override
public Boolean exists(String nom){
    return null != sprintDAO.findOneByNom(nom);
}


@Override
public Sprint findOneByIdWithAll(Long id){
    Sprint sprint = sprintDAO.findOneById(id);
    if (null != sprint) {
        Set<Story> sprintStories = storyDAO.findByStorySprintId(sprint.getId());
        sprint.setSprintStories(sprintStories);
        Set<Project> sprintProjects = projectDAO.findByProjectSprintsId(sprint.getId());
        if (sprintProjects != null) {
            Iterator<Project> sprintProjectIterator = sprintProjects.iterator();
            if (sprintProjectIterator.hasNext()) {
                Project sprintProject = sprintProjectIterator.next();
                sprint.setSprintProject(sprintProject);
            }
        }
    }
    return sprint;
}


@Override
public List<Sprint> findAllWithAll(){
    List<Sprint> sprints = sprintDAO.findAll();
    for (Sprint sprint : sprints) {
        Set<Story> sprintStories = storyDAO.findByStorySprintId(sprint.getId());
        sprint.setSprintStories(sprintStories);
        Iterator<Project> sprintProjectIterator = projectDAO.findByProjectSprintsId(sprint.getId()).iterator();
        if (sprintProjectIterator.hasNext()) {
            Project sprintProject = sprintProjectIterator.next();
            sprint.setSprintProject(sprintProject);
        }
    }
    return sprints;
}


@Override
public List<Sprint> findAll(){
    return sprintDAO.findAll();
}


@Override
public void delete(Sprint sprint){
    sprintDAO.delete(sprint);
}


@Override
public void deleteOneById(Long id){
    sprintDAO.delete(id);
}


@Override
public Sprint updateSprint(Long idSPrint,Sprint sprint){
    Sprint sprintAUpdater = sprintDAO.findOneById(idSPrint);
    sprintAUpdater.setNom(sprint.getNom());
    sprintAUpdater.setDateDebut(sprint.getDateDebut());
    sprintAUpdater.setDateFin(sprint.getDateFin());
    sprintAUpdater.setDescription(sprint.getDescription());
    return sprintDAO.save(sprintAUpdater);
}


@Override
public List<Sprint> findByProjectSprintIdWithStories(Long idProject){
    Set<Sprint> sprints = sprintDAO.findBySprintProjectId(idProject);
    List<Sprint> sprintsOfProject = new ArrayList<>();
    for (Sprint sprint : sprints) {
        Set<Story> sprintStories = storyDAO.findByStorySprintId(sprint.getId());
        sprint.setSprintStories(sprintStories);
        sprintsOfProject.add(sprint);
    }
    return sprintsOfProject;
}


}