package com.fosun.fc.projects.creepers.downloader;
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.PlainText;
@Component("simulateDownloader")
public class SimulateDownloader extends AbstractDownloader{

 private  Logger logger;

 protected  CreepersParamDTO param;

@Autowired
 protected  ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;


@Override
public void setThread(int threadNum){
// TODO Auto-generated method stub
}


@Override
public Page download(Request request,Task task){
    // do nothing but read file as page
    logger.info("=========>>SimulateDownloader.download");
    File htmlFile = new File("D:\\simulate\\4.html");
    StringBuffer sb = new StringBuffer();
    BufferedReader reader = null;
    try {
        System.out.println("以行为单位读取文件内容，一次读一整行：");
        reader = new BufferedReader(new FileReader(htmlFile));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            sb.append("\n").append(tempString);
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Page page = new Page();
    page.setRawText(sb.toString());
    page.setUrl(new PlainText(request.getUrl()));
    page.setRequest(request);
    page.putField(BaseConstant.PARAM_DTO_KEY, param);
    page.setStatusCode(200);
    return page;
}


public CreepersParamDTO getParam(){
    return param;
}


public SimulateDownloader setParam(CreepersParamDTO param){
    this.param = param;
    return this;
}


}