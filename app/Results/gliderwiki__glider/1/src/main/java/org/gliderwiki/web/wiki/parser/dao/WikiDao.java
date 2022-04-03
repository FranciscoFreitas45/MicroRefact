package org.gliderwiki.web.wiki.parser.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiLog;
public interface WikiDao {


public int insertWikiLog(WeWikiLog wikiLog)
;

public List<WeTemplate> getWeTemplateList(WeTemplate temp)
;

public List<WeWiki> getWikiList(WeWiki searchWiki)
;

public int insertSelectedWikiBak(WeWikiBak wikiBak)
;

public int delWeWikiTag(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

public int updateWikiFile(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

public int delWeWikiNote(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

public int delWeWikiSummary(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

public int delWeWikiFile(Integer weFileIdx)
;

public int delWeWikiGraph(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

public List<WeTemplate> getWeTemplateIdx(WeTemplate temp)
;

public int delWeWikiLink(Integer weWikiIdx,int weWikiRevision,String weUseYn)
;

}