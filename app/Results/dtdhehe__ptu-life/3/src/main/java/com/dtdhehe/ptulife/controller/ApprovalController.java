package com.dtdhehe.ptulife.controller;
 import com.alibaba.fastjson.JSONObject;
import com.dtdhehe.ptulife.entity.Approval;
import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.enums.ApprovalTypeEnum;
import com.dtdhehe.ptulife.service.ApprovalService;
import com.dtdhehe.ptulife.service.MailService;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.MailUtils;
import com.dtdhehe.ptulife.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.dtdhehe.ptulife.Interface.MailService;
import com.dtdhehe.ptulife.DTO.PtuUser;
import com.dtdhehe.ptulife.DTO.ResultVO;
@Controller
@RequestMapping("/approval/approvalController")
public class ApprovalController {

 private  Logger logger;

@Autowired
 private  ApprovalService approvalService;

@Autowired
 private  MailService mailService;


@RequestMapping("/getApproval")
public String getApproval(){
    return "approval/approval";
}


@RequestMapping("/getApprovalTable")
@ResponseBody
public JSONObject getApprovalTable(HttpServletRequest request,Integer rows,Integer page,String approvalType,String flag){
    // 查出当前登录用户
    PtuUser ptuUser = (PtuUser) request.getSession().getAttribute("loginUser");
    logger.info("当前用户是:" + ptuUser);
    // 对查询条件判断是否为空
    if (StringUtils.isEmpty(approvalType)) {
        approvalType = "";
    }
    Page<Approval> approvalPage;
    try {
        Pageable pageable = PageRequest.of(page, rows, Sort.Direction.DESC, "createTime");
        if (flag.equals("1")) {
            // 查看申请记录
            approvalPage = approvalService.queryApprovalByUserId(ptuUser.getUserId(), approvalType, pageable);
        } else {
            // 查看待审核记录
            approvalPage = approvalService.queryApprovalByEmail(ptuUser.getEmail(), approvalType, pageable);
        }
        List<Approval> newsList = approvalPage.getContent();
        Iterator<Approval> it = newsList.iterator();
        while (it.hasNext()) {
            Approval approval = it.next();
            // 将type转成中文含义
            String typeCode = approval.getApprovalType();
            String type = "";
            if (typeCode.equals(ApprovalTypeEnum.LEAVE.getTypeCode())) {
                type = ApprovalTypeEnum.LEAVE.getTypeText();
            }
            if (typeCode.equals(ApprovalTypeEnum.ROOM.getTypeCode())) {
                type = ApprovalTypeEnum.ROOM.getTypeText();
            }
            if (typeCode.equals(ApprovalTypeEnum.LAB.getTypeCode())) {
                type = ApprovalTypeEnum.LAB.getTypeText();
            }
            approval.setApprovalType(type);
            // 将数据库日期转成页面日期
            approval.setApprovalTime(DateUtils.date2ViewType(approval.getApprovalTime(), "date"));
            // 将多媒体状态转成中文含义
            if (approval.getMedia() != null) {
                approval.setMedia(approval.getMedia().equals("1") ? "是" : "否");
            }
            // 将审核状态转码
            switch(approval.getStatus()) {
                case "2":
                    approval.setStatus("未通过");
                    break;
                case "1":
                    approval.setStatus("已通过");
                    break;
                default:
                    approval.setStatus("审核中");
                    break;
            }
        }
        long total = approvalPage.getTotalElements();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", newsList);
        JSONObject json = new JSONObject(map);
        return json;
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return null;
}


@RequestMapping("/doRefuse")
@ResponseBody
public ResultVO doRefuse(String id){
    ResultVO resultVO = new ResultVO();
    logger.info("要拒绝的申请id为:" + id);
    if (StringUtils.isEmpty(id)) {
        logger.info("传入的申请id为空");
        resultVO.setError_msg("申请的id为null");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        approvalService.doRefuse(id);
        resultVO.setStatus("0");
        resultVO.setError_msg("操作成功");
    } catch (Exception e) {
        logger.error("审核拒绝失败,id=" + id);
        logger.error(e.getMessage());
        resultVO.setError_msg("审核拒绝失败");
        resultVO.setStatus("1");
    }
    return resultVO;
}


@RequestMapping("/doPass")
@ResponseBody
public ResultVO doPass(String id){
    ResultVO resultVO = new ResultVO();
    logger.info("要通过的申请id为:" + id);
    if (StringUtils.isEmpty(id)) {
        logger.info("传入的申请id为空");
        resultVO.setError_msg("申请id为null");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        approvalService.doPass(id);
        resultVO.setStatus("0");
        resultVO.setError_msg("操作成功");
    } catch (Exception e) {
        logger.error("审核通过失败,id=" + id);
        logger.error(e.getMessage());
        resultVO.setError_msg("审核通过失败");
        resultVO.setStatus("1");
    }
    return resultVO;
}


@RequestMapping("/saveApproval")
@ResponseBody
@Transactional(rollbackFor = Exception.class)
public ResultVO saveApproval(Approval approval){
    ResultVO resultVO = new ResultVO();
    try {
        logger.info("保存审批记录");
        approval = approvalService.save(approval);
        logger.info("审批记录保存成功,approval = " + approval);
        // 保存成功后，发送邮件给审核人邮箱
        String htmls = MailUtils.getApprovalHtml(approval.getApprovalType(), approval.getApprovalReason());
        mailService.sendHtmlMail(approval.getVerifyEmail(), "审核邮件", htmls);
        logger.info("邮件发送成功");
        resultVO.setStatus("0");
        resultVO.setError_msg("审批成功");
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        resultVO.setError_msg("审批失败");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return resultVO;
}


@RequestMapping("/getLeave")
public String getLeave(){
    return "approval/type/leave";
}


}