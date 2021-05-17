import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class TreeCommonImpl implements TreeCommon{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsOrganizationInfoLeftDTO> TreeStructureUtil(List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOList,List<QmsOrganizationInfoDTO> parentNodeList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("TreeStructureUtil"))
    .queryParam("qmsOrganizationInfoDTOList",qmsOrganizationInfoDTOList)
    .queryParam("parentNodeList",parentNodeList);
  List<QmsOrganizationInfoLeftDTO> aux = restTemplate.getForObject(builder.toUriString(), List<QmsOrganizationInfoLeftDTO>.class)

 return aux;
}


}