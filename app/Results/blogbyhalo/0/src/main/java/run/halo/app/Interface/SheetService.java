package run.halo.app.Interface;
public interface SheetService {

   public Object pageBy(Object Object);
   public Page<SheetListVO> convertToListVo(Page<Sheet> sheetPage);
   public Object getById(Object Object);
   public SheetDetailVO convertToDetailVo(Sheet sheet);
   public void publishVisitEvent(Integer sheetId);
   public Object getBySlug(Object Object);
}