package org.gliderwiki.Interface;
public interface SpaceService {

   public List<Map<String,String>> getRecentSpaceList(Integer weUserIdx,Integer startRow,Integer endRow);
}