package org.gliderwiki.Interface;
public interface CommonService {

   public WeUser getUserInfo(Integer weUserIdx);
   public int addFavorite(Integer weUserIdx,FavorityType type,int spaceIdx,int wikiIdx);
}