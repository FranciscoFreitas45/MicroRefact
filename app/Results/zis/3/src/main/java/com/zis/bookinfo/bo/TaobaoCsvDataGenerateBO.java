package com.zis.bookinfo.bo;
 import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.dto.BookInfoAndDetailV2DTO;
import com.zis.common.mail.MailSenderFactory;
import com.zis.common.mail.SimpleMailSender;
import com.zis.common.util.ImageUtils;
import com.zis.common.util.TextClearUtils;
import com.zis.common.util.ZipUtils;
import com.zis.common.util.ZisUtils;
import com.zis.Interface.SimpleMailSender;
public class TaobaoCsvDataGenerateBO {

 private  Logger logger;

 private  String T_HEAD1;

 private  String T_HEAD2;

 private  String T_ROW;

 private  String DESCRIPTION_FMT;

 private  Integer DEFAULT_SOLD_TYPE;

 private  Integer DEFAULT_SOLD_COUNT;

 private  Integer DEFAULT_ON_SALES;

 private  Integer DEFAULT_CATEGORY_ID;

 private  String encoding;

 private  String baseDir;

 private  SimpleMailSender mailSender;


public String generateOneLine(BookInfoAndDetailDTO book){
    // 标题使用 书名 (版次)+ 作者 + 条形码
    String title = book.getTaobaoTitle();
    if (StringUtils.isBlank(title)) {
        title = TextClearUtils.buildTaobaoTitle(book);
    }
    Integer categoryId = book.getTaobaoCatagoryId();
    if (categoryId == null) {
        // 默认分类：大学教材
        categoryId = DEFAULT_CATEGORY_ID;
    }
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    String merchantNo = genUniqueIsbn(book);
    String description = genDescription(book);
    // XXX 运费模板ID可以考虑和店铺关联
    String deliveryFeeId = "7838769160";
    String imageName = genImageName(book);
    String publishDateStr = ZisUtils.getDateString("yyyy-MM", book.getPublishDate());
    // 使用系统中的库存量，如果不存在/为零，则使用默认值
    Integer stockBalance = (book.getStockBalance() != null && book.getStockBalance() > 0) ? book.getStockBalance() : DEFAULT_SOLD_COUNT;
    return String.format(T_ROW, title, categoryId, DEFAULT_SOLD_TYPE, book.getBookPrice(), stockBalance, DEFAULT_ON_SALES, description, deliveryFeeId, imageName, merchantNo, book.getBookName(), book.getBookPrice(), book.getBookAuthor(), book.getBookPublisher(), book.getIsbn(), publishDateStr);
}


public void setEncoding(String encoding){
    this.encoding = encoding;
}


public String genUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码-bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


public void generateV2(List<BookInfoAndDetailV2DTO> bookList,String[] emails){
    if (bookList == null || bookList.isEmpty()) {
        logger.info("[生成文件-淘宝csv] 未生成任何文件，传入数据为空。");
        return;
    }
    // 生成器基础检查，如果不通过会报错
    basicCheck();
    // 创建临时文件夹
    try {
        String batchId = ZisUtils.getDateString("yyyy-MM-dd_HHmmss");
        String tmpDir = baseDir + batchId;
        String picDir = tmpDir + "/data/";
        FileUtils.forceMkdir(new File(tmpDir));
        FileUtils.forceMkdir(new File(picDir));
        logger.info("[生成文件-淘宝csv] 生成临时目录 {}", tmpDir);
        // 生成csv文件
        generateCSVV2(bookList, tmpDir + "/data.csv");
        logger.info("[生成文件-淘宝csv] 生成数据文件 {}", tmpDir + "/data.csv");
        // 生成(下载)图片文件
        downloadImageV2(bookList, picDir);
        logger.info("[生成文件-淘宝csv] 生成图片目录 {}", picDir);
        // 打包
        String destZipFile = tmpDir + ".zip";
        ZipUtils.compress(tmpDir, destZipFile);
        logger.info("[生成文件-淘宝csv] 生成压缩包 {}", tmpDir);
        // 发送邮件
        mailSender.send(emails, "淘宝数据包-" + batchId, "请在附件中下载，解压缩后导入淘宝助理\n\n- - - - - - - - - - - - - - - - -\n本邮件由ZIS系统自动发送", destZipFile);
        logger.info("[生成文件-淘宝csv]  压缩包{} 发送邮件到邮箱列表 {}", destZipFile, ArrayUtils.toString(emails));
        // 清理临时文件
        FileUtils.deleteQuietly(new File(tmpDir));
        FileUtils.deleteQuietly(new File(destZipFile));
        logger.info("[生成文件-淘宝csv] 清理所有临时文件 {}, {}", tmpDir, destZipFile);
    } catch (Exception e) {
        logger.error("[生成文件-淘宝csv] 系统异常，原因为", e);
        try {
            mailSender.send(emails, "生成淘宝数据包失败", "生成淘宝数据包失败！错误原因如下，请联系管理员：\n" + e.getMessage());
        } catch (Exception mailEx) {
            logger.error("[生成文件-淘宝csv] 发送邮件失败，原因为" + mailEx.getMessage(), mailEx);
        }
        throw new RuntimeException(e);
    }
}


public void generateCSV(List<BookInfoAndDetailDTO> bookList,String filepath){
    FileWriterWithEncoding writer = new FileWriterWithEncoding(filepath, encoding);
    writer.write(T_HEAD1);
    writer.write(T_HEAD2);
    for (BookInfoAndDetailDTO book : bookList) {
        writer.write(generateOneLine(book));
        writer.flush();
        logger.debug("[生成文件-淘宝csv] 生成数据行 bookId={}, bookName={}", book.getId(), book.getBookName());
    }
    writer.close();
}


public void basicCheck(){
    if (StringUtils.isBlank(baseDir)) {
        throw new RuntimeException("[生成文件-淘宝csv] 生成器未完成初始化，baseDir未设置，请检查配置文件");
    }
}


public String genImageName(BookInfoAndDetailDTO book){
    return String.format("%s_%s", book.getIsbn(), book.getId());
}


public String genDescription(BookInfoAndDetailDTO book){
    if (StringUtils.isBlank(book.getSummary()) && StringUtils.isBlank(book.getCatalog())) {
        StringBuilder builder = new StringBuilder();
        builder.append("ISBN：").append(book.getIsbn()).append("<br/>");
        builder.append("书名：").append(book.getBookName()).append("<br/>");
        builder.append("作者：").append(book.getBookAuthor()).append("<br/>");
        builder.append("版次：").append(book.getBookEdition()).append("<br/>");
        builder.append("出版社：").append(book.getBookPublisher()).append("<br/>");
        builder.append("出版日期：").append(ZisUtils.getDateString("yyyy年MM月", book.getPublishDate())).append("<br/>");
        return builder.toString();
    }
    String summary = formatContent(book.getSummary());
    String catalog = formatContent(book.getCatalog());
    return String.format(DESCRIPTION_FMT, summary, catalog);
}


public String genImageNameV2(BookInfoAndDetailV2DTO book){
    return String.format("%s_%s", book.getIsbn(), book.getId());
}


public void downloadImageV2(List<BookInfoAndDetailV2DTO> bookList,String picDir){
    for (BookInfoAndDetailV2DTO book : bookList) {
        if (StringUtils.isBlank(book.getImageUrl())) {
            // 跳过无图片的记录
            continue;
        }
        String imageFileName = genImageNameV2(book) + ".tbi";
        try {
            ImageUtils.downloadImg(book.getImageUrl(), picDir, imageFileName);
            // 休眠100毫秒，防止被对方系统拉黑
            ZisUtils.sleepQuietly(100);
            logger.debug("[生成文件-淘宝csv] 成功下载图片 {}，保存到 {}", book.getImageUrl(), picDir);
        } catch (Exception e) {
            logger.error("[生成文件-淘宝csv] 下载图片过程出错", e);
        }
    }
}


public void setBaseDir(String baseDir){
    this.baseDir = baseDir;
}


public String generateOneLineV2(BookInfoAndDetailV2DTO book){
    // 标题使用 书名 (版次)+ 作者 + 条形码
    String title = book.getTaobaoTitle();
    if (StringUtils.isBlank(title)) {
        title = TextClearUtils.buildTaobaoTitle(book);
    }
    Integer categoryId = book.getTaobaoCatagoryId();
    if (categoryId == null) {
        // 默认分类：大学教材
        categoryId = DEFAULT_CATEGORY_ID;
    }
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    String merchantNo = genUniqueIsbn(book);
    String description = genDescriptionV2(book);
    // 运费模板
    String deliveryFeeId = book.getDeliveryTemplateId().toString();
    String imageName = genImageNameV2(book);
    String publishDateStr = ZisUtils.getDateString("yyyy-MM", book.getPublishDate());
    // 使用系统中的库存量，如果不存在/为零，则使用默认值
    Integer stockBalance = (book.getStockBalance() != null && book.getStockBalance() > 0) ? book.getStockBalance() : DEFAULT_SOLD_COUNT;
    return String.format(T_ROW, title, categoryId, DEFAULT_SOLD_TYPE, book.getBookPrice(), stockBalance, DEFAULT_ON_SALES, description, deliveryFeeId, imageName, merchantNo, book.getBookName(), book.getBookPrice(), book.getBookAuthor(), book.getBookPublisher(), book.getIsbn(), publishDateStr);
}


public void generateCSVV2(List<BookInfoAndDetailV2DTO> bookList,String filepath){
    FileWriterWithEncoding writer = new FileWriterWithEncoding(filepath, encoding);
    writer.write(T_HEAD1);
    writer.write(T_HEAD2);
    for (BookInfoAndDetailV2DTO book : bookList) {
        writer.write(generateOneLineV2(book));
        writer.flush();
        logger.debug("[生成文件-淘宝csv] 生成数据行 bookId={}, bookName={}", book.getId(), book.getBookName());
    }
    writer.close();
}


public String genDescriptionV2(BookInfoAndDetailV2DTO book){
    if (StringUtils.isBlank(book.getSummary()) && StringUtils.isBlank(book.getCatalog())) {
        StringBuilder builder = new StringBuilder();
        builder.append("ISBN：").append(book.getIsbn()).append("<br/>");
        builder.append("书名：").append(book.getBookName()).append("<br/>");
        builder.append("作者：").append(book.getBookAuthor()).append("<br/>");
        builder.append("版次：").append(book.getBookEdition()).append("<br/>");
        builder.append("出版社：").append(book.getBookPublisher()).append("<br/>");
        builder.append("出版日期：").append(ZisUtils.getDateString("yyyy年MM月", book.getPublishDate())).append("<br/>");
        return builder.toString();
    }
    String summary = formatContent(book.getSummary());
    String catalog = formatContent(book.getCatalog());
    return String.format(DESCRIPTION_FMT, summary, catalog);
}


public String formatContent(String content){
    if (StringUtils.isBlank(content)) {
        return "";
    }
    content = content.replaceAll("\\r", "<br/>");
    content = content.replaceAll("\\n", "<br/>");
    content = content.replaceAll("\\t", "&nbsp;");
    content = content.replaceAll("\"", "&quot;");
    return content;
}


public void generate(List<BookInfoAndDetailDTO> bookList,String[] emails){
    if (bookList == null || bookList.isEmpty()) {
        logger.info("[生成文件-淘宝csv] 未生成任何文件，传入数据为空。");
        return;
    }
    // 生成器基础检查，如果不通过会报错
    basicCheck();
    // 创建临时文件夹
    try {
        String batchId = ZisUtils.getDateString("yyyy-MM-dd_HHmmss");
        String tmpDir = baseDir + batchId;
        String picDir = tmpDir + "/data/";
        FileUtils.forceMkdir(new File(tmpDir));
        FileUtils.forceMkdir(new File(picDir));
        logger.info("[生成文件-淘宝csv] 生成临时目录 {}", tmpDir);
        // 生成csv文件
        generateCSV(bookList, tmpDir + "/data.csv");
        logger.info("[生成文件-淘宝csv] 生成数据文件 {}", tmpDir + "/data.csv");
        // 生成(下载)图片文件
        downloadImage(bookList, picDir);
        logger.info("[生成文件-淘宝csv] 生成图片目录 {}", picDir);
        // 打包
        String destZipFile = tmpDir + ".zip";
        ZipUtils.compress(tmpDir, destZipFile);
        logger.info("[生成文件-淘宝csv] 生成压缩包 {}", tmpDir);
        // 发送邮件
        mailSender.send(emails, "淘宝数据包-" + batchId, "请在附件中下载，解压缩后导入淘宝助理\n\n- - - - - - - - - - - - - - - - -\n本邮件由ZIS系统自动发送", destZipFile);
        logger.info("[生成文件-淘宝csv]  压缩包{} 发送邮件到邮箱列表 {}", destZipFile, ArrayUtils.toString(emails));
        // 清理临时文件
        FileUtils.deleteQuietly(new File(tmpDir));
        FileUtils.deleteQuietly(new File(destZipFile));
        logger.info("[生成文件-淘宝csv] 清理所有临时文件 {}, {}", tmpDir, destZipFile);
    } catch (Exception e) {
        logger.error("[生成文件-淘宝csv] 系统异常，原因为", e);
        try {
            mailSender.send(emails, "生成淘宝数据包失败", "生成淘宝数据包失败！错误原因如下，请联系管理员：\n" + e.getMessage());
        } catch (Exception mailEx) {
            logger.error("[生成文件-淘宝csv] 发送邮件失败，原因为" + mailEx.getMessage(), mailEx);
        }
        throw new RuntimeException(e);
    }
}


public void downloadImage(List<BookInfoAndDetailDTO> bookList,String picDir){
    for (BookInfoAndDetailDTO book : bookList) {
        if (StringUtils.isBlank(book.getImageUrl())) {
            // 跳过无图片的记录
            continue;
        }
        String imageFileName = genImageName(book) + ".tbi";
        try {
            ImageUtils.downloadImg(book.getImageUrl(), picDir, imageFileName);
            // 休眠100毫秒，防止被对方系统拉黑
            ZisUtils.sleepQuietly(100);
            logger.debug("[生成文件-淘宝csv] 成功下载图片 {}，保存到 {}", book.getImageUrl(), picDir);
        } catch (Exception e) {
            logger.error("[生成文件-淘宝csv] 下载图片过程出错", e);
        }
    }
}


}