package run.halo.app.Interface;
public interface CategoryService {

   public List<Category> listAll(Sort sort,boolean queryEncryptCategory);
   public Object createInBatch(Object Object);
}