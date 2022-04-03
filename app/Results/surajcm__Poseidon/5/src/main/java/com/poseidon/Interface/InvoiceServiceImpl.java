package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.InvoiceService;
public class InvoiceServiceImpl implements InvoiceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Optional<InvoiceVO> fetchInvoiceVOFromTagNo(String tagNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fetchInvoiceVOFromTagNo"))
    .queryParam("tagNo",tagNo)
;  Optional<InvoiceVO> aux = restTemplate.getForObject(builder.toUriString(), Optional<InvoiceVO>.class);

 return aux;
}


}