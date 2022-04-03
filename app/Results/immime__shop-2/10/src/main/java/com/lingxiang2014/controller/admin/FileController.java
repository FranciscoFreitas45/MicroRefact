package com.lingxiang2014.controller.admin;
 import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.lingxiang2014.FileInfo;
import com.lingxiang2014.Message;
import com.lingxiang2014.FileInfo.FileType;
import com.lingxiang2014.FileInfo.OrderType;
import com.lingxiang2014.service.FileService;
import com.lingxiang2014.util.JsonUtils;
@Controller("adminFileController")
@RequestMapping("/admin/file")
public class FileController extends BaseController{

@Resource(name = "fileServiceImpl")
 private  FileService fileService;


@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
public void upload(FileType fileType,MultipartFile file,HttpServletResponse response){
    Map<String, Object> data = new HashMap<String, Object>();
    if (!fileService.isValid(fileType, file)) {
        data.put("message", Message.warn("admin.upload.invalid"));
    } else {
        String url = fileService.upload(fileType, file, false);
        if (url == null) {
            data.put("message", Message.warn("admin.upload.error"));
        } else {
            data.put("message", SUCCESS_MESSAGE);
            data.put("url", url);
        }
    }
    try {
        response.setContentType("text/html; charset=UTF-8");
        JsonUtils.writeValue(response.getWriter(), data);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@RequestMapping(value = "/browser", method = RequestMethod.GET)
@ResponseBody
public List<FileInfo> browser(String path,FileType fileType,OrderType orderType){
    return fileService.browser(path, fileType, orderType);
}


}