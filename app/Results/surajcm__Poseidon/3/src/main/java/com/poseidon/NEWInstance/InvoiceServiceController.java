package com.poseidon.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InvoiceServiceController {

 private InvoiceService invoiceservice;


@GetMapping
("/fetchInvoiceVOFromTagNo")
public Optional<InvoiceVO> fetchInvoiceVOFromTagNo(@RequestParam(name = "tagNo") String tagNo){
  return invoiceservice.fetchInvoiceVOFromTagNo(tagNo);
}


}