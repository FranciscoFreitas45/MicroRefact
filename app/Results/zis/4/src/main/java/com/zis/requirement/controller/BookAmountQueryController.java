package com.zis.requirement.controller;
 import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.dto.GetAmountDTO;
@Controller
@RequestMapping(value = "/requirement")
public class BookAmountQueryController {

@Autowired
 private BookAmountService bookAmountService;


@RequiresPermissions(value = { "requirement:books:view" })
@RequestMapping(value = "/getAmountForStu")
public String getAmountForStu(String isbn,String bookName,String school,String institute,String partName,Integer grade,Integer term,String operator,HttpServletRequest request,ModelMap context){
    if (StringUtils.isBlank(operator) || StringUtils.isBlank(school)) {
        return "requirement/getAmountForStu";
    }
    return "forward:/requirement/getAmountAction";
}


public void setQueryConditionToPage(String isbn,String bookName,String school,String institute,String partName,Integer grade,Integer term,String operator,HttpServletRequest request,ModelMap context){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(isbn)) {
        condition.append("isbn=" + isbn + "&");
    }
    if (StringUtils.isNotBlank(bookName)) {
        condition.append("bookName=" + bookName + "&");
    }
    if (StringUtils.isNotBlank(school)) {
        condition.append("school=" + school + "&");
    }
    if (StringUtils.isNotBlank(institute)) {
        condition.append("institute=" + institute + "&");
    }
    if (StringUtils.isNotBlank(partName)) {
        condition.append("partName=" + partName + "&");
    }
    if (grade != null) {
        condition.append("grade=" + grade + "&");
    }
    if (term != null) {
        condition.append("term=" + term + "&");
    }
    if (StringUtils.isNotBlank(operator)) {
        condition.append("operator=" + operator + "&");
    }
    context.put("queryCondition", condition.toString());
}


public Specification<BookAmount> buildSpec(String isbn,String bookName,String school,String institute,String partName,Integer grade,Integer term,String operator){
    QueryUtil<BookAmount> query = new QueryUtil<BookAmount>();
    if (!StringUtils.isBlank(isbn)) {
        query.eq("isbn", isbn);
    }
    if (!StringUtils.isBlank(bookName)) {
        query.like("bookName", "%" + bookName + "%");
    }
    if (!StringUtils.isBlank(school)) {
        query.like("college", "%" + school + "%");
    }
    if (!StringUtils.isBlank(institute)) {
        query.like("institute", "%" + institute + "%");
    }
    if (!StringUtils.isBlank(partName)) {
        query.like("partName", "%" + partName + "%");
    }
    if (grade != null && grade > 0) {
        query.eq("grade", grade);
    }
    if (term != null && term > 0) {
        query.eq("term", term);
    }
    if (!StringUtils.isBlank(operator)) {
        query.eq("operator", operator);
    }
    query.asc("college").asc("institute").asc("partName").asc("grade").asc("term");
    return query.getSpecification();
}


@RequiresPermissions(value = { "requirement:books:view" })
@RequestMapping(value = "/getAmountAction")
public String getAmount(GetAmountDTO getAmountDTO,BindingResult br,HttpServletRequest request,ModelMap context){
    if (br.hasErrors()) {
        return "requirement/getAmount";
    }
    String isbn = getAmountDTO.getIsbn();
    String bookName = getAmountDTO.getIsbn();
    String school = getAmountDTO.getSchool();
    String institute = getAmountDTO.getInstitute();
    String partName = getAmountDTO.getPartName();
    Integer grade = getAmountDTO.getGrade();
    Integer term = getAmountDTO.getTerm();
    String operator = getAmountDTO.getOperator();
    if (term != null) {
        if (term != 1 && term != 2) {
            context.put("actionError", "学期在1和2之间");
            return "error";
        }
    }
    // 创建查询条件
    Specification<BookAmount> spec = buildSpec(isbn, bookName, school, institute, partName, grade, term, operator);
    // 分页查询
    Pageable page = WebHelper.buildPageRequest(request);
    Page<BookAmount> pList = this.bookAmountService.findBySpecPage(spec, page);
    // 将返回结果存入ModelMap中
    context.put("list", pList.getContent());
    context.put("isbn", isbn);
    context.put("bookName", bookName);
    context.put("school", school);
    context.put("institute", institute);
    context.put("partName", partName);
    context.put("grade", grade);
    context.put("term", term);
    context.put("operator", operator);
    context.put("operator", operator);
    context.put("page", page.getPageNumber() + 1);
    setQueryConditionToPage(isbn, bookName, school, institute, partName, grade, term, operator, request, context);
    if (pList.hasPrevious()) {
        context.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (pList.hasNext()) {
        context.put("nextPage", page.next().getPageNumber());
    }
    return "requirement/getAmount";
}


}