package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.StudentStatsRecord;
import kielce.tu.weaii.telelearn.repositories.jpa.StudentStatsJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.StudentStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class StudentStatRepositoryImpl implements StudentStatsRepository{

 private  StudentStatsJPARepository jpaRepository;


@Override
public List<StudentStatsRecord> getStudentStat(Long studentId){
    return jpaRepository.findAllByStudentId(studentId);
}


@Override
public Optional<StudentStatsRecord> getByScheduleId(Long id){
    return jpaRepository.findByScheduleId(id);
}


@Override
public StudentStatsRecord save(StudentStatsRecord record){
    return jpaRepository.saveAndFlush(record);
}


@Override
public void delete(StudentStatsRecord record){
    jpaRepository.delete(record);
}


}