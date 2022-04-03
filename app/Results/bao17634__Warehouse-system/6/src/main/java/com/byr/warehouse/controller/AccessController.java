package com.byr.warehouse.controller;
 import com.byr.warehouse.Service.LogService;
import com.byr.warehouse.myexception.StoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;
import com.byr.warehouse.Interface.LogService;
@ControllerAdvice
public class AccessController {

 private Logger logger;

@Autowired
 private LogService logService;


@ExceptionHandler(Exception.class)
@ResponseBody
public Map<String,Object> runtimeExceptionHandler(Exception runtimeException){
    final String localizedMessage = getExceptionAllinformation_01(runtimeException);
    logService.saveSysLog(localizedMessage.substring(0, 1023));
    Map model = new TreeMap();
    model.put("status", false);
    model.put("message", localizedMessage);
    runtimeException.printStackTrace();
    return model;
}


public String toString_02(Throwable e){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, true);
    e.printStackTrace(pw);
    pw.flush();
    sw.flush();
    return sw.toString();
}


public String getTrace(Throwable t){
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    t.printStackTrace(writer);
    StringBuffer buffer = stringWriter.getBuffer();
    return buffer.toString();
}


public String getExceptionAllinformation_01(Exception ex){
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream pout = new PrintStream(out);
    ex.printStackTrace(pout);
    String ret = new String(out.toByteArray());
    pout.close();
    try {
        out.close();
    } catch (Exception e) {
    }
    return ret;
}


@ExceptionHandler(StoreException.class)
@ResponseBody
public Map<String,Object> storeExceptionHandler(StoreException storeException){
    final String localizedMessage = getExceptionAllinformation_01(storeException);
    logService.saveSysLog(localizedMessage.substring(0, 1023));
    Map model = new TreeMap();
    model.put("status", false);
    model.put("message", storeException.getMessage());
    model.put("timestamp", new Timestamp(System.currentTimeMillis()));
    storeException.printStackTrace();
    return model;
}


}