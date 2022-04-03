package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.RiverDao;
public class RiverDaoImpl implements RiverDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public River findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  River aux = restTemplate.getForObject(builder.toUriString(), River.class);

 return aux;
}


public Object create(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Drop> getDrops(Long riverId,DropFilter filter,int page,int dropCount,Account queryingAccount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDrops"))
    .queryParam("riverId",riverId)
    .queryParam("filter",filter)
    .queryParam("page",page)
    .queryParam("dropCount",dropCount)
    .queryParam("queryingAccount",queryingAccount)
;  List<Drop> aux = restTemplate.getForObject(builder.toUriString(), List<Drop>.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public RiverCollaborator findCollaborator(Long riverId,Long accountId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCollaborator"))
    .queryParam("riverId",riverId)
    .queryParam("accountId",accountId)
;  RiverCollaborator aux = restTemplate.getForObject(builder.toUriString(), RiverCollaborator.class);

 return aux;
}


public RiverCollaborator addCollaborator(River river,Account account,boolean readOnly){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addCollaborator"))
    .queryParam("river",river)
    .queryParam("account",account)
    .queryParam("readOnly",readOnly)
;  RiverCollaborator aux = restTemplate.getForObject(builder.toUriString(), RiverCollaborator.class);

 return aux;
}


public void updateCollaborator(RiverCollaborator collaborator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateCollaborator"))
    .queryParam("collaborator",collaborator)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public RiverDrop findRiverDrop(Long id,Long dropId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRiverDrop"))
    .queryParam("id",id)
    .queryParam("dropId",dropId)
;  RiverDrop aux = restTemplate.getForObject(builder.toUriString(), RiverDrop.class);

 return aux;
}


public List<River> findAll(String searchTerm,int count,int page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("searchTerm",searchTerm)
    .queryParam("count",count)
    .queryParam("page",page)
;  List<River> aux = restTemplate.getForObject(builder.toUriString(), List<River>.class);

 return aux;
}


public List<RiverTagTrend> getTrendingTags(Long riverId,TrendFilter trendFilter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTrendingTags"))
    .queryParam("riverId",riverId)
    .queryParam("trendFilter",trendFilter)
;  List<RiverTagTrend> aux = restTemplate.getForObject(builder.toUriString(), List<RiverTagTrend>.class);

 return aux;
}


public List<RiverTagTrend> getTrendingPlaces(Long riverId,TrendFilter trendFilter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTrendingPlaces"))
    .queryParam("riverId",riverId)
    .queryParam("trendFilter",trendFilter)
;  List<RiverTagTrend> aux = restTemplate.getForObject(builder.toUriString(), List<RiverTagTrend>.class);

 return aux;
}


}