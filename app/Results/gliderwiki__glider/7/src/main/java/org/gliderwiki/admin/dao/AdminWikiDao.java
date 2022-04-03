package org.gliderwiki.admin.dao;
 import java.util.Date;
import java.util.List;
import org.gliderwiki.web.domain.WeWiki;
public interface AdminWikiDao {


public int updateWeWikiByAdmin(WeWiki domain)
;

public WeWiki getWikiDetailInfo(Integer weWikiIdx)
;

public List<WeWiki> getWikiListMonth(Date month)
;

public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName)
;

}