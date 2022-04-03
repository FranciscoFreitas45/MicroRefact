package Interface;
public interface CommonExcelServiceI {

   public HSSFWorkbook exportExcel(String title,Collection<?> titleSet,Collection<?> dataSet);
}