package com.zis.api;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.api.response.BaseApiResponse;
import com.zis.bookinfo.service.BookService;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.biz.SchoolBiz;
import com.zis.requirement.dto.BookAmountAddApiRequestDTO;
import com.zis.Interface.BookService;
@Controller
@RequestMapping(value = "/api")
public class AddBookAmountController extends BaseApiController{

@Autowired
 private  BookAmountService addAmountBiz;

@Autowired
 private  BookService bookService;

@Autowired
 private  SchoolBiz schoolBiz;

 private  Logger logger;

 private  Map<String,Object> map;


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


@RequestMapping(value = "/addBookRequirement", produces = "text/plain; charset=utf-8")
public String addBookAmount(String departId,String amount,String operator,String bookIds,String grade,String term,HttpServletResponse response,String token){
    logger.info("api.AddBookRequirement.addBookAmount--" + "departId=" + departId + " amount=" + amount + " operator=" + operator + " bookIds=" + bookIds);
    // 参数检查
    String errMsg = validateParam(grade, term, departId, bookIds, operator, amount);
    if (StringUtils.isNotBlank(errMsg)) {
        renderErrResult(errMsg, response);
        logger.info("api.AddBookRequirement.addBookAmount--参数校验失败，" + errMsg);
        return "";
    }
    // token检查
    String tokenCheckResult = checkToken(token);
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult, response);
        logger.info("api.AddBookRequirement.addBookAmount--" + tokenCheckResult);
        return "";
    }
    // 保存记录
    try {
        addAmountBiz.addBookAmount(buildRequestDTO(operator));
        // 渲染结果
        renderSuccessResult(response);
        logger.info("api.AddBookRequirement--添加教材使用量成功");
        return "";
    } catch (Exception e) {
        logger.error("api invoke failed, for AddBookAmount", e);
        renderErrResult(e.getMessage(), response);
        return "";
    } finally {
        // 清理token
        clearSessionToken();
    }
}


public BookAmountAddApiRequestDTO buildRequestDTO(String operator){
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


public String validateParam(String grade,String term,String departId,String bookIds,String operator,String amount){
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


public void renderSuccessResult(HttpServletResponse resp){
    BaseApiResponse response = new BaseApiResponse();
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    renderResult(response, resp);
}


}