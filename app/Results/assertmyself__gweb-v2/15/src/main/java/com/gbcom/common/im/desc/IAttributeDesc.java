package com.gbcom.common.im.desc;
 import com.gbcom.common.im.ds.IIDNameStruct;
public interface IAttributeDesc extends IIDNameStruct{

 public  int IS_KEY_MASK;

 public  int IS_NE_KEY_MASK;

 public  int IS_READ_ONLY_MASK;

 public  int IS_AVAILABLE_IN_CREATE_MASK;

 public  int IS_AVAILABLE_IN_MODIFY_MASK;

 public  int IS_VISIBLE_MASK;

 public  int IS_COMPLEX_MASK;

 public  int IS_BATCH_MODIFIED_MASK;

 public  int IS_OMC_MASK;

 public  int IS_SKT_NEED_MASK;

 public  int IS_FRIEND_NAME_MASK;

 public  int IS_RD_VISIBLE_MASK;

 public  int IS_ARRAY_MASK;

 public  int IS_DEPEND_ON_PARENT_MASK;

 public  int IS_MOC_MASK;

 public  int IS_MOI_MASK;

 public  int IS_STATUS_MANAGER_MASK;

 public  int IS_STATUS_RUN_MASK;

 public  int IS_STATUS_RUNDETAIL_MASK;

 public  int IS_RESEARCH_AVAILABLE_IN_CREATE_MASK;

 public  int IS_RESEARCH_AVAILABLE_IN_MODIFY_MASK;

 public  int IS_DYNAMIC_STATUS_MASK;


public boolean isMOI()
;

public String getName()
;

public boolean isAvaliableInCreate()
;

public String getMaxValue()
;

public boolean isOMC()
;

public boolean isManageStatus()
;

public DataType getDataType()
;

public boolean isFriendlyName()
;

public boolean isDependOnParent()
;

public boolean isMOC()
;

public String getDefaultValue()
;

public boolean isReadOnly()
;

public boolean isRunStatus()
;

public ControlType getControlType()
;

public boolean isResearchAvaliableInCreate()
;

public boolean isAvaliableInModify()
;

public int getMask()
;

public boolean isBatchModifiable()
;

public boolean isRDVisible()
;

public boolean isKey()
;

public int getArrayLength()
;

public boolean isVisible()
;

public boolean isSKTNeeded()
;

public String getUIName()
;

public String getMinValue()
;

public boolean isResearchAvaliableInModify()
;

public boolean isRunDetailStatus()
;

public int getLength()
;

public boolean isArray()
;

public boolean isNeKey()
;

public boolean isComplex()
;

public boolean isDynamicStatus()
;

}