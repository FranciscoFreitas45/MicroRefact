package com.empl.mgr.Interface;
public interface CountyDao {

   public List<AddressDto> findCountyByCityId(long cityId);
}