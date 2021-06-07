import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class BillRTableService {

 private BillRepository billrepository;


public List<Bill> getBills(Long rTableId){
return billrepository.getBills(rTableId);
}


public void setBills(Long rTableId,List<Bill> bills){
billrepository.setBills(rTableId,bills);
}


public void addBill(Long rTableId,Bill bill){
billrepository.addBill(rTableId,bill);
}


}