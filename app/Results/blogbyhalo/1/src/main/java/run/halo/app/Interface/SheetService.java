package run.halo.app.Interface;
public interface SheetService {

   public Object countByStatus(Object Object);
   public Sheet createBy(Sheet sheet,Set<SheetMeta> metas,boolean autoSave);
}