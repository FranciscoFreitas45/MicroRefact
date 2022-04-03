package org.gliderwiki.admin.service;
 import java.util.Date;
import java.util.List;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
public interface AdminSpaceService {


public List<WeSpace> getSpaceListMonth(WeSpace space,Date month)
;

public List<WeSpace> getSpaceSearchList(WeSpace weSpace)
;

public int updateSpaceByAdmin(Integer spaceIdx,String spaceName,String spaceDesc,Integer spaceAdminIdx,String spaceExposed,Integer weUserIdx)
;

public Object[] deleteSpaceInfo(Integer weSpaceIdx,Integer weUserIdx)
;

public List<WeBbs> getBbsSearchList(WeBbs weBbs)
;

public Object[] getSpaceDetailInfo(Integer weSpaceIdx,String attrId)
;

}