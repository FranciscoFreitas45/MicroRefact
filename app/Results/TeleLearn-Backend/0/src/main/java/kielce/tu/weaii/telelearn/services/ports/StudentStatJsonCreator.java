package kielce.tu.weaii.telelearn.services.ports;
 import java.time.LocalDate;
public interface StudentStatJsonCreator {


public String getStudentStatJson(Long studentId,LocalDate today)
;

}