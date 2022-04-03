package com.empl.mgr.Interface;
public interface CityDao {

   public List<AddressDto> findCityByProvinceId(long provinceId);
}