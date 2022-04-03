package com.gbcom.common.im.desc;
 import java.io.Serializable;
public interface IOperationDesc extends Serializable{

 private String OPT;

 private String GROUP;

 private String ID;

 private String NAME;

 private String AT_CMD;

 private String RSP_PARSER;

 private String ENTRY;

 private String KEY;

 private String LEVEL;


public int getCID()
;

public int getOperGroup()
;

public String getOperName()
;

public int getOperID()
;

public String getRspParserClassName()
;

public int getOperLevel()
;

public String getAtCmd()
;

}