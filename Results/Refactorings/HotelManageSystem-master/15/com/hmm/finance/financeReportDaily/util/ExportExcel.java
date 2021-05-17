import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
public class ExportExcel {


public HSSFWorkbook exportExcel(List<FinanceReportDaily> classmateList){
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("信息表");
    sheet.setColumnWidth(0, 3000);
    HSSFCellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    int rowNum = 1;
    String[] headers = { "日期", "客房收入", "后勤支出", "工资支出", "总收入", "总支出", "利润" };
    // headers表示excel表中第一行的表头
    HSSFRow row = sheet.createRow(0);
    // 在excel表中添加表头
    for (int i = 0; i < headers.length; i++) {
        HSSFCell cell = row.createCell(i);
        HSSFRichTextString text = new HSSFRichTextString(headers[i]);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }
    // 在表中存放查询到的数据放入对应的列
    for (FinanceReportDaily frd : classmateList) {
        HSSFRow row1 = sheet.createRow(rowNum);
        Date date1 = frd.getDate();
        SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
        String str = timeFormater.format(date1);
        HSSFCell cell = row1.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(str);
        cell = row1.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getRoomIncome());
        cell = row1.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getLogisticstCost());
        cell = row1.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getSalaryCost());
        cell = row1.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getTotalIncome());
        cell = row1.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getTotalCost());
        cell = row1.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(frd.getProfit());
        rowNum++;
    }
    return workbook;
}


}