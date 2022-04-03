package com.ushahidi.swiftriver.core.Interface;
public interface RiverService {

   public List<GetRiverDTO> findRivers(String searchTerm,int count,int page);
}