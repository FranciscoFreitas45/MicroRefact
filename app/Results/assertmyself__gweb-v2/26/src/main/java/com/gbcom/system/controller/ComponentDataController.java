package com.gbcom.system.controller;
 import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.system.utils.FileWebUtil;
import com.hc.core.controller.BaseCRUDActionController;
@Controller
public class ComponentDataController extends BaseCRUDActionController{

 private  Logger LOG;


@RequestMapping
public void downFile(HttpServletRequest request,HttpServletResponse response,String path){
    try {
        path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
        if (path.startsWith("\\")) {
            path = getServletContext().getRealPath("/") + path;
            path = path.replaceAll("\\\\", "/");
        }
        LOG.info("down file path is ：" + path);
        File file = new File(path);
        if (file.exists()) {
            FileWebUtil.downloadSection(request, response, path, file.getName(), false);
        } else {
            LOG.info("文件不存在");
        }
    } catch (Exception e) {
        log.error("down file error!!!", e);
    }
}


}