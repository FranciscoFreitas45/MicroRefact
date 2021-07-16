package com.NEWInstance;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import com.pl.edu.wat.wcy.pz.restaurantServer.repository.RTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin

public class RTableController2 {

    private RTableRepository rtablerepository;

    @PutMapping("/setStatus/{id}")
    public void setStatus(@PathVariable(name = "id") Long id, @RequestParam(name="status") String status){
        rtablerepository.setStatus(id,status);
    }


}