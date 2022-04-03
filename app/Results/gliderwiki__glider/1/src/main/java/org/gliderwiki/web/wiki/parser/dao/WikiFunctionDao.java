package org.gliderwiki.web.wiki.parser.dao;
 import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;
public interface WikiFunctionDao {


public int insertWeAgree(WeWikiAgree wewikiagree)
;

public int updateWikiByUser(WeWiki weWiki)
;

}