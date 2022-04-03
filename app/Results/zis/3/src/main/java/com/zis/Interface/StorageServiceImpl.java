package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.StorageService;
public class StorageServiceImpl implements StorageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<StorageIoDetail> findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(Integer repoId,List<Integer> batchIds,String ioDetailType,List<String> DetailStatusList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn"))
    .queryParam("repoId",repoId)
    .queryParam("batchIds",batchIds)
    .queryParam("ioDetailType",ioDetailType)
    .queryParam("DetailStatusList",DetailStatusList)
;  List<StorageIoDetail> aux = restTemplate.getForObject(builder.toUriString(), List<StorageIoDetail>.class);

 return aux;
}


}