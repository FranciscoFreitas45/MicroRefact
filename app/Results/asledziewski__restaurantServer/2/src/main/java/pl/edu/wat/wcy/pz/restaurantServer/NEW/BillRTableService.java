package pl.edu.wat.wcy.pz.restaurantServer.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.repository.BillPositionRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;

 import java.util.List;

   @Service
   public class BillRTableService {

    @Autowired
     private BillPositionRepository billpositionrepository;


    public List<Bill> getBills(Long rTableId){
    return billpositionrepository.getBills(rTableId);
    }


    public void setBills(Long rTableId, List<Bill> bills){
    billpositionrepository.setBills(rTableId,bills);
    }


    public void addBill(Long rTableId,Bill bill){
    billpositionrepository.addBill(rTableId,bill);
    }
   }

