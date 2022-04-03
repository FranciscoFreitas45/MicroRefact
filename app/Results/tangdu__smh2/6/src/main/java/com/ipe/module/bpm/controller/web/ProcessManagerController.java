package com.ipe.module.bpm.controller.web;
 import com.ipe.module.bpm.service.ProcessManagerService;
import com.ipe.module.core.web.controller.AbstractController;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("bpm")
public class ProcessManagerController extends AbstractController{

 private  Logger LOGGER;

@Autowired
 private  ProcessManagerService processManagerService;

@Value("#{app.temp_filepath}")
 private  String tempFilePath;


@RequestMapping("/process_ins_list")
@ResponseBody
public BodyWrapper processInsList(RestRequest restRequest,String params){
    return processManagerService.proInstaceList(params, restRequest);
}


@RequestMapping(value = "/del_def_all")
@ResponseBody
public BodyWrapper removeAll(String deployId){
    try {
        processManagerService.delDefAll(deployId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure("删除失败");
    }
}


@RequestMapping("/process_def_his_list")
@ResponseBody
public BodyWrapper processDefHisList(RestRequest restRequest,String params){
    return processManagerService.proDefHisList(params, restRequest);
}


@RequestMapping("/deploy")
public void deployProcess(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletResponse response){
    try {
        MultipartFile multipartFile = multipartHttpServletRequest.getMultiFileMap().getFirst("file");
        File file = new File(tempFilePath + multipartFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        IOUtils.closeQuietly(multipartFile.getInputStream());
        processManagerService.deploy(file.getPath());
        super.renderSuccess(response);
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        super.renderFailure("部署失败", response);
    }
}


@RequestMapping("/my_start_process_ins_list")
@ResponseBody
public BodyWrapper myStartProcessInsList(RestRequest restRequest,String params){
    return processManagerService.myStartTask(params, restRequest);
}


@RequestMapping("/teststartProcess")
@ResponseBody
public BodyWrapper teststartProcess(String key){
    try {
        processManagerService.testStartProcess(key);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/my_partake_process_ins_list")
@ResponseBody
public BodyWrapper myPartakeProcessInsList(RestRequest restRequest,String params){
    return processManagerService.myPartakeTask(params, restRequest);
}


@RequestMapping("/viewProcessInfo")
@ResponseBody
public BodyWrapper viewProcessInfo(String processInstanceId){
    try {
        List<Map<String, Object>> list = processManagerService.viewProcessInfo(processInstanceId);
        return success(list);
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/process_def_list")
@ResponseBody
public BodyWrapper processDefList(RestRequest restRequestt,String params){
    return processManagerService.proDefList(params, restRequestt);
}


@RequestMapping("/viewDnyDiagram")
public void viewDiagram(String executionId,HttpServletResponse response){
    response.setContentType("image/png");
    try {
        InputStream inputStream = processManagerService.viewDnyDiagram(executionId);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        byte[] data = new byte[4096];
        int size = 0;
        size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }
        inputStream.close();
        bis.close();
        bos.close();
        outputStream.close();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        super.renderFailure("操作失败", response);
    }
}


}