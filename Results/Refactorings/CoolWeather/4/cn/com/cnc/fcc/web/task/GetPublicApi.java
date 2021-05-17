import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import cn.com.cnc.fcc.AppContext;
import cn.com.cnc.fcc.config.ApplicationProperties;
import cn.com.cnc.fcc.domain.HstServerInfo;
import cn.com.cnc.fcc.domain.PapiTokenSlave;
import cn.com.cnc.fcc.repository.HstServerInfoRepository;
import cn.com.cnc.fcc.repository.PapiTokenSlaveRepository;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.http.HttpRequest;
import cn.com.cnc.fcc.web.rest.hst.HostSlaveConstants;
@RestController
public class GetPublicApi {

 private  Logger log;

 private  ApplicationProperties applicationProperties;

 private  HstServerInfoRepository hstServerInfoRepository;

 private  PapiTokenSlaveRepository papiTokenSlaveRepository;

 private  DateUtil dateUtil;

 private  HttpRequest httpRequest;

 private  String HST_ERROR;


public boolean GetToken(String url){
    this.applicationProperties = AppContext.getApplicationContext().getBean(ApplicationProperties.class);
    this.hstServerInfoRepository = AppContext.getApplicationContext().getBean(HstServerInfoRepository.class);
    this.papiTokenSlaveRepository = AppContext.getApplicationContext().getBean(PapiTokenSlaveRepository.class);
    this.dateUtil = AppContext.getApplicationContext().getBean(DateUtil.class);
    this.httpRequest = AppContext.getApplicationContext().getBean(HttpRequest.class);
    String ret = "";
    try {
        boolean isHost = false;
        if (HostSlaveConstants.HOST.equals(applicationProperties.getHostSlave().getHostOrSlave())) {
            isHost = true;
        } else if (HostSlaveConstants.SLAVE.equals(applicationProperties.getHostSlave().getHostOrSlave())) {
            isHost = false;
        } else {
            throw new IllegalArgumentException(String.format(HST_ERROR, "Host Or Slave's falg is uncorrect"));
        }
        if (!isHost) {
            log.info("***********Token Update Start**********");
            ZonedDateTime nowDate = dateUtil.getDBNowDate();
            PapiTokenSlave papiTokenSlave = papiTokenSlaveRepository.findByType("00");
            if (papiTokenSlave == null) {
                throw new IllegalArgumentException(String.format(HST_ERROR, "Slave Token Infomation is not Exist"));
            }
            HashMap<String, Object> jsonMap = Maps.newLinkedHashMap();
            jsonMap.put("username", papiTokenSlave.getApiCode());
            jsonMap.put("password", papiTokenSlave.getApiPassword());
            JSONObject tokenJsonObject = JSONObject.parseObject(JSON.toJSONString(jsonMap));
            url = url + "/papi/gettoken";
            String buffer = this.httpRequest.getData(url, HttpRequest.HttpMode.POST, tokenJsonObject.toString(), null);
            JSONObject obj = JSONObject.parseObject(buffer.toString());
            String accesstoken = obj.getString("id_token");
            papiTokenSlave.setApiToken(accesstoken);
            papiTokenSlave.setUpdProgarmCd(this.getClass().getSimpleName());
            papiTokenSlave.setUpdOperCd("Batch");
            papiTokenSlave.setUpdDateTime(nowDate);
            papiTokenSlave.setTriggerDateTime(nowDate);
            papiTokenSlaveRepository.save(papiTokenSlave);
            log.info("***********Token Update End**********");
        }
        return true;
    } catch (Exception e) {
        ret = "error is " + e.getMessage();
        log.info(ret);
        return false;
    }
}


}