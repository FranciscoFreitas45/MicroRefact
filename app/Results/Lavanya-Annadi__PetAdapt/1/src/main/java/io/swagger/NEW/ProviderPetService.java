package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.ProviderRepository;
import io.swagger.model.Provider;
@Service
public class ProviderPetService {

@Autowired
 private ProviderRepository providerrepository;


public void setProvider(Long id,Provider provider){
providerrepository.setProvider(id,provider);
}


public Provider getProvider(Long id){
return providerrepository.getProvider(id);
}


}