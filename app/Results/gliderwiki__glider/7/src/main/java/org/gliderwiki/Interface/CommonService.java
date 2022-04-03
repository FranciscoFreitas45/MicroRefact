package org.gliderwiki.Interface;
public interface CommonService {

   public List<WikiVo> getWikiSearchList(String wiki_text);
   public int delFavorite(Integer weUserIdx,FavorityType type,Integer addIdx);
}