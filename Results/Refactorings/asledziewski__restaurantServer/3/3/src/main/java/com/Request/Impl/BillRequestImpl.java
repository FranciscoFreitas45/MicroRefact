package com.Request.Impl;

import com.DTO.Bill;
import com.Request.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillRequestImpl implements BillRequest {

 private RestTemplate restTemplate = new RestTemplate();


public List<Bill> getBills(Long rTableId){
 Bill[] aux = restTemplate.getForObject("http://2/RTable/{id}/Bill/getBills",Bill[].class,rTableId);
 assert aux != null;
 return  Arrays.asList(aux);
}


public void setBills(List<Bill> bills,Long rTableId){
 restTemplate.put("http://2/RTable/{id}/Bill/setBills",bills,rTableId);
 return ;
}


public void addBill(Bill bill,Long rTableId){
 restTemplate.put("http://2/RTable/{id}/Bill/addBill",bill,rTableId);
 return ;
}


}