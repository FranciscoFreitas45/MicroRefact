package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.TreeCommon;
public class TreeCommonImpl implements TreeCommon{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<QmsOrganizationInfoLeftDTO> TreeStructureUtil(List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOList,List<QmsOrganizationInfoDTO> parentNodeList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/TreeStructureUtil"))
    .queryParam("qmsOrganizationInfoDTOList",qmsOrganizationInfoDTOList)
    .queryParam("parentNodeList",parentNodeList)
;  List<QmsOrganizationInfoLeftDTO> aux = restTemplate.getForObject(builder.toUriString(), List<QmsOrganizationInfoLeftDTO>.class);

 return aux;
}


}