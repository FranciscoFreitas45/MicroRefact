package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SystemService;
public class SystemServiceImpl implements SystemService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<DictEntity> queryDict(String dicTable,String dicCode,String dicText){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryDict"))
    .queryParam("dicTable",dicTable)
    .queryParam("dicCode",dicCode)
    .queryParam("dicText",dicText)
;  List<DictEntity> aux = restTemplate.getForObject(builder.toUriString(), List<DictEntity>.class);

 return aux;
}


public Object loadAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getEntity(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEntity"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByQueryString(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByQueryString"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getDataGridReturn(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDataGridReturn"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getListByCriteriaQuery(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListByCriteriaQuery"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void addLog(String LogContent,Short operatetype,Short loglevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLog"))
    .queryParam("LogContent",LogContent)
    .queryParam("operatetype",operatetype)
    .queryParam("loglevel",loglevel)
;
  restTemplate.put(builder.toUriString(), null);
}


public void refleshTypeGroupCach(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/refleshTypeGroupCach"))
;
  restTemplate.put(builder.toUriString(), null);
}


public void refleshTypesCach(TSType type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/refleshTypesCach"))
    .queryParam("type",type)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object findByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void refreshTypeGroupAndTypes(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/refreshTypeGroupAndTypes"))
;
  restTemplate.put(builder.toUriString(), null);
}


public Object findHql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findUniqueByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUniqueByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findForJdbc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForJdbc"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getList(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getList"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object comTree(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/comTree"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object ComboTree(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ComboTree"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteAllEntitie(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllEntitie"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object treegrid(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/treegrid"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object singleResult(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/singleResult"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object saveOrUpdate(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveOrUpdate"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getCountForJdbc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountForJdbc"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getCountForJdbcParam(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountForJdbcParam"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public TSTypegroup getTypeGroup(String typegroupcode,String typgroupename){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTypeGroup"))
    .queryParam("typegroupcode",typegroupcode)
    .queryParam("typgroupename",typgroupename);
  TSTypegroup aux = restTemplate.getForObject(builder.toUriString(), TSTypegroup.class);

 return aux;
}


public TSType getType(String typecode,String typename,TSTypegroup tsTypegroup){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getType"))
    .queryParam("typecode",typecode)
    .queryParam("typename",typename)
    .queryParam("tsTypegroup",tsTypegroup);
  TSType aux = restTemplate.getForObject(builder.toUriString(), TSType.class);

 return aux;
}


public Object uploadFile(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadFile"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object updateEntitie(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateEntitie"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object executeSql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/executeSql"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findListbySql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListbySql"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object viewOrDownloadFile(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/viewOrDownloadFile"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object createXml(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createXml"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object parserXml(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parserXml"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getAutoList(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAutoList"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}