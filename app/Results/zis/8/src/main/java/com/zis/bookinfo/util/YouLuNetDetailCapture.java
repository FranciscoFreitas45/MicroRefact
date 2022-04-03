package com.zis.bookinfo.util;
 import com.zis.common.util.ZisUtils;
@Deprecated
public class YouLuNetDetailCapture extends AbstractNetCapture{

 private  String[] invalidTitleElements;


public Integer getBookIdByIsbn(String isbn){
    String url = "http://www.youlu.net/search/result3/?isbn=" + isbn;
    String buf = getUrlContent(url, "gbk");
    String failMsg = "共有图书数量 0 种";
    if (buf.contains(failMsg)) {
        return -1;
    }
    String idStr = parseInfo(isbn, buf, "p class=\"bookname\"", "/", "\"");
    Integer bookId = Integer.parseInt(idStr);
    return bookId;
}


public String clearBookName(String bookName){
    for (String elem : invalidTitleElements) {
        bookName = bookName.replace(elem, "");
    }
    bookName = bookName.replace("　", " ");
    bookName = bookName.replace("  ", " ");
    bookName = bookName.replace("  ", " ");
    return clearInvalidChar(bookName);
}


public BookMetadata getBookInfo(String id){
    String url = "http://www.youlu.net/" + id;
    String buf = getUrlContent(url, DEFAULT_CHARSET);
    String failMsg = "图书信息不存在";
    if (buf.contains(failMsg)) {
        return null;
    }
    // 处理版次和书名
    String youLuBookTitle = parseInfo(id, buf, "info1", "<h1>", "</h1>");
    String edition = "第一版";
    if (youLuBookTitle.contains("版")) {
        Integer posStart = youLuBookTitle.indexOf("(");
        if (posStart == -1) {
            posStart = youLuBookTitle.indexOf("（");
        }
        Integer posEnd = youLuBookTitle.lastIndexOf("版");
        if (posStart > 0 && posEnd > 0) {
            edition = youLuBookTitle.substring(posStart + 1, posEnd + 1);
            youLuBookTitle = youLuBookTitle.replace(youLuBookTitle.substring(posStart + 1, posEnd + 1), // 标题中去掉版次
            "");
        }
    }
    String bookName = clearBookName(youLuBookTitle);
    // 
    String isbnCode = parseInfo(id, buf, "\"infoTit\">ISBN", "</span>", "[");
    String tagPrice = parseInfo(id, buf, "\"infoTit\">定价：", "listPrice\">", "<");
    tagPrice = tagPrice.replace("&nbsp;", "").replace("￥", "");
    String author = parseInfo(id, buf, "作/译者", "_blank\">", "</a>");
    String publisher = parseInfo(id, buf, ">出版社", "\">", "</a>");
    String publishDate = parseInfo(id, buf, "出版日期：", "</span>", "&nbsp");
    // 内容&目录
    String contentUrlFmt = "http://www.youlu.net/info/bodyContent.aspx?bookId=%s&contentType=%s";
    String summary = getUrlContent(String.format(contentUrlFmt, id, "summary"), "gbk").replace(",", "，").replace("\"", "");
    String catelog = getUrlContent(String.format(contentUrlFmt, id, "catalog"), "gbk").replace(",", "，").replace("\"", "");
    // 库存
    String stock = parseInfo(id, buf, "startRequestBookBuyLink", isbnCode + "', '", "',");
    String salePrice = parseInfo(id, buf, "旧书普通用户价", "：", "元");
    BookMetadata bi = new BookMetadata();
    bi.setAuthor(author);
    bi.setEdition(edition);
    bi.setIsbnCode(isbnCode);
    bi.setName(bookName);
    bi.setPrice(Double.valueOf(tagPrice));
    bi.setPublishDate(ZisUtils.stringToDate(publishDate, "yyyy年MM月"));
    bi.setPublisher(publisher);
    bi.setSummary(summary);
    bi.setCatalog(catelog);
    bi.setStock(Integer.valueOf(stock));
    bi.setSalesPrice(Double.valueOf(salePrice));
    logger.info("获取有路网数据成功, youluId=" + id);
    return bi;
}


public String clearInvalidChar(String s){
    if (s == null) {
        return "";
    }
    return s.replace(",", " ").replace("\"", "").trim();
}


}