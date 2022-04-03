package com.bau.graduateprojects.qrstudentsattendance.servicies.secret;
 import com.bau.graduateprojects.qrstudentsattendance.repositories.secret.SecretRepository;
import org.springframework.stereotype.Service;
@Service
public class SecretServiceImp implements SecretService{

 private  SecretRepository repository;

public SecretServiceImp(SecretRepository repository) {
    this.repository = repository;
}
@Override
public String getKey(Long cId){
    return repository.getKey(cId);
}


}