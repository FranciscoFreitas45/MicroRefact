package com.xwtec.xwserver.controller.tool;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xwtec.xwserver.pojo.tool.CommonNews;
import com.xwtec.xwserver.service.tool.RefreshRedisService;
import com.xwtec.xwserver.util.jedis.JedisUtil;
import com.xwtec.xwserver.util.json.JSONObject;
@Controller
@RequestMapping("refresh")
public class RefreshRedisController {

@Resource
 private RefreshRedisService refreshService;

@Resource
 private JedisUtil jedisUtil;

 private  Logger logger;


@ResponseBody
@RequestMapping("requestDispatcher.action")
public JSONObject requestDispatcher(){
    JSONObject jsobject = new JSONObject();
    try {
        List<CommonNews> commonNewsList = refreshService.getCommonNews();
        List<Map<String, JSONObject>> mapList = new ArrayList<Map<String, JSONObject>>();
        for (int i = 0; i < commonNewsList.size(); i++) {
            // 编号
            String id = commonNewsList.get(i).getNid();
            // 标题
            String title = commonNewsList.get(i).getNtitle();
            // 内容
            String ncontent = commonNewsList.get(i).getNcontent();
            // 时间
            String NPOSTDATE = commonNewsList.get(i).getNpostdate();
            String cateid = commonNewsList.get(i).getCateid();
            JSONObject jsonObject = new JSONObject();
            // 拼写缓存key值
            String key = "APP_COMNEWS_WEB_" + cateid + "_DETAIL_" + id;
            jsonObject.put("ID", id);
            jsonObject.put("TITLE", title);
            jsonObject.put("NCONTENT", ncontent);
            jsonObject.put("NPOSTDATE", NPOSTDATE);
            Map<String, JSONObject> map = new HashMap<String, JSONObject>();
            map.put(key, jsonObject);
            mapList.add(map);
        }
        refreshService.doAction(mapList);
        jsobject.put("status", "success");
        jsobject.put("message", "");
    } catch (Exception e) {
        jsobject.put("status", "error");
        jsobject.put("message", e);
        logger.error(e);
    }
    return jsobject;
}


}