package com.ec.survey.tools;
 import com.ec.survey.model.WebserviceTask;
import com.ec.survey.service.ExportService;
import com.ec.survey.service.WebserviceService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service("exportsRemover")
@Scope("prototype")
public class ExportsRemover implements Runnable{

 protected  Logger logger;

@Resource(name = "exportService")
 protected  ExportService exportService;

@Resource(name = "webserviceService")
 protected  WebserviceService webserviceService;

 private  int task;


public void init(int task){
    this.task = task;
}


public void setTask(int task){
    this.task = task;
}


@Override
public void run(){
    try {
        exportService.deleteOldExports();
        WebserviceTask t = webserviceService.get(task);
        t.setDone(true);
        webserviceService.save(t);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        try {
            webserviceService.setError(task, e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.toString());
        } catch (InterruptedException e1) {
            logger.error(e1.getLocalizedMessage(), e1);
        }
    }
}


public int getTask(){
    return task;
}


}