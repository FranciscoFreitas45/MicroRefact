package org.danyuan.application.crawler.param.service;
 import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.ParseException;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.utils.httpsdownload.HttpUtil;
import org.danyuan.application.crawler.param.dao.SysCrawlerRulerInfoDao;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDao;
@Service
public class SysCrawlerRulerInfoService extends BaseServiceImpl<SysCrawlerRulerInfo>implements BaseService<SysCrawlerRulerInfo>{

@Autowired
 private SysCrawlerRulerInfoDao sysCrawlerRulerInfoDao;


public String stop(List<SysCrawlerRulerInfo> list){
    for (SysCrawlerRulerInfo sysCrawlerRulerInfo : list) {
        sysCrawlerRulerInfo.setStatue("0");
        sysCrawlerRulerInfoDao.save(sysCrawlerRulerInfo);
    }
    return "1";
}


public String start(List<SysCrawlerRulerInfo> list) throws ParseException{
    for (SysCrawlerRulerInfo sysCrawlerRulerInfo : list) {
        if ("0".equals(sysCrawlerRulerInfo.getStatue())) {
            // 启动任务
            Map<String, String> map = new HashMap<>();
            map.put("uuid", sysCrawlerRulerInfo.getUuid());
            map.put("taskUuid", sysCrawlerRulerInfo.getUuid());
            map.put("contentInfo", sysCrawlerRulerInfo.getContentJsonInfo());
            map.put("delete", sysCrawlerRulerInfo.getDeleteFlag() + "");
            map.put("statue", "1");
            HttpUtil.postJson("http://127.0.0.1:3000/crawler", map, "UTF-8");
        }
        sysCrawlerRulerInfo.setStatue("1");
        sysCrawlerRulerInfoDao.save(sysCrawlerRulerInfo);
    }
    return "1";
}


}