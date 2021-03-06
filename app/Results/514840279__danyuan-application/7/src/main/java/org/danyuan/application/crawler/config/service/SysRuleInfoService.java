package org.danyuan.application.crawler.config.service;
 import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.ParseException;
import org.danyuan.application.common.utils.httpsdownload.HttpUtil;
import org.danyuan.application.crawler.param.dao.SysCrawlerRulerInfoDao;
import org.danyuan.application.crawler.param.po.SysCrawlerRulerInfo;
import org.danyuan.application.crawler.task.po.SysCrawlerTaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDao;
@Service
public class SysRuleInfoService {

@Autowired
 private SysCrawlerRulerInfoDao sysCrawlerRulerInfoDao;


public String startTask(List<SysCrawlerTaskInfo> list,int i) throws ParseException{
    for (SysCrawlerTaskInfo sysCrawlerTaskInfo : list) {
        SysCrawlerRulerInfo sysCrawlerRulerInfo = new SysCrawlerRulerInfo();
        sysCrawlerRulerInfo.setTaskUuid(sysCrawlerTaskInfo.getUuid());
        Example<SysCrawlerRulerInfo> example = Example.of(sysCrawlerRulerInfo);
        List<SysCrawlerRulerInfo> ruleList = sysCrawlerRulerInfoDao.findAll(example);
        for (SysCrawlerRulerInfo sysCrawlerRulerInfo2 : ruleList) {
            // 去重
            if (!sysCrawlerRulerInfo2.getStatue().equals(i + "")) {
                sysCrawlerRulerInfo2.setStatue(i + "");
                // 修改状态
                sysCrawlerRulerInfoDao.save(sysCrawlerRulerInfo2);
                if (i == 1) {
                    // 启动任务
                    Map<String, String> map = new HashMap<>();
                    map.put("uuid", sysCrawlerRulerInfo2.getUuid());
                    map.put("taskUuid", sysCrawlerRulerInfo2.getUuid());
                    map.put("contentInfo", sysCrawlerRulerInfo2.getContentJsonInfo());
                    map.put("delete", sysCrawlerRulerInfo2.getDeleteFlag() + "");
                    map.put("statue", sysCrawlerRulerInfo2.getStatue());
                    HttpUtil.postJson("http://127.0.0.1:3000/crawler", map, "UTF-8");
                }
            }
        }
    }
    return "1";
}


}