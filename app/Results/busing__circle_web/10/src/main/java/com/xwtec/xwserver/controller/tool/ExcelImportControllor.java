package com.xwtec.xwserver.controller.tool;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.service.tool.IExcelImportService;
import com.xwtec.xwserver.util.ExcelUtil;
import com.xwtec.xwserver.util.FileUtil;
import com.xwtec.xwserver.util.ProUtil;
import com.xwtec.xwserver.util.json.JSONObject;
import com.DTO.JSONObject;
@Controller
@RequestMapping("excelimport")
public class ExcelImportControllor {

 private  Logger logger;

@Resource
 private IExcelImportService excelImportService;

@Resource
 private Map<String,Object> schemaMap;


@RequestMapping("addToDB.action")
@ResponseBody
public JSONObject addToDB(String name,String realName,String schema){
    // 返回数据
    JSONObject jsonReturn = new JSONObject();
    // 返回成功数量
    int num = 0;
    try {
        // 获取路径
        StringBuilder path = new StringBuilder(ProUtil.get("uploadFileBasePath"));
        // 拼接路径
        path.append(ConstantBusiKeys.Tool.EXCEL_IMPORT_PATH + name);
        Workbook wb = ExcelUtil.getWorkbook(path.toString());
        List<String[]> list = ExcelUtil.getAllData(wb, 0);
        int indexNum = realName.lastIndexOf('.');
        if (indexNum == -1) {
            indexNum = realName.length();
        }
        String tableName = realName.substring(0, indexNum);
        num = excelImportService.addToDB(list, tableName, schema);
        jsonReturn.put("flag", true);
    } catch (Exception e) {
        logger.error("[ExcelImportControllor.addToDB]:failed. throw ex:" + e.getMessage());
        jsonReturn.put("flag", false);
        jsonReturn.put("desc", e.getMessage());
    }
    jsonReturn.put("num", num);
    return jsonReturn;
}


@RequestMapping("init.action")
public ModelAndView init(Page page,ModelAndView modelAndView){
    // 把数据源传给前台
    modelAndView.setViewName("/tool/excel_import.jsp");
    // 数据源
    modelAndView.addObject("queryDataSource", schemaMap);
    return modelAndView;
}


@RequestMapping("upload.action")
@ResponseBody
public String upload(MultipartFile file){
    // 返回前台的文件名
    String returnValue = "";
    try {
        // 上传文件并把数据返回前台
        returnValue = FileUtil.upload(file, ConstantBusiKeys.Tool.EXCEL_IMPORT_PATH);
    } catch (Exception e) {
        logger.error("[FileUtil.upload]:failed. throw ex:" + e.getMessage());
    }
    return returnValue;
}


}