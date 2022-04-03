package kielce.tu.weaii.telelearn.services.adapters;
 import com.fasterxml.jackson.databind.ObjectMapper;
import kielce.tu.weaii.telelearn.services.ports.StudentStatJsonCreator;
import kielce.tu.weaii.telelearn.services.ports.StudentStatsService;
import kielce.tu.weaii.telelearn.views.StudentStatsView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
@Service
@RequiredArgsConstructor
@Profile("!prod")
public class StudentStatJsonCreatorImplDev implements StudentStatJsonCreator{

 private  StudentStatsService studentStatsService;


@SneakyThrows
@Override
public String getStudentStatJson(Long studentId,LocalDate today){
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(StudentStatsView.from(studentStatsService.getStudentStat(studentId, today)));
}


}