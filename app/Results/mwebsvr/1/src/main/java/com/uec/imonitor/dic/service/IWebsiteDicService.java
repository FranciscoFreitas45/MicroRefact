package com.uec.imonitor.dic.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;
public interface IWebsiteDicService {


public WebsiteDicEntity findWebsiteByName(String name)
;

public WebsiteDicEntity findById(int id)
;

public List<WebsiteDicEntity> listWebsiteClassifi()
;

public List<WebsiteDicEntity> listWebsite()
;

}