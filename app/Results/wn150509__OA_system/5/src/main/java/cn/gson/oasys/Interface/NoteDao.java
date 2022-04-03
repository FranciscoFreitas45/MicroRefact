package cn.gson.oasys.Interface;
public interface NoteDao {

   public Object findOne(Object Object);
   public Object save(Object Object);
   public Noteuser finduserid(long noteid,Long userId);
   public List<Note> findByCatalogId(long catalogId,long userid);
}