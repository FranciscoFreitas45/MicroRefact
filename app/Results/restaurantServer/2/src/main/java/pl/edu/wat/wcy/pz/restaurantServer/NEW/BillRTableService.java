package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.BillRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import java.util.*;
@Service
public class BillRTableService {

@Autowired
 private BillRepository billrepository;


public List<Bill> getBills(Long rTableId){
return billrepository.getBills(rTableId);
}


public void setBills(Long rTableId,List<Bill> bills){
billrepository.setBills(rTableId,bills);
}


public void addBill(Long rTableId,Bill bill){
billrepository.save(bill);
}


}