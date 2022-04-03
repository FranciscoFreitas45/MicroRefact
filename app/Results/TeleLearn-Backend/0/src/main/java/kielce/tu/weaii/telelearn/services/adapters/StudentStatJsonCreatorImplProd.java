package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.services.ports.StudentStatJsonCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
@Service
@RequiredArgsConstructor
@Profile("prod")
public class StudentStatJsonCreatorImplProd implements StudentStatJsonCreator{

 private  JdbcTemplate jdbcTemplate;


@SneakyThrows
@Override
public String getStudentStatJson(Long studentId,LocalDate today){
    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("get_student_stats");
    SqlParameterSource parameters = new MapSqlParameterSource("studentid", studentId).addValue("today", today);
    return simpleJdbcCall.executeObject(Object.class, parameters).toString();
}


}