package com.byr.warehouse.Service;
 import com.byr.warehouse.dao.DaliyCountReposity;
import com.byr.warehouse.pojo.DaliyCount;
import com.byr.warehouse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
@Service
public class ChartDataProviderService {

@Autowired
 private  DaliyCountReposity daliyCountReposity;


public List<DaliyCount> getStatusCountIn7Days(){
    List<DaliyCount> betweenDays = daliyCountReposity.findBetweenDays(DateUtil.getDateBefore(new Date(), 7), new Date());
    return betweenDays;
}


public int getOutountIn7Days(){
    return 0;
}


public int getEnterCountIn7Days(){
    return 0;
}


}