package com.bau.graduateprojects.qrstudentsattendance;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentRepositoryImpl;
import com.bau.graduateprojects.qrstudentsattendance.Interface.CourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.CourseRepositoryImpl;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherRepositoryImpl;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureRepositoryImpl;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherCourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherCourseRepositoryImpl;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureAttendanceRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureAttendanceRepositoryImpl;
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
public StudentRepository studentrepository(){

return  new StudentRepositoryImpl(); 
    }



@Bean
public CourseRepository courserepository(){

return  new CourseRepositoryImpl(); 
    }



@Bean
public TeacherRepository teacherrepository(){

return  new TeacherRepositoryImpl(); 
    }



@Bean
public LectureRepository lecturerepository(){

return  new LectureRepositoryImpl(); 
    }



@Bean
public TeacherCourseRepository teachercourserepository(){

return  new TeacherCourseRepositoryImpl(); 
    }



@Bean
public LectureAttendanceRepository lectureattendancerepository(){

return  new LectureAttendanceRepositoryImpl(); 
    }



}