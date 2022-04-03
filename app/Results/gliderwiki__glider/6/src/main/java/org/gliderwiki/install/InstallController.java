package org.gliderwiki.install;
 import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.CommonUtil;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.util.SendMailSMTP;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.PatchInfoVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.mysql.jdbc.StringUtils;
import org.gliderwiki.DTO.TempUploadVo;
@Controller
public class InstallController {

 private Logger logger;

@Autowired
 private  RestTemplate restTemplate;

 public  String REST_SERVER_URL;

 private  String jdbcUrl;

 private  String jdbcId;

 private  String jdbcPassword;

 private  String jdbcSchema;

 private  String adminMailId;

 private  String adminPass;

public InstallController() {
}
public void setJdbcPassword(String jdbcPassword){
    this.jdbcPassword = jdbcPassword;
}


public String getJdbcPassword(){
    return jdbcPassword;
}


@RequestMapping(value = "/admin/installUpload", method = RequestMethod.POST)
public ModelAndView profileUpload(TempUploadVo fileVo,HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    String svcPath = request.getSession().getServletContext().getRealPath("/resource/temp/");
    String svcRealPath = request.getSession().getServletContext().getRealPath("/resource/real/");
    String svcDataPath = request.getSession().getServletContext().getRealPath("/resource/data/");
    String rtnCode = ShellCommands.execute("uname");
    logger.debug("### System uname : " + rtnCode);
    logger.debug("### startsWith uname : " + rtnCode.startsWith("Linux"));
    if (rtnCode.startsWith("Linux") || rtnCode.equalsIgnoreCase("linux")) {
        logger.debug("############ Linux 퍼미션 조정 ############");
        ShellCommands.execute("chmod -R 755 " + svcPath);
        ShellCommands.execute("chmod -R 755 " + svcRealPath);
        ShellCommands.execute("chmod -R 755 " + svcDataPath);
    }
    // 업로드 날짜 및 파일명 구성
    String today = DateUtil.getToday();
    double maxSize = 10d;
    // 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
    TempUploadVo tempFile = new TempUploadVo();
    String msg = "";
    try {
        tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, "1", today, maxSize);
        msg = "성공";
    } catch (Exception e) {
        logger.debug("### upload error!! ###");
        msg = e.getMessage();
        e.printStackTrace();
    }
    logger.debug("tempFile : " + tempFile.toString());
    Map<String, String> param = new HashMap<String, String>();
    if (tempFile.isUploaded()) {
        // 임시 파일이기 때문에 DB 인서트는 하지 않음
        param.put("result", "1");
        param.put("msg", msg);
        param.put("thumbPath", tempFile.getThumbPath());
        param.put("thumbName", tempFile.getThumbName());
    } else {
        param.put("result", "-1");
        param.put("msg", msg);
        param.put("thumbPath", "");
        param.put("thumbName", "");
    }
    logger.debug("param : " + param.toString());
    return new ModelAndView("json_").addObject("param", param);
}


public String getJdbcId(){
    return jdbcId;
}


public void setJdbcId(String jdbcId){
    this.jdbcId = jdbcId;
}


public String getJdbcUrl(){
    return jdbcUrl;
}


@RequestMapping(value = "/admin/install/loadData", method = RequestMethod.POST)
public ModelAndView loadData(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### loadData ");
    // CREATE할 테이블 타입
    String charType = request.getParameter("charType");
    logger.debug("charType : " + charType);
    String enc = "";
    if (charType.startsWith("euc")) {
        enc = "EUC_KR";
    } else {
        enc = "UTF8";
    }
    // 관리자 로그인 메일주소(ID)
    String adminMailId = request.getParameter("adminMailId");
    // 관리자 로그인 비밀번
    String adminPass = request.getParameter("adminPass");
    // 시스템 도메인 주소
    String adminSite = request.getParameter("adminSite");
    // 등록  메일주소
    String userMail = request.getParameter("userMail");
    // 활성화를 위한 액티브 키
    String activeKey = request.getParameter("activeKey");
    logger.debug("adminMailId : " + adminMailId);
    logger.debug("adminPass : " + adminPass);
    logger.debug("adminSite : " + adminSite);
    logger.debug("userMail : " + userMail);
    logger.debug("activeKey :" + activeKey);
    this.setAdminMailId(adminMailId);
    this.setAdminPass(adminPass);
    String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);
    Map<String, Object> param = new HashMap<String, Object>();
    int result = 0;
    // 액티브 키로 어드민 암호화 한다.
    String passwd = SecretKeyPBECipher.setUserPassword(adminPass, activeKey);
    WeUser weUser = new WeUser();
    weUser.setWe_user_id(adminMailId);
    weUser.setWe_user_pwd(passwd);
    weUser.setWe_user_nick("ADMIN");
    weUser.setWe_user_name("어드민");
    weUser.setWe_user_key(activeKey);
    WeProfile weProfile = new WeProfile();
    weProfile.setWe_user_email(adminMailId);
    weProfile.setWe_user_site(adminSite);
    SingleDatasourceDao singleDao = new SingleDatasourceDao();
    // 어드민 사용자와 기본 데이터들을 저장한다.
    result = singleDao.insertInitTableData(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), weUser, weProfile, tableInitPath, enc);
    if (result == 1) {
        // Rest API 콜
        WeInstallUser installUserVo = new WeInstallUser();
        // 액티브 키
        installUserVo.setWe_active_key(activeKey);
        // 인증일
        installUserVo.setWe_auth_date(new Date());
        // 인증여부
        installUserVo.setWe_auth_yn("Y");
        // 사이트명
        installUserVo.setWe_domain(adminSite);
        // 이메일
        installUserVo.setWe_email(userMail);
        try {
            HttpEntity<WeInstallUser> entity = new HttpEntity<WeInstallUser>(installUserVo);
            String restUrl = REST_SERVER_URL + "/service/installAuthUser";
            logger.debug("restUrl : " + restUrl);
            ResponseEntity<WeInstallUser> entityResponse = restTemplate.postForEntity(restUrl, entity, WeInstallUser.class);
            WeInstallUser restVo = entityResponse.getBody();
            logger.debug("###restVo : " + restVo.toString());
        } catch (Exception e) {
            logger.debug("레스트 에러 들어옴");
            // Rest Error 일 경우 pass
            result = -4;
            e.getMessage();
        }
    }
    // -3 일 경우 관리자 회원 계정 저장 중 에러발생
    // -2 일 경우 기본 데이터 로드중 에러 발생
    param.put("result", result + "");
    modelAndView.setViewName("admin/install/install");
    return new ModelAndView("json_").addObject("param", param);
}


public String getAdminMailId(){
    return adminMailId;
}


@RequestMapping(value = "/admin/install/createTables", method = RequestMethod.POST)
public ModelAndView createTables(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    // CREATE할 테이블 타입
    String charType = request.getParameter("charType");
    logger.debug("charType : " + charType);
    // strKor
    String strKor = request.getParameter("strKor");
    // String temp =  request.getParameter("strKor");  //strKor
    // String strKor = new String( temp.getBytes("ISO-8859-1"),"UTF-8");
    String enc = "";
    if (charType.startsWith("euc")) {
        enc = "EUC_KR";
    } else {
        enc = "UTF8";
    }
    logger.debug("strKor : " + strKor);
    int result = 0;
    // DB 캐릭터 셋 조회
    List dbCharList = new ArrayList<MySQLVariable>();
    // euckr 이나 utf8 일 경우 true
    boolean isCharacterSet = false;
    isCharacterSet = this.getMySQLCharacterSet(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword());
    Map<String, Object> param = new HashMap<String, Object>();
    if (isCharacterSet) {
        // MySQL 서버 Variable이 utf8 이거나 euckr 일 경우 한글 처리 가능
        String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);
        // 테이블 이름 로드 - 드랍할 경우 대비
        LoadTableData crateTable = new LoadTableData();
        String encoding = crateTable.LoadTableData(tableInitPath, enc);
        logger.debug("###### 테이블 가져온다 : " + crateTable.getAllTables());
        logger.debug("###encoding : " + encoding);
        SingleDatasourceDao singleDao = new SingleDatasourceDao();
        String rtnMsg = "";
        try {
            // 캐릭터 셋 타입에 따라 다른 테이블형태를 create 한다.
            rtnMsg = singleDao.createTables(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), charType, crateTable.getAllTables(), tableInitPath, enc);
            result = 1;
        } catch (Exception e) {
            result = -1;
            rtnMsg = e.getMessage();
        }
        logger.debug("###테이블생성 result : " + result);
        logger.debug("###테이블생성 rtnMsg : " + rtnMsg);
        String resultStr = "";
        if (StringUtil.strNull(rtnMsg).equals("1")) {
            // 테이블에 테스트 데이터를 인서트 한 후 정상적으로 출력되는지 확인한다.
            singleDao.insertLogToKor(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), strKor);
            resultStr = singleDao.selectKorLog(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword());
        }
        int tableSize = 0;
        tableSize = crateTable.getAllTables().size();
        param.put("resultStr", resultStr);
        param.put("rtnMsg", rtnMsg);
        param.put("result", result);
        param.put("tableSize", tableSize);
        param.put("encoding", encoding);
    } else {
        // 한글 캐릭터 셋이 아닐 경우 설치를 지원하지 않는다.
        param.put("resultStr", "");
        param.put("rtnMsg", -1);
        param.put("result", -1);
        param.put("tableSize", "");
        param.put("encoding", enc);
    }
    modelAndView.setViewName("admin/install/install");
    return new ModelAndView("json_").addObject("param", param);
}


public boolean getMySQLCharacterSet(String jdbcUrl,String jdbcId,String jdbcPassword){
    List<MySQLVariable> charList = new ArrayList<MySQLVariable>();
    SingleDatasourceDao singleDao = new SingleDatasourceDao();
    boolean isResult = false;
    try {
        charList = singleDao.getMySQLCharset(jdbcUrl, jdbcId, jdbcPassword);
        logger.debug("##### charList : " + charList.size());
        logger.debug("##### charList.toString() : " + charList.toString());
        if (charList != null) {
            for (int i = 0; i < charList.size(); i++) {
                logger.debug("Cols 1 : " + charList.get(i).getVariable_name());
                logger.debug("Cols 2 : " + charList.get(i).getValue());
                String variable = charList.get(i).getVariable_name();
                String value = charList.get(i).getValue();
                // value 가 utf 로 시작되거나 euckr 일 경우 true 아니면 false
                if (variable.trim().equalsIgnoreCase("character_set_client") || variable.trim().equalsIgnoreCase("character_set_connection") || variable.trim().equalsIgnoreCase("character_set_database")) {
                    if (value.trim().startsWith("utf8") || value.trim().startsWith("UTF8") || value.trim().startsWith("euckr") || value.trim().startsWith("euckr")) {
                        isResult = true;
                    } else {
                        isResult = false;
                    }
                }
            }
        }
    } catch (Exception e) {
        isResult = false;
        e.getMessage();
    }
    return isResult;
}


public void setAdminMailId(String adminMailId){
    this.adminMailId = adminMailId;
}


public void setAdminPass(String adminPass){
    this.adminPass = adminPass;
}


public String getAdminPass(){
    return adminPass;
}


@RequestMapping(value = "/admin/install", method = RequestMethod.GET)
public ModelAndView installMain(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### installMain ");
    if (SecurityContextHolder.getContext().getAuthentication() != null) {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    String initPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
    Properties status = PropertyUtil.getPropertyInfo(initPath, SystemConst.INIT_NAME);
    String installYn = status.getProperty("install.status");
    logger.info("#### installYn : " + installYn);
    if (installYn.trim().equals("Y")) {
        logger.info("글라이더 시스템에 접근할 수 없습니다.\n" + "이미 GLiDER™ Wiki가 설치 되었습니다. " + "설정 변경 및 재설치는 GLiDER™ Wiki의 공식 웹 사이트(http://www.gliderwiki.org)에 문의 하여 주시기 바랍니다");
        String title = "글라이더 시스템에 접근할 수 없습니다";
        String message = "글라이더 시스템에 접근할 수 없습니다.<BR>" + "이미 GLiDER™ Wiki가 설치 되었습니다.<BR>" + "설정 변경 및 재설치는 GLiDER™ Wiki의 공식 웹 사이트(<a href=\"http://www.gliderwiki.org\" target=\"_blank\">http://www.gliderwiki.org</a>)에 문의 하여 주시기 바랍니다";
        modelAndView.addObject("title", title);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error/INFO");
        return modelAndView;
    }
    String domain = CommonUtil.getClientDomain(request);
    /*시스템 정보를 가져옵니다.*/
    String rtnCode = ShellCommands.execute("uname");
    logger.debug("#### rtnCode : " + rtnCode);
    modelAndView.addObject("rtnCode", rtnCode.trim());
    modelAndView.addObject("domain", domain);
    modelAndView.setViewName("admin/install/install");
    return modelAndView;
}


public String getJdbcSchema(){
    return jdbcSchema;
}


public void setJdbcSchema(String jdbcSchema){
    this.jdbcSchema = jdbcSchema;
}


@RequestMapping(value = "/admin/install/jdbcConnection", method = RequestMethod.GET)
public ModelAndView jdbcConnection(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### installStep2 ");
    // JDBC URL
    String jdbc_url = request.getParameter("jdbc_url");
    // JDBC ID
    String jdbc_id = request.getParameter("jdbc_id");
    // JDBC PW
    String jdbc_pw = request.getParameter("jdbc_pw");
    logger.debug("### jdbc_url " + jdbc_url);
    logger.debug("### jdbc_id " + jdbc_id);
    logger.debug("### jdbc_pw " + jdbc_pw);
    /* 생성된 DB Information Properties의 정보로 DB Select를 해온다.*/
    int result = 0;
    SingleDatasourceDao ds = new SingleDatasourceDao();
    result = ds.selectInfoJDBC(jdbc_url, jdbc_id, jdbc_pw);
    logger.debug("***********************");
    logger.debug("JDBC Connection Result : " + result);
    logger.debug("***********************");
    Map<String, Object> param = new HashMap<String, Object>();
    if (1 == result) {
        int startSchema = jdbc_url.lastIndexOf("/");
        int endSchema = jdbc_url.indexOf("?");
        if (endSchema < 1) {
            endSchema = jdbc_url.length();
        }
        logger.debug("###startSchema : " + startSchema);
        logger.debug("###endSchema : " + endSchema);
        // DB스키마 명을 세팅한다.
        String schema = jdbc_url.substring(startSchema + 1, endSchema);
        logger.debug("###schema : " + schema);
        this.setJdbcUrl(jdbc_url);
        this.setJdbcId(jdbc_id);
        this.setJdbcPassword(jdbc_pw);
        this.setJdbcSchema(schema);
        // /WEB-INF/spring/properties/jdbc 디렉토리 하위에 생성하기 위해 경로를 세팅한다.
        String jdbcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "jdbc");
        logger.debug("jdbcPath : " + jdbcPath);
        logger.debug("##root path : " + request.getServletContext().getRealPath("/"));
        InstallPropertyUtil property = new InstallPropertyUtil();
        // DB Information Properties 쓰기 (jdbc.properties)
        result = property.CreateJDBCProperties(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), jdbcPath);
        MySQLVariable variables = new MySQLVariable();
        if (result == 1) {
            SingleDatasourceDao singleDao = new SingleDatasourceDao();
            try {
                variables = singleDao.checkVariables(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword());
            } catch (Exception e) {
                e.getMessage();
            }
            if (StringUtil.strNull(variables.getValue()).equals("0")) {
                // 대소문자 구분함으로 에러 메시지 처리함
                param.put("result", "SUCCESS");
                param.put("status", "-3");
            } else {
                param.put("result", "SUCCESS");
                param.put("status", "1");
            }
        } else {
            // 프로퍼티 에러 -1
            param.put("result", "FAIL");
            param.put("status", result);
        }
    } else {
        /*
			 * MySQL이 연결 안될 경우 
			 * org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; 
			 * nested exception is com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure
			 * 와 같은 에러가 발생함 
			 * SQLException 일 경우 대부분 아이디 // 패스워드가 잘못된 경우임 
			 */
        // OK
        param.put("result", "FAIL");
        // -2
        param.put("status", result);
    }
    modelAndView.setViewName("admin/install/install");
    return new ModelAndView("json_").addObject("param", param);
}


@RequestMapping(value = "/admin/install/dropTables", method = RequestMethod.GET)
public ModelAndView dropTables(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### dropTables ");
    // CREATE할 테이블 타입
    String charType = request.getParameter("charType");
    logger.debug("charType : " + charType);
    String enc = "";
    if (charType.startsWith("euc")) {
        enc = "EUC_KR";
    } else {
        enc = "UTF8";
    }
    Map<String, Object> param = new HashMap<String, Object>();
    String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);
    // 테이블 이름 로드 하기
    LoadTableData createTable = new LoadTableData();
    String encoding = createTable.LoadTableData(tableInitPath, enc);
    logger.debug("$$encoding : " + encoding);
    SingleDatasourceDao singleDao = new SingleDatasourceDao();
    // 드랍 버튼 클릭시 스키마에 존재하는 생성 테이블들을 삭제해준다.
    int result = singleDao.dropTables(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), createTable.getAllTables());
    int tableSize = createTable.getAllTables().size();
    logger.debug("### 리턴 result : " + result);
    logger.debug("### 리턴 tableSize : " + tableSize);
    if (result > 0) {
        param.put("result", result);
        param.put("encoding", encoding);
        param.put("tableSize", tableSize);
    } else {
        param.put("result", result);
        param.put("encoding", encoding);
        param.put("tableSize", tableSize);
    }
    modelAndView.setViewName("admin/install/install");
    return new ModelAndView("json_").addObject("param", param);
}


public void setJdbcUrl(String jdbcUrl){
    this.jdbcUrl = jdbcUrl;
}


@RequestMapping(value = "/admin/install/mailSend", method = RequestMethod.POST)
public ModelAndView mailSend(HttpServletRequest request,HttpServletResponse response,ModelAndView modelAndView){
    logger.debug("### installStep5 ");
    String testUserMail = StringUtil.strNull(request.getParameter("testUserMail"));
    String mailUserId = StringUtil.strNull(request.getParameter("mailUserId"));
    String mailUserPassword = StringUtil.strNull(request.getParameter("mailUserPassword"));
    // mailUserKey
    String mailUserKey = StringUtil.strNull(request.getParameter("mailUserKey"));
    String smtpHostName = StringUtil.strNull(request.getParameter("smtpHostName"));
    String smtpServerPort = StringUtil.strNull(request.getParameter("smtpServerPort"));
    String mailCharset = StringUtil.strNull(request.getParameter("mailCharset"));
    String siteDomain = StringUtil.strNull(request.getParameter("siteDomain"));
    String siteOwner = StringUtil.strNull(request.getParameter("siteOwner"));
    // /WEB-INF/spring/properties/config 하위 디렉토리에 mail.properties 파일을 저장한다.
    String configPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
    // 패스워드 암호화
    String passwd = SecretKeyPBECipher.setUserPassword(mailUserPassword, mailUserKey);
    logger.debug("### 변환 패스워드 :" + passwd);
    InstallPropertyUtil property = new InstallPropertyUtil();
    int result = property.CreateMailProperties(mailUserId, passwd, mailUserKey, smtpHostName, smtpServerPort, mailCharset, siteDomain, siteOwner, configPath);
    Map<String, Object> param = new HashMap<String, Object>();
    String domain = CommonUtil.getClientDomain(request);
    String adminUrl = domain + "admin/wikiadminlogin";
    // 결과에 상관없이 암호화 모듈때문에 properties는 생성되어야 한다.
    property.CreateCionfigProperties(mailUserId, mailUserKey, siteDomain, configPath);
    if (result == 1) {
        // 메일을 전송한다.
        String emailMsgTxt = "안녕하세요. " + siteOwner + " 입니다. <br><br>" + "관리자 모드에서 보내는 테스트 메일 입니다.<br><br>" + "어드민 모드는 " + adminUrl + "을 통해서 입력한 계정으로 로그인 할 수 있습니다.<br><br>" + "계정 정보는 아래와 같습니다.<br><br>" + "ID : " + this.getAdminMailId() + "  /   Password : " + this.getAdminPass() + "<br><br> 비밀번호 분실시 초기화 할 수 없으므로 반드시 기억하시기 바랍니다. 회원 프로필 정보에서 추후 비밀번호는 변경이 가능합니다.";
        // 제목
        String emailTitle = siteOwner + " Wiki 안내 메일입니다.";
        try {
            SendMailSMTP sender = new SendMailSMTP();
            result = sender.sendSimpleMail(testUserMail, emailTitle, emailMsgTxt, request);
            logger.debug("###result : " + result);
            if (result > 0) {
                // init.properties 파일의 인스톨 상태값을 Y로 변경한다.
                String initPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
                Properties props = PropertyUtil.getPropertyInfo(initPath, SystemConst.INIT_NAME);
                String installYn = "Y";
                String version = props.getProperty("install.version");
                property.createInitProperties(installYn, version, initPath);
                param.put("result", "SUCCESS");
                param.put("status", SystemConst.CALL_SUCCESS);
                param.put("rtnResult", result);
            } else {
                param.put("result", "FAIL");
                param.put("status", SystemConst.CALL_FAIL);
                param.put("rtnResult", -1);
            }
        } catch (UnsupportedEncodingException e) {
            param.put("result", "FAIL");
            param.put("status", SystemConst.CALL_FAIL);
            param.put("rtnResult", -4);
        }
    } else {
        // Properties 생성 중 에러 발생
        param.put("result", "FAIL");
        param.put("status", SystemConst.CALL_FAIL);
        param.put("rtnResult", -3);
    }
    return new ModelAndView("json_").addObject("param", param);
}


}