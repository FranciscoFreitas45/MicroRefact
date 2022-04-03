package lk.sliit.csse.procurementsystem.services;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.sliit.csse.procurementsystem.repositories.MaterialRequestRepository;
@Service("orderService")
public class OrderService {

@Autowired
 private  MaterialRequestRepository materialRequestRepository;


}