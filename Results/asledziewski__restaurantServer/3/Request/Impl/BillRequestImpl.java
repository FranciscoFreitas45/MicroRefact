import org.springframework.web.client.RestTemplate;
public class BillRequestImpl implements BillRequest{

 private RestTemplate restTemplate;


public List<Bill> getBills(Long rTableId){
 List<Bill> aux = restTemplate.getForObject('http://2/RTable/{id}/Bill/getBills',List<Bill>.class,rTableId);
return aux;
}


public void setBills(List<Bill> bills,Long rTableId){
 restTemplate.put('http://2/RTable/{id}/Bill/setBills',bills,rTableId);
 return ;
}


public void addBill(Bill bill,Long rTableId){
 restTemplate.put('http://2/RTable/{id}/Bill/addBill',bill,rTableId);
 return ;
}


}