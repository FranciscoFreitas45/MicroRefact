package com.fosun.fc.projects.creepers.pageprocessor;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesCreditReference;
import com.fosun.fc.projects.creepers.downloader.CreditReferenceSeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.pipeline.CreditReferencePipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.PropertiesUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
@Component("creditReferenceCenterProcessor")
public class CreditReferenceCenterProcessor implements PageProcessor{

 private Logger logger;

 private  String HTML_CONTENT;

 private  String WORD_YEAR;

 private  String WORD_MONT;

 private  String WORD_MONT_COUNT;

 private  String WORD_DAY;

 private  String WORD_GRANT;

 private  String WORD_CUT_OFF;

 private  String WORD_CREDIT_LIMIT;

 private  String WORD_CREDIT_LIMIT_TO_RMB;

 private  String WORD_OVERDRAFT_BALANCE;

 private  String WORD_OVERDRAFT_BALANCE_TWO;

 private  String WORD_OVERDRAFT_BALANCE_THR;

 private  String WORD_LOG_OUT;

 private  String WORD_INACTIVATED;

 private  String WORD_FOR;

 private  String WORD_AT;

 private  String WORD_IN;

 private  String WORD_HANDLED;

 private  String WORD_GUARANTEE_CONTRACT_AMOUNT;

 private  String WORD_GUARANTEET_AMOUNT;

 private  String WORD_GUARANTEED_PRINCIPAL_BALANCE;

 private  String WORD_LAST_FIVE_YEARS;

 private  String WORD_OVERDRAFT_SIXTY;

 private  String SPLIT_WORD;

 private  String SPLIT_COMMA;

 private  String SPLIT_PERIOD;

 private  String SPLIT_WORD_LEFT;

 private  String SPLIT_WORD_RIGHT;

 private  String REGEX_BETWEEN_CONTENT;

 private  String REGEX_DATE_YYYY_MM_DD;

 private  String REGEX_DATE_YYYY_MM;

 private  String REGEX_NUMBER;

 private  String XPATH_NODES_ALL_CONTENT;

 private  String XPATH_TD_ALL_TEXT;

 private  String XPATH_LI_ALL_TEXT;

 private  String XPATH_NODES_TD;

 private  String XPATH_NODES_TR_CENTER;

 private  String XPATH_NODES_LI;

 private  String XPATH_NODES_TR_TD_TABLE_TBODY_TR_LIST;

 private  String LABEL_NULL;

 private  String LABEL_PERIOD;

 private  String LABEL_SPACE;

 private  String LABEL_BR;

 private  String LABEL_START_STRONG;

 private  String LABEL_END_STRONG;

 private  String LABEL_END_SPAN;

 private  String LABEL_END_TD;

 private  String CONTENT_CREDIT_HEAD_ONE;

 private  String CONTENT_RPT_NO;

 private  String CONTENT_QUERY_DT;

 private  String CONTENT_RPT_DT;

 private  String CONTENT_NAME;

 private  String CONTENT_ID_TYPE;

 private  String CONTENT_ID_NO;

 private  String CONTENT_MARITAL_STATUS;

 private  String CONTENT_INFO_SUMMARY;

 private  String CONTENT_COMMON_INFO;

 private  String CONTENT_QUERY_BY;

 private  String CONTENT_QUERY_REASON;

 private  String CONTENT_SERIAL_NUM;

 private  String CONTENT_CC;

 private  String CONTENT_ASSET_HANDLE;

 private  String CONTENT_COMPENSATORY;

 private  String CONTENT_HOUSING_LOAN;

 private  String CONTENT_OTHER_LOAN;

 private  String CONTENT_GUARANTEE;

 private  String CONTENT_ACCOUNT_NO;

 private  String CONTENT_OUTSTANDING_ACCOUNT_NO;

 private  String CONTENT_OVERDUE_ACCOUNT_NO;

 private  String CONTENT_NINETY_ACCOUNT_NO;

 private  String CONTENT_GUARANTEE_NO;

 private  String CONTENT_NEVER_OVERDUE_60;

 private  String CONTENT_OVERDUE_60;

 private  String CONTENT_OVERDUE_ACCOUNT;

 private  String CONTENT_NEVER_HL_OVERDRAFT;

 private  String CONTENT_HL_OVERDRAFT;

 private  String CONTENT_NO_COMMON_INFO;

 private  String CONTENT_QUERY_RECORD;

 private  String VALUE_CC_STATUS_VALID;

 private  String VALUE_CC_STATUS_LOG_OUT;

 private  String VALUE_CC_STATUS_INACTIVATED;

 private  String REPLACE_META_BEFORE;

 private  String REPLACE_META_AFTER;

 private  Site site;


@Override
public void process(Page page){
    // ????????????????????? ?????????????????????
    page.putField(HTML_CONTENT, page.getHtml().toString().replace(REPLACE_META_BEFORE, REPLACE_META_AFTER));
    Map<String, String> rptNoMap = new HashMap<String, String>();
    // ?????????putField
    TableNamesCreditReference[] getNames = CreepersConstant.TableNamesCreditReference.values();
    for (TableNamesCreditReference TableNamesCreditReference : getNames) {
        if (TableNamesCreditReference.getMapKey().equals(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey())) {
        // continue;
        }
        Object obj;
        if (TableNamesCreditReference.isList()) {
            obj = new ArrayList<Map<String, Object>>();
        } else {
            obj = new HashMap<String, Object>();
        }
        page.putField(TableNamesCreditReference.getMapKey(), obj);
    }
    ResultItems resultItems = page.getResultItems();
    CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
    // ????????????????????????
    List<Selectable> nodesList = page.getHtml().xpath(XPATH_NODES_ALL_CONTENT).nodes();
    String lastLabel = "";
    for (int i = 0; i < nodesList.size(); i++) {
        // logger.info("nodesList(" + i + "):\n" + nodesList.get(i) + "\n");
        String nodesCONTENT = nodesList.get(i).toString();
        // ?????????????????????
        if (LABEL_BR.equals(nodesCONTENT.trim().toUpperCase())) {
            logger.info("???????????????????????????????????????");
            continue;
        // ?????????????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG;
            logger.info("???????????????????????????");
            continue;
        // ??????????????????
        } else if (nodesList.get(i).regex(CONTENT_QUERY_RECORD + LABEL_END_STRONG).match()) {
            lastLabel = CONTENT_QUERY_RECORD + LABEL_END_STRONG;
            logger.info("??????????????????????????????");
            continue;
        // ???????????????????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG;
            logger.info("?????????????????????????????????");
            continue;
        // ????????????????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG;
            logger.info("????????????????????????????????????");
            continue;
        // ??????????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG;
            logger.info("??????????????????????????????");
            continue;
        // ??????????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG;
            logger.info("?????????????????????????????????");
            continue;
        // ???????????????????????????-- ???????????? ???????????? ????????????
        } else if (nodesList.get(i).regex(LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG).match()) {
            lastLabel = LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG;
            List<Selectable> tdContentList = nodesList.get(i).xpath(XPATH_TD_ALL_TEXT).nodes();
            Map<String, String> accountMap = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ACCOUNT_BAK.getMapKey());
            Map<String, String> basicMap = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey());
            for (Selectable selectable : tdContentList) {
                if (selectable.regex(CONTENT_RPT_NO).match()) {
                    logger.info(CONTENT_RPT_NO + selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                    accountMap.put(CreepersConstant.TCreepersAccountBakColumn.RPT_NO.getValue(), selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                    accountMap.put(CreepersConstant.TCreepersAccountBakColumn.USR.getValue(), param.getLoginName());
                    accountMap.put(CreepersConstant.TCreepersAccountBakColumn.PWD.getValue(), param.getPassword());
                    accountMap.put(CreepersConstant.TCreepersAccountBakColumn.CDE.getValue(), param.getMessageCaptchaValue());
                    rptNoMap.put(CreepersConstant.TCreepersAccountBakColumn.RPT_NO.getValue(), selectable.regex(CONTENT_RPT_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                } else if (selectable.regex(CONTENT_QUERY_DT).match()) {
                    logger.info(CONTENT_QUERY_DT + selectable.regex(CONTENT_QUERY_DT + REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.QUERY_DT.getValue(), selectable.regex(CONTENT_QUERY_DT + REGEX_BETWEEN_CONTENT).toString());
                    basicMap.putAll(rptNoMap);
                } else if (selectable.regex(CONTENT_RPT_DT).match()) {
                    logger.info(CONTENT_RPT_DT + selectable.regex(CONTENT_RPT_DT + REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.RPT_DT.getValue(), selectable.regex(CONTENT_RPT_DT + REGEX_BETWEEN_CONTENT).toString());
                }
            }
        // ???????????????????????????-- ?????? ???????????? ????????? ????????????
        } else if ((LABEL_START_STRONG + CONTENT_CREDIT_HEAD_ONE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> tdContentList = nodesList.get(i).xpath(XPATH_TD_ALL_TEXT).nodes();
            Map<String, String> basicMap = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey());
            for (Selectable selectable : tdContentList) {
                if (selectable.regex(CONTENT_NAME).match()) {
                    logger.info(CONTENT_NAME + selectable.regex(CONTENT_NAME + REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.NAME.getValue(), selectable.regex(CONTENT_NAME + REGEX_BETWEEN_CONTENT).toString().trim());
                } else if (selectable.regex(CONTENT_ID_TYPE).match()) {
                    logger.info(CONTENT_ID_TYPE + selectable.regex(CONTENT_ID_TYPE + REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.ID_TYPE.getValue(), selectable.regex(CONTENT_ID_TYPE + REGEX_BETWEEN_CONTENT).toString().trim());
                } else if (selectable.regex(CONTENT_ID_NO).match()) {
                    logger.info(CONTENT_ID_NO + selectable.regex(CONTENT_ID_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.ID_NO.getValue(), selectable.regex(CONTENT_ID_NO + REGEX_BETWEEN_CONTENT).toString().trim());
                } else if (selectable.regex(CONTENT_MARITAL_STATUS).match()) {
                    logger.info(CONTENT_MARITAL_STATUS + selectable.regex(REGEX_BETWEEN_CONTENT).toString().trim());
                    basicMap.put(CreepersConstant.TCreepersBasicColumn.MARITAL_STATUS.getValue(), selectable.regex(REGEX_BETWEEN_CONTENT).toString().trim());
                }
            }
        // ?????????????????????
        } else if (nodesList.get(i).regex(CONTENT_INFO_SUMMARY + LABEL_END_SPAN).match()) {
            lastLabel = LABEL_NULL;
            List<Selectable> trContentList = nodesList.get(i).xpath(XPATH_NODES_TR_TD_TABLE_TBODY_TR_LIST).nodes();
            Map<String, String> genralMap = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey());
            genralMap.putAll(rptNoMap);
            for (Selectable eachTrContentList : trContentList) {
                List<Selectable> tdContentList = eachTrContentList.xpath(XPATH_NODES_TD).nodes();
                if (tdContentList.get(1).regex(CONTENT_CC + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info("???????????????????????????????????????????????????");
                    continue;
                } else if (tdContentList.get(0).regex(CONTENT_OUTSTANDING_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info(CONTENT_CC + CONTENT_OUTSTANDING_ACCOUNT_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_CC_NO.getValue(), tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_HOUSING_LOAN + CONTENT_OUTSTANDING_ACCOUNT_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_HOUSING_LOAN_NO.getValue(), tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_OTHER_LOAN + CONTENT_OUTSTANDING_ACCOUNT_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OUTSTANDING_OTHER_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                } else if (tdContentList.get(0).regex(CONTENT_OVERDUE_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info(CONTENT_CC + CONTENT_OVERDUE_ACCOUNT_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_CC_NO.getValue(), tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_HOUSING_LOAN + CONTENT_OVERDUE_ACCOUNT_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_HOUSING_LOAN_NO.getValue(), tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_OTHER_LOAN + CONTENT_OVERDUE_ACCOUNT_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OVERDUE_OTHER_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                } else if (tdContentList.get(0).regex(CONTENT_NINETY_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info(CONTENT_CC + CONTENT_NINETY_ACCOUNT_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_CC_NO.getValue(), tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_HOUSING_LOAN + CONTENT_NINETY_ACCOUNT_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_HOUSING_LOAN_NO.getValue(), tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_OTHER_LOAN + CONTENT_NINETY_ACCOUNT_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.NINETY_OTHER_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                } else if (tdContentList.get(0).regex(CONTENT_GUARANTEE_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info(CONTENT_CC + CONTENT_GUARANTEE_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_CC_NO.getValue(), tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_HOUSING_LOAN + CONTENT_GUARANTEE_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_HOUSING_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_OTHER_LOAN + CONTENT_GUARANTEE_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.GUARANTEE_OTHER_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                } else if (tdContentList.get(0).regex(CONTENT_ACCOUNT_NO + LABEL_SPACE + LABEL_END_TD).match()) {
                    logger.info(CONTENT_CC + CONTENT_ACCOUNT_NO + tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.CC_NO.getValue(), tdContentList.get(1).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_HOUSING_LOAN + CONTENT_ACCOUNT_NO + tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.HOUSING_LOAN_NO.getValue(), tdContentList.get(2).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    logger.info(CONTENT_OTHER_LOAN + CONTENT_ACCOUNT_NO + tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                    genralMap.put(CreepersConstant.TCreepersGeneralColumn.OTHER_LOAN_NO.getValue(), tdContentList.get(3).xpath(XPATH_TD_ALL_TEXT).toString().trim());
                }
            }
        // ?????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_CC + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            String lastSpan = "";
            List<Map<String, String>> ccAccountList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_CC_DETAIL.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> ccDetaillMap = new HashMap<String, String>();
                ccDetaillMap.putAll(rptNoMap);
                if (liContent.regex(CONTENT_NEVER_OVERDUE_60 + LABEL_END_STRONG).match()) {
                    lastSpan = CONTENT_NEVER_OVERDUE_60;
                    continue;
                } else if ((CONTENT_OVERDUE_60 + LABEL_END_STRONG).equals(lastSpan)) {
                    lastSpan = CONTENT_OVERDUE_60;
                    continue;
                } else if ((CONTENT_OVERDUE_ACCOUNT + LABEL_END_STRONG).equals(lastSpan)) {
                    lastSpan = CONTENT_OVERDUE_ACCOUNT;
                    continue;
                } else if (CONTENT_NEVER_OVERDUE_60.equals(lastSpan)) {
                    logger.info(CONTENT_NEVER_OVERDUE_60 + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                    // liContent.xpath(XPATH_LI_ALL_TEXT).regex("").all();
                    ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(), (ccAccountList.size() + 1) + LABEL_PERIOD + memo);
                    // analyzeCcDetailNeverOverDue(memo, ccDetaillMap);
                    ccAccountList.add(ccDetaillMap);
                } else if (CONTENT_OVERDUE_60.equals(lastSpan)) {
                    logger.info(CONTENT_OVERDUE_60 + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                    ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(), (ccAccountList.size() + 1) + LABEL_PERIOD + memo);
                    // analyzeCcDetailOverDue(memo, ccDetaillMap);
                    ccAccountList.add(ccDetaillMap);
                } else if (CONTENT_OVERDUE_ACCOUNT.equals(lastSpan)) {
                    logger.info(CONTENT_OVERDUE_ACCOUNT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    ccDetaillMap.put(CreepersConstant.TCreepersCcDetailColumn.MEMO.getValue(), (ccAccountList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    ccAccountList.add(ccDetaillMap);
                }
            }
        // ????????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_HOUSING_LOAN + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            String lastSpan = "";
            List<Map<String, String>> hlDetailList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_HL_DETAIL.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> hlDetailMap = new HashMap<String, String>();
                hlDetailMap.putAll(rptNoMap);
                if (liContent.regex(CONTENT_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                    lastSpan = CONTENT_HL_OVERDRAFT;
                    continue;
                } else if (liContent.regex(CONTENT_NEVER_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                    lastSpan = CONTENT_NEVER_HL_OVERDRAFT;
                    continue;
                } else if (CONTENT_HL_OVERDRAFT.equals(lastSpan)) {
                    logger.info(CONTENT_HL_OVERDRAFT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    hlDetailMap.put(CreepersConstant.TCreepersHlDetailColumn.MEMO.getValue(), (hlDetailList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    hlDetailList.add(hlDetailMap);
                } else if (CONTENT_NEVER_HL_OVERDRAFT.equals(lastSpan)) {
                    logger.info(CONTENT_NEVER_HL_OVERDRAFT + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    hlDetailMap.put(CreepersConstant.TCreepersHlDetailColumn.MEMO.getValue(), (hlDetailList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    hlDetailList.add(hlDetailMap);
                }
            }
        // ???????????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_COMPENSATORY + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            List<Map<String, String>> compensatoryList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> compensatoryMap = new HashMap<String, String>();
                compensatoryMap.putAll(rptNoMap);
                logger.info(CONTENT_COMPENSATORY + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                compensatoryMap.put(CreepersConstant.TCreepersCompensatoryColumn.MEMO.getValue(), (compensatoryList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                compensatoryList.add(compensatoryMap);
            }
        // ????????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_ASSET_HANDLE + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            List<Map<String, String>> assetHandleList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_ASSET_HANDLE.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> assetHandleMap = new HashMap<String, String>();
                assetHandleMap.putAll(rptNoMap);
                logger.info(CONTENT_ASSET_HANDLE + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                assetHandleMap.put(CreepersConstant.TCreepersAssetHandleColumn.MEMO.getValue(), (assetHandleList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                assetHandleList.add(assetHandleMap);
            }
        // ????????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_OTHER_LOAN + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            String lastSpan = "";
            List<Map<String, String>> olHandleList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> olHandleMap = new HashMap<String, String>();
                olHandleMap.putAll(rptNoMap);
                if (liContent.regex(CONTENT_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                    lastSpan = CONTENT_HL_OVERDRAFT;
                    continue;
                } else if (liContent.regex(CONTENT_NEVER_HL_OVERDRAFT + LABEL_END_STRONG).match()) {
                    lastSpan = CONTENT_NEVER_HL_OVERDRAFT;
                    continue;
                } else if (CONTENT_HL_OVERDRAFT.equals(lastSpan)) {
                    logger.info(CONTENT_OTHER_LOAN + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    olHandleMap.put(CreepersConstant.TCreepersOlDetailColumn.MEMO.getValue(), (olHandleList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                } else if (CONTENT_NEVER_HL_OVERDRAFT.equals(lastSpan)) {
                    logger.info(CONTENT_OTHER_LOAN + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                    olHandleMap.put(CreepersConstant.TCreepersOlDetailColumn.MEMO.getValue(), (olHandleList.size() + 1) + LABEL_PERIOD + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                }
            }
        // ???????????????????????????
        } else if ((LABEL_START_STRONG + CONTENT_GUARANTEE + LABEL_SPACE + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> olContentList = nodesList.get(i).xpath(XPATH_NODES_LI).nodes();
            List<Map<String, String>> guaranteeList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey());
            for (Selectable liContent : olContentList) {
                Map<String, String> guaranteeMap = new HashMap<String, String>();
                guaranteeMap.putAll(rptNoMap);
                logger.info(CONTENT_ASSET_HANDLE + liContent.xpath(XPATH_LI_ALL_TEXT).toString());
                String memo = liContent.xpath(XPATH_LI_ALL_TEXT).toString();
                guaranteeMap.put(CreepersConstant.TCreepersGuaranteeColumn.MEMO.getValue(), (guaranteeList.size() + 1) + LABEL_PERIOD + memo);
                // analyzeGuarantee(memo, guaranteeMap);
                guaranteeList.add(guaranteeMap);
            }
        // ??????????????????
        } else if (nodesList.get(i).regex(CONTENT_COMMON_INFO + LABEL_SPACE + LABEL_END_TD).match()) {
            lastLabel = LABEL_NULL;
            if (nodesList.get(i).regex(CONTENT_NO_COMMON_INFO + LABEL_SPACE + LABEL_END_STRONG).match()) {
                logger.info("??????????????????");
                continue;
            } else {
                logger.info("???????????????????????????????????????????????????");
            }
        // ??????????????????
        } else if ((CONTENT_QUERY_RECORD + LABEL_END_STRONG).equals(lastLabel)) {
            lastLabel = LABEL_NULL;
            List<Selectable> trContentList = nodesList.get(i).xpath(XPATH_NODES_TR_CENTER).nodes();
            List<Map<String, String>> queryLogList = resultItems.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey());
            for (Selectable eachtrContentList : trContentList) {
                Map<String, String> queryLogMap = new HashMap<String, String>();
                queryLogMap.putAll(rptNoMap);
                if (eachtrContentList.regex(LABEL_START_STRONG + CONTENT_SERIAL_NUM + LABEL_END_STRONG).match()) {
                    logger.info("??????????????????");
                    continue;
                }
                List<Selectable> tdContentList = eachtrContentList.xpath(XPATH_TD_ALL_TEXT).nodes();
                logger.info(CONTENT_SERIAL_NUM + tdContentList.get(0).toString());
                logger.info(CONTENT_QUERY_DT + tdContentList.get(1).toString());
                queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_DT.getValue(), tdContentList.get(1).toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
                logger.info(CONTENT_QUERY_BY + tdContentList.get(2).toString());
                queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_BY.getValue(), tdContentList.get(2).toString());
                logger.info(CONTENT_QUERY_REASON + tdContentList.get(3).toString());
                queryLogMap.put(CreepersConstant.TCreepersQueryLogColumn.QUERY_REASON.getValue(), tdContentList.get(3).toString());
                queryLogList.add(queryLogMap);
            }
        }
    }
}


@SuppressWarnings("unused")
public void analyzeGuarantee(String content,Map<String,String> map){
    Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
    String lastWord = "";
    StringBuffer tempWord = new StringBuffer();
    int wordCount = 0;
    boolean isFirstTime = true;
    for (Term term : parse.getTerms()) {
        logger.info("========================================");
        String currentWord = term.getName();
        logger.info(currentWord);
        if (lastWord.isEmpty()) {
            lastWord = currentWord;
            logger.info("====>>" + currentWord.replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
        } else if (WORD_FOR.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_FOR.equals(lastWord)) {
            if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_NAME.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (CONTENT_ID_TYPE.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (CONTENT_ID_TYPE.equals(lastWord)) {
            if (!SPLIT_COMMA.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_COMMA.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_ID_TYPE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (CONTENT_ID_NO.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (CONTENT_ID_NO.equals(lastWord)) {
            if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.INSURED_ID_NO.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_AT.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_AT.equals(lastWord)) {
            if (!WORD_HANDLED.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (WORD_HANDLED.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.LOAN_ORG.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_GUARANTEE_CONTRACT_AMOUNT.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_GUARANTEE_CONTRACT_AMOUNT.equals(lastWord)) {
            if (!SPLIT_COMMA.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_COMMA.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEE_CONTRACT_AMOUNT.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_GUARANTEET_AMOUNT.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_GUARANTEET_AMOUNT.equals(lastWord)) {
            if (!SPLIT_PERIOD.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_PERIOD.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEET_AMOUNT.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_CUT_OFF.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_CUT_OFF.equals(lastWord)) {
            if (!SPLIT_COMMA.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_COMMA.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.STATISTICAL_DT.getValue(), tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_GUARANTEED_PRINCIPAL_BALANCE.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_GUARANTEED_PRINCIPAL_BALANCE.equals(lastWord)) {
            if (!SPLIT_PERIOD.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
            } else if (SPLIT_PERIOD.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersGuaranteeColumn.GUARANTEED_PRINCIPAL_BALANCE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                isFirstTime = true;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        }
    }
}


@SuppressWarnings("unused")
public void analyzeCcDetailOverDue(String content,Map<String,String> map){
    Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
    String lastWord = "";
    StringBuffer tempWord = new StringBuffer();
    int wordCount = 0;
    boolean isLogOut = false;
    boolean isFirstTime = true;
    if (content.contains(WORD_LOG_OUT)) {
        // ?????????
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_LOG_OUT);
        isLogOut = true;
    } else if (content.contains(WORD_INACTIVATED)) {
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_INACTIVATED);
    } else {
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_VALID);
    }
    for (Term term : parse.getTerms()) {
        logger.info("========================================");
        String currentWord = term.getName();
        logger.info(currentWord);
        if (lastWord.isEmpty()) {
            lastWord = currentWord;
            map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_DT.getValue(), currentWord.replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
        } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM_DD, lastWord)) {
            if (!WORD_GRANT.equals(currentWord)) {
                logger.info("=================>>????????????DIC??????");
                logger.info("????????????:" + tempWord.toString());
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (WORD_GRANT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_ORG.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_GRANT.equals(lastWord)) {
            if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CC_TYPE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (SPLIT_WORD_LEFT.equals(lastWord)) {
            if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.ACCOUNT_TYPE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_CUT_OFF.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_CUT_OFF.equals(lastWord) || (SPLIT_WORD_RIGHT.equals(lastWord) && CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord))) {
            if (!CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                // ??????????????????????????????
                tempWord.append(currentWord);
                logger.info("=================>>????????????DIC??????");
                logger.info("????????????:" + tempWord.toString());
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                tempWord.append(currentWord);
                map.put(CreepersConstant.TCreepersCcDetailColumn.STATISTICAL_DT.getValue(), tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, LABEL_NULL));
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                if (isLogOut) {
                    lastWord = WORD_LOG_OUT;
                }
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_CREDIT_LIMIT.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_CREDIT_LIMIT.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_COMMA.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CREDIT_LIMIT.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_OVERDRAFT_BALANCE.equals(currentWord) || WORD_OVERDRAFT_BALANCE_TWO.equals(currentWord) || WORD_OVERDRAFT_BALANCE_THR.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_OVERDRAFT_BALANCE.equals(lastWord) || WORD_OVERDRAFT_BALANCE_TWO.equals(lastWord) || WORD_OVERDRAFT_BALANCE_THR.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_PERIOD.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.OVERDRAFT_BALANCE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_LAST_FIVE_YEARS.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_LAST_FIVE_YEARS.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && WORD_MONT_COUNT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CC_OVERDRAFT_SIXTY.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_OVERDRAFT_SIXTY.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_IN.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_IN.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && WORD_MONT_COUNT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CC_OVERDRAFT_NINETY.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        }
    }
}


@Override
public Site getSite(){
    if (null == site) {
        site = Site.me().setDomain("www.pbccrc.org.cn").setRetryTimes(8).setCycleRetryTimes(8).setTimeOut(30000);
    }
    return site;
}


@SuppressWarnings("unused")
public void analyzeCcDetailNeverOverDue(String content,Map<String,String> map){
    Result parse = ToAnalysis.parse(content.replaceAll(",", ""));
    String lastWord = "";
    StringBuffer tempWord = new StringBuffer();
    int wordCount = 0;
    boolean isLogOut = false;
    boolean isFirstTime = true;
    if (content.contains(WORD_LOG_OUT)) {
        // ?????????
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_LOG_OUT);
        isLogOut = true;
    } else if (content.contains(WORD_INACTIVATED)) {
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_INACTIVATED);
    } else {
        map.put(CreepersConstant.TCreepersCcDetailColumn.CC_STATUS.getValue(), VALUE_CC_STATUS_VALID);
    }
    for (Term term : parse.getTerms()) {
        logger.info("========================================");
        String currentWord = term.getName();
        logger.info(currentWord);
        if (WORD_CUT_OFF.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_CREDIT_LIMIT.equals(currentWord) || WORD_CREDIT_LIMIT_TO_RMB.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (WORD_OVERDRAFT_BALANCE.equals(currentWord)) {
            lastWord = currentWord;
            continue;
        } else if (lastWord.isEmpty()) {
            lastWord = currentWord;
            map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_DT.getValue(), currentWord.replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, SPLIT_WORD).replace(WORD_DAY, LABEL_NULL));
        } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM_DD, lastWord)) {
            if (!WORD_GRANT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (WORD_GRANT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.GRANT_ORG.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_GRANT.equals(lastWord)) {
            if (!SPLIT_WORD_LEFT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (SPLIT_WORD_LEFT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CC_TYPE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (SPLIT_WORD_LEFT.equals(lastWord)) {
            if (!SPLIT_WORD_RIGHT.equals(currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (SPLIT_WORD_RIGHT.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.ACCOUNT_TYPE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_CUT_OFF.equals(lastWord)) {
            if (!CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                tempWord.append(currentWord);
                logger.info("=================>>????????????DIC??????");
                logger.info("????????????:" + tempWord.toString());
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (CommonMethodUtils.matches(REGEX_DATE_YYYY_MM, currentWord)) {
                tempWord.append(currentWord);
                map.put(CreepersConstant.TCreepersCcDetailColumn.STATISTICAL_DT.getValue(), tempWord.toString().replace(WORD_YEAR, SPLIT_WORD).replace(WORD_MONT, LABEL_NULL));
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                if (isLogOut) {
                    lastWord = WORD_LOG_OUT;
                }
            } else {
                logger.error("?????????????????????????????????????????????");
            }
        } else if (WORD_CREDIT_LIMIT.equals(lastWord) || WORD_CREDIT_LIMIT_TO_RMB.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && (SPLIT_COMMA.equals(currentWord) || SPLIT_PERIOD.equals(currentWord))) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.CREDIT_LIMIT.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        } else if (WORD_OVERDRAFT_BALANCE.equals(lastWord)) {
            if (CommonMethodUtils.matches(REGEX_NUMBER, currentWord)) {
                tempWord.append(currentWord);
                if (!isFirstTime) {
                    wordCount++;
                }
                isFirstTime = false;
                continue;
            } else if (!CommonMethodUtils.matches(REGEX_NUMBER, currentWord) && SPLIT_PERIOD.equals(currentWord)) {
                map.put(CreepersConstant.TCreepersCcDetailColumn.OVERDRAFT_BALANCE.getValue(), tempWord.toString());
                if (wordCount > 0) {
                    logger.info("=================>>????????????DIC??????");
                    logger.info("????????????:" + tempWord.toString());
                }
                tempWord.setLength(0);
                wordCount = 0;
                lastWord = currentWord;
                isFirstTime = true;
            } else {
                tempWord.append(currentWord);
                wordCount++;
                continue;
            }
        }
    }
}


public void main(String[] args){
    // ????????????
    Spider.create(new CreditReferenceCenterProcessor()).addPipeline(new CreditReferencePipline("/webmagic/")).addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new CreditReferenceSeleniumDownloader()).thread(2).runAsync();
// local ????????????
// Spider.create(new CreditReferenceCenterProcessor()).addPipeline(new
// CreditReferencePipline("/webmagic/"))
// .addUrl("https://ipcrs.pbccrc.org.cn/").setDownloader(new
// SimulateDownloader()).thread(2).runAsync();
// Map<String, String> map = new HashMap<String, String>();
// 1.???????????????
// String content =
// "2009???3???2????????????????????????????????????????????????????????????4202132123xxxx?????????????????????????????????????????????????????????????????????????????????????????????50,000???????????????50,000?????????2010???10???5??????????????????????????????20,000???";
// new CreditReferenceCenterProcessor().analyzeGuarantee(content, map);
// 2.????????????????????????
// String content =
// "2014???7???2???????????????????????????????????????????????????????????????????????????2016???4???,???????????????????????????17,000??????????????????0???";
// String content = "2012???3???15???????????????????????????????????????????????????????????????????????????2015???1???????????????";
// new
// CreditReferenceCenterProcessor().analyzeCcDetailNeverOverDue(content,
// map);
// 3.??????60??????
// String content =
// "2007???6???30???????????????????????????????????????????????????????????????????????????2010???10??????????????????10,000???????????????5,000?????????5?????????6??????????????????60????????????3??????????????????90??????";
// "2007???6???30?????????????????????????????????????????????????????????????????????2010???10????????????????????????10,000???????????????5,000?????????5?????????6??????????????????60????????????3??????????????????90??????";
// new CreditReferenceCenterProcessor().analyzeCcDetailOverDue(content,
// map);
// 4.??????????????????
// String content = "";
// new CreditReferenceCenterProcessor().an
// Set<String> set = map.keySet();
// for (String string : set) {
// System.out.println(string + ":" + map.get(string));
// }
}


}