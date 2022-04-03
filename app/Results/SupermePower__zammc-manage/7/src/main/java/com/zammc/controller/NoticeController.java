package com.zammc.controller;
 import com.zammc.domain.notice.NoticeEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeController {

@Autowired
 private  NoticeService noticeService;


@RequestMapping(value = "/toAdd")
public ModelAndView toAdd(){
    return new ModelAndView("notice/notice-add");
}


@RequestMapping(value = "/addNotice")
@ResponseBody
public Message addNotice(NoticeEntity noticeEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = noticeService.addNotice(noticeEntity, request);
    } catch (Exception e) {
        log.error("NoticeController addNotice noticeEntity -> {} Exception \n", noticeEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "添加失败");
    }
    return message;
}


@RequestMapping(value = "/deleteNotice")
@ResponseBody
public Message deleteNotice(NoticeEntity noticeEntity){
    Message message = null;
    try {
        noticeService.deleteNotice(noticeEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.失败, "删除成功");
    } catch (Exception e) {
        log.error("NoticeController deleteNotice noticeId -> {} Exception \n", noticeEntity.getNoticeId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "删除失败");
    }
    return message;
}


@RequestMapping(value = "/startUsingNotice")
@ResponseBody
public Message startUsingNotice(NoticeEntity noticeEntity){
    Message message = null;
    try {
        noticeService.startUsingNotice(noticeEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.失败, "操作成功");
    } catch (Exception e) {
        log.error("NoticeController startUsingNotice noticeId -> {} Exception \n", noticeEntity.getNoticeId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/toEdit")
public ModelAndView toEdit(NoticeEntity noticeEntity){
    ModelAndView modelAndView = new ModelAndView("notice/notice-edit");
    try {
        NoticeEntity notice = noticeService.queryNoticeById(noticeEntity);
        modelAndView.addObject("notice", notice);
    } catch (Exception e) {
        log.error("NoticeController toEdit noticeId -> {} Exception \n", noticeEntity.getNoticeId(), e);
    }
    return modelAndView;
}


@RequestMapping(value = "/queryNoticePage")
public ModelAndView queryNoticePage(NoticeEntity noticeEntity,PageBean pageBean){
    ModelAndView modelAndView = new ModelAndView("notice/notice-list");
    try {
        noticeService.queryNoticePage(noticeEntity, pageBean);
        modelAndView.addObject("page", pageBean).addObject("notice", noticeEntity);
    } catch (Exception e) {
        log.error("NoticeController queryNoticePage Exception \n", e);
    }
    return modelAndView;
}


@RequestMapping(value = "/forbiddenNotice")
@ResponseBody
public Message forbiddenNotice(NoticeEntity noticeEntity){
    Message message = null;
    try {
        noticeService.forbiddenNotice(noticeEntity);
        message = new Message(MessageStatus.SUCCESS, MessageTitle.失败, "操作成功");
    } catch (Exception e) {
        log.error("NoticeController forbiddenNotice noticeId -> {} Exception \n", noticeEntity.getNoticeId(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "操作失败");
    }
    return message;
}


@RequestMapping(value = "/editNotice")
@ResponseBody
public Message editNotice(NoticeEntity noticeEntity,HttpServletRequest request){
    Message message = null;
    try {
        message = noticeService.editNotice(noticeEntity, request);
    } catch (Exception e) {
        log.error("NoticeController editNotice noticeEntity -> {} Exception \n", noticeEntity.toString(), e);
        message = new Message(MessageStatus.FAIL, MessageTitle.失败, "修改失败");
    }
    return message;
}


}