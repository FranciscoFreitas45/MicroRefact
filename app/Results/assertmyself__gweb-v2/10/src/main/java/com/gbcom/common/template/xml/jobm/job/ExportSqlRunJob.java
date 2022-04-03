package com.gbcom.common.template.xml.jobm.job;
 import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.common.template.xml.jobm.JobParam;
import com.gbcom.common.template.xml.jobm.JobWrapperInfo;
import com.gbcom.op.scheduler.execute.AbstractStatelessJob;
import com.gbcom.op.scheduler.execute.JobExeContext;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.SystemUtils;
import com.gbcom.system.mysql.SqlExportManager;
import com.gbcom.DTO.SqlExportManager;
public class ExportSqlRunJob extends AbstractStatelessJob{

 private  String FILENAME;

 private  Logger LOG;

 private  String PARAM_SERVERIP;

 private  String PARAM_USERNAME;

 private  String PARAM_PASSWORD;


@Override
public void execute(JobExeContext jobexecontext){
    Map<String, String> jobMap = new HashMap<String, String>();
    JobWrapperInfo jobWrapperInfo = (JobWrapperInfo) jobexecontext.getMap().getValue("job");
    String jobName = jobWrapperInfo.getJobName();
    for (JobParam param : jobWrapperInfo.getJobParamList()) {
        LOG.info("JOB - " + jobName + " ::param name=" + param.getName() + " value=" + param.getValue());
        jobMap.put(param.getName(), param.getValue());
    }
    try {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss)");
        String fileName = FILENAME + sf.format(date.getTime()) + ".sql";
        String path = SystemUtils.USER_HOME + File.separator + fileName;
        SqlExportManager sqlService = new SqlExportManager();
        boolean isSuccess = sqlService.exportSql(path);
        if (isSuccess) {
        // todo
        }
    } catch (IOException e) {
        LOG.error("export sql failed>> IOException:读取属性文件错误", e);
    } catch (Exception e) {
        LOG.error("export sql failed >>other exception", e);
    }
}


}