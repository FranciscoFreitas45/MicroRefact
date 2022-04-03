package com.empl.mgr.Interface;
public interface TownshipDao {

   public List<AddressDto> findTownshipByCountyId(long countyId);
}