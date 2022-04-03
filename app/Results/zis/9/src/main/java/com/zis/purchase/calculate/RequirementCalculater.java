package com.zis.purchase.calculate;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.cache.SysVarCache;
import com.zis.common.cache.SysVarConstant;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.repository.BookAmountDao;
import com.zis.Interface.BookAmountDao;
import com.zis.Interface.BookService;
import com.zis.DTO.BookAmountDao;
import com.zis.DTO.QueryUtil;
import com.zis.DTO.BookService;
import com.zis.DTO.BookAmountDao;
@Component(value = "requirementCalculater")
public class RequirementCalculater implements BookAmountCalculateInterface{

@Autowired
 private  BookAmountDao bookAmountDao;

@Autowired
 private  SysVarCache sysVarCache;

@Autowired
 private  BookService bookService;


public Integer calculate(int bookId,Integer repoId){
    Integer portio = sysVarCache.getSystemVar(SysVarConstant.PURCHASE_REQUIREMENT_PORTIO.getKeyName());
    return getRequirementAmount(bookId) * portio / 100;
}


public Integer getRequirementAmount(int bookId){
    if (bookId <= 0) {
        return 0;
    }
    Bookinfo book = bookService.findBookById(bookId);
    // 图书不存在或者状态不是正式，直接返回0
    if (book == null || !ConstantString.USEFUL.equals(book.getBookStatus())) {
        return 0;
    }
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookamount.class);
    List<BookAmount> list;
    // 如果存在不同版本的图书，最新版本的使用量是之前所有版本之和
    if (book.getIsNewEdition() && StringUtils.isNotBlank(book.getGroupId())) {
        QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
        query.eq("groupId", book.getGroupId());
        query.eq("bookStatus", ConstantString.USEFUL);
        // DetachedCriteria criteria =
        // DetachedCriteria.forClass(Bookinfo.class);
        // criteria.setProjection(Projections.property("id"));
        // criteria.add(Restrictions.eq("groupId", book.getGroupId()));
        // criteria.add(Restrictions.eq("bookStatus",
        // ConstantString.USEFUL));
        Specification<Bookinfo> spec = query.getSpecification();
        // FIXME 获取IDS为空
        List<Bookinfo> ids = this.bookService.findBySpecificationAll(spec);
        List<Integer> idsInt = new ArrayList<Integer>();
        if (ids != null) {
            for (int i = 0; i < ids.size(); i++) {
                idsInt.add(ids.get(i).getId());
            }
        }
        list = this.bookAmountDao.findByBookIdListGradeAndCollege(idsInt);
    // dc.add(Restrictions.in("bookId", ids));
    } else {
        // dc.add(Restrictions.eq("bookId", bookId));
        list = this.bookAmountDao.findByBookIdGradeAndCollege(bookId);
    }
    // dc.add(Restrictions.ne("grade", 1));//大一数据不统计
    // dc.add(Restrictions.ne("college", "A测试专用"));//测试专业不统计
    // = this.bookAmountDao.findByCriteria(dc);
    if (list == null || list.isEmpty()) {
        return 0;
    }
    int total = 0;
    for (BookAmount ba : list) {
        total += ba.getAmount();
    }
    return total;
}


}