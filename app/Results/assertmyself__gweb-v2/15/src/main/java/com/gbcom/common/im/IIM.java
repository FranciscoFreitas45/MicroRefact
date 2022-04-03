package com.gbcom.common.im;
 import com.gbcom.common.im.desc.IClassDesc;
import java.io.Serializable;
public interface IIM extends Serializable{


public String getVersion()
;

public IClassDesc getClassDesc(String className)
;

public long getTimeStamp()
;

public IClassDesc getClassDescByTableName(String tableName)
;

public String getType()
;

public IClassDesc getRoot()
;

}