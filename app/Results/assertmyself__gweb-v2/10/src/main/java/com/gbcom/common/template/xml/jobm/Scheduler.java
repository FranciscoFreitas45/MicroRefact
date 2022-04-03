package com.gbcom.common.template.xml.jobm;
 import com.gbcom.op.scheduler.oper.IJobOper;
import com.gbcom.op.scheduler.oper.JobOperImpl;
import com.gbcom.op.scheduler.util.JobException;
public class Scheduler {

 private  Scheduler instance;

 private  IJobOper jobOper;

/**
 * 私有构造函数
 */
private Scheduler() {
    jobOper = new JobOperImpl();
}
public void cancelJob(String jobGroup,String jobName){
    jobOper.cancelJob(jobName, jobGroup);
}


public Scheduler getInstance(){
    return instance;
}


public void cancelJobs(String jobGroup){
    jobOper.cancelJob(jobGroup);
    String[] jobNames = new String[] { "begin", "stat", "end" };
    for (String jobName : jobNames) {
        jobOper.delete(jobName, jobGroup);
    }
}


public void register(JobWrapper jobWrapper){
    jobOper.register(jobWrapper.getJob());
}


public void cancelTrigger(String triggerGroup){
}


}