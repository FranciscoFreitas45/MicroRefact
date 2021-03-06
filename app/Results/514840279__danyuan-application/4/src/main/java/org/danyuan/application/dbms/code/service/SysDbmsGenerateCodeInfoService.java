package org.danyuan.application.dbms.code.service;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.utils.compress.CompressFile;
import org.danyuan.application.common.utils.files.FileDelete;
import org.danyuan.application.common.utils.files.TxtFilesWriter;
import org.danyuan.application.dbms.code.po.SysDbmsGenerateCodeInfo;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
@Service
public class SysDbmsGenerateCodeInfoService extends BaseServiceImpl<SysDbmsGenerateCodeInfo>implements BaseService<SysDbmsGenerateCodeInfo>{

@Autowired
 private  SysDbmsTabsColsInfoDao sysDbmsTabsColsInfoDao;

@Autowired
 private  SysDbmsTabsInfoDao sysDbmsTabsInfoDao;

 public  String OUTPUTFILE;


public void getGenerateController(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo,SysDbmsTabsInfo tabsInfo,List<SysDbmsTabsColsInfo> colsInfos,String username,String pathString){
    String thirdString = "";
    String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
    for (int i = 0; i < 3; i++) {
        thirdString += subpathString[i] + ".";
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".controller;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("import " + thirdString + "common.base.BaseController;\r\n");
    stringBuilder.append("import " + thirdString + "common.base.BaseControllerImpl;\r\n");
    stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
    stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".service." + sysDbmsGenerateCodeInfo.getClassName() + "Service;\r\n");
    stringBuilder.append("import org.slf4j.Logger;\r\n");
    stringBuilder.append("import org.slf4j.LoggerFactory;\r\n");
    stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
    stringBuilder.append("import org.springframework.web.bind.annotation.GetMapping;\r\n");
    stringBuilder.append("import org.springframework.web.bind.annotation.PathVariable;\r\n");
    stringBuilder.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
    stringBuilder.append("import org.springframework.web.bind.annotation.RestController;\r\n");
    stringBuilder.append("import org.springframework.web.servlet.ModelAndView;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("/**\r\n");
    stringBuilder.append(" * @????????? " + sysDbmsGenerateCodeInfo.getClassName() + "Controller.java\r\n");
    stringBuilder.append(" * @?????? " + sysDbmsGenerateCodeInfo.getClassPath() + ".controller\r\n");
    stringBuilder.append(" * @?????? controller???\r\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd??? HH:mm:ss");
    stringBuilder.append(" * @?????? " + simpleDateFormat.format(new Date()) + "\r\n");
    stringBuilder.append(" * @author " + username + "\r\n");
    stringBuilder.append(" * @?????? V1.0\r\n");
    stringBuilder.append(" */\r\n");
    stringBuilder.append("@RestController\r\n");
    String subServiceNameString = sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase() + sysDbmsGenerateCodeInfo.getClassName().substring(1);
    stringBuilder.append("@RequestMapping(\"/" + subServiceNameString + "\")\r\n");
    stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + "Controller extends BaseControllerImpl<" + sysDbmsGenerateCodeInfo.getClassName() + "> implements BaseController<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("	private static final Logger		logger	= LoggerFactory.getLogger(" + sysDbmsGenerateCodeInfo.getClassName() + "Controller.class);\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("	@Autowired\r\n");
    stringBuilder.append("	" + sysDbmsGenerateCodeInfo.getClassName() + "Service " + subServiceNameString + "Service;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("	@GetMapping(\"/detail/{uuid}\")\r\n");
    stringBuilder.append("	public ModelAndView name(@PathVariable(\"uuid\") String uuid) {\r\n");
    stringBuilder.append("	    logger.info(\"detail\", " + sysDbmsGenerateCodeInfo.getClassName() + "Controller.class);\r\n");
    stringBuilder.append("		ModelAndView modelAndView = new ModelAndView(\"" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/") + "/" + subServiceNameString.toLowerCase() + "detail\");\r\n");
    stringBuilder.append("		" + sysDbmsGenerateCodeInfo.getClassName() + " info = new " + sysDbmsGenerateCodeInfo.getClassName() + "();\r\n");
    stringBuilder.append("		info.setUuid(uuid);\r\n");
    stringBuilder.append("		modelAndView.addObject(\"" + subServiceNameString + "\", " + subServiceNameString + "Service.findOne(info));\r\n");
    stringBuilder.append("		return modelAndView;\r\n");
    stringBuilder.append("	}\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("}");
    // ????????????
    String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Controller.java";
    TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
}


public void getGenerateService(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo,SysDbmsTabsInfo tabsInfo,List<SysDbmsTabsColsInfo> colsInfos,String username,String pathString){
    String thirdString = "";
    String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
    for (int i = 0; i < 3; i++) {
        thirdString += subpathString[i] + ".";
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".service;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("import " + thirdString + "common.base.BaseService;\r\n");
    stringBuilder.append("import " + thirdString + "common.base.BaseServiceImpl;\r\n");
    stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
    // stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao." + sysDbmsGenerateCodeInfo.getClassName() + "Dao;\r\n");
    stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
    stringBuilder.append("import org.springframework.stereotype.Service;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("/**\r\n");
    stringBuilder.append(" * @????????? " + sysDbmsGenerateCodeInfo.getClassName() + "Service.java\r\n");
    stringBuilder.append(" * @?????? " + sysDbmsGenerateCodeInfo.getClassPath() + ".service\r\n");
    stringBuilder.append(" * @?????? service???\r\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd??? HH:mm:ss");
    stringBuilder.append(" * @?????? " + simpleDateFormat.format(new Date()) + "\r\n");
    stringBuilder.append(" * @author " + username + "\r\n");
    stringBuilder.append(" * @?????? V1.0\r\n");
    stringBuilder.append(" */\r\n");
    stringBuilder.append("@Service\r\n");
    stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + "Service extends BaseServiceImpl<" + sysDbmsGenerateCodeInfo.getClassName() + "> implements BaseService<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
    // stringBuilder.append(" @Autowired\r\n");
    // stringBuilder.append(" private " + sysDbmsGenerateCodeInfo.getClassName() + "Dao " + sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase() + sysDbmsGenerateCodeInfo.getClassName().substring(1) + "Dao;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("}\r\n");
    stringBuilder.append("");
    // ????????????
    String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Service.java";
    TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
}


public void getGenerateDao(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo,SysDbmsTabsInfo tabsInfo,List<SysDbmsTabsColsInfo> colsInfos,String username,String pathString){
    String thirdString = "";
    String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
    for (int i = 0; i < 3; i++) {
        thirdString += subpathString[i] + ".";
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("import " + thirdString + "common.base.BaseDao;\r\n");
    stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
    stringBuilder.append("import org.springframework.stereotype.Repository;\r\n");
    stringBuilder.append("\r\n");
    stringBuilder.append("/**\r\n");
    stringBuilder.append(" * @????????? " + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java\r\n");
    stringBuilder.append(" * @?????? " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao\r\n");
    stringBuilder.append(" * @?????? dao???\r\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd??? HH:mm:ss");
    stringBuilder.append(" * @?????? " + simpleDateFormat.format(new Date()) + "\r\n");
    stringBuilder.append(" * @author " + username + "\r\n");
    stringBuilder.append(" * @?????? V1.0\r\n");
    stringBuilder.append(" */\r\n");
    stringBuilder.append("@Repository\r\n");
    stringBuilder.append("public interface " + sysDbmsGenerateCodeInfo.getClassName() + "Dao extends BaseDao<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
    stringBuilder.append("	\r\n");
    stringBuilder.append("}\r\n");
    stringBuilder.append("");
    // ????????????
    String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java";
    TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
}


public void generate(List<SysDbmsGenerateCodeInfo> list,String username,String pathString) throws FileNotFoundException{
    String path = System.getProperty("user.dir") + "/" + OUTPUTFILE + "/" + pathString;
    File file = new File(path);
    for (SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo : list) {
        // javafile ??????
        String pathtempString = path + "/src/main/java/" + sysDbmsGenerateCodeInfo.getClassPath().replace(".", "/");
        file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        SysDbmsTabsColsInfo colsInfo = new SysDbmsTabsColsInfo();
        colsInfo.setTabsUuid(sysDbmsGenerateCodeInfo.getUuid());
        Example<SysDbmsTabsColsInfo> example = Example.of(colsInfo);
        List<SysDbmsTabsColsInfo> colsInfos = sysDbmsTabsColsInfoDao.findAll(example);
        SysDbmsTabsInfo tabsInfo = new SysDbmsTabsInfo();
        tabsInfo = sysDbmsTabsInfoDao.findById(sysDbmsGenerateCodeInfo.getUuid()).get();
        // ???????????????
        if (sysDbmsGenerateCodeInfo.getGenerateEntity() == 1) {
            file = new File(pathtempString + "/po");
            if (!file.exists()) {
                file.mkdirs();
            }
            GenerateEntity.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/po");
        }
        // dao?????????
        if (sysDbmsGenerateCodeInfo.getGenerateDao() == 1) {
            file = new File(pathtempString + "/dao");
            if (!file.exists()) {
                file.mkdirs();
            }
            getGenerateDao(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/dao");
        }
        // service?????????
        if (sysDbmsGenerateCodeInfo.getGenerateService() == 1) {
            file = new File(pathtempString + "/service");
            if (!file.exists()) {
                file.mkdirs();
            }
            getGenerateService(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/service");
        }
        // controller?????????
        if (sysDbmsGenerateCodeInfo.getGenerateController() == 1) {
            file = new File(pathtempString + "/controller");
            if (!file.exists()) {
                file.mkdirs();
            }
            getGenerateController(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/controller");
        }
        String thirdString = "";
        String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
        for (int i = 0; i < 3; i++) {
            thirdString += subpathString[i] + ".";
        }
        // html?????????
        if (sysDbmsGenerateCodeInfo.getGenerateHtml() == 1) {
            // static ??????????????????
            pathtempString = path + "/src/main/resources/static/pages/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
            file = new File(pathtempString);
            if (!file.exists()) {
                file.mkdirs();
            }
            // html?????????
            GenerateHtml.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
            // js?????????
            GenerateJs.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
        }
        // detailhtml?????????
        if (sysDbmsGenerateCodeInfo.getGenerateDetail() == 1) {
            // templates ????????????
            pathtempString = path + "/src/main/resources/templates/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
            file = new File(pathtempString);
            if (!file.exists()) {
                file.mkdirs();
            }
            GenerateHtml.generateDetail(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
            // js?????????
            // static ??????????????????
            pathtempString = path + "/src/main/resources/static/pages/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
            file = new File(pathtempString);
            if (!file.exists()) {
                file.mkdirs();
            }
            GenerateJs.generateDetail(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
        }
        // Sql ??????
        if (sysDbmsGenerateCodeInfo.getGenerateSql() == 1) {
            // sql ??????????????????
            pathtempString = path + "/src/main/resources/sql/";
            file = new File(pathtempString);
            if (!file.exists()) {
                file.mkdirs();
            }
            // Sql ddl ??????
            GenerateSql.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
            // Sql ddl ??????
            GenerateSql.generateOracle(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
            // Sql ??????????????? ??????
            GenerateSql.generateConfig(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
        }
        // ???????????? ???????????? ????????????
        if (sysDbmsGenerateCodeInfo.getGenerateDoc() == 1) {
            // sql ??????????????????
            try {
                pathtempString = path + "/????????????.xlsx";
                GenerateDoc.generateXlsx(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
                pathtempString = path + "/????????????.xls";
                GenerateDoc.generateXls(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // ????????????
    FileOutputStream fos1 = new FileOutputStream(new File(path + ".zip"));
    CompressFile.toZip(path, fos1, true);
    // ?????? ????????? TODO
    FileDelete.delFolder(path);
}


}