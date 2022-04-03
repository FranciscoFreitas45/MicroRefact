package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseSysSequence;
public class SysSequence extends BaseSysSequence{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public SysSequence() {
    super();
}/**
 * Constructor for primary key
 */
public SysSequence(java.lang.String id) {
    super(id);
}/**
 * Constructor for required fields
 */
public SysSequence(java.lang.String id, java.lang.Long lastid) {
    super(id, lastid);
}
}