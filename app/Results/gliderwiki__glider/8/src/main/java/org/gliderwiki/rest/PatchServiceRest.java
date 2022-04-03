package org.gliderwiki.rest;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.rest.service.PatchService;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WePatch;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.PatchInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.gliderwiki.Interface.EntityService;
@Controller
public class PatchServiceRest {

 private Logger logger;

@SuppressWarnings("rawtypes")
@Autowired
 private  EntityService entityService;

@Autowired
 private  PatchService patchService;


@RequestMapping(method = RequestMethod.GET, value = "/extentionList/{version}")
@ResponseBody
public WeFunction[] extentionList(String version){
    logger.debug("##### version : " + version);
    String clientVersion = version.replaceAll("-", ".");
    WeFunction[] arrayFunctionList = null;
    // 버전이 다를 경우 WE_FUNCTION 에서 데이터 가져옴  Y = 확장 기능파일 , N = 패치파일
    arrayFunctionList = patchService.getCurrentFunctionList(clientVersion, "Y");
    return arrayFunctionList;
}


@RequestMapping(method = RequestMethod.GET, value = "/patchList/download/{functionIdx}")
@ResponseBody
public File download(String functionIdx,HttpServletRequest request,HttpServletResponse response){
    logger.debug("#### 서버 파일 다운로드 시작 ####");
    String filePath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v101");
    String fileName = "framework-20130302.jar";
    String fullPath = filePath + "/" + fileName;
    logger.debug("##fullPath : " + fullPath);
    File file = new File(fullPath);
    return file;
}


@RequestMapping(method = RequestMethod.GET, value = "/patchList/{version}")
@ResponseBody
public WeFunction[] patchList(String version){
    logger.debug("##### version : " + version);
    String clientVersion = version.replaceAll("-", ".");
    WeFunction[] arrayFunctionList = null;
    // 버전이 다를 경우 WE_FUNCTION 에서 데이터 가져옴  Y = 확장 기능파일 , N = 패치파일
    arrayFunctionList = patchService.getCurrentFunctionList(clientVersion, "N");
    /*
		 * 
		INSERT INTO `wiki2`.`we_function` 
			(
			`WE_FUNCTION_NAME`, 
			`WE_FUNCTION_DESC`, 
			`WE_FUNCTION_CODE`, 
			`WE_FUNCTION_VER`, 
			`WE_FUNCTION_TYPE`, 
			`WE_USE_YN`, 
			`WE_EXTEND_YN`, 
			`WE_CALL_URL`, 
			`WE_INS_DATE`, 
			`WE_INS_USER`
			)
			VALUES
			(
			'프레임웍 버그 패치', 
			'framework20130302.jar 버그 패치 버전입니다.', 
			'v01', 
			'1.0.1', 
			'LIB', 
			'Y', 
			'N', 
			'/service/', 
			NOW(), 
			1
			);

		 * 
		 * 
		 */
    return arrayFunctionList;
}


@RequestMapping(method = RequestMethod.POST, value = "/patchList/getPatchInfo/{functionIdx}")
@ResponseBody
public WePatch patchInfo(String functionIdx,WePatch wePatchModel,HttpServletRequest request,HttpServletResponse response){
    WePatch domain = patchService.getWePatchInfo(wePatchModel);
    return domain;
}


@RequestMapping(method = RequestMethod.POST, value = "/patchService")
@ResponseBody
public PatchInfoVo patchService(PatchInfoVo patchInfoModel,HttpServletRequest request,HttpServletResponse response){
    logger.debug("## Rest Call :  " + patchInfoModel.toString());
    // TODOLIST 사용자 정보 인증
    // 사용자 도메인
    // 사용자 이메일
    // 인증키를 받아 회원 DB에 등록되어 있는지 검증한다.
    String serverVersion = PropertyUtil.getVersionProps(request, true);
    logger.debug("##serverVersion : " + serverVersion);
    patchInfoModel.setWeServerVerionInfo(serverVersion);
    return patchInfoModel;
}


@RequestMapping(method = RequestMethod.POST, value = "/patchList/browser/{functionIdx}")
public void browserDownload(String functionIdx,HttpServletRequest request,HttpServletResponse response){
    logger.debug("###browserDownload !!");
    logger.debug("###functionIdx : " + functionIdx);
    // html 에서 아래와 같이 호출 함
    // var funcFileDownload = function(url){
    // $('#patchDownloadForm').attr('method', 'post');
    // $('#patchDownloadForm').attr('action', '/patchList/download/2');
    // $('#patchDownloadForm').submit();
    // }
    String filePath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v101");
    String fileName = "framework-20130302.jar";
    String fullPath = filePath + "/" + fileName;
    logger.debug("##fullPath : " + fullPath);
    File file = new File(fullPath);
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment; fileName=\"" + file.getName() + "\";");
    response.setHeader("Content-Transfer-Encoding", "binary");
    ServletOutputStream out = response.getOutputStream();
    FileInputStream fls = null;
    try {
        fls = new FileInputStream(file);
        FileCopyUtils.copy(fls, out);
    } finally {
        if (fls != null) {
            try {
                fls.close();
            } catch (IOException ex) {
            }
        }
    }
    out.flush();
}


@RequestMapping(method = RequestMethod.POST, value = "/installAuthUser")
@ResponseBody
public WeInstallUser installAuthUser(WeInstallUser installVo,HttpServletRequest request,HttpServletResponse response){
    logger.debug("## Rest Call :  " + installVo.toString());
    // 사용자 정보 인증 - 액티브 키와 유저 메일로  건수가 있는지 조회한 후 있으면 update, 없으면 insert를 수행한다.
    WeInstallUser installUser = patchService.getInstallUserInfo(installVo);
    if (installUser == null) {
        // 다운로드 정보가 없으므로 새로 저장함
        installVo.setWe_install_date(new Date());
        installVo.setWe_company("undefined");
        installVo.setWe_new_yn("Y");
        installVo.setWe_use_purpose("6");
        entityService.insertEntity(installVo);
        return installVo;
    } else {
        // update
        installUser.setWe_auth_date(new Date());
        installUser.setWe_auth_yn("Y");
        entityService.updateEntity(installUser);
        return installUser;
    }
}


}