package com.zis.bookinfo.action;
 import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils.isBlank;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.BookMetadataSource;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.ZisUtils;
public class BookSaveOrUpdateAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 private  Integer id;

 private  Integer outId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Date publishDate;

 private  Double bookPrice;

 private  String bookEdition;

 private  Boolean isNewEdition;

 private  Boolean repeatIsbn;

 private  String bookStatus;

 private  String operateType;

 private  String imageUrl;

 private  String taobaoTitle;

 private  Integer taobaoCatagoryId;

 private  String summary;

 private  String catalog;

 private  BookService bookService;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


@Validations(/* �ǿ��ַ� */
requiredStrings = { @RequiredStringValidator(fieldName = "isbn", trim = true, key = "isbn不能为空"), @RequiredStringValidator(fieldName = "bookName", trim = true, key = "书名不能为空"), @RequiredStringValidator(fieldName = "bookAuthor", trim = true, key = "作者不能为空"), @RequiredStringValidator(fieldName = "bookEdition", trim = true, key = "版次不能为空"), @RequiredStringValidator(fieldName = "bookPublisher", trim = true, key = "出版社不能为空") }, /* �������� */
stringLengthFields = { @StringLengthFieldValidator(fieldName = "bookName", maxLength = "128", key = "书名不能超过128个字符"), @StringLengthFieldValidator(fieldName = "isbn", maxLength = "30", key = "isbn不能超过30个字符"), @StringLengthFieldValidator(fieldName = "bookAuthor", maxLength = "50", key = "作者不能超过50个字符"), @StringLengthFieldValidator(fieldName = "bookPublisher", maxLength = "30", key = "出版社不能超过30个字符"), @StringLengthFieldValidator(fieldName = "groupId", maxLength = "20", key = "ͬgroupId不能超过20个字符"), @StringLengthFieldValidator(fieldName = "relateId", maxLength = "20", key = "relateId不能超过20个字符") }, /* �ǿ����� */
requiredFields = { @RequiredFieldValidator(fieldName = "bookPrice", key = "价格不能为空"), @RequiredFieldValidator(fieldName = "publishDate", key = "出版日期不能为空") }, /* ��ֵ */
intRangeFields = { @IntRangeFieldValidator(fieldName = "outId", min = "1", key = "外部ID必须大于0") })
public Bookinfo buildBook(Bookinfo book){
    book.setIsbn(isbn);
    book.setBookName(bookName);
    book.setBookAuthor(bookAuthor);
    book.setBookPublisher(bookPublisher);
    book.setPublishDate(publishDate);
    book.setBookPrice(bookPrice);
    book.setBookEdition(bookEdition);
    book.setIsNewEdition(isNewEdition);
    book.setRepeatIsbn(repeatIsbn);
    return book;
}


public void setBookPrice(Double bookPrice){
    this.bookPrice = bookPrice;
}


public void setBookStatus(String bookStatus){
    this.bookStatus = bookStatus;
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public Integer getId(){
    return id;
}


public void setCatalog(String catalog){
    this.catalog = catalog;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public Date getPublishDate(){
    return publishDate;
}


public void setRepeatIsbn(Boolean repeatIsbn){
    this.repeatIsbn = repeatIsbn;
}


public void setTaobaoTitle(String taobaoTitle){
    this.taobaoTitle = taobaoTitle;
}


public String getCatalog(){
    return catalog;
}


public BookService getBookService(){
    return bookService;
}


public String getImageUrl(){
    return imageUrl;
}


public void setTaobaoCatagoryId(Integer taobaoCatagoryId){
    this.taobaoCatagoryId = taobaoCatagoryId;
}


public String getBookName(){
    return bookName;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getTaobaoCatagoryId(){
    return taobaoCatagoryId;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public void setIsNewEdition(Boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public String saveOrUpdate(){
    if (bookPrice < 0) {
        addFieldError("bookPrice", "价格必须大于0");
        return INPUT;
    }
    try {
        Bookinfo book;
        BookinfoDetail detail;
        if (id != null) {
            Bookinfo bi = bookService.findBookById(id);
            book = buildBook(bi);
            // 审核
            if (ConstantString.CHECKOk.equals(operateType)) {
                this.bookService.updateBookForCheckOK(book);
            } else // 废弃
            if (ConstantString.NOTUSE.equals(operateType)) {
                this.bookService.updateBookForDisable(id);
            } else // 修改
            {
                detail = buildBookInfoDetail();
                book.setBookStatus(bi.getBookStatus());
                bookService.updateBook(book, detail);
            }
        } else // 新增
        {
            book = buildBook(new Bookinfo());
            detail = buildBookInfoDetail();
            book.setOutId(outId);
            book.setBookStatus(ConstantString.USEFUL);
            // XXX 暂时不处理图书详情
            bookService.addBook(book, detail);
            // 检查系统中是否存在相似记录
            DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
            dc.add(Restrictions.like("bookAuthor", "%" + book.getBookAuthor() + "%"));
            dc.add(Restrictions.eq("bookPublisher", book.getBookPublisher()));
            dc.add(Restrictions.ne("bookStatus", BookinfoStatus.DISCARD));
            List<Bookinfo> samebookList = bookService.findBookByCriteria(dc);
            if (samebookList != null && samebookList.size() > 1) {
                // 如果存在相似记录，则跳转到图书列表页由用户操作
                this.addActionMessage("新添加的图书在系统中有相似记录，请检查是否有重复记录，并将不同版本的图书设置到同一分组。");
                return "showRelated";
            }
        }
        this.addActionMessage(bookName + "已保存");
        return SUCCESS;
    } catch (Exception e) {
        this.addActionError("操作失败:" + e.getMessage());
        logger.error("操作失败:" + e.getMessage(), e);
        return ERROR;
    }
}


public void setSummary(String summary){
    this.summary = summary;
}


public Integer getOutId(){
    return outId;
}


public Double getBookPrice(){
    return bookPrice;
}


public String getSummary(){
    return summary;
}


public String getOperateType(){
    return operateType;
}


public BookinfoDetail buildBookInfoDetail(){
    // 如果页面中未填写任何信息，返回null（业务层不会对detail做任何处理）
    if (isBlank(imageUrl) && isBlank(taobaoTitle) && isBlank(summary) && isBlank(catalog) && taobaoCatagoryId == null) {
        return null;
    }
    BookinfoDetail detail = null;
    if (id != null) {
        detail = bookService.findBookInfoDetailByBookId(id);
    }
    // DB中无记录，并且页面中未填写任何信息，返回null
    if (detail == null) {
        detail = new BookinfoDetail();
        detail.setBookid(id);
        detail.setImageUrl(imageUrl);
        detail.setCatalog(catalog);
        detail.setSummary(summary);
        detail.setTaobaoForbidden(false);
        detail.setTaobaoTitle(taobaoTitle);
        detail.setTaobaoCatagoryId(taobaoCatagoryId);
        detail.setSource(BookMetadataSource.USER);
        detail.setGmtCreate(ZisUtils.getTS());
        detail.setGmtModify(ZisUtils.getTS());
        detail.setVersion(0);
        return detail;
    } else {
        if (!isBlank(imageUrl)) {
            detail.setImageUrl(imageUrl);
        }
        if (!isBlank(taobaoTitle)) {
            detail.setTaobaoTitle(taobaoTitle);
        }
        if (!isBlank(summary)) {
            detail.setSummary(summary);
        }
        if (!isBlank(catalog)) {
            detail.setCatalog(catalog);
        }
        if (taobaoCatagoryId != null) {
            detail.setTaobaoCatagoryId(taobaoCatagoryId);
        }
        return detail;
    }
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public String getBookEdition(){
    return bookEdition;
}


public String getBookStatus(){
    return bookStatus;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public String getIsbn(){
    return isbn;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public Boolean getIsNewEdition(){
    return isNewEdition;
}


public String getTaobaoTitle(){
    return taobaoTitle;
}


public void setOutId(Integer outId){
    this.outId = outId;
}


public Boolean getRepeatIsbn(){
    return repeatIsbn;
}


public String getBookPublisher(){
    return bookPublisher;
}


}