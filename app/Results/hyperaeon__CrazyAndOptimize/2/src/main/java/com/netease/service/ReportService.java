package com.netease.service;
 import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.netease.domain.Basic;
import com.netease.domain.Check_Record;
import com.netease.domain.Loan_Record;
import com.netease.domain.Loan_Record_Detail;
import com.netease.domain.User;
import com.netease.dto.AllInfoDTO;
import com.netease.dto.BasicInfoDTO;
import com.netease.dto.CheckRecordDTO;
import com.netease.dto.CheckRecordDetailDTO;
import com.netease.dto.LoanDetailDTO;
import com.netease.dto.LoanRecordAbstractDTO;
import com.netease.dto.LoanRecordDTO;
import com.netease.enums.CheckType;
import com.netease.enums.CredentialType;
import com.netease.enums.DetailType;
import com.netease.enums.MarriageType;
import com.netease.enums.ReportStatusType;
import com.netease.repositories.BasicRepository;
import com.netease.repositories.UserRepository;
import com.netease.util.ParseReportUtil;
import com.Interface.UserRepository;
import com.Interface.UserService;
import com.DTO.User;
@Service
public class ReportService {

 private  Logger logger;

@Autowired
 private BasicRepository basicRepository;

@Autowired
 private UserRepository userRepository;

@Autowired
 private EntityManagerFactory entityManagerFactory;

@Autowired
 private UserService userService;

@Autowired
 private BasicService basicService;

@Autowired
 private Loan_RecordService loan_RecordService;

@Autowired
 private Check_RecordService check_RecordService;


public void convertUserToDomain(BasicInfoDTO basicInfoDTO,User user){
    if (basicInfoDTO == null) {
        return;
    }
    user.setName(basicInfoDTO.getName());
    user.setCredential_number(basicInfoDTO.getCredentialNumber());
    logger.info("身份证号码：" + basicInfoDTO.getCredentialNumber());
    user.setCredential_type(CredentialType.IDENTIFICATION.getValue());
    user.setReport_status(ReportStatusType.REPORTFINISH.getValue());
    user.setIs_married(basicInfoDTO.getIsMarried());
}


public void buildCheckRecordList(List<Check_Record> recordList,Integer queryCharacter,List<CheckRecordDetailDTO> checkList,Integer reportId){
    DateTime dateTime = new DateTime();
    for (CheckRecordDetailDTO dto : checkList) {
        Check_Record checkRecord = new Check_Record();
        checkRecord.setCreate_time(dateTime);
        checkRecord.setUpdate_time(dateTime);
        checkRecord.setQuery_character(queryCharacter);
        checkRecord.setQuery_date(new Date(dto.getCheckDate().getTime()));
        checkRecord.setQuery_operator(dto.getCheckOperator());
        checkRecord.setQueryReason(dto.getCheckReason());
        checkRecord.setReportId(reportId);
        recordList.add(checkRecord);
    }
}


public Map<String,Object> getPersonCreditReport(String login_name){
    logger.info("ReportService:getPersonCreditReport");
    Map<String, Object> map = new HashMap<String, Object>();
    // 根据登录名查找用户
    User user = userService.getByLoginName(login_name);
    // 返回前端用户姓名
    map.put("name", user.getName());
    // 根据用户id查询basic表获取基础信息
    List<Basic> basicList = basicService.findByUserIdOrderByReportTime(user.getId());
    logger.info("排除前大小：" + basicList.size());
    // 剔除已经被删除的报告
    Iterator<Basic> basicIterator = basicList.iterator();
    while (basicIterator.hasNext()) {
        Basic basic = (Basic) basicIterator.next();
        if (basic.getIsDelete() != null && basic.getIsDelete() == 1) {
            basicIterator.remove();
        }
    }
    logger.info("排除后大小：" + basicList.size());
    if (basicList == null || basicList.size() == 0) {
        return map;
    }
    // 获取最新的用户报告
    Basic basic = basicList.get(0);
    Integer reportId = basic.getId();
    map.put("reportTime", basic.getReportTime().toString("yyyy-MM-dd HH:mm:ss"));
    map.put("report_number", basic.getReportNumber());
    map.put("reportId", reportId);
    // 逾期记录数量
    int overdueCount = 0;
    // 查询信贷记录表，获取信贷记录
    List<Loan_Record> loan_RecordList = loan_RecordService.findByReportId(reportId);
    for (Loan_Record record : loan_RecordList) {
        overdueCount = overdueCount + record.getOverdue_account() + record.getOverdue_ninty_account();
        if (record.getAccount_type() == 0) {
            // 信用卡记录
            map.put("credit_account_amount", record.getAccount_amount());
            map.put("credit_unpayed_account", record.getUnpayed_account());
            map.put("credit_overdue_account", record.getOverdue_account() + record.getOverdue_ninty_account());
            map.put("credit_warrant_amount", record.getWarrant_amount());
        } else if (record.getAccount_type() == 1) {
            // 购房贷款
            map.put("houseloan_account_amount", record.getAccount_amount());
            map.put("houseloan_unpayed_account", record.getUnpayed_account());
            map.put("houseloan_overdue_account", record.getOverdue_account() + record.getOverdue_ninty_account());
            map.put("houseloan_warrant_amount", record.getWarrant_amount());
        } else if (record.getAccount_type() == 2) {
            // 其他贷款
            map.put("otherloan_account_amount", record.getAccount_amount());
            map.put("otherloan_unpayed_account", record.getUnpayed_account());
            map.put("otherloan_overdue_account", record.getOverdue_account() + record.getOverdue_ninty_account());
            map.put("otherloan_warrant_amount", record.getWarrant_amount());
        } else if (record.getAccount_type() == 0) {
            // 为他人担保信
            int i;
        } else {
            // 未知
            int i;
        }
    }
    map.put("overdue", overdueCount);
    // 公共记录
    // 查询记录
    // 信用卡审批
    List<Check_Record> creditCardApprovalList = check_RecordService.findCreditCardApproval(reportId);
    logger.info("信用卡审批：" + creditCardApprovalList.size());
    // 个人查询
    List<Check_Record> personageQueryList = check_RecordService.findPersonageQuery(reportId);
    logger.info("查询记录：" + personageQueryList.size());
    // 贷后管理
    List<Check_Record> loanAfterManagerList = check_RecordService.findLoanAfterManager(reportId);
    // 贷款审批
    List<Check_Record> loanApprovalList = check_RecordService.findLoanApproval(reportId);
    map.put("creditCardApprovalList", creditCardApprovalList);
    map.put("personageQueryList", personageQueryList);
    map.put("loanAfterManagerList", loanAfterManagerList);
    map.put("loanApprovalList", loanApprovalList);
    map.put("creditCardApproval", creditCardApprovalList.size());
    map.put("personageQuery", personageQueryList.size());
    map.put("loanAfterManager", loanAfterManagerList.size());
    map.put("loanApproval", loanApprovalList.size());
    map.put("creditCardApprovalListTest", creditCardApprovalList);
    return map;
}


public void saveAll(AllInfoDTO allInfoDTO,String loginName){
    logger.info("开始保存解析后的内容...");
    // 获取userid
    User user = userRepository.getByLoginName(loginName);
    Integer userId = -1;
    if (user != null) {
        userId = user.getId();
    }
    // 1、先更新用户的基本信息，包括姓名，身份证等信息
    convertUserToDomain(allInfoDTO.getBasicInfoDTO(), user);
    userRepository.save(user);
    // 2、保存报告基本信息
    Basic basic = convertBasicToDomain(allInfoDTO.getBasicInfoDTO(), userId);
    basic = basicRepository.save(basic);
    if (basic.getId() == null || basic.getId() < 0) {
        logger.error("保存报告基本信息失败");
    }
    // 3、保存信贷记录信息
    List<Loan_Record> loanRecordList = convertLoanRecordToDomain(allInfoDTO.getLoanRecordDTO(), basic.getId());
    if (!batchInsert(loanRecordList)) {
        logger.error("批量保存报告信贷记录信息失败");
    }
    // 4、保存信贷明细记录信息
    List<Loan_Record_Detail> loanRecordDetailList = convertLoanRecordDetailToDomain(allInfoDTO.getLoanRecordDTO(), basic.getId());
    if (!batchInsert(loanRecordDetailList)) {
        logger.error("批量保存报告信贷明细记录信息失败");
    }
    // 5、保存公共记录
    // 6、保存查询记录
    List<Check_Record> checkRecordList = convertCheckRecordToDomain(allInfoDTO.getCheckRecordDTO(), basic.getId());
    if (!batchInsert(checkRecordList)) {
        logger.error("批量保存Check_Record录信息失败");
    }
    // 修改用户状态为2，表示报告已经生成
    user.setReport_status(ReportStatusType.REPORTFINISH.getValue());
    userRepository.save(user);
}


public List<Loan_Record> convertLoanRecordToDomain(LoanRecordDTO loanRecordDTO,Integer reportId){
    if (loanRecordDTO == null) {
        return null;
    }
    List<LoanRecordAbstractDTO> loanRecordAbstractDTOList = loanRecordDTO.getLoanRecordAbstractDTOList();
    List<Loan_Record> recordList = new ArrayList<Loan_Record>();
    for (LoanRecordAbstractDTO abstractDto : loanRecordAbstractDTOList) {
        Loan_Record loanRecord = new Loan_Record();
        DateTime dateTime = new DateTime();
        loanRecord.setReportId(reportId);
        loanRecord.setAccount_amount(abstractDto.getAccountAmount());
        loanRecord.setAccount_type(abstractDto.getAccountType().getValue());
        loanRecord.setOverdue_account(abstractDto.getOverdueAccount());
        loanRecord.setOverdue_ninty_account(abstractDto.getOverdueNintyAccount());
        loanRecord.setUnpayed_account(abstractDto.getUnpayedAccount());
        loanRecord.setWarrant_amount(abstractDto.getWarrantAmount());
        loanRecord.setCreate_time(dateTime);
        loanRecord.setUpdate_time(dateTime);
        recordList.add(loanRecord);
    }
    return recordList;
}


public Basic convertBasicToDomain(BasicInfoDTO basicInfoDto,Integer userId){
    Basic basic = new Basic();
    DateTime dateTime = new DateTime();
    basic.setQuery_time(new DateTime(basicInfoDto.getQueryTime()));
    basic.setReportNumber(basicInfoDto.getReportNumber());
    basic.setReportTime(new DateTime(basicInfoDto.getReportTime()));
    basic.setUserId(userId);
    basic.setCreate_time(dateTime);
    basic.setUpdate_time(dateTime);
    return basic;
}


public boolean batchInsert(List<?> list){
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    if (CollectionUtils.isNotEmpty(list)) {
        try {
            for (int i = 0; i < list.size(); i++) {
                em.persist(list.get(i));
                if (i % 50 == 0) {
                    em.flush();
                    em.clear();
                }
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    em.close();
    return true;
}


public boolean parseAndSave(String content,String loginName){
    // path = "E:/temp/creditreport.html";
    ParseReportUtil.parseBasic(content);
    saveAll(ParseReportUtil.all, loginName);
    return true;
}


public List<Check_Record> convertCheckRecordToDomain(CheckRecordDTO checkRecordDTO,Integer reportId){
    if (checkRecordDTO == null) {
        return null;
    }
    List<Check_Record> recordList = new ArrayList<Check_Record>();
    // 机构查询
    buildCheckRecordList(recordList, CheckType.ORGANIZATION.getValue(), checkRecordDTO.getOrganizationCheckList(), reportId);
    // 个人查询
    buildCheckRecordList(recordList, CheckType.PERSONAL.getValue(), checkRecordDTO.getPersonalCheckList(), reportId);
    return recordList;
}


public List<Loan_Record_Detail> convertLoanRecordDetailToDomain(LoanRecordDTO loanRecordDTO,Integer reportId){
    if (loanRecordDTO == null) {
        return null;
    }
    List<LoanDetailDTO> loanDetailDTOList = loanRecordDTO.getLoanDetailDTOList();
    List<Loan_Record_Detail> detailList = new ArrayList<Loan_Record_Detail>();
    DateTime dateTime = new DateTime();
    for (LoanDetailDTO detailDto : loanDetailDTOList) {
        List<String> contentList = detailDto.getDetailList();
        for (String content : contentList) {
            Loan_Record_Detail detail = new Loan_Record_Detail();
            detail.setAccountType(detailDto.getAccountType().getValue());
            detail.setDetailType(DetailType.NULL.getValueByDesc(detailDto.getDetailName()));
            detail.setReportId(reportId);
            detail.setDetail_content(content);
            detail.setCreate_time(dateTime);
            detail.setUpdate_time(dateTime);
            detailList.add(detail);
        }
    }
    return detailList;
}


}