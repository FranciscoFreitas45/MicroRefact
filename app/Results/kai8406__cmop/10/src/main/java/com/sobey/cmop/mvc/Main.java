package com.sobey.cmop.mvc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.sobey.cmop.mvc.Interface.AccountService;
import com.sobey.cmop.mvc.Interface.AccountServiceImpl;
import com.sobey.cmop.mvc.Interface.ApplyService;
import com.sobey.cmop.mvc.Interface.ApplyServiceImpl;
import com.sobey.cmop.mvc.Interface.AuditService;
import com.sobey.cmop.mvc.Interface.AuditServiceImpl;
import com.sobey.cmop.mvc.Interface.IBasicUnitService;
import com.sobey.cmop.mvc.Interface.IBasicUnitServiceImpl;
import com.sobey.cmop.mvc.Interface.ChangeHistoryService;
import com.sobey.cmop.mvc.Interface.ChangeHistoryServiceImpl;
import com.sobey.cmop.mvc.Interface.ChangeServcie;
import com.sobey.cmop.mvc.Interface.ChangeServcieImpl;
import com.sobey.cmop.mvc.Interface.ICompareResourcesService;
import com.sobey.cmop.mvc.Interface.ICompareResourcesServiceImpl;
import com.sobey.cmop.mvc.Interface.ComputeService;
import com.sobey.cmop.mvc.Interface.ComputeServiceImpl;
import com.sobey.cmop.mvc.Interface.CPService;
import com.sobey.cmop.mvc.Interface.CPServiceImpl;
import com.sobey.cmop.mvc.Interface.DepartmentService;
import com.sobey.cmop.mvc.Interface.DepartmentServiceImpl;
import com.sobey.cmop.mvc.Interface.DnsService;
import com.sobey.cmop.mvc.Interface.DnsServiceImpl;
import com.sobey.cmop.mvc.Interface.EipService;
import com.sobey.cmop.mvc.Interface.EipServiceImpl;
import com.sobey.cmop.mvc.Interface.ElbService;
import com.sobey.cmop.mvc.Interface.ElbServiceImpl;
import com.sobey.cmop.mvc.Interface.Es3Service;
import com.sobey.cmop.mvc.Interface.Es3ServiceImpl;
import com.sobey.cmop.mvc.Interface.EsgService;
import com.sobey.cmop.mvc.Interface.EsgServiceImpl;
import com.sobey.cmop.mvc.Interface.FailureService;
import com.sobey.cmop.mvc.Interface.FailureServiceImpl;
import com.sobey.cmop.mvc.Interface.HostServerService;
import com.sobey.cmop.mvc.Interface.HostServerServiceImpl;
import com.sobey.cmop.mvc.Interface.ImportService;
import com.sobey.cmop.mvc.Interface.ImportServiceImpl;
import com.sobey.cmop.mvc.Interface.IpPoolService;
import com.sobey.cmop.mvc.Interface.IpPoolServiceImpl;
import com.sobey.cmop.mvc.Interface.LocationService;
import com.sobey.cmop.mvc.Interface.LocationServiceImpl;
import com.sobey.cmop.mvc.Interface.MdnService;
import com.sobey.cmop.mvc.Interface.MdnServiceImpl;
import com.sobey.cmop.mvc.Interface.OneCmdbUtilService;
import com.sobey.cmop.mvc.Interface.OneCmdbUtilServiceImpl;
import com.sobey.cmop.mvc.Interface.OperateService;
import com.sobey.cmop.mvc.Interface.OperateServiceImpl;
import com.sobey.cmop.mvc.Interface.RedmineUtilService;
import com.sobey.cmop.mvc.Interface.RedmineUtilServiceImpl;
import com.sobey.cmop.mvc.Interface.IResourcesJsonService;
import com.sobey.cmop.mvc.Interface.IResourcesJsonServiceImpl;
import com.sobey.cmop.mvc.Interface.ICompareResourcesService;
import com.sobey.cmop.mvc.Interface.ICompareResourcesServiceImpl;
import com.sobey.cmop.mvc.Interface.ServerModelService;
import com.sobey.cmop.mvc.Interface.ServerModelServiceImpl;
import com.sobey.cmop.mvc.Interface.ServiceTagService;
import com.sobey.cmop.mvc.Interface.ServiceTagServiceImpl;
import com.sobey.cmop.mvc.Interface.TemplateMailService;
import com.sobey.cmop.mvc.Interface.TemplateMailServiceImpl;
import com.sobey.cmop.mvc.Interface.VlanService;
import com.sobey.cmop.mvc.Interface.VlanServiceImpl;
import com.sobey.cmop.mvc.Interface.PropertiesLoader;
import com.sobey.cmop.mvc.Interface.PropertiesLoaderImpl;
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
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public ApplyService applyservice(){

return  new ApplyServiceImpl(); 
    }



@Bean
public AuditService auditservice(){

return  new AuditServiceImpl(); 
    }



@Bean
public IBasicUnitService ibasicunitservice(){

return  new IBasicUnitServiceImpl(); 
    }



@Bean
public ChangeHistoryService changehistoryservice(){

return  new ChangeHistoryServiceImpl(); 
    }



@Bean
public ChangeServcie changeservcie(){

return  new ChangeServcieImpl(); 
    }



@Bean
public ICompareResourcesService icompareresourcesservice(){

return  new ICompareResourcesServiceImpl(); 
    }



@Bean
public ComputeService computeservice(){

return  new ComputeServiceImpl(); 
    }



@Bean
public CPService cpservice(){

return  new CPServiceImpl(); 
    }



@Bean
public DepartmentService departmentservice(){

return  new DepartmentServiceImpl(); 
    }



@Bean
public DnsService dnsservice(){

return  new DnsServiceImpl(); 
    }



@Bean
public EipService eipservice(){

return  new EipServiceImpl(); 
    }



@Bean
public ElbService elbservice(){

return  new ElbServiceImpl(); 
    }



@Bean
public Es3Service es3service(){

return  new Es3ServiceImpl(); 
    }



@Bean
public EsgService esgservice(){

return  new EsgServiceImpl(); 
    }



@Bean
public FailureService failureservice(){

return  new FailureServiceImpl(); 
    }



@Bean
public HostServerService hostserverservice(){

return  new HostServerServiceImpl(); 
    }



@Bean
public ImportService importservice(){

return  new ImportServiceImpl(); 
    }



@Bean
public IpPoolService ippoolservice(){

return  new IpPoolServiceImpl(); 
    }



@Bean
public LocationService locationservice(){

return  new LocationServiceImpl(); 
    }



@Bean
public MdnService mdnservice(){

return  new MdnServiceImpl(); 
    }



@Bean
public OneCmdbUtilService onecmdbutilservice(){

return  new OneCmdbUtilServiceImpl(); 
    }



@Bean
public OperateService operateservice(){

return  new OperateServiceImpl(); 
    }



@Bean
public RedmineUtilService redmineutilservice(){

return  new RedmineUtilServiceImpl(); 
    }



@Bean
public IResourcesJsonService iresourcesjsonservice(){

return  new IResourcesJsonServiceImpl(); 
    }



@Bean
public ICompareResourcesService icompareresourcesservice(){

return  new ICompareResourcesServiceImpl(); 
    }



@Bean
public ServerModelService servermodelservice(){

return  new ServerModelServiceImpl(); 
    }



@Bean
public ServiceTagService servicetagservice(){

return  new ServiceTagServiceImpl(); 
    }



@Bean
public TemplateMailService templatemailservice(){

return  new TemplateMailServiceImpl(); 
    }



@Bean
public VlanService vlanservice(){

return  new VlanServiceImpl(); 
    }



@Bean
public PropertiesLoader propertiesloader(){

return  new PropertiesLoaderImpl(); 
    }



}