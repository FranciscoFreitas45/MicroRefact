package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.River;
public interface RiverRequest {

   public List<River> getRivers(long id);
   public void setFollowingRivers(List<River> followingRivers,long id);
   public void setCollaboratingRivers(List<River> collaboratingRivers,long id);
   public void setRivers(List<River> rivers,long id);
   public List<River> getCollaboratingRivers(long id);
   public List<River> getFollowingRivers(long id);
}