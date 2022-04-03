package com.ushahidi.swiftriver.core.solr.util;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
public class QueryUtil {


public String getQueryString(String searchTerms){
    if (searchTerms == null || searchTerms.trim().length() == 0)
        return "*:*";
    List<String> keywordsList = new ArrayList<String>();
    // Sanitize the each keyword
    for (String keyword : searchTerms.split(",")) {
        if (searchTerms.trim().length() > 1) {
            keywordsList.add(keyword.trim());
        }
    }
    String[] keywordsArray = keywordsList.toArray(new String[keywordsList.size()]);
    return keywordsArray.length == 1 ? searchTerms : StringUtils.join(keywordsArray, " AND ");
}


}