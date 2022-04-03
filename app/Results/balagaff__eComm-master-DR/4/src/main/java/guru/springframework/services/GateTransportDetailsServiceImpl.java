package guru.springframework.services;
 import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import guru.springframework.domain;
import guru.springframework.repositories;
@Service
public class GateTransportDetailsServiceImpl implements GateTransportDetailsService{

@Autowired
 private  GateTransportDetailsRepository gatetransportdetailsRepository;


@Override
public List<GateTransportDetails> getAllTransportdetails(){
    return gatetransportdetailsRepository.findAll();
}


}