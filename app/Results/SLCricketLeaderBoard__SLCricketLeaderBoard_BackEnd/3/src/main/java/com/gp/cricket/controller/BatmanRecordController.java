package com.gp.cricket.controller;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.service.BatmanRecordService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class BatmanRecordController {

@Autowired
 private BatmanRecordService batmanRecordService;


@PostMapping("recordBatmanRecord")
public Integer recordBatmanRecord(BatmanRecord record){
    BatmanRecord result = batmanRecordService.saveRecord(record);
    if (result != null) {
        return 1;
    }
    return 0;
}


}