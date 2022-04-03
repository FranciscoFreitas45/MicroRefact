package com.zis.common.util;
 import org.apache.commons.lang3.StringUtils;
import com.zis.bookinfo.bean.Bookinfo;
public class TextClearUtils {

 public  int MATCH_MODE_NONE;

 public  int MATCH_MODE_FORCE_START;

 public  int MATCH_MODE_FORCE_END;

 public  int MATCH_MODE_FORCE_NONE_START;

 public  int MATCH_MODE_FORCE_NONE_END;


public String cutBookPublisher(String bookPublisher){
    if (bookPublisher.contains("出版社")) {
        bookPublisher = bookPublisher.split("出版社")[0];
    }
    return bookPublisher;
}


public String getShortestBookAuthor(String bookAuthor){
    final String splitChar = " ";
    final int maxLen = 8;
    // 多个作者用空格分隔
    String[] authors = bookAuthor.split(splitChar);
    // 逐个追加作者，直到超过长度限制
    StringBuilder tmpStr = new StringBuilder(authors[0]);
    for (int i = 1; i < authors.length; i++) {
        if (tmpStr.length() + authors[i].length() > maxLen)
            break;
        tmpStr.append(splitChar).append(authors[i]);
    }
    return tmpStr.length() > maxLen ? "" : tmpStr.toString();
}


public String subString(String text,String beginStr,String endStr,int matchMode){
    if (StringUtils.isBlank(text)) {
        throw new RuntimeException("illegal argument, text should not be null");
    }
    if (StringUtils.isBlank(beginStr)) {
        throw new RuntimeException("illegal argument, text beginStr not be null");
    }
    // 强制匹配开始字符
    if (matchMode == MATCH_MODE_FORCE_START) {
        if (!text.startsWith(beginStr)) {
            return text;
        }
    } else // 强制匹配结束字符
    if (matchMode == MATCH_MODE_FORCE_END) {
        if (StringUtils.isBlank(endStr)) {
            throw new RuntimeException("强制匹配结束字符模式下，endStr不能为空");
        }
        if (!text.endsWith(endStr)) {
            return text;
        }
    } else // 强制要求不能匹配开始字符
    if (matchMode == MATCH_MODE_FORCE_NONE_START) {
        if (text.startsWith(beginStr)) {
            return text;
        }
    } else // 强制要求不能匹配结束字符
    if (matchMode == MATCH_MODE_FORCE_NONE_END) {
        if (StringUtils.isBlank(endStr)) {
            throw new RuntimeException("强制要求不能匹配结束字符模式下，endStr不能为空");
        }
        if (text.endsWith(endStr)) {
            return text;
        }
    } else // 不做任何匹配限制
    if (matchMode == MATCH_MODE_NONE) {
    } else {
        throw new RuntimeException("错误的匹配模式：" + matchMode);
    }
    int beginIndex = text.indexOf(beginStr);
    // int beginIndex = text.indexOf(beginStr) + beginStr.length();
    // 有结束字符
    if (StringUtils.isNotBlank(endStr)) {
        int endIndex = text.indexOf(endStr);
        if (beginIndex >= 0 && endIndex >= 0 && endIndex > beginIndex) {
            return text.substring(beginIndex + beginStr.length(), endIndex);
        }
    } else // 没有结束字符
    {
        if (beginIndex >= 0) {
            return text.substring(beginIndex + beginStr.length());
        }
    }
    return text;
}


public String buildTitleFormType(String type,Bookinfo book){
    if ("youzan".equals(type)) {
        return buildYouZanTitle(book);
    } else if ("taobao".equals(type)) {
        return buildTaobaoTitle(book);
    } else {
        return "";
    }
}


public String buildTaobaoTitle(Bookinfo book){
    if (book == null) {
        throw new IllegalArgumentException("构造淘宝标题失败，参数不能为空。");
    }
    String title = buildTitle(book, 120);
    return title;
}


public String getBookEdition(Bookinfo book){
    String bookEdition = book.getBookEdition();
    if (("第一版".equals(bookEdition) || "第1版".equals(bookEdition)) && book.getIsNewEdition() == true) {
        return "";
    } else {
        return "(" + bookEdition + ")";
    }
}


public String buildTitle(Bookinfo book,Integer maxLength){
    StringBuilder sb = new StringBuilder();
    sb.append("二手");
    sb.append(book.getBookName());
    String bookEdition = getBookEdition(book);
    String bookAuthor = getShortestBookAuthor(book.getBookAuthor());
    String bookPublisher = book.getBookPublisher();
    Integer bookEditionLength = bookEdition.getBytes().length;
    Integer bookAuthorLength = bookAuthor.getBytes().length;
    Integer isbnLength = book.getIsbn().getBytes().length;
    Integer bookPublisherLength = bookPublisher.getBytes().length;
    if (sb.toString().getBytes().length + bookEditionLength <= maxLength) {
        sb.append(bookEdition);
    }
    if (sb.toString().getBytes().length + bookAuthorLength <= maxLength) {
        sb.append(bookAuthor);
    }
    if (sb.toString().getBytes().length + bookPublisherLength <= maxLength) {
        sb.append(bookPublisher);
    } else {
        bookPublisher = cutBookPublisher(bookPublisher);
        Integer cutBookPublisherLength = bookPublisher.getBytes().length;
        if (sb.toString().getBytes().length + cutBookPublisherLength <= maxLength) {
            sb.append(bookPublisher);
        }
    }
    if (sb.toString().getBytes().length + isbnLength <= maxLength) {
        sb.append(book.getIsbn());
    }
    return sb.toString();
}


public String buildYouZanTitle(Bookinfo book){
    if (book == null) {
        throw new IllegalArgumentException("构造有赞标题失败，参数不能为空。");
    }
    String title = buildTitle(book, 200);
    return title;
}


public String deleteString(String text,String firstMatchStr,String secondMatchStr,int matchMode){
    if (StringUtils.isBlank(text)) {
        throw new RuntimeException("illegal argument, text should not be null");
    }
    if (StringUtils.isBlank(firstMatchStr)) {
        throw new RuntimeException("illegal argument, firstMatchStr should not be null");
    }
    // 不支持MATCH_MODE_FORCE_START模式
    if (matchMode == MATCH_MODE_FORCE_START) {
        throw new RuntimeException("该方法不支持MATCH_MODE_FORCE_START模式");
    } else // 强制匹配结束字符
    if (matchMode == MATCH_MODE_FORCE_END) {
        if (StringUtils.isBlank(secondMatchStr)) {
            if (!text.endsWith(firstMatchStr)) {
                return text;
            }
        } else {
            if (!text.endsWith(secondMatchStr)) {
                return text;
            }
        }
    } else // 强制要求不能匹配开始字符
    if (matchMode == MATCH_MODE_FORCE_NONE_START) {
        if (text.startsWith(firstMatchStr)) {
            return text;
        }
    } else // 强制要求不能匹配结束字符
    if (matchMode == MATCH_MODE_FORCE_NONE_END) {
        if (StringUtils.isBlank(secondMatchStr)) {
            if (text.endsWith(firstMatchStr)) {
                return text;
            }
        } else {
            if (text.endsWith(secondMatchStr)) {
                return text;
            }
        }
    } else // 不做任何匹配限制
    if (matchMode == MATCH_MODE_NONE) {
    } else {
        throw new RuntimeException("错误的匹配模式：" + matchMode);
    }
    // secondMatchStr 为空
    int endIndex = text.indexOf(firstMatchStr);
    if (StringUtils.isBlank(secondMatchStr)) {
        if (endIndex > 0) {
            // 如果text是以firstMatchStr开始，该方法不会删除
            return text.substring(0, endIndex);
        }
    } else // secondMatchStr 不为空
    {
        int secondEndIndex = text.indexOf(secondMatchStr, endIndex);
        if (endIndex > 0 && secondEndIndex > 0) {
            // 如果text是以firstMatchStr开始，该方法不会删除
            return text.substring(0, endIndex);
        }
    }
    return text;
}


public String clearSpecialChar(String text,String replacement){
    String[] specialCharSet = { "、", "，", "：", "；", "。", ",", ":", ";", ".", "(", ")", "（", "）", "/", "-", "|" };
    for (String character : specialCharSet) {
        text = text.replace(character, replacement);
    }
    return text;
}


}