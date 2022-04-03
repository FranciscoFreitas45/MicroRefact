package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ICommonDao;
public class ICommonDaoImpl implements ICommonDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object getAllDbTableName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllDbTableName"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getAllDbTableSize(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllDbTableSize"))
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


public Object saveOrUpdate(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveOrUpdate"))
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


public Object deleteAllEntitie(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllEntitie"))
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


public Object findUniqueByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUniqueByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProperty"))
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


public Object deleteEntityById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteEntityById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object updateEntitie(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateEntitie"))
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


public Object updateBySqlString(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateBySqlString"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findListbySql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListbySql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByPropertyisOrder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPropertyisOrder"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getPageList(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPageList"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getDataTableReturn(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDataTableReturn"))
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


public Object getPageListBySql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPageListBySql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getSession(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSession"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByExample(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExample"))
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


public T uploadFile(UploadFile uploadFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadFile"))
    .queryParam("uploadFile",uploadFile)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/viewOrDownloadFile"))
    .queryParam("uploadFile",uploadFile)
;  HttpServletResponse aux = restTemplate.getForObject(builder.toUriString(), HttpServletResponse.class);

 return aux;
}


public HttpServletResponse createXml(ImportFile importFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createXml"))
    .queryParam("importFile",importFile)
;  HttpServletResponse aux = restTemplate.getForObject(builder.toUriString(), HttpServletResponse.class);

 return aux;
}


public void parserXml(String fileName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parserXml"))
    .queryParam("fileName",fileName)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/comTree"))
    .queryParam("all",all)
    .queryParam("comboTree",comboTree)
;  List<ComboTree> aux = restTemplate.getForObject(builder.toUriString(), List<ComboTree>.class);

 return aux;
}


public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in,boolean recursive){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ComboTree"))
    .queryParam("all",all)
    .queryParam("comboTreeModel",comboTreeModel)
    .queryParam("in",in)
    .queryParam("recursive",recursive)
;  List<ComboTree> aux = restTemplate.getForObject(builder.toUriString(), List<ComboTree>.class);

 return aux;
}


public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/treegrid"))
    .queryParam("all",all)
    .queryParam("treeGridModel",treeGridModel)
;  List<TreeGrid> aux = restTemplate.getForObject(builder.toUriString(), List<TreeGrid>.class);

 return aux;
}


public Object createQuery(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createQuery"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object executeSql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/executeSql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object executeSqlReturnKey(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/executeSqlReturnKey"))
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


public Object findForJdbcParam(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findForJdbcParam"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findObjForJdbc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findObjForJdbc"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findOneForJdbc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneForJdbc"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getCountForJdbc(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountForJdbc"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getCountForJdbcParam(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCountForJdbcParam"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object batchSave(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/batchSave"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findHql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object pageList(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/pageList"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findByDetached(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDetached"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object executeProcedure(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/executeProcedure"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}