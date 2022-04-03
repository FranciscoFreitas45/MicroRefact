package com.zis.bookinfo.util;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.zis.common.util.TextClearUtils;
public class YouLuNetTextUtil {


public String clearBookAuthor(String bookAuthor){
    if (StringUtils.isBlank(bookAuthor)) {
        throw new RuntimeException("illegal argument, bookAuthor should not be null");
    }
    // 替换特殊字符，例如：、 ， ： ； , ; :
    bookAuthor = TextClearUtils.clearSpecialChar(bookAuthor);
    // 译者 大仲马合著 张三 => 大仲马
    bookAuthor = TextClearUtils.subString(bookAuthor, "译者", "原著");
    bookAuthor = TextClearUtils.subString(bookAuthor, "译者", "合著");
    // 译者 大仲马著 张三 => 大仲马
    bookAuthor = TextClearUtils.subString(bookAuthor, "译者", "著");
    // 删除"译者"及之后的内容（前提：不是最开始的）
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "译者");
    // 删除"著……译"及之后的内容（前提：不是最开始的）
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "原著", "译", TextClearUtils.MATCH_MODE_FORCE_END);
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "著", "译", TextClearUtils.MATCH_MODE_FORCE_END);
    // 删除结尾的“等”、“著”、“编”
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "等", null, TextClearUtils.MATCH_MODE_FORCE_END);
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "著", null, TextClearUtils.MATCH_MODE_FORCE_END);
    bookAuthor = TextClearUtils.deleteString(bookAuthor, "编", null, TextClearUtils.MATCH_MODE_FORCE_END);
    bookAuthor = TextClearUtils.deleteString(bookAuthor, " 辑", null, TextClearUtils.MATCH_MODE_FORCE_END);
    // 作者重复，例如“张三 张三”
    bookAuthor = bookAuthor.trim();
    String[] eachAuthor = bookAuthor.split(" ");
    if (eachAuthor.length > 1) {
        List<String> authorList = new ArrayList<String>();
        for (String author : eachAuthor) {
            if (!authorList.contains(author)) {
                authorList.add(author);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < authorList.size(); i++) {
            if (i != 0) {
                builder.append(" ");
            }
            builder.append(authorList.get(i));
        }
        bookAuthor = builder.toString();
    }
    return bookAuthor;
}


}