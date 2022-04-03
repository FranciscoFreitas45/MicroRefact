package com.zis.bookinfo.controller;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoAid;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.service.BookServiceDWR;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.purchase.biz.DoPurchaseServiceDWR;
import com.zis.Interface.DoPurchaseServiceDWR;
@Controller
@RequestMapping(value = "/bookInfo")
public class BookController {

 private  String BOOKINFO_WAIT_COUNT;

 private  String BOOKINFO_WAIT_COUNT_TIME;

 private  int BOOKINFO_WAIT_COUNT_EXPIRE_TIME;

 private  Logger logger;

@Autowired
 private  BookService bookService;

@Autowired
 private  BookServiceDWR bookServiceDWR;

@Autowired
 private  DoPurchaseServiceDWR doPurchaseServiceDWR;


@RequiresPermissions(value = "bookInfo:view")
@RequestMapping(value = "/getWaitCheckList")
public String getWaitCheckList(ModelMap context,HttpServletRequest request){
    Pageable page = WebHelper.buildPageRequest(request);
    Page<Bookinfo> list = this.bookService.getWaitCheckList(page);
    if (list.hasPrevious()) {
        context.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (list.hasNext()) {
        context.put("nextPage", page.next().getPageNumber());
    }
    context.put("list", list.getContent());
    return "bookinfo/dealWaitCheck";
}


@RequiresPermissions(value = "bookInfo:view")
@RequestMapping(value = "/showSameBooks")
public String showSameBooks(String sameBookIds,ModelMap context){
    if (StringUtils.isBlank(sameBookIds)) {
        return "error";
    }
    String ids = sameBookIds.replace("[", "");
    ids = ids.replace("]", "");
    String[] idArr = ids.split(",");
    List<Bookinfo> list = new ArrayList<Bookinfo>();
    for (String bookIdStr : idArr) {
        try {
            Integer id = Integer.valueOf(bookIdStr.trim());
            if (id == null) {
                continue;
            }
            Bookinfo book = this.bookService.findBookById(id);
            if (book != null && !ConstantString.ABANDON.equals(book.getBookStatus())) {
                list.add(book);
            }
        } catch (Exception e) {
            return "error";
        }
    }
    // 将返回结果存入modelMap中
    context.put("ALLBOOKS", list);
    System.out.println(list);
    return "bookinfo/sameBookList";
}


@RequiresPermissions(value = "bookInfo:view")
@RequestMapping(value = "/getAllBooks")
public String getAllBooks(ModelMap model,String bookName,Boolean strictBookName,String bookISBN,String bookAuthor,String bookPublisher,HttpServletRequest request){
    logger.debug("start:" + System.currentTimeMillis());
    // 创建查询条件
    Specification<Bookinfo> spec;
    try {
        spec = buildSpec(bookName, strictBookName, bookISBN, bookAuthor, bookPublisher);
    } catch (Exception e) {
        model.put("actionError", e.getMessage());
        logger.error(e.getMessage(), e);
        return "bookinfo/list";
    }
    // 分页查询
    Pageable page = WebHelper.buildPageRequest(request);
    Page<Bookinfo> list = this.bookService.findBySpecification(spec, page);
    // 待审核数
    int waitCheckCount = getWaitingCount(request);
    // 将返回结果存入ActionContext中
    model.put("waitCheckCount", waitCheckCount + "");
    model.put("list", list.getContent());
    model.put("page", page.getPageNumber() + 1);
    setQueryConditionToPage(model, bookISBN, bookName, strictBookName, bookAuthor, bookPublisher);
    if (list.hasPrevious()) {
        model.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (list.hasNext()) {
        model.put("nextPage", page.next().getPageNumber());
    }
    return "bookinfo/list";
}


@RequiresPermissions(value = "toolkit:toolkit")
@RequestMapping(value = "/analysisSameBook")
public String analysisSameBook(){
    this.bookService.analysisSimilarityBook();
    return "success";
}


public Integer buildSimilaritySearchContition(String similarityCheckLevel){
    Integer checkLevel;
    if (StringUtils.isBlank(similarityCheckLevel) || !StringUtils.isNumeric(similarityCheckLevel)) {
        similarityCheckLevel = "0";
        checkLevel = Integer.parseInt(similarityCheckLevel);
    } else {
        checkLevel = Integer.parseInt(similarityCheckLevel);
    }
    return checkLevel;
}


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
@RequestMapping(value = "/removeRelateId")
public String removeRelateId(Integer id,String pageType,ModelMap map){
    bookService.removeRelateId(id, pageType);
    map.put("pageType", pageType);
    return "forward:/bookInfo/showGroupList";
}


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
@RequestMapping(value = "/removeAll")
public String removeAll(HttpSession session,String pageType){
    @SuppressWarnings("unchecked")
    List<Bookinfo> list = (List<Bookinfo>) session.getAttribute("relateList");
    this.bookService.removeAllRelation(list, pageType);
    return "success";
}


public void setQueryConditionToPage(ModelMap context,String bookISBN,String bookName,Boolean strictBookName,String bookAuthor,String bookPublisher){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(bookISBN)) {
        condition.append("bookISBN=" + bookISBN + "&");
    }
    if (StringUtils.isNotBlank(bookName)) {
        condition.append("bookName=" + bookName + "&");
    }
    if (strictBookName != null && strictBookName == true) {
        condition.append("strictBookName=true&");
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        condition.append("bookAuthor=" + bookAuthor + "&");
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        condition.append("bookPublisher=" + bookPublisher + "&");
    }
    context.put("queryCondition", condition.toString());
}


@RequiresPermissions(value = "toolkit:toolkit")
@RequestMapping(value = "/adjustBooks")
public String adjustBooks(){
    bookService.processOneISBNToMultiBooks();
    bookService.processSameBooksToOneGroup();
    return "success";
}


public Specification<Bookinfo> buildSpec(String bookName,Boolean strictBookName,String bookISBN,String bookAuthor,String bookPublisher){
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    if (!StringUtils.isBlank(bookName)) {
        if (strictBookName != null && strictBookName == true) {
            query.eq("bookName", bookName);
        } else {
            query.like("bookName", "%" + bookName + "%");
        }
    }
    // ISBN不为空则添加条件
    if (!StringUtils.isBlank(bookISBN)) {
        String[] isbns = bookISBN.split(",");
        for (int i = 0; i < isbns.length; i++) {
            isbns[i] = isbns[i].trim();
            if (!StringUtils.isNumeric(isbns[i])) {
                throw new RuntimeException("isbn输入了非法字符");
            }
        }
        if (isbns.length == 1) {
            query.eq("isbn", isbns[0]);
        } else {
            // criteria.add(Restrictions.in("isbn", isbns));
            query.in("isbn", (Object[]) isbns);
        }
    }
    // 模糊查询作者
    if (!StringUtils.isBlank(bookAuthor))
        query.like("bookAuthor", "%" + bookAuthor + "%");
    if (!StringUtils.isBlank(bookPublisher)) {
        query.eq("bookPublisher", bookPublisher);
    }
    // 状态为废弃的不查询
    query.ne("bookStatus", ConstantString.ABANDON);
    return query.getSpecification();
}


public int getWaitingCount(HttpServletRequest request){
    ServletContext servletContext = request.getSession().getServletContext();
    Date lastCountTime = (Date) servletContext.getAttribute(BOOKINFO_WAIT_COUNT_TIME);
    if (lastCountTime != null) {
        long diff = new Date().getTime() - lastCountTime.getTime();
        if (diff <= BOOKINFO_WAIT_COUNT_EXPIRE_TIME) {
            return (Integer) servletContext.getAttribute(BOOKINFO_WAIT_COUNT);
        }
    }
    int waitingCount = bookService.countWaitingBooks();
    servletContext.setAttribute(BOOKINFO_WAIT_COUNT, waitingCount);
    servletContext.setAttribute(BOOKINFO_WAIT_COUNT_TIME, new Date());
    return waitingCount;
}


@RequiresPermissions(value = "bookInfo:saveOrUpdate")
@RequestMapping(value = "/findBookById")
public String findBookById(Integer bookId,ModelMap ctx){
    Bookinfo book = bookService.findBookById(bookId);
    if (book == null) {
        ctx.put("actionError", "无此图书, bookId=" + bookId);
        return "error";
    }
    BookinfoDetail detail = bookService.findBookInfoDetailByBookId(bookId);
    BookInfoAndDetailDTO infoAndDetail = new BookInfoAndDetailDTO();
    BeanUtils.copyProperties(book, infoAndDetail);
    if (detail != null) {
        BeanUtils.copyProperties(detail, infoAndDetail);
    }
    ctx.put("book", infoAndDetail);
    ctx.put("bookId", book.getId());
    ctx.put("isNewEdition", book.getIsNewEdition());
    ctx.put("repeatIsbn", book.getRepeatIsbn());
    return "bookinfo/alterBook";
}


@RequiresPermissions(value = { "bookInfo:saveOrUpdate", "bookInfo:delete", "purchase:management" }, logical = Logical.OR)
@RequestMapping(value = "/batchOperateBooks")
public String batchOperate(Integer[] batchSelectedItem,String operateType,ModelMap map){
    try {
        // 设置成不同版本
        if (BookBatchOperateType.SET_TO_GROUP.equals(operateType)) {
            bookServiceDWR.updateSameBooksToOneGroup(batchSelectedItem);
            return "success";
        } else // 设置成相关图书
        if (BookBatchOperateType.SET_TO_RELATED.equals(operateType)) {
            bookServiceDWR.updateRelatedBooksToOneGroup(batchSelectedItem);
            return "success";
        } else // 批量删除
        if (BookBatchOperateType.BATCH_DELETE.equals(operateType)) {
            bookServiceDWR.updateBookForBatchDelete(batchSelectedItem);
            return "success";
        } else // 批量拉黑
        if (BookBatchOperateType.BATCH_ADD_TO_BLACK_LIST.equals(operateType)) {
            for (Integer bookId : batchSelectedItem) {
                doPurchaseServiceDWR.addBlackList(bookId);
            }
            return "success";
        } else {
            map.put("actionError", "系统错误，不支持的操作类型" + operateType);
            return "error";
        }
    } catch (Exception e) {
        if (e instanceof UnauthorizedException) {
            map.put("exception", "未经授权，无法访问");
            map.put("exceptions", e.getMessage());
            return "unauthorized";
        }
        logger.error("操作失败，" + e.getMessage(), e);
        map.put("actionError", "操作失败，" + e.getMessage());
        return "error";
    }
}


@RequiresPermissions(value = "bookInfo:view")
@RequestMapping(value = "/showGroupList")
public String showGroupList(String relateId,String groupId,HttpSession session,ModelMap map){
    // 通过组的id将查询到的集合放入容器
    if (groupId != null) {
        List<Bookinfo> groupList = bookService.getBooksByGroupId(groupId);
        map.put("relateList", groupList);
        session.setAttribute("relateList", groupList);
        map.put("pageType", ConstantString.PAGEGROUP);
    // 关联id不为setAttribute空则将关联的信息房屋容器中
    } else if (relateId != null) {
        List<Bookinfo> relateList = bookService.getBooksByRelateId(relateId);
        map.put("relateList", relateList);
        session.setAttribute("relateList", relateList);
        map.put("pageType", ConstantString.PAGEGRELATE);
    }
    return "bookinfo/RelateList";
}


@RequiresPermissions(value = { "bookInfo:bookInfo:view", "toolkit:toolkit" }, logical = Logical.OR)
@RequestMapping(value = "/showSameBooksList")
public String showSameBookList(ModelMap context,String similarityCheckLevel,HttpServletRequest request){
    Integer checkLevel = buildSimilaritySearchContition(similarityCheckLevel);
    // 分页查询
    Pageable page = WebHelper.buildPageRequest(request);
    Page<BookinfoAid> sameBookList = this.bookService.findByCheckLevelAndTotalCountGtOne(checkLevel, page);
    // 将返回结果存入ModelMap中
    context.put("similarityCheckLevel", similarityCheckLevel);
    context.put("sameBookList", sameBookList.getContent());
    if (sameBookList.hasPrevious()) {
        context.put("prePage", sameBookList.previousPageable().getPageNumber());
    }
    if (sameBookList.hasNext()) {
        context.put("nextPage", sameBookList.nextPageable().getPageNumber());
    }
    return "/bookinfo/sameBook";
}


}