package com.zis.oldapi;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.api.response.BaseApiResponse;
import com.zis.api.response.BookInfoQueryData;
import com.zis.api.response.BookInfoQueryResponse;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.YouLuNetDetailCapture;
import com.zis.Interface.YouLuNetDetailCapture;
public class InterfaceBookinfoAction extends ActionSupport{

 private  long serialVersionUID;

 private  String isbn;

 private  BookService bookService;

 private  YouLuNetDetailCapture youLuNetDetailCapture;

 private  Logger logger;


public String queryBookInfo(){
    logger.info("api.InterfaceBookinfoAction.queryBookInfo.request--" + "isbn=" + isbn);
    BaseApiResponse response = new BaseApiResponse();
    // 参数检验
    if (StringUtils.isBlank(isbn)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg("ISNB不能为空!");
        renderResult(response);
        return SUCCESS;
    }
    try {
        // 查询数据
        List<Bookinfo> booklist = bookService.findBookByISBN(isbn);
        if (booklist.isEmpty()) {
            try {
                Bookinfo book = getFromYouLuNet();
                if (book != null) {
                    booklist.add(book);
                }
            } catch (Exception e) {
                logger.error("有路网查询失败", e);
                BookInfoQueryResponse responseBookinfo = new BookInfoQueryResponse();
                responseBookinfo.setCode(BaseApiResponse.CODE_SUCCESS);
                renderResult(responseBookinfo);
                return SUCCESS;
            }
        }
        // 复制list
        List<BookInfoQueryData> resultList = new ArrayList<BookInfoQueryData>();
        for (Bookinfo bi : booklist) {
            BookInfoQueryData entry = new BookInfoQueryData();
            BeanUtils.copyProperties(bi, entry);
            entry.setBookId(bi.getId());
            resultList.add(entry);
        }
        BookInfoQueryResponse responseBookinfo = new BookInfoQueryResponse();
        responseBookinfo.setCode(BookInfoQueryResponse.CODE_SUCCESS);
        responseBookinfo.setResultList(resultList);
        renderResult(responseBookinfo);
        logger.info("api.InterfaceBookinfoAction.queryBookInfo.response successful..");
        return SUCCESS;
    } catch (BeansException e) {
        logger.error("api invoke failed!", e);
        BookInfoQueryResponse responseBookinfo = new BookInfoQueryResponse();
        responseBookinfo.setCode(BookInfoQueryResponse.CODE_INNER_ERROR);
        responseBookinfo.setMsg(e.getMessage());
        renderResult(responseBookinfo);
        logger.info("api.InterfaceBookinfoAction.queryBookInfo.response failed!");
        return SUCCESS;
    }
}


public Bookinfo getFromYouLuNet(){
    logger.info("api.InterfaceBookinfoAction.queryBookInfo.request--get from youlu.net, isbn=" + isbn);
    int bookid = youLuNetDetailCapture.getBookIdByIsbn(isbn);
    if (bookid <= 0) {
        throw new RuntimeException("有路网解析数据错误，bookId必须大于0，isbn=" + isbn);
    }
    Bookinfo book = bookService.saveBookinfoByCaptureFromYouluNet(bookid);
    return book;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public void setYouLuNetDetailCapture(YouLuNetDetailCapture youLuNetDetailCapture){
    this.youLuNetDetailCapture = youLuNetDetailCapture;
}


public BookService getBookService(){
    return bookService;
}


public void renderResult(Object obj){
    // json序列化
    String content = JSON.toJSONString(obj);
    // 
    ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
    try {
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(content);
        out.flush();
        out.close();
    } catch (IOException e) {
        logger.error("序列化过程失败", e);
    }
}


public String getIsbn(){
    return isbn;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public YouLuNetDetailCapture getYouLuNetDetailCapture(){
    return youLuNetDetailCapture;
}


}