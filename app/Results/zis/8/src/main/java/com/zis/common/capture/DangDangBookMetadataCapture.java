package com.zis.common.capture;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.bookinfo.util.BookMetadataSource;
import com.zis.common.util.ZisUtils;
public class DangDangBookMetadataCapture extends AbstractBookMetadataCapture{

 private  String URL_FMT_DETAIL_PAGE;

 private  String URL_FMT_LIST_PAGE;

 private  String page404Keywords;

 protected  String REGEX_EDITION;

 protected  String REGEX_USELESS_BRACKETS;

 protected  String REGEX_ISBN;

 private  Logger logger;


@Override
public boolean page404(Document doc){
    // 当当网通过title判断404页面
    return doc.title().equals("对不起，您要访问的页面暂时没有找到。");
}


@Override
public String getDetailPageUrl(String itemId){
    return String.format(URL_FMT_DETAIL_PAGE, itemId);
}


public String parseTitle(Document doc,boolean isDirect){
    if (isDirect) {
        Element h1 = getUniqueElementByTag(doc, "h1");
        return h1.text();
    } else {
        // <head><title>《现代时尚》 【简介_书评_在线阅读】 - 当当 - 思宇图书专营店</title></head>
        Element t = doc.head().child(0);
        Matcher m = Pattern.compile("(?<=《).*?(?=》)").matcher(t.html());
        while (m.find()) {
            return m.group();
        }
        return null;
    }
}


@Override
public BookMetadata parseMetadata(Document doc){
    // 跳过电子书
    if (doc.title().contains("当当电子书")) {
        return null;
    }
    // 是否是当当直营（直营和入驻店铺详情页不同）
    boolean isDirect = false;
    if (doc.html().contains("当当自营")) {
        isDirect = true;
    }
    // ItemId
    // <div class="share_div clearfix">
    // ...
    // <a href="javascript:;" class="btn_scsp" title="收藏" dd_name="收藏商品"
    // data-prd="24058225" ...>
    Element itemIdElem = doc.getElementsByClass("share_div").get(0);
    String itemId = itemIdElem.child(2).attr("data-prd");
    logger.debug("[数据抓取-当当网] 商品ID={}", itemId);
    // 标题
    String title = parseTitle(doc, isDirect);
    logger.debug("[数据抓取-当当网] 标题=\t{}", title);
    // 书名、版次
    String edition = null;
    String bookName = null;
    Matcher mt = Pattern.compile(REGEX_EDITION).matcher(title);
    while (mt.find()) {
        edition = mt.group();
        logger.debug("[数据抓取-当当网] 版次\t{}", edition);
    }
    if (edition != null) {
        bookName = title.replaceAll(edition, "").replaceAll(REGEX_USELESS_BRACKETS, "");
    } else {
        edition = "第一版";
        bookName = title;
        logger.debug("[数据抓取-当当网] 版次(默认)\t{}", edition);
    }
    logger.debug("[数据抓取-当当网] 书名\t{}", bookName);
    // 作者、出版社、出版日期
    String author = null;
    String publisher = null;
    Date publishDate = null;
    String isbn = null;
    if (isDirect) {
        Element messboxInfo = getUniqueElementByClass(doc, "messbox_info");
        for (Element element : messboxInfo.children()) {
            // <span class="t1" id="author" dd_name="作者">
            if ("作者".equals(element.attr("dd_name"))) {
                logger.debug("[数据抓取-当当网] 作者相关数据\t{}", element.text());
                StringBuilder builder = new StringBuilder();
                for (Element authorNode : element.children()) {
                    builder.append(" ").append(authorNode.text());
                }
                author = builder.substring(1).toString();
            }
            // <span class="t1" dd_name="出版社">
            if ("出版社".equals(element.attr("dd_name"))) {
                publisher = element.child(0).text();
                publisher = formatPublisher(publisher);
            }
            // <span class="t1">出版时间:2009年8月&nbsp;</span>
            if (element.text().contains("出版时间")) {
                // (?<=作/译者[：:]{1}).*?(?=出版社)
                Matcher m = Pattern.compile("(?<=出版时间:).*?(?=&)").matcher(element.html());
                while (m.find()) {
                    publishDate = ZisUtils.stringToDate(m.group().trim(), "yyyy年MM月");
                }
            }
        }
    } else {
        Elements infos = getUniqueElementByClass(doc, "messbox_info").children();
        // <span class="t1" id="author" dd_name="作者">作者:<a
        // href="...">邵光远</a>主编</span>
        author = infos.get(0).childNode(1).childNode(0).toString();
        // <span class="t1" dd_name="出版社">出版社:<a
        // href="...">内蒙古人民出版社</a></span>
        publisher = infos.get(1).childNode(1).childNode(0).toString();
        publisher = formatPublisher(publisher);
        // <span class="t1">出版时间:2006年08月&nbsp;</span>
        publishDate = ZisUtils.stringToDate(infos.get(2).text(), "出版时间:yyyy年MM月");
        // <div class="pro_content" id="detail_describe">
        Element detail = doc.getElementById("detail_describe");
        // <li>国际标准书号ISBN：7204083857474</li>
        String isbnTxt = detail.child(0).child(9).html();
        Matcher m = Pattern.compile("(?<=国际标准书号ISBN：).*?(?=$)").matcher(isbnTxt);
        while (m.find()) {
            isbn = m.group().trim();
        }
    }
    logger.debug("[数据抓取-当当网] 作者=\t{}", author);
    logger.debug("[数据抓取-当当网] 出版社=\t{}", publisher);
    logger.debug("[数据抓取-当当网] 出版时间=\t{}", publishDate);
    // 定价
    Double tagPrice = null;
    if (isDirect) {
        // <span class="price_m">&yen;99.00</span>
        Element priceElement = getUniqueElementByClass(doc, "price_pc").child(3);
        logger.debug("[数据抓取-当当网] 定价相关数据={}", priceElement.text());
        tagPrice = Double.valueOf(priceElement.text().substring(1));
    } else {
        // <div class="price_m" id='original-price'> <span
        // class="yen">&yen;</span>1780</div>
        Element e = doc.getElementById("original-price");
        if (e == null) {
            e = doc.getElementById("dd-price");
        }
        Node n = e.childNode(2);
        String priceStr = n.toString();
        // String priceStr =
        // doc.getElementById("original-price").childNode(2).toString();
        tagPrice = Double.valueOf(priceStr);
    }
    logger.debug("[数据抓取-当当网] 定价=\t{}", tagPrice);
    // 条形码
    // <div class="pro_content" id="detail_describe"> <ul
    // class="key clearfix"> ... <li>国际标准书号ISBN：9787121209161</li>...
    if (isDirect) {
        Element detailDescrib = doc.getElementById("detail_describe");
        String isbnTxt = detailDescrib.child(0).child(9).text();
        logger.debug("[数据抓取-当当网] ISBN相关数据={}", isbnTxt);
        Matcher m = Pattern.compile(REGEX_ISBN).matcher(isbnTxt);
        while (m.find()) {
            isbn = m.group();
        }
    }
    logger.debug("[数据抓取-当当网] ISBN=\t{}", isbn);
    // 图片
    Element detailPic = doc.getElementById("detailPic");
    String imageUrl = detailPic.attr("src");
    logger.debug("[数据抓取-当当网] 图片URL={}", imageUrl);
    // 摘要
    String summary = null;
    if (isDirect) {
        // <div id="content" class="section">
        Element summaryElement = doc.getElementById("content");
        if (summaryElement != null) {
            summary = summaryElement.child(1).text();
            logger.debug("[数据抓取-当当网] 摘要={}", summary);
        }
    }
    // 目录
    String catalog = null;
    if (isDirect) {
        // <div id="catalog" class="section">
        Element catalogElement = doc.getElementById("catalog");
        if (catalogElement != null) {
            catalog = catalogElement.child(1).child(2).text();
            logger.debug("[数据抓取-当当网] 目录={}", catalog);
        }
    }
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
    meta.setSource(BookMetadataSource.DANG_DANG);
    return meta;
}


@Override
public List<BookMetadata> captureListPage(String keyword){
    Document doc = super.doRequestUrl(URL_FMT_LIST_PAGE + keyword);
    // 没有记录
    if (doc.title().equals(page404Keywords)) {
        logger.info("[数据抓取-当当网] 无此记录，keyword=" + keyword);
        return new ArrayList<BookMetadata>();
    }
    Elements items = doc.getElementsByClass("shoplist").get(0).child(0).children();
    if (items == null || items.isEmpty()) {
        logger.info("[数据抓取-当当网] 无此记录，keyword=" + keyword);
        return new ArrayList<BookMetadata>();
    }
    List<BookMetadata> list = new ArrayList<BookMetadata>();
    for (Element item : items) {
        try {
            // <li
            String itemId = item.child(0).attr("id").substring(1);
            // class="line1"
            // id="p23764025">
            BookMetadata meta = super.captureDetailPage(itemId);
            if (meta != null) {
                list.add(meta);
            }
        } catch (Exception e) {
            // 单一错误不影响其他记录
            logger.error("[数据抓取-当当网] 操作失败，原因如下：" + e.getMessage(), e);
            e.printStackTrace();
        }
    }
    return list;
}


}