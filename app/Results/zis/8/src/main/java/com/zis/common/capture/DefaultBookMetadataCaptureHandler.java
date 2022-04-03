package com.zis.common.capture;
 import org.apache.commons.lang3.StringUtils.isBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.common.util.CosineSimilarAlgorithm;
import com.zis.common.util.ImageUtils;
public class DefaultBookMetadataCaptureHandler {

 private  Map<String,BookMetadataCapture> captureMap;

 private  String baseDir;

 private  ThreadLocal<String> local;

 private  Logger logger;


public String shortStr(String str1,String str2){
    if (isBlank(str1)) {
        return str2;
    }
    if (isBlank(str2)) {
        return str1;
    }
    return str1.length() > str2.length() ? str2 : str1;
}


public void setBaseDir(String baseDir){
    this.baseDir = baseDir;
}


public BookMetadata captureDetailPage(String itemId,String bookMetadataSource){
    if (isBlank(itemId) || isBlank(bookMetadataSource)) {
        throw new IllegalArgumentException("抓取数据失败，参数不能为空");
    }
    BookMetadataCapture capture = captureMap.get(bookMetadataSource);
    if (capture == null) {
        throw new BookMetadataCaptureException("抓取数据失败，没有找到对应的Capture:" + bookMetadataSource);
    }
    return capture.captureDetailPage(itemId);
}


public BookMetadata chooseBestResult(List<BookMetadata> metaList){
    if (metaList == null || metaList.isEmpty()) {
        return null;
    }
    if (metaList.size() == 1) {
        return metaList.get(0);
    }
    // 初始化临时的图片目录
    String tmpDir = baseDir + System.currentTimeMillis() + "/";
    local.set(tmpDir);
    // 最终结果
    BookMetadata metaFinal = metaList.get(0);
    for (int i = 1; i < metaList.size(); i++) {
        BookMetadata mt = metaList.get(i);
        // 选择最短的有效书名
        metaFinal.setName(shortStr(metaFinal.getName(), mt.getName()));
        // 选择非空的版次
        metaFinal.setEdition(notnullStr(metaFinal.getEdition(), mt.getEdition()));
        // 选择非空的作者名称
        metaFinal.setAuthor(notnullStr(metaFinal.getAuthor(), mt.getAuthor()));
        // 选择非空的出版社
        metaFinal.setPublisher(longStr(metaFinal.getPublisher(), mt.getPublisher()));
        // 选择非空的出版日期
        metaFinal.setPublishDate(notnullDate(metaFinal.getPublishDate(), mt.getPublishDate()));
        // 选择非空的ISBN
        metaFinal.setIsbnCode(notnullStr(metaFinal.getIsbnCode(), mt.getIsbnCode()));
        // 选择非空的摘要
        metaFinal.setSummary(notnullStr(metaFinal.getSummary(), mt.getSummary()));
        // 选择非空的目录
        metaFinal.setCatalog(notnullStr(metaFinal.getCatalog(), mt.getCatalog()));
        // 选择最高价格
        metaFinal.setPrice(maxDouble(metaFinal.getPrice(), mt.getPrice()));
        // 选择最大的图片
        metaFinal.setImageUrl(maxFile(metaFinal, mt));
    }
    // 清理临时图片目录
    FileUtils.deleteQuietly(new File(local.get()));
    return metaFinal;
}


public boolean isSameBook(List<BookMetadata> list){
    if (list == null || list.isEmpty()) {
        throw new IllegalArgumentException("illegal argument, for empty list");
    }
    // 只有一本，返回true
    if (list.size() == 1) {
        return true;
    }
    BookMetadata target = list.get(0);
    for (int i = 1; i < list.size(); i++) {
        BookMetadata compare = list.get(i);
        if (!compare.getIsbnCode().equals(target.getIsbnCode())) {
            return false;
        }
        if (!compare.getPublisher().equals(target.getPublisher())) {
            return false;
        }
        if (!compare.getEdition().equals(target.getEdition())) {
            return false;
        }
        if (CosineSimilarAlgorithm.getSimilarity(compare.getName(), target.getName()) < 0.5) {
            return false;
        }
    }
    return true;
}


public void setCaptureMap(Map<String,BookMetadataCapture> captureMap){
    this.captureMap = captureMap;
}


public Double maxDouble(Double d1,Double d2){
    if (d1 == null) {
        return d2;
    }
    if (d2 == null) {
        return d1;
    }
    return d1.compareTo(d2) > 0 ? d1 : d2;
}


public String maxFile(BookMetadata m1,BookMetadata m2){
    if (isBlank(m1.getImageUrl())) {
        return m2.getImageUrl();
    }
    if (isBlank(m2.getImageUrl())) {
        return m1.getImageUrl();
    }
    String m1Path = m1.getSource() + m1.getOutId();
    File f1 = new File(local.get() + m1Path);
    FileChannel fc1 = null;
    long size1 = 0;
    try {
        if (!f1.exists()) {
            ImageUtils.downloadImg(m1.getImageUrl(), local.get(), m1Path);
        }
        fc1 = new FileInputStream(f1).getChannel();
        size1 = fc1.size();
    } catch (Exception e) {
        return m2.getImageUrl();
    } finally {
        try {
            fc1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String m2Path = m2.getSource() + m2.getOutId();
    File f2 = new File(local.get() + m2Path);
    FileChannel fc2 = null;
    long size2 = 0;
    try {
        if (!f2.exists()) {
            ImageUtils.downloadImg(m2.getImageUrl(), local.get(), m2Path);
        }
        fc2 = new FileInputStream(f2).getChannel();
        size2 = fc2.size();
    } catch (Exception e) {
        return m1.getImageUrl();
    } finally {
        try {
            fc2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return size1 > size2 ? m1.getImageUrl() : m2.getImageUrl();
}


public String longStr(String str1,String str2){
    if (isBlank(str1)) {
        return str2;
    }
    if (isBlank(str2)) {
        return str1;
    }
    return str1.length() > str2.length() ? str1 : str2;
}


public String notnullStr(String str1,String str2){
    if (isBlank(str1)) {
        return str2;
    }
    if (isBlank(str2)) {
        return str1;
    }
    return str1;
}


public BookMetadata captureListPage(String keyword){
    if (isBlank(keyword)) {
        throw new IllegalArgumentException("抓取数据失败，参数不能为空");
    }
    List<BookMetadata> metaList = new ArrayList<BookMetadata>();
    for (Entry<String, BookMetadataCapture> entry : captureMap.entrySet()) {
        List<BookMetadata> list = entry.getValue().captureListPage(keyword);
        // 无记录，则跳过，自动尝试抓取其他网站
        if (list == null || list.isEmpty()) {
            logger.info("[数据抓取-{}] 未找到记录，keyword={}", entry.getKey(), keyword);
            continue;
        }
        // 存在多条记录，并且不是同一本书，则跳过，自动尝试抓取其他网站
        if (list.size() > 1 && !isSameBook(list)) {
            logger.info("[数据抓取-{}] 存在多条记录，keyword={}", entry.getKey(), keyword);
            continue;
        }
        metaList.addAll(list);
    }
    // 采集结束，比较各属性，选择最优记录入库
    return chooseBestResult(metaList);
}


public Date notnullDate(Date d1,Date d2){
    if (d1 == null) {
        return d2;
    }
    if (d2 == null) {
        return d1;
    }
    return d1;
}


}