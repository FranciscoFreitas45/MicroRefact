package hei2017.Interface;
public interface StoryService {

   public Story findOneByIdWithAll(Long id);
   public Story save(Story story);
}