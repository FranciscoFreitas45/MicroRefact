package com.circle.controller.commission;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.circle.pojo.commission.ExtractApp;
import com.circle.pojo.user.User;
import com.circle.service.commossion.ICommissionService;
import com.circle.utils.NumUtils;
import com.circle.utils.SystemExceptionUtil;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
@Controller
@RequestMapping("commission")
public class CommissionController {

 private  Logger logger;

@Resource
 private ICommissionService commissionService;


@RequestMapping("dealExtractApp.action")
public ModelAndView dealExtractApp(ModelAndView mav,int id,int status){
    boolean flag = commissionService.dealExtractApp(id, status);
    if (flag) {
        mav.addObject("message", "处理成功");
    } else {
        mav.addObject("message", "处理失败");
    }
    mav.setViewName("/commission/queryExtractApp.action");
    return mav;
}


@RequestMapping("queryExtractApp.action")
public ModelAndView queryExtractApp(ModelAndView mav,Page page,HttpServletRequest request){
    page = page == null ? new Page() : page;
    try {
        List<Map<String, Object>> extractApp = commissionService.queryExtractApplyList(page);
        mav.addObject("extractApp", extractApp);
        mav.addObject("page", page);
        mav.setViewName("/usercenter/commissionhistory_list.jsp");
    } catch (SPTException e) {
        SystemExceptionUtil.logSPTException(mav, logger, e);
    }
    return mav;
}


@ResponseBody
@RequestMapping("queryCommissionHistory.action")
public ModelAndView queryCommissionHistory(ModelAndView mav,Page page,HttpServletRequest request){
    page = page == null ? new Page() : page;
    try {
        User user = (User) request.getSession().getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        List<Map<String, Object>> history = commissionService.queryCommossionHistoryList(page, user.getId());
        mav.addObject("history", history);
        mav.addObject("page", page);
        mav.setViewName("/usercenter/commissionhistory_list.jsp");
        mav.addObject("menuId", "6");
    } catch (SPTException e) {
        SystemExceptionUtil.logSPTException(mav, logger, e);
    }
    return mav;
}


@RequestMapping("makeCommission.action")
public ModelAndView makeCommission(ModelAndView mav,HttpServletRequest request){
    mav.addObject("menuId", "7");
    mav.setViewName("/usercenter/link_share.jsp");
    return mav;
}


@RequestMapping("addExtractApp.action")
@ResponseBody
public String addExtractApp(ModelAndView mav,ExtractApp extractApp,HttpServletRequest request){
    JSONObject jsonResult = new JSONObject();
    boolean flag = false;
    String message = "";
    try {
        User user = (User) request.getSession().getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        extractApp.setUserId(user.getId());
        Map<String, Object> commission = commissionService.queryUserCommossion(user.getId());
        // 检查是否有佣金数据
        if (commission != null) {
            double money = Double.parseDouble(commission.get("commission").toString());
            if (extractApp.getExtractCommission() <= 0) {
                message = "提现金额错误";
            } else if (NumUtils.subZeroAndDot(extractApp.getExtractCommission() + "").indexOf(".") != -1) {
                message = "提现金额必须为整数";
            } else if (extractApp.getExtractCommission() < 10) {
                message = "提现金额需大于10元";
            } else if (extractApp.getExtractCommission() > money) {
                message = "您还没有那么多钱,请再努力赚点吧";
            } else {
                extractApp.setCommissionId(Integer.parseInt(commission.get("id").toString()));
                flag = commissionService.addExtractApp(extractApp);
                message = flag ? "提交成功" : "提交失败";
            }
        } else {
            message = "您还没有佣金,不能提现";
        }
    } catch (SPTException e) {
        message = "申请提现异常,请重试";
        SystemExceptionUtil.logSPTException(mav, logger, e);
    }
    jsonResult.put("message", message);
    jsonResult.put("flag", flag);
    return jsonResult.toString();
}


}