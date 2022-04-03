package com.xwtec.xwserver.dao.tool.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.dao.tool.IDatabaseDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
@Repository
public class DatabaseDaoImpl implements IDatabaseDao{

@Resource
 private ICommonDao commonDao;


public List<Map<String,Object>> getDataBySql(Page page){
    // sql语句
    String sql = page.getSearchParam().get("querySql");
    /**
     * modified by houxu
     * date 2013-11-28
     * 过滤";"识别第一条SQL,并执行
     */
    sql = sql.split(";")[0];
    // 根据sql查询结果
    return commonDao.queryForList(sql, null, page);
}


}