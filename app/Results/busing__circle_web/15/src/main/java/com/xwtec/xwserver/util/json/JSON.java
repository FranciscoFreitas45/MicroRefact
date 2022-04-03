package com.xwtec.xwserver.util.json;
 import java.io.Writer;
import java.io.Serializable;
public interface JSON extends Serializable{


public int size()
;

public boolean isEmpty()
;

public boolean isArray()
;

public String toString(int indentFactor,int indent)
;

public Writer write(Writer writer)
;

}