package com.fosun.fc.projects.creepers.service.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.modules.utils.JsonResult.JSON_RESULT_TYPE;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListCourtDao;
import com.fosun.fc.projects.creepers.dao.CreepersListCreditDao;
import com.fosun.fc.projects.creepers.dao.CreepersListFundDao;
import com.fosun.fc.projects.creepers.dao.CreepersListInsuranceDao;
import com.fosun.fc.projects.creepers.dao.CreepersListJudgementDao;
import com.fosun.fc.projects.creepers.dao.CreepersListPatentDao;
import com.fosun.fc.projects.creepers.dao.CreepersListShixinDao;
import com.fosun.fc.projects.creepers.dao.CreepersListTourGuideDao;
import com.fosun.fc.projects.creepers.dao.CreepersListTradeMarkDao;
import com.fosun.fc.projects.creepers.dao.CreepersTourBlackListDao;
import com.fosun.fc.projects.creepers.dto.CreepersGeneralDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListCourtDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListCreditDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListFundDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListInsuranceDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListJudgementDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListPatentDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListShixinDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListTourGuideDTO;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersTourBlackListDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDishonest;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;
import com.fosun.fc.projects.creepers.entity.TCreepersListCourt;
import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;
import com.fosun.fc.projects.creepers.entity.TCreepersListFund;
import com.fosun.fc.projects.creepers.entity.TCreepersListInsurance;
import com.fosun.fc.projects.creepers.entity.TCreepersListJudgement;
import com.fosun.fc.projects.creepers.entity.TCreepersListPatent;
import com.fosun.fc.projects.creepers.entity.TCreepersListShixin;
import com.fosun.fc.projects.creepers.entity.TCreepersListTourGuide;
import com.fosun.fc.projects.creepers.entity.TCreepersListTradeMark;
import com.fosun.fc.projects.creepers.entity.TCreepersMedical;
import com.fosun.fc.projects.creepers.entity.TCreepersPatent;
import com.fosun.fc.projects.creepers.entity.TCreepersSaction;
import com.fosun.fc.projects.creepers.entity.TCreepersShixin;
import com.fosun.fc.projects.creepers.entity.TCreepersShixinAll;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasionDetail;
import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;
import com.fosun.fc.projects.creepers.redis.service.IRedisPubService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtAnnounceService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDisInfoService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.service.ICreepersCreditReferenceService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersFundService;
import com.fosun.fc.projects.creepers.service.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.service.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.service.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import com.fosun.fc.projects.creepers.service.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.service.ICreepersPatentService;
import com.fosun.fc.projects.creepers.service.ICreepersSactionService;
import com.fosun.fc.projects.creepers.service.ICreepersShixinAllService;
import com.fosun.fc.projects.creepers.service.ICreepersShixinService;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.service.ICreepersTouristBlackListService;
import com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.service.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.Interface.CreepersListCourtDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListJudgementDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListPatentDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListTradeMarkDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListShixinDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListInsuranceDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListFundDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListTourGuideDao;
import com.fosun.fc.projects.creepers.Interface.CreepersTourBlackListDao;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtAnnounceService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundService;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDisInfoService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionDetailService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristBlackListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.DTO.CreepersLoginParamDTO;
@Component("creepersListServiceImpl")
@Transactional
public class CreepersListServiceImpl implements ICreepersListService{

 private Logger logger;

@Autowired
 private  CreepersListCourtDao creepersListCourtDao;

@Autowired
 private  CreepersListCreditDao creepersListCreditDao;

@Autowired
 private  ICreepersListCreditService creepersListCreditServiceImpl;

@Autowired
 private  CreepersListJudgementDao creepersListJudgementDao;

@Autowired
 private  CreepersListPatentDao creepersListPatentDao;

@Autowired
 private  CreepersListTradeMarkDao creepersListTradeMarkDao;

@Autowired
 private  CreepersListShixinDao creepersListShixinDao;

@Autowired
 private  CreepersListInsuranceDao creepersListInsuranceDao;

@Autowired
 private  CreepersListFundDao creepersListFundDao;

@Autowired
 private  CreepersListTourGuideDao creepersListTourGuideDao;

@Autowired
 private  CreepersTourBlackListDao creepersTourBlackListDao;

@Autowired
 private  IRedisPubService redisPubService;

@Autowired
 private  ICreepersJudgementService creepersJudgementServiceImpl;

@Autowired
 private  ICreepersCourtAnnounceService creepersCourtAnnounceServiceImpl;

@Autowired
 private  ICreepersPatentService creepersPatentServiceImpl;

@Autowired
 private  ICreepersTradeMarkService creepersTradeMarkServiceImpl;

@Autowired
 private  ICreepersShixinService creepersShixinServiceImpl;

@Autowired
 private  ICreepersFundService creepersFundServiceImpl;

@Autowired
 private  ICreepersInsuranceService creepersInsuranceServiceImpl;

@Autowired
 private  ICreepersCreditReferenceService creepersCreditReferenceServiceImpl;

@Autowired
 private  ICreepersCourtCorpBondsService creepersCourtCorpBondsServiceImpl;

@Autowired
 private  ICreepersCourtDishonestyService creepersCourtDishonestyServiceImpl;

@Autowired
 private  ICreepersCourtDisInfoService creepersCourtDisInfoServiceImpl;

@Autowired
 private  ICreepersSactionService creepersSactionServiceImpl;

@Autowired
 private  ICreepersShixinAllService creepersShixinAllServiceImpl;

@Autowired
 private  ICreepersTaxEvasionService creepersTaxEvasionServiceImpl;

@Autowired
 private  ICreepersTaxEvasionDetailService creepersTaxEvasionDetailServiceImpl;

@Autowired
 private  ICreepersTouristGuideService creepersTouristGuideServiceImpl;

@Autowired
 private  ICreepersTouristBlackListService creepersTouristBlackListServiceImpl;

@Autowired
 private  ICreepersMedicalService creepersMedicalServiceImpl;

@Autowired
 private  ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;


@Override
public void deleteList(CreepersLoginParamDTO param){
// TODO Auto-generated method stub
}


@Override
public void doRecycleByParam(CreepersLoginParamDTO param){
    String requestType = param.getTaskType();
    logger.info("Entry CreepersListServiceImpl.doRecycleByUserCode!");
    logger.info("======>>>  requestType:{}", requestType);
    logger.info("======>>>  UserCode:{}", param.getLoginName());
    // 消息体格式：请求类型|企业名称
    // 数据库中没记录，调用消息层发起请求
    String message = "";
    if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType)) {
        TCreepersListInsurance entity = creepersListInsuranceDao.findTop1ByUserCode(param.getLoginName());
        param.setLoginName(entity.getUserCode());
        param.setPassword(entity.getPassword());
    } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType)) {
        // creepersListFundDao.queryListByUserCode(userCode);
        TCreepersListFund entity = creepersListFundDao.findTop1ByUserCode(param.getLoginName());
        param.setLoginName(entity.getUserCode());
        param.setPassword(entity.getPassword());
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType)) {
        TCreepersListCredit entity = creepersListCreditDao.findTop1ByUserCode(param.getLoginName());
        param.setLoginName(entity.getUserCode());
        param.setPassword(entity.getPassword());
        param.setMessageCaptchaValue(entity.getMessageCode());
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
        logger.info("======>>>  guideNo:{}", param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()));
        logger.info("======>>>  cardNo:{}", param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
        logger.info("======>>>  certNo:{}", param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CERT_NO.getValue()));
        TCreepersListTourGuide entity = new TCreepersListTourGuide();
        if (StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            entity = creepersListTourGuideDao.findByGuideNoAndCardNoAndCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue())).get(0);
        } else if (StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())) && StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            entity = creepersListTourGuideDao.findByGuideNoAndCardNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())).get(0);
        } else if ((StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) || StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))) && StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            entity = creepersListTourGuideDao.findByGuideNoOrCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue())).get(0);
        } else if ((StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) || StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            entity = creepersListTourGuideDao.findByGuideNoAndCertNoOrCardNoAndCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue())).get(0);
        }
        param.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue(), entity.getGuideNo());
        param.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue(), entity.getCardNo());
    }
    param.setTaskType(requestType);
    message = JSONObject.toJSONString(param, BaseConstant.features);
    redisPubService.sendMsg(requestType, message);
}


@SuppressWarnings({ "unchecked" })
@Override
public JsonResult<Map<String,Object>> processByParam(CreepersLoginParamDTO param){
    JsonResult<Map<String, Object>> jsonResult = null;
    Map<String, Object> map = new HashMap<String, Object>();
    String requestType = param.getTaskType();
    logger.info("Entry CreepersListServiceImpl.processByParam!");
    // 消息体格式：请求类型|企业名称
    if (processFlag(param)) {
        // 数据库中存在记录直接查询DB并返回结果
        map.put("result", this.queryInfoByParam(param));
        String message = "";
        if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
            message = "获取" + BaseConstant.TaskListType.valueOf(requestType.toUpperCase()).getNameChinese() + "成功！";
        } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType)) {
            message = "获取人行个人征信报告成功！";
            // ========================================================
            // 因个别原因导致接口字段外置，后期接口统一规范后，会将外放内容去掉！！！
            // 这个是不能容忍的扯淡需求！！！！！！！！
            // ========================================================
            // 注意！！！！！这块内容删除前，这个注释不能删！！！ by MaXin 2016-11-2 16:38:10
            // ========================================================
            Map<String, Object> result = (Map<String, Object>) map.get("result");
            CreepersGeneralDTO generalDto = (CreepersGeneralDTO) result.get(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey());
            result.putAll(BeanMapper.toMap(generalDto));
            CreepersListCreditDTO taskListDto = (CreepersListCreditDTO) result.get(CreepersConstant.TableNamesListName.T_CREEPERS_LIST_CREDIT.getMapKey());
            result.put(CreepersConstant.TCreepersListCreditColumn.IMAGE_URL.getValue(), taskListDto.getImageUrl());
        // ========================================================
        } else {
            message = "爬取信息类型:[" + requestType + "]异常，请联系爬虫组同事进行维护。";
        }
        jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.success, message, map);
    } else {
        String message = "";
        String mqMessage = "";
        if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
            message = BaseConstant.TaskListType.valueOf(requestType.toUpperCase()).getNameChinese() + "已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
        } else {
            message = "爬取信息类型:[" + requestType + "]异常，请联系爬虫组同事进行维护。";
        }
        mqMessage = JSONObject.toJSONString(param, BaseConstant.features);
        // 数据库中没记录，调用消息层发起请求
        redisPubService.sendMsg(requestType, mqMessage);
        jsonResult = new JsonResult<Map<String, Object>>(JSON_RESULT_TYPE.failure, message, map);
    }
    return jsonResult;
}


@Override
public void insertList(CreepersLoginParamDTO param){
// TODO Auto-generated method stub
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public Map queryInfoByMerName(String requestType,String merName){
    Map resultMap = new HashMap<>();
    try {
        if (BaseConstant.TaskListType.COURT_LIST.getValue().equals(requestType)) {
            List<TCreepersCourtAnnounce> list = creepersCourtAnnounceServiceImpl.findByMerName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_COURT_ANNOUNCE.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.JUDGEMENT_LIST.getValue().equals(requestType)) {
            List<TCreepersJudgement> list = creepersJudgementServiceImpl.findByMerName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_JUDGEMENT.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.PATENT_LIST.getValue().equals(requestType)) {
            List<TCreepersPatent> list = creepersPatentServiceImpl.findByMerName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_PATENT.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.TRADE_MARK_LIST.getValue().equals(requestType)) {
            List<TCreepersTradeMark> list = creepersTradeMarkServiceImpl.findByMerName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_TRADE_MARK.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.SHI_XIN_LIST.getValue().equals(requestType)) {
            List<TCreepersShixin> list = creepersShixinServiceImpl.findListByMerName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SHIXIN.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue().equals(requestType)) {
            List<TCreepersTourBlackList> list = creepersTouristBlackListServiceImpl.findListByGuideNo(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_BLACK_LIST.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.TOUR_AGENCY_BLACK_LIST.getValue().equals(requestType)) {
            List<TCreepersTourBlackList> list = creepersTouristBlackListServiceImpl.findListByAgentNameAndType(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_BLACK_LIST.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue().equals(requestType)) {
            List<TCreepersCourtCorpBonds> list = creepersCourtCorpBondsServiceImpl.findListByName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_CORP_BONDS.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue().equals(requestType)) {
            List<TCreepersCourtDishonest> list = creepersCourtDishonestyServiceImpl.findListByName(merName);
            List<TCreepersCourtDisInfo> list2 = creepersCourtDisInfoServiceImpl.findListByName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DISHONEST.getMapKey(), CommonMethodUtils.entityList(list));
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DIS_INFO.getMapKey(), CommonMethodUtils.entityList(list2));
            }
        } else if (BaseConstant.TaskListType.SACTION_LIST.getValue().equals(requestType)) {
            List<TCreepersSaction> list = creepersSactionServiceImpl.findListByName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SACTION.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.SHI_XIN_ALL_LIST.getValue().equals(requestType)) {
            List<TCreepersShixinAll> list = creepersShixinAllServiceImpl.findListByName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SHIXIN_ALL.getMapKey(), CommonMethodUtils.entityList(list));
            }
        } else if (BaseConstant.TaskListType.TAX_EVASION_LIST.getValue().equals(requestType)) {
            List<TCreepersTaxEvasion> list = creepersTaxEvasionServiceImpl.findListByName(merName);
            List<TCreepersTaxEvasionDetail> list2 = creepersTaxEvasionDetailServiceImpl.findListByName(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION.getMapKey(), CommonMethodUtils.entityList(list));
                resultMap.put(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION_DETAIL.getMapKey(), CommonMethodUtils.entityList(list2));
            }
        } else if (BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_GSP_LIST.getValue().equals(requestType)) {
            List<TCreepersMedical> list = creepersMedicalServiceImpl.findListByKey(merName);
            if (!CommonMethodUtils.isEmpty(list)) {
                resultMap.put(requestType, list.get(0).getContent());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("step:  ======>>  CreepersListServiceImpl queryInfoByMerName FALSE!!!");
        logger.error("Search Data Or BeanList to Map Exception!\n" + e.getMessage());
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(requestType);
        param.putSearchKeyWord(merName);
        param.setErrorPath(getClass().toString());
        param.setErrorInfo("Search Data Or BeanList to Map Exception!\n" + e.getMessage());
        creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
    }
    return resultMap;
}


@Override
public void doRecycleByMerName(String requestType,String merName){
    logger.info("Entry CreepersListServiceImpl.doRecycleByMerName!");
    logger.info("======>>>  requestType:{}", requestType);
    logger.info("======>>>  merName:{}", merName);
    // 消息体格式：请求类型|企业名称
    // 数据库中没记录，调用消息层发起请求
    redisPubService.sendMsg(requestType, merName);
}


@SuppressWarnings("rawtypes")
public Boolean processFlag(CreepersLoginParamDTO param){
    String requestType = param.getTaskType();
    Boolean flag = false;
    List taskList = queryListByKey(param);
    if (!CommonMethodUtils.isEmpty(taskList)) {
        String result = "";
        if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType)) {
            TCreepersListInsurance entity = (TCreepersListInsurance) taskList.get(0);
            result = entity.getFlag();
        } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType)) {
            TCreepersListFund entity = (TCreepersListFund) taskList.get(0);
            result = entity.getFlag();
        } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType)) {
            TCreepersListCredit entity = (TCreepersListCredit) taskList.get(0);
            result = entity.getFlag();
        } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
            TCreepersListTourGuide entity = (TCreepersListTourGuide) taskList.get(0);
            result = entity.getFlag();
        }
        if (BaseConstant.TaskListStatus.SUCCEED.getValue().equals(result)) {
            flag = true;
        }
    } else {
    // 对于全量的业务，不存在CreepersList表，所以都走这里，通过job启动任务。
    // 此处逻辑不要轻易修改，后果很严重！！！
    // flag = true;
    }
    return flag;
}


@SuppressWarnings("rawtypes")
@Override
public JsonResult addTaskByParam(CreepersLoginParamDTO param){
    logger.info("Entry CreepersListServiceImpl.addTaskByParam!");
    logger.info("======>>>  param:{}", param);
    // 消息体格式：请求类型|企业名称
    // 数据库中没记录，调用消息层发起请求
    String redisMessage = JSONObject.toJSONString(param, BaseConstant.features);
    redisPubService.sendMsg(param.getTaskType(), redisMessage);
    String message = "";
    if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(param.getTaskType())) {
        message = "社保信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(param.getTaskType())) {
        message = "公积金信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(param.getTaskType())) {
        message = "人行个人征信报告信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(param.getTaskType())) {
        message = "导游证信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else {
        message = "爬取信息类型:[" + param.getTaskType() + "]不存在，请联系爬虫组同事进行维护。";
    }
    return new JsonResult(JSON_RESULT_TYPE.failure, message);
}


@SuppressWarnings({ "rawtypes" })
public Map queryInfoByParam(CreepersLoginParamDTO param){
    String requestType = param.getTaskType();
    String userCode = param.getLoginName();
    Map map = new HashMap();
    if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType)) {
        map = creepersInsuranceServiceImpl.findListByCertNoForMap(userCode);
    } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType)) {
        map = creepersFundServiceImpl.findByLoginNameForMap(userCode);
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType)) {
        map = creepersCreditReferenceServiceImpl.findByLoginNameForMap(param);
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
        map = creepersTouristGuideServiceImpl.findByParamForMap(param);
    }
    return map;
}


@SuppressWarnings("rawtypes")
@Override
public List selectList(CreepersLoginParamDTO param){
    return new ArrayList<>();
}


@SuppressWarnings("rawtypes")
@Override
public List queryListByKey(CreepersLoginParamDTO param){
    logger.info("Entry CreepersListServiceImpl.queryListByKey!");
    String requestType = param.getTaskType();
    String uniqueKey = param.getLoginName();
    logger.info("requestType:{}", requestType);
    logger.info("key's value:{}", uniqueKey);
    List resultList = new ArrayList<>();
    if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(requestType)) {
        return creepersListFundDao.queryListByUserCode(param.getLoginName());
    } else if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(requestType)) {
        return creepersListInsuranceDao.queryListByUserCode(param.getLoginName());
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(requestType)) {
        return creepersListCreditDao.findByUserCode(uniqueKey);
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(requestType)) {
        if (StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            return creepersListTourGuideDao.findByGuideNoAndCardNoAndCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));
        } else if (StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())) && StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            return creepersListTourGuideDao.findByGuideNoAndCardNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()));
        } else if ((StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) || StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))) && StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            return creepersListTourGuideDao.findByGuideNoOrCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));
        } else if ((StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue())) || StringUtils.isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))) && StringUtils.isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            return creepersListTourGuideDao.findByGuideNoAndCertNoOrCardNoAndCertNo(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));
        } else {
            return resultList;
        }
    } else {
        return resultList;
    }
}


@Override
@SuppressWarnings({ "unchecked", "rawtypes" })
public Page<?> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType,String taskType){
    PageRequest pageable = buildPageRequest(pageNumber, pageSize, sortType);
    if (BaseConstant.TaskListType.COURT_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListCourt> spec = (Specification<TCreepersListCourt>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListCourt> entityPage = creepersListCourtDao.findAll(spec, pageable);
        List<TCreepersListCourt> list = entityPage.getContent();
        List<CreepersListCourtDTO> dtoList = new ArrayList<CreepersListCourtDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListCourtDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListCourtDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.JUDGEMENT_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListJudgement> spec = (Specification<TCreepersListJudgement>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListJudgement> entityPage = creepersListJudgementDao.findAll(spec, pageable);
        List<TCreepersListJudgement> list = entityPage.getContent();
        List<CreepersListJudgementDTO> dtoList = new ArrayList<CreepersListJudgementDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListJudgementDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListJudgementDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.PATENT_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListPatent> spec = (Specification<TCreepersListPatent>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListPatent> entityPage = creepersListPatentDao.findAll(spec, pageable);
        List<TCreepersListPatent> list = entityPage.getContent();
        List<CreepersListPatentDTO> dtoList = new ArrayList<CreepersListPatentDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListPatentDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListPatentDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.TRADE_MARK_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListTradeMark> spec = (Specification<TCreepersListTradeMark>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListTradeMark> entityPage = creepersListTradeMarkDao.findAll(spec, pageable);
        List<TCreepersListTradeMark> list = entityPage.getContent();
        List<CreepersListPatentDTO> dtoList = new ArrayList<CreepersListPatentDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListPatentDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListPatentDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.SHI_XIN_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListShixin> spec = (Specification<TCreepersListShixin>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListShixin> entityPage = creepersListShixinDao.findAll(spec, pageable);
        List<TCreepersListShixin> list = entityPage.getContent();
        List<CreepersListShixinDTO> dtoList = new ArrayList<CreepersListShixinDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListShixinDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListShixinDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListInsurance> spec = (Specification<TCreepersListInsurance>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListInsurance> entityPage = creepersListInsuranceDao.findAll(spec, pageable);
        List<TCreepersListInsurance> list = entityPage.getContent();
        List<CreepersListInsuranceDTO> dtoList = new ArrayList<CreepersListInsuranceDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListInsuranceDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListInsuranceDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListFund> spec = (Specification<TCreepersListFund>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListFund> entityPage = creepersListFundDao.findAll(spec, pageable);
        List<TCreepersListFund> list = entityPage.getContent();
        List<CreepersListFundDTO> dtoList = new ArrayList<CreepersListFundDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListFundDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListFundDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListCredit> spec = (Specification<TCreepersListCredit>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListCredit> entityPage = creepersListCreditDao.findAll(spec, pageable);
        List<TCreepersListCredit> list = entityPage.getContent();
        List<CreepersListCreditDTO> dtoList = new ArrayList<CreepersListCreditDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListCreditDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListCreditDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersListTourGuide> spec = (Specification<TCreepersListTourGuide>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersListTourGuide> entityPage = creepersListTourGuideDao.findAll(spec, pageable);
        List<TCreepersListTourGuide> list = entityPage.getContent();
        List<CreepersListTourGuideDTO> dtoList = new ArrayList<CreepersListTourGuideDTO>();
        dtoList = BeanMapper.mapList(list, CreepersListTourGuideDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersListTourGuideDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else if (BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue().equalsIgnoreCase(taskType)) {
        Specification<TCreepersTourBlackList> spec = (Specification<TCreepersTourBlackList>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
        Page<TCreepersTourBlackList> entityPage = creepersTourBlackListDao.findAll(spec, pageable);
        List<TCreepersTourBlackList> list = entityPage.getContent();
        List<CreepersTourBlackListDTO> dtoList = new ArrayList<CreepersTourBlackListDTO>();
        dtoList = BeanMapper.mapList(list, CreepersTourBlackListDTO.class);
        Page<?> dtoPage = new PageImpl<CreepersTourBlackListDTO>(dtoList, pageable, entityPage.getTotalElements());
        return dtoPage;
    } else {
        List dtoList = new ArrayList();
        Page<?> dtoPage = new PageImpl<>(dtoList, pageable, 0L);
        return dtoPage;
    }
}


@SuppressWarnings({ "rawtypes" })
public JsonResult addTaskByMerName(String requestType,String merName){
    logger.info("Entry CreepersListServiceImpl.addTaskByMerName!");
    logger.info("======>>>  requestType:{}", requestType);
    logger.info("======>>>  merName:{}", merName);
    // 消息体格式：请求类型|企业名称
    // 数据库中没记录，调用消息层发起请求
    redisPubService.sendMsg(requestType, merName);
    String message = "";
    if (BaseConstant.TaskListType.COURT_LIST.getValue().equals(requestType)) {
        message = "法院公告信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.JUDGEMENT_LIST.getValue().equals(requestType)) {
        message = "法院判决书信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.PATENT_LIST.getValue().equals(requestType)) {
        message = "发明专利信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.TRADE_MARK_LIST.getValue().equals(requestType)) {
        message = "商标信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.BUSI_INFO_LIST.getValue().equals(requestType)) {
        message = "工商注册信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else if (BaseConstant.TaskListType.SHI_XIN_LIST.getValue().equals(requestType)) {
        message = "失信人信息已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
    } else {
        message = "爬取信息类型:[" + requestType + "]不存在，请联系爬虫组同事进行维护。";
    }
    return new JsonResult(JSON_RESULT_TYPE.failure, message);
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public JsonResult processByMerName(String requestType,String merName){
    JsonResult jsonResult = null;
    Map map = new HashMap();
    logger.info("Entry CreepersListServiceImpl.processByMerName!");
    logger.info("======>>>  requestType:{}", requestType);
    logger.info("======>>>  merName:{}", merName);
    String message = "";
    // 判断该次查询，是否会触发爬取操作。消息体格式：请求类型|企业名称
    if (processFlag(requestType, merName)) {
        // 数据库中存在记录，则直接查询DB并返回结果
        Map resultList = this.queryInfoByMerName(requestType, merName);
        // 封装返回消息
        map.put("result", resultList);
        if (BaseConstant.TaskListType.COURT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.JUDGEMENT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.PATENT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TRADE_MARK_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.SHI_XIN_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TOUR_AGENCY_BLACK_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.COURT_CORP_BONDS_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.SACTION_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.SHI_XIN_ALL_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TAX_EVASION_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.MEDICAL_GSP_LIST.getValue().equals(requestType)) {
            if (CommonMethodUtils.isEmpty(resultList)) {
                message = merName + "：" + BaseConstant.TaskListType.valueOf(requestType.toUpperCase()).getNameChinese() + "不存在此关键字！";
            } else {
                message = "获取" + BaseConstant.TaskListType.valueOf(requestType.toUpperCase()).getNameChinese() + "信息成功！";
            }
        } else {
            message = "爬取信息类型:[" + requestType + "]异常，请联系爬虫组同事进行维护。";
        }
        jsonResult = new JsonResult(JSON_RESULT_TYPE.success, message, map);
    } else {
        // 不存在，启动爬虫爬取数据
        redisPubService.sendMsg(requestType, merName);
        // 封装返回消息
        if (BaseConstant.TaskListType.COURT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.JUDGEMENT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.PATENT_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.TRADE_MARK_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.BUSI_INFO_LIST.getValue().equals(requestType) || BaseConstant.TaskListType.SHI_XIN_LIST.getValue().equals(requestType)) {
            message = BaseConstant.TaskListType.valueOf(requestType.toUpperCase()).getNameChinese() + "已经加入待爬列表中，等待爬取。请稍后再进行信息查询。";
        } else {
            message = "爬取信息类型:[" + requestType + "]异常，请联系爬虫组同事进行维护。";
        }
        jsonResult = new JsonResult(JSON_RESULT_TYPE.failure, message, map);
    }
    return jsonResult;
}


@Override
public void updateList(CreepersLoginParamDTO param){
    String taskType = param.getTaskType();
    if (BaseConstant.TaskListType.INSURANCE_LIST.getValue().equals(taskType)) {
        creepersListInsuranceDao.updateListByUserCode(param.getLoginName(), param.getTaskStatus());
    } else if (BaseConstant.TaskListType.FUND_LIST.getValue().equals(taskType)) {
        creepersListFundDao.updateListByUserCode(param.getLoginName(), param.getTaskStatus());
    } else if (BaseConstant.TaskListType.CREDIT_REFERENCE_LIST.getValue().equals(taskType)) {
        creepersListCreditServiceImpl.updateTaskListStatus(param.getLoginName(), param.getTaskStatus());
    } else if (BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue().equals(taskType)) {
        TCreepersListTourGuide entity = creepersListTourGuideDao.findTop1ByGuideNoOrCardNo(param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()), param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
        entity.setFlag(param.getTaskStatus());
        CommonMethodUtils.setByDT(entity);
        creepersListTourGuideDao.saveAndFlush(entity);
    }
}


}