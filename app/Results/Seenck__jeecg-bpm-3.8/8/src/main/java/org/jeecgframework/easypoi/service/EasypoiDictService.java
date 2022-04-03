package org.jeecgframework.easypoi.service;
 import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.dao.JeecgDictDao;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("easypoiDictService")
public class EasypoiDictService implements EasypoiDictServiceI{

 private  Logger log;

@Autowired
 private  JeecgDictDao jeecgDictDao;

@Autowired
 private  MutiLangServiceI mutiLangService;


public String[] queryDict(String dicTable,String dicCode,String dicText){
    List<String> dictReplace = new ArrayList<String>();
    ;
    List<DictEntity> dictList = null;
    // step.1 如果没有字典表则使用系统字典表
    if (StringUtil.isEmpty(dicTable)) {
        dictList = jeecgDictDao.querySystemDict(dicCode);
    } else {
        try {
            dicText = StringUtil.isEmpty(dicText, dicCode);
            dictList = jeecgDictDao.queryCustomDict(dicTable, dicCode, dicText);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
    for (DictEntity t : dictList) {
        dictReplace.add(mutiLangService.getLang(t.getTypename()) + "_" + t.getTypecode());
    }
    if (dictReplace != null && dictReplace.size() != 0) {
        return dictReplace.toArray(new String[dictReplace.size()]);
    }
    return null;
}


}