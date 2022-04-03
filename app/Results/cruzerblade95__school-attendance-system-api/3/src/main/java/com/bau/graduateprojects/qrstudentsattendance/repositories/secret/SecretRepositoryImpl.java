package com.bau.graduateprojects.qrstudentsattendance.repositories.secret;
 import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
@Repository
public class SecretRepositoryImpl implements SecretRepository{

 private  SpringJpaSecretRepository jpaSecretRepository;

public SecretRepositoryImpl(SpringJpaSecretRepository jpaSecretRepository) {
    this.jpaSecretRepository = jpaSecretRepository;
}
@Override
public String getKey(Long cId){
    if (isExist(cId)) {
        return jpaSecretRepository.findSecretKeyEntityByCourseId(cId).getSKey();
    }
    throw new ResourceNotFoundException("key not found for this course");
}


public boolean isExist(Long cId){
    return jpaSecretRepository.existsSecretKeyEntityByCourseId(cId);
}


}