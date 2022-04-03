package Interface;
public interface ParadeService {

   public Parade findOne(int id);
   public Parade save(Parade parade);
   public List<Parade> getParadesByArea(Area area);
   public Boolean hasArea(Chapter chapter);
   public List<Parade> filterParadesChapter(Chapter chapter,String option);
   public Parade reconstrucParadeStatus(Parade parade);
}