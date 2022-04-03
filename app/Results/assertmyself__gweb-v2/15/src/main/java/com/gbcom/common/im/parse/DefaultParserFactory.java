package com.gbcom.common.im.parse;
 import com.gbcom.common.im.parse.alarm.IAlarmParser;
import com.gbcom.system.utils.SpringContextUtil;
public class DefaultParserFactory extends ParserFactory{

 private  DefaultParserFactory instance;


public DefaultParserFactory getInstance(){
    return instance;
}


@Override
public IAlarmParser getAlarmParser(String className){
    return (IAlarmParser) SpringContextUtil.getBean(className);
}


}