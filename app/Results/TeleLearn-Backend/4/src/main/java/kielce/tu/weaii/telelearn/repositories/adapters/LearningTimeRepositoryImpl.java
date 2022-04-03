package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.LearningTime;
import kielce.tu.weaii.telelearn.repositories.jpa.LearningTimeJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.LearningTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class LearningTimeRepositoryImpl implements LearningTimeRepository{

 private  LearningTimeJPARepository jpaRepository;


@Override
public LearningTime save(LearningTime learningTime){
    return jpaRepository.saveAndFlush(learningTime);
}


}