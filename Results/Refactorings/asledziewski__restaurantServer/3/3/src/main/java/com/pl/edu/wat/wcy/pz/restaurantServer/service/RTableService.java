package com.pl.edu.wat.wcy.pz.restaurantServer.service;

import com.DTO.Bill;
import com.DTO.Reservation;
import com.pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import com.pl.edu.wat.wcy.pz.restaurantServer.repository.RTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class RTableService{

 private RTableRepository rTableRepository;

@Autowired
 private  RestTemplate restTemplate;


public void addRTable(RTable rTable){
    if (rTableRepository.existsByNumber(rTable.getNumber())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Table with this number already exists.");
    } else {
        rTable.setStatus("FREE");
        rTableRepository.save(rTable);
    }
}


public void updateRTable(Long id,RTable rTable){
    Optional<RTable> oldRTable = rTableRepository.findById(id);
    if (!oldRTable.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found.");
    else {
        rTable.setRTableId(id);
        rTableRepository.save(rTable);
    }
}


public List<RTable> getRTablesBySize(int size){
    return rTableRepository.findAllBySize(size);
}


public void deleteRTableById(Long id){
    if (rTableRepository.findById(id).isPresent()) {
        rTableRepository.deleteById(id);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found.");
    }
}


public Optional<RTable> getRTableById(Long id){
    Optional<RTable> rTable = rTableRepository.findById(id);
    if (rTable.isPresent()) {
        return rTable;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found.");
    }
}

/*
public List<Bill> getRTableBills(Long id){
    Optional<RTable> rTable = getRTableById(id);
    return rTable.get().getBills();
}



public List<Reservation> getRTableReservations(Long id){
    Optional<RTable> rTable = getRTableById(id);
    return rTable.get().getReservations();
}

*/
public List<RTable> getRTables(){
 return rTableRepository.findAll();
}
/*

public Bill getCurrentBill(Long id){
    List<Bill> bills = getRTableBills(id);
    Bill temp;
    for (int i = 0; i < bills.size(); i++) {
        temp = bills.get(i);
        if (temp.getStatus().equals("IN_PROGRESS")) {
            return temp;
        }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table don't have active bills.");
}
*/


}