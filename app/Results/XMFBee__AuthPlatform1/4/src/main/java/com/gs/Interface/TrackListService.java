package com.gs.Interface;
public interface TrackListService {

   public Object count(Object Object);
   public Object queryByPager(Object Object);
   public int countName(TrackList trackList,User user);
   public List<TrackList> queryByPagerName(Pager pager,TrackList trackList);
   public Object insert(Object Object);
   public Object update(Object Object);
}