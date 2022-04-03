package com.xwtec.xwserver.dao.tool;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.tool.CommonNews;
import com.Interface.ICommonDao;
@Repository
public class RefreshRedisDao {

@Resource
 private ICommonDao commonDao;

 private  String COMMONNEWS_LIST;


public List<CommonNews> commonNesList(){
    return commonDao.queryForList(COMMONNEWS_LIST, CommonNews.class);
}


}