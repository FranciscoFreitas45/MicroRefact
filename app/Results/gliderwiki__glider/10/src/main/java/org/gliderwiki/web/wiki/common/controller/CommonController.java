package org.gliderwiki.web.wiki.common.controller;
 import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.Attachment;
import org.gliderwiki.web.domain.AttachmentCategory;
import org.gliderwiki.web.domain.AttachmentType;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.user.service.UserConnectionService;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.UploadFileVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.DTO.Attachment;
import org.gliderwiki.DTO.WeProfile;
@Controller
public class CommonController {

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  UserConnectionService userConnectionService;

@Autowired
 private  CommonService commonService;

@Value("#{config['file.maxSize']}")
 private String maxSize;


@RequestMapping(value = "/calendar", method = { RequestMethod.GET, RequestMethod.POST })
public ModelAndView calendar(ModelAndView modelAndView){
    modelAndView.setViewName("calendar/calendar");
    return modelAndView;
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public String uploadFile(HttpServletRequest request){
    logger.debug("uploadController 진입!");
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    int result = 0;
    List<MultipartFile> files = multipartRequest.getFiles("uploadFile");
    int uploadTotalSize = files.size();
    logger.debug("파일 사이즈 : {}", uploadTotalSize);
    for (int i = 0; i < uploadTotalSize; i++) {
        MultipartFile file = files.get(i);
        Attachment attachment = new Attachment();
        String attFileName = file.getOriginalFilename();
        attachment.setCategory(AttachmentCategory.PUBLIC);
        attachment.setUser_id("bluepoet");
        attachment.setBoard_no(2);
        attachment.setFile_name(attFileName.substring(0, attFileName.lastIndexOf(".")));
        attachment.setExtension(attFileName.substring(attFileName.lastIndexOf(".") + 1, attFileName.length()));
        attachment.setCreated_date(new Date());
        File destFile = GliderFileUtils.getDestFile(attachment.getFilePath());
        try {
            GliderFileUtils.forceMKParentDir(destFile);
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // DB 데이터 저장
        result = entityService.insertEntity(attachment);
    }
    logger.debug("업로드 후 결과 : {} ", result);
    return "upload/uploadForm";
}


@RequestMapping(value = "/sysinfo", method = RequestMethod.GET)
public ModelAndView sysinfo(ModelAndView modelAndView){
    logger.debug("##### sysinfo");
    // 자바 버전
    String jvm = System.getProperty("java.version");
    // 자바 공급자
    String vendor = System.getProperty("java.vendor");
    // 공급자 주소
    String url = System.getProperty("java.vendor.url");
    // 자바 설치 디렉토리
    String home = System.getProperty("java.home");
    // 자바 클래스 버전
    String classVersion = System.getProperty("java.class.version");
    // os 이름
    String osName = System.getProperty("os.name");
    // os 아키텍쳐
    String osArch = System.getProperty("os.arch");
    // os 버전 정보
    String osVersion = System.getProperty("os.version");
    // 파일구분자
    String fileSeparator = System.getProperty("file.separator");
    // 경로 구분자
    String pathSeparator = System.getProperty("path.separator");
    // 행 구분자
    String lineSeparator = System.getProperty("line.separator");
    // 현재 작업 디렉토리
    String userDir = System.getProperty("user.dir");
    // 사용자홈 디렉토리
    String userHome = System.getProperty("user.home");
    // 사용자계정
    String userName = System.getProperty("user.name");
    // 파일인코딩
    String fileEncoding = System.getProperty("file.encoding");
    // 임시경로
    String tmpDir = System.getProperty("java.io.tmpdir");
    // 로케일
    Locale loc = Locale.getDefault();
    logger.debug("#loc:" + loc);
    String enc = new java.io.OutputStreamWriter(System.out).getEncoding();
    logger.debug("=======================================");
    logger.debug("#default encoding = " + enc);
    logger.debug("#fileEncoding : " + fileEncoding);
    logger.debug("#tmpDir : " + tmpDir);
    logger.debug("#jvm : " + jvm);
    logger.debug("#vendor : " + vendor);
    logger.debug("#url : " + url);
    logger.debug("#home : " + home);
    logger.debug("#classVersion : " + classVersion);
    logger.debug("#osName : " + osName);
    logger.debug("#osArch : " + osArch);
    logger.debug("#osVersion : " + osVersion);
    logger.debug("#fileSeparator : " + fileSeparator);
    logger.debug("#pathSeparator : " + pathSeparator);
    logger.debug("#lineSeparator : " + lineSeparator);
    logger.debug("#userDir : " + userDir);
    logger.debug("#userHome : " + userHome);
    logger.debug("#userName : " + userName);
    modelAndView.addObject("enc", enc);
    modelAndView.addObject("fileEncoding", fileEncoding);
    modelAndView.addObject("tmpDir", tmpDir);
    modelAndView.addObject("jvm", jvm);
    modelAndView.addObject("vendor", vendor);
    modelAndView.addObject("url", url);
    modelAndView.addObject("home", home);
    modelAndView.addObject("classVersion", classVersion);
    modelAndView.addObject("osName", osName);
    modelAndView.addObject("osArch", osArch);
    modelAndView.addObject("osVersion", osVersion);
    modelAndView.addObject("fileSeparator", fileSeparator);
    modelAndView.addObject("pathSeparator", pathSeparator);
    modelAndView.addObject("lineSeparator", lineSeparator);
    modelAndView.addObject("userDir", userDir);
    modelAndView.addObject("userHome", userHome);
    modelAndView.addObject("userName", userName);
    modelAndView.addObject("loc", loc);
    // 페이지 이동
    modelAndView.setViewName("sysinfo");
    return modelAndView;
}


@RequestMapping(value = "/syntax", method = RequestMethod.GET)
public ModelAndView syntax(ModelAndView modelAndView){
    modelAndView.setViewName("temp/syntax");
    return modelAndView;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(ModelAndView modelAndView,ModelMap model){
    // 페이지 이동
    modelAndView.setViewName("list");
    return modelAndView;
}


@RequestMapping(value = "/common/userinfo", method = RequestMethod.GET)
public ModelAndView userinfo(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("userIdx"), "0"));
    logger.debug("#### weUserIdx : " + weUserIdx);
    if (weUserIdx == 0) {
        throw new GliderwikiException("사용자 전달값이 올바르지  않습니다.");
    }
    Map<String, Object> param = new HashMap<String, Object>();
    try {
        WeUser userInfo = commonService.getUserInfo(weUserIdx);
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("userId", userInfo.getWe_user_id());
        param.put("nick", userInfo.getWe_user_nick());
        param.put("weUserIdx", weUserIdx + "");
    } catch (Exception e) {
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("userId", "");
        param.put("nick", "");
        param.put("weUserIdx", weUserIdx + "");
    }
    logger.debug("## param : " + param.toString());
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/user/viewProfile", method = RequestMethod.GET)
public ModelAndView userProfile(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weUserIdx"), "0"));
    logger.debug("### weUserIdx : " + weUserIdx);
    Map<String, Object> param = new HashMap<String, Object>();
    // 해당 사용자의 프로필 정보 조회
    List<AddFriendVo> myConnectionList = null;
    try {
        myConnectionList = userConnectionService.getConnectionToMe(weUserIdx);
        // 타겟 사용자 기본정보
        WeUser weUser = null;
        // 타겟 사용자 프로필 정보
        WeProfile weProfile = null;
        // 회원 기본 정보 조회
        weUser = commonService.getUserInfo(weUserIdx);
        // 회원 프로파일 정보 조회
        weProfile = commonService.getUserProfileInfo(weUserIdx);
        String lastVisitDate = weProfile.getWe_visit_date().toString();
        logger.debug("### lastVisitDate : " + lastVisitDate);
        param.put("result", "SUCCESS");
        param.put("status", SystemConst.CALL_SUCCESS);
        param.put("connectionListSize", myConnectionList.size() + "");
        param.put("weProfile", weProfile);
        param.put("weUser", weUser);
        param.put("lastVisitDate", lastVisitDate);
        param.put("weUserIdx", weUserIdx + "");
    } catch (Exception e) {
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("connectionListSize", "");
        param.put("weProfile", null);
        param.put("weUser", null);
        param.put("weUserIdx", weUserIdx + "");
    }
    return new ModelAndView("json_").addObject("param", param);
}


}