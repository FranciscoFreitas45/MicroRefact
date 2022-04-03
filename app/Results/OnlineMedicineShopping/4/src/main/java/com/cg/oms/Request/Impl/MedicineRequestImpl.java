package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Medicine;
import com.cg.oms.Request.MedicineRequest;
public class MedicineRequestImpl implements MedicineRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Medicine> getMedicineList(Long orderMedicineId){
 List<Medicine> aux = restTemplate.getForObject("http://1/OrderMedicine/{id}/Medicine/getMedicineList",List<Medicine>.class,orderMedicineId);
return aux;
}


public void setMedicineList(List<Medicine> medicineList,Long orderMedicineId){
 restTemplate.put("http://1/OrderMedicine/{id}/Medicine/setMedicineList",medicineList,orderMedicineId);
 return ;
}


}