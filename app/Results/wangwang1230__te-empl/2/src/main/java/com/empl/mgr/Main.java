package com.empl.mgr;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.empl.mgr.Interface.ChooseEducationDao;
import com.empl.mgr.Interface.ChooseEducationDaoImpl;
import com.empl.mgr.Interface.ChooseMarriageDao;
import com.empl.mgr.Interface.ChooseMarriageDaoImpl;
import com.empl.mgr.Interface.ChooseNationalDao;
import com.empl.mgr.Interface.ChooseNationalDaoImpl;
import com.empl.mgr.Interface.ChoosePoliticsDao;
import com.empl.mgr.Interface.ChoosePoliticsDaoImpl;
import com.empl.mgr.Interface.PositionDao;
import com.empl.mgr.Interface.PositionDaoImpl;
import com.empl.mgr.Interface.DepartmentDao;
import com.empl.mgr.Interface.DepartmentDaoImpl;
import com.empl.mgr.Interface.AddressDao;
import com.empl.mgr.Interface.AddressDaoImpl;
import com.empl.mgr.Interface.ProvinceDao;
import com.empl.mgr.Interface.ProvinceDaoImpl;
import com.empl.mgr.Interface.CityDao;
import com.empl.mgr.Interface.CityDaoImpl;
import com.empl.mgr.Interface.CountyDao;
import com.empl.mgr.Interface.CountyDaoImpl;
import com.empl.mgr.Interface.TownshipDao;
import com.empl.mgr.Interface.TownshipDaoImpl;
import com.empl.mgr.Interface.VillageDao;
import com.empl.mgr.Interface.VillageDaoImpl;
import com.empl.mgr.Interface.EmployeesTrainingLogDao;
import com.empl.mgr.Interface.EmployeesTrainingLogDaoImpl;
import com.empl.mgr.Interface.TrainingDao;
import com.empl.mgr.Interface.TrainingDaoImpl;
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
public ChooseEducationDao chooseeducationdao(){

return  new ChooseEducationDaoImpl(); 
    }



@Bean
public ChooseMarriageDao choosemarriagedao(){

return  new ChooseMarriageDaoImpl(); 
    }



@Bean
public ChooseNationalDao choosenationaldao(){

return  new ChooseNationalDaoImpl(); 
    }



@Bean
public ChoosePoliticsDao choosepoliticsdao(){

return  new ChoosePoliticsDaoImpl(); 
    }



@Bean
public PositionDao positiondao(){

return  new PositionDaoImpl(); 
    }



@Bean
public DepartmentDao departmentdao(){

return  new DepartmentDaoImpl(); 
    }



@Bean
public AddressDao addressdao(){

return  new AddressDaoImpl(); 
    }



@Bean
public ProvinceDao provincedao(){

return  new ProvinceDaoImpl(); 
    }



@Bean
public CityDao citydao(){

return  new CityDaoImpl(); 
    }



@Bean
public CountyDao countydao(){

return  new CountyDaoImpl(); 
    }



@Bean
public TownshipDao townshipdao(){

return  new TownshipDaoImpl(); 
    }



@Bean
public VillageDao villagedao(){

return  new VillageDaoImpl(); 
    }



@Bean
public EmployeesTrainingLogDao employeestraininglogdao(){

return  new EmployeesTrainingLogDaoImpl(); 
    }



@Bean
public TrainingDao trainingdao(){

return  new TrainingDaoImpl(); 
    }



}