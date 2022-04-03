package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.StorageRepoInfoDao;
import com.zis.DTO.StorageRepoInfo;
import java.util.*;
public class StorageRepoInfoDaoImpl implements StorageRepoInfoDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<StorageRepoInfo> findByOwnerIdOrderByGmtCreateAsc(Integer ownerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOwnerIdOrderByGmtCreateAsc"))
    .queryParam("ownerId",ownerId)
;  List<StorageRepoInfo> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}