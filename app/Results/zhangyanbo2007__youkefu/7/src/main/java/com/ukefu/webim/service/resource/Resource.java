package com.ukefu.webim.service.resource;
 import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.JobDetail;
public class Resource {

 public  Logger log;


public OutputTextFormat next()


public boolean val(String inputFile,String acceptDocType){
    String file = inputFile != null ? inputFile.toLowerCase() : null;
    return file != null && acceptDocType != null && ((acceptDocType.indexOf(file.substring(file.lastIndexOf(".") + 1)) >= 0 || acceptDocType.indexOf("all") >= 0));
}


public boolean isAvailable()


public void process(OutputTextFormat meta,JobDetail job)


public void updateTask()


public Resource getResource(JobDetail job){
    return job != null && UKDataContext.getResource(job.getTasktype()) != null ? (Resource) UKDataContext.getResource(job.getTasktype()).getConstructor(new Class[] { JobDetail.class }).newInstance(new Object[] { job }) : null;
}


public void rmResource()


public OutputTextFormat getText(OutputTextFormat object)


public void end(boolean clear)


public void begin()


public JobDetail getJob()


}