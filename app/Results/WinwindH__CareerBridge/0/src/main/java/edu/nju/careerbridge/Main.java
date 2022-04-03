package edu.nju.careerbridge;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import edu.nju.careerbridge.Interface.UserBasicMessageRepository;
import edu.nju.careerbridge.Interface.UserBasicMessageRepositoryImpl;
import edu.nju.careerbridge.Interface.EducationRepository;
import edu.nju.careerbridge.Interface.EducationRepositoryImpl;
import edu.nju.careerbridge.Interface.HonorRepository;
import edu.nju.careerbridge.Interface.HonorRepositoryImpl;
import edu.nju.careerbridge.Interface.ProjectExperienceRepository;
import edu.nju.careerbridge.Interface.ProjectExperienceRepositoryImpl;
import edu.nju.careerbridge.Interface.SkillRepository;
import edu.nju.careerbridge.Interface.SkillRepositoryImpl;
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
public UserBasicMessageRepository userbasicmessagerepository(){

return  new UserBasicMessageRepositoryImpl(); 
    }



@Bean
public EducationRepository educationrepository(){

return  new EducationRepositoryImpl(); 
    }



@Bean
public HonorRepository honorrepository(){

return  new HonorRepositoryImpl(); 
    }



@Bean
public ProjectExperienceRepository projectexperiencerepository(){

return  new ProjectExperienceRepositoryImpl(); 
    }



@Bean
public SkillRepository skillrepository(){

return  new SkillRepositoryImpl(); 
    }



}