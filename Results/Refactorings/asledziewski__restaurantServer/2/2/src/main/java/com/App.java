package com;


import com.Interface.DishService;
import com.Interface.DishServiceImpl;
import com.Interface.RTableRepository;
import com.Interface.RTableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RTableRepository rTableRepository(){
        return new RTableRepositoryImpl();
    }

    @Bean
    public DishService dishService(){
        return new DishServiceImpl();
    }
}
