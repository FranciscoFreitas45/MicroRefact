package guru.springframework.services;
 import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import guru.springframework.domain;
import guru.springframework.repositories;
@Service("packagetypeservice")
public class PackageTypeServiceImpl implements PackageTypeService{

@Autowired
 private  PackageTypeRepository packagetypeRepository;


@Override
public List<PackageType> getAllPackagetypes(){
    return packagetypeRepository.findAll();
}


}