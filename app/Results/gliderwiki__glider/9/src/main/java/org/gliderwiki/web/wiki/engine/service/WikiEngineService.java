package org.gliderwiki.web.wiki.engine.service;
 import java.util.List;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
public interface WikiEngineService {


public void createRevision(int wikiIdx,int wikiRev,int wikiBakRev)
;

public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak)
;

public WeWiki getOriginWiki(WeWiki weWiki)
;

}