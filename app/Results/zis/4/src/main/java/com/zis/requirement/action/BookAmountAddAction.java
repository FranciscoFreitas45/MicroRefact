package com.zis.requirement.action;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.bean.Departmentinfo;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.biz.SchoolBiz;
public class BookAmountAddAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 private  BookAmountService addAmountBiz;

 private  SchoolBiz schoolBiz;

 private  Integer grade;

 private  Integer term;

 private  Integer amount;

 private  String operator;

 private  Integer did;


public List<BookAmount> buildBookAmountList(Departmentinfo di,Map<Integer,Bookinfo> map){
    List<BookAmount> list = new ArrayList<BookAmount>();
    for (Entry<Integer, Bookinfo> entry : map.entrySet()) {
        BookAmount ba = new BookAmount();
        // 设置院校信息
        ba.setPartId(did);
        ba.setCollege(di.getCollege());
        ba.setInstitute(di.getInstitute());
        ba.setPartName(di.getPartName());
        ba.setGrade(grade);
        ba.setTerm(term);
        // 设置教材信息
        ba.setBookId(entry.getKey());
        ba.setBookAuthor(entry.getValue().getBookAuthor());
        ba.setBookName(entry.getValue().getBookName());
        ba.setBookPublisher(entry.getValue().getBookPublisher());
        ba.setIsbn(entry.getValue().getIsbn());
        ba.setGmtCreate(ZisUtils.getTS());
        ba.setGmtModify(ZisUtils.getTS());
        ba.setAmount(amount);
        ba.setOperator(operator);
        list.add(ba);
    }
    return list;
}


public Integer getDid(){
    return did;
}


public Integer getTerm(){
    return term;
}


@Validations(// 学年学期不能为空
requiredFields = { @RequiredFieldValidator(fieldName = "term", key = "学期不能为空") }, // 学年学期区间
intRangeFields = { @IntRangeFieldValidator(fieldName = "term", min = "1", max = "2", key = "学期在1和2之间") }, // 学年学期只能填数字
conversionErrorFields = { @ConversionErrorFieldValidator(fieldName = "amount", key = "数量只能填数字", shortCircuit = true), @ConversionErrorFieldValidator(fieldName = "term", key = "学期只能填数字", shortCircuit = true) })
public String addAmount(){
    // 参数检查
    if (did <= 0) {
        this.addFieldError("schoolNotExist", "院校信息不存在");
        return INPUT;
    }
    if (grade == null) {
        this.addFieldError("Error", "学年不能为空");
        return INPUT;
    }
    Departmentinfo di = schoolBiz.findDepartmentInfoById(did);
    if (di == null) {
        String errMsg = "后台添加教材使用量失败，院校不存在id=" + did;
        logger.error(errMsg);
        this.addActionError(errMsg);
        return INPUT;
    }
    // 从session中提取数据
    HttpSession session = ServletActionContext.getRequest().getSession();
    @SuppressWarnings("unchecked")
    Map<Integer, Bookinfo> map = (Map<Integer, Bookinfo>) session.getAttribute("BookMap");
    if (map == null) {
        String errMsg = "未添加图书";
        logger.error(errMsg);
        this.addActionError(errMsg);
        return INPUT;
    }
    // 准备要保存到数据库的数据
    List<BookAmount> list = buildBookAmountList(di, map);
    try {
        // 写入数据库
        this.addAmountBiz.saveBookAmountList(list);
        // 清理session中记录的数据
        session.setAttribute("BookMap", null);
    } catch (Exception e) {
        logger.error("后台添加教材使用量失败", e);
        this.addActionError(e.getMessage());
        return INPUT;
    }
    return SUCCESS;
}


public void setAddAmountBiz(BookAmountService addAmountBiz){
    this.addAmountBiz = addAmountBiz;
}


public void setSchoolBiz(SchoolBiz schoolBiz){
    this.schoolBiz = schoolBiz;
}


public SchoolBiz getSchoolBiz(){
    return schoolBiz;
}


public BookAmountService getAddAmountBiz(){
    return addAmountBiz;
}


public void setGrade(Integer grade){
    this.grade = grade;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public void setTerm(Integer term){
    this.term = term;
}


public String getOperator(){
    return operator;
}


public void setDid(Integer did){
    this.did = did;
}


public Integer getGrade(){
    return grade;
}


public Integer getAmount(){
    return amount;
}


}