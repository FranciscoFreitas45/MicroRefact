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
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;
@Component
public class TaxEvasionProcessor extends BaseCreepersListProcessorimplements PageProcessor{

 private Logger logger;

 private  Site site;

 private  String BASE_URL;

 private  String REGEX_URL_CREDIT_SEARCH;

 private  String ACCESS_URL_CREDIT_SEARCH;

 private  String ACCESS_URL_CREDIT_DETAIL;

 private  String USER_AGENT;

 private  String SYMBOL_EQ;

 private  String SYMBOL_AND;

 private  String SYMBOL_EMPTY;

 private  String SYMBOL_COLON_EN;

 private  String SYMBOL_COLON_CN;

 private  String KEY_TOTAL_PAGE;

 private  String KEY_PAGE_NO;

 private  String KEY_THREAD_NUM;

 private  String JSON_KEY_RESULT;

 private  String JSON_KEY_RESULTS;

 private  String JSON_KEY_TOTAL_PAGE_COUNT;

 private  String JSON_KEY_NAME;

 private  String JSON_KEY_CODE;

 private  String JSON_KEY_GOOD_COUNT;

 private  String JSON_KEY_BAD_COUNT;

 private  String JSON_KEY_DISHONESTY_COUNT;

 private  String JSON_KEY_AREA;

 private  String JSON_KEY_OBJECT_TYPE;

 private  String JSON_KEY_ENCRY_STR;

 private  String XPATH_DIV_TARGET_DATA;

 private  String XPATH_TEXT;

 private  String XPATH_UL;

 private  String XPATH_LI;

 private  String XPATH_STRONG;

 private  String REGEX_TIP;

 private  String SHI_XIN_COLUMN_SOURCE;

 private  String SHI_XIN_COLUMN_TYPE;

 private  String SHI_XIN_COLUMN_PROVINCE;

 private  String SHI_XIN_COLUMN_COURT;

 private  String SHI_XIN_COLUMN_NAME;

 private  String SHI_XIN_COLUMN_TAX_NO;

 private  String SHI_XIN_COLUMN_CODE;

 private  String SHI_XIN_COLUMN_ADDR;

 private  String SHI_XIN_COLUMN_LEGAL_REP;

 private  String SHI_XIN_COLUMN_LEGAL_SEX;

 private  String SHI_XIN_COLUMN_CERT_NAME;

 private  String SHI_XIN_COLUMN_FINANCIAL_NAME;

 private  String SHI_XIN_COLUMN_FINANCIAL_SEX;

 private  String SHI_XIN_COLUMN_FINANCIAL_CERT_NAME;

 private  String SHI_XIN_COLUMN_INTER_INFO;

 private  String SHI_XIN_COLUMN_CASE_NATURE;

 private  String SHI_XIN_COLUMN_ILLEGAL_FACTS;

 private  String SHI_XIN_COLUMN_PUNISH_INFO;

 private  String SHI_XIN_COLUMN_PUBLISH_LEVEL;


@Override
public void process(Page page){
    logger.info("======================>>>  TaxEvasionProcessor start!");
    CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
    String jsonRequest = JSON.toJSONString(page.getRequest());
    param.setBreakDownRequest(jsonRequest);
    try {
        if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_SEARCH).match()) {
            JSONObject json = page.getJson().toObject(JSONObject.class);
            logger.info("page.json:" + json.toJSONString());
            JSONArray jsonArr = json.getJSONObject(JSON_KEY_RESULT).getJSONArray(JSON_KEY_RESULTS);
            logger.info("results:" + jsonArr.toJSONString());
            List<HashMap<String, Object>> tCreepersTaxEvasion = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < jsonArr.size(); i++) {
                // 提取偷税漏税黑名单信息
                HashMap<String, Object> map = new HashMap<String, Object>();
                String name = jsonArr.getJSONObject(i).getString(JSON_KEY_NAME);
                String code = jsonArr.getJSONObject(i).getString(JSON_KEY_CODE);
                String goodRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_GOOD_COUNT);
                String badRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_BAD_COUNT);
                String disRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_DISHONESTY_COUNT);
                String province = jsonArr.getJSONObject(i).getString(JSON_KEY_AREA);
                String objectType = jsonArr.getJSONObject(i).getString(JSON_KEY_OBJECT_TYPE);
                String entryStr = jsonArr.getJSONObject(i).getString(JSON_KEY_ENCRY_STR);
                map.put(CreepersConstant.TCreepersShixinAllColumn.NAME.getValue(), name);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.NAME.getValue() + SYMBOL_COLON_EN + name);
                map.put(CreepersConstant.TCreepersShixinAllColumn.CODE.getValue(), code);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.CODE.getValue() + SYMBOL_COLON_EN + code);
                map.put(CreepersConstant.TCreepersShixinAllColumn.PROVINCE.getValue(), province);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.PROVINCE.getValue() + SYMBOL_COLON_EN + province);
                map.put(CreepersConstant.TCreepersShixinAllColumn.GOOD_RECORD.getValue(), goodRecord);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.GOOD_RECORD.getValue() + SYMBOL_COLON_EN + goodRecord);
                map.put(CreepersConstant.TCreepersShixinAllColumn.BAD_RECORD.getValue(), badRecord);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.BAD_RECORD.getValue() + SYMBOL_COLON_EN + badRecord);
                map.put(CreepersConstant.TCreepersShixinAllColumn.DIS_RECORD.getValue(), disRecord);
                logger.info(CreepersConstant.TCreepersShixinAllColumn.DIS_RECORD.getValue() + SYMBOL_COLON_EN + disRecord);
                tCreepersTaxEvasion.add(map);
                // 添加链接
                page.addTargetRequest(BASE_URL + ACCESS_URL_CREDIT_DETAIL + JSON_KEY_OBJECT_TYPE + SYMBOL_EQ + objectType + SYMBOL_AND + JSON_KEY_ENCRY_STR + SYMBOL_EQ + entryStr);
                logger.info(BASE_URL + ACCESS_URL_CREDIT_DETAIL + JSON_KEY_OBJECT_TYPE + SYMBOL_EQ + objectType + SYMBOL_AND + JSON_KEY_ENCRY_STR + SYMBOL_EQ + entryStr);
            }
            page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION.getMapKey(), tCreepersTaxEvasion);
            int totalPageCount = null == json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE_COUNT) ? 0 : Integer.parseInt(json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE_COUNT));
            int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 1 : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
            int pageNo = null == page.getRequest().getExtra(KEY_PAGE_NO) ? 1 : (int) page.getRequest().getExtra(KEY_PAGE_NO);
            NameValuePair[] nameValuePairs = (NameValuePair[]) page.getRequest().getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
            if (pageNo == 1) {
                for (int j = 1; j <= threadNum; j++) {
                    if (pageNo + j <= totalPageCount) {
                        Request request = new Request(BASE_URL + ACCESS_URL_CREDIT_SEARCH + new Date().getTime());
                        request.setMethod(HttpConstant.Method.POST);
                        NameValuePair[] newNameValuePairs = copyAndModifyNameValuePair(nameValuePairs, "page", pageNo + j + "");
                        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, newNameValuePairs);
                        request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                        request.putExtra(KEY_PAGE_NO, pageNo + j);
                        request.putExtra(KEY_THREAD_NUM, threadNum);
                        page.addTargetRequest(request);
                        logger.info("=============>添加request：" + request);
                    }
                }
            } else {
                if (pageNo + threadNum <= totalPageCount) {
                    Request request = new Request(BASE_URL + ACCESS_URL_CREDIT_SEARCH + new Date().getTime());
                    request.setMethod(HttpConstant.Method.POST);
                    modifySpecNameValuePair(nameValuePairs, "page", pageNo + threadNum + "");
                    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePairs);
                    request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                    request.putExtra(KEY_PAGE_NO, pageNo + threadNum);
                    request.putExtra(KEY_THREAD_NUM, threadNum);
                    page.addTargetRequest(request);
                    logger.info("=============>添加request：" + request);
                }
            }
            logger.info("======================>>>  偷税漏税黑名单查询   end!");
        } else if (page.getUrl().regex(BASE_URL + ACCESS_URL_CREDIT_DETAIL).match()) {
            logger.info("======================>>> 失信信息 start!");
            List<Selectable> list = page.getHtml().xpath(XPATH_DIV_TARGET_DATA).nodes();
            for (int i = 0; i < list.size(); i++) {
                if (0 == i) {
                    logger.info("===========暂不录入基础信息===========");
                } else if (1 == i) {
                    logger.info("===========暂不录入优良信息===========");
                } else if (2 == i) {
                    if (!list.get(i).regex(REGEX_TIP).match())
                        getNegativeInfo(page, list.get(i));
                } else if (3 == i) {
                    logger.info("===========暂不录入受惩黑名单信息===========");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("TaxEvasionProcessor process error:", e);
        param.setErrorInfo("TaxEvasionProcessor process error:" + e.getMessage());
        param.setErrorPath(getClass().getName().toString());
        creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        logger.error("======================>>>  TaxEvasionProcessor end!");
    }
}


public void modifySpecNameValuePair(NameValuePair[] nameValuePairs,String key,String value){
    if (null != nameValuePairs && nameValuePairs.length > 0 && StringUtils.isNoneBlank(key) && StringUtils.isNoneBlank(value)) {
        for (int i = 0; i < nameValuePairs.length; i++) {
            if (key.equals(nameValuePairs[i].getName())) {
                nameValuePairs[i] = new BasicNameValuePair(key, value);
            }
        }
    }
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


public void getNegativeInfo(Page page,Selectable divSel){
    // 创建存储数据结构
    List<HashMap<String, Object>> tCreepersTaxEvasionDetail = new ArrayList<HashMap<String, Object>>();
    List<Selectable> ulList = divSel.xpath(XPATH_UL).nodes();
    for (Selectable ulSel : ulList) {
        logger.info("===========负面记录============");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Selectable> liList = ulSel.xpath("//li").nodes();
        for (Selectable liSel : liList) {
            String columnName = liSel.xpath(XPATH_LI + XPATH_STRONG + XPATH_TEXT).toString().replace(SYMBOL_COLON_EN, SYMBOL_EMPTY).replace(SYMBOL_COLON_CN, SYMBOL_EMPTY).trim();
            String columnValue = liSel.xpath(XPATH_LI + XPATH_TEXT).toString().trim();
            if (SHI_XIN_COLUMN_SOURCE.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.SOURCE.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_SOURCE + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.SOURCE.getValue()));
            } else if (SHI_XIN_COLUMN_TYPE.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.TYPE.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_TYPE + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.TYPE.getValue()));
            } else if (SHI_XIN_COLUMN_PROVINCE.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PROVINCE.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_PROVINCE + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PROVINCE.getValue()));
            } else if (SHI_XIN_COLUMN_COURT.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.COURT.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_COURT + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.COURT.getValue()));
            } else if (SHI_XIN_COLUMN_NAME.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.NAME.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_NAME + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.NAME.getValue()));
            } else if (SHI_XIN_COLUMN_TAX_NO.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.TAX_NO.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_TAX_NO + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.TAX_NO.getValue()));
            } else if (SHI_XIN_COLUMN_CODE.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CODE.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_CODE + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CODE.getValue()));
            } else if (SHI_XIN_COLUMN_ADDR.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.ADDR.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_ADDR + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.ADDR.getValue()));
            } else if (SHI_XIN_COLUMN_LEGAL_REP.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_REP.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_LEGAL_REP + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_REP.getValue()));
            } else if (SHI_XIN_COLUMN_LEGAL_SEX.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_SEX.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_LEGAL_SEX + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_SEX.getValue()));
            } else if (SHI_XIN_COLUMN_CERT_NAME.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CERT_NAME.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_CERT_NAME + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CERT_NAME.getValue()));
            } else if (SHI_XIN_COLUMN_FINANCIAL_NAME.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_NAME.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_FINANCIAL_NAME + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_NAME.getValue()));
            } else if (SHI_XIN_COLUMN_FINANCIAL_SEX.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_SEX.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_FINANCIAL_SEX + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_SEX.getValue()));
            } else if (SHI_XIN_COLUMN_FINANCIAL_CERT_NAME.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_CERT_NAME.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_FINANCIAL_CERT_NAME + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_CERT_NAME.getValue()));
            } else if (SHI_XIN_COLUMN_INTER_INFO.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.INTER_INFO.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_INTER_INFO + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.INTER_INFO.getValue()));
            } else if (SHI_XIN_COLUMN_CASE_NATURE.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CASE_NATURE.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_CASE_NATURE + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CASE_NATURE.getValue()));
            } else if (SHI_XIN_COLUMN_ILLEGAL_FACTS.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.ILLEGAL_FACTS.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_ILLEGAL_FACTS + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.ILLEGAL_FACTS.getValue()));
            } else if (SHI_XIN_COLUMN_PUNISH_INFO.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUNISH_INFO.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_PUNISH_INFO + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUNISH_INFO.getValue()));
            } else if (SHI_XIN_COLUMN_PUBLISH_LEVEL.equals(columnName)) {
                map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUBLISH_LEVEL.getValue(), columnValue);
                logger.info(SHI_XIN_COLUMN_PUBLISH_LEVEL + SYMBOL_COLON_EN + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUBLISH_LEVEL.getValue()));
            } else {
                logger.info(columnName + SYMBOL_COLON_CN + columnValue + "，该值暂未录入数据库");
            }
        }
        tCreepersTaxEvasionDetail.add(map);
    }
    page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION_DETAIL.getMapKey(), tCreepersTaxEvasionDetail);
}


@Override
public Site getSite(){
    if (null == site) {
        site = Site.me().setDomain(BASE_URL).setTimeOut(30000).setUserAgent(USER_AGENT);
    }
    return site;
}


public void main(String[] args){
    String url = "http://www.creditchina.gov.cn/credit_info_search?t=" + new Date().getTime();
    Request request = new Request(url);
    request.setMethod(HttpConstant.Method.POST);
    NameValuePair[] nameValuePair = new NameValuePair[10];
    nameValuePair[0] = new BasicNameValuePair("keyword", "");
    nameValuePair[1] = new BasicNameValuePair("searchtype", "0");
    nameValuePair[2] = new BasicNameValuePair("objectType", "2");
    nameValuePair[3] = new BasicNameValuePair("areas", "");
    nameValuePair[4] = new BasicNameValuePair("creditType", "4");
    nameValuePair[5] = new BasicNameValuePair("dataType", "1");
    nameValuePair[6] = new BasicNameValuePair("areaCode", "");
    nameValuePair[7] = new BasicNameValuePair("templateId", "6");
    nameValuePair[8] = new BasicNameValuePair("exact", "0");
    nameValuePair[9] = new BasicNameValuePair("page", "1");
    request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
    Spider.create(new TaxEvasionProcessor()).setDownloader(new HttpRequestDownloader()).thread(1).addRequest(request).run();
}


}