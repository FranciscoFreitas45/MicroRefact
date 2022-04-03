package Interface;
public interface ChapterService {

   public List<Area> listFreeAreas();
   public Chapter createChapter();
   public Chapter reconstruct(FormObjectChapter formObjectChapter,BindingResult binding);
   public Chapter saveCreate(Chapter chapter);
}