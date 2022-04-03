package com.gbcom.common.im.desc;
 import java.io.Serializable;
import com.gbcom.common.im.ds.IIDNameStruct;
import com.gbcom.common.im.parse.alarm.IAlarmParser;
public interface IClassDesc extends Serializable, IIDNameStruct{

 public  int IS_AVAILABLE_IN_CREATE_MASK;

 public  int IS_AVAILABLE_IN_MODIFY_MASK;


public String getName()
;

public IAttributeDesc[] getFriendAttributeDescs()
;

public boolean isAvaliableInCreate()
;

public IAttributeDesc[] getKeyAttributeDesc()
;

public boolean isVector()
;

public IAttributeDesc[] getAttributeDescs()
;

public int getAttributeDescIndex(int aid)
;

public IAttributeDesc[] getNonMOCAttributeDescs()
;

public IAttributeDesc[] getSkeletonAttributeDesc()
;

public IAttributeDesc getRunStatusAttributeDesc()
;

public IAttributeDesc getManageStatusAttributeDesc()
;

public boolean isAvaliableInModify()
;

public IAlarmParser getAlarmParser()
;

public String getVersion()
;

public int getMask()
;

public IClassDesc getParent()
;

public void setMask(int mask)
;

public IClassDesc getChild(String cName)
;

public IAttributeDesc getAttributeDescByIndex(int index)
;

public IAttributeDesc getAttributeDesc(String attributeName)
;

public int getMaxIndex()
;

public IClassDesc[] getChildren()
;

public String getUIName()
;

public String getType()
;

public String getAlarmParserClassName()
;

public IAttributeDesc getFriendAttributeDesc()
;

public void setMaxIndex(int max)
;

public IAttributeDesc getRunDetailStatusAttributeDesc()
;

}