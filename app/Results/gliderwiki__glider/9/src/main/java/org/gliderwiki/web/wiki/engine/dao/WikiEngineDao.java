package org.gliderwiki.web.wiki.engine.dao;
 import java.util.List;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
public interface WikiEngineDao {


public int insertWeWikiBak(WeWiki weWiki)
;

public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak)
;

public WeWiki getOriginWiki(WeWiki weWiki)
;

}