package hei2017.service.Impl;
 import hei2017.dao.SprintDAO;
import hei2017.dao.StoryDAO;
import hei2017.dao.TaskDAO;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.entity.Task;
import hei2017.service.StoryService;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import hei2017.Interface.SprintDAO;
import hei2017.Interface.TaskDAO;
@Named
@Transactional
public class StoryServiceImpl implements StoryService{

@Inject
 private SprintDAO sprintDAO;

@Inject
 private StoryDAO storyDAO;

@Inject
 private TaskDAO taskDAO;


@Override
public Set<Story> findByStorySprint(Long idSprint){
    return storyDAO.findByStorySprintId(idSprint);
}


@Override
public List<Story> findAllWithoutSprint(){
    List<Story> listStoriesWithoutSprint = new ArrayList<>();
    List<Story> listStories = storyDAO.findAll();
    if (listStories != null) {
        for (Story story : listStories) {
            if (story.getStorySprint() == null)
                listStoriesWithoutSprint.add(story);
        }
    }
    return listStoriesWithoutSprint;
}


@Override
public Story updateStory(Long id,Story story){
    Story storyAUpdater = storyDAO.findOneById(id);
    storyAUpdater.setNom(story.getNom());
    storyAUpdater.setDescription(story.getDescription());
    storyAUpdater.setPoints(story.getPoints());
    storyAUpdater.setStatus(story.getStatus());
    storyAUpdater.setStorySprint(story.getStorySprint());
    storyAUpdater.setStoryTasks(story.getStoryTasks());
    return storyDAO.save(storyAUpdater);
}


@Override
public Long count(){
    return storyDAO.count();
}


@Override
public Story save(Story story){
    return storyDAO.save(story);
}


@Override
public Set<Story> findByStorySprintWithTask(Long idSprint){
    Set<Story> stories = findByStorySprint(idSprint);
    if (stories != null) {
        for (Story story : stories) {
            Set<Task> tasks = taskDAO.findByTaskStoryId(story.getId());
            story.setStoryTasks(tasks);
        }
    }
    return stories;
}


@Override
public List<Story> findAll(){
    return storyDAO.findAll();
}


@Override
public void delete(Story story){
    storyDAO.delete(story);
}


@Override
public void deleteOneById(Long id){
    storyDAO.delete(id);
}


@Override
public Story findOneById(Long id){
    return storyDAO.findOne(id);
}


@Override
public Story findOneByNom(String nom){
    return storyDAO.findOneByNom(nom);
}


@Override
public Boolean exists(String nom){
    return null != storyDAO.findOneByNom(nom);
}


@Override
public Story findOneByIdWithAll(Long id){
    Story story = storyDAO.findOneById(id);
    if (null != story) {
        Set<Task> storyTasks = taskDAO.findByTaskStoryId(story.getId());
        story.setStoryTasks(storyTasks);
        Sprint storySprint = sprintDAO.findBySprintStoriesId(story.getId());
        story.setStorySprint(storySprint);
    }
    return story;
}


@Override
public List<Story> findAllWithAll(){
    List<Story> stories = storyDAO.findAll();
    for (Story story : stories) {
        Set<Task> storyTasks = taskDAO.findByTaskStoryId(story.getId());
        story.setStoryTasks(storyTasks);
        Sprint storySprint = sprintDAO.findBySprintStoriesId(story.getId());
        story.setStorySprint(storySprint);
    }
    return stories;
}


}