package org.jeecgframework.web.system.service;
 import org.jeecgframework.web.system.pojo.base.MutiLangEntity;
public interface MutiLangServiceI {


public String getLang(String lang_key,String args)
;

public void putMutiLang(String langKey,String langCode,String langContext)
;

public void initAllMutiLang()
;

public void refleshMutiLangCach()
;

}