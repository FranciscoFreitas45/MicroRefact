package org.gliderwiki.web.wiki.parser.dao;
 import org.gliderwiki.web.domain.WeWiki;
public interface WikiRegistDao {


public int getCurrentWikiIdx()
;

public String getMinDepthIdx(WeWiki weWiki)
;

public int getNextWikiIdx()
;

public int updateParentDepthIdx(WeWiki weWiki,String minDepthIdx)
;

public String getMaxDepthIdx(WeWiki weWiki)
;

public int insertArrayFileList(String[] weFileIdx,int currIdx,int weWikiRevision)
;

}