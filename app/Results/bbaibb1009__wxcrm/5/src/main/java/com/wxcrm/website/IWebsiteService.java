package com.wxcrm.website;
 import java.util.List;
import com.wxcrm.util.Page;
public interface IWebsiteService {


public Page queryWebsite(WcWebsite webSite)
;

public List<WcWebsite> getWebSiteByAdminId(Integer wadId)
;

public WcWebsite getWebSiteById(Integer id)
;

public void updWebSite(WcWebsite webSite)
;

public WcWebsite queryMySiteByAdmin(Integer wadId)
;

public void addWebsite(WcWebsite webSite)
;

}