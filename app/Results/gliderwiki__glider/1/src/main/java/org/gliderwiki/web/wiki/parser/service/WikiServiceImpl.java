package org.gliderwiki.web.wiki.parser.service;
 import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.GliderTagParser;
import org.gliderwiki.util.GliderTagPaserUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiComment;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiLog;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.dao.WikiDao;
import org.gliderwiki.web.wiki.parser.dao.WikiRegistDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.DTO.WeWikiTag;
import org.gliderwiki.DTO.WeWikiBak;
import org.gliderwiki.DTO.WeWikiFile;
import org.gliderwiki.DTO.WeFile;
import org.gliderwiki.DTO.WeWikiLog;
@Service("wikiRegistService")
public class WikiServiceImpl implements WikiService{

 private Logger logger;

@Inject
 private  PlatformTransactionManager tx;

@SuppressWarnings("rawtypes")
@Autowired
 private  EntityService entityService;

@Autowired
 private  WikiRegistDao wikiRegistDao;

@Autowired
 private  WikiDao wikiDao;

@Autowired
 private  CommonService commonService;


@SuppressWarnings("unchecked")
public WeWiki getWikiInfo(WeWiki wiki){
    return (WeWiki) entityService.getRowEntity(wiki);
}


@Override
public List<WeTemplate> getWeTemplateList(WeTemplate temp){
    return wikiDao.getWeTemplateList(temp);
}


@SuppressWarnings("unchecked")
public int insertTags(WeWiki weWiki,int result,int tagSize,String[] arrWeTag,int nextIdx,int revision){
    WeWikiTag weWikiTag = new WeWikiTag();
    weWikiTag.setWe_use_yn("Y");
    weWikiTag.setWe_wiki_idx(nextIdx);
    weWikiTag.setWe_wiki_revision(revision);
    weWikiTag.setWe_ins_date(DateUtil.getTodayTime());
    for (int index = 0; index < tagSize; index++) {
        weWikiTag.setWe_tag(arrWeTag[index].trim());
        try {
            entityService.insertEntity(weWikiTag);
        } catch (DBHandleException e) {
            result = -3;
            logger.debug("###?????? ?????? ");
            throw new DBHandleException("???????????? ??????", e.getCause(), result);
        }
    }
    return result;
}


public int insertSummary(int result,List<Object> headList,int nextIdx,int revision){
    Map<String, Object> tagMap;
    int headSize = headList.size();
    for (int i = 0; i < headSize; i++) {
        tagMap = (Map<String, Object>) headList.get(i);
        WeWikiSummary summary = new WeWikiSummary();
        summary.setWe_wiki_idx(nextIdx);
        // ?????? ????????? ???????????? ?????? 1???
        summary.setWe_wiki_revision(revision);
        summary.setWe_summary_tag((String) tagMap.get("tag"));
        summary.setWe_summary_title((String) tagMap.get("tagVal"));
        summary.setWe_use_yn("Y");
        logger.debug("###################   headList    : " + headList.toString());
        logger.debug("###################   htmltag    : " + tagMap.toString());
        // insert()
        try {
            entityService.insertEntity(summary);
            result = 1;
        } catch (DBHandleException e) {
            result = -4;
            logger.debug("###????????? ??????  ?????? ");
            throw new DBHandleException("????????? ??????  ??????", e.getCause(), result);
        }
    }
    return result;
}


@SuppressWarnings("unchecked")
public int insertLinks(int result,List<Object> linkList,int nextIdx,int revision){
    Map<String, Object> tagMap;
    int linkSize = linkList.size();
    for (int i = 0; i < linkSize; i++) {
        tagMap = (Map<String, Object>) linkList.get(i);
        WeWikiLink link = new WeWikiLink();
        link.setWe_wiki_idx(nextIdx);
        // ?????? ????????? ???????????? ?????? 1???
        link.setWe_wiki_revision(revision);
        String fullTextLink = "<a href='" + (String) tagMap.get("tagUrl") + "' target='_blank' title='" + (String) tagMap.get("tagTitle").toString().trim() + "'>" + (String) tagMap.get("tagTitle").toString().trim() + "</a>";
        link.setWe_link_text(fullTextLink);
        link.setWe_link_title((String) tagMap.get("tagTitle"));
        link.setWe_link_url((String) tagMap.get("tagUrl"));
        link.setWe_use_yn("Y");
        logger.debug("###################   linkTagList    : " + link.toString());
        logger.debug("###################   htmltag    : " + tagMap.toString());
        // insert()
        try {
            entityService.insertEntity(link);
            result = 1;
        } catch (DBHandleException e) {
            result = -3;
            logger.debug("###???????????? ?????? ");
            throw new DBHandleException("???????????? ??????", e.getCause(), result);
        }
    }
    return result;
}


@Override
public int delWeWikiFile(Integer weFileIdx){
    return wikiDao.delWeWikiFile(weFileIdx);
}


@SuppressWarnings("unchecked")
public WeWiki getWikiForEdit(WeWiki weWiki,MemberSessionVo loginUser){
    weWiki = (WeWiki) entityService.getRowEntity(weWiki);
    logger.debug("############## : " + weWiki.toString());
    // weWiki.validationWikiModified();
    // weWiki.toggleEditYn();
    // 
    // weWiki.setWe_upd_date(new Date());
    // weWiki.setWe_upd_user(loginUser.getWeUserIdx());
    // 
    // entityService.updateEntity(weWiki);
    return weWiki;
}


@Override
public int modifiedWikiAndSaveRevision(WeWiki weWiki,WeSpace weSpace,String weTag,MemberSessionVo loginUser,HttpServletRequest request,String[] weFileIdx){
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
    // ?????? ????????? ????????????
    logger.debug("#####################################");
    logger.debug("????????? weWikiForm" + weWiki.toString());
    WeWiki originalWiki = new WeWiki();
    originalWiki = (WeWiki) commonService.getWikiInfo(weWiki.getWe_wiki_idx());
    int result = 0;
    // "," ???????????? split ??????
    int tagSize = 0;
    String[] arrWeTag = null;
    if (weTag != null && !weTag.equals("")) {
        arrWeTag = weTag.split(",");
        tagSize = arrWeTag.length;
    }
    try {
        GliderTagParser gtp = new GliderTagParser();
        logger.debug("####### getWe_wiki_text() : {}", weWiki.getWe_wiki_text());
        Map<String, Object> resultMap = null;
        try {
            resultMap = gtp.getHtml(weWiki.getWe_wiki_text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object> linkList = (List<Object>) resultMap.get("linkTagList");
        List<Object> headList = (List<Object>) resultMap.get("h1TagList");
        List<Object> noteList = (List<Object>) resultMap.get("noteTagList");
        Integer graphCnt = (Integer) resultMap.get("graphCnt");
        logger.debug("#####################################");
        logger.debug("linkList : {}", linkList.toString());
        logger.debug("headList : {}", headList.toString());
        logger.debug("noteList : {}", noteList.toString());
        logger.debug("#####################################");
        // we_wiki_bak ???????????? ??????
        logger.debug("#####################################");
        logger.debug("???????????? ??? originalWiki : " + originalWiki.toString());
        WeWikiBak wikiBak = new WeWikiBak();
        BeanUtils.copyProperties(originalWiki, wikiBak);
        wikiBak.setWe_edit_text(StringUtil.strNullToSpace(request.getParameter("weEditText"), // ????????????
        loginUser.getWeUserNick() + "?????? ??????"));
        wikiBak.setWe_mov_date(new Date());
        wikiBak.setWe_upd_date(new Date());
        wikiBak.setWe_upd_user(loginUser.getWeUserIdx());
        logger.debug("#####################################");
        logger.debug("???????????? ??? wikiBak : " + wikiBak.toString());
        result = wikiDao.insertSelectedWikiBak(wikiBak);
        int originRevision = originalWiki.getWe_wiki_revision();
        int revision = originalWiki.getWe_wiki_revision() + 1;
        // ??????????????? ??????????????? ????????? ??????
        // originalWiki.setWe_wiki_markup(resultMap.get("htmltag").toString().replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));
        originalWiki.setWe_wiki_markup(resultMap.get("htmltag").toString());
        originalWiki.setWe_wiki_title(weWiki.getWe_wiki_title());
        originalWiki.setWe_wiki_text(GliderTagPaserUtil.replaceHtmlToParsing(weWiki.getWe_wiki_text().toString()));
        originalWiki.setWe_upd_date(new Date());
        originalWiki.setWe_upd_user(loginUser.getWeUserIdx());
        originalWiki.setWe_edit_text(StringUtil.strNullToSpace(request.getParameter("weEditText"), // ????????????
        loginUser.getWeUserNick() + "?????? ??????"));
        originalWiki.setWe_user_ip(request.getRemoteAddr());
        originalWiki.setWe_wiki_revision(revision);
        logger.debug("##########Wiki ???????????? ????????????");
        result = entityService.updateEntity(originalWiki);
        WeWikiLink linkCountObj = new WeWikiLink();
        linkCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
        linkCountObj.setWe_wiki_revision(originRevision);
        linkCountObj.setWe_use_yn("Y");
        int linkCount = entityService.getCountEntity(linkCountObj);
        if (linkCount > 0) {
            logger.debug("### ?????? ?????? ?????? ???????????? : " + linkCount);
            result = wikiDao.delWeWikiLink(originalWiki.getWe_wiki_idx(), originRevision, "N");
            logger.debug("##1 result : " + result);
        }
        WeWikiSummary summaryCountObj = new WeWikiSummary();
        summaryCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
        summaryCountObj.setWe_wiki_revision(originRevision);
        summaryCountObj.setWe_use_yn("Y");
        int summayCount = entityService.getCountEntity(summaryCountObj);
        if (summayCount > 0) {
            logger.debug("### ????????? ?????? ?????? ???????????? : " + summayCount);
            result = wikiDao.delWeWikiSummary(originalWiki.getWe_wiki_idx(), originRevision, "N");
            logger.debug("##2 result : " + result);
        }
        WeWikiNote noteCountObj = new WeWikiNote();
        noteCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
        noteCountObj.setWe_wiki_revision(originRevision);
        noteCountObj.setWe_use_yn("Y");
        int noteCount = entityService.getCountEntity(noteCountObj);
        if (noteCount > 0) {
            logger.debug("### ??????  ?????? ?????? ???????????? : " + noteCount);
            result = wikiDao.delWeWikiNote(originalWiki.getWe_wiki_idx(), originRevision, "N");
            logger.debug("##3 result : " + result);
        }
        WeWikiTag tagCountObj = new WeWikiTag();
        tagCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
        tagCountObj.setWe_wiki_revision(originRevision);
        tagCountObj.setWe_use_yn("Y");
        int tagCount = entityService.getCountEntity(tagCountObj);
        if (tagCount > 0) {
            logger.debug("### ??????  ?????? ?????? ???????????? : " + tagCount);
            result = wikiDao.delWeWikiTag(originalWiki.getWe_wiki_idx(), originRevision, "N");
            logger.debug("##4 result : " + result);
        }
        WeWikiGraph wikiGraph = new WeWikiGraph();
        wikiGraph.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
        wikiGraph.setWe_wiki_revision(originRevision);
        wikiGraph.setWe_use_yn("Y");
        int graphCount = entityService.getCountEntity(wikiGraph);
        if (graphCount > 0) {
            logger.debug("### ?????????  ?????? ?????? ???????????? : " + graphCount);
            result = wikiDao.delWeWikiGraph(originalWiki.getWe_wiki_idx(), originRevision, "N");
            logger.debug("##4 result : " + result);
        }
        // links ??????
        if (linkList != null) {
            result = this.insertLinks(result, linkList, originalWiki.getWe_wiki_idx(), revision);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ?????? ?????? ??? result : " + result);
        if (headList != null) {
            result = this.insertSummary(result, headList, originalWiki.getWe_wiki_idx(), revision);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ????????? ?????? ??? result  : " + result);
        if (noteList != null) {
            result = this.insertNote(result, noteList, originalWiki.getWe_wiki_idx(), revision);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ??? ?????? ??? result  : " + result);
        if (graphCnt != null && graphCnt != 0) {
            result = this.insertGraph(result, graphCnt, originalWiki.getWe_wiki_idx(), revision);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("## ????????? ????????? result  :  " + result);
        if (tagSize > 0) {
            // ?????????
            result = this.insertTags(weWiki, result, tagSize, arrWeTag, originalWiki.getWe_wiki_idx(), revision);
            // ??????
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        if (result >= 1) {
            // ?????? ????????? ??????
            WeWikiFile fileCountObj = new WeWikiFile();
            fileCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
            fileCountObj.setWe_wiki_revision(originRevision);
            fileCountObj.setWe_use_yn("Y");
            // ?????? ?????? ???????????? ???????????? ?????? ???????????? ???????????? ??????.
            int fileCount = entityService.getCountEntity(fileCountObj);
            if (fileCount > 0) {
                logger.debug("### ??????  ?????? ?????? ???????????? : " + tagCount);
                result = wikiDao.updateWikiFile(originalWiki.getWe_wiki_idx(), revision, "Y");
                logger.debug("##4 result : " + result);
            }
            // ????????? ?????? ??????
            if (weFileIdx != null) {
                int retCount = 0;
                retCount = wikiRegistDao.insertArrayFileList(weFileIdx, originalWiki.getWe_wiki_idx(), revision);
                logger.debug("### retCount :  " + retCount);
                // ??????????????? ????????? ???????????? ????????? temp ?????? real ??? ????????? ?????? we_file ?????? ????????????
                // ????????????.
                boolean fileRemove = false;
                WeFile weFile = new WeFile();
                weFile = commonService.getUserFileInfo(Integer.parseInt(weFileIdx[0]));
                logger.debug("###weFile : " + weFile.toString());
                String fromFilePath = request.getSession().getServletContext().getRealPath("/resource/temp");
                String toFilePath = request.getSession().getServletContext().getRealPath("/resource/real");
                try {
                    File fromFile = new File(fromFilePath + weFile.getWe_file_save_path());
                    File toFile = new File(toFilePath + weFile.getWe_file_save_path());
                    fileRemove = FileUploader.copyTo(fromFile, toFile);
                    if (fileRemove) {
                        for (int i = 0; i < weFileIdx.length; i++) {
                            WeFile delWeFile = new WeFile();
                            delWeFile.setWe_file_idx(Integer.parseInt(weFileIdx[i]));
                            entityService.deleteEntity(delWeFile);
                        }
                    }
                } catch (Exception e) {
                    tx.rollback(status);
                    result = -1;
                    throw new DBHandleException("?????? ?????? ????????? ??????", null, result);
                }
            }
            // ?????? ??? ??????????????? WE_WIKI_LOG ??? ????????? ????????? .
            WeWikiLog wikiLog = new WeWikiLog();
            wikiLog.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
            wikiLog.setWe_wiki_revision(revision);
            wikiLog.setWe_wiki_status("S");
            wikiLog.setWe_user_idx(loginUser.getWeUserIdx());
            wikiLog.setWe_wiki_action_type("U");
            int rtnLog = wikiDao.insertWikiLog(wikiLog);
            logger.debug("###rtnLog : " + rtnLog);
            logger.debug("### ?????? ?????? ??? result  : " + result);
            tx.commit(status);
        } else {
            result = -1;
            logger.info("???????????? Error  (result=" + result + "}");
            tx.rollback(status);
            throw new DBHandleException("???????????? ??????", null, result);
        }
    } catch (DBHandleException e) {
        // ????????? ?????? ?????? ??????
        result = -2;
        logger.info("***???????????? Exception " + e.getMessage());
        logger.info("***???????????? Exception " + e.getCause());
        logger.info("***???????????? Exception " + e.getStatus());
        tx.rollback(status);
        throw new DBHandleException(e.getMessage(), e.getCause(), result);
    }
    return result;
}


@Override
public int addWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request){
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
    /**
     * ?????? ???????????? ?????? ???????????? ?????? - ?????? ?????? ???????????? ?????? - ?????? ?????? ???????????? ?????? - ?????? ?????? ?????? ???????????????
     * ????????? ?????? - ?????? ?????? ?????????????????? ??????
     */
    // ????????? ????????? ????????? ????????? ?????????. (IFNULL(MAX(LAST_INSERT_ID(we_wiki_idx)) + 1, 1) as wikiIdx)
    int nextIdx = 0;
    // ?????? ????????? ????????? ????????? ????????? ?????????. (IFNULL(MAX(LAST_INSERT_ID(we_wiki_idx)), 1) as wikiIdx )
    int currIdx = 0;
    int result = 0;
    // "," ???????????? split ??????
    int tagSize = 0;
    String[] arrWeTag = null;
    if (weTag != null && !weTag.equals("")) {
        arrWeTag = weTag.split(",");
        tagSize = arrWeTag.length;
    }
    logger.debug("@@@@@@@@@@\n\n\n" + "" + "weWiki.getWe_wiki_text() : {}", weWiki.getWe_wiki_text());
    try {
        GliderTagParser gtp = new GliderTagParser();
        logger.debug("####### getWe_wiki_text() : {}", weWiki.getWe_wiki_text());
        Map<String, Object> resultMap = null;
        try {
            resultMap = gtp.getHtml(weWiki.getWe_wiki_text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object> linkList = (List<Object>) resultMap.get("linkTagList");
        List<Object> headList = (List<Object>) resultMap.get("h1TagList");
        List<Object> noteList = (List<Object>) resultMap.get("noteTagList");
        Integer graphCnt = (Integer) resultMap.get("graphCnt");
        logger.debug("#####################################");
        logger.debug("linkList : {}", linkList.toString());
        logger.debug("headList : {}", headList.toString());
        logger.debug("noteList : {}", noteList.toString());
        logger.debug("#####################################");
        // ?????? ???????????? ??????
        nextIdx = this.getNextWikiIdx();
        logger.debug("###?????? ????????? ????????? : " + weWiki.getWe_wiki_parent_idx());
        // ?????? ????????? ????????? ?????? ?????? ???????????? ?????????
        if (weWiki.getWe_wiki_parent_idx() == null || weWiki.getWe_wiki_parent_idx() == 0) {
            // ?????? ???????????? IDX??? ?????? ??????
            weWiki.setWe_wiki_parent_idx(nextIdx);
        // ????????????.
        }
        // weWiki.setWe_wiki_markup(resultMap.get("htmltag").toString().replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));
        weWiki.setWe_wiki_markup(resultMap.get("htmltag").toString());
        weWiki.setWe_wiki_text(GliderTagPaserUtil.replaceHtmlToParsing(weWiki.getWe_wiki_text().toString()));
        result = entityService.insertEntity(weWiki);
        if (result > 0) {
            // ?????? ????????? ?????? ????????? ??????
            currIdx = this.getCurrentWikiIdx();
        } else {
            result = -1;
            logger.info("???????????? Error  (result=" + result + "}");
            tx.rollback(status);
            throw new DBHandleException("???????????? ??????", null, result);
        }
        // links ??????
        if (linkList != null) {
            result = this.insertLinks(result, linkList, nextIdx, 1);
            if (result < 1) {
                logger.info("***????????????  ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ?????? ?????? ??? result : " + result);
        if (headList != null) {
            result = this.insertSummary(result, headList, nextIdx, 1);
            if (result < 1) {
                logger.info("***????????????  ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ????????? ?????? ??? result  : " + result);
        if (noteList != null) {
            result = this.insertNote(result, noteList, nextIdx, 1);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("### ?????? ?????? ??? result  : " + result);
        if (graphCnt != null && graphCnt != 0) {
            result = this.insertGraph(result, graphCnt, nextIdx, 1);
            if (result < 1) {
                logger.info("***???????????? ?????? ??????!!!! ");
                tx.rollback(status);
            }
        }
        logger.debug("## ????????? ????????? result  :  " + result);
        if (result >= 1) {
            // ?????? ????????? ??????
            // ????????? ?????? ??????
            if (tagSize > 0) {
                // ?????????
                result = this.insertTags(weWiki, result, tagSize, arrWeTag, nextIdx, 1);
                // ??????
                if (result < 1) {
                    logger.info("***???????????? ?????? ??????!!!! ");
                    tx.rollback(status);
                }
            }
            // ????????? ?????? ??????
            if (weFileIdx != null) {
                logger.debug("### nextIdx : " + nextIdx);
                int retCount = 0;
                // ??????
                retCount = wikiRegistDao.insertArrayFileList(weFileIdx, nextIdx, 1);
                // ?????????
                // ????????????
                // 1??????.
                logger.debug("### retCount :  " + retCount);
                // ??????????????? ????????? ???????????? ????????? temp ?????? real ??? ????????? ?????? we_file ?????? ????????????
                // ????????????.
                boolean fileRemove = false;
                WeFile weFile = new WeFile();
                weFile = commonService.getUserFileInfo(Integer.parseInt(weFileIdx[0]));
                String fromFilePath = request.getSession().getServletContext().getRealPath("/resource/temp");
                String toFilePath = request.getSession().getServletContext().getRealPath("/resource/real");
                File fromFile = new File(fromFilePath + weFile.getWe_file_save_path());
                File toFile = new File(toFilePath + weFile.getWe_file_save_path());
                fileRemove = FileUploader.copyTo(fromFile, toFile);
                if (fileRemove) {
                    for (int i = 0; i < weFileIdx.length; i++) {
                        WeFile delWeFile = new WeFile();
                        delWeFile.setWe_file_idx(Integer.parseInt(weFileIdx[i]));
                        entityService.deleteEntity(delWeFile);
                    }
                }
            }
            // ?????? ??? ??????????????? WE_WIKI_LOG ??? ????????? ????????? .
            WeWikiLog wikiLog = new WeWikiLog();
            wikiLog.setWe_wiki_idx(nextIdx);
            wikiLog.setWe_wiki_revision(1);
            wikiLog.setWe_wiki_status("S");
            wikiLog.setWe_user_idx(weWiki.getWe_ins_user());
            wikiLog.setWe_wiki_action_type("I");
            int rtnLog = wikiDao.insertWikiLog(wikiLog);
            logger.debug("###rtnLog : " + rtnLog);
            result = currIdx;
            tx.commit(status);
        } else {
            result = -1;
            logger.info("???????????? Error  (result=" + result + "}");
            tx.rollback(status);
            throw new DBHandleException("???????????? ??????", null, result);
        }
    } catch (DBHandleException e) {
        // ????????? ?????? ?????? ??????
        result = -2;
        logger.info("***???????????? Exception " + e.getMessage());
        logger.info("***???????????? Exception " + e.getCause());
        logger.info("***???????????? Exception " + e.getStatus());
        tx.rollback(status);
        throw new DBHandleException(e.getMessage(), e.getCause(), result);
    }
    return result;
}


public int getCurrentWikiIdx(){
    return wikiRegistDao.getCurrentWikiIdx();
}


@Override
public List<WeWiki> getWikiList(int spaceIdx){
    WeWiki searchWiki = new WeWiki();
    searchWiki.setWe_use_yn("Y");
    searchWiki.setWe_space_idx(spaceIdx);
    List<WeWiki> wikiList = (List<WeWiki>) wikiDao.getWikiList(searchWiki);
    return wikiList;
}


@Override
public int addFavorite(int loginUserIdx,int spaceIdx,int wikiIdx){
    WeFavorite searchFavorite = new WeFavorite();
    searchFavorite.setWe_favorite_type(FavorityType.WIKI);
    searchFavorite.setWe_space_idx(spaceIdx);
    searchFavorite.setWe_user_idx(loginUserIdx);
    searchFavorite.setWe_wiki_idx(wikiIdx);
    searchFavorite.setWe_use_yn("Y");
    try {
        WeFavorite weFavorite = (WeFavorite) entityService.getRowEntity(searchFavorite);
        if (weFavorite != null) {
            return -1;
        } else {
            return commonService.addFavorite(loginUserIdx, FavorityType.WIKI, spaceIdx, wikiIdx);
        }
    } catch (Throwable e) {
        throw new GliderwikiException(e);
    }
}


public int addSubWikiAllContents(WeWiki weWiki,WeSpace weSpace,String weTag,String[] weFileIdx,HttpServletRequest request){
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
    String maxStep = "";
    String minStep = "";
    // ?????? ??????????????? order??? ??? ?????? depth??? ????????? ???????????? ?????? ?????? order ?????? ????????????.
    // ?????? ????????? ????????? ?????? ?????? ???????????? ?????? ???????????? ????????????.
    minStep = wikiRegistDao.getMinDepthIdx(weWiki);
    // minDepth?????? ????????? Order ?????? ???????????? ?????????
    logger.debug("minDepth : {}", minStep);
    int insertResult = 0;
    try {
        if (minStep == null || "".equals(minStep)) {
            // ?????? ?????? ???????????? ????????????
            // ?????? ????????? ?????? ??????
            maxStep = wikiRegistDao.getMaxDepthIdx(weWiki);
            // Order + 1
            logger.debug("?????? ????????? ?????? ????????? maxDepth : " + maxStep);
            weWiki.setWe_wiki_order_idx(Integer.parseInt(maxStep));
        } else {
            // ?????? ????????? Order ??? ?????? ???????????? ????????? ????????? ?????? ???????????? depth ??? ??????????????? ?????? order ??? ????????? ?????? ?????? +1 ??????
            logger.debug("????????? ?????? ??????  minStep : " + minStep);
            // ????????? ?????? ??? ????????? depth ?????? ???????????????, ?????? Order ????????? ????????????
            int result = wikiRegistDao.updateParentDepthIdx(weWiki, minStep);
        // depth ??????
        }
        weWiki.setWe_wiki_depth_idx(weWiki.getWe_wiki_depth_idx() + 1);
        logger.debug("##order ???????????? : " + weWiki.getWe_wiki_order_idx());
        insertResult = this.addWikiAllContents(weWiki, weSpace, weTag, weFileIdx, request);
        if (insertResult > 0) {
            tx.commit(status);
        } else {
            insertResult = -1;
            logger.info("???????????? Error  (insertResult=" + insertResult + "}");
            tx.rollback(status);
            throw new DBHandleException("?????? ???????????? ??????", null, insertResult);
        }
    } catch (DBHandleException e) {
        // ????????? ?????? ?????? ??????
        insertResult = -2;
        logger.info("***?????? ???????????? Exception " + e.getMessage());
        logger.info("***?????? ???????????? Exception " + e.getCause());
        logger.info("***?????? ???????????? Exception " + e.getStatus());
        tx.rollback(status);
        throw new DBHandleException(e.getMessage(), e.getCause(), insertResult);
    }
    return insertResult;
}


public int insertGraph(int result,Integer graphCnt,int nextIdx,int revision){
    WeWikiGraph weWikiGraph = new WeWikiGraph();
    weWikiGraph.setWe_wiki_idx(nextIdx);
    weWikiGraph.setWe_wiki_revision(revision);
    weWikiGraph.setWe_graph_cnt(graphCnt);
    weWikiGraph.setWe_use_yn("Y");
    // insert()
    try {
        entityService.insertEntity(weWikiGraph);
        result = 1;
    } catch (DBHandleException e) {
        result = -4;
        logger.debug("###????????? ??????  ?????? ");
        throw new DBHandleException("????????? ??????  ??????", e.getCause(), result);
    }
    return result;
}


public int getNextWikiIdx(){
    return wikiRegistDao.getNextWikiIdx();
}


@SuppressWarnings("unchecked")
@Override
public int enableWikiEditor(WeWiki wiki){
    wiki.setWe_edit_yn("Y");
    wiki.setWe_upd_date(new Date());
    return entityService.updateEntity(wiki);
}


public int insertNote(int result,List<Object> noteList,int nextIdx,int revision){
    Map<String, Object> tagMap;
    int noteSize = noteList.size();
    for (int i = 0; i < noteSize; i++) {
        tagMap = (Map<String, Object>) noteList.get(i);
        WeWikiNote note = new WeWikiNote();
        note.setWe_wiki_idx(nextIdx);
        // ?????? ????????? ???????????? ?????? 1???
        note.setWe_wiki_revision(revision);
        note.setWe_wiki_note_name((String) tagMap.get("tagName"));
        note.setWe_wiki_note_desc((String) tagMap.get("tagDesc"));
        note.setWe_use_yn("Y");
        logger.debug("###################   noteList    : " + noteList.toString());
        logger.debug("###################   htmltag    : " + tagMap.toString());
        // insert()
        try {
            entityService.insertEntity(note);
            result = 1;
        } catch (DBHandleException e) {
            result = -5;
            logger.debug("###????????????  ?????? ");
            throw new DBHandleException("?????? ??????  ??????", e.getCause(), result);
        }
    }
    return result;
}


@Override
public int insertWikiComment(WeWikiComment domain){
    int result = 0;
    try {
        result = entityService.insertEntity(domain);
    } catch (Throwable e) {
        result = -1;
        e.printStackTrace();
    }
    return result;
}


@Override
public List<WeTemplate> getWeTemplateIdx(WeTemplate temp){
    return wikiDao.getWeTemplateIdx(temp);
}


}