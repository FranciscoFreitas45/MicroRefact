package org.gliderwiki.web.space.controller;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.PageNavigation;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeBbsComment;
import org.gliderwiki.web.space.service.BoardService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.SpaceService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.DTO.MemberSessionVo;
@Controller
@RequestMapping(value = "/space/{spaceIdx}/board")
public class BoardController {

 private  int BLOCK_LIST;

 private  int BLOCK_PAGE;

 private Logger logger;

@Resource(name = "boardService")
 private  BoardService boardService;

@Resource(name = "spaceService")
 private  SpaceService spaceService;

@Autowired
 private  EntityService entityService;

@Autowired
 private  CommonService commonService;

@Autowired
 private  RequestManager requestManager;


@RequestMapping(value = "/{boardIdx}/deleteComment", method = RequestMethod.POST)
public ModelAndView deleteComment(Integer boardIdx,MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("##deleteComment");
    String weBbsCommentIdx = request.getParameter("weBbsCommentIdx");
    String weUserIdx = request.getParameter("weUserIdx");
    WeBbsComment domain = new WeBbsComment();
    domain = commonService.getWeBbsComment(weBbsCommentIdx);
    int result = 0;
    Map<String, Object> param = new HashMap<String, Object>();
    if (domain.getWe_ins_user() == Integer.parseInt(weUserIdx) || loginUser.getWeGrade() == 9) {
        domain.setWe_use_yn("N");
        domain.setWe_ins_user(loginUser.getWeUserIdx());
        domain.setWe_ins_date(new Date());
        result = entityService.updateEntity(domain);
        if (result == 1) {
            param.put("result", "SUCCESS");
            param.put("status", SystemConst.CALL_SUCCESS);
        } else {
            param.put("result", "FAIL");
            param.put("status", SystemConst.CALL_FAIL);
        }
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/form", method = RequestMethod.GET)
public String createForm(Integer spaceIdx,MemberSessionVo loginUser,ModelMap model){
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    String randomKey = StringUtil.getRandomKey();
    WeBbs weBbs = new WeBbs();
    weBbs.setWe_space_idx(spaceIdx);
    model.addAttribute("spaceIdx", spaceIdx);
    model.addAttribute("WeBbs", weBbs);
    // 인증키 생성
    model.addAttribute("randomKey", randomKey);
    return "/space/board/form";
}


@RequestMapping(value = "/{boardIdx}", method = RequestMethod.GET)
public String show(Integer spaceIdx,Integer boardIdx,MemberSessionVo loginUser,ModelMap model){
    boardService.updateHitCount(boardIdx);
    String randomKey = StringUtil.getRandomKey();
    WeBbsComment weBbsComment = new WeBbsComment();
    weBbsComment.setWe_bbs_idx(boardIdx);
    weBbsComment.setWe_use_yn("Y");
    List commentList = null;
    try {
        commentList = (List) entityService.getListEntity(weBbsComment);
    } catch (Throwable e) {
        e.printStackTrace();
    }
    model.addAttribute("bbs", boardService.show(boardIdx));
    model.addAttribute("WeBbsComment", weBbsComment);
    model.addAttribute("boardIdx", boardIdx);
    model.addAttribute("spaceIdx", spaceIdx);
    // 인증키 생성
    model.addAttribute("randomKey", randomKey);
    model.addAttribute("commentList", commentList);
    return "/space/board/show";
}


@RequestMapping(value = "/create", method = RequestMethod.POST)
public String create(HttpServletRequest request,MemberSessionVo loginUser,WeBbs weBbs){
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    weBbs.setWe_user_ip(requestManager.getRemoteAddress(request));
    weBbs.setWe_ins_user(loginUser.getWeUserIdx());
    weBbs.setWe_ins_name(loginUser.getWeUserNick());
    boardService.create(weBbs);
    return "redirect:/space/" + weBbs.getWe_space_idx() + "/board";
}


@RequestMapping(value = "/create", method = RequestMethod.PUT)
public String update(MemberSessionVo loginUser,WeBbs weBbs){
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    boardService.update(weBbs);
    return "redirect:/space/" + weBbs.getWe_space_idx() + "/board";
}


@RequestMapping(value = "/getComment", method = RequestMethod.GET)
public ModelAndView getComment(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String weBbsCommentIdx = StringUtil.strNull(request.getParameter("weBbsCommentIdx"));
    String weUserIdx = StringUtil.strNull(request.getParameter("weUserIdx"));
    String weBbsIdx = StringUtil.strNull(request.getParameter("weBbsIdx"));
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    // 보안문자열 값
    // String randomKey = StringUtil.getRandomKey();
    Map<String, String> param = new HashMap<String, String>();
    WeBbsComment domain = new WeBbsComment();
    domain.setWe_bbs_comment_idx(Integer.parseInt(weBbsCommentIdx));
    domain.setWe_ins_user(Integer.parseInt(weUserIdx));
    domain.setWe_bbs_idx(Integer.parseInt(weBbsIdx));
    domain.setWe_use_yn("Y");
    WeBbsComment bbsComment = null;
    try {
        bbsComment = (WeBbsComment) entityService.getRowEntity(domain);
    } catch (Throwable e) {
        e.printStackTrace();
    }
    if (bbsComment != null) {
        param.put("contents", bbsComment.getWe_bbs_text());
        param.put("result", "1");
    // param.put("randomKey", randomKey);
    } else {
        param.put("contents", "");
        param.put("result", "-1");
        throw new GliderwikiException("정보가 올바르지 않습니다");
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/{boardIdx}/form", method = RequestMethod.GET)
public String updateForm(Integer spaceIdx,Integer boardIdx,MemberSessionVo loginUser,ModelMap model){
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    WeBbs board = boardService.show(boardIdx);
    if (board.getWe_ins_user() != loginUser.getWeUserIdx()) {
        throw new GliderwikiException("수정권한이 없습니다.");
    }
    String randomKey = StringUtil.getRandomKey();
    model.addAttribute("WeBbs", board);
    model.addAttribute("spaceIdx", spaceIdx);
    // 인증키 생성
    model.addAttribute("randomKey", randomKey);
    return "/space/board/form";
}


@RequestMapping(value = "", method = RequestMethod.GET)
public String list(HttpServletRequest request,Integer spaceIdx,MemberSessionVo loginUser,ModelMap model){
    int nowPage = 1;
    // 현재 페이지값 세팅
    if (request.getParameter("page") != null) {
        nowPage = Integer.parseInt(request.getParameter("page"));
        if (nowPage < 1) {
            nowPage = 1;
        }
    }
    int totalCount = boardService.getArticleTotalCount(spaceIdx);
    PageNavigation pageNav = new PageNavigation(nowPage, totalCount, BLOCK_LIST, BLOCK_PAGE);
    // 메뉴에서 필요함
    SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);
    model.addAttribute("spaceInfo", spaceInfoVo);
    model.addAttribute("spaceIdx", spaceIdx);
    // 페이징 관련 뷰에게 넘길 값을 지정한다
    // 이전페이지 블록의 존재유무
    model.addAttribute("pageIsPrev", pageNav.isPrevPage());
    // 다음페이지 블록의 존재유무
    model.addAttribute("pageIsNext", pageNav.isNextPage());
    // 시작페이지 번호
    model.addAttribute("pageStart", pageNav.getStartPage());
    // 종료페이지 번호
    model.addAttribute("pageEnd", pageNav.getEndPage());
    // 현재페이지 번호
    model.addAttribute("nowPage", nowPage);
    model.addAttribute("list", boardService.getList(spaceIdx, pageNav.getStartRow(), pageNav.getBlockSize()));
    return "/space/board/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
public String delete(MemberSessionVo loginUser,int boardIdx,int spaceIdx){
    if (loginUser.isGuest()) {
        throw new GliderwikiException("로그인 정보가 올바르지 않습니다");
    }
    boardService.delete(boardIdx);
    return "redirect:/space/" + spaceIdx + "/board";
}


@RequestMapping(value = "/{boardIdx}/updateComment", method = RequestMethod.POST)
public ModelAndView updateComment(Integer boardIdx,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("##updateComment");
    String weBbsCommentIdx = request.getParameter("weBbsCommentIdx");
    String weBbsText = request.getParameter("weBbsText");
    WeBbsComment domain = new WeBbsComment();
    domain = commonService.getWeBbsComment(weBbsCommentIdx);
    domain.setWe_ins_date(new Date());
    domain.setWe_bbs_idx(boardIdx);
    domain.setWe_bbs_text(weBbsText);
    int result = entityService.updateEntity(domain);
    Map<String, Object> param = new HashMap<String, Object>();
    if (result == 1) {
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
    } else {
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/{boardIdx}/insertComment", method = RequestMethod.POST)
public String insertComment(HttpServletRequest request,Integer spaceIdx,Integer boardIdx,MemberSessionVo loginUser,WeBbsComment weBbsComment){
    String noSpam = StringUtil.strNull(request.getParameter("noSpam"));
    String randomKey = StringUtil.strNull(request.getParameter("randomKey"));
    if (!noSpam.equals(randomKey)) {
        throw new GliderwikiException("인증값이 올바르지 않습니다");
    }
    WeBbsComment domain = new WeBbsComment();
    domain.setWe_bbs_idx(boardIdx);
    domain.setWe_ins_date(new Date());
    domain.setWe_ins_user(loginUser.getWeUserIdx());
    domain.setWe_user_ip(requestManager.getRemoteAddress(request));
    domain.setWe_bbs_text(weBbsComment.getWe_bbs_text());
    domain.setWe_ins_name(weBbsComment.getWe_ins_name());
    domain.setWe_use_yn("Y");
    logger.debug("### domain : " + domain.toString());
    int result = boardService.insertBbsComment(domain);
    return "redirect:/space/" + spaceIdx + "/board/" + boardIdx;
}


}