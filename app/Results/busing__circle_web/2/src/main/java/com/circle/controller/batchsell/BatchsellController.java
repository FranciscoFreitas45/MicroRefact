package com.circle.controller.batchsell;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.good.GoodBean;
import com.circle.service.batchsell.IBatchSellService;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.json.JSONArray;
import com.DTO.JSONArray;
@Controller
@RequestMapping("batchsell")
public class BatchsellController {

 private  Logger logger;

@Resource
 private  IBatchSellService batchSellService;


@ResponseBody
@RequestMapping("queryBatchsell.action")
public JSONArray queryBatchsell(HttpServletRequest request){
    List<GoodBean> list = new ArrayList<GoodBean>();
    try {
        BatchSell _batchSell = batchSellService.queryCurrentBatchSell();
        if (_batchSell != null)
            list = batchSellService.queryGoods(_batchSell.getId());
    } catch (Exception ex) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], ex));
    }
    return JSONArray.fromObject(list);
}


public void setBatchSellService(IBatchSellService batchSellService){
    this.batchSellService = batchSellService;
}


public IBatchSellService getBatchSellService(){
    return batchSellService;
}


}