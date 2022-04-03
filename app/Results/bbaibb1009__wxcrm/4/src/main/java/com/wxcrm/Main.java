package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IMemcachedServiceImpl;
import com.wxcrm.Interface.IWebsiteService;
import com.wxcrm.Interface.IWebsiteServiceImpl;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IJdbcDaoImpl;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IHibernateDaoImpl;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IMemcachedServiceImpl;
import com.wxcrm.Interface.IWeixinEnterService;
import com.wxcrm.Interface.IWeixinEnterServiceImpl;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IJdbcDaoImpl;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IHibernateDaoImpl;
import com.wxcrm.Interface.IWeiMemberService;
import com.wxcrm.Interface.IWeiMemberServiceImpl;
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
public IWebsiteService iwebsiteservice(){

return  new IWebsiteServiceImpl(); 
    }



@Bean
public IJdbcDao ijdbcdao(){

return  new IJdbcDaoImpl(); 
    }



@Bean
public IHibernateDao ihibernatedao(){

return  new IHibernateDaoImpl(); 
    }



@Bean
public IMemcachedService imemcachedservice(){

return  new IMemcachedServiceImpl(); 
    }



@Bean
public IWeixinEnterService iweixinenterservice(){

return  new IWeixinEnterServiceImpl(); 
    }



@Bean
public IJdbcDao ijdbcdao(){

return  new IJdbcDaoImpl(); 
    }



@Bean
public IHibernateDao ihibernatedao(){

return  new IHibernateDaoImpl(); 
    }



@Bean
public IWeiMemberService iweimemberservice(){

return  new IWeiMemberServiceImpl(); 
    }



}