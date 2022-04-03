package com.ipe.module.core.web.controller;
 import com.ipe.module.core.entity.Notice;
import com.ipe.module.core.service.NoticeService;
import com.ipe.module.core.web.security.SystemRealm;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.util.Date;
@Controller
@RequestMapping("/notice")
public class NoticeController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  NoticeService noticeService;

@Value("#{app.notice_filepath}")
 private  String noticeFilePath;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(Notice notice,MultipartHttpServletRequest multipartHttpServletRequest){
    try {
        MultipartFile multipartFile = multipartHttpServletRequest.getFileMap().get("file");
        String appendixPath = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {
            notice.setAppendixName(multipartFile.getOriginalFilename());
            appendixPath = noticeFilePath + "/" + multipartFile.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(appendixPath), multipartFile.getBytes());
        }
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        notice.setUserId(user.getUserId());
        notice.setCreatedDate(new Date());
        notice.setAppendixPath(appendixPath);
        noticeService.save(notice);
        return success(notice);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(Notice notice,MultipartHttpServletRequest multipartHttpServletRequest){
    try {
        MultipartFile multipartFile = multipartHttpServletRequest.getFileMap().get("file");
        String appendixPath = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {
            notice.setAppendixName(multipartFile.getOriginalFilename());
            appendixPath = noticeFilePath + "/" + multipartFile.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(appendixPath), multipartFile.getBytes());
        }
        SystemRealm.UserInfo user = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
        notice.setUserId(user.getUserId());
        notice.setAppendixPath(appendixPath);
        noticeService.save(notice);
        return success(notice);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        noticeService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(Notice notice,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<Notice> page = noticeService.findAll(null, new PageRequest(startRow, endRow));
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


}