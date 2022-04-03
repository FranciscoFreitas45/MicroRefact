package com.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.app.Interface.BranchService;
import com.app.Interface.BranchServiceImpl;
import com.app.Interface.TeacherService;
import com.app.Interface.TeacherServiceImpl;
import com.app.Interface.ClassesService;
import com.app.Interface.ClassesServiceImpl;
import com.app.Interface.DivisionService;
import com.app.Interface.DivisionServiceImpl;
import com.app.Interface.InstituteService;
import com.app.Interface.InstituteServiceImpl;
import com.app.Interface.ScheduleService;
import com.app.Interface.ScheduleServiceImpl;
import com.app.Interface.LoginService;
import com.app.Interface.LoginServiceImpl;
import com.app.Interface.PermissionsService;
import com.app.Interface.PermissionsServiceImpl;
import com.app.Interface.StudentService;
import com.app.Interface.StudentServiceImpl;
import com.app.Interface.ParentService;
import com.app.Interface.ParentServiceImpl;
import com.app.Interface.SubjectService;
import com.app.Interface.SubjectServiceImpl;
import com.app.Interface.SubjectDivCompositService;
import com.app.Interface.SubjectDivCompositServiceImpl;
import com.app.Interface.ExamSubjectStudentCompositTableService;
import com.app.Interface.ExamSubjectStudentCompositTableServiceImpl;
import com.app.Interface.ResultService;
import com.app.Interface.ResultServiceImpl;
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
public BranchService branchservice(){

return  new BranchServiceImpl(); 
    }



@Bean
public TeacherService teacherservice(){

return  new TeacherServiceImpl(); 
    }



@Bean
public ClassesService classesservice(){

return  new ClassesServiceImpl(); 
    }



@Bean
public DivisionService divisionservice(){

return  new DivisionServiceImpl(); 
    }



@Bean
public InstituteService instituteservice(){

return  new InstituteServiceImpl(); 
    }



@Bean
public ScheduleService scheduleservice(){

return  new ScheduleServiceImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



@Bean
public PermissionsService permissionsservice(){

return  new PermissionsServiceImpl(); 
    }



@Bean
public StudentService studentservice(){

return  new StudentServiceImpl(); 
    }



@Bean
public ParentService parentservice(){

return  new ParentServiceImpl(); 
    }



@Bean
public SubjectService subjectservice(){

return  new SubjectServiceImpl(); 
    }



@Bean
public SubjectDivCompositService subjectdivcompositservice(){

return  new SubjectDivCompositServiceImpl(); 
    }



@Bean
public ExamSubjectStudentCompositTableService examsubjectstudentcomposittableservice(){

return  new ExamSubjectStudentCompositTableServiceImpl(); 
    }



@Bean
public ResultService resultservice(){

return  new ResultServiceImpl(); 
    }



}