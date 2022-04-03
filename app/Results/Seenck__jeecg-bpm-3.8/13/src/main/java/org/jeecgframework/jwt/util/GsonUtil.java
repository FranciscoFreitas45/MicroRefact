package org.jeecgframework.jwt.util;
 import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
public class GsonUtil {

 private  Gson gson;


public String toJson(Object object){
    return gson.toJson(object);
}


public List<Map<String,T>> toListMap(String json,Class<T> clz){
    Type type = new TypeToken<List<Map<String, T>>>() {
    }.getType();
    return gson.fromJson(json, type);
}


public Map<String,T> toMap(String json,Class<T> clz){
    Type type = new TypeToken<Map<String, T>>() {
    }.getType();
    return gson.fromJson(json, type);
}


public T fromJson(String json,Class<T> clz){
    return gson.fromJson(json, clz);
}


public List<T> fromJsonList(String json,Class<T> cls){
    List<T> mList = new ArrayList<T>();
    JsonArray array = new JsonParser().parse(json).getAsJsonArray();
    Gson mGson = new Gson();
    for (final JsonElement elem : array) {
        mList.add(mGson.fromJson(elem, cls));
    }
    return mList;
}


public List<T> jsonToList(String json,Class<T> clz){
    Type type = new TypeToken<List<T>>() {
    }.getType();
    return gson.fromJson(json, type);
}


}