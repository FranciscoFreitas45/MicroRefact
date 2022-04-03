package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.Request.MedicineRequest;
import java.util.*;
public class MedicineRequestImpl implements MedicineRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Medicine> getMedicineList(Long cartId){
 List<Medicine> aux = restTemplate.getForObject("http://1/Cart/{id}/Medicine/getMedicineList",List.class,cartId);
return aux;
}


public void setMedicineList(List<Medicine> medicineList,Long cartId){
 restTemplate.put("http://1/Cart/{id}/Medicine/setMedicineList",medicineList,cartId);
 return ;
}


}