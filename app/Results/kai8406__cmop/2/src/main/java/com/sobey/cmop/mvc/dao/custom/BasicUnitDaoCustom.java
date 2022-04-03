package com.sobey.cmop.mvc.dao.custom;
 import java.util.List;
public interface BasicUnitDaoCustom {


@SuppressWarnings("rawtypes")
public List getMonitorComputeListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getMonitorElbListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getComputeListByElb(Integer elbId)
;

@SuppressWarnings("rawtypes")
public List getComputeItemListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getNetworkElbItemListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getNetworkEipItemListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getNetworkDnsItemListByResources(Integer userId)
;

@SuppressWarnings("rawtypes")
public List getStorageItemListByResources(Integer userId)
;

}