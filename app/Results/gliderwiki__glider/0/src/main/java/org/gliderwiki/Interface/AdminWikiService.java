package org.gliderwiki.Interface;
public interface AdminWikiService {

   public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName);
}