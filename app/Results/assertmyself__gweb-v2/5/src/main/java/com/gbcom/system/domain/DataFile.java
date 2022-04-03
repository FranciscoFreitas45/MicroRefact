package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseDataFile;
public class DataFile extends BaseDataFile{

 private  long serialVersionUID;

/*[CONSTRUCTOR MARKER BEGIN]*/
public DataFile() {
    super();
}/**
 * Constructor for primary key
 */
public DataFile(java.lang.Long id) {
    super(id);
}/**
 * Constructor for required fields
 */
public DataFile(java.lang.Long id, java.lang.String fileName) {
    super(id, fileName);
}
}