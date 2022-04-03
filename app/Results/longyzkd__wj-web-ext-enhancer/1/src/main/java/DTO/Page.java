package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Page {

 protected  int page;

 protected  int limit;

 private  Integer total;

 private  List<T> dataList;

 private  Boolean success;

 private  String orderBy;


public int getLimit(){
    return limit;
}


public int getPage(){
    return page;
}


public List<T> getDataList(){
    return dataList;
}


public int getFirstResult(){
    int firstResult = (page - 1) * limit;
    if (firstResult >= total) {
        firstResult = 0;
    }
    return firstResult;
}


public Boolean getSuccess(){
    return success;
}


public String getOrderBy(){
    return orderBy;
}


public Integer getTotal(){
    return total;
}


}