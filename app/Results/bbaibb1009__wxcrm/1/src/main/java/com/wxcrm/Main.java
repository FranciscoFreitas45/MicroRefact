package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IMenuService;
import com.wxcrm.Interface.IMenuServiceImpl;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IAdminServiceImpl;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWeixinServiceImpl;
import com.wxcrm.Interface.IShopRoleService;
import com.wxcrm.Interface.IShopRoleServiceImpl;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IJdbcDaoImpl;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IHibernateDaoImpl;
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
public IMenuService imenuservice(){

return  new IMenuServiceImpl(); 
    }



@Bean
public IAdminService iadminservice(){

return  new IAdminServiceImpl(); 
    }



@Bean
public IWeixinService iweixinservice(){

return  new IWeixinServiceImpl(); 
    }



@Bean
public IShopRoleService ishoproleservice(){

return  new IShopRoleServiceImpl(); 
    }



@Bean
public IJdbcDao ijdbcdao(){

return  new IJdbcDaoImpl(); 
    }



@Bean
public IHibernateDao ihibernatedao(){

return  new IHibernateDaoImpl(); 
    }



}