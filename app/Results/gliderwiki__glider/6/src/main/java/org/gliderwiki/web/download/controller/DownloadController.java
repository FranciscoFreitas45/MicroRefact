package org.gliderwiki.web.download.controller;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.AttachmentType;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.download.service.DownloadService;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.DTO.WeInstallUser;
@Controller
public class DownloadController {

 private Logger logger;

@SuppressWarnings("rawtypes")
@Autowired
 private  EntityService entityService;

@Autowired
 private  DownloadService downloadService;


@RequestMapping(method = RequestMethod.POST, value = "/downloadWiki")
public void wikiDownLoad(WeInstallUser weInstallUser,BindingResult result,HttpServletRequest request,HttpServletResponse response){
    logger.debug("### weInstallUser :" + weInstallUser.toString());
    int rtnResult = 0;
    // TODOList 테이블에 저장
    if (weInstallUser.getWe_email() == null || "".equals(weInstallUser.getWe_email()) || weInstallUser.getWe_active_key() == null || "".equals(weInstallUser.getWe_active_key())) {
        throw new GliderwikiException("올바른 접근이 아닙니다.");
    }
    // TODOList DB에서 현재 파일 명 조회
    String fileName = "downloadPack-0226.zip";
    try {
        // 인증키 전송
        rtnResult = downloadService.sendActiveKey(weInstallUser.getWe_email(), weInstallUser.getWe_active_key(), request);
        if (rtnResult > 0) {
            logger.debug("## Install User 데이터 저장 ");
            weInstallUser.setWe_install_date(new Date());
            entityService.insertEntity(weInstallUser);
            GliderFileUtils.downloadFile(request, response, new File(request.getSession().getServletContext().getRealPath("/resource/data/gliderwiki/" + fileName)), AttachmentType.DOWNLOAD);
        }
    } catch (Exception e) {
        logger.debug("###Wiki Download Result : " + rtnResult);
        e.printStackTrace();
    }
}


@RequestMapping(value = "/download/{version}", method = RequestMethod.GET)
public ModelAndView downloadView(String version,ModelAndView modelAndView){
    // 고객
    logger.debug("### 다운로드 폼 ");
    // Active Key를 생성 한다.
    // 128
    String activeKey = StringUtil.stringBuffersChars(128);
    logger.debug("activeKey {} ", activeKey);
    modelAndView.addObject("version", version);
    modelAndView.addObject("activeKey", activeKey);
    modelAndView.setViewName("download/download");
    return modelAndView;
}


}