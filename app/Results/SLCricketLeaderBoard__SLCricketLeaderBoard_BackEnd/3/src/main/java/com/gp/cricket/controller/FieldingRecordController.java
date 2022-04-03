package com.gp.cricket.controller;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.FieldingRecord;
import com.gp.cricket.service.FieldingRecordService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class FieldingRecordController {

@Autowired
 private FieldingRecordService fieldingRecordService;


@PostMapping("recordFielderRecord")
public Integer recordFielderRecord(FieldingRecord record){
    FieldingRecord result = fieldingRecordService.saveRecord(record);
    if (result != null) {
        return 1;
    }
    return 0;
}


}