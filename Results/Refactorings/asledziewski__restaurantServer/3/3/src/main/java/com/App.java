package com;



import com.Request.BillRequest;
import com.Request.Impl.BillRequestImpl;
import com.Request.Impl.ReservationRequestImpl;
import com.Request.ReservationRequest;
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

/*
    @Bean
    public BillRequest billRequest(){
        return new BillRequestImpl();
    }
    @Bean
    public ReservationRequest reservationRequest(){
        return new ReservationRequestImpl();
    }

 */
}
