package org.gliderwiki.web.wiki.parser.controller;
 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.exception.FilePermitMsgException;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.CommonUtil;
import org.gliderwiki.util.GliderTagPaserUtil;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.util.WikiProdectStatus;
import org.gliderwiki.web.common.DownLoadAction;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.AttachmentType;
import org.gliderwiki.web.domain.WeBbsComment;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WePoint;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiComment;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.user.service.UserActionService;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.WikiService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.UserActionService;
import org.gliderwiki.Interface.RequestManager;
import org.gliderwiki.DTO.WeSpace;
import org.gliderwiki.DTO.MemberSessionVo;
import org.gliderwiki.DTO.WeWikiComment;
import org.gliderwiki.DTO.WeUser;
import org.gliderwiki.DTO.WeProfile;
import org.gliderwiki.DTO.GliderFileUtils;
@Controller
@RequestMapping(value = "/wiki")
public class WikiController {

 private Logger logger;

@SuppressWarnings("rawtypes")
@Autowired
 private  EntityService entityService;

@Autowired
 private  WikiService wikiService;

@Autowired
 private  CommonService commonService;

@Autowired
 private  UserActionService userActionService;

@Value("#{config['file.maxSize']}")
 private String uploadMaxSize;

@Autowired
 private  RequestManager requestManager;


@SuppressWarnings("unchecked")
@RequestMapping(value = "/editor/{spaceIdx}", method = RequestMethod.GET)
public String editor(MemberSessionVo loginUser,Integer spaceIdx,WeWiki wikiForm,Model model){
    logger.debug("#### spaceIdx : {}", spaceIdx);
    // ????????? ?????? ??????
    WeTemplate temp = new WeTemplate();
    temp.setWe_use_yn("Y");
    List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(spaceIdx);
    // ?????? ?????? ??????
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    wikiForm.setWe_space_idx(spaceIdx);
    JSONArray jsonTemplate = null;
    if (template != null) {
        // ???????????????
        jsonTemplate = JSONArray.fromObject(template);
        // jsonTemplate =
        // JSONObject.fromObject(JSONSerializer.toJSON(template)); // ????????????
        // ??????
        logger.debug("### jsonTemplate : " + jsonTemplate.toString());
    } else {
        jsonTemplate = null;
    }
    model.addAttribute("editMode", "new");
    model.addAttribute("weSpace", weSpace);
    model.addAttribute("template", jsonTemplate);
    return "/wiki/editor";
}


@RequestMapping("/download")
public ModelAndView fileDownload(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String realPath = request.getSession().getServletContext().getRealPath("/");
    logger.debug("#realPath : " + realPath);
    String we_file_idx = request.getParameter("we_file_idx");
    if (we_file_idx == null) {
        throw new FilePermitMsgException("File is not exsist!!");
    }
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    WeWikiFile fileInfo = commonService.getWikiFileInfo(Integer.parseInt(we_file_idx));
    // ???????????? ?????? ??????
    String filePath = realPath + SystemConst.WIKI_FILE_DOWNLOAD_PATH;
    // ???????????? ??? ?????? ?????? ??????
    // ?????? ?????? ?????????
    HttpSession session = request.getSession();
    String doc_root = filePath + fileInfo.getWe_file_save_path() + fileInfo.getWe_file_save_name();
    String type = session.getServletContext().getMimeType(doc_root);
    DownLoadAction download = new DownLoadAction();
    // ????????? (????????????, ????????????, response, type)
    download.getFileDownload(doc_root, fileInfo.getWe_file_real_name(), response, type);
    // ?????? ????????? ????????????
    fileInfo.setWe_file_down(fileInfo.getWe_file_down() + 1);
    entityService.updateEntity(fileInfo);
    return null;
}


@RequestMapping(value = "/fullscreen/{wikiIdx}", method = RequestMethod.GET)
public ModelAndView getFullScreenView(Integer wikiIdx,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    WeWiki wikiVo = new WeWiki();
    wikiVo.setWe_wiki_idx(wikiIdx);
    wikiVo.setWe_use_yn("Y");
    // wiki body ??????
    WeWiki wikiHtml = (WeWiki) entityService.getRowEntity(wikiVo);
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    String domain = CommonUtil.getClientDomain(request);
    String htmlSource = "";
    String href = "/resource/";
    String destHref = domain + href;
    if (wikiHtml.getWe_wiki_markup().indexOf(href) != -1) {
        htmlSource = wikiHtml.getWe_wiki_markup().replaceAll(href, destHref);
    } else {
        htmlSource = wikiHtml.getWe_wiki_markup();
    }
    modelAndView.addObject("wikiHtml", wikiHtml);
    modelAndView.addObject("htmlSource", htmlSource);
    modelAndView.setViewName("/wiki/fullscreen");
    return modelAndView;
}


@ResponseBody
@SuppressWarnings("unchecked")
@RequestMapping(value = "/edit", method = RequestMethod.POST)
public JsonResponse edit(MemberSessionVo loginUser,String weTag,WeWiki wikiForm,HttpServletRequest request){
    logger.debug("### ?????? ?????? ??????  ");
    int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
    String weEditText = StringUtil.strNullToSpace(request.getParameter("weEditText"), "");
    logger.debug("####### isUpload : " + isUpload);
    wikiForm.setWe_edit_text(weEditText);
    String[] weFileIdx = request.getParameterValues("weFileIdx");
    logger.debug("####### wikiForm :::{}", wikiForm.toString());
    logger.debug("####### weTag :::{}", weTag);
    JsonResponse res = new JsonResponse();
    JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();
    // TODO TODOLIST ?????? ?????? ?????? ?????? ??????
    // ????????????, ????????????, ???????????? Validation
    // ???????????? ??????
    // ?????? ???????????? ?????? ??????
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    // if(weSpace == null) {
    // ????????? ??????
    // throw new PageMoveException("", "", 1);
    // }
    // ?????? html ??? ?????? markUp ??????, markUp??? ?????? HTML ??????
    // ????????????, ????????????, ???????????? check
    int insertWikiIdx = 0;
    try {
        if (isUpload == 0) {
            // ???????????? ??????
            insertWikiIdx = wikiService.modifiedWikiAndSaveRevision(wikiForm, weSpace, StringUtil.strNull(weTag), loginUser, request);
        } else {
            // ?????? ?????????
            insertWikiIdx = wikiService.modifiedWikiAndSaveRevision(wikiForm, weSpace, StringUtil.strNull(weTag), loginUser, request, weFileIdx);
        }
        // ????????? ?????? ?????? ?????? ??????
        logger.debug("#insertResult : {}", insertWikiIdx);
        // ?????? ????????? ?????? - ?????? ??????
        commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.UPDATE_WIKI.point);
        /**
         * TODO TODOLIST : ?????? ?????????, ?????? ??????????????? ??? ?????? ?????? ???????????? ?????? 1. ????????? ????????????
         * WE_USER_ALARM ???????????? 2(SPACE_NEW_POST) ?????? ???????????? ?????? ?????? WE_ALARM_INFO???
         * ????????????. ?????????, ????????? ?????????, ?????? ????????????, ?????? ?????????, ?????? ??????, ?????? ??????
         */
        logger.debug("### ?????? ?????? ??????");
        int count = 0;
        if (loginUser.getWeUserIdx() != wikiForm.getWe_ins_user()) {
            count = commonService.requestAlarmInfo(loginUser.getWeUserIdx(), loginUser.getWeUserNick(), SystemConst.WIKI_EDIT, wikiForm.getWe_ins_user(), wikiForm.getWe_wiki_idx(), weSpace.getWe_space_idx());
            logger.debug("### ?????? ?????? ??? : {} ", count);
        }
        result.setMessage("?????? ?????????????????????.");
        result.setRedirectUrl("/space/main/" + wikiForm.getWe_space_idx());
        logger.debug("##### result : " + result.toString());
        res.setStatus(ResponseStatus.SUCCESS);
        logger.debug("##### res : " + res.toString());
    } catch (DBHandleException e) {
        // TODOLIST Exception ????????? ???????????? ??????
        logger.debug("### DBHandleException : " + e.getCause());
        logger.debug("### DBHandleException : " + e.getMessage());
        logger.debug("### DBHandleException : " + e.getStatus());
        result.setErrorMsg(e.getMessage());
        result.setMessage("????????? ????????? ?????? ???????????????.");
        res.setStatus(ResponseStatus.FAIL);
    }
    res.setResponse(result);
    return res;
}


@RequestMapping(value = "/{wikiIdx}/deleteComment", method = RequestMethod.POST)
public ModelAndView deleteComment(Integer wikiIdx,MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("##deleteComment");
    String weWikiCommentIdx = request.getParameter("weWikiCommentIdx");
    String weUserIdx = request.getParameter("weUserIdx");
    WeWikiComment domain = new WeWikiComment();
    domain = commonService.getWeWikiComment(weWikiCommentIdx);
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


@RequestMapping("/getHtmlFile")
public void getHtmlFile(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String realPath = request.getSession().getServletContext().getRealPath("/");
    logger.debug("#realPath : " + realPath);
    String file_name = request.getParameter("file_name");
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    logger.debug("file_name : " + file_name);
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    // ???????????? ?????? ??????
    String filePath = realPath + SystemConst.WIKI_HTML_DOWNLOAD_PATH;
    String fileName = file_name;
    String fullPath = filePath + "/" + fileName;
    logger.debug("##fullPath : " + fullPath);
    File file = new File(fullPath);
    String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
    if (mimetype == null || mimetype.length() == 0) {
        mimetype = "application/octet-stream;";
    }
    response.setContentType(mimetype + "; charset=UTF-8");
    response.setContentLength((int) file.length());
    // response.setHeader("Content-Disposition", "attachment; fileName=\""+file.getName()+"\";");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
    // response.setHeader("Content-Transfer-Encoding", "binary");
    ServletOutputStream out = response.getOutputStream();
    FileInputStream fls = null;
    try {
        fls = new FileInputStream(file);
        FileCopyUtils.copy(fls, out);
        fls.close();
        out.flush();
        out.close();
    } finally {
        if (fls != null) {
            try {
                fls.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}


@ResponseBody
@SuppressWarnings("unchecked")
@RequestMapping(value = "/new", method = RequestMethod.POST)
public JsonResponse save(MemberSessionVo loginUser,String weTag,WeWiki wikiForm,HttpServletRequest request){
    logger.debug("### ?????? ??????  ");
    int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
    logger.debug("####### isUpload : " + isUpload);
    String[] weFileIdx = request.getParameterValues("weFileIdx");
    if (weFileIdx != null) {
        int size = weFileIdx.length;
        for (int i = 0; i < size; i++) {
            logger.debug("####### weFileIdx[" + i + "] : " + weFileIdx[i]);
        }
    }
    logger.debug("####### wikiForm :::{}", wikiForm.toString());
    logger.debug("####### weTag :::{}", weTag);
    JsonResponse res = new JsonResponse();
    JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();
    // TODO TODOLIST ?????? ?????? ?????? ?????? ??????
    // ????????????, ????????????, ???????????? Validation
    // ???????????? ??????
    // ???????????? ?????? - ??????????????? ???????????? ?????? ?????? ????????? ????????? ?????? ???????????????.
    // ?????? ???????????? ?????? ??????
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    // if(weSpace == null) {
    // ????????? ??????
    // throw new PageMoveException("", "", 1);
    // }
    // ?????? html ??? ?????? markUp ??????, markUp??? ?????? HTML ??????
    // ????????????, ????????????, ???????????? check
    // ?????? ?????? ????????? ?????? ???????????? ??????
    wikiForm.newBasicSetting(loginUser.getWeUserIdx(), "S", request.getRemoteAddr());
    // ?????? ???????????? ??????
    // ???????????? ?????? - ?????? ??????
    // ???????????? ?????? - ?????? ??????
    // ???????????? ?????? - ?????? ??????
    // ?????? ??????????????? ????????? ?????? - ?????? ??????
    // ?????? ????????? ?????????????????? ??????
    int insertWikiIdx = 0;
    try {
        if (weFileIdx == null) {
            // ???????????? ??????
            insertWikiIdx = wikiService.addWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag));
        } else {
            // ?????? ?????????
            insertWikiIdx = wikiService.addWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag), weFileIdx, request);
        }
        // ????????? ?????? ?????? ?????? ??????
        logger.debug("#insertResult : {}", insertWikiIdx);
        // ?????? ????????? ?????? - ?????? ??????
        commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.REGIST_WIKI.point);
        /**
         * TODO TODOLIST : ?????? ?????????, ?????? ??????????????? ??? ?????? ?????? ???????????? ?????? 1. ????????? ????????????
         * WE_USER_ALARM ???????????? 2(SPACE_NEW_POST) ?????? ???????????? ?????? ?????? WE_ALARM_INFO???
         * ????????????. ?????????, ????????? ?????????, ?????? ????????????, ?????? ?????????, ?????? ??????, ?????? ??????
         */
        logger.debug("### ?????? ?????? ??????");
        int count = commonService.requestAlarmInfo(loginUser.getWeUserIdx(), loginUser.getWeUserNick(), SystemConst.SPACE_NEW_POST, weSpace.getWe_ins_user(), insertWikiIdx, weSpace.getWe_space_idx());
        logger.debug("### ?????? ?????? ??? : {} ", count);
        result.setMessage("????????? ?????????????????????.");
        result.setRedirectUrl("/space/main/" + wikiForm.getWe_space_idx());
        res.setStatus(ResponseStatus.SUCCESS);
    } catch (DBHandleException e) {
        // TODOLIST Exception ????????? ???????????? ??????
        logger.debug("### DBHandleException : " + e.getCause());
        logger.debug("### DBHandleException : " + e.getMessage());
        logger.debug("### DBHandleException : " + e.getStatus());
        result.setErrorMsg(e.getMessage());
        result.setMessage("????????? ????????? ?????? ???????????????.");
        res.setStatus(ResponseStatus.FAIL);
    }
    res.setResponse(result);
    return res;
}


@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
public ModelAndView imageUpload(MemberSessionVo loginUser,TempUploadVo fileVo,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String svcPath = (String) request.getAttribute("realRootPath");
    String width = StringUtil.strNullToSpace(request.getParameter("width"), "0");
    String height = StringUtil.strNullToSpace(request.getParameter("height"), "0");
    // ????????? ?????? ??? ????????? ??????
    String today = DateUtil.getToday();
    String weUserIdx = Integer.toString(loginUser.getWeUserIdx());
    double maxSize = Double.parseDouble(uploadMaxSize);
    TempUploadVo tempFile = null;
    Map<String, String> param = new HashMap<String, String>();
    // ?????? ??????, ????????? ?????????, ????????????, ?????? ????????? ?????????
    try {
        // ????????? ??????, ????????? ????????? ?????? ????????? ???????????? ??????
        // if(!height.equals("0") && !height.equals("0")) {
        // tempFile = FileUploader.uploadThumbFile(fileVo.getFile(),
        // svcPath, weUserIdx, today, maxSize, width, height);
        // } else {
        // // ????????? ?????? ?????????
        // tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath,
        // weUserIdx, today, maxSize);
        // }
        tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize);
    } catch (FilePermitMsgException e) {
        logger.debug("###????????? ????????? : " + e.getCustomMsg());
        logger.debug("###????????? ????????? : " + e.toString());
        param.put("result", "-1");
        param.put("msg", e.getCustomMsg());
        return new ModelAndView("json_").addObject("param", param);
    } catch (Exception ee) {
        logger.debug("**************************????????? ?????? Exception ??????!!!!");
        ee.printStackTrace();
    }
    logger.debug("tempFile : " + tempFile.toString());
    if (tempFile.isUploaded()) {
        // ???????????? ????????????
        WeFile weFile = new WeFile();
        weFile.setWe_file_real_name(tempFile.getFileRealName());
        weFile.setWe_file_save_name(tempFile.getFileSaveName());
        weFile.setWe_file_save_path(tempFile.getFilePath());
        weFile.setWe_file_size(tempFile.getFileSize() + "");
        weFile.setWe_file_type(tempFile.getFileType());
        weFile.setWe_thumb_yn(tempFile.getThumbYn());
        weFile.setWe_thumb_name(tempFile.getThumbName());
        weFile.setWe_thumb_path(tempFile.getThumbPath());
        weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
        weFile.setWe_ins_date(DateUtil.getTodayTime());
        weFile.setWe_ins_user(weUserIdx);
        int result = commonService.insertWeFile(weFile);
        logger.debug("### result : " + result);
        if (result == 1) {
            WeFile retFile = (WeFile) entityService.getRowEntity(weFile);
            param.put("result", "1");
            param.put("msg", "??????");
            param.put("realFileName", retFile.getWe_file_real_name());
            param.put("saveFileName", retFile.getWe_file_save_name());
            param.put("thumbPath", retFile.getWe_thumb_path());
            param.put("thumbName", retFile.getWe_thumb_name());
            param.put("filePath", retFile.getWe_file_save_path());
            param.put("fileSize", retFile.getWe_file_size() + "");
            param.put("tmpsrc", svcPath);
            param.put("fileIndex", retFile.getWe_file_idx() + "");
        } else {
            param.put("result", "0");
            param.put("msg", "?????? ????????? ?????? ???????????????. ?????? ???????????????.");
            param.put("realFileName", tempFile.getFileRealName());
        }
    } else {
        param.put("result", "-1");
        param.put("msg", "?????? ???????????? ?????? ???????????????.");
        param.put("realFileName", tempFile.getFileRealName());
    }
    logger.debug("param : " + param.toString());
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/list/{spaceIdx}", method = RequestMethod.GET)
public String list(Integer spaceIdx,Model model){
    // ????????? ?????? ?????? ?????? ?????????.
    List<WeWiki> wikiList = wikiService.getWikiList(spaceIdx);
    model.addAttribute("wikiList", wikiList);
    model.addAttribute("spaceIdx", spaceIdx);
    return "/wiki/list";
}


@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
public ModelAndView fileUpload(MemberSessionVo loginUser,TempUploadVo fileVo,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String svcPath = (String) request.getAttribute("tempRootPath");
    // ????????? ?????? ??? ????????? ??????
    String today = DateUtil.getToday();
    String weUserIdx = Integer.toString(loginUser.getWeUserIdx());
    double maxSize = Double.parseDouble(uploadMaxSize);
    TempUploadVo tempFile = null;
    Map<String, String> param = new HashMap<String, String>();
    // ?????? ??????, ????????? ?????????, ????????????, ?????? ????????? ?????????
    try {
        tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize);
    } catch (FilePermitMsgException e) {
        logger.debug("###????????? ????????? : " + e.getCustomMsg());
        logger.debug("###????????? ????????? : " + e.toString());
        param.put("result", "-1");
        param.put("msg", e.getCustomMsg());
        return new ModelAndView("json_").addObject("param", param);
    } catch (MaxUploadSizeExceededException e1) {
        logger.debug("###????????? ????????? : " + e1.getMessage());
        logger.debug("###????????? ????????? : " + e1.getLocalizedMessage());
        param.put("result", "-1");
        param.put("msg", e1.getMessage());
        return new ModelAndView("json_").addObject("param", param);
    } catch (Exception ee) {
        logger.debug("**************************????????? ?????? Exception ??????!!!!");
        ee.printStackTrace();
    }
    logger.debug("tempFile : " + tempFile.toString());
    if (tempFile.isUploaded()) {
        // ???????????? ????????????
        WeFile weFile = new WeFile();
        weFile.setWe_file_real_name(tempFile.getFileRealName());
        weFile.setWe_file_save_name(tempFile.getFileSaveName());
        weFile.setWe_file_save_path(tempFile.getFilePath());
        weFile.setWe_file_size(tempFile.getFileSize() + "");
        weFile.setWe_file_type(tempFile.getFileType());
        weFile.setWe_thumb_yn(tempFile.getThumbYn());
        weFile.setWe_thumb_name(tempFile.getThumbName());
        weFile.setWe_thumb_path(tempFile.getThumbPath());
        weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
        weFile.setWe_ins_date(DateUtil.getTodayTime());
        weFile.setWe_ins_user(weUserIdx);
        int result = commonService.insertWeFile(weFile);
        logger.debug("### result : " + result);
        if (result == 1) {
            WeFile retFile = (WeFile) entityService.getRowEntity(weFile);
            param.put("result", "1");
            param.put("msg", "??????");
            param.put("realFileName", retFile.getWe_file_real_name());
            param.put("saveFileName", retFile.getWe_file_save_name());
            param.put("thumbPath", retFile.getWe_thumb_path());
            param.put("thumbName", retFile.getWe_thumb_name());
            param.put("filePath", retFile.getWe_file_save_path());
            param.put("fileSize", retFile.getWe_file_size() + "");
            param.put("tmpsrc", svcPath);
            param.put("fileIndex", retFile.getWe_file_idx() + "");
        } else {
            param.put("result", "0");
            param.put("msg", "?????? ????????? ?????? ???????????????. ?????? ???????????????.");
            param.put("realFileName", tempFile.getFileRealName());
        }
    } else {
        param.put("result", "-1");
        param.put("msg", "?????? ???????????? ?????? ???????????????.");
        param.put("realFileName", tempFile.getFileRealName());
    }
    logger.debug("param : " + param.toString());
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/{wikiIdx}/updateComment", method = RequestMethod.POST)
public ModelAndView updateComment(Integer wikiIdx,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("##updateComment");
    String weWikiCommentIdx = request.getParameter("weWikiCommentIdx");
    String weBbsText = request.getParameter("weBbsText");
    WeWikiComment domain = new WeWikiComment();
    domain = commonService.getWeWikiComment(weWikiCommentIdx);
    domain.setWe_ins_date(new Date());
    domain.setWe_wiki_idx(wikiIdx);
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


@RequestMapping(value = "/mywiki", method = RequestMethod.GET)
public String myWikiList(MemberSessionVo loginUser,Model model){
    // ????????? ?????? ?????? ?????? ????????? ??????
    List<WikiLogVo> wikiLogVoList = userActionService.getMyWikiLogAction(loginUser);
    List<Integer> wikiSpaceIdxList = new ArrayList<Integer>();
    // ???????????? ?????? ?????? ????????? ?????? ???????????? ?????? ?????? ??????.
    if (wikiLogVoList != null) {
        if (wikiLogVoList.size() > 0) {
            for (int index = 0; index < wikiLogVoList.size(); index++) {
                if (!wikiSpaceIdxList.contains(wikiLogVoList.get(index).getWeSpaceIdx())) {
                    wikiSpaceIdxList.add(wikiLogVoList.get(index).getWeSpaceIdx());
                }
            }
        }
    }
    List<WikiLogVo> spaceInfoList = null;
    logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.size());
    logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.toString());
    // TODOLIST ?????? ????????? ?????? ???????????? ??????????????? ????????????.
    if (wikiSpaceIdxList != null) {
        if (wikiSpaceIdxList.size() > 0) {
            spaceInfoList = userActionService.getSpaceInfoByIdx(wikiSpaceIdxList);
            wikiSpaceIdxList = null;
        }
    }
    model.addAttribute("wikiLogVoList", wikiLogVoList);
    model.addAttribute("spaceInfoList", spaceInfoList);
    return "/wiki/mywiki";
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/new/{spaceIdx}/{parentIdx}", method = RequestMethod.GET)
public String replyForm(MemberSessionVo loginUser,Integer spaceIdx,Integer parentIdx,Model model){
    // ????????? ?????? ??????
    WeTemplate temp = new WeTemplate();
    temp.setWe_use_yn("Y");
    List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(spaceIdx);
    // ?????? ?????? ??????
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    JSONArray jsonTemplate = null;
    if (template != null) {
        // ???????????????
        jsonTemplate = JSONArray.fromObject(template);
        // jsonTemplate =
        // JSONObject.fromObject(JSONSerializer.toJSON(template)); // ????????????
        // ??????
        logger.debug("### jsonTemplate : " + jsonTemplate.toString());
    } else {
        jsonTemplate = null;
    }
    // wikiForm ??? ?????? ??????.
    WeWiki wikiForm = new WeWiki();
    wikiForm = commonService.getWikiInfo(parentIdx);
    wikiForm.setWe_wiki_text(null);
    wikiForm.setWe_wiki_title(null);
    wikiForm.setWe_wiki_markup(null);
    logger.debug("#### wikiForm " + wikiForm.toString());
    model.addAttribute("weWiki", wikiForm);
    model.addAttribute("editMode", "reply");
    model.addAttribute("weSpace", weSpace);
    model.addAttribute("template", jsonTemplate);
    model.addAttribute("wikiForm", wikiForm);
    return "/wiki/form";
}


@RequestMapping(value = "/{wikiIdx}/insertComment", method = RequestMethod.POST)
public String insertComment(HttpServletRequest request,Integer wikiIdx,MemberSessionVo loginUser,WeWikiComment weWikiComment){
    String noSpam = StringUtil.strNull(request.getParameter("noSpam"));
    String randomKey = StringUtil.strNull(request.getParameter("randomKey"));
    if (!noSpam.equals(randomKey)) {
        throw new GliderwikiException("???????????? ???????????? ????????????");
    }
    WeWikiComment domain = new WeWikiComment();
    domain.setWe_wiki_idx(wikiIdx);
    domain.setWe_ins_date(new Date());
    domain.setWe_ins_user(loginUser.getWeUserIdx());
    domain.setWe_user_ip(requestManager.getRemoteAddress(request));
    domain.setWe_bbs_text(weWikiComment.getWe_bbs_text());
    domain.setWe_ins_name(weWikiComment.getWe_ins_name());
    domain.setWe_use_yn("Y");
    logger.debug("### domain : " + domain.toString());
    int result = wikiService.insertWikiComment(domain);
    if (result == 1) {
        return "redirect:/wiki/" + wikiIdx;
    } else {
        throw new GliderwikiException("?????? ????????? ????????????!");
    }
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/new/{spaceIdx}", method = RequestMethod.GET)
public String newForm(MemberSessionVo loginUser,Integer spaceIdx,WeWiki wikiForm,Model model){
    logger.debug("#### spaceIdx : {}", spaceIdx);
    // ????????? ?????? ??????
    WeTemplate temp = new WeTemplate();
    temp.setWe_use_yn("Y");
    List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(spaceIdx);
    // ?????? ?????? ??????
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    wikiForm.setWe_space_idx(spaceIdx);
    JSONArray jsonTemplate = null;
    if (template != null) {
        // ???????????????
        jsonTemplate = JSONArray.fromObject(template);
        // jsonTemplate =
        // JSONObject.fromObject(JSONSerializer.toJSON(template)); // ????????????
        // ??????
        logger.debug("### jsonTemplate : " + jsonTemplate.toString());
    } else {
        jsonTemplate = null;
    }
    // ?????? ?????? ??????
    model.addAttribute("menuAttr", "default");
    model.addAttribute("editMode", "new");
    model.addAttribute("weSpace", weSpace);
    model.addAttribute("template", jsonTemplate);
    return "/wiki/form";
}


@SuppressWarnings("unchecked")
@RequestMapping(value = { "/{weWikiIdx}", "/pdf/{weWikiIdx}" }, method = RequestMethod.GET)
public String view(HttpServletRequest request,MemberSessionVo loginUser,Integer weWikiIdx,ModelMap model,String pdfView){
    logger.debug("###loginUser : " + loginUser.toString());
    WeWiki weWiki = commonService.getWikiInfo(weWikiIdx);
    // ????????????
    List<WeWikiTag> tagList = null;
    // ????????????
    List<WeWikiNote> noteList = null;
    // ????????????
    List<WeWikiLink> linkList = null;
    // ????????????
    List<WeWikiFile> fileList = null;
    // ???????????????
    List<WeWikiSummary> summaryList = null;
    // ?????????
    WeWikiGraph wikiGraph = null;
    try {
        tagList = commonService.getWeWikiTagList(weWikiIdx, weWiki.getWe_wiki_revision());
        noteList = commonService.getWeWikiNoteList(weWikiIdx, weWiki.getWe_wiki_revision());
        linkList = commonService.getWeWikiLinkList(weWikiIdx, weWiki.getWe_wiki_revision());
        fileList = commonService.getWeWikiFileList(weWikiIdx, weWiki.getWe_wiki_revision());
        summaryList = commonService.getWeWikiSummaryList(weWikiIdx, weWiki.getWe_wiki_revision());
        wikiGraph = commonService.getWeWikiGraph(weWikiIdx, weWiki.getWe_wiki_revision());
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (loginUser.getWeUserIdx() != null) {
        logger.debug("#### loginUser.getWeUserIdx() : " + loginUser.getWeUserIdx());
    }
    // ?????????
    String protectStatus = WikiProdectStatus.prodectStatus(weWiki.getWe_wiki_protect());
    // ?????? ???????????????
    WeUser weuser = new WeUser();
    weuser.setWe_user_idx(weWiki.getWe_ins_user());
    weuser = (WeUser) entityService.getRowEntity(weuser);
    // ?????? ??????
    WeProfile weprofile = new WeProfile();
    weprofile.setWe_user_idx(weuser.getWe_user_idx());
    weprofile.setWe_away_yn(null);
    weprofile.setWe_tech_yn(null);
    weprofile.setWe_point(null);
    weprofile = (WeProfile) entityService.getRowEntity(weprofile);
    logger.debug("####weprofile : " + weprofile.toString());
    // ????????? ??????
    weWiki.setWe_wiki_view_cnt(weWiki.getWe_wiki_view_cnt() + 1);
    entityService.updateEntity(weWiki);
    String htmlContent = "";
    String htmlTagRemove = StringUtil.removeTags(weWiki.getWe_wiki_markup().replaceAll("\r\n", ""));
    if (htmlTagRemove.length() > 80) {
        htmlContent = htmlTagRemove.substring(0, 80) + "...";
    } else {
        htmlContent = htmlTagRemove;
    }
    // ?????? ????????????
    String randomKey = StringUtil.getRandomKey();
    WeWikiComment comment = new WeWikiComment();
    comment.setWe_wiki_idx(weWikiIdx);
    comment.setWe_use_yn("Y");
    List commentList = null;
    try {
        commentList = (List) entityService.getListEntity(comment);
    } catch (Throwable e) {
        e.printStackTrace();
    }
    String domain = CommonUtil.getClientDomain(request);
    // TODO ?????? ????????? insert ?????? ???
    model.addAttribute("weWiki", weWiki);
    model.addAttribute("tagList", tagList);
    model.addAttribute("noteList", noteList);
    model.addAttribute("linkList", linkList);
    model.addAttribute("fileList", fileList);
    model.addAttribute("summaryList", summaryList);
    model.addAttribute("wikiGraph", wikiGraph);
    model.addAttribute("userNick", weuser.getWe_user_nick());
    model.addAttribute("weGrade", weprofile.getWe_grade());
    model.addAttribute("protectStatus", protectStatus);
    model.addAttribute("title", weWiki.getWe_wiki_title());
    model.addAttribute("htmlContent", htmlContent);
    model.addAttribute("WeWikiComment", comment);
    // ????????? ??????
    model.addAttribute("randomKey", randomKey);
    model.addAttribute("commentList", commentList);
    model.addAttribute("domain", domain);
    if (StringUtils.equals("ok", pdfView)) {
        return "wiki/pdf/show";
    }
    return "/wiki/show";
}


@RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
@ResponseBody
public int addFavorite(MemberSessionVo loginUser,int spaceIdx,int wikiIdx){
    int result = wikiService.addFavorite(loginUser.getWeUserIdx(), spaceIdx, wikiIdx);
    return result;
}


@RequestMapping(value = "/export/pdf/{wikiIdx}", method = RequestMethod.GET)
public void getPdfFileExport(HttpServletRequest request,HttpServletResponse response,String wikiIdx){
    GliderFileUtils fileUtils = new GliderFileUtils();
    fileUtils.htmlToPdfExport(request, response, wikiIdx);
}


@RequestMapping(value = "/removeFile", method = RequestMethod.GET)
public ModelAndView removeFile(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String chkType = StringUtil.strNullToSpace(request.getParameter("chkType"), "");
    Integer weFileIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weFileIdx"), "0"));
    String targetId = StringUtil.strNullToSpace(request.getParameter("targetId"), "");
    String formId = StringUtil.strNullToSpace(request.getParameter("formId"), "");
    logger.debug("### chkType : " + chkType);
    logger.debug("### weFileIdx : " + weFileIdx);
    logger.debug("### targetId : " + targetId);
    logger.debug("### formId : " + formId);
    Map<String, Object> param = new HashMap<String, Object>();
    try {
        if (chkType.equals("1")) {
            // ?????? ?????? ????????? ??????
            wikiService.delWeWikiFile(weFileIdx);
        } else {
            commonService.delWeFile(weFileIdx);
        }
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("chkType", chkType);
        param.put("weFileIdx", weFileIdx);
        param.put("targetId", targetId);
        param.put("formId", formId);
    } catch (Exception e) {
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("chkType", chkType);
        param.put("weFileIdx", weFileIdx);
        param.put("targetId", targetId);
        param.put("formId", formId);
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/htmlDownload", method = RequestMethod.POST)
public ModelAndView htmlDownload(MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String we_wiki_idx = request.getParameter("we_wiki_idx");
    String we_wiki_title = request.getParameter("we_wiki_title");
    if (we_wiki_idx == null) {
        throw new FilePermitMsgException("Wiki is not available!!");
    }
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    StringBuffer htmlSource = new StringBuffer();
    String domain = CommonUtil.getClientDomain(request);
    htmlSource = commonService.getHtmlSourceByWikiIdx(Integer.parseInt(we_wiki_idx));
    long millSec = System.currentTimeMillis();
    String html = "";
    String href = "/resource/";
    String destHref = domain + href;
    if (htmlSource.indexOf(href) != -1) {
        html = htmlSource.toString().replaceAll(href, destHref);
    } else {
        html = htmlSource.toString();
    }
    String realPath = request.getSession().getServletContext().getRealPath("/");
    logger.debug("#realPath : " + realPath);
    logger.debug("*************** ?????? ???????????? ????????? *******************");
    // ???????????? ?????? ??????
    String filePath = realPath + SystemConst.WIKI_HTML_DOWNLOAD_PATH;
    logger.debug("## filePath : " + filePath);
    String fileName = millSec + ".html";
    File file = new File(filePath + "/" + fileName);
    logger.debug("## filePath : " + filePath + "/" + fileName);
    logger.debug("## file.getName() : " + file.getName());
    BufferedWriter bufferedWriter = null;
    Map<String, String> param = new HashMap<String, String>();
    Writer out = null;
    try {
        /*
			FileWriter fileWriter = new FileWriter(file);
	        bufferedWriter = new BufferedWriter(fileWriter);
	        bufferedWriter.write(html);
	        bufferedWriter.close();
			logger.debug("### bufferedWriter ??????");
			//BufeferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("jedis.txt),"UTF-8")))
			*/
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        out.write(html);
        out.close();
        param.put("result", "SUCCESS");
        param.put("filename", fileName);
    } catch (IOException e) {
        param.put("result", "FAIL");
        throw new GliderwikiException("HTML ?????? ????????? ?????? ?????? ??????!", e);
    } finally {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/edit/{wikiIdx}", method = RequestMethod.GET)
public String editForm(MemberSessionVo loginUser,Integer wikiIdx,WeWiki wikiForm,Model model){
    // [TODO] ????????? ?????? ??????
    // [TODO] ?????? ?????? ?????? ??????
    // [TODO] ?????? ????????? ?????? ??????
    // [TODO] ???????????? ??????
    logger.debug("####wikiIdx : {}", wikiIdx);
    wikiForm.setWe_wiki_idx(wikiIdx);
    // ????????? ?????? ??????
    WeTemplate temp = new WeTemplate();
    temp.setWe_use_yn("Y");
    List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());
    // ?????? ?????? ??????
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    // ????????? ?????? ??????
    JSONArray jsonTemplate = null;
    if (template != null) {
        // ???????????????
        jsonTemplate = JSONArray.fromObject(template);
        // jsonTemplate =
        // JSONObject.fromObject(JSONSerializer.toJSON(template)); // ????????????
        // ??????
        logger.debug("### jsonTemplate : " + jsonTemplate.toString());
    } else {
        jsonTemplate = null;
    }
    try {
        // TODO ?????? ????????? ???????????? Push ?????????
        wikiForm = wikiService.getWikiForEdit(wikiForm, loginUser);
    } catch (Throwable e) {
        e.printStackTrace();
    }
    // ????????? ?????? ?????? ?????? ?????? ??????
    List<WeWikiFile> fileList = commonService.getWeWikiFileList(wikiForm.getWe_wiki_idx(), wikiForm.getWe_wiki_revision());
    // ????????? ?????? ?????? ?????? ?????? ??????
    List<WeWikiTag> tagList = commonService.getWeWikiTagList(wikiForm.getWe_wiki_idx(), wikiForm.getWe_wiki_revision());
    String weTag = "";
    if (tagList.size() > 0) {
        for (int index = 0; index < tagList.size(); index++) {
            if (index < tagList.size() - 1) {
                weTag = weTag + tagList.get(index).getWe_tag() + ", ";
            } else {
                weTag = weTag + tagList.get(index).getWe_tag();
            }
        }
    }
    // ????????? ?????? ??? ???....
    if (model.containsAttribute("errorMessage")) {
        return "redirect:/wiki/" + wikiIdx;
    }
    model.addAttribute("wikiForm", wikiForm);
    model.addAttribute("editMode", "edit");
    model.addAttribute("weTag", weTag);
    model.addAttribute("fileList", fileList);
    model.addAttribute("fileSize", fileList.size());
    model.addAttribute("weSpace", weSpace);
    model.addAttribute("template", jsonTemplate);
    return "/wiki/form";
}


@RequestMapping(value = "/{wikiIdx}/getComment", method = RequestMethod.GET)
public ModelAndView getComment(Integer wikiIdx,MemberSessionVo loginUser,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String weWikiCommentIdx = StringUtil.strNull(request.getParameter("weWikiCommentIdx"));
    String weUserIdx = StringUtil.strNull(request.getParameter("weUserIdx"));
    if (loginUser.isGuest()) {
        throw new GliderwikiException("????????? ????????? ???????????? ????????????");
    }
    // ??????????????? ???
    // String randomKey = StringUtil.getRandomKey();
    Map<String, String> param = new HashMap<String, String>();
    WeWikiComment domain = new WeWikiComment();
    domain.setWe_wiki_comment_idx(Integer.parseInt(weWikiCommentIdx));
    domain.setWe_ins_user(Integer.parseInt(weUserIdx));
    domain.setWe_wiki_idx(wikiIdx);
    domain.setWe_use_yn("Y");
    WeWikiComment bbsComment = null;
    try {
        bbsComment = (WeWikiComment) entityService.getRowEntity(domain);
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
        throw new GliderwikiException("????????? ???????????? ????????????");
    }
    return new ModelAndView("json_").addObject("param", param);
}


@ResponseBody
@SuppressWarnings("unchecked")
@RequestMapping(value = "/sub/{parentIdx}", method = RequestMethod.POST)
public JsonResponse saveSubWiki(MemberSessionVo loginUser,String weTag,WeWiki wikiForm,HttpServletRequest request){
    logger.debug("### ???????????? ?????? ?????? ??????  :  {}", wikiForm.getWe_wiki_parent_idx());
    logger.debug("### weWiki : {}", wikiForm.toString());
    logger.debug("### ?????? ??????  ");
    int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
    logger.debug("####### isUpload : " + isUpload);
    String[] weFileIdx = request.getParameterValues("weFileIdx");
    if (weFileIdx != null) {
        int size = weFileIdx.length;
        for (int i = 0; i < size; i++) {
            logger.debug("####### weFileIdx[" + i + "] : " + weFileIdx[i]);
        }
    }
    logger.debug("####### wikiForm :::{}", wikiForm.toString());
    logger.debug("####### weTag :::{}", weTag);
    JsonResponse res = new JsonResponse();
    JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();
    WeSpace searchSpace = new WeSpace();
    searchSpace.setWe_use_yn("Y");
    searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());
    WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);
    // if(weSpace == null) {
    // ????????? ??????
    // throw new PageMoveException("", "", 1);
    // }
    // ?????? html ??? ?????? markUp ??????, markUp??? ?????? HTML ??????
    // wikiForm.newBasicSetting(loginUser.getWeUserIdx(), "S",
    // request.getRemoteAddr());
    // ?????? ????????? ????????? ????????? ????????? ????????? ??????.
    wikiForm.setWe_use_yn("Y");
    wikiForm.setWe_ins_user(loginUser.getWeUserIdx());
    wikiForm.setWe_wiki_revision(1);
    // ????????????
    wikiForm.setWe_wiki_status("S");
    wikiForm.setWe_user_ip(request.getRemoteAddr());
    wikiForm.setWe_wiki_agree(0);
    wikiForm.setWe_wiki_view_cnt(0);
    wikiForm.setWe_wiki_protect("0");
    // ?????? ?????? ??????
    wikiForm.setWe_edit_yn("Y");
    wikiForm.setWe_ins_date(new Date());
    int insertWikiIdx = 0;
    try {
        if (weFileIdx == null) {
            // ???????????? ?????? (????????? ?????????????????? ?????? ????????? ?????? ?????? ?????? ??????????????? ??????)
            insertWikiIdx = wikiService.addSubWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag));
        } else {
            // ?????? ?????????
            insertWikiIdx = wikiService.addSubWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag), weFileIdx, request);
        }
        // ?????? ??? ?????????
        logger.debug("#insertWikiIdx : " + insertWikiIdx);
        // ?????? ????????? ?????? - ?????? ??????
        commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.REGIST_WIKI.point);
        /**
         * TODO TODOLIST : ?????? ?????????, ?????? ??????????????? ??? ?????? ?????? ???????????? ?????? 1. ????????? ????????????
         * WE_USER_ALARM ???????????? 2(SPACE_NEW_POST) ?????? ???????????? ?????? ?????? WE_ALARM_INFO???
         * ????????????. ?????????, ????????? ?????????, ?????? ????????????, ?????? ?????????, ?????? ??????, ?????? ??????
         */
        // logger.debug("### ?????? ?????? ??????");
        // int count =
        // commonService.requestAlarmInfo(loginUser.getWeUserIdx(),
        // loginUser.getWeUserNick(),
        // SystemConst.SPACE_NEW_POST, weSpace.getWe_admin_idx(), null,
        // weSpace.getWe_space_idx());
        // logger.debug("### ?????? ?????? ??? : {} ", count);
        result.setMessage("?????? ????????? ?????? ???????????????. ????????? ?????? ???????????????.");
        result.setRedirectUrl("/wiki/" + (insertWikiIdx));
        res.setStatus(ResponseStatus.SUCCESS);
    } catch (DBHandleException e) {
        // TODOLIST Exception ????????? ???????????? ??????
        logger.debug("### DBHandleException : " + e.getCause());
        logger.debug("### DBHandleException : " + e.getMessage());
        logger.debug("### DBHandleException : " + e.getStatus());
        result.setErrorMsg(e.getMessage());
        result.setMessage("????????? ????????? ?????? ???????????????.");
        res.setStatus(ResponseStatus.FAIL);
    }
    res.setResponse(result);
    return res;
}


}