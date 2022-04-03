package org.live.common.utils;
 import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.live.common.response.DataTableModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class DataTableUtils {


public DataTableModel parseDataTableModel(Page<?> page,Integer draw,Long recordsTotal){
    // 过滤后的总记录数
    Long recordsFiltered = page.getTotalElements();
    // 数据
    List<?> data = page.getContent();
    DataTableModel model = new DataTableModel(draw, recordsTotal, recordsFiltered, data);
    return model;
}


public JSONObject parseJSONObject(HttpServletRequest request){
    // 请求参数映射成JSONObject类型
    return readJsonObject(request);
}


public Map<String,Object> parseMap(HttpServletRequest request){
    // 将request映射成jsonObject
    JSONObject jsonObject = readJsonObject(request);
    Map<String, Object> params = new HashMap<String, Object>();
    // 出于安全考虑所携带的参数，响应请求时应返回此参数
    Integer draw = jsonObject.getInteger("draw");
    // 用于排序的相关参数
    JSONObject order = jsonObject.getJSONArray("order").getJSONObject(0);
    // 记录起始下标
    Integer start = jsonObject.getInteger("start");
    // 单页记录长度
    Integer length = jsonObject.getInteger("length");
    // 应用于所有列的过虑条件
    JSONObject search = jsonObject.getJSONObject("search");
    // 字段基本信息数据
    JSONArray columns = jsonObject.getJSONArray("columns");
    // 当前页数
    Integer currentPage = (start + length - 1) / length;
    // 排序类型
    Sort.Direction direction = (StringUtils.equals(order.getString("dir"), "asc")) ? Sort.Direction.ASC : Sort.Direction.DESC;
    // 排序根据
    String sortBy = columns.getJSONObject(order.getInteger("column")).getString("name");
    Sort sort = new Sort(direction, sortBy);
    // 映射请求分页请求参数
    PageRequest pageRequest = new PageRequest(currentPage, length, sort);
    // 有序map
    Map<String, Object> filter = new LinkedHashMap<String, Object>();
    // 过滤参数
    String searchVal = search.getString("value");
    if (StringUtils.isNotEmpty(searchVal)) {
        /**
         * 有过滤参数 *
         */
        for (Object object : columns) {
            JSONObject column = (JSONObject) object;
            String name = column.getString("name");
            int index = name.lastIndexOf(".");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            filter.put(name, searchVal);
        }
    }
    params.put("searchVal", searchVal);
    params.put("draw", draw);
    params.put("pageRequest", pageRequest);
    params.put("filter", filter);
    return params;
}


public JSONObject readJsonObject(HttpServletRequest request){
    StringBuilder jsonStr = new StringBuilder();
    String line = null;
    BufferedReader reader = null;
    try {
        reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonStr.append(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return (JSONObject) JSON.parse(jsonStr.toString());
}


}