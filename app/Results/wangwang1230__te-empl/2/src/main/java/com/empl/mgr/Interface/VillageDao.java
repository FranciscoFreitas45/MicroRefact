package com.empl.mgr.Interface;
public interface VillageDao {

   public List<AddressDto> findVillageByTownshipId(long towhshipId);
}