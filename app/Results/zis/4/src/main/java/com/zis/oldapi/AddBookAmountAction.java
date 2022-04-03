package com.zis.oldapi;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.zis.api.response.BaseApiResponse;
import com.zis.bookinfo.service.BookService;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.biz.SchoolBiz;
import com.zis.requirement.dto.BookAmountAddApiRequestDTO;
import com.zis.Interface.BookService;
public class AddBookAmountAction extends BaseApiAction{

 private  long serialVersionUID;

 private  BookAmountService addAmountBiz;

 private  BookService bookService;

 private  SchoolBiz schoolBiz;

 private  String departId;

 private  String amount;

 private  String operator;

 private  String bookIds;

 private  String grade;

 private  String term;

 private  Logger logger;

 private  Map<String,Object> map;


public String getTerm(){
    return term;
}


public String validateParam(){
    // 年级
    Integer grade1 = parseInt(grade);
    if (grade1 == null) {
        return "grade必须为数字";
    }
    if (grade1 < 1 || grade1 > 5) {
        return "grade在1到5之间";
    }
    map.put("grade", grade1);
    // 学期
    Integer term1 = parseInt(term);
    if (term1 == null) {
        return "term必须为数字";
    }
    if (term1 < 1 || term1 > 2) {
        return "term在1到2之间";
    }
    map.put("term", term1);
    // 检查院校id
    Integer departid = parseInt(departId);
    if (departid == null) {
        return "departId必须为数字";
    }
    map.put("departId", departid);
    Departmentinfo di = schoolBiz.findDepartmentInfoById(departid);
    if (di == null) {
        return "院校不存在, id=" + departid;
    }
    if (grade1 > di.getYears()) {
        return "学年错误，该专业是" + di.getYears() + "年制";
    }
    // 检查书的id
    if (StringUtils.isBlank(bookIds)) {
        return "bookIds不能为空";
    }
    List<Integer> bookIdList = new ArrayList<Integer>();
    for (String idStr : bookIds.split(",")) {
        Integer id = parseInt(idStr);
        if (id == null || bookService.findBookById(id) == null) {
            return "图书不存在，id=" + id;
        }
        bookIdList.add(id);
    }
    map.put("bookIdList", bookIdList);
    // 检查录入人
    if (StringUtils.isBlank(operator)) {
        return "operator不能为空";
    }
    // 检查数量
    if (StringUtils.isBlank(amount)) {
        return "amount不能为空";
    }
    Integer amount1 = parseInt(amount);
    if (amount1 == null) {
        return "书的数量必须为数字";
    }
    map.put("amount", amount1);
    // 返回空，代表没有错误
    return "";
}


public void setAddAmountBiz(BookAmountService addAmountBiz){
    this.addAmountBiz = addAmountBiz;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public void renderSuccessResult(){
    BaseApiResponse response = new BaseApiResponse();
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    renderResult(response);
}


public void setSchoolBiz(SchoolBiz schoolBiz){
    this.schoolBiz = schoolBiz;
}


public Integer parseInt(String val){
    if (StringUtils.isBlank(val)) {
        return null;
    }
    try {
        return Integer.parseInt(val.trim());
    } catch (NumberFormatException e) {
        return null;
    }
}


public void setBookIds(String bookIds){
    this.bookIds = bookIds;
}


public String addBookAmount(){
    logger.info("api.AddBookRequirement.addBookAmount--" + "departId=" + departId + " amount=" + amount + " operator=" + operator + " bookIds=" + bookIds);
    // 参数检查
    String errMsg = validateParam();
    if (StringUtils.isNotBlank(errMsg)) {
        renderErrResult(errMsg);
        logger.info("api.AddBookRequirement.addBookAmount--参数校验失败，" + errMsg);
        return SUCCESS;
    }
    // token检查
    String tokenCheckResult = checkToken();
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult);
        logger.info("api.AddBookRequirement.addBookAmount--" + tokenCheckResult);
        return SUCCESS;
    }
    // 保存记录
    try {
        addAmountBiz.addBookAmount(buildRequestDTO());
        // 清理token
        clearSessionToken();
        // 渲染结果
        renderSuccessResult();
        logger.info("api.AddBookRequirement--添加教材使用量成功");
        return SUCCESS;
    } catch (Exception e) {
        logger.error("api invoke failed, for AddBookAmount", e);
        renderErrResult(e.getMessage());
        return SUCCESS;
    }
}


public String getDepartId(){
    return departId;
}


public BookAmountAddApiRequestDTO buildRequestDTO(){
    @SuppressWarnings("unchecked")
    List<Integer> bookIdList = (List<Integer>) map.get("bookIdList");
    BookAmountAddApiRequestDTO dto = new BookAmountAddApiRequestDTO();
    dto.setAmount((Integer) map.get("amount"));
    dto.setBookIdList(bookIdList);
    dto.setDepartId((Integer) map.get("departId"));
    dto.setGrade((Integer) map.get("grade"));
    dto.setOperator(operator);
    dto.setTerm((Integer) map.get("term"));
    return dto;
}


public SchoolBiz getSchoolBiz(){
    return schoolBiz;
}


public BookAmountService getAddAmountBiz(){
    return addAmountBiz;
}


public BookService getBookService(){
    return bookService;
}


public void setDepartId(String departId){
    this.departId = departId;
}


public void setGrade(String grade){
    this.grade = grade;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setAmount(String amount){
    this.amount = amount;
}


public String getOperator(){
    return operator;
}


public String getBookIds(){
    return bookIds;
}


public void setTerm(String term){
    this.term = term;
}


public String getAmount(){
    return amount;
}


public String getGrade(){
    return grade;
}


}