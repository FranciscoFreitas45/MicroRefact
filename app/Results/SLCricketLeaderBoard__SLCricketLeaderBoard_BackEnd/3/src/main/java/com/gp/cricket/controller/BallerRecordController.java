package com.gp.cricket.controller;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.service.BallerRecordService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class BallerRecordController {

@Autowired
 private BallerRecordService ballerRecordService;


@PostMapping("recordBallerRecord")
public Integer recordBallerRecord(BallerRecord record){
    BallerRecord result = ballerRecordService.saveRecord(record);
    if (result != null) {
        return 1;
    }
    return 0;
}


}