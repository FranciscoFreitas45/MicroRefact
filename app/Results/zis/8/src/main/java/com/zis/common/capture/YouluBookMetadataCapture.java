package com.zis.common.capture;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.bookinfo.util.BookMetadataSource;
import com.zis.common.util.ZisUtils;
public class YouluBookMetadataCapture extends AbstractBookMetadataCapture{

 private  String URL_FMT_DETAIL_PAGE;

 private  String URL_FMT_LIST_PAGE;

 private  String URL_FMT_CONTENT;

 private  String URL_NO_PHOTO;

 private  String page404Keywords;

 protected  String REGEX_ITEM_ID;

 protected  String REGEX_ISBN;

 protected  String REGEX_AUTHOR;

 protected  String REGEX_PUBLISHER;

 protected  String REGEX_PUBLISH_DATE;

 protected  String REGEX_TAG_PRICE;

 protected  String REGEX_EDITION;

 protected  String REGEX_USELESS_BRACKETS;

 private  Logger logger;


public String parseImageUrl(Document doc){
    // Element info3 = getUniqueElementByClass(doc, "info3");
    Element pic = getUniqueElementByClass(doc, "show-pic");
    if (pic == null) {
        return null;
    }
    // Element node = pic.child(0).child(0).child(0);
    Element node = pic.child(0).child(0).child(0);
    String imageUrl = node.attr("src").replace("jpg-n", "jpg").replace("jpg-s", "jpg");
    // 有路网无此图片是通过重定向到noPhoto页面实现的（很古怪的逻辑）
    String realImageUrl = super.getRedirectUrl(imageUrl);
    return URL_NO_PHOTO.equals(realImageUrl) ? null : imageUrl;
}


@Override
public boolean page404(Document doc){
    Elements elements = doc.getElementsByTag("h2");
    for (Element element : elements) {
        if (element.text().contains(page404Keywords)) {
            logger.debug("[数据抓取-有路网] 无此记录");
            return true;
        }
    }
    return false;
}


@Override
public String getDetailPageUrl(String itemId){
    return URL_FMT_DETAIL_PAGE + itemId;
}


@Override
public BookMetadata parseMetadata(Document doc){
    // 有路网标题
    Element h1 = getUniqueElementByTag(doc, "h1");
    // 抓取图书名称
    Element eBookName = doc.getElementById("name");
    // TODO 书名中剔除不必要内容
    String title = h1.ownText();
    String bookName = eBookName.text();
    String edition = null;
    logger.debug("[数据抓取-有路网] 标题\t{}", title);
    // 拆分书名、版次
    Matcher tm = Pattern.compile(REGEX_EDITION).matcher(title);
    while (tm.find()) {
        edition = tm.group();
        logger.debug("[数据抓取-有路网] 版次\t{}", edition);
    }
    // if (edition != null) {
    // bookName = title.replaceAll(edition,
    // "").replaceAll(REGEX_USELESS_BRACKETS, "");
    // } else {
    // edition = "第一版";
    // bookName = title.replaceAll(REGEX_USELESS_BRACKETS, "");
    // logger.debug("[数据抓取-有路网] 版次(默认)\t{}", edition);
    // }
    if (edition == null) {
        edition = "第一版";
        logger.debug("[数据抓取-有路网] 版次(默认)\t{}", edition);
    }
    logger.debug("[数据抓取-有路网] 书名\t{}", bookName);
    // 条形码、作者、出版社、出版日期、标价
    // Elements infoTits = doc.getElementsByClass("infoList");
    Elements infoTits = doc.getElementsByClass("detail-info");
    String itemId = null;
    String isbn = null;
    String author = null;
    String publisher = null;
    String publishDateStr = null;
    Date publishDate = null;
    Double tagPrice = null;
    for (Element e : infoTits) {
        String txt = e.text();
        if (txt.contains("ISBN")) {
            logger.debug("[数据抓取-有路网] ISBN相关数据\t{}", txt);
            Matcher m = Pattern.compile(REGEX_ISBN).matcher(txt);
            while (m.find()) {
                isbn = m.group();
                logger.debug("[数据抓取-有路网] ISBN=\t{}", isbn);
            }
        }
        if (txt.contains("作者")) {
            logger.debug("[数据抓取-有路网] 作者相关数据\t{}", txt);
            Matcher m = Pattern.compile(REGEX_AUTHOR).matcher(txt);
            while (m.find()) {
                author = m.group();
                logger.debug("[数据抓取-有路网] 作者=\t{}", author);
            }
        // author = e.child(0).text();
        }
        if (txt.contains("出版社")) {
            logger.debug("[数据抓取-有路网] 出版社相关数据\t{}", txt);
            Matcher m = Pattern.compile(REGEX_PUBLISHER).matcher(txt);
            while (m.find()) {
                publisher = m.group();
                if (StringUtils.isNotBlank(publisher)) {
                    publisher = publisher.split(" ")[0].trim();
                }
                logger.debug("[数据抓取-有路网] 出版社=\t{}", publisher);
            }
        // publisher = e.child(3).text();
        }
        if (txt.contains("出版日期")) {
            logger.debug("[数据抓取-有路网] 出版日期相关数据\t{}", txt);
            Matcher m = Pattern.compile(REGEX_PUBLISH_DATE).matcher(txt);
            while (m.find()) {
                publishDateStr = m.group();
                logger.debug("[数据抓取-有路网] 出版日期=\t{}", publishDateStr);
            }
            if ("--".equals(publishDateStr) || StringUtils.isBlank(publishDateStr)) {
                publishDate = null;
            } else {
                publishDate = ZisUtils.stringToDate(publishDateStr.trim(), "yyyy年MM月");
            }
        }
        if (txt.contains("定价")) {
            logger.debug("[数据抓取-有路网] 定价相关数据\t{}", txt);
            Matcher m = Pattern.compile(REGEX_TAG_PRICE).matcher(txt);
            String priceStr = null;
            while (m.find()) {
                priceStr = m.group();
                logger.debug("[数据抓取-有路网] 标价=\t{}", priceStr);
            }
            tagPrice = (priceStr == null ? null : Double.valueOf(priceStr));
        }
    }
    // 抓取平台图书ID
    Element bookId = doc.getElementById("ctl00_cphMain_txtBookId");
    logger.debug("[数据抓取-有路网] 图书编号相关数据\t{}", bookId);
    itemId = bookId.val();
    logger.debug("[数据抓取-有路网] 图书编号=\t{}", itemId);
    // 图片URL
    String imageUrl = parseImageUrl(doc);
    logger.debug("[数据抓取-有路网] 图片URL=\t{}", imageUrl);
    // 库存、售价暂不抓取
    // 摘要
    String summaryUrl = String.format(URL_FMT_CONTENT, itemId, "summary");
    String summary = super.doRequestUrlToPlainText(summaryUrl, "utf-8");
    logger.debug("[数据抓取-有路网] 摘要=\t{}", summary);
    // 目录
    String catalogUrl = String.format(URL_FMT_CONTENT, itemId, "catalog");
    String catalog = super.doRequestUrlToPlainText(catalogUrl, "utf-8");
    logger.debug("[数据抓取-有路网] 目录=\t{}", catalog);
    BookMetadata meta = new BookMetadata();
    meta.setAuthor(author);
    meta.setSummary(summary);
    meta.setCatalog(catalog);
    meta.setEdition(edition);
    meta.setImageUrl(imageUrl);
    meta.setIsbnCode(isbn);
    meta.setName(bookName);
    meta.setOutId(itemId);
    meta.setPrice(tagPrice);
    if (publishDate != null) {
        meta.setPublishDate(publishDate);
    }
    meta.setPublisher(publisher);
    meta.setSummary(summary);
    meta.setSource(BookMetadataSource.YOU_LU);
    return meta;
}


@Override
public List<BookMetadata> captureListPage(String keyword){
    if (StringUtils.isBlank(keyword)) {
        throw new IllegalArgumentException("查询关键词keyword不能为空");
    }
    String url = URL_FMT_LIST_PAGE + keyword;
    Document doc = super.doRequestUrl(url);
    Element newBookList = getUniqueElementByClass(doc, "newBookList");
    if (newBookList == null) {
        return new ArrayList<BookMetadata>();
    }
    List<BookMetadata> resultList = new ArrayList<BookMetadata>();
    Elements ulList = newBookList.child(0).children();
    for (Element li : ulList) {
        try {
            String itemId = li.child(0).child(0).attr("href").substring(1);
            resultList.add(captureDetailPage(itemId));
        } catch (Exception e) {
            // 单一错误不影响其他记录
            logger.error("[数据抓取-有路网] 操作失败，原因如下：" + e.getMessage(), e);
        }
    }
    return resultList;
}


}