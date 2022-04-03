package com.easyshopping.service.impl.FileServiceImpl;
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
import com.easyshopping.FileInfo;
import com.easyshopping.FileInfo.FileType;
import com.easyshopping.FileInfo.OrderType;
import com.easyshopping.Setting;
import com.easyshopping.plugin.StoragePlugin;
import com.easyshopping.service.FileService;
import com.easyshopping.service.PluginService;
import com.easyshopping.util.FreemarkerUtils;
import com.easyshopping.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
public class NameComparator implements Comparator<FileInfo>{


public int compare(FileInfo fileInfos1,FileInfo fileInfos2){
    return new CompareToBuilder().append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory()).append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
}


}