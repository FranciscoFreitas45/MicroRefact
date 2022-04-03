package com.easyshopping.controller.shop.member;
 import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.easyshopping.Pageable;
import com.easyshopping.controller.shop.BaseController;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Message;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.Interface.MessageService;
@Controller("shopMemberMessageController")
@RequestMapping("/member/message")
public class MessageController extends BaseController{

 private  int PAGE_SIZE;

@Resource(name = "messageServiceImpl")
 private MessageService messageService;

@Resource(name = "memberServiceImpl")
 private MemberService memberService;


@RequestMapping(value = "/view", method = RequestMethod.GET)
public String view(Long id,Model model){
    Message message = messageService.find(id);
    if (message == null || message.getIsDraft() || message.getForMessage() != null) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if ((!member.equals(message.getSender()) && !member.equals(message.getReceiver())) || (member.equals(message.getReceiver()) && message.getReceiverDelete()) || (member.equals(message.getSender()) && message.getSenderDelete())) {
        return ERROR_VIEW;
    }
    if (member.equals(message.getReceiver())) {
        message.setReceiverRead(true);
    } else {
        message.setSenderRead(true);
    }
    messageService.update(message);
    model.addAttribute("memberMessage", message);
    return "/shop/member/message/view";
}


@RequestMapping(value = "/draft", method = RequestMethod.GET)
public String draft(Integer pageNumber,Model model){
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    Member member = memberService.getCurrent();
    model.addAttribute("page", messageService.findDraftPage(member, pageable));
    return "/shop/member/message/draft";
}


@RequestMapping(value = "/check_username", method = RequestMethod.GET)
@ResponseBody
public boolean checkUsername(String username){
    if (!StringUtils.equalsIgnoreCase(username, memberService.getCurrentUsername()) && memberService.usernameExists(username)) {
        return true;
    }
    return false;
}


@RequestMapping(value = "/reply", method = RequestMethod.POST)
public String reply(Long id,String content,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!isValid(Message.class, "content", content)) {
        return ERROR_VIEW;
    }
    Message forMessage = messageService.find(id);
    if (forMessage == null || forMessage.getIsDraft() || forMessage.getForMessage() != null) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    if ((!member.equals(forMessage.getSender()) && !member.equals(forMessage.getReceiver())) || (member.equals(forMessage.getReceiver()) && forMessage.getReceiverDelete()) || (member.equals(forMessage.getSender()) && forMessage.getSenderDelete())) {
        return ERROR_VIEW;
    }
    Message message = new Message();
    message.setTitle("reply: " + forMessage.getTitle());
    message.setContent(content);
    message.setIp(request.getRemoteAddr());
    message.setIsDraft(false);
    message.setSenderRead(true);
    message.setReceiverRead(false);
    message.setSenderDelete(false);
    message.setReceiverDelete(false);
    message.setSender(member);
    message.setReceiver(member.equals(forMessage.getReceiver()) ? forMessage.getSender() : forMessage.getReceiver());
    if ((member.equals(forMessage.getReceiver()) && !forMessage.getSenderDelete()) || (member.equals(forMessage.getSender()) && !forMessage.getReceiverDelete())) {
        message.setForMessage(forMessage);
    }
    messageService.save(message);
    if (member.equals(forMessage.getSender())) {
        forMessage.setSenderRead(true);
        forMessage.setReceiverRead(false);
    } else {
        forMessage.setSenderRead(false);
        forMessage.setReceiverRead(true);
    }
    messageService.update(forMessage);
    if ((member.equals(forMessage.getReceiver()) && !forMessage.getSenderDelete()) || (member.equals(forMessage.getSender()) && !forMessage.getReceiverDelete())) {
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:view.jhtml?id=" + forMessage.getId();
    } else {
        addFlashMessage(redirectAttributes, com.easyshopping.Message.success("shop.member.message.replySuccess"));
        return "redirect:list.jhtml";
    }
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Integer pageNumber,Model model){
    Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
    Member member = memberService.getCurrent();
    model.addAttribute("page", messageService.findPage(member, pageable));
    return "/shop/member/message/list";
}


@RequestMapping(value = "/send", method = RequestMethod.POST)
public String send(Long draftMessageId,String username,String title,String content,Boolean isDraft,HttpServletRequest request,RedirectAttributes redirectAttributes){
    if (!isValid(Message.class, "content", content)) {
        return ERROR_VIEW;
    }
    Member member = memberService.getCurrent();
    Message draftMessage = messageService.find(draftMessageId);
    if (draftMessage != null && draftMessage.getIsDraft() && member.equals(draftMessage.getSender())) {
        messageService.delete(draftMessage);
    }
    Member receiver = null;
    if (StringUtils.isNotEmpty(username)) {
        receiver = memberService.findByUsername(username);
        if (member.equals(receiver)) {
            return ERROR_VIEW;
        }
    }
    Message message = new Message();
    message.setTitle(title);
    message.setContent(content);
    message.setIp(request.getRemoteAddr());
    message.setIsDraft(isDraft);
    message.setSenderRead(true);
    message.setReceiverRead(false);
    message.setSenderDelete(false);
    message.setReceiverDelete(false);
    message.setSender(member);
    message.setReceiver(receiver);
    messageService.save(message);
    if (isDraft) {
        addFlashMessage(redirectAttributes, com.easyshopping.Message.success("shop.member.message.saveDraftSuccess"));
        return "redirect:draft.jhtml";
    } else {
        addFlashMessage(redirectAttributes, com.easyshopping.Message.success("shop.member.message.sendSuccess"));
        return "redirect:list.jhtml";
    }
}


@RequestMapping(value = "delete", method = RequestMethod.POST)
@ResponseBody
public com.easyshopping.Message delete(Long id){
    Member member = memberService.getCurrent();
    messageService.delete(id, member);
    return SUCCESS_MESSAGE;
}


}