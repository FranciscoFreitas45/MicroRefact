package org.gliderwiki.admin.service;
 import java.util.Date;
import java.util.List;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminSpaceDao;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
@Service("adminSpaceService")
@RemoteProxy(name = "AdminSpaceService")
public class AdminSpaceServiceImpl implements AdminSpaceService{

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  AdminSpaceDao adminSpaceDao;

@Autowired
 private  CommonService commonService;


@Override
public List<WeSpace> getSpaceListMonth(WeSpace space,Date month){
    return adminSpaceDao.getSpaceListMonth(space, month);
}


@Override
public List<WeSpace> getSpaceSearchList(WeSpace weSpace){
    return adminSpaceDao.getSpaceSearchList(weSpace);
}


@Override
@RemoteMethod
public int updateSpaceByAdmin(Integer spaceIdx,String spaceName,String spaceDesc,Integer spaceAdminIdx,String spaceExposed,Integer weUserIdx){
    WeSpace space = new WeSpace();
    space.setWe_space_name(spaceName);
    space.setWe_space_idx(spaceIdx);
    space.setWe_space_desc(spaceDesc);
    space.setWe_admin_idx(spaceAdminIdx);
    space.setWe_space_exposed(spaceExposed);
    space.setWe_upd_user(weUserIdx);
    return adminSpaceDao.updateSpaceByAdmin(space);
}


@Override
@RemoteMethod
public Object[] deleteSpaceInfo(Integer weSpaceIdx,Integer weUserIdx){
    WeSpace space = commonService.getWikiSpaceInfoDWR(weSpaceIdx);
    space.setWe_use_yn("N");
    space.setWe_upd_user(weUserIdx);
    space.setWe_upd_date(new Date());
    int result = entityService.updateEntity(space);
    if (result > 0) {
        adminSpaceDao.updateWikiUseYn(weSpaceIdx, weUserIdx);
    }
    Object[] returnArrayObject = new Object[2];
    // ??????
    returnArrayObject[0] = result;
    // ??????
    returnArrayObject[1] = weSpaceIdx;
    return returnArrayObject;
}


@Override
public List<WeBbs> getBbsSearchList(WeBbs weBbs){
    return adminSpaceDao.getBbsSearchList(weBbs);
}


@Override
@RemoteMethod
public Object[] getSpaceDetailInfo(Integer weSpaceIdx,String attrId){
    // 1. ?????? ?????? ?????? ??????
    WeSpace weSpace = adminSpaceDao.getSpaceDetailInfo(weSpaceIdx);
    logger.debug("weSpace : " + weSpace.getWe_view_privacy().toString());
    // 2. ????????? ?????? ?????? ??????
    List<WeWiki> wikiList = adminSpaceDao.getWikiListInSpace(weSpaceIdx);
    // 3. ????????? ?????? ????????? ?????? (??????)
    // 4. ????????? ?????? ????????? ????????? ????????? (??????)
    Object[] returnArrayObject = new Object[5];
    // ?????? ??????
    returnArrayObject[0] = weSpace;
    // ?????? ??????
    returnArrayObject[1] = wikiList;
    returnArrayObject[2] = attrId;
    returnArrayObject[3] = weSpace.getWe_view_privacy().toString();
    returnArrayObject[4] = weSpace.getWe_edit_privacy().toString();
    return returnArrayObject;
}


}