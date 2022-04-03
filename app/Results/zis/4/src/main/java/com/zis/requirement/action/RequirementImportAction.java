package com.zis.requirement.action;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.biz.SchoolBiz;
import com.zis.requirement.dto.RequirementImportDTO;
import com.zis.Interface.BookService;
public class RequirementImportAction extends ActionSupport{

 private  long serialVersionUID;

 private  File excelFile;

 private  String excelFileFileName;

 private  BookAmountService bookAmountService;

 private  SchoolBiz schoolBiz;

 private  BookService bookService;

 private  List<RequirementImportDTO> cannotImport;

 private  Logger logger;


public File getExcelFile(){
    return excelFile;
}


public void setExcelFile(File excelFile){
    this.excelFile = excelFile;
}


public String getExcelFileFileName(){
    return excelFileFileName;
}


public void saveImportRecord(List<RequirementImportDTO> list){
    for (RequirementImportDTO record : list) {
        if (StringUtils.isBlank(record.getIsbn())) {
            record.setFailReason("ISBN为空");
            cannotImport.add(record);
            continue;
        }
        if (record.getCount() == null || record.getCount() == 0) {
            record.setFailReason("数量为空");
            cannotImport.add(record);
            continue;
        }
        List<Bookinfo> bookList = bookService.findBookByISBN(record.getIsbn());
        // 如果条形码没有查到记录或者查到了多种图书，加入到失败列表中，留给人工处理
        if (bookList.isEmpty()) {
            record.setFailReason("条码错误");
            cannotImport.add(record);
            continue;
        }
        if (bookList.size() > 1) {
            record.setFailReason("一码多书");
            cannotImport.add(record);
            continue;
        }
        Bookinfo bi = bookList.get(0);
        BookAmount ba = new BookAmount();
        ba.setBookId(bi.getId());
        ba.setIsbn(bi.getIsbn());
        ba.setBookName(bi.getBookName());
        ba.setBookAuthor(bi.getBookAuthor());
        ba.setBookPublisher(bi.getBookPublisher());
        DetachedCriteria dc = DetachedCriteria.forClass(Departmentinfo.class);
        dc.add(Restrictions.eq("college", record.getCollege().trim()));
        dc.add(Restrictions.eq("institute", record.getInstitute().trim()));
        dc.add(Restrictions.eq("partName", record.getPartName().trim()));
        // 如果没有查到院校信息或者查到了多条记录，加入到失败列表中，留给人工处理
        List<Departmentinfo> departList = schoolBiz.findByCollegeInstituteAndPartName(record.getCollege().trim(), record.getInstitute().trim(), record.getPartName().trim());
        if (departList.size() != 1) {
            record.setFailReason("专业错误");
            cannotImport.add(record);
            continue;
        }
        Departmentinfo di = departList.get(0);
        ba.setPartId(di.getDid());
        ba.setCollege(di.getCollege());
        ba.setInstitute(di.getInstitute());
        ba.setPartName(di.getPartName());
        ba.setGrade(record.getGrade());
        ba.setTerm(record.getTerm());
        ba.setOperator("批量导入");
        ba.setAmount(record.getCount());
        ba.setGmtCreate(ZisUtils.getTS());
        ba.setGmtModify(ZisUtils.getTS());
        try {
            bookAmountService.saveBookAmount(ba);
            logger.info("成功导入教材需求量：" + ba);
        } catch (Exception e) {
            if (e.getMessage().contains("已经登记过")) {
                // 跳过重复录入的报错
                continue;
            }
            record.setFailReason("系统错误：" + e.getMessage());
            cannotImport.add(record);
            logger.error("导入教材需求量失败：" + e.getMessage(), e);
        }
    }
}


public void setExcelFileFileName(String excelFileFileName){
    this.excelFileFileName = excelFileFileName;
}


@Validations(/* �������� */
requiredFields = { @RequiredFieldValidator(fieldName = "excelFile", key = "文件必须输入") })
public String upload(){
    try {
        // 初始化导入器
        FileImporter<RequirementImportDTO> im = initExcelImporter();
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            this.addActionError(errMsg);
            return INPUT;
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = getPropMapping();
        RequirementImportDTO billInstance = new RequirementImportDTO();
        List<RequirementImportDTO> list = im.parse(billInstance, propMapping);
        saveImportRecord(list);
        // 导入失败的数据处理
        if (!cannotImport.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (RequirementImportDTO failRecord : cannotImport) {
                builder.append(getFaildRecordMessage(failRecord));
            }
            this.addActionError("导入失败的记录有：" + builder.toString());
            return INPUT;
        }
        return SUCCESS;
    } catch (Exception e) {
        this.addActionError("导入教材需求失败: " + e.getMessage());
        logger.error(e.getMessage(), e);
        return INPUT;
    }
}


public void setBookAmountService(BookAmountService bookAmountService){
    this.bookAmountService = bookAmountService;
}


public Map<String,Integer> getPropMapping(){
    Map<String, Integer> propMapping = new HashMap<String, Integer>();
    propMapping.put("college", 0);
    propMapping.put("institute", 1);
    propMapping.put("partName", 2);
    propMapping.put("count", 3);
    propMapping.put("grade", 4);
    propMapping.put("term", 5);
    propMapping.put("isbn", 6);
    propMapping.put("bookName", 7);
    return propMapping;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public StringBuilder getFaildRecordMessage(RequirementImportDTO failRecord){
    StringBuilder builder = new StringBuilder();
    builder.append(failRecord.getCollege());
    builder.append(",");
    builder.append(failRecord.getInstitute());
    builder.append(",");
    builder.append(failRecord.getPartName());
    builder.append(",");
    builder.append(failRecord.getCount());
    builder.append(",");
    builder.append(failRecord.getGrade());
    builder.append(",");
    builder.append(failRecord.getTerm());
    builder.append(",");
    builder.append(failRecord.getIsbn());
    builder.append(",");
    builder.append(failRecord.getBookName());
    builder.append(",");
    builder.append(failRecord.getFailReason());
    builder.append(";");
    return builder;
}


public FileImporter<RequirementImportDTO> initExcelImporter(){
    FileImporter<RequirementImportDTO> im = new ExcelImporter<RequirementImportDTO>(new FileInputStream(excelFile), "bookRequirement.xls");
    im.setHeaderRowNums(1);
    return im;
}


public void setSchoolBiz(SchoolBiz schoolBiz){
    this.schoolBiz = schoolBiz;
}


}