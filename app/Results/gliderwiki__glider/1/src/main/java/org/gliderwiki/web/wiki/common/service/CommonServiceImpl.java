package org.gliderwiki.web.wiki.common.service;
 import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeAlarmInfo;
import org.gliderwiki.web.domain.WeBbsComment;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeUserAlarm;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiComment;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.notification.NotificationComponent;
import org.gliderwiki.web.vo.WikiVo;
import org.gliderwiki.web.wiki.common.dao.CommonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.NotificationComponent;
import org.gliderwiki.DTO.WeGroupInfo;
import org.gliderwiki.DTO.WeSpace;
import org.gliderwiki.DTO.WeProfile;
import org.gliderwiki.DTO.WeBbsComment;
import org.gliderwiki.DTO.WeMenu;
import org.gliderwiki.DTO.WeUserAlarm;
import org.gliderwiki.DTO.WeAlarmInfo;
@Service("commonService")
@RemoteProxy(name = "CommonService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CommonServiceImpl implements CommonService{

 private Logger logger;

@Autowired
 private  EntityService entityService;

@Autowired
 private  CommonDao commonDao;

@Resource(name = "notificationComponent")
 private  NotificationComponent notificationComponent;


@Override
public WeWiki getWikiInfo(Integer weWikiIdx){
    WeWiki weWiki = new WeWiki();
    weWiki.setWe_wiki_idx(weWikiIdx);
    weWiki.setWe_use_yn("Y");
    WeWiki rtnObj = (WeWiki) entityService.getRowEntity(weWiki);
    return rtnObj;
}


@RemoteMethod
public List<WeGroupInfo> getGroupList(Integer loginUserIdx){
    WeGroupInfo domain = new WeGroupInfo();
    domain.setWe_use_yn("Y");
    return commonDao.getGroupList(domain);
}


@Override
public List<WeWikiNote> getWeWikiNoteList(Integer weWikiIdx,Integer weWikiRevision){
    WeWikiNote domain = new WeWikiNote();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_use_yn("Y");
    domain.setWe_wiki_revision(weWikiRevision);
    return entityService.getListEntityOrdered(domain, "WE_WIKI_NOTE_NUM");
}


public WeWikiFile getWikiFileInfo(Integer weFileIdx){
    WeWikiFile domain = new WeWikiFile();
    domain.setWe_file_idx(weFileIdx);
    domain.setWe_use_yn("Y");
    WeWikiFile rtnObj = (WeWikiFile) entityService.getRowEntity(domain);
    return rtnObj;
}


@Override
@Transactional(readOnly = false)
public void delWeFile(Integer weFileIdx){
    WeFile domain = new WeFile();
    domain.setWe_file_idx(weFileIdx);
    try {
        entityService.deleteEntity(domain);
    } catch (Exception e) {
        logger.error("We_File 삭제 중  에러 발생 : " + e.getMessage());
    }
}


@Override
public WeGroupInfo getWeGroupInfo(Integer groupIdx){
    WeGroupInfo domain = new WeGroupInfo();
    domain.setWe_group_idx(groupIdx);
    domain.setWe_use_yn("Y");
    return (WeGroupInfo) entityService.getRowEntity(domain);
}


@SuppressWarnings("unchecked")
@Override
@Transactional(readOnly = false)
public int addFavorite(Integer weUserIdx,FavorityType type,int spaceIdx,int wikiIdx){
    WeFavorite searchFavorite = new WeFavorite();
    int cnt;
    if (StringUtils.equals("SPACE", type.name())) {
        searchFavorite.setWe_favorite_type(FavorityType.SPACE);
        searchFavorite.setWe_space_idx(spaceIdx);
        searchFavorite.setWe_user_idx(weUserIdx);
    } else {
        searchFavorite = new WeFavorite(weUserIdx, type, spaceIdx, wikiIdx, null);
    }
    WeFavorite result = (WeFavorite) entityService.getRowEntity(searchFavorite);
    WeFavorite weFavorite = null;
    if (result == null) {
        if (StringUtils.equals("SPACE", type.name())) {
            weFavorite = new WeFavorite(weUserIdx, type, spaceIdx, null);
        } else {
            weFavorite = new WeFavorite(weUserIdx, type, spaceIdx, wikiIdx);
        }
        cnt = entityService.insertEntity(weFavorite);
    } else {
        cnt = commonDao.updateFavorite(result);
    }
    // 위키를 즐겨찾기 했을때만 알림추가
    if (cnt > 0 && StringUtils.equals("WIKI", type.name())) {
        WeWiki weWiki = getWikiInfo(wikiIdx);
        WeUser weUser = getUserInfo(weUserIdx);
        requestAlarmInfo(weUserIdx, weUser.getWe_user_nick(), SystemConst.WIKI_FAVORITE, weWiki.getWe_ins_user(), wikiIdx, null);
    }
    return cnt;
}


@Override
@RemoteMethod
@Transactional(readOnly = false)
public int delRelationDWR(Integer weUserIdx,Integer targetUserIdx){
    WeAddFriend weAddFriend = new WeAddFriend();
    weAddFriend.setWe_user_idx(weUserIdx);
    weAddFriend.setWe_target_user_idx(targetUserIdx);
    return commonDao.delRelationDWR(weAddFriend);
}


@RemoteMethod
public WeSpace getWikiSpaceInfoDWR(Integer weWikiSpaceIdx){
    WeSpace wikiSpace = new WeSpace();
    wikiSpace.setWe_space_idx(weWikiSpaceIdx);
    wikiSpace.setWe_use_yn("Y");
    WeSpace rtnObj = (WeSpace) entityService.getRowEntity(wikiSpace);
    return rtnObj;
}


@Override
public WeWikiGraph getWeWikiGraph(Integer weWikiIdx,Integer weWikiRevision){
    // TODO Auto-generated method stub
    WeWikiGraph domain = new WeWikiGraph();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_wiki_revision(weWikiRevision);
    domain.setWe_use_yn("Y");
    return (WeWikiGraph) entityService.getRowEntity(domain);
}


@Override
public WeTemplate getTemplateByIdx(Integer weTemplateIdx){
    WeTemplate domain = new WeTemplate();
    domain.setWe_template_idx(weTemplateIdx);
    domain.setWe_use_yn("Y");
    return (WeTemplate) entityService.getRowEntity(domain);
}


@Override
public List<WeWikiSummary> getWeWikiSummaryList(Integer weWikiIdx,Integer weWikiRevision){
    WeWikiSummary domain = new WeWikiSummary();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_use_yn("Y");
    domain.setWe_wiki_revision(weWikiRevision);
    return entityService.getListEntityOrdered(domain, "WE_SUMMARY_IDX");
}


@Override
public List<WikiVo> getWikiSearchList(String wiki_text){
    return commonDao.getWikiSearchList(wiki_text);
}


@Override
@Transactional(readOnly = false)
public int insertWeFile(WeFile weFile){
    int result = 0;
    try {
        result = entityService.insertEntity(weFile);
    } catch (Exception e) {
        logger.error("이미지 파일 업로드 중 에러 발생 : " + e.getMessage());
        result = -1;
    }
    return result;
}


@Override
public WeWikiComment getWeWikiComment(String weWikiCommentIdx){
    WeWikiComment domain = new WeWikiComment();
    domain.setWe_wiki_comment_idx(Integer.parseInt(weWikiCommentIdx));
    domain.setWe_use_yn("Y");
    return (WeWikiComment) entityService.getRowEntity(domain);
}


public WeProfile getUserProfileInfo(Integer weUserIdx){
    WeProfile weProfile = new WeProfile(null, null);
    weProfile.setWe_user_idx(weUserIdx);
    WeProfile rtnObj = (WeProfile) entityService.getRowEntity(weProfile);
    return rtnObj;
}


@Override
public String realNotiView(int userIdx){
    try {
        return commonDao.realNotiView(userIdx);
    } catch (Throwable e) {
        throw new GliderwikiException("실시간알림여부 가져오는 도중 에러발생!", e);
    }
}


@Override
@Transactional(readOnly = false)
public void changeRealTimeView(int userIdx,String isView){
    commonDao.changeRealTimeView(userIdx, isView);
}


@Override
public List<WeWikiTag> getWeWikiTagList(Integer weWikiIdx,Integer weWikiRevision){
    WeWikiTag domain = new WeWikiTag();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_use_yn("Y");
    domain.setWe_wiki_revision(weWikiRevision);
    return entityService.getListEntityOrdered(domain, "WE_WIKI_TAG_IDX");
}


@Override
@RemoteMethod
@Transactional(readOnly = false)
public int delFavorite(Integer weUserIdx,FavorityType type,Integer addIdx){
    logger.debug("###FavorityType.SPACE : " + FavorityType.SPACE);
    logger.debug("###type : " + type);
    WeFavorite weFavorite = null;
    if (FavorityType.SPACE == type) {
        weFavorite = new WeFavorite(weUserIdx, type, addIdx, null);
    } else {
        weFavorite = new WeFavorite(weUserIdx, type, null, addIdx);
    }
    weFavorite.setWe_del_date(new Date());
    weFavorite.setWe_use_yn("N");
    return commonDao.delFavorite(weFavorite);
}


@Override
@RemoteMethod
@Transactional(readOnly = false)
public int delFavoriteDWR(Integer weUserIdx,String type,Integer addIdx){
    logger.debug("###FavorityType.SPACE : " + FavorityType.SPACE);
    logger.debug("###type : " + type);
    if (FavorityType.SPACE == FavorityType.valueOf(type)) {
        return this.delFavorite(weUserIdx, FavorityType.SPACE, addIdx);
    } else {
        return this.delFavorite(weUserIdx, FavorityType.WIKI, addIdx);
    }
}


public String getAlarmText(Integer we_meta_idx,Integer we_login_user_idx,String we_user_nick,Integer we_wiki_idx,Integer we_space_idx){
    String appendText = "";
    if (we_meta_idx == SystemConst.WIKI_EDIT) {
        // 수정한 사용자의 닉네임, 대상 위키 정보 필요
        logger.debug("@@@@ 내가 작성한 위키가 수정 되었을 때");
        WeWiki wiki = this.getWikiInfo(we_wiki_idx);
        appendText = we_user_nick + "님이  위키(" + wiki.getWe_wiki_title() + ") 를 수정했습니다.";
    } else if (we_meta_idx == SystemConst.SPACE_NEW_POST) {
        // 작성한 사용자의 닉네임,
        // 대상 공간 정보 필요
        logger.debug("@@@@ 내가 개설한  공간에 새 글이 추가 될 때");
        WeSpace space = this.getWikiSpaceInfoDWR(we_space_idx);
        appendText = we_user_nick + "님이 공간(" + space.getWe_space_name() + ") 에 새 글을 작성 했습니다.";
    } else if (we_meta_idx == SystemConst.WIKI_FAVORITE) {
        // 즐겨찾기 한 사용자의
        // 닉네임, 대상 위키 정보
        // 필요
        logger.debug("@@@@ 내 위키를 다른 사람이 즐겨 찾기 했을 때");
        WeWiki wiki = this.getWikiInfo(we_wiki_idx);
        appendText = we_user_nick + "님이  위키(" + wiki.getWe_wiki_title() + ") 를 즐겨찾기에 추가 했습니다.";
    } else if (we_meta_idx == SystemConst.WIKI_AGREE) {
        // 공감한 사용자의 닉네임, 대상
        // 위키 정보 필요
        logger.debug("@@@@ 내 위키를 다른 사람이 공감 했을 때");
        WeWiki wiki = this.getWikiInfo(we_wiki_idx);
        appendText = we_user_nick + "님이  위키(" + wiki.getWe_wiki_title() + ") 에 공감하기를 클릭했습니다.";
    } else if (we_meta_idx == SystemConst.WIKI_ADD_FRIEND) {
        // 인맥 추가한 사용자의
        // 닉네임정보 필요
        logger.debug("@@@@ 나를 인맥에 추가 했을 때 ");
        appendText = we_user_nick + "님이  관심인맥으로 추가 했습니다.";
    } else if (we_meta_idx == SystemConst.SPACE_JOIN) {
        // 가입 신청 한 사용자의 닉네임
        // 및 대상 공간 정보 필요
        logger.debug("@@@@ 내가 개설한 공간에 가입 신청 했을때");
        WeSpace space = this.getWikiSpaceInfoDWR(we_space_idx);
        appendText = we_user_nick + "님이 공간(" + space.getWe_space_name() + ") 에 가입 신청을 했습니다.";
    } else if (we_meta_idx == SystemConst.SPACE_INVITE) {
        // 초대한 사용자의 닉네임 및
        // 공간 정보 필요
        logger.debug("@@@@ 다른 공간에서 나를 초대했을때");
        WeSpace space = this.getWikiSpaceInfoDWR(we_space_idx);
        appendText = we_user_nick + "님이 공간(" + space.getWe_space_name() + ") 에 초대 하였습니다.";
    }
    return appendText;
}


@Override
@RemoteMethod
public List<WeUser> getWeUserList(Integer loginUserIdx,String userNick,String userEmail,String userName){
    WeUser domain = new WeUser();
    domain.setWe_user_auth_yn("Y");
    if (userNick != null && !"".equals(userNick)) {
        domain.setWe_user_nick(userNick);
    } else if (userEmail != null && !"".equals(userEmail)) {
        domain.setWe_user_id(userEmail);
    } else if (userName != null && !"".equals(userName)) {
        domain.setWe_user_name(userName);
    }
    // TODOLIST 추후 사용자 닉네임, 이메일 등으로 like 검색 한다. loginUserIdx 제외한다.
    return entityService.getListEntityOrdered(domain, "WE_USER_JOIN_DATE");
}


@Override
@Transactional(readOnly = false)
public void updateAllRead(int userIdx){
    commonDao.notiAllRead(userIdx);
}


@Override
public WeBbsComment getWeBbsComment(String weBbsCommentIdx){
    WeBbsComment domain = new WeBbsComment();
    domain.setWe_bbs_comment_idx(Integer.parseInt(weBbsCommentIdx));
    domain.setWe_use_yn("Y");
    return (WeBbsComment) entityService.getRowEntity(domain);
}


public WeFile getUserFileInfo(Integer weFileIdx){
    WeFile weFile = new WeFile();
    weFile.setWe_file_idx(weFileIdx);
    WeFile rtnObj = (WeFile) entityService.getRowEntity(weFile);
    return rtnObj;
}


@Override
public List<WeWikiLink> getWeWikiLinkList(Integer weWikiIdx,Integer weWikiRevision){
    WeWikiLink domain = new WeWikiLink();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_use_yn("Y");
    domain.setWe_wiki_revision(weWikiRevision);
    return entityService.getListEntityOrdered(domain, "WE_LINK_IDX");
}


@Override
public WeMenu getMenuInfo(Integer we_menu_idx){
    WeMenu domain = new WeMenu();
    domain.setWe_menu_idx(we_menu_idx);
    domain.setWe_use_yn("Y");
    return (WeMenu) entityService.getRowEntity(domain);
}


public WeUser getUserInfo(Integer weUserIdx){
    WeUser weUser = new WeUser(weUserIdx, "Y");
    WeUser rtnObj = (WeUser) entityService.getRowEntity(weUser);
    return rtnObj;
}


@Override
@Transactional(readOnly = false)
public void updateUserPoint(Integer we_ins_user,int point){
    WeProfile domain = new WeProfile();
    domain.setWe_user_idx(we_ins_user);
    domain.setWe_point(point);
    commonDao.updateUserPoint(domain);
}


@Override
public StringBuffer getHtmlSourceByWikiIdx(Integer we_wiki_idx){
    WeWiki domain = new WeWiki();
    domain.setWe_wiki_idx(we_wiki_idx);
    domain.setWe_use_yn("Y");
    // wiki body 조회
    WeWiki wikiHtml = (WeWiki) entityService.getRowEntity(domain);
    logger.debug("### wikiHtml : " + wikiHtml.getWe_wiki_markup());
    StringBuffer html = new StringBuffer();
    html.append("<!DOCTYPE html>\n");
    html.append("<html lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ko\">\n");
    html.append("<head>\n");
    html.append("<title>::Enterprise OpenSource Wiki – GLiDER™ </title>\n");
    html.append("<meta charset=\"utf-8\">\n");
    html.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n");
    html.append("<meta http-equiv=\"Expires\" content=\"0\"/>\n");
    html.append("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\n");
    html.append("<link rel=\"stylesheet\" href=\"/resource/glider/front/css/wiki.css\" />\n");
    html.append("<script type=\"text/javascript\" src=\"/resource/glider/code/js/shCore.js\"></script>\n");
    html.append("<script type=\"text/javascript\" src=\"/resource/glider/code/js/shBrushJScript.js\"></script>\n");
    html.append("<link href=\"/resource/glider/code/css/shCore.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
    html.append("<link href=\"/resource/glider/code/css/shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
    html.append("</head>\n");
    html.append("<body>\n");
    html.append("<section class=\"contents\" role=\"main\">\n");
    html.append("    <div class=\"wrap-cont\">\n");
    html.append("	 <h2 class=\"tit-cont\">" + wikiHtml.getWe_wiki_title() + "</h2>\n");
    html.append("	 <div class=\"body-cont wiki\" id=\"wikiViewArea\">\n");
    html.append("	 <article class=\"viewer\" style=\"word-wrap :break-word\">\n");
    html.append(wikiHtml.getWe_wiki_markup());
    html.append("	 </article>\n");
    html.append("	 </div>\n");
    html.append("	 </div>\n");
    html.append("</section>\n");
    html.append("</body>\n");
    html.append("</html>\n");
    return html;
}


@Override
@Transactional(readOnly = false)
public int requestAlarmInfo(Integer we_login_user_idx,String we_user_nick,Integer we_meta_idx,Integer we_target_user_idx,Integer we_wiki_idx,Integer we_space_idx){
    logger.debug("########## 알람 대상인지 파악한다 ###########");
    // 사용자가 선택한 알람에 대상 지시가 선택 되어있는지 조회한다.
    WeUserAlarm alarmDomain = new WeUserAlarm();
    alarmDomain.setWe_user_idx(we_target_user_idx);
    alarmDomain.setWe_meta_idx(we_meta_idx);
    alarmDomain.setWe_use_yn("Y");
    int returnResult = 0;
    int alarmCount = entityService.getCountEntity(alarmDomain);
    // 알람 지시 타입이 있을 경우 타입에 따라 알람 정보 테이블에 저장해준다.
    if (alarmCount > 0) {
        logger.debug("########## 알람 대상일 경우 알람 테이블에 저장한다 ###########");
        WeAlarmInfo insertAlarmDomain = new WeAlarmInfo();
        insertAlarmDomain.setWe_meta_idx(we_meta_idx);
        insertAlarmDomain.setWe_alarm_type("E");
        insertAlarmDomain.setWe_user_idx(we_login_user_idx);
        insertAlarmDomain.setWe_target_user_idx(we_target_user_idx);
        String alermText = this.getAlarmText(we_meta_idx, we_login_user_idx, we_user_nick, we_wiki_idx, we_space_idx);
        insertAlarmDomain.setWe_alarm_text(alermText);
        insertAlarmDomain.setWe_use_yn("Y");
        insertAlarmDomain.setWe_ins_date(DateUtil.getTodayTime());
        insertAlarmDomain.setWe_ins_user(we_login_user_idx);
        insertAlarmDomain.setWe_space_idx(we_space_idx);
        insertAlarmDomain.setWe_wiki_idx(we_wiki_idx);
        try {
            returnResult = entityService.insertEntity(insertAlarmDomain);
            // 실시간 알림추가
            if (we_login_user_idx != we_target_user_idx) {
                notificationComponent.sendNotification(we_target_user_idx, alermText);
            }
        } catch (Exception e) {
            returnResult = -1;
            e.printStackTrace();
        }
    } else {
        // 정상 처리
        returnResult = 0;
    }
    return returnResult;
}


@Override
public List<WeWikiFile> getWeWikiFileList(Integer weWikiIdx,Integer weWikiRevision){
    WeWikiFile domain = new WeWikiFile();
    domain.setWe_wiki_idx(weWikiIdx);
    domain.setWe_use_yn("Y");
    domain.setWe_wiki_revision(weWikiRevision);
    return entityService.getListEntityOrdered(domain, "WE_FILE_IDX");
}


}