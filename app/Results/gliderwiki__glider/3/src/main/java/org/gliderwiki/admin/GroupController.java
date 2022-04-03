package org.gliderwiki.admin;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.admin.service.AdminGroupService;
import org.gliderwiki.admin.service.AdminUserService;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.SessionUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MailSendUserVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.DTO.MemberSessionVo;
@Controller
public class GroupController {

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminUserService adminUserService;

@Autowired
 private  AdminGroupService adminGroupService;


@RequestMapping(value = "/admin/group", method = RequestMethod.GET)
public ModelAndView groupManage(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### 그룹 관리 loginUser :" + loginUser.toString());
    // 그룹 리스트를 조회한다.
    List<WeGroupInfo> groupList = adminGroupService.getGroupInfoList();
    logger.debug("userList : " + groupList.toString());
    modelAndView.addObject("menu", "1");
    modelAndView.addObject("menuCode", "2");
    modelAndView.addObject("groupList", groupList);
    modelAndView.addObject("groupListSize", groupList.size());
    modelAndView.addObject("adminUserIdx", loginUser.getWeUserIdx());
    modelAndView.setViewName("admin/group/groupMgr");
    return modelAndView;
}


@RequestMapping(value = "/admin/group/adduser", method = RequestMethod.GET)
public ModelAndView adduser(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    // TODO 중복된 사용자는 제외하고 저장한다.
    String arrayCheckId = request.getParameter("arrayCheckId");
    String checkGroupIdx = request.getParameter("checkGroupIdx");
    if (checkGroupIdx == null) {
        throw new GliderwikiException("그룹 사용자를 추가할 수 없습니다. 그룹 순번이 존재 하지 않습니다.");
    }
    Map<String, Object> param = new HashMap<String, Object>();
    int result = 0;
    try {
        result = adminGroupService.arrayInsertGroupUser(arrayCheckId, checkGroupIdx);
        if (result > 0) {
            param.put("result", "SUCCESS");
            param.put("status", SystemConst.CALL_SUCCESS);
            param.put("rtnResult", result);
            param.put("rtnMsg", "저장되었습니다.");
        } else {
            param.put("result", "FAIL");
            param.put("status", SystemConst.CALL_FAIL);
            param.put("rtnResult", -1);
            param.put("rtnMsg", "저장이 되지 않았습니다. 다시 시도 하세요");
        }
    } catch (Exception e) {
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("rtnResult", -2);
        param.put("rtnMsg", "에러가 발생하였습니다.");
    }
    return new ModelAndView("json_").addObject("param", param);
}


public boolean isParamUser(WeUser weUser){
    if (StringUtil.strNull(weUser.getWe_user_id()).equals("") && StringUtil.strNull(weUser.getWe_user_name()).equals("") && StringUtil.strNull(weUser.getWe_user_nick()).equals("")) {
        return true;
    } else {
        return false;
    }
}


@RequestMapping(value = "/admin/user", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView userManage(WeUser weUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### weUser :  " + weUser.toString());
    String awayYn = StringUtil.strNull(request.getParameter("awayYn"));
    String authYn = StringUtil.strNull(request.getParameter("authYn"));
    logger.debug("### awayYn :  " + awayYn);
    MailSendUserVo mailUserVo = new MailSendUserVo();
    List<MailSendUserVo> mailUserList = null;
    // 조회 파라미터가 없을 경우는 조회하지 않는다.
    if (!isParamUser(weUser)) {
        mailUserList = adminUserService.getUserListMailInfo(weUser);
    } else {
        weUser.setWe_user_auth_yn(authYn);
        mailUserList = adminUserService.getUserAwayList(weUser, awayYn);
    }
    // 그룹 리스트를 조회한다.
    List<WeGroupInfo> groupList = adminGroupService.getGroupInfoList();
    int mailListSize = 0;
    if (mailUserList != null) {
        mailListSize = mailUserList.size();
    }
    int groupListSize = 0;
    if (groupList != null) {
        groupListSize = groupList.size();
    }
    modelAndView.addObject("menu", "1");
    modelAndView.addObject("menuCode", "1");
    modelAndView.addObject("groupList", groupList);
    modelAndView.addObject("groupListSize", groupListSize);
    modelAndView.addObject("mailUserList", mailUserList);
    modelAndView.addObject("mailListSize", mailListSize);
    modelAndView.addObject("weUser", weUser);
    modelAndView.setViewName("admin/group/userMgr");
    return modelAndView;
}


}