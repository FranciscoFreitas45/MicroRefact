package com.zis.bookinfo.action;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoAid;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.Page;
import com.zis.common.util.PaginationQueryUtil;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.Page;
public class BookAction extends ActionSupportimplements SessionAware,RequestAware{

 private  long serialVersionUID;

 private  String BOOKINFO_WAIT_COUNT;

 private  String BOOKINFO_WAIT_COUNT_TIME;

 private  int BOOKINFO_WAIT_COUNT_EXPIRE_TIME;

 private  Map<String,Object> request;

 private  Map<String,Object> session;

 private  String bookName;

 private  Boolean strictBookName;

 private  String bookISBN;

 private  String bookAuthor;

 private  String bookPublisher;

 private  String bookId;

 private  Integer[] batchSelectedItem;

 private  String operateType;

 private  String relateId;

 private  String groupId;

 private  String id;

 private  String pageType;

 private  String pageSource;

 private  String sameBookIds;

 private  String similarityCheckLevel;

 private  Integer pageNow;

 private  Logger logger;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


public void setGroupId(String groupId){
    this.groupId = groupId;
}


public String getBookISBN(){
    return bookISBN;
}


public String getRelateId(){
    return relateId;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public String getGroupId(){
    return groupId;
}


public String getAllBooks(){
    // 处理GET请求中的中文字符
    preProcessGetRequestCHN();
    logger.debug("start:" + System.currentTimeMillis());
    // 待审核数
    int waitCheckCount = getWaitingCount();
    // 创建查询条件
    logger.debug("after count waiting records:" + System.currentTimeMillis());
    DetachedCriteria criteria = buildDetachedCriteria();
    // 分页查询
    if (pageNow == null || pageNow < 1) {
        pageNow = 1;
    }
    Integer totalCount = PaginationQueryUtil.getTotalCount(criteria);
    Page page = Page.createPage(pageNow, Page.DEFAULT_PAGE_SIZE, totalCount);
    logger.debug("after page:" + System.currentTimeMillis());
    @SuppressWarnings("unchecked")
    List<Bookinfo> list = PaginationQueryUtil.paginationQuery(criteria, page);
    logger.debug("final:" + System.currentTimeMillis());
    // 将返回结果存入ActionContext中
    ActionContext context = ActionContext.getContext();
    context.put("waitCheckCount", waitCheckCount + "");
    context.put("ALLBOOKS", list);
    context.put("bookISBN", bookISBN);
    context.put("bookName", bookName);
    context.put("bookAuthor", bookAuthor);
    context.put("bookPublisher", bookPublisher);
    setQueryConditionToPage(context);
    context.put("pageNow", pageNow);
    if (page.isHasPre()) {
        context.put("prePage", pageNow - 1);
    }
    if (page.isHasNext()) {
        context.put("nextPage", pageNow + 1);
    }
    return SUCCESS;
}


public String removeRelateId(){
    int ID = Integer.parseInt(id);
    bookService.removeRelateId(ID, pageType);
    return SUCCESS;
}


public String removeAll(){
    @SuppressWarnings("unchecked")
    List<Bookinfo> list = (List<Bookinfo>) session.get("relateList");
    this.bookService.removeAllRelation(list, pageType);
    return SUCCESS;
}


public void setQueryConditionToPage(ActionContext context){
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


public String adjustBooks(){
    bookService.processOneISBNToMultiBooks();
    bookService.processSameBooksToOneGroup();
    return SUCCESS;
}


public String findBookById(){
    // 
    int id = Integer.parseInt(bookId);
    Bookinfo book = bookService.findBookById(id);
    if (book == null) {
        this.addActionError("无此图书, bookId=" + bookId);
        return ERROR;
    }
    BookinfoDetail detail = bookService.findBookInfoDetailByBookId(id);
    BookInfoAndDetailDTO infoAndDetail = new BookInfoAndDetailDTO();
    BeanUtils.copyProperties(book, infoAndDetail);
    if (detail != null) {
        BeanUtils.copyProperties(detail, infoAndDetail);
    }
    ActionContext ctx = ActionContext.getContext();
    ctx.put("book", infoAndDetail);
    ctx.put("bookId", book.getId());
    ctx.put("isNewEdition", book.getIsNewEdition());
    ctx.put("repeatIsbn", book.getRepeatIsbn());
    return SUCCESS;
}


public void setId(String id){
    this.id = id;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public String showGroupList(){
    ActionContext context = ActionContext.getContext();
    // 通过组的id将查询到的集合放入容器
    if (groupId != null) {
        List<Bookinfo> groupList = bookService.getBooksByGroupId(groupId);
        context.put("relateList", groupList);
        session.put("relateList", groupList);
        context.put("pageType", ConstantString.PAGEGROUP);
    // 关联id不为空则将关联的信息房屋容器中
    } else if (relateId != null) {
        List<Bookinfo> relateList = bookService.getBooksByRelateId(relateId);
        context.put("relateList", relateList);
        session.put("relateList", relateList);
        context.put("pageType", ConstantString.PAGEGRELATE);
    }
    return SUCCESS;
}


public void setPageType(String pageType){
    this.pageType = pageType;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public String analysisSameBook(){
    this.bookService.analysisSimilarityBook();
    return SUCCESS;
}


public void setBookId(String bookId){
    this.bookId = bookId;
}


public String getBookAuthor(){
    return bookAuthor;
}


public String getPageSource(){
    return pageSource;
}


public DetachedCriteria buildDetachedCriteria(){
    DetachedCriteria criteria = DetachedCriteria.forClass(Bookinfo.class);
    if (!StringUtils.isBlank(bookName)) {
        if (strictBookName != null && strictBookName == true) {
            criteria.add(Restrictions.eq("bookName", bookName));
        } else {
            criteria.add(Restrictions.like("bookName", "%" + bookName + "%"));
        }
    }
    // ISBN不为空则添加条件
    if (!StringUtils.isBlank(bookISBN)) {
        String[] isbns = bookISBN.split(",");
        if (isbns.length == 1) {
            criteria.add(Restrictions.eq("isbn", bookISBN));
        } else {
            criteria.add(Restrictions.in("isbn", isbns));
        }
    }
    // 模糊查询作者
    if (!StringUtils.isBlank(bookAuthor))
        criteria.add(Restrictions.like("bookAuthor", "%" + bookAuthor + "%"));
    if (!StringUtils.isBlank(bookPublisher)) {
        criteria.add(Restrictions.eq("bookPublisher", bookPublisher));
    }
    // 状态为废弃的不查询
    criteria.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    return criteria;
}


public String batchOperate(){
    try {
        // 设置成不同版本
        if (BookBatchOperateType.SET_TO_GROUP.equals(operateType)) {
            bookService.updateSameBooksToOneGroup(batchSelectedItem);
            return SUCCESS;
        } else // 设置成相关图书
        if (BookBatchOperateType.SET_TO_RELATED.equals(operateType)) {
            bookService.updateRelatedBooksToOneGroup(batchSelectedItem);
            return SUCCESS;
        } else // 批量删除
        if (BookBatchOperateType.BATCH_DELETE.equals(operateType)) {
            bookService.updateBookForBatchDelete(batchSelectedItem);
            return SUCCESS;
        } else // 批量拉黑
        if (BookBatchOperateType.BATCH_ADD_TO_BLACK_LIST.equals(operateType)) {
            for (Integer bookId : batchSelectedItem) {
                doPurchaseService.addBlackList(bookId);
            }
            return SUCCESS;
        } else {
            this.addActionError("系统错误，不支持的操作类型" + operateType);
            return ERROR;
        }
    } catch (Exception e) {
        logger.error("操作失败，" + e.getMessage(), e);
        this.addActionError("操作失败，" + e.getMessage());
        return ERROR;
    }
}


public void setSimilarityCheckLevel(String similarityCheckLevel){
    this.similarityCheckLevel = similarityCheckLevel;
}


public void setRequest(Map<String,Object> request){
    this.request = request;
}


public Map<String,Object> getRequest(){
    return request;
}


public String getWaitCheckList(){
    List<Bookinfo> list = bookService.getWaitCheckList();
    ActionContext context = ActionContext.getContext();
    context.put("ALLBOOKS", list);
    return SUCCESS;
}


public String getId(){
    return id;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public String getPageType(){
    return pageType;
}


public void setStrictBookName(Boolean strictBookName){
    this.strictBookName = strictBookName;
}


public Boolean getStrictBookName(){
    return strictBookName;
}


public void setRelateId(String relateId){
    this.relateId = relateId;
}


public DetachedCriteria buildSimilaritySearchContition(){
    DetachedCriteria dc = DetachedCriteria.forClass(BookinfoAid.class);
    if (StringUtils.isBlank(similarityCheckLevel)) {
        similarityCheckLevel = "0";
    }
    try {
        Integer checkLevel = Integer.parseInt(similarityCheckLevel);
        dc.add(Restrictions.eq("checkLevel", checkLevel));
    } catch (NumberFormatException e) {
    }
    return dc;
}


public void setBatchSelectedItem(Integer[] batchSelectedItem){
    this.batchSelectedItem = batchSelectedItem;
}


public void setPageSource(String pageSource){
    this.pageSource = pageSource;
}


public BookService getBookService(){
    return bookService;
}


public String getBookName(){
    return bookName;
}


public void setSession(Map<String,Object> session){
    this.session = session;
}


public void setPageNow(Integer pageNow){
    this.pageNow = pageNow;
}


public String showSameBookList(){
    DetachedCriteria criteria = buildSimilaritySearchContition();
    criteria.add(Restrictions.gt("totalCount", 1));
    // 分页查询
    if (pageNow == null || pageNow < 1) {
        pageNow = 1;
    }
    Integer totalCount = PaginationQueryUtil.getTotalCount(criteria);
    Page page = Page.createPage(pageNow, 100, totalCount);
    @SuppressWarnings("unchecked")
    List<BookinfoAid> sameBookList = PaginationQueryUtil.paginationQuery(criteria, page);
    // 将返回结果存入ActionContext中
    ActionContext context = ActionContext.getContext();
    context.put("similarityCheckLevel", similarityCheckLevel);
    context.put("sameBookList", sameBookList);
    context.put("pageNow", pageNow);
    if (page.isHasPre()) {
        context.put("prePage", pageNow - 1);
    }
    if (page.isHasNext()) {
        context.put("nextPage", pageNow + 1);
    }
    return SUCCESS;
}


public void setSameBookIds(String sameBookIds){
    this.sameBookIds = sameBookIds;
}


public String getSimilarityCheckLevel(){
    return similarityCheckLevel;
}


public String getSameBookIds(){
    return sameBookIds;
}


public String getOperateType(){
    return operateType;
}


public void setBookISBN(String bookISBN){
    this.bookISBN = bookISBN;
}


public String showSameBooks(){
    if (StringUtils.isBlank(sameBookIds)) {
        return ERROR;
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
            return ERROR;
        }
    }
    // 将返回结果存入ActionContext中
    ActionContext context = ActionContext.getContext();
    context.put("ALLBOOKS", list);
    return SUCCESS;
}


public Integer[] getBatchSelectedItem(){
    return batchSelectedItem;
}


public void preProcessGetRequestCHN(){
    // 仅仅对带条件的分页查询使用转化
    if (!"pagination".equals(pageSource)) {
        return;
    }
    if (StringUtils.isNotBlank(bookName)) {
        bookName = ZisUtils.convertGetRequestCHN(bookName);
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        bookAuthor = ZisUtils.convertGetRequestCHN(bookAuthor);
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        bookPublisher = ZisUtils.convertGetRequestCHN(bookPublisher);
    }
}


public Integer getPageNow(){
    return pageNow;
}


public int getWaitingCount(){
    Map<String, Object> application = ActionContext.getContext().getApplication();
    Date lastCountTime = (Date) application.get(BOOKINFO_WAIT_COUNT_TIME);
    if (lastCountTime == null) {
        int waitingCount = bookService.getWaitCheckList().size();
        application.put(BOOKINFO_WAIT_COUNT, waitingCount);
        application.put(BOOKINFO_WAIT_COUNT_TIME, new Date());
        return waitingCount;
    }
    Date currentTime = new Date();
    long diff = currentTime.getTime() - lastCountTime.getTime();
    if (diff > BOOKINFO_WAIT_COUNT_EXPIRE_TIME) {
        int waitingCount = bookService.getWaitCheckList().size();
        application.put(BOOKINFO_WAIT_COUNT, waitingCount);
        application.put(BOOKINFO_WAIT_COUNT_TIME, currentTime);
        return waitingCount;
    } else {
        return (Integer) application.get(BOOKINFO_WAIT_COUNT);
    }
}


public String getBookId(){
    return bookId;
}


public Map<String,Object> getSession(){
    return session;
}


}