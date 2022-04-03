package com.ipe.module.core.web.controller;
 import com.ipe.common.dao.SpringJdbcDao;
import com.ipe.common.util.ExcelParse;
import com.ipe.module.core.entity.ExlImptpl;
import com.ipe.module.core.entity.Notice;
import com.ipe.module.core.service.ExlImptplService;
import com.ipe.module.core.web.controller.AbstractController;
import com.ipe.module.core.web.security.SystemRealm;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/exlImptpl")
public class ExlImptplController extends AbstractController{

 private  Logger LOG;

@Autowired
 private  ExlImptplService exlImptplService;

@Autowired
 private  SpringJdbcDao springJdbcDao;

@Value("#{app.temp_filepath}")
 private  String tempFilePath;


@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper add(ExlImptpl exlImptpl,String details){
    try {
        exlImptpl.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        exlImptplService.save(exlImptpl, details);
        return success(exlImptpl);
    } catch (Exception e) {
        LOG.error("add error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/impexcel" })
@ResponseBody
public BodyWrapper impexcel(Integer sheetIndex,MultipartHttpServletRequest multipartHttpServletRequest){
    try {
        MultipartFile multipartFile = multipartHttpServletRequest.getFileMap().get("file");
        String appendixPath = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {
            appendixPath = tempFilePath + "/" + multipartFile.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(appendixPath), multipartFile.getBytes());
            ExcelParse excelParse = new ExcelParse(appendixPath);
            Map<String, Object> data = new HashMap<>();
            ArrayList<Object[]> arrayList = excelParse.read(sheetIndex);
            // TODO max显示2000行
            data.put("data", arrayList);
            if (!arrayList.isEmpty()) {
                data.put("collength", arrayList.get(0).length);
            } else {
                data.put("collength", 0);
            }
            data.put("sheets", excelParse.sheet());
            return success(data);
        }
        return failure();
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
@ResponseBody
public BodyWrapper edit(ExlImptpl exlImptpl,String details){
    try {
        exlImptpl.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        exlImptplService.edit(exlImptpl, details);
        return success(exlImptpl);
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/del" })
@ResponseBody
public BodyWrapper del(String[] ids,RestRequest rest){
    try {
        exlImptplService.delete(ids);
        return success();
    } catch (Exception e) {
        LOG.error("del error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/impexcelData" })
@ResponseBody
public BodyWrapper impexcelData(String id,MultipartHttpServletRequest multipartHttpServletRequest){
    try {
        MultipartFile multipartFile = multipartHttpServletRequest.getFileMap().get("file");
        String appendixPath = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {
            appendixPath = tempFilePath + "/" + multipartFile.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(appendixPath), multipartFile.getBytes());
            String result = exlImptplService.impData(appendixPath, id);
            return success(result);
        }
        return failure();
    } catch (Exception e) {
        LOG.error("edit error", e);
        return failure(e);
    }
}


@RequestMapping(value = { "/list" })
@ResponseBody
public BodyWrapper list(ExlImptpl exlImptpl,RestRequest rest){
    try {
        int startRow = rest.getStart();
        int endRow = rest.getLimit();
        Page<ExlImptpl> page = exlImptplService.findAll(null, new PageRequest(startRow, endRow));
        for (ExlImptpl imptpl : page.getContent()) {
            if (StringUtils.isNotBlank(imptpl.getMappingTable())) {
                String sql = "select count(*) from " + imptpl.getMappingTable();
                try {
                    long count = springJdbcDao.queryLong(sql);
                    imptpl.setTableCot(String.valueOf(count));
                } catch (Exception e) {
                    imptpl.setTableCot("N/A");
                }
            } else {
                imptpl.setTableCot("UN");
            }
        }
        return success(page);
    } catch (Exception e) {
        LOG.error("query error", e);
        return failure(e);
    }
}


}