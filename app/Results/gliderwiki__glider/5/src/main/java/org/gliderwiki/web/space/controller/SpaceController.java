package org.gliderwiki.web.space.controller;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.AuthorityType;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.ImageInfo;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeSpaceUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.space.service.BoardService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.WikiService;
import org.gliderwiki.Interface.BoardService;
import org.gliderwiki.DTO.MemberSessionVo;
@Controller
@RequestMapping(value = "/space")
public class SpaceController {

 private Logger logger;

@Resource(name = "spaceService")
 private  SpaceService spaceService;

@Resource(name = "commonService")
 private  CommonService commonService;

@Resource(name = "wikiRegistService")
 private  WikiService wikiService;

@Resource(name = "boardService")
 private  BoardService boardService;


@RequestMapping(value = "/openList", method = RequestMethod.GET)
public String openList(MemberSessionVo loginUser,ModelMap model){
    // 내가 개설한 공간 가져오기
    List<Map<String, String>> mySpaceList = spaceService.getMySpaceList(loginUser.getWeUserIdx());
    model.addAttribute("list", mySpaceList);
    model.addAttribute("listType", "open");
    return "/space/list";
}


@RequestMapping(value = "/group/search", method = RequestMethod.GET)
@ResponseBody
public List<WeGroupInfo> groupSearch(){
    List<WeGroupInfo> allGroupList = spaceService.getAllGroupList();
    logger.debug("모든 그룹리스트 가져오기 : {}", allGroupList);
    return allGroupList;
}


@RequestMapping(value = "/entryReject", method = RequestMethod.POST)
@ResponseBody
public WebConstant entryReject(int spaceJoinIdx){
    int result = spaceService.entryReject(spaceJoinIdx);
    if (result > 0) {
        return WebConstant.SUCCESS;
    }
    return WebConstant.FAIL;
}


@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
@ResponseBody
public int userDelete(MemberSessionVo loginUser,int spaceIdx,int userIdx,String type){
    int result = spaceService.userDeleted(spaceIdx, userIdx, type);
    return result;
}


@RequestMapping(value = "/joinRequest", method = RequestMethod.POST)
@ResponseBody
public int joinRequest(MemberSessionVo loginUser,int spaceIdx){
    int result = spaceService.joinRequest(loginUser.getWeUserIdx(), spaceIdx);
    return result;
}


@RequestMapping(value = "/joinManage/{spaceIdx}", method = RequestMethod.GET)
public String joinManage(MemberSessionVo loginUser,int spaceIdx,ModelMap model){
    model.addAttribute("entryList", spaceService.getMyEntryRequestedList(spaceIdx));
    model.addAttribute("inviteList", spaceService.getMyInviteList(spaceIdx));
    return "/space/joinManage";
}


@RequestMapping(value = "/checkSearchSpaceInfo", method = RequestMethod.POST)
@ResponseBody
public WebConstant nameDuplicateCheck(MemberSessionVo loginUser,AuthorityType type,int spaceIdx){
    WebConstant result = spaceService.checkSearchSpaceInfo(type, spaceIdx, loginUser.getWeUserIdx(), "view");
    return result;
}


@RequestMapping(value = "/entryApprove", method = RequestMethod.POST)
@ResponseBody
public WebConstant entryApprove(MemberSessionVo loginUser,Integer spaceIdx,Integer userIdx,Integer spaceJoinIdx){
    WeSpaceUser weSpaceUser = new WeSpaceUser(spaceIdx, userIdx, false, false, true);
    int result = spaceService.entryApprove(spaceJoinIdx, weSpaceUser);
    if (result > 0) {
        return WebConstant.SUCCESS;
    }
    return WebConstant.FAIL;
}


@RequestMapping(value = "/{spaceIdx}", method = RequestMethod.GET)
public String show(MemberSessionVo loginUser,int spaceIdx,ModelMap model){
    SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);
    model.addAttribute("we_space_idx", spaceIdx);
    model.addAttribute("spaceInfo", spaceInfoVo);
    model.addAttribute("adminName", commonService.getUserInfo(spaceInfoVo.getAdminUserIdx()).getWe_user_nick());
    return "/space/show";
}


@RequestMapping(value = "/create", method = RequestMethod.PUT)
public String update(MemberSessionVo loginUser,HttpServletRequest request,WeSpace weSpace){
    logger.debug("저장할 Space 객체 : {}", weSpace);
    String tempImgPath = (String) request.getAttribute("tempRootPath");
    String realImgPath = (String) request.getAttribute("realRootPath");
    ImageInfo imageInfo = new ImageInfo(tempImgPath, realImgPath);
    logger.debug("임시업로드루트 : {}, 실제업로드루트 : {}", tempImgPath, realImgPath);
    spaceService.update(weSpace, loginUser.getWeUserIdx(), imageInfo);
    return "redirect:/space";
}


@RequestMapping(value = "/main/{spaceIdx}", method = RequestMethod.GET)
public String main(int spaceIdx,MemberSessionVo loginUser,ModelMap model){
    SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);
    List<WeWiki> wikiList = wikiService.getWikiList(spaceInfoVo.getSpaceIdx());
    model.addAttribute("spaceInfo", spaceInfoVo);
    model.addAttribute("wikiList", wikiList);
    model.addAttribute("boardList", boardService.getRecentList(spaceIdx));
    model.addAttribute("updatedList", spaceService.getUpdatedList(spaceIdx, 0, 5));
    model.addAttribute("fileList", spaceService.getRecentFileList(spaceIdx));
    return "/space/main";
}


@RequestMapping(value = "", method = RequestMethod.GET)
public ModelAndView list(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    List<Map<String, String>> list = spaceService.getAllSpaceList(loginUser.getWeUserIdx(), loginUser.getWeGrade());
    logger.debug("생성된 리스트 객체 사이즈 : {}", list.size());
    modelAndView.addObject("list", list);
    modelAndView.addObject("listType", "all");
    modelAndView.setViewName("/space/list");
    return modelAndView;
}


@RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
@ResponseBody
public int addFavorite(MemberSessionVo loginUser,FavorityType type,int spaceIdx,int wikiIdx){
    int result = commonService.addFavorite(loginUser.getWeUserIdx(), type, spaceIdx, wikiIdx);
    return result;
}


@RequestMapping(value = "/form", method = RequestMethod.GET)
public String createForm(MemberSessionVo loginUser,ModelMap model){
    model.addAttribute("WeSpace", new WeSpace());
    model.addAttribute("adminName", loginUser.getWeUserNick());
    return "/space/form";
}


@RequestMapping(value = "/logo/preUpload", method = RequestMethod.POST)
public ModelAndView logoImagePreUpload(MemberSessionVo loginUser,HttpServletRequest request){
    String tempRootPath = (String) request.getAttribute("tempRootPath");
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    MultipartFile file = multipartRequest.getFile("uploadFile");
    Map<String, String> result = spaceService.tempFileUpload(file, tempRootPath, Integer.toString(loginUser.getWeUserIdx()));
    logger.debug("param : {}", result.toString());
    return new ModelAndView("json_").addObject("param", result);
}


@RequestMapping(value = "/entryList", method = RequestMethod.GET)
public String entryList(MemberSessionVo loginUser,ModelMap model){
    // 내가 가입한 공간 가져오기
    List<Map<String, String>> myEntryList = spaceService.getMyEntryList(loginUser.getWeUserIdx());
    model.addAttribute("list", myEntryList);
    model.addAttribute("listType", "entry");
    return "/space/list";
}


@RequestMapping(value = "/create", method = RequestMethod.POST)
public String create(MemberSessionVo loginUser,HttpServletRequest request,WeSpace weSpace){
    logger.debug("저장할 Space 객체 : {}", weSpace);
    String tempImgPath = (String) request.getAttribute("tempRootPath");
    String realImgPath = (String) request.getAttribute("realRootPath");
    ImageInfo imageInfo = new ImageInfo(tempImgPath, realImgPath);
    logger.debug("임시업로드루트 : {}, 실제업로드루트 : {}", tempImgPath, realImgPath);
    spaceService.create(weSpace, loginUser.getWeUserIdx(), imageInfo);
    return "redirect:/space/openList";
}


@RequestMapping(value = "/user/search", method = RequestMethod.GET)
@ResponseBody
public List<GroupUserVo> userSearch(int groupIdx){
    logger.debug("search GroupIdx : {}", groupIdx);
    List<GroupUserVo> groupUserList = spaceService.getGroupUserList(groupIdx);
    return groupUserList;
}


@RequestMapping(value = "/group/delete", method = RequestMethod.POST)
@ResponseBody
public int groupDelete(MemberSessionVo loginUser,int spaceIdx,int groupIdx,String type){
    logger.debug("spaceIdx, groupIdx : {}{}", spaceIdx, groupIdx);
    int result = spaceService.groupDeleted(spaceIdx, groupIdx, type);
    return result;
}


@RequestMapping(value = "/{spaceIdx}/form", method = RequestMethod.GET)
public String updateForm(MemberSessionVo loginUser,int spaceIdx,ModelMap model){
    WeSpace weSpace = spaceService.getWeSpaceInfo(spaceIdx);
    Map<String, String> policyData = spaceService.getAuthorityData(weSpace);
    weSpace.setWe_view_data(policyData.get("viewData"));
    weSpace.setWe_view_name(policyData.get("viewName"));
    weSpace.setWe_edit_data(policyData.get("editData"));
    weSpace.setWe_edit_name(policyData.get("editName"));
    String spaceImage = spaceService.getSpaceImageInfo(weSpace.getWe_space_idx());
    // 메뉴 정보에서 spaceInfo 객체를 쓰기위해 추가한다.
    SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);
    model.addAttribute("spaceInfo", spaceInfoVo);
    model.addAttribute("WeSpace", weSpace);
    model.addAttribute("spaceImage", spaceImage);
    model.addAttribute("adminName", commonService.getUserInfo(weSpace.getWe_ins_user()).getWe_user_nick());
    return "/space/form";
}


@RequestMapping(value = "/inviteRequest", method = RequestMethod.POST)
@ResponseBody
public WebConstant inviteRequest(MemberSessionVo loginUser,String arrUserIdx,int spaceIdx){
    WebConstant result = spaceService.inviteRequest(loginUser.getWeUserIdx(), spaceIdx, arrUserIdx);
    return result;
}


@RequestMapping(value = "/createSpaceCheck", method = RequestMethod.GET)
@ResponseBody
public WebConstant authorityCheckFromCreateSpace(MemberSessionVo loginUser){
    return WebConstant.SUCCESS;
}


}