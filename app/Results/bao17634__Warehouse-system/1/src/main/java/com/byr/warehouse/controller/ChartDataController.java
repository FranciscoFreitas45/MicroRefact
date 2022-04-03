package com.byr.warehouse.controller;
 import com.alibaba.fastjson.JSON;
import com.byr.warehouse.Service.ChartDataProviderService;
import com.byr.warehouse.pojo.DaliyCount;
import com.byr.warehouse.pojo.DataModel;
import com.byr.warehouse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Controller
public class ChartDataController implements Serializable{

@Autowired
 private ChartDataProviderService dataProvider;


@ResponseBody
@RequestMapping(value = "/chart/status", method = { RequestMethod.GET, RequestMethod.POST })
public String getStatusCounts(){
    List<DaliyCount> status = dataProvider.getStatusCountIn7Days();
    List<DataModel> data = new ArrayList<>();
    for (DaliyCount daliyCount : status) {
        if (daliyCount.getType().equals("库存数量")) {
            data.add(new DataModel(DateUtil.dateToString(daliyCount.getComputeDate()), String.valueOf(daliyCount.getSize())));
        }
    }
    return JSON.toJSONString(data);
}


}