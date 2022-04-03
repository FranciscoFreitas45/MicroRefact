package pl.edu.wat.wcy.pz.restaurantServer.service;
 import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import pl.edu.wat.wcy.pz.restaurantServer.repository.BillPositionRepository;
import java.util.List;
import java.util.Optional;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.DishService;
@AllArgsConstructor
@Service
public class BillPositionService {

 private  BillPositionRepository billPositionRepository;

 private  BillService billService;

 private  DishService dishService;


public List<BillPosition> getBillPositions(){
    return billPositionRepository.findAll();
}


public void updateBillPosition(Long id,BillPosition billPosition){
    Optional<BillPosition> oldBillPosition = billPositionRepository.findById(id);
    if (!oldBillPosition.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BillPosition not found.");
    else {
        billPosition.setBillPositionId(id);
        billPositionRepository.save(billPosition);
    }
}


public void addBillPosition(BillPosition billPosition){
    if (billService.getBillById(billPosition.getBillId()).get().getStatus().equals("PAID")) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bill has already been closed.");
    } else {
        billPosition.setDishId(billPosition.getDishId());
        billService.getBillById(billPosition.getBillId()).get().changeValue(billPosition.getDishId().getPrice());
        billPositionRepository.save(billPosition);
    }
}


public Optional<BillPosition> getBillPositionById(Long id){
    Optional<BillPosition> billPosition = billPositionRepository.findById(id);
    if (billPosition.isPresent()) {
        return billPosition;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BillPosition not found.");
    }
}


public void deleteBillPositionById(Long id){
    Optional<BillPosition> billPosition = billPositionRepository.findById(id);
    if (billPosition.isPresent()) {
        if (billService.getBillById(billPosition.get().getBillId()).get().getStatus().equals("PAID")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bill has already been closed.");
        } else {
            billService.getBillById(billPosition.get().getBillId()).get().changeValue(-billPosition.get().getDishId().getPrice());
            billPositionRepository.deleteById(id);
        }
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BillPosition not found.");
    }
}


}