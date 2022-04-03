package com.zis.requirement.biz;
 import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zis.requirement.dto.AddBookToDepartmentResult;
@Service
public class BookAmountServiceDWR {

@Autowired
 private  BookAmountService bookAmountService;


@RequiresPermissions(value = "requirement:books:input")
public AddBookToDepartmentResult addSelectedBookToDwrSession(Integer bookId,Integer departId,Integer grade,Integer term,HttpSession session){
    return this.bookAmountService.addSelectedBookToDwrSession(bookId, departId, grade, term, session);
}


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
public String updateForImmigrateBookRequirement(Integer bookIdFrom,Integer bookIdTo){
    return this.bookAmountService.updateForImmigrateBookRequirement(bookIdFrom, bookIdTo);
}


@RequiresPermissions(value = "requirement:books:input")
public AddBookToDepartmentResult removeSelectedBookToDwrSession(Integer bookId,HttpSession session){
    return this.bookAmountService.removeSelectedBookToDwrSession(bookId, session);
}


}