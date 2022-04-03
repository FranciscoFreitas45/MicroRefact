package com.zis.common.excel;
 import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.common.util.ZisUtils;
public class TaobaoItemCsvWriter {

 private  BufferedOutputStream bos;

 private  String header;

 private  String dataFmt;

 private  String describFmt;

public TaobaoItemCsvWriter(String fileName) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileName);
    this.bos = new BufferedOutputStream(fos);
    bos.write(header.getBytes());
    bos.flush();
}
public void writeTaobaoCsv(BookMetadata bi){
    bos.write(getItemData(bi));
    bos.flush();
}


public byte[] getItemData(BookMetadata bi){
    // 标题使用 书名 + 作者 + 条形码 + 出版社
    String title = "二手" + bi.getName() + " " + bi.getAuthor();
    title = appendIfAllow(title, bi.getIsbnCode());
    title = appendIfAllow(title, bi.getPublisher());
    String content = String.format(describFmt, bi.getSummary(), bi.getCatalog());
    StringBuilder builder = new StringBuilder();
    // \"46602357,46398806,2043189,2043183,122216620,1636953,2043193\"
    // \"%2$s,%3$s,%4$s,%2$s,%5$s,%6$s,%7$s\"
    // 书名，price，作者，书名，出版社，isbn，出版日
    String author = StringUtils.isBlank(bi.getAuthor()) ? " " : bi.getAuthor();
    String publisher = StringUtils.isBlank(bi.getPublisher()) ? " " : bi.getPublisher();
    String pubDate = bi.getPublishDate() == null ? " " : ZisUtils.getDateString("yyyy年MM月", bi.getPublishDate());
    String data = String.format(dataFmt, title, bi.getName(), bi.getPrice(), author, publisher, bi.getIsbnCode(), pubDate, content, bi.getSummary());
    builder.append(data);
    return builder.toString().getBytes();
}


public byte[] getBookInfo(BookMetadata bi){
    // 标题使用 书名 + 作者 + 条形码 + 出版社
    String title = "二手" + bi.getName() + " " + bi.getAuthor();
    title = appendIfAllow(title, bi.getIsbnCode());
    title = appendIfAllow(title, bi.getPublisher());
    StringBuilder builder = new StringBuilder();
    builder.append(bi.getIsbnCode());
    builder.append(",\"" + bi.getName() + "\"");
    builder.append(",\"" + title + "\"");
    builder.append(",\"" + bi.getAuthor() + "\"");
    builder.append(",\"" + bi.getPublisher() + "\"");
    builder.append(",\"" + bi.getPrice() + "\"\r\n");
    return builder.toString().getBytes();
}


public String appendIfAllow(String title,String append){
    if ((title + " " + append).getBytes().length <= 60) {
        return title + " " + append;
    } else {
        return title;
    }
}


public void close(){
    this.bos.close();
}


public void write(List<BookMetadata> biList){
    for (BookMetadata bi : biList) {
        this.writeTaobaoCsv(bi);
    }
}


public void writeBookInfo(BookMetadata bi){
    bos.write(getBookInfo(bi));
    bos.flush();
}


public void writeBookInfos(List<BookMetadata> biList){
    for (BookMetadata bi : biList) {
        this.writeBookInfo(bi);
    }
}


}