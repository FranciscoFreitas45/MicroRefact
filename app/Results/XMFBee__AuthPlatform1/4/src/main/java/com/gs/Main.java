package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.CheckinService;
import com.gs.Interface.CheckinServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.AppointmentServiceImpl;
import com.gs.Interface.AccessoriesService;
import com.gs.Interface.AccessoriesServiceImpl;
import com.gs.Interface.CarModelService;
import com.gs.Interface.CarModelServiceImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
import com.gs.Interface.MaterialUseService;
import com.gs.Interface.MaterialUseServiceImpl;
import com.gs.Interface.MaterialReturnService;
import com.gs.Interface.MaterialReturnServiceImpl;
import com.gs.Interface.WorkInfoService;
import com.gs.Interface.WorkInfoServiceImpl;
import com.gs.Interface.SupplyTypeService;
import com.gs.Interface.SupplyTypeServiceImpl;
import com.gs.Interface.MaintainScheduleService;
import com.gs.Interface.MaintainScheduleServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.TrackListService;
import com.gs.Interface.TrackListServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.SupplyService;
import com.gs.Interface.SupplyServiceImpl;
import com.gs.Interface.RoleService;
import com.gs.Interface.RoleServiceImpl;
import com.gs.Interface.PermissionService;
import com.gs.Interface.PermissionServiceImpl;
import com.gs.Interface.RolePermissionService;
import com.gs.Interface.RolePermissionServiceImpl;
import com.gs.Interface.IncomingTypeService;
import com.gs.Interface.IncomingTypeServiceImpl;
import com.gs.Interface.AccessoriesTypeService;
import com.gs.Interface.AccessoriesTypeServiceImpl;
import com.gs.Interface.ComplaintService;
import com.gs.Interface.ComplaintServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.OutgoingTypeService;
import com.gs.Interface.OutgoingTypeServiceImpl;
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
public CheckinService checkinservice(){

return  new CheckinServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public AppointmentService appointmentservice(){

return  new AppointmentServiceImpl(); 
    }



@Bean
public AccessoriesService accessoriesservice(){

return  new AccessoriesServiceImpl(); 
    }



@Bean
public CarModelService carmodelservice(){

return  new CarModelServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public MaterialUseService materialuseservice(){

return  new MaterialUseServiceImpl(); 
    }



@Bean
public MaterialReturnService materialreturnservice(){

return  new MaterialReturnServiceImpl(); 
    }



@Bean
public WorkInfoService workinfoservice(){

return  new WorkInfoServiceImpl(); 
    }



@Bean
public SupplyTypeService supplytypeservice(){

return  new SupplyTypeServiceImpl(); 
    }



@Bean
public MaintainScheduleService maintainscheduleservice(){

return  new MaintainScheduleServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public TrackListService tracklistservice(){

return  new TrackListServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public SupplyService supplyservice(){

return  new SupplyServiceImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public PermissionService permissionservice(){

return  new PermissionServiceImpl(); 
    }



@Bean
public RolePermissionService rolepermissionservice(){

return  new RolePermissionServiceImpl(); 
    }



@Bean
public IncomingTypeService incomingtypeservice(){

return  new IncomingTypeServiceImpl(); 
    }



@Bean
public AccessoriesTypeService accessoriestypeservice(){

return  new AccessoriesTypeServiceImpl(); 
    }



@Bean
public ComplaintService complaintservice(){

return  new ComplaintServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public OutgoingTypeService outgoingtypeservice(){

return  new OutgoingTypeServiceImpl(); 
    }



}