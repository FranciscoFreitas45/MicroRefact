package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseAppVersion;
public class AppVersion extends BaseAppVersion{

 private  long serialVersionUID;

 public  int APP_OS_TYPE_ANDROID;

 public  int APP_OS_TYPE_IOS;

/*[CONSTRUCTOR MARKER BEGIN]*/
public AppVersion() {
    super();
}/**
 * Constructor for primary key
 */
public AppVersion(Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public AppVersion(Long id, Integer appOsType, String appVersionName, Long appVersionCode) {
    super(id, appOsType, appVersionName, appVersionCode);
}
}