package pl.edu.wat.wcy.pz.restaurantServer.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Bill;
import pl.edu.wat.wcy.pz.restaurantServer.Request.BillRequest;

 import java.util.List;

   public class BillRequestImpl implements BillRequest{

     private final RestTemplate restTemplate = new RestTemplate();


    public List<Bill> getBills(Long rTableId){
     List aux = restTemplate.getForObject("http://2/RTable/{id}/Bill/getBills",List.class,rTableId);
    return aux;
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

