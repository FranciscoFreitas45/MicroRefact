package org.gliderwiki.Interface;
public interface AdminSpaceService {

   public List<WeSpace> getSpaceSearchList(WeSpace weSpace);
   public List<WeSpace> getSpaceListMonth(WeSpace space,Date month);
   public List<WeBbs> getBbsSearchList(WeBbs weBbs);
}