package cn.gson.oasys.controller.chat;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import cn.gson.oasys.common.formValid.BindingResultVOUtil;
import cn.gson.oasys.common.formValid.MapToList;
import cn.gson.oasys.common.formValid.ResultEnum;
import cn.gson.oasys.common.formValid.ResultVO;
import cn.gson.oasys.model.dao.discuss.CommentDao;
import cn.gson.oasys.model.dao.discuss.DiscussDao;
import cn.gson.oasys.model.dao.discuss.ReplyDao;
import cn.gson.oasys.model.dao.discuss.VoteTitleListDao;
import cn.gson.oasys.model.dao.discuss.VoteTitlesUserDao;
import cn.gson.oasys.model.dao.system.TypeDao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.discuss.Comment;
import cn.gson.oasys.model.entity.discuss.Discuss;
import cn.gson.oasys.model.entity.discuss.Reply;
import cn.gson.oasys.model.entity.discuss.VoteList;
import cn.gson.oasys.model.entity.discuss.VoteTitles;
import cn.gson.oasys.model.entity.system.SystemTypeList;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.services.discuss.DiscussService;
import cn.gson.oasys.services.discuss.ReplyService;
import cn.gson.oasys.services.discuss.VoteService;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.ReplyDao;
import cn.gson.oasys.DTO.ResultVO;
@Controller
@RequestMapping("/")
public class ChatManageController {

@Autowired
 private DiscussDao discussDao;

@Autowired
 private DiscussService disService;

@Autowired
 private UserDao uDao;

@Autowired
 private TypeDao typeDao;

@Autowired
 private ReplyDao replyDao;

@Autowired
 private ReplyService replyService;

@Autowired
 private CommentDao commentDao;

@Autowired
 private VoteService voteService;

@Autowired
 private VoteTitleListDao voteTitlesDao;

@Autowired
 private VoteTitlesUserDao voteUserDao;


@RequestMapping("seediscuss")
public String seeDiscuss(Long id,Integer pageNumber,HttpSession session){
    disService.addOneDiscuss(id);
    session.removeAttribute("id");
    session.setAttribute("id", id);
    session.setAttribute("pageNumber", pageNumber);
    return "redirect:/replymanage";
}


@RequestMapping("adddiscuss")
public String addDiscuss(HttpServletRequest req,Discuss menu,VoteList voteList,BindingResult br){
    HttpSession session = req.getSession();
    Long userId = Long.parseLong(session.getAttribute("userId") + "");
    User user = uDao.findOne(userId);
    System.out.println(menu);
    ResultVO res = BindingResultVOUtil.hasErrors(br);
    // ????????????
    if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
        System.out.println("?????????????????????");
    } else {
        // ????????????
        if (!StringUtils.isEmpty(session.getAttribute("id"))) {
            Long disId = Long.parseLong(session.getAttribute("id") + "");
            Discuss discuss = discussDao.findOne(disId);
            // ????????????????????????????????????????????????????????????
            if (discuss.getTypeId() == 21) {
                VoteList vote = discuss.getVoteList();
                vote.setEndTime(voteList.getEndTime());
                voteService.savaVoteList(vote);
                System.out.println(discuss);
            }
            discuss.setModifyTime(new Date());
            discuss.setTitle(menu.getTitle());
            discuss.setContent(menu.getContent());
            disService.save(discuss);
            req.setAttribute("success", "?????????");
            System.out.println("?????????");
            return "forward:/chatmanage";
        } else {
            // ????????????
            Long typeId = Long.parseLong(req.getParameter("typeId"));
            if (menu.getTypeId() == 21) {
                String[] title2 = req.getParameterValues("votetitle");
                String[] colors = req.getParameterValues("votecolor");
                System.out.println(voteList);
                Set<VoteTitles> voteTitles = new HashSet<>();
                // ??????????????????
                for (int i = 0; i < colors.length; i++) {
                    VoteTitles voteTitle = new VoteTitles();
                    voteTitle.setColor(colors[i]);
                    voteTitle.setTitle(title2[i]);
                    voteTitle.setVoteList(voteList);
                    voteTitles.add(voteTitle);
                }
                // ?????????????????????????????????????????????
                voteList.setVoteTitles(voteTitles);
                // voteService.savaVoteList(voteList);		//????????????????????????????????????
                // ???????????????????????????????????????
                menu.setVoteList(voteList);
                System.out.println("??????????????????????????????");
            }
            menu.setVisitNum(0);
            menu.setUser(user);
            menu.setCreateTime(new Date());
            disService.save(menu);
            req.setAttribute("success", "?????????");
            System.out.println("?????????");
            return "forward:/chatmanage";
        }
    }
    return null;
}


@RequestMapping("adminmanage")
public String adminManage(int page,HttpSession session,Long userId,Model model){
    Page<Discuss> page2 = disService.paging(page, null, 1L, null, null, null);
    setPagintMess(model, page2, "/chattable", "manage", "???????????????");
    session.removeAttribute("returnUrl");
    session.setAttribute("returnUrl", "adminmanage");
    return "chat/chatmanage";
}


@RequestMapping("deletediscuss")
public String deletediscuss(HttpServletRequest req,Long userId){
    String name = req.getParameter("name");
    Long discussId = Long.parseLong(req.getParameter("discussId"));
    Integer page = Integer.parseInt(req.getParameter("page"));
    System.out.println("????????????" + page);
    Discuss discuss = discussDao.findOne(discussId);
    User user = uDao.findOne(userId);
    if (user.getSuperman()) {
    } else {
        if (Objects.equals(discuss.getUser().getUserId(), user.getUserId())) {
        } else {
            System.out.println("??????????????????????????????");
            return "redirect:/notlimit";
        }
    }
    System.out.println("?????????????????????");
    disService.deleteDiscuss(discussId);
    if ("???????????????".equals(name)) {
        return "forward:/adminmanage?page=" + page;
    } else if ("????????????".equals(name)) {
        return "forward:/chatmanage?page=" + page;
    } else if ("????????????".equals(name)) {
        return "forward:/chatlist?page=" + page;
    } else {
    }
    return "";
}


public void setPagintMess(Model model,Page<Discuss> page2,String url,String manage,String name){
    model.addAttribute("list", disService.packaging(page2.getContent()));
    model.addAttribute("page", page2);
    model.addAttribute("url", url);
    model.addAttribute("name", name);
    if (!StringUtils.isEmpty(manage)) {
        model.addAttribute("manage", "manage");
    }
}


@RequestMapping("replymanage")
public String replyManage(Model model,HttpSession session,int page,int size,Long userId){
    Long id = Long.parseLong(session.getAttribute("id") + "");
    User user = uDao.findOne(userId);
    Discuss discuss = discussDao.findOne(id);
    // ????????????vote???????????????
    voteService.voteServiceHandle(model, user, discuss);
    if (user.getSuperman()) {
        model.addAttribute("manage", "??????????????????");
    } else {
        if (Objects.equals(user.getUserId(), discuss.getUser().getUserId())) {
            model.addAttribute("manage", "??????????????????");
        }
    }
    disService.setDiscussMess(model, id, userId, page, size);
    return "chat/replaymanage";
}


public void setSomething(String baseKey,String type,String time,String visitnum,String icon,Model model){
    if (!StringUtils.isEmpty(icon)) {
        model.addAttribute("icon", icon);
        if (!StringUtils.isEmpty(type)) {
            model.addAttribute("type", type);
            if ("1".equals(type)) {
                model.addAttribute("sort", "&type=1&icon=" + icon);
            } else {
                model.addAttribute("sort", "&type=0&icon=" + icon);
            }
        }
        if (!StringUtils.isEmpty(visitnum)) {
            model.addAttribute("visitnum", visitnum);
            if ("1".equals(visitnum)) {
                model.addAttribute("sort", "&visitnum=1&icon=" + icon);
            } else {
                model.addAttribute("sort", "&visitnum=0&icon=" + icon);
            }
        }
        if (!StringUtils.isEmpty(time)) {
            model.addAttribute("time", time);
            if ("1".equals(time)) {
                model.addAttribute("sort", "&time=1&icon=" + icon);
            } else {
                model.addAttribute("sort", "&time=0&icon=" + icon);
            }
        }
    }
    if (!StringUtils.isEmpty(baseKey)) {
        model.addAttribute("baseKey", baseKey);
    }
}


@RequestMapping("writechat")
public String writeChat(HttpServletRequest req,Long userId,Model model){
    HttpSession session = req.getSession();
    session.removeAttribute("id");
    if (!StringUtils.isEmpty(req.getParameter("id"))) {
        // ?????????????????????
        Long disId = Long.parseLong(req.getParameter("id"));
        Discuss discuss = discussDao.findOne(disId);
        // ?????????????????????
        if (!Objects.isNull(discuss.getVoteList())) {
            model.addAttribute("voteList", discuss.getVoteList());
            List<VoteTitles> voteTitles = voteTitlesDao.findByVoteList(discuss.getVoteList());
            model.addAttribute("voteTitles", voteTitles);
        }
        // ???????????????????????????
        session.setAttribute("id", disId);
        model.addAttribute("discuss", discuss);
        model.addAttribute("typeName", typeDao.findOne(discuss.getTypeId()).getTypeName());
    }
    if (!StringUtils.isEmpty(req.getAttribute("success"))) {
        model.addAttribute("success", "?????????");
    }
    User user = uDao.findOne(userId);
    List<SystemTypeList> typeList = typeDao.findByTypeModel("chat");
    model.addAttribute("typeList", typeList);
    model.addAttribute("user", user);
    return "chat/writechat";
}


@RequestMapping("chatmanage")
public String chatManage(int page,Long userId,Model model,HttpSession session){
    Page<Discuss> page2 = disService.pagingMe(page, null, userId, null, null, null);
    setPagintMess(model, page2, "/metable", "manage", "????????????");
    model.addAttribute("me", "me");
    session.removeAttribute("returnUrl");
    session.setAttribute("returnUrl", "chatmanage");
    return "chat/chatmanage";
}


@RequestMapping("seetable")
public String seeTable(int page,String baseKey,String type,String time,String visitnum,String icon,Long userId,Model model){
    setSomething(baseKey, type, time, visitnum, icon, model);
    // ????????????userid???null???
    Page<Discuss> page2 = disService.paging(page, baseKey, null, type, time, visitnum);
    setPagintMess(model, page2, "/seetable", null, "????????????");
    return "chat/chattable";
}


@RequestMapping("chattable")
public String chatTable(int page,String baseKey,String type,String time,String visitnum,String icon,Long userId,Model model){
    setSomething(baseKey, type, time, visitnum, icon, model);
    Page<Discuss> page2 = disService.paging(page, baseKey, 1L, type, time, visitnum);
    setPagintMess(model, page2, "/chattable", "manage", "???????????????");
    return "chat/chattable";
}


@RequestMapping("metable")
public String meTable(int page,String baseKey,String type,String time,String visitnum,String icon,Long userId,Model model){
    setSomething(baseKey, type, time, visitnum, icon, model);
    Page<Discuss> page2 = disService.pagingMe(page, baseKey, userId, type, time, visitnum);
    setPagintMess(model, page2, "/metable", "manage", "????????????");
    return "chat/chattable";
}


@RequestMapping("chatlist")
public String chatList(int page,Model model,HttpSession session){
    Page<Discuss> page2 = disService.paging(page, null, null, null, null, null);
    setPagintMess(model, page2, "/seetable", null, "????????????");
    session.removeAttribute("returnUrl");
    session.setAttribute("returnUrl", "chatlist");
    return "chat/chatmanage";
}


}