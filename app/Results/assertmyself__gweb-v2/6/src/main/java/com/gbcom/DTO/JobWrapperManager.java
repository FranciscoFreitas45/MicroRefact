package com.gbcom.DTO;
 import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.common.template.xml.northful.NorthConfigManager;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.xml.XStreamUtil;
public class JobWrapperManager {

 private  Logger LOG;

 private  JobWrapperContext context;

 private  Map<String,JobWrapperInfo> jobWrapperMap;

 private  JobWrapperManager instance;

private JobWrapperManager() {
    jobWrapperMap = new HashMap<String, JobWrapperInfo>();
    try {
        final Class<?>[] classContext = { JobWrapperContext.class, JobWrapperInfo.class, TriggerInfo.class, JobParam.class };
        List<JobWrapperInfo> jobinfo = new ArrayList<JobWrapperInfo>();
        // 核心 job
        final URL url = Thread.currentThread().getContextClassLoader().getResource("config/job/core_jobwrapper.xml");
        JobWrapperContext coreContext = XStreamUtil.fromXML(JobWrapperContext.class, url.getFile(), classContext);
        for (JobWrapperInfo info : coreContext.getList()) {
            jobinfo.add(info);
        }
        // 辅助 北向的job:允许多个
        List<String> names = NorthConfigManager.getInstance().getNorthConfig().getList();
        for (String name : names) {
            String path = null;
            // 华数
            if (name.equals("xxx")) {
                path = "config/job/wasu_jobwrapper.xml";
            }
            if (path != null) {
                final URL url2 = Thread.currentThread().getContextClassLoader().getResource(path);
                JobWrapperContext northContext = XStreamUtil.fromXML(JobWrapperContext.class, url2.getFile(), classContext);
                for (JobWrapperInfo info : northContext.getList()) {
                    jobinfo.add(info);
                }
            }
        }
        context.setList(jobinfo);
        for (JobWrapperInfo jobWrapper : context.getList()) {
            jobWrapperMap.put(jobWrapper.getJobName(), jobWrapper);
        }
        LOG.info("parse file success ;;; url=" + url);
    } catch (Exception e) {
        LOG.error("parse file failed!", e);
    }
}
public List<JobWrapperInfo> getJobWrapperList(){
    return context.getList();
}


public JobWrapperManager getInstance(){
    return instance;
}


public JobWrapperInfo getJobWrapperByName(String jobName){
    return jobWrapperMap.get(jobName);
}


}