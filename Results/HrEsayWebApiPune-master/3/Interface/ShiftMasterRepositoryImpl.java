import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ShiftMasterRepositoryImpl implements ShiftMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://2";


public List<ShiftMaster> showShiftListByLocationIds(List<Integer> locationIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/showShiftListByLocationIds"))
    .queryParam("locationIds",locationIds);
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


public List<ShiftMaster> getShiftListByLpad(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShiftListByLpad"))
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


public List<ShiftMaster> getShiftListByLpadForShiftAllocation(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShiftListByLpadForShiftAllocation"))
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


public int deleteShiftTime(int shiftId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteShiftTime"))
    .queryParam("shiftId",shiftId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<ShiftMaster> findBySelfGroupIdAndStatus(int bonusId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySelfGroupIdAndStatus"))
    .queryParam("bonusId",bonusId)
    .queryParam("i",i);
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


public List<ShiftMaster> getShiftListByGroupIdandlocId(int groupId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShiftListByGroupIdandlocId"))
    .queryParam("groupId",groupId);
  List<ShiftMaster> aux = restTemplate.getForObject(builder.toUriString(), List<ShiftMaster>.class)

 return aux;
}


}