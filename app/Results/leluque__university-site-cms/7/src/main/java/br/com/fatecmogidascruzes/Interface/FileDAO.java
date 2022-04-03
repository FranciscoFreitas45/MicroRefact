package br.com.fatecmogidascruzes.Interface;
public interface FileDAO {

   public Object save(Object Object);
   public Object findById(Object Object);
   public Object deleteById(Object Object);
   public Object findByHash(Object Object);
   public Object delete(Object Object);
}