package com.zis.oldapi;
 import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.zis.api.response.BaseApiResponse;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.ZisUtils;
public class InterfaceBooksToMoblieinfoAction extends BaseApiAction{

 private  long serialVersionUID;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String publisher;

 private  String publishDate;

 private  String price;

 private  String edition;

 private  BookService bookService;

 private  Logger logger;


public String validateParam(){
    StringBuffer errMsg = new StringBuffer();
    if (StringUtils.isBlank(isbn)) {
        errMsg.append("isbn不能为空");
    }
    if (StringUtils.isBlank(bookName)) {
        errMsg.append("bookName不能为空");
    }
    Date pubDate = ZisUtils.stringToDate(publishDate);
    if (pubDate == null) {
        errMsg.append("日期格式错误，必须为yyyy-MM--------");
        logger.error("日期格式错误" + publishDate);
    }
    try {
        Float.parseFloat(price);
    } catch (NumberFormatException e1) {
        errMsg.append("价格必须是数值型--------");
        logger.error("价格必须为整数", e1);
    }
    return errMsg.toString();
}


public Bookinfo buildBookInfo(){
    Bookinfo book = new Bookinfo();
    // Date date = new Date();
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    // try {
    // date = dateFormat.parse(publishDate);
    // } catch (ParseException e) {
    // logger.error("日期转换异常", e);
    // }
    Date pubDate = ZisUtils.stringToDate(publishDate);
    if (pubDate == null) {
        throw new RuntimeException("日期格式错误" + publishDate);
    }
    Double bookPrice = null;
    try {
        bookPrice = Double.parseDouble(price);
    } catch (NumberFormatException e1) {
        logger.error("价格转换异常", e1);
    }
    book.setBookName(bookName);
    book.setBookEdition(edition);
    book.setBookPublisher(publisher);
    book.setBookAuthor(bookAuthor);
    book.setIsbn(isbn);
    book.setBookPrice(bookPrice);
    book.setPublishDate(pubDate);
    book.setBookStatus(ConstantString.WAITCHECK);
    return book;
}


public void setPublishDate(String publishDate){
    this.publishDate = publishDate;
}


public void setEdition(String edition){
    this.edition = edition;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public String getPublishDate(){
    return publishDate;
}


public String getIsbn(){
    return isbn;
}


public void setPrice(String price){
    this.price = price;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public String getEdition(){
    return edition;
}


public String getPrice(){
    return price;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public String getPublisher(){
    return publisher;
}


public BookService getBookService(){
    return bookService;
}


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public void setPublisher(String publisher){
    this.publisher = publisher;
}


public String addBookInfo(){
    logger.info("api.InterfaceBooksToMoblieinfoAction.addBookInfo--" + "isbn=" + isbn + " bookName=" + bookName + " bookAuthor=" + bookAuthor + " publisher=" + publisher + " publishDate=" + publishDate + " price=" + price + " edition=" + edition);
    // HttpSession session = ServletActionContext.getRequest().getSession();
    BaseApiResponse response = new BaseApiResponse();
    // 参数检查
    String errMsg = validateParam();
    if (StringUtils.isNotBlank(errMsg)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg(errMsg);
        renderResult(response);
        return SUCCESS;
    }
    // token检查
    String tokenCheckResult = checkToken();
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult);
        logger.info("api.AddBookRequirement.addBookAmount--" + tokenCheckResult);
        return SUCCESS;
    }
    // 准备数据
    try {
        Bookinfo book = buildBookInfo();
        // XXX 暂时不处理图书详情
        bookService.addBook(book, null);
        // 清理token
        clearSessionToken();
    } catch (Exception e) {
        logger.error("添加图书失败: " + e.getMessage(), e);
        response.setCode(BaseApiResponse.CODE_INNER_ERROR);
        response.setMsg(e.getMessage());
        renderResult(response);
        return SUCCESS;
    }
    // 返回结果
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    renderResult(response);
    logger.info("api.InterfaceBooksToMoblieinfoAction.addBookInfo.response successful..");
    return SUCCESS;
}


}