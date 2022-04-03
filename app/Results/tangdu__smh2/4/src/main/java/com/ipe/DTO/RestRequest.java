package com.ipe.DTO;
 import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
public class RestRequest {

 private  Integer start;

 private  Integer limit;

 private  String sort;

 private  String queryParams;

 private  String property;

 private  String direction;


public String getProperty(){
    return property;
}


public Integer getStart(){
    return start;
}


public String getDirection(){
    return direction;
}


public Integer getLimit(){
    return limit;
}


public String getSort(){
    return sort;
}


public String getQueryParams(){
    return queryParams;
}


public org.springframework.data.domain.Sort getSorts(){
    if (this.getSort() != null && !"".equals(this.getSort())) {
        List<Sort> sorts = JSON.parseArray(this.getSort(), Sort.class);
        List<org.springframework.data.domain.Sort.Order> orders = new ArrayList<>();
        for (Sort sort1 : sorts) {
            orders.add(new org.springframework.data.domain.Sort.Order(org.springframework.data.domain.Sort.Direction.fromString(sort1.getDirection()), sort1.getProperty()));
        }
        return new org.springframework.data.domain.Sort(orders);
    }
    return null;
}


}