package hei2017.Interface;
public interface StoryDAO {

   public Set<Story> findByStorySprintId(Long idSprint);
}