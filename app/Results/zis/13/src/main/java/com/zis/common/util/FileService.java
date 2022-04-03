package com.zis.common.util;
 public interface FileService {


public String getAbsolutePath(String relativePath)
;

public byte[] get(String relativePath)
;

public String put(byte[] data,String filename)
;

public void remove(String relativePath)
;

public String getBaseDir()
;

}