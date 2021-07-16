package com.pl.edu.wat.wcy.pz.restaurantServer.service;

import com.DTO.RTable;
import com.Interface.RTableRepository;
import com.pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import com.pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import com.pl.edu.wat.wcy.pz.restaurantServer.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BillService {

 private BillRepository billRepository;

 private RTableRepository rTableRepository;


public List<Bill> getBills(){
    return billRepository.findAll();
}


public void updateBill(Long id,Bill bill){
    Optional<Bill> oldBill = billRepository.findById(id);
    if (!oldBill.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found.");
    else {
        bill.setBillId(id);
        billRepository.save(bill);
    }
}


public void deleteBillById(Long id){
    if (billRepository.findById(id).isPresent()) {
        billRepository.deleteById(id);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found.");
    }
}


public Optional<Bill> getBillById(Long id){
    Optional<Bill> bill = billRepository.findById(id);
    if (bill.isPresent()) {
        return bill;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found.");
    }
}


public void addBill(Bill bill){
    System.out.println(bill);
    RTable rTable = rTableRepository.findById(bill.getRTableId());
    System.out.println("$$$$$$ " +rTable);
    //if (rTable.isPresent()) {
        if (rTable.getStatus().equals("BUSY")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This table is already being served.");
        } else {
            bill.setValue(0);
            bill.setStatus("IN_PROGRESS");
            bill.setCreationDate(new Date());
            rTable.setStatus2("BUSY");
            billRepository.save(bill);
        }
    //} else {
      //  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found.");
    //}
}


public List<BillPosition> getBillBillPositions(Long id){
    Optional<Bill> bill = getBillById(id);
    return bill.get().getBillPositions();
}


}