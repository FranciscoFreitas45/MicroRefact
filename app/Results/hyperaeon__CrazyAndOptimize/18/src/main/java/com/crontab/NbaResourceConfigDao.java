package com.crontab;
 public interface NbaResourceConfigDao {


public void deleteResourceConfigByKey(String host,String module,String key)
;

public NbaResourceConfig queryResourceConfigByKey(String host,String module,String key)
;

public void updateResourceConfig(NbaResourceConfig config)
;

public void insertResourceConfig(NbaResourceConfig config)
;

}