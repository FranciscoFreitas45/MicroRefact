package com.uec.imonitor.common.util;
 import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.request.bean.RequestNewsDetail;
public class ESUtil {


public List<Integer> listRecordIdsOfEsStatusList(List<NewsStatusEntity> esStatusList){
    List<Integer> idList = new ArrayList<>();
    if (esStatusList != null && !esStatusList.isEmpty()) {
        for (NewsStatusEntity rs : esStatusList) {
            idList.add(rs.getRecordId());
        }
    }
    return idList;
}


public List<T> parseESHits(SearchHits hits,Class<T> clazz){
    List<T> list = new ArrayList<>();
    // getClass() 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。
    // (Class<T>) persistentClass=(Class<T>)getSuperClassGenricType(getClass(), 0);
    for (SearchHit hit : hits) {
        String json = hit.getSourceAsString();
        T obj = CommonUtil.parseJson(json, clazz);
        // 加入结果集
        list.add(obj);
    }
    return list;
}


@SuppressWarnings("unchecked")
public Class<Object> getSuperClassGenricType(Class clazz,int index){
    // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
    Type genType = clazz.getGenericSuperclass();
    if (!(genType instanceof ParameterizedType)) {
        return Object.class;
    }
    // 返回表示此类型实际类型参数的 Type 对象的数组。
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if (index >= params.length || index < 0) {
        return Object.class;
    }
    if (!(params[index] instanceof Class)) {
        return Object.class;
    }
    return (Class) params[index];
}


public List<Integer> listIdsOfSpreadAnalysisDetailList(List<NewsSpreadingAnalysisDetail> newsSpreadingInsertList){
    List<Integer> idList = new ArrayList<>();
    if (newsSpreadingInsertList != null && !newsSpreadingInsertList.isEmpty()) {
        for (NewsSpreadingAnalysisDetail rs : newsSpreadingInsertList) {
            idList.add(rs.getInnerid());
        }
    }
    return idList;
}


public List<Integer> listIdsOfRequestNewsDetailList(List<RequestNewsDetail> requestNewsInsertList){
    List<Integer> idList = new ArrayList<>();
    if (requestNewsInsertList != null && !requestNewsInsertList.isEmpty()) {
        for (RequestNewsDetail rs : requestNewsInsertList) {
            idList.add(rs.getNewsId());
        }
    }
    return idList;
}


}