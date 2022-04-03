package com.lingxiang2014.service.impl;
 import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import com.lingxiang2014.FileInfo;
import com.lingxiang2014.Setting;
import com.lingxiang2014.FileInfo.FileType;
import com.lingxiang2014.FileInfo.OrderType;
import com.lingxiang2014.plugin.StoragePlugin;
import com.lingxiang2014.service.FileService;
import com.lingxiang2014.service.PluginService;
import com.lingxiang2014.util.FreemarkerUtils;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.DTO.Setting;
@Service("fileServiceImpl")
public class FileServiceImpl implements ServletContextAware,FileService{

 private  ServletContext servletContext;

@Resource(name = "taskExecutor")
 private  TaskExecutor taskExecutor;

@Resource(name = "pluginServiceImpl")
 private  PluginService pluginService;


public int compare(FileInfo fileInfos1,FileInfo fileInfos2){
    return new CompareToBuilder().append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory()).append(FilenameUtils.getExtension(fileInfos1.getName()), FilenameUtils.getExtension(fileInfos2.getName())).toComparison();
}


public String upload(FileType fileType,MultipartFile multipartFile){
    return upload(fileType, multipartFile, false);
}


public String uploadLocal(FileType fileType,MultipartFile multipartFile){
    if (multipartFile == null) {
        return null;
    }
    Setting setting = SettingUtils.get();
    String uploadPath;
    if (fileType == FileType.flash) {
        uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.media) {
        uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.file) {
        uploadPath = setting.getFileUploadPath();
    } else {
        uploadPath = setting.getImageUploadPath();
    }
    try {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("uuid", UUID.randomUUID().toString());
        String path = FreemarkerUtils.process(uploadPath, model);
        String destPath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        File destFile = new File(servletContext.getRealPath(destPath));
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        multipartFile.transferTo(destFile);
        return destPath;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


public void setServletContext(ServletContext servletContext){
    this.servletContext = servletContext;
}


public boolean isValid(FileType fileType,MultipartFile multipartFile){
    if (multipartFile == null) {
        return false;
    }
    Setting setting = SettingUtils.get();
    if (setting.getUploadMaxSize() != null && setting.getUploadMaxSize() != 0 && multipartFile.getSize() > setting.getUploadMaxSize() * 1024L * 1024L) {
        return false;
    }
    String[] uploadExtensions;
    if (fileType == FileType.flash) {
        uploadExtensions = setting.getUploadFlashExtensions();
    } else if (fileType == FileType.media) {
        uploadExtensions = setting.getUploadMediaExtensions();
    } else if (fileType == FileType.file) {
        uploadExtensions = setting.getUploadFileExtensions();
    } else {
        uploadExtensions = setting.getUploadImageExtensions();
    }
    if (ArrayUtils.isNotEmpty(uploadExtensions)) {
        return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
    }
    return false;
}


public List<FileInfo> browser(String path,FileType fileType,OrderType orderType){
    if (path != null) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!path.endsWith("/")) {
            path += "/";
        }
    } else {
        path = "/";
    }
    Setting setting = SettingUtils.get();
    String uploadPath;
    if (fileType == FileType.flash) {
        uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.media) {
        uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.file) {
        uploadPath = setting.getFileUploadPath();
    } else {
        uploadPath = setting.getImageUploadPath();
    }
    String browsePath = StringUtils.substringBefore(uploadPath, "${");
    browsePath = StringUtils.substringBeforeLast(browsePath, "/") + path;
    List<FileInfo> fileInfos = new ArrayList<FileInfo>();
    if (browsePath.indexOf("..") >= 0) {
        return fileInfos;
    }
    for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)) {
        fileInfos = storagePlugin.browser(browsePath);
        break;
    }
    if (orderType == OrderType.size) {
        Collections.sort(fileInfos, new SizeComparator());
    } else if (orderType == OrderType.type) {
        Collections.sort(fileInfos, new TypeComparator());
    } else {
        Collections.sort(fileInfos, new NameComparator());
    }
    return fileInfos;
}


public void run(){
    try {
        storagePlugin.upload(path, tempFile, contentType);
    } finally {
        FileUtils.deleteQuietly(tempFile);
    }
}


public void addTask(StoragePlugin storagePlugin,String path,File tempFile,String contentType){
    taskExecutor.execute(new Runnable() {

        public void run() {
            try {
                storagePlugin.upload(path, tempFile, contentType);
            } finally {
                FileUtils.deleteQuietly(tempFile);
            }
        }
    });
}


}