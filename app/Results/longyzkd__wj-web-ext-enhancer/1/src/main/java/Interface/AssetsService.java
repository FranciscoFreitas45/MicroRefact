package Interface;
public interface AssetsService {

   public Object list(Object Object);
   public Object uniqueEntity(Object Object);
   public void delCascade(String id);
   public Object addObj(Object Object);
   public void updateForm(AssetsType data);
}