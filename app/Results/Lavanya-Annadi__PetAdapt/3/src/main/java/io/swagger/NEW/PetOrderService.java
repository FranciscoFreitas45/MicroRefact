package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.PetRepository;
import io.swagger.model.Pet;
@Service
public class PetOrderService {

@Autowired
 private PetRepository petrepository;


public Pet getPet(Long id){
return petrepository.getPet(id);
}


public void setPet(Long id,Pet pet){
petrepository.setPet(id,pet);
}


}