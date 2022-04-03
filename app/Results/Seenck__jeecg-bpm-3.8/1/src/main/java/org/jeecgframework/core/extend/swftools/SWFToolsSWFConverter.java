package org.jeecgframework.core.extend.swftools;
 import java.io.File;
import java.io.IOException;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.PinyinUtil;
public class SWFToolsSWFConverter implements SWFConverter{

 private  String PDF2SWF_PATH;


public boolean isWindowsSystem(){
    String p = System.getProperty("os.name");
    return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
}


public void convert2SWF(String inputFile,String extend){
    String swfFile = PinyinUtil.getPinYinHeadChar(FileUtils.getFilePrefix2(inputFile)) + ".swf";
    convert2SWF(inputFile, swfFile, extend);
}


public void main(String[] args){
    // 转换器安装路径
    String exePath = "D:/SWFTools/pdf2swf.exe";
    new SWFToolsSWFConverter().convert2SWF("C:/Users/chenj/Desktop/jeecg/陈劲任务.pdf", exePath);
}


}