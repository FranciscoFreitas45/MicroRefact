package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.CustomizedEmployeeFormRepository;
import com.qidian.hcm.Interface.CustomizedEmployeeFormRepositoryImpl;
import com.qidian.hcm.Interface.AttachmentRepository;
import com.qidian.hcm.Interface.AttachmentRepositoryImpl;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.EmployeePositionServiceImpl;
import com.qidian.hcm.Interface.EmployeeContactService;
import com.qidian.hcm.Interface.EmployeeContactServiceImpl;
import com.qidian.hcm.Interface.EmployeeEmergencyContactService;
import com.qidian.hcm.Interface.EmployeeEmergencyContactServiceImpl;
import com.qidian.hcm.Interface.EmployeeWorkExperienceService;
import com.qidian.hcm.Interface.EmployeeWorkExperienceServiceImpl;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.FileServiceImpl;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.OrganizationRepositoryImpl;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.EmployeePositionServiceImpl;
import com.qidian.hcm.Interface.GradeService;
import com.qidian.hcm.Interface.GradeServiceImpl;
import com.qidian.hcm.Interface.EmployeeRoleRepository;
import com.qidian.hcm.Interface.EmployeeRoleRepositoryImpl;
import com.qidian.hcm.Interface.EmployeeRoleRepository;
import com.qidian.hcm.Interface.EmployeeRoleRepositoryImpl;
import com.qidian.hcm.Interface.HCMConfig;
import com.qidian.hcm.Interface.HCMConfigImpl;
import com.qidian.hcm.Interface.OrganizationService;
import com.qidian.hcm.Interface.OrganizationServiceImpl;
import com.qidian.hcm.Interface.CustomizedFieldService;
import com.qidian.hcm.Interface.CustomizedFieldServiceImpl;
import com.qidian.hcm.Interface.EmployeePermissionService;
import com.qidian.hcm.Interface.EmployeePermissionServiceImpl;
import com.qidian.hcm.Interface.EmployeePermissionService;
import com.qidian.hcm.Interface.EmployeePermissionServiceImpl;
import com.qidian.hcm.Interface.EmployeePermissionService;
import com.qidian.hcm.Interface.EmployeePermissionServiceImpl;
import com.qidian.hcm.Interface.EmployeeContactService;
import com.qidian.hcm.Interface.EmployeeContactServiceImpl;
import com.qidian.hcm.Interface.EmployeeEmergencyContactService;
import com.qidian.hcm.Interface.EmployeeEmergencyContactServiceImpl;
import com.qidian.hcm.Interface.EmployeeWorkExperienceService;
import com.qidian.hcm.Interface.EmployeeWorkExperienceServiceImpl;
import com.qidian.hcm.Interface.RedisService;
import com.qidian.hcm.Interface.RedisServiceImpl;
import com.qidian.hcm.Interface.EmployeeOtherInfoDTO;
import com.qidian.hcm.Interface.EmployeeOtherInfoDTOImpl;
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
public CustomizedEmployeeFormRepository customizedemployeeformrepository(){

return  new CustomizedEmployeeFormRepositoryImpl(); 
    }



@Bean
public AttachmentRepository attachmentrepository(){

return  new AttachmentRepositoryImpl(); 
    }



@Bean
public EmployeePositionService employeepositionservice(){

return  new EmployeePositionServiceImpl(); 
    }



@Bean
public EmployeeContactService employeecontactservice(){

return  new EmployeeContactServiceImpl(); 
    }



@Bean
public EmployeeEmergencyContactService employeeemergencycontactservice(){

return  new EmployeeEmergencyContactServiceImpl(); 
    }



@Bean
public EmployeeWorkExperienceService employeeworkexperienceservice(){

return  new EmployeeWorkExperienceServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public OrganizationRepository organizationrepository(){

return  new OrganizationRepositoryImpl(); 
    }



@Bean
public EmployeePositionService employeepositionservice(){

return  new EmployeePositionServiceImpl(); 
    }



@Bean
public GradeService gradeservice(){

return  new GradeServiceImpl(); 
    }



@Bean
public EmployeeRoleRepository employeerolerepository(){

return  new EmployeeRoleRepositoryImpl(); 
    }



@Bean
public EmployeeRoleRepository employeerolerepository(){

return  new EmployeeRoleRepositoryImpl(); 
    }



@Bean
public HCMConfig hcmconfig(){

return  new HCMConfigImpl(); 
    }



@Bean
public OrganizationService organizationservice(){

return  new OrganizationServiceImpl(); 
    }



@Bean
public CustomizedFieldService customizedfieldservice(){

return  new CustomizedFieldServiceImpl(); 
    }



@Bean
public EmployeePermissionService employeepermissionservice(){

return  new EmployeePermissionServiceImpl(); 
    }



@Bean
public EmployeePermissionService employeepermissionservice(){

return  new EmployeePermissionServiceImpl(); 
    }



@Bean
public EmployeePermissionService employeepermissionservice(){

return  new EmployeePermissionServiceImpl(); 
    }



@Bean
public EmployeeContactService employeecontactservice(){

return  new EmployeeContactServiceImpl(); 
    }



@Bean
public EmployeeEmergencyContactService employeeemergencycontactservice(){

return  new EmployeeEmergencyContactServiceImpl(); 
    }



@Bean
public EmployeeWorkExperienceService employeeworkexperienceservice(){

return  new EmployeeWorkExperienceServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public EmployeeOtherInfoDTO employeeotherinfodto(){

return  new EmployeeOtherInfoDTOImpl(); 
    }



}