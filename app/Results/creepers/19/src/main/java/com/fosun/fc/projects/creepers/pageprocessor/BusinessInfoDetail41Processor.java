package com.fosun.fc.projects.creepers.pageprocessor;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesBusinessInfo;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoDetailPipline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
@Component("businessInfoDetail41Processor")
public class BusinessInfoDetail41Processor extends BaseBusinessDetailProcessorimplements PageProcessor{

 private  Logger logger;

 private  Site site;

 private  String XPATH_ID_DIV_JI_BEN_XIN_XI;

 private  String XPATH_ID_DIV_BEI_AN;

 private  String XPATH_ID_DIV_DONG_CHAN_DI_YA;

 private  String XPATH_ID_DIV_GU_QUAN_CHU_ZHI;

 private  String XPATH_ID_DIV_XING_ZHENG_CHU_FA;

 private  String XPATH_ID_DIV_JING_YING_YI_CHANG_MINGLU;

 private  String XPATH_ID_DIV_YAN_ZHONG_WEI_FA_QI_YE;

 private  String XPATH_ID_DIV_CHOU_CHA_XIN_XI;

 private  String XPATH_ID_DIV_QI_YE_NIAN_BAO;

 private  String XPATH_ID_DIV_SI_FA_PAN_DING;

 private  String XPATH_ID_DIV_TOU_ZI_REN;

 private  String XPATH_ID_DIV_GU_DONG_GU_QUAN;

 private  String XPATH_ID_DIV_GQBG;

 private  String XPATH_PROPERTY_ONCLICK;

 private  String XPATH_PROPERTY_HREF;

 private  String XPATH_PROPERTY_ID;

 private  String XPATH_DIV;

 private  String XPATH_TABLE;

 private  String XPATH_TR1;

 private  String XPATH_TR2;

 private  String XPATH_TH;

 private  String XPATH_A;

 private  String XPATH_HTML;

 private  String BASE_URL;

 private  String REGEX_URL_BUSINESS_PUBLICITY;

 private  String REGEX_URL_ENTERPRISE_PUBLICITY;

 private  String REGEX_URL__OTHER_DEPARTMENT;

 private  String REGEX_URL_JUSTICE_ASSISTANCE;

 private  String REGEX_URL_PAGINATION_0;

 private  String REGEX_URL_PAGINATION_1;

 private  String REGEX_URL_PAGINATION_2;

 private  String REGEX_URL_PAGINATION_3;

 private  String REGEX_URL_PAGINATION_4;

 private  String REGEX_URL_PAGINATION_5;

 private  String REGEX_URL_PAGINATION_6;

 private  String REGEX_URL_PAGINATION_7;

 private  String REGEX_URL_PAGINATION_8;

 private  String REGEX_URL_PAGINATION_9;

 private  String REGEX_URL_PAGINATION_10;

 private  String REGEX_NEXT;

 private  String LINK_ENTERPRISE_PUBLICITY;

 private  String LINK_OTHER_DEPARTMENT;

 private  String LINK_JUSTICE_ASSISTANCE;

 private  String LINK_SHARE_HOLDER;

 private  String LINK_CHANGE;

 private  String LINK_KEY_MEN;

 private  String LINK_BRANCH;

 private  String LINK_PROPERTY;

 private  String LINK_PLEDGE;

 private  String LINK_PENALTY;

 private  String LINK_ABNORMAL;

 private  String LINK_ILLEGAL;

 private  String LINK_CHECK;

 private  String NEXT;

 private  String INVDIV;

 private  String ALTDIV;

 private  String MEMDIV;

 private  String CHILDDIV;

 private  String MORTDIV;

 private  String PLEDGEDIV;

 private  String PUNDIV;

 private  String EXCDIV;

 private  String ILLEGALDIV;

 private  String SPOTCHECKDIV;

 private  String GDDIV;

 private  String ALTINV;

 private  String BASICINFO;

 private  String CLEARINFO;

 private  String ENT_BASIC_INFO;

 private  String ENT_WEB_INFO;

 private  String ENT_SHARE_INFO;

 private  String ENT_INVEST_INFO;

 private  String ENT_ASSET_INFO;

 private  String ENT_WARRANT_INFO;

 private  String ENT_EQUITY_INFO;

 private  String ENT_CHANGE_INFO;

 private  String CONTENT_CREDIT_CODE1;

 private  String CONTENT_CREDIT_CODE2;

 private  String CONTENT_NAME;

 private  String CONTENT_TYPE;

 private  String CONTENT_LEGAL_REPRESENTATIVE1;

 private  String CONTENT_LEGAL_REPRESENTATIVE2;

 private  String CONTENT_LEGAL_REPRESENTATIVE3;

 private  String CONTENT_REGISTERED_CAPITAL;

 private  String CONTENT_CONSTRUCT_DATE1;

 private  String CONTENT_CONSTRUCT_DATE2;

 private  String CONTENT_ADDRESS1;

 private  String CONTENT_ADDRESS2;

 private  String CONTENT_OPERATING_PERIOD_START;

 private  String CONTENT_OPERATING_PERIOD_END;

 private  String CONTENT_BUSINESS_SCOPE;

 private  String CONTENT_REGISTRATION_AUTHORITY;

 private  String CONTENT_AUTHORIZE_DATE;

 private  String CONTENT_REGISTRATION_STATUS;

 private  String CONTENT_COMPANY_NAME;

 private  String CONTENT_COMPANY_PHONE;

 private  String CONTENT_COMPANY_POST_CODE;

 private  String CONTENT_COMPANY_ADDR;

 private  String CONTENT_COMPANY_EMAIL;

 private  String CONTENT_COMPANY_IS_TRANSFER;

 private  String CONTENT_COMPANY_OPERATING_STATUS;

 private  String CONTENT_COMPANY_IS_WEBSITE;

 private  String CONTENT_COMPANY_IS_INVEST;

 private  String CONTENT_COMPANY_CREW_NUMBER;

 private  String CONTENT_COMPANY_TOTAL_ASSET;

 private  String CONTENT_COMPANY_TOTAL_EQUITY;

 private  String CONTENT_COMPANY_TOTAL_AVENUE;

 private  String CONTENT_COMPANY_TOTAL_INCOME;

 private  String CONTENT_COMPANY_TOTAL_BUS_INCOME;

 private  String CONTENT_COMPANY_NET_PROFIT;

 private  String CONTENT_COMPANY_TOTAL_LIABILITIES;

public BusinessInfoDetail41Processor() {
    if (null == site) {
        site = Site.me().setSleepTime(1000).setTimeOut(100000);
    }
}
public void getNdbgInvestInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgInvestInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_INVEST.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 2) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));
        // ???????????????????????????????????????????????????
        map.put(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.INVESTED_NAME.getValue()));
        // ???????????????/????????????????????????
        map.put(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getNdbgInvestInfo:");
}


public List<String> sortColumnName(List<Selectable> webColumnNameList,int columnNum,Map<String,String> matchedColumnNameMap){
    List<String> columnNameList = new ArrayList<String>();
    // ???????????????????????????????????????web????????????????????????
    if (matchedColumnNameMap.size() > 0 && webColumnNameList.size() > 0 && (webColumnNameList.size() / matchedColumnNameMap.size()) == columnNum) {
        int i = 0;
        // ????????????????????????web????????????????????????
        for (Map.Entry<String, String> entry : matchedColumnNameMap.entrySet()) {
            if (entry.getValue().contains(webColumnNameList.get(i).toString())) {
                columnNameList.add(entry.getKey());
            } else {
                logger.info("================>>????????????????????????web??????????????????????????????" + entry.getKey() + ":" + entry.getValue() + ":" + webColumnNameList.get(i).toString() + "<<================");
            }
        }
    } else {
        logger.info("================>>??????????????????????????????web????????????????????????<<================");
    }
    return columnNameList;
}


public void getPledgeInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getPledgeInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PLEDGE.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.SEQ_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.REG_NO.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER.getValue()));
        // ????????????/????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGER_CERT_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_AMOUNT.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGEE_CERT_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue(), tdList.get(7).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.PLEDGE_DT.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue(), tdList.get(8).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.STATUS.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue(), tdList.get(9).toString());
        logger.info("================>>??????????????????????????????????????????memo???<<================");
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MEMO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue(), tdList.get(10).toString());
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.CHANGE_INFO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPledgeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getPledgeInfo:");
}


public void getBranchInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getBranchInfo:");
    // ??????????????????KeyMan?????????????????????
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
    // ???????????????web????????? ??????KeyMan??????
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        Map<String, String> map = new HashMap<String, String>();
        List<Selectable> strList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue() + map.get(CreepersConstant.TCreepersMerBranchColumn.MER_NO.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue(), strList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue() + map.get(CreepersConstant.TCreepersMerBranchColumn.SEQ_NO.getValue()));
        // ?????????/????????????????????????
        map.put(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue(), strList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue() + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_NO.getValue()));
        // ??????
        map.put(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue(), strList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue() + map.get(CreepersConstant.TCreepersMerBranchColumn.NAME.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue(), strList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue() + map.get(CreepersConstant.TCreepersMerBranchColumn.REG_AUTHORITY.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getKeyManInfo:");
}


public void getQygsEquityInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getQygsEquityInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_EQUITY.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ?????????tr???????????????????????????
        if (trNum < 2) {
            trNum++;
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 6) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getQygsEquityInfo:");
}


public void getNdbgWebInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgWebInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 3) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ????????????
        map.put(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.TYPE.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntWebColumn.NAME.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.NAME.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntWebColumn.URL.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntWebColumn.URL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.URL.getValue()));
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntWebColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWebColumn.MEMO.getValue()));
        list.add(map);
        list.add(map);
    }
    logger.info("======>>   end   getNdbgWebInfo:");
}


public void getCheckInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getCheckInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHECK.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.SEQ_NO.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.AUTHORITY.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_TYPE.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_DT.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.CHECK_RESULT.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerCheckColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerCheckColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getCheckInfo:");
}


public void getNdbgEquityInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgEquityInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE_CHANGE.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 4) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.MEMO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.SHARE_HOLDER.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.PRE_RATE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.POST_RATE.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntEquityColumn.CHANGE_DT.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getNdbgEquityInfo:");
}


public void main(String[] args){
    Spider.create(new BusinessInfoDetail41Processor()).addPipeline(new BusinessInfoDetailPipline()).addUrl("http://222.143.24.157/businessPublicity.jspx?id=33D155B8B2AE5FA4E053050A080AED3E").thread(1).runAsync();
// ????????????-->???????????????????????????-->3???????????????
}


public void getNdbgWarrantInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgWarrantInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WARRANT.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 7) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.MEMO.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.CREDITOR.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.OBLIGOR.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_TYPE.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.RIGHTS_AMOUNT.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.DEBT_PERIOD.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_PERIOD.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntWarrantColumn.WARRANT_TYPE.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getNdbgWarrantInfo:");
}


public void getQygsShareInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getQygsShareInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_SHARE.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            trNum++;
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 11) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        logger.info("=============?????????????????????????????????????????????==============");
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(7).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(8).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(9).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(10).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getQygsShareInfo:");
}


public void getClearInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    // ?????????????????????????????????????????????
    logger.info("================>>?????????????????????????????????????????????<<================");
}


public void merBaseInfo(Selectable tableSelectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   merBaseInfo:");
    // logger.info(selectable.toString());
    List<Selectable> tdThList = tableSelectable.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
    boolean isFirstTr = true;
    Map<String, String> map = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_BASIC.getMapKey());
    // ???????????????
    map.putAll(merNoMap);
    logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerBasicColumn.MER_NO.getValue()));
    String lastContent = CONTENT_EMPTY;
    for (Selectable eachTdTh : tdThList) {
        if (isFirstTr) {
            isFirstTr = false;
            continue;
        }
        String currentContent = eachTdTh.xpath("//text(1)").toString();
        logger.info("currentContent:" + currentContent);
        if (CONTENT_CREDIT_CODE1.equals(currentContent) || CONTENT_CREDIT_CODE2.equals(currentContent)) {
            lastContent = CONTENT_CREDIT_CODE1;
            continue;
        } else if (CONTENT_CREDIT_CODE1.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ?????????/????????????????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.MEMO.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.MEMO.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_NAME.equals(currentContent)) {
            lastContent = CONTENT_NAME;
            continue;
        } else if (CONTENT_NAME.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????
            map.put(CreepersConstant.TCreepersMerBasicColumn.MER_NAME.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_NAME.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_TYPE.equals(currentContent)) {
            lastContent = CONTENT_TYPE;
            continue;
        } else if (CONTENT_TYPE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????
            map.put(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_LEGAL_REPRESENTATIVE1.equals(currentContent) || CONTENT_LEGAL_REPRESENTATIVE2.equals(currentContent) || CONTENT_LEGAL_REPRESENTATIVE3.equals(currentContent)) {
            lastContent = CONTENT_LEGAL_REPRESENTATIVE1;
            continue;
        } else if (CONTENT_LEGAL_REPRESENTATIVE1.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ???????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.LEGAL_REP.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.LEGAL_REP.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_REGISTERED_CAPITAL.equals(currentContent)) {
            lastContent = CONTENT_REGISTERED_CAPITAL;
            continue;
        } else if (CONTENT_REGISTERED_CAPITAL.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.REG_CAPITAL.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.REG_CAPITAL.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_CONSTRUCT_DATE1.equals(currentContent) || CONTENT_CONSTRUCT_DATE2.equals(currentContent)) {
            lastContent = CONTENT_CONSTRUCT_DATE1;
            continue;
        } else if (CONTENT_CONSTRUCT_DATE1.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            if (StringUtil.isNotBlank(currentContent)) {
                map.put(CreepersConstant.TCreepersMerBasicColumn.FOUND_DT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.FOUND_DT.getValue() + SPILT_COLON + currentContent);
            }
        } else if (CONTENT_ADDRESS1.equals(currentContent) || CONTENT_ADDRESS2.equals(currentContent)) {
            lastContent = CONTENT_ADDRESS1;
            continue;
        } else if (CONTENT_ADDRESS1.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????
            map.put(CreepersConstant.TCreepersMerBasicColumn.ADDR.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.ADDR.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_OPERATING_PERIOD_START.equals(currentContent)) {
            lastContent = CONTENT_OPERATING_PERIOD_START;
            continue;
        } else if (CONTENT_OPERATING_PERIOD_START.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ???????????????
            if (StringUtil.isNotBlank(currentContent)) {
                map.put(CreepersConstant.TCreepersMerBasicColumn.OPR_START_DT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.OPR_START_DT.getValue() + SPILT_COLON + currentContent);
            }
        } else if (CONTENT_OPERATING_PERIOD_END.equals(currentContent)) {
            lastContent = CONTENT_OPERATING_PERIOD_END;
            continue;
        } else if (CONTENT_OPERATING_PERIOD_END.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ???????????????
            if (StringUtil.isNotBlank(currentContent)) {
                map.put(CreepersConstant.TCreepersMerBasicColumn.OPR_END_DT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.OPR_END_DT.getValue() + SPILT_COLON + currentContent);
            }
        } else if (CONTENT_BUSINESS_SCOPE.equals(currentContent)) {
            lastContent = CONTENT_BUSINESS_SCOPE;
            continue;
        } else if (CONTENT_BUSINESS_SCOPE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.SCOPE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.SCOPE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_REGISTRATION_AUTHORITY.equals(currentContent)) {
            lastContent = CONTENT_REGISTRATION_AUTHORITY;
            continue;
        } else if (CONTENT_REGISTRATION_AUTHORITY.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.REG_AUTHORITY.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.REG_AUTHORITY.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_AUTHORIZE_DATE.equals(currentContent)) {
            lastContent = CONTENT_AUTHORIZE_DATE;
            continue;
        } else if (CONTENT_AUTHORIZE_DATE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            if (StringUtil.isNotBlank(currentContent)) {
                map.put(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersMerBasicColumn.MER_TYPE.getValue() + SPILT_COLON + currentContent);
            }
        } else if (CONTENT_REGISTRATION_STATUS.equals(currentContent)) {
            lastContent = CONTENT_REGISTRATION_STATUS;
            continue;
        } else if (CONTENT_REGISTRATION_STATUS.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersMerBasicColumn.REG_STATUS.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersMerBasicColumn.REG_STATUS.getValue() + SPILT_COLON + currentContent);
        }
    }
    logger.info("======>>   end  merBaseInfo:");
}


public void getPenaltyInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getPenaltyInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PENALTY.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.SEQ_NO.getValue()));
        // ?????????????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_NO.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_TYPE.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_CONTENT.getValue()));
        // ??????????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_AUTHORITY.getValue()));
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.PENALTY_DT.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue(), tdList.get(6).toString());
        logger.info("================>>??????????????????????????????????????????memo???<<================");
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MEMO.getValue()));
        // ????????????
        String urlPara = trSel.xpath(XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
        map.put(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue(), BASE_URL + urlPara);
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.DETAILS.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPenaltyColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getPenaltyInfo:");
}


public void getShareholderInfo(Selectable selectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getShareholder:");
    // ??????????????????ShareHolder?????????????????????
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_SHAREHOLDER.getMapKey());
    // ??????????????????ShareHolder??????
    List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????ShareHolder??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????ShareHolder??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.NAME.getValue()));
        // ????????????/????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_TYPE.getValue()));
        // ????????????/????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.CERT_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.SHARE_TYPE.getValue()));
        // ????????????
        String urlPara = trSel.xpath(XPATH_A + XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
        map.put(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue(), BASE_URL + urlPara);
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MEMO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerShareholderColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getShareholder:");
}


public void getChangeInfo(Selectable selectable,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getMerChange:");
    // ??????????????????MerChange?????????????????????
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_CHANGE.getMapKey());
    // ??????????????????MerChange??????
    List<Selectable> trList = selectable.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????MerChange??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????MerChange??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_ITEM.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_OLD.getValue()));
        // ?????????????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_NEW.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.CHANGE_DT.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerChangeColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getMerChange:");
}


public void getNdbgChangeInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgChangeInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 5) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntChangeColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.MEMO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.SEQ_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.ITEM.getValue()));
        // ???????????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.PRE_INFO.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.POST_INFO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntChangeColumn.CHANGE_DT.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getNdbgChangeInfo:");
}


public void getData(List<Selectable> tdList,ResultItems resultItems,CreepersConstant.TableNamesBusinessInfo tableName,Map<String,String> matchedColumnNameMap){
    logger.info("======>>   entry   " + tableName.getMapKey() + ":");
    if (!tableName.isList()) {
        // ?????????????????????????????????
        Map<String, String> map = resultItems.get(tableName.getMapKey());
        boolean isFirstTr = true;
        String lastContent = CONTENT_EMPTY;
        if (tdList.size() > 0 && matchedColumnNameMap.size() > 0 && ((tdList.size() - 1) / 2 == matchedColumnNameMap.size())) {
            for (Selectable tdSel : tdList) {
                // ???????????????????????????????????????
                if (isFirstTr) {
                    isFirstTr = false;
                    continue;
                }
                String currentContent = tdSel.xpath(XPATH_ALL_TEXT).toString();
                logger.info("currentContent:" + currentContent);
                for (Map.Entry<String, String> entry : matchedColumnNameMap.entrySet()) {
                    if (entry.getValue().contains(currentContent)) {
                        lastContent = tdSel.xpath(XPATH_ALL_TEXT).toString();
                    } else if (entry.getValue().contains(lastContent)) {
                        map.put(entry.getKey(), currentContent);
                        logger.info(entry.getKey() + SPILT_COLON + currentContent);
                        lastContent = CONTENT_EMPTY;
                    }
                }
            }
        } else {
            logger.info("================>>columnName??????????????????web????????????????????????<<================");
        }
    } else {
        logger.info("================>>tableName???????????????????????????map<<================");
    }
}


public void getDatas(Selectable tableSel,ResultItems resultItems,CreepersConstant.TableNamesBusinessInfo tableName,List<String> columnNameList){
    logger.info("======>>   entry   " + tableName.getMapKey() + ":");
    // ?????????????????????????????????
    if (tableName.isList()) {
        List<Map<String, String>> list = resultItems.get(tableName.getMapKey());
        // ??????web???????????????
        List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
        // web????????????????????????
        int webColumns = trList.get(0).xpath(XPATH_TD).nodes().size();
        // ???????????????????????????
        int dbColumns = columnNameList.size();
        // ???????????????????????????????????????web????????????????????????
        if (webColumns > 0 && dbColumns > 0 && webColumns % dbColumns == 0) {
            for (Selectable trSel : trList) {
                // ????????????web????????????
                List<Selectable> tdList = trSel.xpath(XPATH_TD).nodes();
                // web???????????????????????????????????????
                for (int m = 0; m < (webColumns / dbColumns); m++) {
                    // ??????????????????
                    Map<String, String> map = new HashMap<String, String>();
                    // web??????????????????????????????
                    int webColumnIndex = 0;
                    for (int n = 0; n < dbColumns; n++) {
                        webColumnIndex = m * dbColumns + n;
                        if (tdList.get(webColumnIndex).xpath(XPATH_HTML).regex("window.open").match()) {
                            String urlPara = tdList.get(webColumnIndex).xpath(XPATH_A + XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
                            map.put(columnNameList.get(n), BASE_URL + urlPara);
                            logger.info(columnNameList.get(n) + SPILT_COLON + columnNameList.get(n));
                        } else {
                            map.put(columnNameList.get(n), tdList.get(webColumnIndex).xpath(XPATH_ALL_TEXT).toString());
                            logger.info(columnNameList.get(n) + SPILT_COLON + map.get(columnNameList.get(n)));
                        }
                    }
                    // ?????????/????????????????????????
                    map.put("merNo", resultItems.get("merNo"));
                    logger.info("merNo" + SPILT_COLON + map.get("merNo"));
                    list.add(map);
                }
            }
        } else {
            logger.info("================>>columnName??????????????????web????????????????????????<<================");
        }
    } else {
        logger.info("================>>tableName???????????????????????????List<<================");
    }
    logger.info("======>>   end   " + tableName.getMapKey() + "List<Selectable>:");
}


public void getIllegalInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getIllegalInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ILLEGAL.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.SEQ_NO.getValue()));
        // ????????????????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_REASON.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.ILLEGAL_DT.getValue()));
        // ????????????????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_REASON.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.REMOVE_DT.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.AUTHORITY.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerIllegalColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getIllegalInfo:");
}


@Override
public void process(Page page){
    String mainId = page.getUrl().regex("businessPublicity.jspx\\?id=(.*)").toString();
    // *[@id="details"]/h2
    String merNo = "";
    Selectable merNoSel = page.getHtml().xpath("//*[@id=details]/h2");
    if (merNoSel.regex("???([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").match()) {
        merNo = merNoSel.regex("???([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString().trim();
    } else {
        logger.info("================>>?????????????????????/????????????????????????<<================");
    }
    Map<String, String> urlParaNameAndValue = new LinkedHashMap<String, String>();
    urlParaNameAndValue.put("mainId", mainId);
    // ?????????????????????/??????????????????
    urlParaNameAndValue.put("merNo", merNo);
    Map<String, String> merNoMap = new HashMap<String, String>();
    merNoMap.put(CreepersConstant.TCreepersListColumn.MER_NO.getValue(), merNo);
    logger.info(CreepersConstant.TCreepersListColumn.MER_NO.getValue() + SPILT_COLON + merNoMap.get(CreepersConstant.TCreepersListColumn.MER_NO.getValue()));
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
    if (page.getUrl().regex(BASE_URL + REGEX_URL_BUSINESS_PUBLICITY).match()) {
        logger.info("======================>>>  ??????????????????????????????");
        page.addTargetRequest(BASE_URL + LINK_ENTERPRISE_PUBLICITY + mainId + "&merNo=" + merNo);
        logger.info("======================>>>  ????????????????????????????????????");
        page.addTargetRequest(BASE_URL + LINK_OTHER_DEPARTMENT + mainId + "&merNo=" + merNo);
        logger.info("======================>>>  ????????????????????????????????????");
        page.addTargetRequest(BASE_URL + LINK_JUSTICE_ASSISTANCE + mainId + "&merNo=" + merNo);
        logger.info("======================>>>  ??????????????????   start");
        logger.info("======================>>>  ????????????   start");
        // ??????????????????div????????????
        List<Selectable> tbDivList = page.getHtml().xpath(XPATH_ID_DIV_JI_BEN_XIN_XI + XPATH_SPILT).nodes();
        if (tbDivList.size() > 0) {
            for (int i = 0; i < tbDivList.size(); i++) {
                // ????????????????????????????????????id??????????????????table???tr????????????0-->tr???????????????0???????????????tble?????????
                if (tbDivList.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(INVDIV).match() && tbDivList.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getShareholderInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList.get(i + 1), BASE_URL + LINK_SHARE_HOLDER, urlParaNameAndValue);
                } else if (tbDivList.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ALTDIV).match() && tbDivList.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getChangeInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList.get(i + 1), BASE_URL + LINK_CHANGE, urlParaNameAndValue);
                } else if (tbDivList.get(i).regex(BASICINFO).match()) {
                    merBaseInfo(tbDivList.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                }
            }
        }
        logger.info("======================>>>  ????????????   finish");
        logger.info("======================>>>  ????????????   start");
        // ??????????????????div????????????
        List<Selectable> tbDivList2 = page.getHtml().xpath(XPATH_ID_DIV_BEI_AN + XPATH_SPILT).nodes();
        if (tbDivList2.size() > 0) {
            for (int i = 0; i < tbDivList2.size(); i++) {
                // Selectable s = tbDivList2.get(i);
                if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(MEMDIV).match() && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getKeyManInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_KEY_MEN, urlParaNameAndValue);
                } else if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(CHILDDIV).match() && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getBranchInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                } else if (tbDivList2.get(i).regex(CLEARINFO).match()) {
                    getClearInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                }
            }
        }
        logger.info("======================>>>  ????????????   finish");
        logger.info("======================>>>  ????????????????????????   start");
        // ??????????????????????????????div????????????
        List<Selectable> tbDivList3 = page.getHtml().xpath(XPATH_ID_DIV_DONG_CHAN_DI_YA + XPATH_SPILT).nodes();
        if (tbDivList3.size() > 0) {
            for (int i = 0; i < tbDivList3.size(); i++) {
                // Selectable s = tbDivList3.get(i);
                if (tbDivList3.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(MORTDIV).match() && tbDivList3.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getPropertyInfo(tbDivList3.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList3.get(i + 1), BASE_URL + LINK_PROPERTY, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ????????????????????????   finish");
        logger.info("======================>>>  ????????????????????????   start");
        // ??????????????????????????????div????????????
        List<Selectable> tbDivList4 = page.getHtml().xpath(XPATH_ID_DIV_GU_QUAN_CHU_ZHI + XPATH_SPILT).nodes();
        if (tbDivList4.size() > 0) {
            for (int i = 0; i < tbDivList4.size(); i++) {
                // Selectable s = tbDivList4.get(i);
                if (tbDivList4.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(PLEDGEDIV).match() && tbDivList4.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getPledgeInfo(tbDivList4.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList4.get(i + 1), BASE_URL + LINK_PLEDGE, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ????????????????????????   finish");
        logger.info("======================>>>  ??????????????????   start");
        // ????????????????????????div????????????
        List<Selectable> tbDivList5 = page.getHtml().xpath(XPATH_ID_DIV_XING_ZHENG_CHU_FA + XPATH_SPILT).nodes();
        if (tbDivList5.size() > 0) {
            for (int i = 0; i < tbDivList5.size(); i++) {
                // Selectable s = tbDivList5.get(i);
                if (tbDivList5.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(PUNDIV).match() && tbDivList5.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getPenaltyInfo(tbDivList5.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList5.get(i + 1), BASE_URL + LINK_PENALTY, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ??????????????????   start");
        // ????????????????????????div????????????
        List<Selectable> tbDivList6 = page.getHtml().xpath(XPATH_ID_DIV_JING_YING_YI_CHANG_MINGLU + XPATH_SPILT).nodes();
        if (tbDivList6.size() > 0) {
            for (int i = 0; i < tbDivList6.size(); i++) {
                // Selectable s = tbDivList6.get(i);
                if (tbDivList6.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(EXCDIV).match() && tbDivList6.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getAbnormalInfo(tbDivList6.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList6.get(i + 1), BASE_URL + LINK_ABNORMAL, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ??????????????????   start");
        // ????????????????????????div????????????
        List<Selectable> tbDivList7 = page.getHtml().xpath(XPATH_ID_DIV_YAN_ZHONG_WEI_FA_QI_YE + XPATH_SPILT).nodes();
        if (tbDivList7.size() > 0) {
            for (int i = 0; i < tbDivList7.size(); i++) {
                // Selectable s = tbDivList7.get(i);
                if (tbDivList7.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ILLEGALDIV).match() && tbDivList7.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getIllegalInfo(tbDivList7.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList7.get(i + 1), BASE_URL + LINK_ILLEGAL, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ??????????????????   start");
        // ????????????????????????div????????????
        List<Selectable> tbDivList8 = page.getHtml().xpath(XPATH_ID_DIV_CHOU_CHA_XIN_XI + XPATH_SPILT).nodes();
        if (tbDivList8.size() > 0) {
            for (int i = 0; i < tbDivList8.size(); i++) {
                // Selectable s = tbDivList8.get(i);
                if (tbDivList8.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(SPOTCHECKDIV).match() && tbDivList8.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 0) {
                    getCheckInfo(tbDivList8.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList8.get(i + 1), BASE_URL + LINK_CHECK, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ??????????????????   finish");
    // page.addTargetRequest("http://222.143.24.157/QueryInvList.jspx?pno=2&&mainId=36A75CD835576395E053050A080A2F4E");
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_ENTERPRISE_PUBLICITY).match()) {
        // ??????????????????
        logger.info("======================>>>  ??????????????????   start");
        // ????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        logger.info("======================>>>  ??????????????????   start");
        // ??????????????????????????????div????????????
        List<Selectable> tbDivList1 = page.getHtml().xpath(XPATH_ID_DIV_QI_YE_NIAN_BAO + XPATH_SPILT).nodes();
        if (tbDivList1.size() > 0) {
            getQygsNianbao(tbDivList1.get(0), page, merNo);
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ?????????????????????   start");
        List<Selectable> tbDivList2 = page.getHtml().xpath(XPATH_ID_DIV_TOU_ZI_REN + XPATH_SPILT).nodes();
        if (tbDivList2.size() > 0) {
            for (int i = 0; i < tbDivList2.size(); i++) {
                // Selectable s = tbDivList2.get(i);
                if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(GDDIV).match() && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 3) {
                    getQygsShareInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_KEY_MEN, urlParaNameAndValue);
                } else if (tbDivList2.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(ALTINV).match() && tbDivList2.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 2) {
                    getQygsShareChangeInfo(tbDivList2.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ?????????????????????   finish");
        logger.info("======================>>>  ??????????????????   start");
        List<Selectable> tbDivList3 = page.getHtml().xpath(XPATH_ID_DIV_GU_DONG_GU_QUAN).nodes();
        if (tbDivList2.size() > 0) {
            for (int i = 0; i < tbDivList3.size(); i++) {
                if (tbDivList3.get(i).xpath(XPATH_DIV + XPATH_PROPERTY_ID).regex(XPATH_ID_DIV_GQBG).match() && tbDivList3.get(i).xpath(XPATH_TABLE + XPATH_TR).nodes().size() > 2) {
                    getQygsEquityInfo(tbDivList3.get(i).xpath(XPATH_TABLE), resultItems, merNoMap);
                    addPaginationLink(page, tbDivList2.get(i + 1), BASE_URL + LINK_BRANCH, urlParaNameAndValue);
                }
            }
        }
        logger.info("======================>>>  ??????????????????   finish");
        logger.info("======================>>>  ??????????????????   finish");
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL__OTHER_DEPARTMENT).match()) {
    // ????????????????????????
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_JUSTICE_ASSISTANCE).match()) {
    // ????????????????????????
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_0).match()) {
        // ????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getShareholderInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_1).match()) {
        // ????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getChangeInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_2).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getKeyManInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_3).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getBranchInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_4).match()) {
        // ????????????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getPropertyInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_5).match()) {
        // ????????????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getPledgeInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_6).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getPenaltyInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_7).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getAbnormalInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_8).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getIllegalInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_9).match()) {
        // ??????????????????
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoMap.put("merNo", merNo);
        getCheckInfo(page.getHtml().xpath(XPATH_TABLE), resultItems, merNoMap);
    } else if (page.getUrl().regex(BASE_URL + REGEX_URL_PAGINATION_10).match()) {
        Map<String, String> merNoAndYear = new HashMap<String, String>();
        merNo = page.getUrl().regex("merNo=([a-zA-Z0-9\u4e00-\u9fa5 ]{4,30})").toString();
        merNoAndYear.put("merNo", merNo);
        logger.info("======================>>>  ????????????   start");
        // ????????????????????????div????????????
        List<Selectable> tbDivList = page.getHtml().xpath(XPATH_ID_DIV_SI_FA_PAN_DING + XPATH_TABLE).nodes();
        if (tbDivList.size() > 0) {
            String year = tbDivList.get(0).xpath(XPATH_TR1).regex("([0-9]+)??????").toString();
            merNoAndYear.put("memo", year);
            for (int i = 0; i < tbDivList.size(); i++) {
                // Selectable s = tbDivList.get(i);
                if (tbDivList.get(i).xpath(XPATH_TR2).regex(ENT_BASIC_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgBasic(tbDivList.get(i), resultItems, merNoMap);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_WEB_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgWebInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_SHARE_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgShareInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_INVEST_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgInvestInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_ASSET_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 1) {
                    getNdbgAssetInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_WARRANT_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgWarrantInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_EQUITY_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgEquityInfo(tbDivList.get(i), resultItems, merNoAndYear);
                } else if (tbDivList.get(i).xpath(XPATH_TR1).regex(ENT_CHANGE_INFO).match() && tbDivList.get(i).xpath(XPATH_TR).nodes().size() > 2) {
                    getNdbgChangeInfo(tbDivList.get(i), resultItems, merNoAndYear);
                }
            }
        }
    } else {
        logger.info("=======??????URL:" + page.getUrl() + "=======");
    }
}


public void getKeyManInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getKeyManInfo:");
    // ??????????????????KeyMan?????????????????????
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_KEYMAN.getMapKey());
    // ???????????????web????????? ??????KeyMan??????
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        List<Selectable> strList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        for (int m = 0; m < 2; m++) {
            Map<String, String> map = new HashMap<String, String>();
            map.putAll(merNoMap);
            logger.info(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.MER_NO.getValue()));
            for (int n = 0; n < 3; n++) {
                if (n == 0) {
                    map.put(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue(), strList.get(n + m * 3).toString());
                    logger.info(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.SEQ_NO.getValue()));
                } else if (n == 1) {
                    map.put(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue(), strList.get(n + m * 3).toString());
                    logger.info(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.NAME.getValue()));
                } else if (n == 2) {
                    map.put(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue(), strList.get(n + m * 3).toString());
                    logger.info(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerKeymanColumn.POSITION.getValue()));
                }
            }
            list.add(map);
        }
    }
    logger.info("======>>   end   getKeyManInfo:");
}


public void addPaginationLink(Page page,Selectable tableSel,String url,Map<String,String> urlParaNameAndValue){
    logger.info("======>>   entry   addPaginationLink:");
    int alinkNum = 0;
    if (tableSel.xpath(XPATH_TH).nodes().size() > 0) {
        // ?????????>>??????????????????????????????
        if (tableSel.xpath(XPATH_TH).toString().contains(NEXT)) {
            alinkNum = Integer.parseInt(tableSel.xpath(XPATH_TH).regex(REGEX_NEXT).toString());
            for (int i = 2; i <= alinkNum; i++) {
                StringBuilder paraStr = new StringBuilder();
                for (Map.Entry<String, String> entry : urlParaNameAndValue.entrySet()) {
                    paraStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                page.addTargetRequest(url + i + paraStr);
                logger.info("add Url:" + url + i + paraStr);
            }
        } else {
            List<Selectable> list = tableSel.xpath(XPATH_SPAN + XPATH_ALL_TEXT).nodes();
            if (list.size() > 3) {
                alinkNum = Integer.parseInt(list.get(list.size() - 2).toString());
                for (int i = 2; i <= alinkNum; i++) {
                    StringBuilder paraStr = new StringBuilder();
                    for (Map.Entry<String, String> entry : urlParaNameAndValue.entrySet()) {
                        paraStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                    }
                    page.addTargetRequest(url + i + paraStr);
                    logger.info("add Url:" + url + i + paraStr);
                }
            }
        }
    }
    logger.info("======>>   end  addPaginationLink:");
}


public void getNdbgAssetInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgAssetInfo:");
    List<Selectable> tdThList = tableSel.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
    Map<String, String> map = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_ASSET.getMapKey());
    // ??????????????????????????????
    map.putAll(merNoAndYear);
    logger.info(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MER_NO.getValue()));
    logger.info(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntInvestColumn.MEMO.getValue()));
    int tdThNum = 0;
    String lastContent = CONTENT_EMPTY;
    for (Selectable eachTdTh : tdThList) {
        tdThNum++;
        if (tdThNum < 2) {
            continue;
        }
        String currentContent = eachTdTh.xpath(XPATH_ALL_TEXT).toString();
        logger.info("currentContent:" + currentContent);
        if (CONTENT_COMPANY_TOTAL_ASSET.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_ASSET;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_ASSET.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_ASSET.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_ASSET.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_TOTAL_EQUITY.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_EQUITY;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_EQUITY.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ?????????????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_EQUITY.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_EQUITY.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_TOTAL_LIABILITIES.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_LIABILITIES;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_LIABILITIES.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_LIABILITIES.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_LIABILITIES.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_TOTAL_INCOME.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_INCOME;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_INCOME.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ???????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_INCOME.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_INCOME.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_TOTAL_AVENUE.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_AVENUE;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_AVENUE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_AVENUE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_AVENUE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_TOTAL_BUS_INCOME.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_TOTAL_BUS_INCOME;
            continue;
        } else if (CONTENT_COMPANY_TOTAL_BUS_INCOME.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????????????????????????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.TOTAL_BUS_INCOME.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.TOTAL_BUS_INCOME.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_NET_PROFIT.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_NET_PROFIT;
            continue;
        } else if (CONTENT_COMPANY_NET_PROFIT.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ?????????
            map.put(CreepersConstant.TCreepersEntAssetColumn.NET_PROFIT.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntAssetColumn.NET_PROFIT.getValue() + SPILT_COLON + currentContent);
        }
    }
    logger.info("======>>   finish   getNdbgAssetInfo:");
}


public void getQygsNianbao(Selectable tableSel,Page page,String merNo){
    logger.info("======>>   entry   getQygsNianbao:");
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        if (trNum < 2) {
            trNum++;
            continue;
        }
        List<Selectable> tdList = trSel.xpath(XPATH_TD).nodes();
        if (tdList.size() != 3) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ??????
        logger.info("?????????" + tdList.get(0).xpath(XPATH_ALL_TEXT).toString());
        // ????????????
        String ndbgParam = tdList.get(1).xpath(XPATH_A + XPATH_PROPERTY_HREF).toString();
        // ????????????URL
        String ndbgUrl = ndbgParam + "&merNo=" + merNo;
        logger.info("???????????????" + ndbgUrl);
        page.addTargetRequest(ndbgUrl);
        // ????????????
        logger.info("???????????????" + tdList.get(2).toString());
    }
    logger.info("======>>   end   getQygsNianbao:");
}


public void getNdbgShareInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgShareInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_WEB.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        trNum++;
        // ?????????tr???????????????????????????
        if (trNum < 3) {
            continue;
        }
        // ????????????tr?????????????????????
        if (trNum == trList.size()) {
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 7) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SHAREHOLDER.getValue()));
        // ?????????????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_CAPITAL.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_DT.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.SUB_TYPE.getValue()));
        // ?????????????????????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_CAPITAL.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_DT.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue(), tdList.get(6).toString());
        logger.info(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.REAL_TYPE.getValue()));
        // ??????????????????????????????
        map.putAll(merNoAndYear);
        logger.info(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MER_NO.getValue()));
        logger.info(CreepersConstant.TCreepersEntShareColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareColumn.MEMO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getNdbgShareInfo:");
}


@Override
public Site getSite(){
    return site;
}


public void getQygsShareChangeInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getQygsShareChangeInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_CHANGE.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    int trNum = 0;
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ?????????tr???????????????????????????
        if (trNum < 2) {
            trNum++;
            continue;
        }
        // ?????????????????????
        if (StringUtils.isBlank(trSel.xpath(XPATH_ALL_TEXT).nodes().get(0).toString())) {
            continue;
        }
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        if (tdList.size() != 5) {
            logger.info("=============>>>????????????????????????????????????.");
            continue;
        }
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.MER_NO.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.SEQ_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_CONTENT.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_DT.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_OLD.getValue()));
        // ?????????
        map.put(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntShareChangeColumn.CHANGE_NEW.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getQygsShareChangeInfo:");
}


public void getAbnormalInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getAbnormalInfo:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_ABNORMAL.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.SEQ_NO.getValue()));
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_REASON.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.ABNORMAL_DT.getValue()));
        // ????????????????????????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_REASON.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.REMOVE_DT.getValue()));
        // ????????????????????????
        map.put(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.AUTHORITY.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerAbnormalColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getAbnormalInfo:");
}


public void getNdbgBasic(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoAndYear){
    logger.info("======>>   entry   getNdbgBasic:");
    List<Selectable> tdThList = tableSel.xpath(XPATH_TBODY + XPATH_TR + XPATH_SPILT).nodes();
    Map<String, String> map = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_BASIC.getMapKey());
    // ???????????????
    map.putAll(merNoAndYear);
    logger.info(CreepersConstant.TCreepersEntBasicColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersEntBasicColumn.MER_NO.getValue()));
    int tdThNum = 0;
    String lastContent = CONTENT_EMPTY;
    for (Selectable eachTdTh : tdThList) {
        if (tdThNum < 2) {
            if (tdThNum == 0) {
                String year = eachTdTh.regex("([0-9]+)??????").toString();
                map.put(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue(), year);
            }
            tdThNum++;
            continue;
        }
        String currentContent = eachTdTh.xpath(XPATH_ALL_TEXT).toString();
        logger.info("currentContent:" + currentContent);
        if (CONTENT_CREDIT_CODE1.equals(currentContent) || CONTENT_CREDIT_CODE2.equals(currentContent)) {
            lastContent = CONTENT_CREDIT_CODE1;
            continue;
        } else if (CONTENT_CREDIT_CODE1.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ?????????/????????????????????????
            // map.put(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue(),
            // currentContent);
            // logger.info(CreepersConstant.TCreepersEntBasicColumn.MEMO.getValue()
            // + SPILT_COLON + currentContent);
            logger.info("===========>?????????/????????????????????????????????????");
        } else if (CONTENT_COMPANY_NAME.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_NAME;
            continue;
        } else if (CONTENT_COMPANY_NAME.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.MER_NAME.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.MER_NAME.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_PHONE.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_PHONE;
            continue;
        } else if (CONTENT_COMPANY_PHONE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.PHONE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.PHONE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_POST_CODE.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_POST_CODE;
            continue;
        } else if (CONTENT_COMPANY_POST_CODE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.POST_CODE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.POST_CODE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_ADDR.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_ADDR;
            continue;
        } else if (CONTENT_COMPANY_ADDR.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.ADDR.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.ADDR.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_EMAIL.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_EMAIL;
            continue;
        } else if (CONTENT_COMPANY_EMAIL.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.EMAIL.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.EMAIL.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_IS_TRANSFER.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_IS_TRANSFER;
            continue;
        } else if (CONTENT_COMPANY_IS_TRANSFER.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ?????????????????????????????????????????????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.IS_TRANSFER.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.IS_TRANSFER.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_OPERATING_STATUS.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_OPERATING_STATUS;
            continue;
        } else if (CONTENT_COMPANY_OPERATING_STATUS.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ??????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.STATUS.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.STATUS.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_IS_WEBSITE.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_IS_WEBSITE;
            continue;
        } else if (CONTENT_COMPANY_IS_WEBSITE.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.IS_WEBSITE.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.IS_WEBSITE.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_IS_INVEST.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_IS_INVEST;
            continue;
        } else if (CONTENT_COMPANY_IS_INVEST.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????????????????????????????????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.IS_INVEST.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.IS_INVEST.getValue() + SPILT_COLON + currentContent);
        } else if (CONTENT_COMPANY_CREW_NUMBER.equals(currentContent)) {
            lastContent = CONTENT_COMPANY_CREW_NUMBER;
            continue;
        } else if (CONTENT_COMPANY_CREW_NUMBER.equals(lastContent)) {
            lastContent = CONTENT_EMPTY;
            // ????????????
            map.put(CreepersConstant.TCreepersEntBasicColumn.CREW_NUMBER.getValue(), currentContent);
            logger.info(CreepersConstant.TCreepersEntBasicColumn.CREW_NUMBER.getValue() + SPILT_COLON + currentContent);
        }
    }
    logger.info("======>>   finish   getNdbgBasic:");
}


public void getPropertyInfo(Selectable tableSel,ResultItems resultItems,Map<String,String> merNoMap){
    logger.info("======>>   entry   getProperty:");
    List<Map<String, String>> list = resultItems.get(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_MER_PROPERTY.getMapKey());
    List<Selectable> trList = tableSel.xpath(XPATH_TR).nodes();
    for (Selectable trSel : trList) {
        // ???????????????Property??????
        Map<String, String> map = new HashMap<String, String>();
        // ???????????????Property??????
        List<Selectable> tdList = trSel.xpath(XPATH_TD_ALLTEXT).nodes();
        // ????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue(), tdList.get(0).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.SEQ_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue(), tdList.get(1).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_NO.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue(), tdList.get(2).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_DT.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue(), tdList.get(3).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.REG_AUTHORITY.getValue()));
        // ???????????????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue(), tdList.get(4).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.CLAIM_AMOUNT.getValue()));
        // ????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue(), tdList.get(5).toString());
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.STATUS.getValue()));
        // ??????????????????
        map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), tdList.get(6).toString());
        logger.info("================>>??????????????????????????????????????????memo???<<================");
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));
        // ????????????
        String urlPara = trSel.xpath(XPATH_PROPERTY_ONCLICK).toString().replace("window.open('", CONTENT_EMPTY).replace("')", CONTENT_EMPTY).replace("?", "\\\\?");
        map.put(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue(), BASE_URL + urlPara);
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MEMO.getValue()));
        // ???????????????
        map.putAll(merNoMap);
        logger.info(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue() + SPILT_COLON + map.get(CreepersConstant.TCreepersMerPropertyColumn.MER_NO.getValue()));
        list.add(map);
    }
    logger.info("======>>   end   getProperty:");
}


}