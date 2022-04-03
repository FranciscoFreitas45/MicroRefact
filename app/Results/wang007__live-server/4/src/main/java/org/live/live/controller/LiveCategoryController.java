package org.live.live.controller;
 import org.live.common.response.ResponseModel;
import org.live.common.response.SimpleResponseModel;
import org.live.common.support.UploadFilePathConfig;
import org.live.common.utils.CopyPropertiesUtils;
import org.live.common.utils.CreateOrderNoUtils;
import org.live.common.utils.UploadUtils;
import org.live.live.entity.LiveCategory;
import org.live.live.service.LiveCategoryService;
import org.live.live.vo.LiveCategoryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import org.live.Interface.UploadFilePathConfig;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
@Controller
@RequestMapping("/live")
public class LiveCategoryController {

 private  Logger LOGGER;

@Resource
 private  LiveCategoryService categoryService;

@Resource
 private  UploadFilePathConfig pathConfig;


@RequestMapping(value = "/category/cover/{id}", method = RequestMethod.PUT)
@ResponseBody
public ResponseModel<Object> updateCategoryCover(MultipartFile file,String id){
    ResponseModel model = new SimpleResponseModel<Object>();
    if (file == null) {
        model.setMessage("上传失败");
        model.error();
        return model;
    }
    try {
        LiveCategory liveCategory = categoryService.findOne(id);
        String coverUrl = liveCategory.getCoverUrl();
        File oldFile = new File(pathConfig.getUploadFileRootPath(), coverUrl);
        // 删除之前的图片
        if (oldFile.exists())
            oldFile.delete();
        // 文件后缀
        String fileSuffix = UploadUtils.getFileSuffix(file.getOriginalFilename());
        // 路径： 相对于项目的 /projectDir/upload/系统日期/系统时间+6位随机数.xxx
        String dateStr = CreateOrderNoUtils.getDate();
        String fileName = CreateOrderNoUtils.getCreateOrderNo() + fileSuffix;
        String targetPathSuffix = dateStr + File.separator + fileName;
        File targetFile = UploadUtils.createFile(pathConfig.getUploadFilePath(), targetPathSuffix);
        file.transferTo(targetFile);
        liveCategory.setCoverUrl(pathConfig.getUploadFilePathPrefix() + "/" + dateStr + "/" + fileName);
        categoryService.save(liveCategory);
        model.setMessage("上传成功");
        model.success();
        return model;
    } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        model.setMessage("上传失败");
        model.error();
        return model;
    }
}


@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
@ResponseBody
public ResponseModel<Object> getLiveCategory(String id){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        LiveCategory category = categoryService.findOne(id);
        model.setData(category);
        model.success();
    } catch (Exception e) {
        LOGGER.error("查询单条分类信息失败！", e);
        model.error();
    }
    return model;
}


@RequestMapping(value = "/category", method = RequestMethod.PUT)
@ResponseBody
public ResponseModel<Object> uploadLiveCategory(LiveCategoryVo liveCategoryVo,Integer enabledNum){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        if (enabledNum == null || enabledNum == 0)
            liveCategoryVo.setEnabled(false);
        LiveCategory liveCategory = categoryService.findOne(liveCategoryVo.getId());
        CopyPropertiesUtils.copyPropertiesIgnoreNull(liveCategory, liveCategoryVo);
        categoryService.save(liveCategory);
        model.setMessage("保存成功！");
        model.success();
    } catch (Exception e) {
        model.setMessage("保存失败！");
        model.error();
    }
    return model;
}


@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
@ResponseBody
public ResponseModel<Object> deleteLiveCategory(String id){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        boolean successFlag = categoryService.deleteLiveCategoryById(id);
        if (successFlag) {
            model.setMessage("删除成功！");
            model.success();
        } else {
            model.setMessage("删除失败，该直播分类下存在直播间！");
            model.error();
        }
    } catch (Exception e) {
        model.error();
        model.setMessage("删除失败！");
    }
    return model;
}


@RequestMapping(value = "/category/serialNo", method = RequestMethod.GET)
@ResponseBody
public ResponseModel<Object> findMaxSerialNo(){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        Integer serialNo = categoryService.findMaxSerialNo();
        if (serialNo == null)
            serialNo = 0;
        model.setData(serialNo + 1);
        model.success();
    } catch (Exception e) {
        LOGGER.error("查询直播分类的最大serialNo出现异常", e);
        model.error();
    }
    return model;
}


@RequestMapping(value = "/category", method = RequestMethod.GET)
public String toRoomCategory(HttpServletRequest request){
    try {
        List<LiveCategory> liveCategorys = categoryService.findAllCategory();
        request.setAttribute("liveCategorys", liveCategorys);
    } catch (Exception e) {
        LOGGER.error("查询直播房间分类时发生异常", e);
    }
    return "live/category";
}


@RequestMapping(value = "/category", method = RequestMethod.POST)
@ResponseBody
public ResponseModel<Object> addLiveCategory(MultipartFile file,LiveCategory liveCategory,Integer enabledNum){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        if (file == null) {
            model.setMessage("上传失败");
            model.error();
            return model;
        }
        // 启用状态
        if (enabledNum == null || enabledNum == 0)
            liveCategory.setEnabled(false);
        // 文件后缀
        String fileSuffix = UploadUtils.getFileSuffix(file.getOriginalFilename());
        // 路径： 相对于项目的 /projectDir/upload/系统日期/系统时间+6位随机数.xxx
        String dateStr = CreateOrderNoUtils.getDate();
        String fileName = CreateOrderNoUtils.getCreateOrderNo() + fileSuffix;
        String targetPathSuffix = dateStr + File.separator + fileName;
        File targetFile = UploadUtils.createFile(pathConfig.getUploadFilePath(), targetPathSuffix);
        file.transferTo(targetFile);
        liveCategory.setCoverUrl(pathConfig.getUploadFilePathPrefix() + "/" + dateStr + "/" + fileName);
        categoryService.save(liveCategory);
        model.setMessage("上传成功");
        model.success();
        return model;
    } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        model.setMessage("上传失败");
        model.error();
        return model;
    }
}


}