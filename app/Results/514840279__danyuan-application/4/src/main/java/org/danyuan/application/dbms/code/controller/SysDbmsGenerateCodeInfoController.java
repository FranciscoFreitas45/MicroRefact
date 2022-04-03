package org.danyuan.application.dbms.code.controller;
 import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.common.base.ResultUtil;
import org.danyuan.application.dbms.code.po.SysDbmsGenerateCodeInfo;
import org.danyuan.application.dbms.code.service.SysDbmsGenerateCodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysDbmsGenerateCodeInfo")
public class SysDbmsGenerateCodeInfoController extends BaseControllerImpl<SysDbmsGenerateCodeInfo>implements BaseController<SysDbmsGenerateCodeInfo>{

 private  Logger logger;

@Autowired
 private SysDbmsGenerateCodeInfoService sysDbmsGenerateCodeInfoService;

 public  String OUTPUTFILE;


@RequestMapping(value = "/downloadCode/{path}", method = RequestMethod.GET)
public void downloadCode(HttpServletResponse response,String path) throws IOException{
    // 根据参数进行导出xml 并打包返回zip文件路径
    File file = new File(System.getProperty("user.dir") + "/" + OUTPUTFILE + "/" + path + ".zip");
    response.setHeader("content-type", "application/octet-stream");
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
    byte[] buff = new byte[1024];
    BufferedInputStream bis = null;
    ServletOutputStream os = null;
    try {
        os = response.getOutputStream();
        bis = new BufferedInputStream(new FileInputStream(file));
        int i = bis.read(buff);
        while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    file.delete();
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsGenerateCodeInfoController.class);
    ModelAndView modelAndView = new ModelAndView("dbms/code/sysdbmsgeneratecodeinfodetail");
    SysDbmsGenerateCodeInfo info = new SysDbmsGenerateCodeInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsGenerateCodeInfo", sysDbmsGenerateCodeInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping("/generate")
public BaseResult<String> generate(Pagination<SysDbmsGenerateCodeInfo> vo){
    try {
        String pathString = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        pathString = simpleDateFormat.format(new Date());
        sysDbmsGenerateCodeInfoService.generate(vo.getList(), vo.getUsername(), pathString);
        return ResultUtil.success(pathString);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


}