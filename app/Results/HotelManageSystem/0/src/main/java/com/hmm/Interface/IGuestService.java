package com.hmm.Interface;
public interface IGuestService {

   public Guest findGuestByIdCard(String idCard);
   public void save(Guest guest);
   public List<Guest> findGuestByRoomNo(String roomNo);
}