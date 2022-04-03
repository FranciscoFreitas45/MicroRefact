package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.constant.dictmap.NoticeMap;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.common.exception.BizExceptionEnum;
import cn.maxcj.core.log.LogObjectHolder;
import cn.maxcj.core.shiro.ShiroKit;
import cn.maxcj.modular.system.model.Notice;
import cn.maxcj.modular.system.service.INoticeService;
import cn.maxcj.modular.system.service.IUserService;
import cn.maxcj.modular.system.warpper.NoticeWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.DTO.IUserService;
import cn.maxcj.DTO.IUserService;
import cn.maxcj.DTO.IUserService;
import cn.maxcj.DTO.IUserService;
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController{

 private  String PREFIX;

@Autowired
 private  INoticeService noticeService;

@Autowired
 private  IUserService IUserService;


@RequestMapping(value = "/club_list")
@ResponseBody
public Object club_list(String condition){
    List<Map<String, Object>> list = this.noticeService.club_list(this.IUserService.getByAccount(ShiroKit.getUser().getAccount()).getDeptid());
    return super.warpObject(new NoticeWrapper(list));
}


@RequestMapping(value = "/add")
@ResponseBody
@BussinessLog(value = "新增通知", key = "title", dict = NoticeMap.class)
public Object add(Notice notice){
    if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    notice.setCreater(ShiroKit.getUser().getId());
    notice.setCreatetime(new Date());
    /**
     * 判断是否为社联成员提交
     */
    int pid = this.IUserService.isSheLian(ShiroKit.getUser().getId());
    if (pid != 24) {
        // 不是社联人员提交
        notice.setIsshelian(0);
        notice.setDeptid(this.IUserService.getByAccount(ShiroKit.getUser().getAccount()).getDeptid());
    } else {
        notice.setIsshelian(1);
    }
    notice.insert();
    return SUCCESS_TIP;
}


@RequestMapping("/getNum")
@ResponseBody
public Integer getNoticeNum(){
    return this.noticeService.noticNum();
}


@RequestMapping("/notice_add")
public String noticeAdd(){
    return PREFIX + "notice_add.html";
}


@RequestMapping("/notice_update/{noticeId}")
public String noticeUpdate(Integer noticeId,Model model){
    Notice notice = this.noticeService.selectById(noticeId);
    model.addAttribute("notice", notice);
    LogObjectHolder.me().set(notice);
    return PREFIX + "notice_edit.html";
}


@RequestMapping("/club")
public String club(){
    return PREFIX + "club_notice.html";
}


@RequestMapping("")
public String index(){
    return PREFIX + "notice.html";
}


@RequestMapping(value = "/update")
@ResponseBody
@BussinessLog(value = "修改通知", key = "title", dict = NoticeMap.class)
public Object update(Notice notice){
    if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
    }
    Notice old = this.noticeService.selectById(notice.getId());
    old.setTitle(notice.getTitle());
    old.setContent(notice.getContent());
    old.updateById();
    return SUCCESS_TIP;
}


@RequestMapping("/hello")
public String hello(){
    List<Map<String, Object>> notices = noticeService.list(null);
    super.setAttr("noticeList", notices);
    return "/noticelist.html";
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    List<Map<String, Object>> list = this.noticeService.list(condition);
    return super.warpObject(new NoticeWrapper(list));
}


@RequestMapping(value = "/delete")
@ResponseBody
@BussinessLog(value = "删除通知", key = "noticeId", dict = NoticeMap.class)
public Object delete(Integer noticeId){
    // 缓存通知名称
    LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));
    this.noticeService.deleteById(noticeId);
    return SUCCESS_TIP;
}


@RequestMapping("/club_notice")
public String club_notice(){
    Integer deptId = this.IUserService.getByAccount(ShiroKit.getUser().getAccount()).getDeptid();
    List<Map<String, Object>> notices = noticeService.club_list(deptId);
    super.setAttr("noticeList", notices);
    return "/noticelist.html";
}


}