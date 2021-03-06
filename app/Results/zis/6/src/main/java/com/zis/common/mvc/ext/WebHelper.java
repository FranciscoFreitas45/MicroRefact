package com.zis.common.mvc.ext;
 import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
public class WebHelper {

 private  Logger logger;

 private  String KEY_PAGE;

 private  String KEY_SIZE;

 private  String KEY_SORT;

 private  String KEY_DIRECTION;

 public  Integer DEFAULT_PAGE_SIZE;

 public  String DEFAULT_DIRECTION;


public Pageable buildPageRequest(HttpServletRequest request){
    Integer page = getIntRequestParam(request, KEY_PAGE);
    if (page == null || page < 0) {
        page = 0;
    }
    Integer size = getIntRequestParam(request, KEY_SIZE);
    if (size == null || size < 0) {
        size = DEFAULT_PAGE_SIZE;
    }
    String[] sorts = request.getParameterValues(KEY_SORT);
    if (sorts == null) {
        return new PageRequest(page, size);
    } else {
        String directionStr = request.getParameter(KEY_DIRECTION);
        Direction direction = DEFAULT_DIRECTION.equals(directionStr) ? Direction.DESC : Direction.ASC;
        return new PageRequest(page, size, direction, sorts);
    }
}


public Double getDoubleRequestParam(HttpServletRequest request,String paramName){
    String valStr = request.getParameter(paramName);
    try {
        return Double.valueOf(valStr);
    } catch (NumberFormatException e) {
        logger.info("failed to convert param '" + paramName + "' to Double:" + valStr);
        return null;
    }
}


public Integer getIntRequestParam(HttpServletRequest request,String paramName){
    String valStr = request.getParameter(paramName);
    try {
        return Integer.valueOf(valStr);
    } catch (NumberFormatException e) {
        logger.info("failed to convert param '" + paramName + "' to Integer:" + valStr);
        return null;
    }
}


}