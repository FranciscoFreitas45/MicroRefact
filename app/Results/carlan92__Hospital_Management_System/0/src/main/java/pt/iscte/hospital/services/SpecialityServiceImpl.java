package pt.iscte.hospital.services;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.Speciality;
import pt.iscte.hospital.repositories.SpecialityRepository;
import java.util.List;
@Service
public class SpecialityServiceImpl implements SpecialityService{

 private  SpecialityRepository specialityRepository;

@Autowired
public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
    this.specialityRepository = specialityRepository;
}
@Override
public void addSpeciality(Speciality speciality){
    specialityRepository.save(speciality);
}


@Override
public Speciality findByName(String name){
    return specialityRepository.findByName(name);
}


@Override
public List<Speciality> findAll(){
    return specialityRepository.findAll();
}


}