package com.gp.cricket.controller;
 import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.ClubPayment;
import com.gp.cricket.service.ClubPaymentService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ClubPaymentController {

@Autowired
 private ClubPaymentService clubPaymentService;


@GetMapping("clubpayment/{clubId}")
public ResponseEntity<List<ClubPayment>> getClubpaymentData(Integer clubId){
    List<ClubPayment> objectList = clubPaymentService.getClubpaymentData(clubId);
    if (objectList != null) {
        return ResponseEntity.ok(objectList);
    }
    return ResponseEntity.badRequest().build();
}


@PostMapping("clubpayment")
public ResponseEntity<Integer> addClubPayment(ClubPayment clubPayment){
    Integer result = clubPaymentService.addClubPayment(clubPayment);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


}