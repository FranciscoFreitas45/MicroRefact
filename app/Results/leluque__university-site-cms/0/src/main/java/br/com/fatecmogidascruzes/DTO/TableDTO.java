package br.com.fatecmogidascruzes.DTO;
 import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
public class TableDTO {

 private  Integer recordsTotal;

 private  Integer recordsFiltered;

 private  Integer draw;

 private  List<T> data;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public void add(T row){
    this.data.add(row);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))

.queryParam("row",row)
;
restTemplate.put(builder.toUriString(),null);
}


}