package com.dtdhehe.ptulife.controller;
 import com.alibaba.fastjson.JSONObject;
import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service.UserService;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/upload/uploadController")
public class UploadController {

 private  Logger logger;

@Autowired
 private  UserService userService;

@Resource
 private  RedisUtils redisUtils;


@RequestMapping("/uploadHeadImg")
public String uploadHeadImg(MultipartFile file,String userId){
    File f = new File("D:/ptu/uploads");
    Map<String, Object> resultMap = new HashMap<>();
    if (!f.exists()) {
        f.mkdirs();
    }
    try {
        String fileName = file.getOriginalFilename();
        logger.info("原文件名:" + fileName);
        String fileNewName = DateUtils.getCurrentDateTime() + fileName;
        logger.info("新文件名:" + fileNewName);
        // 新文件路径
        String resultUrl = "D:/ptu/uploads/" + fileNewName;
        File upFile = new File(resultUrl);
        file.transferTo(upFile);
        Map<String, Object> data = new HashMap<>();
        // 因为浏览器原因，设置虚拟路径为   /uploads/
        data.put("src", "/uploads/" + fileNewName);
        logger.info("图片路径为:" + "/uploads/" + fileNewName);
        // 将文件路径保存到user对象中
        PtuUser ptuUser = userService.findByUserId(userId);
        ptuUser.setHeadImg("/uploads/" + fileNewName);
        redisUtils.del(ptuUser.getUserId());
        PtuUser updateUser = userService.update(ptuUser);
        if (updateUser == null) {
            resultMap.put("code", 1);
            resultMap.put("msg", "上传失败");
            resultMap.put("data", null);
        } else {
            // 成功上传
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            resultMap.put("data", data);
        }
    } catch (Exception e) {
        resultMap.put("code", 1);
        resultMap.put("msg", "上传失败");
        resultMap.put("data", null);
        logger.error(e.getMessage());
    }
    String resultJson = new JSONObject(resultMap).toString();
    return resultJson;
}


@RequestMapping("/uploadGoodsImg")
public String uploadGoodsImg(MultipartFile file){
    File f = new File("D:/ptu/uploads");
    Map<String, Object> resultMap = new HashMap<>();
    if (!f.exists()) {
        f.mkdirs();
    }
    try {
        String fileName = file.getOriginalFilename();
        logger.info("原文件名:" + fileName);
        String fileNewName = DateUtils.getCurrentDateTime() + fileName;
        logger.info("新文件名:" + fileNewName);
        // 新文件路径
        String resultUrl = "D:/ptu/uploads/" + fileNewName;
        File upFile = new File(resultUrl);
        file.transferTo(upFile);
        Map<String, Object> data = new HashMap<>();
        // 因为浏览器原因，设置虚拟路径为   /uploads/
        data.put("src", "/uploads/" + fileNewName);
        logger.info("图片路径为:" + "/uploads/" + fileNewName);
        // 成功上传
        resultMap.put("code", 0);
        resultMap.put("msg", "上传成功");
        resultMap.put("data", data);
    } catch (Exception e) {
        resultMap.put("code", 1);
        resultMap.put("msg", "上传失败");
        resultMap.put("data", null);
        logger.error(e.getMessage());
    }
    String resultJson = new JSONObject(resultMap).toString();
    return resultJson;
}


@RequestMapping("/uploadEditPic")
public String uploadEditPic(MultipartFile file){
    File f = new File("D:/ptu/uploads");
    // 返回给富文本编辑器的json
    Map<String, Object> resultMap = new HashMap<>();
    if (!f.exists()) {
        f.mkdirs();
    }
    try {
        String fileName = file.getOriginalFilename();
        logger.info("原文件名:" + fileName);
        String fileNewName = DateUtils.getCurrentDateTime() + fileName;
        logger.info("新文件名:" + fileNewName);
        // 新文件路径
        String resultUrl = "D:/ptu/uploads/" + fileNewName;
        File upFile = new File(resultUrl);
        file.transferTo(upFile);
        Map<String, Object> data = new HashMap<>();
        // 因为浏览器原因，设置虚拟路径为   /uploads/
        data.put("src", "/uploads/" + fileNewName);
        data.put("title", fileName);
        // 成功上传
        resultMap.put("code", 0);
        resultMap.put("msg", "上传成功");
        resultMap.put("data", data);
    } catch (Exception e) {
        resultMap.put("code", 1);
        resultMap.put("msg", "上传失败");
        resultMap.put("data", null);
        e.printStackTrace();
    }
    String resultJson = new JSONObject(resultMap).toString();
    return resultJson;
}


}