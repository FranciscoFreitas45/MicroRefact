package com.zis.api;
 import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.api.response.BaseApiResponse;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.ZisUtils;
@Controller
@RequestMapping(value = "/api")
public class InterfaceBooksToMoblieinfoController extends BaseApiController{

@Autowired
 private  BookService bookService;

 private  Logger logger;


public String validateParam(String isbn,String bookName,String publishDate,String price){
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


public Bookinfo buildBookInfo(String publishDate,String bookName,String edition,String publisher,String price,String bookAuthor,String isbn){
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


@RequestMapping(value = "/addBookInfo", produces = "text/plain; charset=utf-8")
public String addBookInfo(String isbn,String bookName,String bookAuthor,String publisher,String publishDate,String price,String edition,HttpServletResponse resp,String token){
    logger.info("api.InterfaceBooksToMoblieinfoAction.addBookInfo--" + "isbn=" + isbn + " bookName=" + bookName + " bookAuthor=" + bookAuthor + " publisher=" + publisher + " publishDate=" + publishDate + " price=" + price + " edition=" + edition);
    // HttpSession session = ServletActionContext.getRequest().getSession();
    BaseApiResponse response = new BaseApiResponse();
    // 参数检查
    String errMsg = validateParam(isbn, bookName, publishDate, price);
    if (StringUtils.isNotBlank(errMsg)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg(errMsg);
        renderResult(response, resp);
        return "";
    }
    // token检查
    String tokenCheckResult = checkToken(token);
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult, resp);
        logger.info("api.AddBookRequirement.addBookAmount--" + tokenCheckResult);
        return "";
    }
    // 准备数据
    try {
        Bookinfo book = buildBookInfo(publishDate, bookName, edition, publisher, price, bookAuthor, isbn);
        // XXX 暂时不处理图书详情
        bookService.addBook(book, null);
        // 返回结果
        response.setCode(BaseApiResponse.CODE_SUCCESS);
        renderResult(response, resp);
        logger.info("api.InterfaceBooksToMoblieinfoAction.addBookInfo.response successful..");
        return "";
    } catch (Exception e) {
        logger.error("添加图书失败: " + e.getMessage(), e);
        response.setCode(BaseApiResponse.CODE_INNER_ERROR);
        response.setMsg(e.getMessage());
        renderResult(response, resp);
        return "";
    } finally {
        // 清理token
        clearSessionToken();
    }
}


}