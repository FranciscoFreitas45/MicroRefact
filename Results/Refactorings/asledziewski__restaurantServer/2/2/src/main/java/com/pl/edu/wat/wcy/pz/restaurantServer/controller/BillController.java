package com.pl.edu.wat.wcy.pz.restaurantServer.controller;

import com.Interface.RTableRepository;
import com.pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import com.pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import com.pl.edu.wat.wcy.pz.restaurantServer.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
public class BillController {

 private BillService billService;

 private RTableRepository rTableRepository;


@GetMapping("/bills")
public Collection<Bill> getBills(){
    return billService.getBills();
}


@PutMapping("/bills/{id}")
public void updateBill(Long id,Bill bill){
    if (bill.getStatus().equals("PAID")) {
        rTableRepository.findById(bill.getRTableId()).setStatus("FREE");
    }
    billService.updateBill(id, bill);
}


@GetMapping(value = "/bills/{id}")
public Bill getBillById(Long id){
    Optional<Bill> bill = billService.getBillById(id);
    return bill.orElse(null);
}


@DeleteMapping("/bills/{id}")
public void deleteBill(Long id){
    Optional<Bill> bill = billService.getBillById(id);
    billService.deleteBillById(id);
}


@GetMapping(value = "/bills/{id}/billPositions")
public Collection<BillPosition> getBillBillPositions(Long id){
    return billService.getBillBillPositions(id);
}


@PostMapping("/bills")
public void addBill(Bill bill){
    billService.addBill(bill);
}


}