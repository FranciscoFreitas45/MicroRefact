package Interface;
public interface ParadeService {

   public Parade findOne(int id);
   public Parade save(Parade parade);
   public void putOrDeletePath(Integer paradeId);
}