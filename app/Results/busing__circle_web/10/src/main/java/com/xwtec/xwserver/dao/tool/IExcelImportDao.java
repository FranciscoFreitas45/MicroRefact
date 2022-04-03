package com.xwtec.xwserver.dao.tool;
 import java.util.List;
import java.util.Map;
import com.xwtec.xwserver.exception.SPTException;
public interface IExcelImportDao {


public int[] addToDB(String sql,List<Map<String,?>> paramListMap)
;

}