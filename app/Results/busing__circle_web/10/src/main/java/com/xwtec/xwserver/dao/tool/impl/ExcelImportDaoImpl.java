package com.xwtec.xwserver.dao.tool.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.dao.tool.IExcelImportDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class ExcelImportDaoImpl implements IExcelImportDao{

@Resource
 private ICommonDao commonDao;


public int[] addToDB(String sql,List<Map<String,?>> paramListMap){
    return commonDao.batchUpdate(sql, paramListMap);
}


}