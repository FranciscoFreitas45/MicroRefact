package hei2017.Request;
import hei2017.DTO.Story;
public interface StoryRequest {

   public Set<Story> getSprintStories(Long id);
   public void setSprintStories(Set<Story> sprintStories,Long id);
   public Sprint addStory(Story story,Long id);
}