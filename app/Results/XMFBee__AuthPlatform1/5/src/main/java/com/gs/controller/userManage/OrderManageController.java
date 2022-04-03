package com.gs.controller.userManage;
 import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.bean.echarts.QuarterUtil;
import com.gs.bean.echarts.WorkInFoBean;
import com.gs.common.bean;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.WorkInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gs.Interface.MaintainRecordService;
import com.gs.DTO.ComboBox4EasyUI;
import com.gs.DTO.Company;
@Controller
@RequestMapping("/Order")
public class OrderManageController {

 private  Logger logger;

@Resource
 private  WorkInfoService workInfoService;

@Resource
 private  MaintainRecordService maintainRecordService;


@ResponseBody
@RequestMapping(value = "statusOperate/{workId}/{recordId}", method = RequestMethod.POST)
public ControllerResult inactive(HttpSession session,String workId,String recordId){
    if (SessionUtil.isLogin(session)) {
        String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            if (workId != null && !workId.equals("") && recordId != null && !recordId.equals("")) {
                workInfoService.active(workId);
                maintainRecordService.updateActualEndTime(recordId);
                maintainRecordService.updateCurrentStatus("待提醒", "'" + recordId + "'");
                logger.info("确认完成工单成功");
                return ControllerResult.getSuccessResult("确认完成工单成功");
            } else {
                return ControllerResult.getFailResult("确认完成工单失败");
            }
        } else {
            logger.info("此用户无拥有确认完成工单的角色");
            return ControllerResult.getNotRoleResult("权限不足");
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@ResponseBody
@RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
public Pager4EasyUI<WorkInfo> queryByPagerDisable(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司总技师,汽车公司技师,汽车公司接待员";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("分页查询所有被禁用登记记录");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            pager.setTotalRecords(workInfoService.count((User) session.getAttribute("user"), "N"));
            List<WorkInfo> worksList = workInfoService.queryByPager(pager, "N");
            return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), worksList);
        } else {
            logger.info("此用户无拥有查询未完成工单的角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "queryAll", method = RequestMethod.GET)
public List<ComboBox4EasyUI> queryAllWork(HttpSession session){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("查询所有订单");
            List<WorkInfo> workInfosList = workInfoService.queryAll((User) session.getAttribute("user"));
            List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
            for (WorkInfo work : workInfosList) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(work.getWorkId());
                comboBox4EasyUI.setText(work.getRecordId());
                comboxs.add(comboBox4EasyUI);
            }
            return comboxs;
        } else {
            logger.info("此用户无拥有工单查询方法的角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "queryBySche", method = RequestMethod.GET)
public Pager4EasyUI<WorkInfo> queryBySche(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,车主,汽车公司总技师,汽车公司技师,汽车公司接待员";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("维修保养进度工单分页查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            pager.setTotalRecords(workInfoService.count((User) session.getAttribute("user")));
            List<WorkInfo> worksList = workInfoService.queryByPagerschelude(pager);
            return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), worksList);
        } else {
            logger.info("此用户无拥有维修保养进度分页查询的角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "update", method = RequestMethod.POST)
public ControllerResult updateWork(HttpSession session,WorkInfo workInfo){
    if (SessionUtil.isLogin(session)) {
        String roles = "汽车公司总技师,汽车公司技师,公司超级管理员,公司普通管理员";
        if (RoleUtil.checkRoles(roles)) {
            if (workInfo != null && !workInfo.equals("")) {
                logger.info("修改工单");
                System.out.println("fdjsldkfls");
                workInfoService.update(workInfo);
                return ControllerResult.getSuccessResult("修改成功");
            } else {
                return ControllerResult.getFailResult("修改失败，请输入正确的信息");
            }
        } else {
            logger.info("此用户无拥有修改工单的角色");
            return ControllerResult.getNotRoleResult("权限不足");
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@ResponseBody
@RequestMapping(value = "queryByFrontpage", method = RequestMethod.GET)
public Pager4EasyUI<WorkInfo> queryByFrontpage(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isOwnerLogin(session)) {
        String roles = "车主";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("车主维修保养进度工单分页查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("frontUser"));
            pager.setTotalRecords(workInfoService.countByFront((User) session.getAttribute("frontUser")));
            List<WorkInfo> worksList = workInfoService.queryByFront(pager);
            return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), worksList);
        } else {
            logger.info("此用户无拥有维修保养进度工单分页查询的车主角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@InitBinder
public void initBinder(WebDataBinder binder){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}


@ResponseBody
@RequestMapping(value = "blurredQuery")
public Pager4EasyUI blurredQuery(int pageNumber,int pageSize,HttpServletRequest req,HttpSession session){
    // TODO 系统所有管理员可以查看
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("登记记录模糊查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            String text = req.getParameter("text");
            String value = req.getParameter("value");
            if (text != null && text != "" && value != null && value != "") {
                List<WorkInfo> workInfos = null;
                User user = new User();
                Company company = new Company();
                if (text.equals("员工/汽车公司")) {
                    // 当多种模糊搜索条件时
                    user.setUserNickname(value);
                    company.setCompanyName(value);
                } else if (text.equals("员工")) {
                    user.setUserNickname(value);
                } else if (text.equals("汽车公司")) {
                    company.setCompanyName(value);
                }
                user.setCompany(company);
                WorkInfo workInfo = new WorkInfo();
                workInfo.setUser(user);
                pager.setUser((User) session.getAttribute("user"));
                workInfos = workInfoService.blurredQuery(pager, workInfo);
                pager.setTotalRecords(workInfoService.countByBlurred(workInfo, (User) session.getAttribute("user")));
                return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), workInfos);
            } else {
                // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                pager.setTotalRecords(workInfoService.count((User) session.getAttribute("user")));
                pager.setUser((User) session.getAttribute("user"));
                List<WorkInfo> permissions = workInfoService.queryByPager(pager);
                return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), permissions);
            }
        } else {
            logger.info("此用户无拥有查看登记记录模糊查询方法的角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "queryByPager", method = RequestMethod.GET)
public Pager4EasyUI<WorkInfo> queryByPager(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            pager.setTotalRecords(workInfoService.count((User) session.getAttribute("user"), "Y"));
            List<WorkInfo> worksList = workInfoService.queryByPager(pager, "Y");
            return new Pager4EasyUI<WorkInfo>(pager.getTotalRecords(), worksList);
        } else {
            logger.info("此用户无拥有可用工单分页查询的角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "userId")
public Object queryByCondition(HttpSession session){
    if (SessionUtil.isLogin(session)) {
        return session.getAttribute("user");
    } else {
        logger.info("请先登录");
        return null;
    }
}


}