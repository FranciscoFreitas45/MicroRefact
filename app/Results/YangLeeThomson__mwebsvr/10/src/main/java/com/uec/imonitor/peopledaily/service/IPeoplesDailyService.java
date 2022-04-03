package com.uec.imonitor.peopledaily.service;
 import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDaily;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
public interface IPeoplesDailyService {


public Map<String,String> pushDataChannelHour()
;

public Page<PeoplesDailyEntity> pagePeopleNews(DataTablesRequestEntity aoData)
;

public PeoplesDailyEntity findByWebpageCode(String webpageCode)
;

public void dataToAPICloud()
;

public PeoplesDailyEntity peoplesDailyOpera(String webpageCode)
;

public Map<String,String> pushDataOrgHour()
;

public PeoplesDailyEntity saveNewsForMediaTest()
;

public PeoplesDailyEntity verifyArticle(String webpageCode)
;

public void dataToImediaTest()
;

public List<PeoplesDailyEntity> savePeopleDaily(String json)
;

public Map<String,String> groupByOrgAll()
;

public Map<String,String> groupByOrg()
;

public Map<String,String> groupByChannel()
;

public Map<String,String> groupByChannelAll()
;

public String pushDataToCloud(List<String> contentList,String postUrl)
;

public Map<String,String> pushDataChannelHourAll()
;

public void dataToImedia()
;

public Map<String,String> pushDataOrgHourAll()
;

}