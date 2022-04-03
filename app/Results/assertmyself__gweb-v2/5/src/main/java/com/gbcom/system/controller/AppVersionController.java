package com.gbcom.system.controller;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.gbcom.common.hibernate.GridJq;
import com.gbcom.common.hibernate.QueryTranslateJq;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.daoservice.AppVersionService;
import com.gbcom.system.domain.AppVersion;
import com.gbcom.system.manager.ConfigManager;
import com.gbcom.system.utils.SecurityUtil;
import com.hc.core.controller.BaseCRUDActionController;
import com.hc.core.orm.hibernate.Page;
import com.hc.core.utils.ReflectionUtils;
import com.jspsmart.upload.SmartUpload;
@Controller
public class AppVersionController extends BaseCRUDActionController<AppVersion>{

 private  Logger logger;

@Autowired
 private  AppVersionService appVersionService;

@Autowired
 private  ConfigManager configManager;

 private  File attachment;


@RequestMapping
@UserLog
public String add(Model model){
    AppVersion appVersion = new AppVersion();
    // 如需增加其他默认值请在此添加
    model.addAttribute("bean", appVersion);
    return "view/system/appVersion/add";
}


public void copyFile(File srcFile,String dstFilePath){
    try {
        InputStream inStream = new FileInputStream(srcFile);
        FileOutputStream fileoutputstream = new FileOutputStream(dstFilePath);
        byte[] buffer = new byte[1444];
        int length;
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = inStream.read(buffer)) != -1) {
            // 字节数 文件大小
            bytesum += byteread;
            System.out.println(bytesum);
            fileoutputstream.write(buffer, 0, byteread);
        }
        fileoutputstream.flush();
        fileoutputstream.close();
        inStream.close();
    } catch (IOException e) {
        // To change body of catch statement use File | Settings | File Templates.
        e.printStackTrace();
    }
}


public void getVersion(HttpServletResponse response,String md5){
    // 通过MD5从数据库中查找对应的记录
    String hql = "from appVersion where md5='" + md5 + "'";
    AppVersion appVersion = appVersionService.findUnique(hql);
    if (null == appVersion) {
        return;
    }
    // 区分IOS和Android版本文件
    if (appVersion.getAppOsType() == AppVersion.APP_OS_TYPE_ANDROID) {
        String filePath = getServletContext().getRealPath("/") + appVersion.getAppFilePath();
        File file = new File(filePath);
        // 返回版本文件
        sendBinaryStream(response, file);
    } else if (appVersion.getAppOsType() == AppVersion.APP_OS_TYPE_IOS) {
        sendSuccessJSON(response, appVersion.getAppPlistFileUrl());
    }
}


public File getAttachment(){
    return this.attachment;
}


@RequestMapping
@UserLog
public void upload(HttpServletResponse response,HttpServletRequest request){
    if (!(request instanceof MultipartHttpServletRequest)) {
        sendFailureJSON(response, "导入失败");
        return;
    }
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    // 获得文件：
    MultipartFile file = multipartRequest.getFile("attachment");
    // 获得文件名：
    String filename = file.getOriginalFilename();
    String fileName = filename;
    String filePath = "";
    try {
        if (-1 != fileName.indexOf(".apk")) {
            filePath = configManager.getAppFilePath();
            filePath += "/android/" + fileName;
            // 文件拷贝
            // copyFile(file, filePath);
            File source = new File(filePath.toString());
            file.transferTo(source);
        } else if (-1 != fileName.indexOf(".ipa")) {
            filePath = configManager.getAppFilePath();
            filePath = "/ios/" + fileName;
            // 文件拷贝
            // copyFile(file, filePath);
            File source = new File(filePath.toString());
            file.transferTo(source);
        } else {
            sendFailureJSON(response, "导入版本文件非法");
            return;
        }
        File attachment = new File(filePath);
        setAttachment(attachment);
        sendSuccessJSON(response, filePath);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void save(HttpServletResponse response,AppVersion entity){
    try {
        AppVersion target;
        if (entity.getId() != null) {
            target = appVersionService.get(entity.getId());
            ReflectionUtils.copyBean(entity, target, new String[] { "updateContent" });
        } else {
            target = entity;
        }
        appVersionService.save(target);
        sendSuccessJSON(response, "保存成功");
    } catch (Exception e) {
        super.processException(response, e);
    }
}


@RequestMapping
public void insert(HttpServletResponse response,HttpServletRequest request,Long appVersionCode,String updateContent,String attachmentFileName){
    try {
        /*
            // 新建一个SmartUpload对象
            SmartUpload su = new SmartUpload();
            // 上传初始化
            ServletContext context = getServletContext();
            su.initialize(context,null, request,response, null);
            // 设定上传限制
            // 1.限制每个上传文件的最大长度。
            // su.setMaxFileSize(10000); //字节
            // 2.限制总上传数据的长度。
            // su.setTotalMaxFileSize(20000);
            // 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
            // su.setAllowedFilesList("doc,txt");
            // 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有
            //扩展名的文件。
            // su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
            // 上传文件
            su.upload();
            // 将上传文件全部保存到指定目录，需要先在在Web应用的根目录下，创建一个upload目录
            int count = su.save("/upload");
            System.out.println(count+"个文件上传成功！<br>");
            // 利用Request对象获取参数之值
            //System.out.println("TEST="+su.getRequest().getParameter("TEST")
            //        +"<BR><BR>");
            // 逐一提取上传文件信息，同时可保存文件。
            for (int i=0;i<su.getFiles().getCount();i++)
            {
                com.jspsmart.upload.SmartFile file = su.getFiles().getFile(i);
                // 若文件不存在则继续
                if (file.isMissing()) continue;
                            */
        // 显示当前文件信息
        /*System.out.println("<TABLE BORDER=1>");
                System.out.println("<TR><TD>表单项名（FieldName）</TD><TD>"
                        + file.getFieldName() + "</TD></TR>");
                System.out.println("<TR><TD>文件长度（Size）</TD><TD>" +
                        file.getSize() + "</TD></TR>");
                System.out.println("<TR><TD>文件名（FileName）</TD><TD>"
                        + file.getFileName() + "</TD></TR>");
                System.out.println("<TR><TD>文件扩展名（FileExt）</TD><TD>"
                        + file.getFileExt() + "</TD></TR>");
                System.out.println("<TR><TD>文件全名（FilePathName）</TD><TD>"
                        + file.getFilePathName() + "</TD></TR>");
                System.out.println("</TABLE><BR>");
                */
        // 检查目标目录是否存在，如果不存在，则需要创建
        // 检查上传版本文件是android还是ios类型，可以根据后缀进行判断
        // 检查文件名称格式是否正确。
        /*
                if (!(request instanceof MultipartHttpServletRequest)) {
                    sendSuccessJSON(response, "导入失败");
                    return;
                }

                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                // 获得文件：
                MultipartFile file = multipartRequest.getFile("attachment");
                // 获得文件名：
                String filename = file.getOriginalFilename();
                */
        File file = null;
        if (null != attachmentFileName && !attachmentFileName.isEmpty()) {
            file = new File(attachmentFileName);
        } else {
            file = getAttachment();
        }
        if (null == file) {
            sendFailureJSON(response, "未导入版本文件");
            return;
        }
        String filename = file.getName();
        AppVersion appVersion = new AppVersion();
        // 临时这么写
        String vendorCode = "";
        Integer appOsType = null;
        String fileName = filename;
        String filePath = "";
        String versionName = "";
        // Long versionCode = null;
        String plistFileDir = "";
        int begin = 0;
        int end = 0;
        // filePath = file.getAbsolutePath();
        // 检查版本是否已经存在
        AppVersion checkAppVersion = appVersionService.findUnique("from AppVersion where appFileName='" + fileName + "'");
        if (null != checkAppVersion) {
            sendFailureJSON(response, "导入失败，该版本已经存在");
            return;
        }
        checkAppVersion = appVersionService.findUnique("from AppVersion where appVersionCode=" + appVersionCode.longValue());
        if (null != checkAppVersion) {
            sendFailureJSON(response, "导入失败，该版本已经存在");
            return;
        }
        if (-1 != fileName.indexOf(".apk")) {
            appOsType = AppVersion.APP_OS_TYPE_ANDROID;
            // filePath = configManager.getAppFilePath();
            filePath += "/android/" + fileName;
            // 文件拷贝
            // copyFile(file, filePath);
            // file.saveAs(filePath, su.SAVE_PHYSICAL);
            // File source = new File(filePath.toString());
            // file.transferTo(source);
            begin = fileName.indexOf("smarthome-");
            if (-1 == begin) {
                sendFailureJSON(response, "导入失败");
                return;
            }
            begin += "smarthome-".length();
            end = fileName.indexOf(".apk");
            versionName = fileName.substring(begin, end - 1);
            int end2 = versionName.indexOf("-");
            if (-1 != end2) {
                vendorCode = versionName.substring(begin, end2 - 1);
                versionName = versionName.substring(end2 + 1);
            }
        } else if (-1 != fileName.indexOf(".ipa")) {
            appOsType = AppVersion.APP_OS_TYPE_IOS;
            // filePath = configManager.getAppFilePath();
            filePath = "/ios/" + fileName;
            // 文件拷贝
            // copyFile(file, filePath);
            // file.saveAs(filePath, su.SAVE_PHYSICAL);
            // File source = new File(filePath.toString());
            // file.transferTo(source);
            begin = fileName.indexOf("smarthome-");
            if (-1 == begin) {
                sendFailureJSON(response, "导入失败");
                return;
            }
            begin += "smarthome-".length();
            end = fileName.indexOf(".ipa");
            versionName = fileName.substring(begin, end - 1);
            int end2 = versionName.indexOf("-");
            if (-1 != end2) {
                vendorCode = versionName.substring(begin, end2 - 1);
                versionName = versionName.substring(end2 + 1);
            }
            // 自动生成plist文件
            String plistFileName = fileName.substring(0, begin);
            plistFileName += "plist";
            String plistFilePath = "/gsapp/ios/" + plistFileName;
            plistFileDir = "https://cloud.gsine.com.cn" + plistFilePath;
            String appPlistPath = getServletContext().getRealPath("/") + plistFilePath;
            FileWriter fw = new FileWriter(appPlistPath);
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            buffer.append("\n");
            buffer.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
            buffer.append("\n");
            buffer.append("<plist version=\"1.0\">");
            buffer.append("\n");
            buffer.append("<dict>");
            buffer.append("\n");
            buffer.append("<key>items</key>");
            buffer.append("\n");
            buffer.append("<array>");
            buffer.append("\n");
            buffer.append("<dict>");
            buffer.append("\n");
            buffer.append("<key>assets</key>");
            buffer.append("\n");
            buffer.append("<array>");
            buffer.append("\n");
            buffer.append("<dict>");
            buffer.append("\n");
            buffer.append("<key>kind</key>");
            buffer.append("\n");
            buffer.append("<string>display-image</string>");
            buffer.append("\n");
            buffer.append("<key>url</key>");
            buffer.append("\n");
            buffer.append("<string>http://cloud.gsine.com.cn/gsapp/ios/ios_app_icon.png</string>");
            buffer.append("\n");
            buffer.append("<key>needs-shine</key>");
            buffer.append("\n");
            buffer.append("<string>YES/NO</string>");
            buffer.append("\n");
            buffer.append("</dict>");
            buffer.append("\n");
            buffer.append("<dict>");
            buffer.append("\n");
            buffer.append("<key>kind</key>");
            buffer.append("\n");
            buffer.append("<string>software-package</string>");
            buffer.append("\n");
            buffer.append("<key>url</key>");
            buffer.append("\n");
            buffer.append("<string>https://cloud.gsine.com.cn/gsapp/ios/" + fileName + "</string>");
            buffer.append("\n");
            buffer.append("</dict>");
            buffer.append("\n");
            buffer.append("</array>");
            buffer.append("\n");
            buffer.append("<key>metadata</key>");
            buffer.append("\n");
            buffer.append("<dict>");
            buffer.append("\n");
            buffer.append("<key>bundle-identifier</key>");
            buffer.append("\n");
            buffer.append("<string>cn.com.gsine.app</string>");
            buffer.append("\n");
            buffer.append("<key>bundle-version</key>");
            buffer.append("\n");
            buffer.append("<string>" + versionName + "</string>");
            buffer.append("\n");
            buffer.append("<key>kind</key>");
            buffer.append("\n");
            buffer.append("<string>software</string>");
            buffer.append("\n");
            buffer.append("<key>title</key>");
            buffer.append("\n");
            buffer.append("<string>寰创通信</string>");
            buffer.append("\n");
            buffer.append("</dict>");
            buffer.append("\n");
            buffer.append("</dict>");
            buffer.append("\n");
            buffer.append("</array>");
            buffer.append("\n");
            buffer.append("</dict>");
            buffer.append("\n");
            buffer.append("</plist>");
            buffer.append("\n");
            String s = buffer.toString();
            fw.write(s, 0, s.length());
            fw.flush();
        } else {
            // 忽略，继续上传
            sendFailureJSON(response, "导入失败");
            return;
        }
        appVersion.setVendorCode(vendorCode);
        appVersion.setAppOsType(appOsType);
        // 持久化，保存到数据库
        appVersion.setAppVersionName(versionName);
        appVersion.setAppVersionCode(appVersionCode);
        appVersion.setAppFileName(fileName);
        appVersion.setAppFilePath(filePath);
        DecimalFormat df = new DecimalFormat(".## ");
        // appVersion.setAppFileSize(df.format(file.getSize()/1024000f));
        appVersion.setAppFileSize(df.format(file.getTotalSpace() / 1024000f));
        String md5 = SecurityUtil.MD5(vendorCode + "," + appOsType.longValue() + "," + appVersionCode.longValue());
        appVersion.setMd5(md5);
        appVersion.setAppPlistFileUrl(plistFileDir);
        appVersion.setCreateTimestamp(System.currentTimeMillis());
        // 暂时这么写
        appVersion.setUpdateContent(updateContent);
        appVersion.setState(true);
        appVersionService.save(appVersion);
    // 将文件另存
    // file.saveAs("/upload/" + file.getFileName());
    // 另存到以WEB应用程序的根目录为文件根目录的目录下
    // file.saveAs("/upload/" + file.getFileName(),su.SAVE_VIRTUAL);
    // 另存到操作系统的根目录为文件根目录的目录下
    // file.saveAs("c:\\temp\\" + myFile.getFileName(),su.SAVE_PHYSICAL);
    /*
            }          */
    } catch (Exception e) {
        logger.error("Exception：", e);
        super.processException(response, e);
        return;
    }
    sendSuccessJSON(response, "导入成功");
}


@RequestMapping
@UserLog
public void delete(HttpServletResponse response,Long id){
    AppVersion appVersion = appVersionService.get(id);
    String appPath = getServletContext().getRealPath("/") + appVersion.getAppFilePath();
    // String appPath = getServletContext().getRealPath("/")+filePath;
    System.out.println(appPath);
    File file = new File(appPath);
    file.delete();
    // 如果是ios的版本文件，还需要删除plist文件
    if (-1 != appPath.indexOf(".ipa")) {
        String appPlistPath = appPath.replaceAll(".ipa", ".plist");
        System.out.println(appPlistPath);
        File filePlist = new File(appPlistPath);
        filePlist.delete();
    }
    appVersionService.delete(id);
    sendSuccessJSON(response, "删除成功");
}


@RequestMapping
@UserLog
public String modify(Model model,Long id){
    AppVersion appVersion = appVersionService.get(id);
    // 处理其他业务逻辑
    model.addAttribute("bean", appVersion);
    return "view/system/appVersion/input";
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_NEOPER)
public void download(HttpServletResponse response,String filePath,HttpServletRequest request){
    String appPath = getServletContext().getRealPath("/") + filePath;
    System.out.println(appPath);
    try {
        // 新建一个SmartUpload对象
        SmartUpload su = new SmartUpload();
        // 初始化
        ServletContext context = getServletContext();
        // ServletConfig config = (ServletConfig) context.getAttribute("servletConfig");
        su.initialize(context, null, request, response, null);
        // 设定contentDisposition为null以禁止浏览器自动打开文件，
        // 保证点击链接后是下载文件。若不设定，则下载的文件扩展名为doc时，浏览器将自动用word打开它。
        // 扩展名为pdf时，浏览器将用acrobat打开。
        su.setContentDisposition(null);
        // 下载文件
        su.downloadFile(appPath);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping
@UserLog
public String view(Model model,Long id){
    AppVersion appVersion = appVersionService.get(id);
    model.addAttribute("bean", appVersion);
    return "view/system/appVersion/view";
}


@RequestMapping
@UserLog
public String grid(Model model){
    // 判断是否有编辑权限
    // model.addAttribute("canEdit", true);
    return "view/system/appVersion/grid";
}


public void setAttachment(File attachment){
    this.attachment = attachment;
}


@RequestMapping
public void gridDataCustom(HttpServletResponse response,String filters,String columns,int page,int rows,Long appOsType){
    /*
        String json = "";
        Page pageModel = new Page(page, rows, true);
        appFiles.clear();

        String appPath = "";
        //ServletContext context = request.getSession(true).getServletContext();
        ServletContext context = getServletContext();
        if(null != context)
        {
            appPath = context.getRealPath("/")+"/gsapp/";
            switch(appOsType.intValue()) {
                case AppVersion.APP_OS_TYPE_ANDROID:
                    appPath += "android/";
                    break;
                case AppVersion.APP_OS_TYPE_IOS:
                    appPath += "ios/";
                    break;
                default:
                    return;
            }
        }

        System.out.println("gsapp="+appPath);
        File file = new File(appPath);
        try {
            if (!file.exists()) {
                pageModel.setTotal(0);
                pageModel.setRecords(0);
                pageModel.setPage(page);
                pageModel.setRows(appFiles);
            }
            else {
                File[] file1 = file.listFiles();
                int records = 0;
                pageModel.setTotal(file1.length);

                // page从1开始
                for (int i = (0+(page-1)*rows); i < file1.length; i++) {
                    //if(file1[i].getName().equals("xx.txt")) continue;
                    SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                    java.util.Date dt = new Date(file1[i].lastModified());
                    String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
                    AppVersion app = new AppVersion();
                    app.setId(Long.valueOf(i + 1));
                    app.setAppOsType(appOsType);

                    // 从APP版本文件中获取版本号
                    String filePath = "/gsapp/";
                    String fileName = file1[i].getName();
                    String appVersion = "";
                    String appPlistFilePath = "";
                    int begin = 0;
                    int end = 0;

                    switch(appOsType.intValue()) {
                        case AppVersion.APP_OS_TYPE_ANDROID:
                            begin = fileName.indexOf("smarthome-");
                            if (-1 != begin) {
                                begin += "smarthome-".length();
                            }
                            end = fileName.indexOf(".apk");
                            filePath += "android/";
                            if (-1 != begin && -1 != end) {
                                appVersion = fileName.substring(begin, end-1);
                            }
                            break;
                        case AppVersion.APP_OS_TYPE_IOS:
                            begin = fileName.indexOf("smarthome-");
                            if (-1 != begin) {
                                begin += "smarthome-".length();
                            }
                            end = fileName.indexOf(".ipa");
                            filePath += "ios/";
                            if (-1 != begin && -1 != end) {
                                appVersion = fileName.substring(begin, end-1);
                                appPlistFilePath = fileName.substring(0, end-1);
                                appPlistFilePath += ".plist";
                            }
                            break;
                        default:
                            return;
                    }
                    filePath += file1[i].getName();

                    app.setAppFileName(fileName);
                    app.setAppVersion(appVersion);
                    app.setAppFilePath(filePath);
                    app.setAppFileDate(sDateTime);
                    DecimalFormat df   =   new   DecimalFormat( ".## ");
                    app.setAppFileSize(df.format(file1[i].length()/1024000f));
                    app.setAppPlistFilePath(appPlistFilePath);

                    appFiles.add(app);

                    records ++;
                    if(records>=rows)
                    {
                        break;
                    }
                }

                pageModel.setRecords(file1.length);
                pageModel.setPage(page);
                pageModel.setRows(appFiles);
            }

            //输出显示
            json = GridJq.toJSON(columns, pageModel);
            System.out.println(json);
            sendJSON(response, json);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }   */
    try {
        Page<AppVersion> pageModel = new Page<AppVersion>(page, rows, true);
        String hql = "from AppVersion where appOsType=" + appOsType + " order by id desc";
        // 增加自定义查询条件
        // 执行查询
        QueryTranslateJq queryTranslate = new QueryTranslateJq(hql, filters);
        pageModel = appVersionService.findByPage(pageModel, queryTranslate.toString());
        // 输出显示
        String json = GridJq.toJSON(columns, pageModel);
        sendJSON(response, json);
    } catch (Exception e) {
        super.processException(response, e);
    }
}


}