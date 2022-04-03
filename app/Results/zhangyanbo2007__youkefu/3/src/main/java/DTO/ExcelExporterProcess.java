package DTO;
 import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UCKeFuTime;
import com.ukefu.util.UKTools;
import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.UKeFuDic;
import Interface.MetadataTable;
public class ExcelExporterProcess {

 private  HSSFWorkbook wb;

 private  Sheet sheet;

 private  CellStyle firstStyle;

 private  int rowNum;

 private  List<Map<String,Object>> values;

 private  MetadataTable table;

 private  OutputStream output;

 private  Row titleRow;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public ExcelExporterProcess(List<Map<String, Object>> values, MetadataTable table, OutputStream output) {
    this.values = values;
    this.table = table;
    this.output = output;
    wb = new HSSFWorkbook();
    sheet = wb.createSheet();
    firstStyle = createFirstCellStyle();
    createHead();
}
public void process(){
    createContent();
    if (table != null) {
        for (TableProperties tp : table.getTableproperty()) {
            sheet.autoSizeColumn(table.getTableproperty().indexOf(tp));
        }
        wb.write(this.output);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/process"))

;
restTemplate.put(builder.toUriString(),null);
}


}