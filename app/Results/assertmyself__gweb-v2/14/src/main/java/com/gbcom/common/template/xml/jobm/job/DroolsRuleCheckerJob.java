package com.gbcom.common.template.xml.jobm.job;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.gbcom.common.drools.DroolsEngineFactory;
import com.gbcom.common.drools.DroolsRuleEngine;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.system.utils.DateUtil;
public class DroolsRuleCheckerJob extends AbstractStatelessJob{

 private  Logger LOG;


public void doJob(){
    // List<ApCache> list = HeartListSingleton.getInstance().getOnlineList();
    List list = new ArrayList(0);
    long begin = System.currentTimeMillis();
    LOG.info("~~~~ BEGIN Check DroolsRuleCheckerJob  ~~~~  size=" + list.size() + "; Time= " + DateUtil.format(new Date()));
    DroolsRuleEngine engine = DroolsEngineFactory.dbEngine();
    engine.refreshEnginRule();
    List<Object> facts = new ArrayList<Object>();
    for (Object obj : list) {
        facts.add(obj);
    }
    engine.executeRuleEngine(facts);
    LOG.info("~~~~ END Check DroolsRuleCheckerJob  ~~~~  Time= " + DateUtil.format(new Date()) + ";spengd times=" + (System.currentTimeMillis() - begin) / 1000 + "S");
}


public void start(){
    // 重启队列可以考虑阻塞队列
    // AutoRebootHandler.getInstance().clear();
    doJob();
}


@Override
public void execute(JobExeContext context){
    // 单线程方式执行
    start();
}


}