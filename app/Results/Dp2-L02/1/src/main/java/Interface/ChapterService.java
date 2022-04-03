package Interface;
public interface ChapterService {

   public List<Chapter> findAll();
   public Chapter findOne(int id);
   public Chapter loggedChapter();
   public Chapter reconstructPersonalData(Chapter chapter,BindingResult binding);
   public Chapter update(Chapter chapter);
}