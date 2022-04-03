package org.live.Interface;
public interface AttentionRepository {

   public long countAttentionsByLiveRoom_Id(String liveRoomId);
   public List<Attention> findAttentionsByUser_IdAndLiveRoom_Id(String userId,String liveRoomId);
}