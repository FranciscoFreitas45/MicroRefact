package org.jeecgframework.core.common.service;
 import java.util.Collection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public interface CommonExcelServiceI extends CommonService{


public HSSFWorkbook exportExcel(String title,Collection<?> titleSet,Collection<?> dataSet)
;

}