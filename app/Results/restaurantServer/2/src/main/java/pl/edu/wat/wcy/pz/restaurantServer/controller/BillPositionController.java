package pl.edu.wat.wcy.pz.restaurantServer.controller;
 import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import pl.edu.wat.wcy.pz.restaurantServer.service.BillPositionService;
import java.util.Collection;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin
public class BillPositionController {

 private  BillPositionService billPositionService;


@GetMapping("/billPositions")
public Collection<BillPosition> getBillPositions(){
    return billPositionService.getBillPositions();
}


@PutMapping("/billPositions/{id}")
public void updateBillPosition(Long id,BillPosition billPosition){
    billPositionService.updateBillPosition(id, billPosition);
}


@DeleteMapping("/billPositions/{id}")
public void deleteBillPosition(Long id){
    Optional<BillPosition> billPosition = billPositionService.getBillPositionById(id);
    billPositionService.deleteBillPositionById(id);
}


@PostMapping("/billPositions")
public void addBillPosition(BillPosition billPosition){
    billPositionService.addBillPosition(billPosition);
}


@GetMapping(value = "/billPositions/{id}")
public BillPosition getBillPositionById(Long id){
    Optional<BillPosition> billPosition = billPositionService.getBillPositionById(id);
    return billPosition.orElse(null);
}


}