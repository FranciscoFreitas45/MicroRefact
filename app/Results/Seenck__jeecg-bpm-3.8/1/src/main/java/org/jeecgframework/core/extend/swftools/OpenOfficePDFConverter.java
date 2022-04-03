package org.jeecgframework.core.extend.swftools;
 import java.io.File;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.jeecgframework.core.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class OpenOfficePDFConverter implements PDFConverter{

 private  Logger log;

 private  OfficeManager officeManager;

 private  String OFFICE_HOME;

 private  int[] port;


public void convert2PDF(String inputFile,String extend){
    String pdfFile = FileUtils.getFilePrefix2(inputFile) + ".pdf";
    convert2PDF(inputFile, pdfFile, extend);
}


public void startService(){
    DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
    try {
        // 准备启动服务
        // 设置OpenOffice.org安装目录
        configuration.setOfficeHome(OFFICE_HOME);
        // 设置转换端口，默认为8100
        configuration.setPortNumbers(port);
        // 设置任务执行超时为5分钟
        configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
        // 设置任务队列超时为24小时
        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
        officeManager = configuration.buildOfficeManager();
        // 启动服务
        officeManager.start();
        log.info("office转换服务启动成功!");
    } catch (Exception ce) {
        log.info("office转换服务启动失败!详细信息:" + ce);
    }
}


public void stopService(){
    log.info("关闭office转换服务....");
    if (officeManager != null) {
        officeManager.stop();
    }
    log.info("关闭office转换成功!");
}


}