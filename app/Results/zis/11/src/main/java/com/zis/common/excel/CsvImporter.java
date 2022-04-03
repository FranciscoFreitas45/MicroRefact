package com.zis.common.excel;
 import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class CsvImporter extends FileImporter<T>{

/**
 * @param inputStream
 * @param templateName
 */
public CsvImporter(InputStream inputStream, String templateName) {
    super(inputStream, templateName);
// TODO Auto-generated constructor stub
}
@Override
public List<String> loadFactHeader(){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Double getDouble(Integer rowNums,int index){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Object getDate(Integer rowNums,Integer index){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Integer getInteger(Integer rowNums,int index){
    // TODO Auto-generated method stub
    return null;
}


@Override
public String getString(Integer rowNums,int index){
    // TODO Auto-generated method stub
    return null;
}


@Override
public Integer getRowNums(){
    // TODO Auto-generated method stub
    return null;
}


@Override
public List<String> loadFileFormat(InputStream input){
    // TODO Auto-generated method stub
    return null;
}


}