package com.gbcom.common.im.desc;
 public interface IEnumAttribute {


public String getGroupName(int group)
;

public String[] getAllDis(int group)
;

public int[] getAllGroup()
;

public int getGroupValue(int groupId,int value,boolean isBitSet)
;

public String getEnumName(int group,int value,boolean isBitSet)
;

public int getEnumValue(int group,String dis)
;

}