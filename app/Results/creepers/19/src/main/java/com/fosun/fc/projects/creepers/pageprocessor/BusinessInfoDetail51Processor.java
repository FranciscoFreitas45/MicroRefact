package com.fosun.fc.projects.creepers.pageprocessor;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
@Component("businessInfoDetail51Processor")
public class BusinessInfoDetail51Processor extends BaseBusinessDetailProcessorimplements PageProcessor{

 private  Logger logger;

 private  Site site;

 private  String XPATH_ID_DIV_JI_BEN_XIN_XI;

 private  String XPATH_ID_DIV_BEI_AN;

 private  String XPATH_ID_DIV_XZCF;

 private  String XPATH_ID_DIV_GU_QUAN_CHU_ZHI;

 private  String XPATH_ID_DIV_DONG_CHAN_DI_YA;

 private  String XPATH_ID_DIV_YI_CHANG_MING_LU;

 private  String XPATH_ID_DIV_CHOU_CHA_XIN_XI;

 private  String XPATH_ID_DIV_YAN_ZHONG_WEI_FA;

 private  String XPATH_ID_DIV_SI_FA_PAN_DING;

 private  String XPATH_ID_DIV_QYNB;

 private  String XPATH_ID_DIV_TZRBGXX;

 private  String XPATH_ID_DIV_TZRXX;

 private  String XPATH_ID_DIV_DETAILS;

 private  String XPATH_ID_TABLE_FR;

 private  String XPATH_ID_TABLE_BG;

 private  String XPATH_ID_TABLE_RY1;

 private  String XPATH_ID_TABLE_FR2;

 private  String XPATH_ID_TABLE_GSCFXX;

 private  String XPATH_ID_TABLE_GQ;

 private  String XPATH_ID_TABLE_DC;

 private  String XPATH_ID_TABLE_YC;

 private  String XPATH_ID_TABLE_CCJC;

 private  String XPATH_ID_TABLE_WFXX;

 private  String XPATH_ID_TABLE_TZRBGXX;

 private  String XPATH_ID_TABLE_QYTZR;

 private  String XPATH_ID_TABLE_TZRXXBG;

 private  String XPATH_ID_TABLE_WZXX;

 private  String XPATH_ID_TABLE_TZRXX;

 private  String XPATH_ID_TABLE_TZXX;

 private  String XPATH_ID_TABLE_DBXX;

 private  String XPATH_ID_TABLE_GQBG;

 private  String XPATH_ID_TABLE_BGXX;

 private  String XPATH_NAME_TR_FR;

 private  String XPATH_NAME_TR_BG;

 private  String XPATH_NAME_TR_RY1;

 private  String XPATH_NAME_TR_GSCFXX;

 private  String XPATH_NAME_TR_YC;

 private  String XPATH_NAME_TR_CCJC;

 private  String XPATH_NAME_TR_WFXX;

 private  String XPATH_NAME_TR_QYTZR;

 private  String XPATH_NAME_TR_TZRXXBG;

 private  String REGEX_URL_BASE;

 private  String REGEX_URL_ID_0;

 private  String REGEX_URL_ID_2;

 private  String REGEX_URL_ID_3;

 private  String REGEX_URL_ID_4;

 private  String REGEX_URL_ID_5;

 private  String REGEX_URL_ID_6;

 private  String REGEX_URL_ID_7;

 private  String REGEX_URL_ID_8;

 private  String REGEX_URL_ID_9;

 private  String REGEX_URL_ID_10;

 private  String REGEX_URL_ID_11;

 private  String REGEX_URL_ID_30;

 private  String REGEX_URL_ID_31;

 private  String REGEX_URL_ID_32;

 private  String REGEX_URL_ID_33;

 private  String REGEX_URL_ID_GS2;

 private  String REGEX_URL_ID_GS3;

 private  String REGEX_URL_ID_GS4;

 private  String REGEX_URL_ID_GS5;

 private  String REGEX_URL_ID_NDBG;

 private  String REGEX_URL_ID_QYGS_FOR_TZRXX;

 private  String REGEX_URL_ID_QYGS_FOR_TZRBGXX;

 private  String REGEX_URL_GET_MAENT_PRIPID;

 private  String REGEX_CONTENT_MER_NO;

 private  String REPLACE_FUNCTION_DO_XZFY_DETAIL;

 private  String REPLACE_FUNCTION_DO_NDBG;

 private  String APPEND_URL_METHOD_DCDY_DETAIL;

 private  String APPEND_URL_METHOD_DO_XZFY_DETAIL;

 private  String APPEND_URL_METHOD_NDBG_DETAIL;

public BusinessInfoDetail51Processor() {
    if (null == site) {
        site = Site.me().setSleepTime(1000).setTimeOut(100000);
    }
}
public void method5(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method5!");
    Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_DC);
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    int trNum = 1;
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey());
    for (Selectable tr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = tr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 8) {
            logger.info("=============>>>???????????????8???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(2).toString())) {
            map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()));
        }
        // ????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(6).toString())) {
            map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));
        }
        // ??????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue(), method5GetMerPropertyDetailURL(map));
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.DETAILS.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("entry method5!");
}


public void methodNdbgEquity(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgEquity:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
            logger.info("=============>>>???????????????4???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(3).toString())) {
            map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgEquity:");
}


public void method6(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method6!");
    Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_YC);
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_YC).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()));
        // ??????????????????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(3).toString())) {
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()));
        }
        // ??????????????????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(4).toString())) {
            map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()));
        }
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish method6!");
}


public void method7(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method7!");
    Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_CCJC);
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_CCJC).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHECK.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()));
        // ??????
        if (StringUtil.isNotBlank(tdList.get(3).toString())) {
            map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()));
        }
        // ??????
        map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()));
        // ??????????????????
        map.putAll(merNoMap);
        list.add(map);
    }
    logger.info("finish method7!");
}


public void method8(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_WFXX);
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_WFXX).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 6) {
            logger.info("=============>>>???????????????6???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()));
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(2).toString())) {
            map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()));
        }
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(4).toString())) {
            map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()));
        }
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()));
        list.add(map);
    }
}


public void methodQygsForTzrxxInfo(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    // XPATH_ID_TABLE_TZRBGXX
    logger.info("entry methodQygsForTzrxxInfo!");
    // ???????????????????????????
    methodQygsForTzrxxInfoDetail(divSelectable.xpath(XPATH_ID_TABLE_QYTZR), resultItems, merNoMap);
    // ??????????????????
    methodQygsForTzrxxInfoChange(divSelectable.xpath(XPATH_ID_TABLE_TZRXXBG), resultItems, merNoMap);
    logger.info("finish methodQygsForTzrxxInfo!");
}


public void method1(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method1!");
    // logger.info(selectable.toString());
    // ????????????
    merBaseInfo(divSelectable.xpath(XPATH_TABLE).nodes().get(0), resultItems, merNoMap);
    // ????????????
    method1Shareholder(divSelectable.xpath(XPATH_ID_TABLE_FR), resultItems, merNoMap);
    // ????????????
    method1ChangeInfo(divSelectable.xpath(XPATH_ID_TABLE_BG), resultItems, merNoMap);
    logger.info("finish method1!");
}


public void method2(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method2!");
    // ??????????????????
    method2KeyMan(divSelectable.xpath(XPATH_ID_TABLE_RY1), resultItems, merNoMap);
    // ??????????????????
    method2Branch(divSelectable.xpath(XPATH_ID_TABLE_FR2), resultItems, merNoMap);
    logger.info("finish method2!");
}


public void methodNdbgWarrant(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgWarrant:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WARRANT.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 7) {
            logger.info("=============>>>???????????????7???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ?????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue(), tdList.get(7).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgWarrant:");
}


public void method3(Selectable selectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method3!");
    Selectable tableSelectable = selectable.xpath(XPATH_ID_TABLE_GSCFXX);
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_GSCFXX).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 8) {
            logger.info("=============>>>???????????????8???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()));
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()));
        // ??????????????????????????????
        if (StringUtil.isNotBlank(tdList.get(5).toString())) {
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()));
        }
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(6).toString())) {
            map.put(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue(), tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()));
        }
        // ??????
        String detail = eachTr.xpath(XPATH_TD).nodes().get(7).xpath(XPATH_A_ONCLICK).toString();
        String detailURL = method3GetPenaltyDetailURL(detail);
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue(), detailURL);
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish method3!");
}


public void method4(Selectable selectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry method4!");
    List<Selectable> trList = selectable.xpath(XPATH_ID_TABLE_GQ + XPATH_TR).nodes();
    int trNum = 1;
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey());
    for (Selectable tr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = tr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 11) {
            logger.info("=============>>>???????????????11???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()));
        // ??????/????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue()));
        // ??????/????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()));
        // ??????????????????????????????
        if (StringUtil.isNotBlank(tdList.get(7).toString())) {
            map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue(), tdList.get(7).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()));
        }
        // ??????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue(), tdList.get(8).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(9).toString())) {
            map.put(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue(), tdList.get(9).toString());
            logger.info(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()));
        }
        // ????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue(), tdList.get(10).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish method4!");
}


public String methodQygsQynbGetNdbgUrl(String ndbgParam,String pageUrl){
    String param = ndbgParam.replace(REPLACE_FUNCTION_DO_NDBG + SPILT_APOSTROPHE, CONTENT_EMPTY).replace(SPILT_APOSTROPHE + REPLACE_FUNCTION_END, CONTENT_EMPTY);
    StringBuffer result = new StringBuffer();
    result.append(REGEX_URL_BASE);
    result.append(APPEND_URL_METHOD_NDBG_DETAIL);
    result.append(CommonMethodUtils.matchesValue(REGEX_URL_GET_MAENT_PRIPID, pageUrl.replace(SPILT_QUESTION, REPLACE_QUESTION)));
    result.append(APPEND_URL_MAENT_ND);
    result.append(param);
    result.append(APPEND_URL_RANDOM);
    result.append(new Date().getTime());
    return result.toString();
}


public void main(String[] args){
    Spider.create(new BusinessInfoDetail51Processor()).addPipeline(new BusinessInfoDetailPipline()).addUrl("http://gsxt.scaic.gov.cn/ztxy.do?method=ndbgDetail&maent.pripid=CD6675400159&maent.nd=2015&random=" + new Date().getTime()).thread(1).runAsync();
}


public void methodNdbgShare(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgShare:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 7) {
            logger.info("=============>>>???????????????7???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(2).toString())) {
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        }
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(5).toString())) {
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        }
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgShare:");
}


public String method5GetMerPropertyDetailURL(Map<String,String> map){
    StringBuffer result = new StringBuffer();
    result.append(REGEX_URL_BASE);
    result.append(APPEND_URL_METHOD_DCDY_DETAIL);
    result.append(map.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));
    result.append(APPEND_URL_MAENT_XH);
    result.append(map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));
    result.append(APPEND_URL_RANDOM);
    result.append(new Date().getTime());
    return result.toString();
}


public void methodQygsForTzrxxInfoDetail(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry methodQygsForTzrxxInfoDetail!");
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_QYTZR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 11) {
            logger.info("=============>>>???????????????11???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ?????? T_CREEPERS_ENT_SHARE
        map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(5).toString())) {
            map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        }
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(6).toString())) {
            // map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(),
            // tdList.get(6).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        }
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(7).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(8).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(9).toString())) {
            map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(9).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        }
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(10).toString())) {
            // map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(),
            // tdList.get(10).toString());
            logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish methodQygsForTzrxxInfoDetail!");
}


public String method3GetPenaltyDetailURL(String detail){
    logger.info("entry getPenaltyDetailURL!");
    String[] arrStr = detail.replace(REPLACE_FUNCTION_DO_XZFY_DETAIL + SPILT_APOSTROPHE, CONTENT_EMPTY).replace(SPILT_APOSTROPHE + REPLACE_FUNCTION_END, CONTENT_EMPTY).split(SPILT_COMMA);
    StringBuffer result = new StringBuffer();
    result.append(REGEX_URL_BASE);
    result.append(APPEND_URL_METHOD_DO_XZFY_DETAIL);
    result.append(arrStr[0]);
    result.append(APPEND_URL_MAENT_XH);
    result.append(arrStr[1]);
    result.append(APPEND_URL_RANDOM);
    result.append(new Date().getTime());
    logger.info("finish getPenaltyDetailURL!");
    return result.toString();
}


public void method1ChangeInfo(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   method1ChangeInfo:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_BG).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey());
    for (Selectable eachTr : trList) {
        // logger.info(eachTr.toString());
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
            logger.info("=============>>>???????????????4???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(3).toString())) {
            map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
            logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()));
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   method1ChangeInfo");
}


public void methodNdbg(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry methodNdbg!");
    // ??????????????????
    entNdbgBasic(divSelectable.xpath(XPATH_TABLE).nodes().get(0), resultItems, merNoMap);
    // ?????????????????????
    methodNdbgWeb(divSelectable.xpath(XPATH_ID_TABLE_WZXX), resultItems, merNoMap);
    // ?????????????????????
    methodNdbgShare(divSelectable.xpath(XPATH_ID_TABLE_TZRXX), resultItems, merNoMap);
    // ??????????????????
    methodNdbgInvest(divSelectable.xpath(XPATH_ID_TABLE_TZXX), resultItems, merNoMap);
    // ????????????????????????
    entNdbgAsset(divSelectable.xpath(XPATH_TABLE).nodes().get(4), resultItems, merNoMap);
    // ??????????????????????????????
    methodNdbgWarrant(divSelectable.xpath(XPATH_ID_TABLE_DBXX), resultItems, merNoMap);
    // ??????????????????
    methodNdbgEquity(divSelectable.xpath(XPATH_ID_TABLE_GQBG), resultItems, merNoMap);
    // ????????????
    methodNdbgChange(divSelectable.xpath(XPATH_ID_TABLE_BGXX), resultItems, merNoMap);
    logger.info("finish methodNdbg!");
}


public void methodNdbgInvest(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgInvest:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INVEST.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 2) {
            logger.info("=============>>>???????????????2???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ?????????????????????????????????????????????
        map.put(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()));
        // ?????????/????????????????????????
        map.put(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgInvest:");
}


public void methodNdbgWeb(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgWeb:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 3) {
            logger.info("=============>>>???????????????3???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersEntWebColumn.NAME.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersEntWebColumn.URL.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.URL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.URL.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgWeb:");
}


@Override
public void process(Page page){
    String merNo = page.getHtml().xpath(XPATH_ID_DIV_DETAILS + XPATH_ALL_TEXT).regex(REGEX_CONTENT_MER_NO).toString().replaceAll(REPLACE_CONTENT_NBSP + SPILT_SEMICOLON, CONTENT_EMPTY).trim();
    Map<String, String> merNoMap = new HashMap<String, String>();
    merNoMap.put(CreepersConstant.TCreepersListColumn.MER_NO.getValue(), merNo);
    logger.info(CreepersConstant.TCreepersListColumn.MER_NO.getValue() + SPILT_COLON + merNoMap.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));
    page.putField(PAGE_URL, page.getUrl().toString());
    // ?????????putField
    TableNamesBusinessInfo[] getNames = CreepersConstant.TableNamesBusinessInfo.values();
    for (TableNamesBusinessInfo tableNamesBusinessInfo : getNames) {
        Object obj;
        if (tableNamesBusinessInfo.isList()) {
            obj = new ArrayList<Map<String, Object>>();
        } else {
            obj = new HashMap<String, Object>();
        }
        page.putField(tableNamesBusinessInfo.getMapKey(), obj);
    }
    ResultItems resultItems = page.getResultItems();
    if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_0).match()) {
        // ???????????? id:0 ?????????????????????????????? ?????????????????? id:gs1 ???????????????????????????url
        logger.info("======================>>>method 1!  ????????????   start");
        method1(page.getHtml().xpath(XPATH_ID_DIV_JI_BEN_XIN_XI), resultItems, merNoMap);
        logger.info("======================>>>method 1!  ????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_2).match()) {
        // ???????????? id:2
        logger.info("======================>>>method 2!  ????????????   start");
        method2(page.getHtml().xpath(XPATH_ID_DIV_BEI_AN), resultItems, merNoMap);
        logger.info("======================>>>method 2!  ????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_3).match()) {
        // ?????????????????? id:3
        logger.info("======================>>>method 3!  ?????????????????? start");
        method3(page.getHtml().xpath(XPATH_ID_DIV_XZCF), resultItems, merNoMap);
        logger.info("======================>>>method 3!  ?????????????????? finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_4).match()) {
        // ???????????????????????? id:4
        logger.info("======================>>>method 4!  ????????????????????????   start");
        method4(page.getHtml().xpath(XPATH_ID_DIV_GU_QUAN_CHU_ZHI), resultItems, merNoMap);
        logger.info("======================>>>method 4!  ????????????????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_5).match()) {
        // ???????????????????????? id:5
        logger.info("======================>>>method 5!  ????????????????????????   start");
        method5(page.getHtml().xpath(XPATH_ID_DIV_DONG_CHAN_DI_YA), resultItems, merNoMap);
        logger.info("======================>>>method 5!  ????????????????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_6).match()) {
        // ?????????????????? id:6
        logger.info("======================>>>method 6!  ?????????????????? start");
        method6(page.getHtml().xpath(XPATH_ID_DIV_YI_CHANG_MING_LU), resultItems, merNoMap);
        logger.info("======================>>>method 6!  ?????????????????? finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_7).match()) {
        // ?????????????????? id:7
        logger.info("======================>>>method 7!  ?????????????????? start");
        method7(page.getHtml().xpath(XPATH_ID_DIV_CHOU_CHA_XIN_XI), resultItems, merNoMap);
        logger.info("======================>>>method 7!  ?????????????????? finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_8).match()) {
        // ?????????????????? id:8
        logger.info("======================>>>method 8!  ?????????????????? start");
        method8(page.getHtml().xpath(XPATH_ID_DIV_YAN_ZHONG_WEI_FA), resultItems, merNoMap);
        logger.info("======================>>>method 8!  ?????????????????? finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_9).match()) {
        // id:9
        logger.info("======================>>>method 9!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_10).match()) {
        // id:10
        logger.info("======================>>>method 10!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_11).match()) {
        // id:11
        logger.info("======================>>>method 11!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_30).match()) {
        // id:30
        logger.info("======================>>>method 30!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_31).match()) {
        // id:31
        logger.info("======================>>>method 31!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_32).match()) {
        // id:32
        logger.info("======================>>>method 32!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_33).match()) {
        // id:33
        logger.info("======================>>>method 33!  ");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS2).match()) {
        // ???????????? id:qygs_qynb ?????????????????? id:gs2 ???????????????????????????url
        logger.info("======================>>>method gs2!  ??????????????????   start");
        methodQygsQynb(page.getHtml().xpath(XPATH_ID_DIV_QYNB + XPATH_ID_DIV_SI_FA_PAN_DING), page, merNoMap);
        logger.info("======================>>>method gs2!  ??????????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_QYGS_FOR_TZRXX).match()) {
        // ????????????????????? id:qygsForTzrxxInfo
        logger.info("======================>>>method qygsForTzrxxInfo!  ?????????????????????   start");
        methodQygsForTzrxxInfo(page.getHtml().xpath(XPATH_ID_DIV_TZRXX), resultItems, merNoMap);
        logger.info("======================>>>method qygsForTzrxxInfo!  ?????????????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_QYGS_FOR_TZRBGXX).match()) {
        // ?????????????????? id:qygsForTzrbgxxInfo
        logger.info("======================>>>method qygsForTzrbgxxInfo!  ??????????????????   start");
        methodQygsForTzrbgxxInfo(page.getHtml().xpath(XPATH_ID_DIV_TZRBGXX), resultItems, merNoMap);
        logger.info("======================>>>method qygsForTzrbgxxInfo!  ??????????????????    finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_NDBG).match()) {
        // ????????????:
        logger.info("======================>>>method ndbg!  ????????????   start");
        methodNdbg(page.getHtml().xpath(XPATH_ID_DIV_SI_FA_PAN_DING), resultItems, merNoMap);
        logger.info("======================>>>method ndbg!  ????????????   finish");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS3).match()) {
        // ???????????????????????? id:gs3 ???????????????????????????url
        logger.info("======================>>>method gs3!  ????????????????????????");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS4).match()) {
        // ???????????????????????? id:gs4 ???????????????????????????url
        logger.info("======================>>>method gs4!  ????????????????????????");
    } else if (page.getUrl().regex(REGEX_URL_BASE + REGEX_URL_ID_GS5).match()) {
        // id:gs5
        logger.info("======================>>>method gs5!  ");
    }
}


public void methodNdbgChange(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   methodNdbgChange:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()));
        // ?????????
        if (tdList.get(2).toString().contains(CONTENT_MORE)) {
            map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(), eachTr.xpath(XPATH_TD).nodes().get(2).xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes().get(1).toString().replace(CONTENT_MORE, CONTENT_EMPTY));
        } else {
            map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(), tdList.get(2).toString());
        }
        logger.info(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()));
        // ?????????
        if (tdList.get(3).toString().contains(CONTENT_MORE)) {
            map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(), eachTr.xpath(XPATH_TD).nodes().get(3).xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes().get(1).toString().replace(CONTENT_MORE, CONTENT_EMPTY));
        } else {
            map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(), tdList.get(3).toString());
        }
        logger.info(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()));
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(4).toString())) {
            map.put(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()));
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   finish   methodNdbgChange:");
}


public void method2Branch(Selectable selectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry  method2Branch!");
    List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BRANCH.getMapKey());
    int rowNum = 1;
    for (Selectable eachTr : trList) {
        if (rowNum < 3) {
            rowNum++;
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 4) {
            logger.info("=============>>>???????????????4???td ????????????:" + tdList);
            continue;
        }
        // ??????
        map.put(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()));
        // ?????????/????????????????????????
        map.put(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish  method2Branch!");
}


public void method1Shareholder(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   method1Shareholder:");
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_FR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey());
    for (Selectable eachTr : trList) {
        // logger.info(eachTr.toString());
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()));
        // ??????/????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()));
        // ??????/????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   method1Shareholder:");
}


@Override
public Site getSite(){
    return site;
}


public void methodQygsForTzrbgxxInfo(Selectable divSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    // XPATH_ID_TABLE_TZRBGXX
    logger.info("entry methodQygsForTzrbgxxInfo!");
    Selectable tableSelectable = divSelectable.xpath(XPATH_ID_TABLE_TZRBGXX);
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 6) {
            logger.info("=============>>>???????????????6???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        // map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(),
        // tdList.get(0).toString());
        // logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()
        // + SPILT_COLON +
        // map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));
        // ??????????????????
        if (StringUtil.isNotBlank(tdList.get(4).toString())) {
            map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
        }
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(5).toString())) {
            map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(5).toString());
            logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish methodQygsForTzrbgxxInfo!");
}


public void methodQygsQynb(Selectable divSelectable,Page page,Map<String,String> merNoMap){
    logger.info("entry methodQygs_qynb!");
    Selectable tableSelectable = divSelectable.xpath(XPATH_TABLE);
    List<Selectable> trList = tableSelectable.xpath(XPATH_TR).nodes();
    int trNum = 1;
    for (Selectable eachTr : trList) {
        if (trNum < 3) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = eachTr.xpath(XPATH_TD).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 3) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        // ??????
        logger.info("?????????" + tdList.get(0).toString());
        // ????????????
        String ndbgParam = tdList.get(1).xpath(XPATH_A_ONCLICK).toString();
        // ????????????URL
        String ndbgUrl = methodQygsQynbGetNdbgUrl(ndbgParam, page.getUrl().toString());
        logger.info("???????????????" + ndbgUrl);
        page.addTargetRequest(ndbgUrl);
        // ????????????
        logger.info("???????????????" + tdList.get(2).toString());
    }
    logger.info("finish methodQygs_qynb!");
}


public void methodQygsForTzrxxInfoChange(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry methodQygsForTzrxxInfoDetail!");
    List<Selectable> trList = tableSelectable.xpath(XPATH_NAME_TR_TZRXXBG).nodes();
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE_CHANGE.getMapKey());
    for (Selectable eachTr : trList) {
        List<Selectable> tdList = eachTr.xpath(XPATH_TD_ALLTEXT).nodes();
        if (CommonMethodUtils.isEmpty(tdList) || tdList.size() < 5) {
            logger.info("=============>>>???????????????5???td ????????????:" + tdList);
            continue;
        }
        Map<String, String> map = new HashMap<String, String>();
        // ??????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()));
        list.add(map);
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()));
        list.add(map);
        // ????????????
        if (StringUtil.isNotBlank(tdList.get(10).toString())) {
            map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue(), tdList.get(2).toString());
            logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()));
        }
        // ?????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("finish methodQygsForTzrxxInfoDetail!");
}


public void method2KeyMan(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("entry  method2KeyMan!");
    List<Selectable> tdList = tableSelectable.xpath(XPATH_NAME_TR_RY1 + XPATH_TD_ALLTEXT).nodes();
    Map<String, String> map = new HashMap<String, String>();
    map.putAll(merNoMap);
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
    int rowNum = 0;
    for (Selectable eachTd : tdList) {
        logger.info("current content:" + eachTd.toString());
        rowNum++;
        if (rowNum == 1) {
            // ??????
            map.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(), eachTd.toString());
            logger.info(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()));
        } else if (rowNum == 2) {
            // ??????
            map.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(), eachTd.toString());
            logger.info(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()));
        } else if (rowNum == 3) {
            // ??????
            map.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(), eachTd.toString());
            logger.info(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()));
            logger.info(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()));
            Map<String, String> temp = new HashMap<String, String>();
            temp.putAll(map);
            list.add(temp);
            rowNum = 0;
        }
    }
    logger.info("finish  method2KeyMan!");
}


}