package es.us.isa.ideas.app.services;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.SocialNetworkConfiguration;
import es.us.isa.ideas.app.repositories.SocialNetworkConfigurationRepository;
import es.us.isa.ideas.app.social.SocialNetwork;
@Service
@Transactional
public class SocialNetworkConfigurationService extends BusinessService<SocialNetworkConfiguration>{

@Autowired
 private SocialNetworkConfigurationRepository socialNetworkConfigurationRepository;


public List<String> missingServices(int researcherId){
    List<String> result = new ArrayList<String>();
    Collection<SocialNetworkConfiguration> configurations = getSocialNetworConfigurations(researcherId);
    boolean found = false;
    for (SocialNetwork network : SocialNetwork.values()) {
        found = false;
        for (SocialNetworkConfiguration snc : configurations) {
            if (snc.getService().equalsIgnoreCase(network.getName()))
                found = true;
        }
        if (!found)
            result.add(network.getName());
    }
    return result;
}


@Override
public JpaRepository<SocialNetworkConfiguration,Integer> getRepository(){
    return socialNetworkConfigurationRepository;
}


public Collection<SocialNetworkConfiguration> getSocialNetworConfigurations(int researcherId){
    return socialNetworkConfigurationRepository.findByActorId(researcherId);
}


}