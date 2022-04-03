package org.gliderwiki.admin;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.admin.service.AdminKeywordService;
import org.gliderwiki.admin.service.AdminSpaceService;
import org.gliderwiki.admin.service.AdminWikiService;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.KeywordVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.AdminSpaceService;
import org.gliderwiki.Interface.AdminWikiService;
import org.gliderwiki.Interface.CommonService;
@Controller
public class ContentController {

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminSpaceService adminSpaceService;

@Autowired
 private  AdminWikiService adminWikiService;

@Autowired
 private  AdminKeywordService adminKeywordService;

@Autowired
 private  CommonService commonService;


@RequestMapping(value = "/admin/keyword", method = RequestMethod.GET)
public ModelAndView adminKeyword(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String startRow = StringUtil.strNullToSpace(request.getParameter("startRow"), "0");
    KeywordVo keyword = new KeywordVo();
    // 잘라올 시작점
    keyword.setStartRow(Integer.parseInt(startRow));
    // 21개
    keyword.setEndRow(SystemConst.FETCH_LIMIT_ROW);
    List<KeywordVo> keywordList = (List<KeywordVo>) adminKeywordService.getKeywordList(keyword);
    int keywordSize = 0;
    if (keywordList != null) {
        keywordSize = keywordList.size();
    }
    modelAndView.addObject("menu", "2");
    modelAndView.addObject("menuCode", "5");
    modelAndView.addObject("keywordSize", keywordSize);
    modelAndView.addObject("keywordList", keywordList);
    modelAndView.addObject("weUserIdx", loginUser.getWeUserIdx());
    modelAndView.addObject("startRow", startRow);
    // 21개
    modelAndView.addObject("limit", SystemConst.FETCH_LIMIT_ROW - 1);
    // 20개
    modelAndView.addObject("fetch_limit_row", SystemConst.FETCH_LIMIT_ROW);
    modelAndView.setViewName("admin/contents/keywordMgr");
    return modelAndView;
}


@RequestMapping(value = "/admin/wiki", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView wikiManage(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### 위키 ");
    String weUserNick = StringUtil.strNull(request.getParameter("we_user_nick"));
    String weWikiTitle = StringUtil.strNull(request.getParameter("we_wiki_title"));
    String weWikiText = StringUtil.strNull(request.getParameter("we_wiki_text"));
    String weSpaceName = StringUtil.strNull(request.getParameter("we_space_name"));
    List<WeWiki> searchWikiList = null;
    if (!"".equals(weUserNick) || !"".equals(weWikiTitle) || !"".equals(weWikiText) || !"".equals(weSpaceName)) {
        searchWikiList = adminWikiService.getWikiSearchList(weUserNick, weWikiTitle, weWikiText, weSpaceName);
    }
    int wikiSize = 0;
    if (searchWikiList != null) {
        wikiSize = searchWikiList.size();
    }
    modelAndView.addObject("menu", "2");
    modelAndView.addObject("menuCode", "4");
    modelAndView.addObject("searchWikiList", searchWikiList);
    modelAndView.addObject("wikiSize", wikiSize);
    modelAndView.addObject("weUserNick", weUserNick);
    modelAndView.addObject("weWikiTitle", weWikiTitle);
    modelAndView.addObject("weWikiText", weWikiText);
    modelAndView.addObject("weSpaceName", weSpaceName);
    modelAndView.addObject("weUserIdx", loginUser.getWeUserIdx());
    modelAndView.setViewName("admin/contents/wikiMgr");
    return modelAndView;
}


@RequestMapping(value = "/admin/preview/{weWikiIdx}", method = RequestMethod.GET)
public ModelAndView wikiPreview(Integer weWikiIdx,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### weWikiIdx : " + weWikiIdx);
    Map<String, Object> param = new HashMap<String, Object>();
    WeWiki weWiki = null;
    try {
        weWiki = commonService.getWikiInfo(weWikiIdx);
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("wikiMarkup", weWiki.getWe_wiki_markup());
        param.put("wikiTitle", weWiki.getWe_wiki_title());
        param.put("wikiRevision", weWiki.getWe_wiki_revision());
        param.put("weWikiIdx", weWikiIdx);
    } catch (Exception e) {
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("wikiMarkup", "");
        param.put("wikiTitle", "");
        param.put("wikiRevision", "");
        param.put("weWikiIdx", weWikiIdx);
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/admin/board/preview", method = RequestMethod.POST)
public ModelAndView bbsPreview(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String we_bbs_idx = request.getParameter("we_bbs_idx");
    WeBbs domain = new WeBbs();
    domain.setWe_bbs_idx(Integer.parseInt(we_bbs_idx));
    domain.setWe_use_yn("Y");
    WeBbs rntObj = (WeBbs) entityService.getRowEntity(domain);
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("result", "SUCCESS");
    param.put("status", SystemConst.CALL_SUCCESS);
    param.put("markup", rntObj.getWe_bbs_text().replaceAll("\r\n", "<BR>"));
    param.put("title", rntObj.getWe_bbs_title());
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/admin/space", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView spaceManage(MemberSessionVo loginUser,WeSpace weSpace,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### 공간관리 ");
    logger.debug("### weSpace :  " + weSpace.toString());
    List<WeSpace> spaceSearchList = null;
    if (weSpace.getWe_admin_idx() != null || (weSpace.getWe_space_name() != null && !"".equals(weSpace.getWe_space_name()))) {
        spaceSearchList = adminSpaceService.getSpaceSearchList(weSpace);
    }
    int spaceSize = 0;
    if (spaceSearchList != null) {
        spaceSize = spaceSearchList.size();
    }
    logger.debug("###spaceSize : " + spaceSize);
    WeSpace space = new WeSpace();
    space.setWe_use_yn("Y");
    // 최근 1개월(-30일) 의 공간 목록을 조회한다.
    Date month = DateUtil.getOffsetDateToDate(DateUtil.getToday(), SystemConst.SEACH_MONTH);
    List<WeSpace> spaceList = adminSpaceService.getSpaceListMonth(space, month);
    modelAndView.addObject("menu", "2");
    modelAndView.addObject("menuCode", "3");
    modelAndView.addObject("spaceList", spaceList);
    modelAndView.addObject("spaceSearchList", spaceSearchList);
    modelAndView.addObject("spaceSize", spaceSize);
    modelAndView.addObject("weSpace", weSpace);
    modelAndView.addObject("weUserIdx", loginUser.getWeUserIdx());
    modelAndView.setViewName("admin/contents/spaceMgr");
    return modelAndView;
}


@RequestMapping(value = "/admin/board", method = { RequestMethod.POST, RequestMethod.GET })
public ModelAndView adminBoard(WeBbs weBbs,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    List<WeBbs> bbsSearchList = null;
    if (weBbs.getWe_space_idx() != null || weBbs.getWe_ins_name() != null || (weBbs.getWe_bbs_title() != null && !"".equals(weBbs.getWe_bbs_text()))) {
        bbsSearchList = adminSpaceService.getBbsSearchList(weBbs);
    }
    int bbsSize = 0;
    if (bbsSearchList != null) {
        bbsSize = bbsSearchList.size();
    }
    WeSpace space = new WeSpace();
    space.setWe_use_yn("Y");
    // 공간 개설목록을 조회한다.
    List<WeSpace> spaceList = adminSpaceService.getSpaceListMonth(space, null);
    modelAndView.addObject("menu", "2");
    modelAndView.addObject("menuCode", "6");
    modelAndView.addObject("spaceList", spaceList);
    modelAndView.addObject("bbsSize", bbsSize);
    modelAndView.addObject("spaceIdx", weBbs.getWe_space_idx());
    modelAndView.addObject("weUserNick", weBbs.getWe_ins_name());
    modelAndView.addObject("weWikiTitle", weBbs.getWe_bbs_title());
    modelAndView.addObject("weWikiText", weBbs.getWe_bbs_text());
    modelAndView.addObject("bbsSearchList", bbsSearchList);
    modelAndView.setViewName("admin/contents/boardMgr");
    return modelAndView;
}


}