package com.uec.imonitor.peopledaily.controller;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.uec.imonitor.common.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.datatables.DataTablesResponseEntity;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;
@Scope("prototype")
@Controller
@RequestMapping(value = "")
public class PeoplesDailyController extends BaseController{

 private  Logger logger;

@Autowired
 private  IPeoplesDailyService peoplesDailyService;

@Value("${fanwen.ippool}")
 private  String ipPool;


@ResponseBody
// @RequestMapping(value = "/peoplesdaily", method = {RequestMethod.POST,RequestMethod.GET})
@RequestMapping(value = "/peoplesdaily", method = RequestMethod.POST)
public ModelMap peopleDailyJsonPost(HttpServletRequest request){
    // String[] ips = ipPool.split(",");
    // List<String> ipList = Arrays.asList(ips);
    // String requestIp = CommonUtil.getIp(request);
    // String requestIp = CommonUtil.getIpAddress(request);
    // String requestIp3 = CommonUtil.getIpAddr(request);
    // logger.info("requestIp :" + requestIp);
    // logger.info("requestIp2 :" + requestIp2);
    // logger.info("requestIp3 :" + requestIp3);
    /*String X_Forwarded_For = request.getHeader("X-Forwarded-For");
		String x_forwarded_for = request.getHeader("x-forwarded-for");
		String Proxy_ip = request.getHeader("Proxy-Client-IP");
		String Real_ip = request.getHeader("X-Real-IP");
		String WL_Proxy_ip = request.getHeader("WL-Proxy-Client-IP");
		String HTTP_CLIENT_IP = request.getHeader("HTTP_CLIENT_IP");
		String HTTP_X_FORWARDED_FOR = request.getHeader("HTTP_X_FORWARDED_FOR");
		String getRemoteAddr = request.getRemoteAddr();

		logger.info("Real_IP:" + X_Forwarded_For);
		logger.info("Real_IP:" + x_forwarded_for);
		logger.info("Real_IP:" + Proxy_ip);
		logger.info("Real_IP:" + Real_ip);
		logger.info("Real_IP:" + WL_Proxy_ip);
		logger.info("Real_IP:" + HTTP_CLIENT_IP);
		logger.info("Real_IP:" + HTTP_X_FORWARDED_FOR);
		logger.info("Real_IP:" + getRemoteAddr);*/
    String requestIp = CommonUtil.getIpAddress(request);
    ModelMap map = new ModelMap();
    // if (ipList.contains(requestIp)){
    logger.info("本次请求合法IP:" + requestIp + "时间为:" + new Date());
    String news = "";
    try {
        news = IOUtils.toString(request.getInputStream());
    } catch (IOException e) {
        map.put("errorCode", 2);
        map.put("errorMsg", "Request body is empty!");
        logger.error(e + "");
        logger.info("本次请求体数据时间：" + new Date());
        e.printStackTrace();
        return map;
    }
    if (null != news && !StringUtils.isEmpty(news)) {
        List<PeoplesDailyEntity> save = new ArrayList<>();
        try {
            logger.info("调用service保存方法");
            save = peoplesDailyService.savePeopleDaily(news);
            if (!CollectionUtils.isEmpty(save)) {
                map.put("errorCode", 0);
                map.put("errorMsg", "OK");
            } else {
                map.put("errorCode", 3);
                map.put("errorMsg", "News Repeat");
            }
        } catch (Exception e) {
            map.put("errorCode", 1);
            map.put("errorMsg", "Analysis error!");
            logger.info("本次请求时间：" + new Date());
            logger.error(e + "");
            e.printStackTrace();
            return map;
        }
    } else {
        map.put("errorCode", 2);
        map.put("errorMsg", "Request body is empty!");
    }
    // }else {
    // logger.info("本次请求非法IP:" + requestIp + "时间为:" + new Date());
    // map.put("errorCode", 4);
    // map.put("errorMsg", "Illegal Request!");
    // }
    return map;
}


@ResponseBody
@RequestMapping(value = "/{tenantMark}/pagenews", method = RequestMethod.GET)
public ModelMap pagePeopleNews(DataTablesRequestEntity aoData){
    DataTablesResponseEntity<PeoplesDailyEntity> responseEntity = new DataTablesResponseEntity<PeoplesDailyEntity>();
    Page<PeoplesDailyEntity> pagePeopleNews = peoplesDailyService.pagePeopleNews(aoData);
    if (null != pagePeopleNews) {
        responseEntity.setAaData(pagePeopleNews.getContent());
        responseEntity.setsEcho(aoData.getsEcho());
        responseEntity.setiTotalRecords(pagePeopleNews.getTotalElements());
        responseEntity.setiTotalDisplayRecords(pagePeopleNews.getTotalElements());
    }
    return super.getModelMap(true, responseEntity, null, null);
}


@ResponseBody
@RequestMapping(value = "/transDataToCloud", method = RequestMethod.GET)
public ModelMap transDataToCloud(){
    ModelMap map = new ModelMap();
    try {
        peoplesDailyService.dataToAPICloud();
    } catch (Exception e) {
        logger.error(e + "");
    }
    return map;
}


@ResponseBody
@RequestMapping(value = "/verifyArticle", method = RequestMethod.GET)
public ModelMap verifyArticle(String webpageCode){
    ModelMap map = new ModelMap();
    if (null != webpageCode && !StringUtils.isEmpty(webpageCode)) {
        logger.info("审核新闻的webpagecode为:" + webpageCode + "时间为:" + new Date());
        PeoplesDailyEntity peoplesDailyEntity = peoplesDailyService.verifyArticle(webpageCode);
        if (null != peoplesDailyEntity) {
            logger.info("本次审核新闻操作成功,webpagecode为:" + webpageCode + "时间为:" + new Date());
            map.put("errorCode", 0);
            map.put("errorMsg", "OK!");
            return map;
        } else {
            logger.error("根据webpagecode查询不到相应新闻，webpagecode为:" + webpageCode + "时间为:" + new Date());
            map.put("errorCode", 1);
            map.put("errorMsg", "Analysis error!");
            return map;
        }
    } else {
        logger.info("本次审核失败,webpagecode为null:" + "时间为:" + new Date());
        map.put("errorCode", 2);
        map.put("errorMsg", "Request body is empty!");
        return map;
    }
}


@ResponseBody
@RequestMapping(value = "/{tenantMark}/peoplesdailyopera", method = RequestMethod.GET)
public ModelMap peoplesDailyOperal(String webpageCode){
    PeoplesDailyEntity peoplesDailyEntity = peoplesDailyService.peoplesDailyOpera(webpageCode);
    return super.getModelMap(true, peoplesDailyEntity, null, null);
}


@ResponseBody
@RequestMapping(value = "/{tenantMark}/peoplesdailydetail", method = RequestMethod.GET)
public ModelMap peoplesDailyDetail(String webpageCode){
    PeoplesDailyEntity peoplesDailyEntity = peoplesDailyService.findByWebpageCode(webpageCode);
    return super.getModelMap(true, peoplesDailyEntity, null, null);
}


}