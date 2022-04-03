package com.xwtec.xwserver.service.tool;
 import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IDatabaseService {


public List<Map<String,Object>> getDataBySql(Page page)
;

public boolean checkSql(ModelAndView modelAndView,Page page)
;

}