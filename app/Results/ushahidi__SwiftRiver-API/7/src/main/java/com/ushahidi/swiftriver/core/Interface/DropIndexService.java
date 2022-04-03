package com.ushahidi.swiftriver.core.Interface;
public interface DropIndexService {

   public List<GetDropDTO> findDrops(String searchTerm,int count,int page);
}