package org.gliderwiki.admin.dao;
 import java.util.Date;
import java.util.List;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
public interface AdminSpaceDao {


public List<WeSpace> getSpaceListMonth(WeSpace space,Date month)
;

public int updateWikiUseYn(Integer weSpaceIdx,Integer weUserIdx)
;

public List<WeSpace> getSpaceSearchList(WeSpace weSpace)
;

public int updateSpaceByAdmin(WeSpace space)
;

public List<WeWiki> getWikiListInSpace(Integer weSpaceIdx)
;

public List<WeBbs> getBbsSearchList(WeBbs weBbs)
;

public WeSpace getSpaceDetailInfo(Integer weSpaceIdx)
;

}