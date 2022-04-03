package com.empl.mgr.service;
 import com.empl.mgr.support.JSONReturn;
public interface AddressService {


public JSONReturn findCountyByCityId(long cityId)
;

public JSONReturn findVillageByTownshipId(long towhshipId)
;

public JSONReturn findProvinceAll()
;

public JSONReturn findTownshipByCountyId(long countyId)
;

public JSONReturn findCityByProvinceId(long provinceId)
;

}