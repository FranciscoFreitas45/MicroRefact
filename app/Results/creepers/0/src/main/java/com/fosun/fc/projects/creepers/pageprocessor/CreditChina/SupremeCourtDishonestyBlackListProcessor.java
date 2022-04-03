package com.fosun.fc.projects.creepers.pageprocessor.CreditChina;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.BaseCreepersListProcessor;
import com.google.common.collect.Sets;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;
@Component("supremeCourtDishonestyBlackListProcessor")
public class SupremeCourtDishonestyBlackListProcessor extends BaseCreepersListProcessorimplements PageProcessor{

 private  Logger logger;

 private  Site site;

 private  String BASE_URL;

 private  String USER_AGENT;

 private  String ACCESS_URL_SEARCH;

 private  String ACCESS_URL_DETAIL;

 private  String SYMBOL_AND;

 private  String SYMBOL_EQ;

 private  String SYMBOL_EMPTY;

 private  String SYMBOL_COLON_EN;

 private  String SYMBOL_COLON_CN;

 private  String URL_PARAM_OBJECT_TYPE;

 private  String URL_PARAM_ENCRY_STR;

 private  String URL_PARAM_NAME;

 private  String URL_PARAM_AREA;

 private  String URL_PARAM_ID_CARD_OR_ORG_CODE;

 private  String URL_PARAM_SOURCE;

 private  String REGEX_URL_CREDIT_SEARCH;

 private  String REGEX_URL_CREDIT_DETAIL;

 private  String XPATH_DIV_TARGET_DATA;

 private  String XPATH_TEXT;

 private  String XPATH_UL;

 private  String XPATH_LI;

 private  String XPATH_STRONG;

 private  String REGEX_TIP;

 private  String COURT_DIS_INFO_COLUMN_NAME;

 private  String COURT_DIS_INFO_COLUMN_TYPE;

 private  String COURT_DIS_INFO_COLUMN_CERT_NO;

 private  String COURT_DIS_INFO_COLUMN_CASE_NO;

 private  String COURT_DIS_INFO_COLUMN_PRIVENCE;

 private  String COURT_DIS_INFO_COLUMN_SOURCE;

 private  String COURT_DIS_INFO_COLUMN_EXEC_COURT;

 private  String COURT_DIS_INFO_COLUMN_PERFORMANCE;

 private  String COURT_DIS_INFO_COLUMN_BEHAVIOR_DETAILS;

 private  String COURT_DIS_INFO_COLUMN_LEGAL_REP;

 private  String COURT_DIS_INFO_COLUMN_CASE_DT;

 private  String COURT_DIS_INFO_COLUMN_JUDGEMENT_COURT;

 private  String COURT_DIS_INFO_COLUMN_DUTY;

 private  String COURT_DIS_INFO_COLUMN_PUBLISH_DT;

 private  String COURT_DIS_INFO_COLUMN_JUDGEMENT_NO;

 private  String COURT_DIS_INFO_COLUMN_PERFORM_Y;

 private  String COURT_DIS_INFO_COLUMN_PERFORM_N;

 private  String JSON_KEY_RESULT;

 private  String JSON_KEY_RESULTS;

 private  String JSON_KEY_TOTAL_PAGE;

 private  String KEY_TOTAL_PAGE;

 private  String KEY_PAGE_NO;

 private  String KEY_THREAD_NUM;

public SupremeCourtDishonestyBlackListProcessor() {
    site = Site.me().setDomain(BASE_URL).setAcceptStatCode(Sets.newHashSet(200)).setSleepTime(3000).setTimeOut(30000).setUserAgent(USER_AGENT);
}
@Override
public void process(Page page){
    logger.info("======================>>>  SupremeCourtDishonestyBlackListProcessor start!");
    CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
    String jsonRequest = JSON.toJSONString(page.getRequest());
    param.setBreakDownRequest(jsonRequest);
    try {
        if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_SEARCH).match()) {
            JSONObject json = page.getJson().toObject(JSONObject.class);
            logger.info("page.json:" + json.toJSONString());
            JSONArray jsonArr = json.getJSONObject(JSON_KEY_RESULT).getJSONArray(JSON_KEY_RESULTS);
            logger.info("result:" + jsonArr.toJSONString());
            List<HashMap<String, Object>> tCreepersSupremeCourtDishonesty = new ArrayList<HashMap<String, Object>>();
            // 添加公司信用详情查看请求
            for (int i = 0; i < jsonArr.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                String name = jsonArr.getJSONObject(i).getString(URL_PARAM_NAME);
                String code = jsonArr.getJSONObject(i).getString(URL_PARAM_ID_CARD_OR_ORG_CODE);
                String province = jsonArr.getJSONObject(i).getString(URL_PARAM_AREA);
                String source = URL_PARAM_SOURCE;
                map.put(CreepersConstant.TCreepersCourtDishonestColumn.NAME.getValue(), name);
                logger.info(CreepersConstant.TCreepersCourtDishonestColumn.NAME.getValue() + SYMBOL_COLON_EN + name);
                map.put(CreepersConstant.TCreepersCourtDishonestColumn.CODE.getValue(), code);
                logger.info(CreepersConstant.TCreepersCourtDishonestColumn.CODE.getValue() + SYMBOL_COLON_EN + code);
                map.put(CreepersConstant.TCreepersCourtDishonestColumn.PROVINCE.getValue(), province);
                logger.info(CreepersConstant.TCreepersCourtDishonestColumn.PROVINCE.getValue() + SYMBOL_COLON_EN + province);
                map.put(CreepersConstant.TCreepersCourtDishonestColumn.SOURCE.getValue(), source);
                logger.info(CreepersConstant.TCreepersCourtDishonestColumn.SOURCE.getValue() + SYMBOL_COLON_EN + source);
                tCreepersSupremeCourtDishonesty.add(map);
                String objectType = jsonArr.getJSONObject(i).getString(URL_PARAM_OBJECT_TYPE);
                String encryStr = jsonArr.getJSONObject(i).getString(URL_PARAM_ENCRY_STR);
                page.addTargetRequest(BASE_URL + ACCESS_URL_DETAIL + URL_PARAM_OBJECT_TYPE + SYMBOL_EQ + objectType + SYMBOL_AND + URL_PARAM_ENCRY_STR + SYMBOL_EQ + encryStr);
                logger.info(BASE_URL + ACCESS_URL_DETAIL + URL_PARAM_OBJECT_TYPE + SYMBOL_EQ + objectType + SYMBOL_AND + URL_PARAM_ENCRY_STR + SYMBOL_EQ + encryStr);
            }
            page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DISHONEST.getMapKey(), tCreepersSupremeCourtDishonesty);
            int totalPageCount = null == json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE) ? 0 : Integer.parseInt(json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE));
            int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 1 : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
            int pageNo = null == page.getRequest().getExtra(KEY_PAGE_NO) ? 1 : (int) page.getRequest().getExtra(KEY_PAGE_NO);
            NameValuePair[] nameValuePairs = (NameValuePair[]) page.getRequest().getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
            if (pageNo == 1) {
                for (int j = 1; j <= threadNum; j++) {
                    if (pageNo + j <= totalPageCount) {
                        Request request = new Request(BASE_URL + ACCESS_URL_SEARCH + new Date().getTime());
                        request.setMethod(HttpConstant.Method.POST);
                        NameValuePair[] newNameValuePairs = copyAndModifyNameValuePair(nameValuePairs, "page", pageNo + j + "");
                        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, newNameValuePairs);
                        request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                        request.putExtra(KEY_PAGE_NO, pageNo + threadNum);
                        request.putExtra(KEY_THREAD_NUM, threadNum);
                        page.addTargetRequest(request);
                        logger.info("=============>添加request：" + request);
                    }
                }
            } else {
                if (pageNo + threadNum <= totalPageCount) {
                    Request request = new Request(BASE_URL + ACCESS_URL_SEARCH + new Date().getTime());
                    request.setMethod(HttpConstant.Method.POST);
                    NameValuePair[] newNameValuePairs = copyAndModifyNameValuePair(nameValuePairs, "page", pageNo + threadNum + "");
                    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, newNameValuePairs);
                    request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                    request.putExtra(KEY_PAGE_NO, pageNo + threadNum);
                    request.putExtra(KEY_THREAD_NUM, threadNum);
                    page.addTargetRequest(request);
                    logger.info("=============>添加request：" + request);
                }
            }
            logger.info("======================>>>  信用查询   end!");
        } else if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_DETAIL).match()) {
            logger.info("======================>>>  失信信息   start!");
            List<Selectable> list = page.getHtml().xpath(XPATH_DIV_TARGET_DATA).nodes();
            for (int i = 0; i < list.size(); i++) {
                if (0 == i) {
                    logger.info("===========暂不录入基础信息===========");
                } else if (1 == i) {
                    logger.info("===========暂不录入优良信息===========");
                } else if (2 == i) {
                    logger.info("===========暂不录入不良信息===========");
                } else if (3 == i) {
                    if (!list.get(i).regex(REGEX_TIP).match())
                        getSupremeCourtDisInfo(page, list.get(i));
                }
            }
            logger.info("======================>>>  失信信息   end!");
        }
        logger.info("======================>>>  SupremeCourtDishonestyBlackListProcessor end!");
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("SupremeCourtDishonestyBlackListProcessor process error:", e);
        param.setErrorInfo("SupremeCourtDishonestyBlackListProcessor process error:" + e.getMessage());
        param.setErrorPath(getClass().toString());
        if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_DETAIL).match())
            param.setBreakDownRequest("");
        creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
        logger.error("======================>>>  SupremeCourtDishonestyBlackListProcessor end!");
    }
}


public void modifySpecNameValuePair(NameValuePair[] nameValuePairs,String key,String value){
    for (int i = 0; i < nameValuePairs.length; i++) {
        if ("page".equals(nameValuePairs[i].getName())) {
            nameValuePairs[i] = new BasicNameValuePair(key, value);
        }
    }
}


public void getSupremeCourtDisInfo(Page page,Selectable divSel){
    // 创建存储数据结构
    List<HashMap<String, Object>> tCreepersSupremeCourtDisInfo = new ArrayList<HashMap<String, Object>>();
    List<Selectable> ulList = divSel.xpath(XPATH_UL).nodes();
    for (Selectable ulSel : ulList) {
        logger.info("===========失信记录============");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Selectable> liList = ulSel.xpath("//li").nodes();
        for (Selectable liSel : liList) {
            String name = liSel.xpath(XPATH_LI + XPATH_STRONG + XPATH_TEXT).toString().replace(SYMBOL_COLON_EN, SYMBOL_EMPTY).replace(SYMBOL_COLON_CN, SYMBOL_EMPTY).trim();
            String value = liSel.xpath(XPATH_LI + XPATH_TEXT).toString().trim();
            if (COURT_DIS_INFO_COLUMN_NAME.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.NAME.getValue(), value);
                logger.info(COURT_DIS_INFO_COLUMN_NAME + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.NAME.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_TYPE.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.TYPE.getValue(), value);
                logger.info(COURT_DIS_INFO_COLUMN_CERT_NO + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.TYPE.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_CERT_NO.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.CODE.getValue(), value);
                logger.info(COURT_DIS_INFO_COLUMN_CERT_NO + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.CODE.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_CASE_NO.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.CASE_NO.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.CASE_NO.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_PRIVENCE.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.PROVINCE.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.PROVINCE.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_SOURCE.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.SOURCE.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.SOURCE.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_EXEC_COURT.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.COURT.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.COURT.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_PERFORMANCE.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORMANCE.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORMANCE.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_BEHAVIOR_DETAILS.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.SPECIFIC.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.SPECIFIC.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_LEGAL_REP.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.LEGAL_REP.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.LEGAL_REP.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_CASE_DT.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.CASE_DT.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.CASE_DT.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_JUDGEMENT_COURT.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.IMPLEMENT_UNIT.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.IMPLEMENT_UNIT.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_DUTY.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.DUTY.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.DUTY.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_PUBLISH_DT.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.PUBLISH_DT.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.PUBLISH_DT.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_JUDGEMENT_NO.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.IMPLEMENT_NO.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.IMPLEMENT_NO.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_PERFORM_Y.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORM_Y.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORM_Y.getValue()));
            } else if (COURT_DIS_INFO_COLUMN_PERFORM_N.equals(name)) {
                map.put(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORM_N.getValue(), value);
                logger.info(name + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersCourtDisInfoColumn.PERFORM_N.getValue()));
            } else {
                logger.info(name + SYMBOL_COLON_CN + value + "，该值暂未录入数据库");
            }
        }
        tCreepersSupremeCourtDisInfo.add(map);
    }
    page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DIS_INFO.getMapKey(), tCreepersSupremeCourtDisInfo);
}


public NameValuePair[] copyAndModifyNameValuePair(NameValuePair[] nameValuePairs,String key,String value){
    if (null != nameValuePairs && nameValuePairs.length > 0 && StringUtils.isNoneBlank(key) && StringUtils.isNoneBlank(value)) {
        NameValuePair[] newNameValuePairs = new NameValuePair[nameValuePairs.length];
        for (int i = 0; i < newNameValuePairs.length; i++) {
            if (key.equals(nameValuePairs[i].getName())) {
                newNameValuePairs[i] = new BasicNameValuePair(key, value + SYMBOL_EMPTY);
            } else {
                newNameValuePairs[i] = new BasicNameValuePair(nameValuePairs[i].getName(), nameValuePairs[i].getValue());
            }
        }
        return newNameValuePairs;
    } else {
        return nameValuePairs;
    }
}


@Override
public Site getSite(){
    return site;
}


public void main(String[] args){
    CreepersParamDTO param = new CreepersParamDTO();
    param.setTaskType(BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue());
    String url = "http://www.creditchina.gov.cn/shiXinRen?t=" + new Date().getTime() + "&currentPageNo=1";
    Request request = new Request(url);
    request.setMethod(HttpConstant.Method.POST);
    NameValuePair[] nameValuePair = new NameValuePair[2];
    nameValuePair[0] = new BasicNameValuePair("keyword", "");
    nameValuePair[1] = new BasicNameValuePair("page", "1");
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
    Spider.create(new SupremeCourtDishonestyBlackListProcessor()).setDownloader(new HttpRequestDownloader().setParam(param)).thread(1).addRequest(request).run();
}


}