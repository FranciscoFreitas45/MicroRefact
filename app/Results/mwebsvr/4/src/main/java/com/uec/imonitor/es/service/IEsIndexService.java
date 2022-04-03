package com.uec.imonitor.es.service;
 import com.uec.imonitor.common.exception.BaseException;
public interface IEsIndexService {


public boolean initAllNewsSpreading()
;

public void indexRequsetNewsSetting()
;

public void indexNewsSpreadingSetting()
;

public void setIndexAliase(String originalName,String aliaseName)
;

public void EsIndexSetting()
;

public boolean initAllRequestNews()
;

}