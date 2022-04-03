package org.jeecgframework.web.cgreport.dao.core;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;
@Repository("cgReportDao")
public interface CgReportDao {


@Arguments("configId")
public List<Map<String,Object>> queryCgReportItems(String configId)
;

@Arguments("id")
public Map queryCgReportMainConfig(String id)
;

}