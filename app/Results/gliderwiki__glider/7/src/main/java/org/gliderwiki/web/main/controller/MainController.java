package org.gliderwiki.web.main.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.CommonUtil;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.main.service.MainService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.keyword.service.TagService;
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
public class MainController {

 private Logger logger;

 public  String wikiRecentType;

 public  String wikiAllType;

@Resource(name = "spaceService")
 private  SpaceService spaceService;

@Resource(name = "mainService")
 private  MainService mainService;

@Resource(name = "tagService")
 private  TagService tagService;

@Resource(name = "commonService")
 private  CommonService commonService;

@Autowired
 private  BasicDataSource defaultDataSource;


@RequestMapping(value = "/search", method = RequestMethod.GET)
public ModelAndView searchResult(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String temp = StringUtil.strNull(request.getParameter("qry"));
    // String wiki_text = new String( temp.getBytes("ISO-8859-1"),"UTF-8");
    String wiki_text = temp;
    logger.debug("#wiki_text :" + wiki_text);
    List<WikiVo> wikiList = null;
    List<WeSpace> weSpaceList = null;
    if (!"".equals(wiki_text)) {
        wikiList = commonService.getWikiSearchList(wiki_text);
    }
    int wikiSize = 0;
    if (wikiList != null) {
        wikiSize = wikiList.size();
    }
    weSpaceList = tagService.createSpaceListWithTag(wikiList);
    modelAndView.addObject("wikiList", wikiList);
    modelAndView.addObject("wikiSize", wikiSize);
    modelAndView.addObject("weSpaceList", weSpaceList);
    modelAndView.addObject("wiki_text", wiki_text);
    modelAndView.setViewName("/searchWiki");
    return modelAndView;
}


@RequestMapping(value = "/moreitem/{type}", method = RequestMethod.GET)
public ModelAndView moreItem(String type,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    Map<String, Object> param = new HashMap<String, Object>();
    Integer userIdx = Integer.parseInt(request.getParameter("userIdx"));
    Integer startRow = Integer.parseInt(request.getParameter("startRow"));
    Integer endRow = Integer.parseInt(request.getParameter("endRow"));
    List<Map<String, String>> recentList = null;
    if (type != null) {
        if (type.equals("recent") || type.equals("all")) {
            // ?????? ?????? , ?????? ??????
            recentList = mainService.getMoreWikiList(userIdx, type, startRow, endRow);
        } else if (type.equals("space")) {
            // ?????? ??????
            recentList = spaceService.getRecentSpaceList(userIdx, startRow, endRow);
        } else if (type.equals("notice")) {
            // ????????????
            recentList = mainService.getMyNotiList(userIdx, startRow, endRow);
        } else if (type.equals("update")) {
            // ?????? ????????????
            recentList = mainService.getUpdatedList(startRow, endRow);
        }
        if (recentList.size() == 0) {
            param.put("result", "0");
            param.put("recentList", null);
        } else {
            param.put("result", "1");
            param.put("recentList", recentList);
        }
    } else {
        param.put("result", "-1");
    }
    modelAndView.setViewName("/main/dashboard");
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
public String main(MemberSessionVo loginUser,ModelMap model){
    // ????????? ????????? ?????? ????????????
    List<Map<String, String>> recentSpaceList = spaceService.getRecentSpaceList(loginUser.getWeUserIdx(), 0, 5);
    // ?????? ???????????? Tag Cloud ????????? ????????????.
    List<WikiTagVo> tagVoList = tagService.getTagList();
    // ??? ???????????? ???????????? -- ????????????
    // List<Map<String, String>> myActionList = mainService.getMyActionList(loginUser.getWeUserIdx());
    // ?????? ?????? ?????? ??? ?????? ??????????????? ???????????? ????????????
    List<Map<String, String>> myNotiList = mainService.getMyNotiList(loginUser.getWeUserIdx(), 0, 5);
    // ??????????????? ???????????? ????????????
    List<Map<String, String>> recentWikiList = mainService.getWikiList(loginUser.getWeUserIdx(), wikiRecentType);
    List<Map<String, String>> allWikiList = mainService.getWikiList(loginUser.getWeUserIdx(), wikiAllType);
    // ?????? ????????? ???????????? ????????????
    int allSpaceCount = mainService.getAllSpaceCount();
    int allWikiCount = mainService.getAllWikiCount();
    // ?????? ???????????? ?????? ????????????
    List<Map<String, String>> updatedList = mainService.getUpdatedList(0, 5);
    // ?????? ????????????
    List<Map<String, String>> favorList = mainService.getFavorList();
    List<Map<String, String>> agreeList = mainService.getAgreeList();
    List<Map<String, String>> userPointList = mainService.getUserPointList();
    // model.addAttribute("myActionList", myActionList); // ??? ?????? ?????? ????????????
    // ?????? ?????? ??????
    model.addAttribute("menuAttr", "default");
    model.addAttribute("recentSpaceList", recentSpaceList);
    model.addAttribute("myNotiList", myNotiList);
    model.addAttribute("tagVoList", tagVoList);
    model.addAttribute("recentWikiList", recentWikiList);
    model.addAttribute("allWikiList", allWikiList);
    model.addAttribute("allSpaceCount", allSpaceCount);
    model.addAttribute("allWikiCount", allWikiCount);
    model.addAttribute("updatedList", updatedList);
    model.addAttribute("favorList", favorList);
    model.addAttribute("agreeList", agreeList);
    model.addAttribute("userPointList", userPointList);
    return "/main/dashboard";
}


@RequestMapping(value = "/nodeTest", method = { RequestMethod.HEAD, RequestMethod.GET })
public String nodeConnectionTest(){
    return "/main/nodeTest";
}


@RequestMapping(value = "/delFavorite", method = RequestMethod.POST)
@ResponseBody
public int delFavorite(MemberSessionVo loginUser,Integer spaceIdx,Integer wikiIdx,String type){
    FavorityType favorType;
    int addIdx;
    if (StringUtils.equals(type, "SPACE")) {
        favorType = FavorityType.SPACE;
        addIdx = spaceIdx;
    } else {
        favorType = FavorityType.WIKI;
        addIdx = wikiIdx;
    }
    int result = commonService.delFavorite(loginUser.getWeUserIdx(), favorType, addIdx);
    return result;
}


@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
public String home(MemberSessionVo loginUser,HttpServletRequest request,ModelMap model){
    String dbUrl = "";
    String driverClassName = "";
    dbUrl = defaultDataSource.getUrl();
    driverClassName = defaultDataSource.getDriverClassName();
    Cookie[] cookies = request.getCookies();
    String domain = CommonUtil.getClientDomain(request);
    logger.debug("##domain : " + domain);
    if (domain.startsWith("http://www.gliderwiki") || domain.startsWith("http://gliderwiki")) {
        model.addAttribute("linkYn", "Y");
    } else {
        model.addAttribute("linkYn", "N");
    }
    // ??????????????? ???????????? ?????? ???????????? ????????? ?????? ??????
    logger.debug("##cookies : " + cookies);
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            logger.debug("cookie : " + cookies[i].getName());
            if (StringUtils.equals("SPRING_SECURITY_REMEMBER_ME_COOKIE", cookies[i].getName())) {
                return "redirect:/dashboard";
            }
        }
    }
    if (dbUrl.startsWith("jdbc:mysql:") && driverClassName.equals("com.mysql.jdbc.Driver")) {
        // MySQL ??? ?????? ????????? ??? ??????????????? ????????? ?????? ?????????
        // URL : jdbc:mysql://localhost:3306/wiki
        // CLASS NAME : com.mysql.jdbc.Driver
        logger.debug("### MYSQL Connection ###");
        List<Map<String, String>> bestWikiList = mainService.getHomeWikiList("best");
        List<Map<String, String>> recentWikiList = mainService.getHomeWikiList("recent");
        int allWikiCnt = mainService.getWikiCount();
        int allTagCnt = mainService.getTagCount();
        int myWikiCnt = mainService.getMyWikiCount(loginUser.getWeUserIdx());
        int myPeopleCnt = mainService.getMyPeopleCount(loginUser.getWeUserIdx());
        int toMePeopleCnt = mainService.getToMePeopleCount(loginUser.getWeUserIdx());
        model.addAttribute("bestWikiList", bestWikiList);
        model.addAttribute("recentWikiList", recentWikiList);
        // ????????? wiki?????? ??? ????????????
        model.addAttribute("allWikiCnt", allWikiCnt);
        model.addAttribute("allTagCnt", allTagCnt);
        model.addAttribute("myWikiCnt", myWikiCnt);
        model.addAttribute("myPeopleCnt", myPeopleCnt);
        model.addAttribute("toMePeopleCnt", toMePeopleCnt);
        model.addAttribute("visitCountInfo", mainService.getVisitCount(request));
    } else {
        // HSQL??? ?????? ?????? ???????????? ??????????????? DB Skip
        // URL : jdbc:hsqldb:mem:spring-config
        // CLASS NAME : org.hsqldb.jdbcDriver
        logger.debug("$$$ HSQL Connection $$$");
        logger.debug("$$$ dbUrl : " + dbUrl);
        logger.debug("$$$ driverClassName : " + driverClassName);
        model.addAttribute("bestWikiList", null);
        model.addAttribute("recentWikiList", null);
        model.addAttribute("allWikiCnt", 0);
        model.addAttribute("allTagCnt", 0);
        model.addAttribute("myWikiCnt", 0);
        model.addAttribute("myPeopleCnt", 0);
        model.addAttribute("toMePeopleCnt", 0);
    }
    return "/index";
}


}