package com.gbcom.common.im.ds;
 import java.io.Serializable;
import java.util.Iterator;
public interface IIDNameArray extends Serializable{


public Iterator iterator()
;

public int size()
;

public IIDNameStruct[] get()
;

public IIDNameStruct findFirst(String name)
;

public void put(IIDNameStruct data)
;

public void remove(IIDNameStruct mo)
;

}