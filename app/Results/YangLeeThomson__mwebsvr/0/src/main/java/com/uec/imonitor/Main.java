package com.uec.imonitor;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.uec.imonitor.Interface.ITenantRequestService;
import com.uec.imonitor.Interface.ITenantRequestServiceImpl;
import com.uec.imonitor.Interface.IDataSearch;
import com.uec.imonitor.Interface.IDataSearchImpl;
import com.uec.imonitor.Interface.IDataIndex;
import com.uec.imonitor.Interface.IDataIndexImpl;
import com.uec.imonitor.Interface.IJobFailureService;
import com.uec.imonitor.Interface.IJobFailureServiceImpl;
import com.uec.imonitor.Interface.INewsStatusService;
import com.uec.imonitor.Interface.INewsStatusServiceImpl;
import com.uec.imonitor.Interface.AggsTermParams;
import com.uec.imonitor.Interface.AggsTermParamsImpl;
import com.uec.imonitor.Interface.IRequestNewsPoiService;
import com.uec.imonitor.Interface.IRequestNewsPoiServiceImpl;
import com.uec.imonitor.Interface.IRequestSiteService;
import com.uec.imonitor.Interface.IRequestSiteServiceImpl;
import com.uec.imonitor.Interface.IWebsiteDicService;
import com.uec.imonitor.Interface.IWebsiteDicServiceImpl;
import com.uec.imonitor.Interface.INewsContentService;
import com.uec.imonitor.Interface.INewsContentServiceImpl;
import com.uec.imonitor.Interface.ITenantRequestService;
import com.uec.imonitor.Interface.ITenantRequestServiceImpl;
import com.uec.imonitor.Interface.IDataSearch;
import com.uec.imonitor.Interface.IDataSearchImpl;
import com.uec.imonitor.Interface.IDataIndex;
import com.uec.imonitor.Interface.IDataIndexImpl;
import com.uec.imonitor.Interface.IJobFailureService;
import com.uec.imonitor.Interface.IJobFailureServiceImpl;
import com.uec.imonitor.Interface.INewsStatusService;
import com.uec.imonitor.Interface.INewsStatusServiceImpl;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDateImpl;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDateImpl;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDateImpl;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDateImpl;
@SpringBootApplication
public class Main {


@Bean
public RestTemplate restTemplate(){
 
 return new RestTemplate();

  }



public static void main(String[] args){

SpringApplication.run(Main.class,args);

   }



@Bean
public ITenantRequestService itenantrequestservice(){

return  new ITenantRequestServiceImpl(); 
    }



@Bean
public IDataSearch idatasearch(){

return  new IDataSearchImpl(); 
    }



@Bean
public IDataIndex idataindex(){

return  new IDataIndexImpl(); 
    }



@Bean
public IJobFailureService ijobfailureservice(){

return  new IJobFailureServiceImpl(); 
    }



@Bean
public INewsStatusService inewsstatusservice(){

return  new INewsStatusServiceImpl(); 
    }



@Bean
public AggsTermParams aggstermparams(){

return  new AggsTermParamsImpl(); 
    }



@Bean
public IRequestNewsPoiService irequestnewspoiservice(){

return  new IRequestNewsPoiServiceImpl(); 
    }



@Bean
public IRequestSiteService irequestsiteservice(){

return  new IRequestSiteServiceImpl(); 
    }



@Bean
public IWebsiteDicService iwebsitedicservice(){

return  new IWebsiteDicServiceImpl(); 
    }



@Bean
public INewsContentService inewscontentservice(){

return  new INewsContentServiceImpl(); 
    }



@Bean
public ITenantRequestService itenantrequestservice(){

return  new ITenantRequestServiceImpl(); 
    }



@Bean
public IDataSearch idatasearch(){

return  new IDataSearchImpl(); 
    }



@Bean
public IDataIndex idataindex(){

return  new IDataIndexImpl(); 
    }



@Bean
public IJobFailureService ijobfailureservice(){

return  new IJobFailureServiceImpl(); 
    }



@Bean
public INewsStatusService inewsstatusservice(){

return  new INewsStatusServiceImpl(); 
    }



@Bean
public CommonDate commondate(){

return  new CommonDateImpl(); 
    }



@Bean
public CommonDate commondate(){

return  new CommonDateImpl(); 
    }



@Bean
public CommonDate commondate(){

return  new CommonDateImpl(); 
    }



@Bean
public CommonDate commondate(){

return  new CommonDateImpl(); 
    }



}