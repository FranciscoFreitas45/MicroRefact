package org.jeecgframework.web.cgform.controller.upload;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.upload.CgUploadServiceI;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.service.SystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import Interface.CgUploadServiceI;
import DTO.CgUploadEntity;
import DTO.TSAttachment;
@Controller
@RequestMapping("/cgUploadController")
public class CgUploadController extends BaseController{

 private  Logger logger;

@Autowired
 private  SystemService systemService;

@Autowired
 private  CgUploadServiceI cgUploadService;


public String toHexString(int index){
    String hexString = Integer.toHexString(index);
    // 1???byte??????16?????????????????????2?????????????????????????????????????????????????????????????????????
    hexString = hexString.substring(hexString.length() - 2);
    return hexString;
}


public void write2Disk(MultipartFile mf,String extend,String savePath){
    File savefile = new File(savePath);
    if ("txt".equals(extend)) {
        // ??????utf-8??????????????????????????????????????????
        // Unicode:FF FE   UTF-8:EF BB
        byte[] allbytes = mf.getBytes();
        try {
            String head1 = toHexString(allbytes[0]);
            String head2 = toHexString(allbytes[1]);
            if ("ef".equals(head1) && "bb".equals(head2)) {
                // UTF-8
                String contents = new String(mf.getBytes(), "UTF-8");
                if (StringUtils.isNotBlank(contents)) {
                    OutputStream out = new FileOutputStream(savePath);
                    out.write(contents.getBytes());
                    out.close();
                }
            } else {
                // GBK
                String contents = new String(mf.getBytes(), "GBK");
                OutputStream out = new FileOutputStream(savePath);
                out.write(contents.getBytes());
                out.close();
            }
        } catch (Exception e) {
            String contents = new String(mf.getBytes(), "UTF-8");
            if (StringUtils.isNotBlank(contents)) {
                OutputStream out = new FileOutputStream(savePath);
                out.write(contents.getBytes());
                out.close();
            }
        }
    } else {
        FileCopyUtils.copy(mf.getBytes(), savefile);
    }
}


@RequestMapping(params = "delFile")
@ResponseBody
public AjaxJson delFile(HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    String id = request.getParameter("id");
    CgUploadEntity file = systemService.getEntity(CgUploadEntity.class, id);
    // update--begin--author:zhangjiaqiang date:20170608 for:???????????????????????????????????????????????????????????????????????????
    String sql = "select " + file.getCgformField() + " from " + file.getCgformName() + " where id = '" + file.getCgformId() + "'";
    List<Object> cgformFieldResult = systemService.findListbySql(sql);
    if (cgformFieldResult != null && !cgformFieldResult.isEmpty() && cgformFieldResult.get(0) != null) {
        String path = cgformFieldResult.get(0).toString();
        String realPath = file.getRealpath();
        realPath = realPath.replace(File.separator, "/");
        boolean updateFlag = false;
        if (path.equals(realPath)) {
            // ???????????????????????????????????????
            String hql = "from CgUploadEntity where cgformId = ?  and cgformField = ?  and cgformName = ?";
            List<CgUploadEntity> uploadList = systemService.findHql(hql, file.getCgformId(), file.getCgformField(), file.getCgformName());
            if (uploadList != null && !uploadList.isEmpty() && uploadList.size() > 1) {
                for (CgUploadEntity cgUploadEntity : uploadList) {
                    if (!file.getId().equals(cgUploadEntity.getId())) {
                        realPath = cgUploadEntity.getRealpath();
                        realPath = realPath.replace(File.separator, "/");
                        cgUploadService.writeBack(file.getCgformId(), file.getCgformName(), file.getCgformField(), file.getId(), realPath);
                        updateFlag = true;
                        break;
                    }
                }
            }
        }
        if (!updateFlag) {
            cgUploadService.writeBack(file.getCgformId(), file.getCgformName(), file.getCgformField(), file.getId(), "");
        }
    }
    // update--end--author:zhangjiaqiang date:20170608 for:???????????????????????????????????????????????????????????????????????????
    message = "" + file.getAttachmenttitle() + "???????????????";
    cgUploadService.deleteFile(file);
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    j.setSuccess(true);
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "ajaxSaveFile")
@ResponseBody
public AjaxJson ajaxSaveFile(MultipartHttpServletRequest request){
    AjaxJson ajaxJson = new AjaxJson();
    Map<String, Object> attributes = new HashMap<String, Object>();
    try {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
        // ???????????????????????????
        // ????????????????????????????????????
        String path = uploadbasepath + "/";
        // ???????????????????????????
        String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path;
        realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
        path += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
        File file = new File(realPath);
        if (!file.exists()) {
            // ???????????????????????????
            file.mkdirs();
        }
        if (fileMap != null && !fileMap.isEmpty()) {
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // ????????????????????????
                MultipartFile mf = entity.getValue();
                // ???????????????
                String fileName = mf.getOriginalFilename();
                // ???????????????????????????SWF?????????
                String swfName = PinyinUtil.getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));
                // ?????????????????????
                String extend = FileUtils.getExtend(fileName);
                // ?????????????????????
                String noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);
                // ?????????????????????
                String myfilename = noextfilename + "." + extend;
                // ?????????????????????
                String savePath = realPath + myfilename;
                write2Disk(mf, extend, savePath);
                TSAttachment attachment = new TSAttachment();
                attachment.setId(UUID.randomUUID().toString().replace("-", ""));
                attachment.setAttachmenttitle(fileName);
                attachment.setCreatedate(new Timestamp(new Date().getTime()));
                attachment.setExtend(extend);
                attachment.setRealpath(path + myfilename);
                // update--begin--author:zhangjiaqiang date:@0170703 for:????????????swf??????
                attachment.setSwfpath(path + FileUtils.getFilePrefix(myfilename) + ".swf");
                SwfToolsUtil.convert2SWF(savePath);
                // update--end--author:zhangjiaqiang date:@0170703 for:????????????swf??????
                systemService.save(attachment);
                attributes.put("url", path + myfilename);
                attributes.put("name", fileName);
                attributes.put("swfpath", attachment.getSwfpath());
            }
        }
        ajaxJson.setAttributes(attributes);
    } catch (Exception e) {
        e.printStackTrace();
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(e.getMessage());
    }
    return ajaxJson;
}


@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
@ResponseBody
public AjaxJson saveFiles(HttpServletRequest request,HttpServletResponse response,CgUploadEntity cgUploadEntity){
    AjaxJson j = new AjaxJson();
    Map<String, Object> attributes = new HashMap<String, Object>();
    // ??????ID
    String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
    // ???????????????ID
    String id = oConvertUtils.getString(request.getParameter("cgFormId"));
    // ????????????
    String tableName = oConvertUtils.getString(request.getParameter("cgFormName"));
    // ???????????????????????????
    String cgField = oConvertUtils.getString(request.getParameter("cgFormField"));
    logger.info("--cgUploadController--saveFiles--????????????-----" + "{id:" + id + "}  {tableName???" + tableName + "}  {cgField:" + cgField + "}");
    if (!StringUtil.isEmpty(id)) {
        cgUploadEntity.setCgformId(id);
        cgUploadEntity.setCgformName(tableName);
        cgUploadEntity.setCgformField(cgField);
    }
    if (StringUtil.isNotEmpty(fileKey)) {
        cgUploadEntity.setId(fileKey);
        cgUploadEntity = systemService.getEntity(CgUploadEntity.class, fileKey);
    }
    UploadFile uploadFile = new UploadFile(request, cgUploadEntity);
    uploadFile.setCusPath("files");
    uploadFile.setSwfpath("swfpath");
    // ?????????????????????
    uploadFile.setByteField(null);
    cgUploadEntity = systemService.uploadFile(uploadFile);
    logger.info("--cgUploadController--saveFiles--????????????----???????????????????????????-----");
    // update--begin--author:zhangjiaqiang date:20170531 for:????????????????????????????????????
    String realPath = cgUploadEntity.getRealpath();
    realPath = realPath.replace(File.separator, "/");
    cgUploadService.writeBack(id, tableName, cgField, fileKey, realPath);
    logger.info("--cgUploadController--saveFiles--????????????----???????????????????????????????????????-----");
    // update--end--author:zhangjiaqiang date:20170531 for:????????????????????????????????????
    attributes.put("url", realPath);
    attributes.put("name", cgUploadEntity.getAttachmenttitle());
    attributes.put("fileKey", cgUploadEntity.getId());
    attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + cgUploadEntity.getId());
    attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + cgUploadEntity.getId());
    j.setMsg("????????????");
    j.setAttributes(attributes);
    logger.info("--cgUploadController--saveFiles--????????????----????????????-----");
    return j;
}


}