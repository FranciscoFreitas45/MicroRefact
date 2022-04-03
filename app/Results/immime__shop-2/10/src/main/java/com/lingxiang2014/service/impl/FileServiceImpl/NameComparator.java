package com.lingxiang2014.service.impl.FileServiceImpl;
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
public class NameComparator implements Comparator<FileInfo>{


public int compare(FileInfo fileInfos1,FileInfo fileInfos2){
    return new CompareToBuilder().append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory()).append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
}


}