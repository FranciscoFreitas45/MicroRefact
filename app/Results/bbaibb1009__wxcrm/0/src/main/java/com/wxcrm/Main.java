package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IMemcachedServiceImpl;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IAdminServiceImpl;
import com.wxcrm.Interface.IWeiMemberService;
import com.wxcrm.Interface.IWeiMemberServiceImpl;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWeixinServiceImpl;
import com.wxcrm.Interface.IWebsiteService;
import com.wxcrm.Interface.IWebsiteServiceImpl;
import com.wxcrm.Interface.IAdminService;
import com.wxcrm.Interface.IAdminServiceImpl;
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
public IMemcachedService imemcachedservice(){

return  new IMemcachedServiceImpl(); 
    }



@Bean
public IAdminService iadminservice(){

return  new IAdminServiceImpl(); 
    }



@Bean
public IWeiMemberService iweimemberservice(){

return  new IWeiMemberServiceImpl(); 
    }



@Bean
public IWeixinService iweixinservice(){

return  new IWeixinServiceImpl(); 
    }



@Bean
public IWebsiteService iwebsiteservice(){

return  new IWebsiteServiceImpl(); 
    }



@Bean
public IAdminService iadminservice(){

return  new IAdminServiceImpl(); 
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