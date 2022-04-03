package com.circle.controller.good;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.circle.constant.SystemDict;
import com.circle.pojo.batchsell.BatchSell;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.goodtype.GoodTypeArg;
import com.circle.pojo.goodtype.GoodTypeAttr;
import com.circle.service.batchsell.IBatchSellService;
import com.circle.service.circle.ICircleService;
import com.circle.service.good.IGoodService;
import com.circle.utils.SystemExceptionUtil;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.Interface.ICircleService;
import com.Interface.IBatchSellService;
@Controller
@RequestMapping("good")
public class GoodController {

 private  Logger logger;

@Resource
 private IGoodService goodService;

@Resource
 private ICircleService circleService;

@Resource
 private IBatchSellService batchSellService;


@RequestMapping("commentList.action")
public ModelAndView commentList(ModelAndView mav,Page page){
    try {
        List<Map<String, Object>> commentList = goodService.queryGoodComment(page);
        mav.addObject("page", page);
        mav.addObject("commentList", commentList);
        mav.setViewName("/good/comment_list.jsp");
    } catch (SPTException e) {
        SystemExceptionUtil.logSPTException(mav, logger, e);
    }
    return mav;
}


@RequestMapping("goodDetail.action")
public ModelAndView toGoodDetail(ModelAndView mav,String circleId,String goodId,HttpServletRequest request){
    try {
        BatchSell batchSell = batchSellService.queryCurrentBatchSell();
        Map<String, Object> goodInfo = goodService.queryGood(goodId);
        List<Map<String, Object>> image_list = goodService.queryImageList(goodId);
        mav.addObject("good", goodInfo);
        mav.addObject("image_list", image_list);
        List<GoodTypeArg> type_arg = SystemDict.GOOD_TYPE_ARG_MAP.get(goodInfo.get("type_id"));
        List<GoodTypeAttr> type_attr = SystemDict.GOOD_TYPE_ATTR_MAP.get(goodInfo.get("type_id"));
        mav.addObject("type_arg", type_arg);
        mav.addObject("type_attr", type_attr);
        List<Map<String, Object>> attr_values = goodService.queryGoodTypeAttrValues(goodId);
        List<Map<String, Object>> arg_values = goodService.queryGoodTypeArgValues(goodId);
        mav.addObject("attr_values", attr_values);
        mav.addObject("arg_values", arg_values);
        Circle circle = circleService.queryCircleByCircleId(circleId);
        mav.addObject("circleId", circleId);
        mav.addObject("circle", circle);
        mav.addObject("batchSell", batchSell);
        mav.setViewName("/good/good_detail.jsp");
    } catch (SPTException e) {
        SystemExceptionUtil.logSPTException(mav, logger, e);
    }
    return mav;
}


}