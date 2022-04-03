package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICommonDaoController {

 private ICommonDao icommondao;


@GetMapping
("/getAllDbTableName")
public Object getAllDbTableName(@RequestParam(name = "Object") Object Object){
  return icommondao.getAllDbTableName(Object);
}


@GetMapping
("/getAllDbTableSize")
public Object getAllDbTableSize(@RequestParam(name = "Object") Object Object){
  return icommondao.getAllDbTableSize(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return icommondao.save(Object);
}


@GetMapping
("/saveOrUpdate")
public Object saveOrUpdate(@RequestParam(name = "Object") Object Object){
  return icommondao.saveOrUpdate(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return icommondao.delete(Object);
}


@GetMapping
("/deleteAllEntitie")
public Object deleteAllEntitie(@RequestParam(name = "Object") Object Object){
  return icommondao.deleteAllEntitie(Object);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return icommondao.get(Object);
}


@GetMapping
("/loadAll")
public Object loadAll(@RequestParam(name = "Object") Object Object){
  return icommondao.loadAll(Object);
}


@GetMapping
("/getEntity")
public Object getEntity(@RequestParam(name = "Object") Object Object){
  return icommondao.getEntity(Object);
}


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return icommondao.findUniqueByProperty(Object);
}


@GetMapping
("/findByProperty")
public Object findByProperty(@RequestParam(name = "Object") Object Object){
  return icommondao.findByProperty(Object);
}


@GetMapping
("/singleResult")
public Object singleResult(@RequestParam(name = "Object") Object Object){
  return icommondao.singleResult(Object);
}


@GetMapping
("/deleteEntityById")
public Object deleteEntityById(@RequestParam(name = "Object") Object Object){
  return icommondao.deleteEntityById(Object);
}


@GetMapping
("/updateEntitie")
public Object updateEntitie(@RequestParam(name = "Object") Object Object){
  return icommondao.updateEntitie(Object);
}


@GetMapping
("/findByQueryString")
public Object findByQueryString(@RequestParam(name = "Object") Object Object){
  return icommondao.findByQueryString(Object);
}


@GetMapping
("/updateBySqlString")
public Object updateBySqlString(@RequestParam(name = "Object") Object Object){
  return icommondao.updateBySqlString(Object);
}


@GetMapping
("/findListbySql")
public Object findListbySql(@RequestParam(name = "Object") Object Object){
  return icommondao.findListbySql(Object);
}


@GetMapping
("/findByPropertyisOrder")
public Object findByPropertyisOrder(@RequestParam(name = "Object") Object Object){
  return icommondao.findByPropertyisOrder(Object);
}


@GetMapping
("/getPageList")
public Object getPageList(@RequestParam(name = "Object") Object Object){
  return icommondao.getPageList(Object);
}


@GetMapping
("/getDataTableReturn")
public Object getDataTableReturn(@RequestParam(name = "Object") Object Object){
  return icommondao.getDataTableReturn(Object);
}


@GetMapping
("/getDataGridReturn")
public Object getDataGridReturn(@RequestParam(name = "Object") Object Object){
  return icommondao.getDataGridReturn(Object);
}


@GetMapping
("/getPageListBySql")
public Object getPageListBySql(@RequestParam(name = "Object") Object Object){
  return icommondao.getPageListBySql(Object);
}


@GetMapping
("/getSession")
public Object getSession(@RequestParam(name = "Object") Object Object){
  return icommondao.getSession(Object);
}


@GetMapping
("/findByExample")
public Object findByExample(@RequestParam(name = "Object") Object Object){
  return icommondao.findByExample(Object);
}


@GetMapping
("/getListByCriteriaQuery")
public Object getListByCriteriaQuery(@RequestParam(name = "Object") Object Object){
  return icommondao.getListByCriteriaQuery(Object);
}


@GetMapping
("/uploadFile")
public T uploadFile(@RequestParam(name = "uploadFile") UploadFile uploadFile){
  return icommondao.uploadFile(uploadFile);
}


@GetMapping
("/viewOrDownloadFile")
public HttpServletResponse viewOrDownloadFile(@RequestParam(name = "uploadFile") UploadFile uploadFile){
  return icommondao.viewOrDownloadFile(uploadFile);
}


@GetMapping
("/createXml")
public HttpServletResponse createXml(@RequestParam(name = "importFile") ImportFile importFile){
  return icommondao.createXml(importFile);
}


@PutMapping
("/parserXml")
public void parserXml(@RequestParam(name = "fileName") String fileName){
icommondao.parserXml(fileName);
}


@GetMapping
("/comTree")
public List<ComboTree> comTree(@RequestParam(name = "all") List<TSDepart> all,@RequestParam(name = "comboTree") ComboTree comboTree){
  return icommondao.comTree(all,comboTree);
}


@GetMapping
("/ComboTree")
public List<ComboTree> ComboTree(@RequestParam(name = "all") List all,@RequestParam(name = "comboTreeModel") ComboTreeModel comboTreeModel,@RequestParam(name = "in") List in,@RequestParam(name = "recursive") boolean recursive){
  return icommondao.ComboTree(all,comboTreeModel,in,recursive);
}


@GetMapping
("/treegrid")
public List<TreeGrid> treegrid(@RequestParam(name = "all") List<?> all,@RequestParam(name = "treeGridModel") TreeGridModel treeGridModel){
  return icommondao.treegrid(all,treeGridModel);
}


@GetMapping
("/createQuery")
public Object createQuery(@RequestParam(name = "Object") Object Object){
  return icommondao.createQuery(Object);
}


@GetMapping
("/executeSql")
public Object executeSql(@RequestParam(name = "Object") Object Object){
  return icommondao.executeSql(Object);
}


@GetMapping
("/executeSqlReturnKey")
public Object executeSqlReturnKey(@RequestParam(name = "Object") Object Object){
  return icommondao.executeSqlReturnKey(Object);
}


@GetMapping
("/findForJdbc")
public Object findForJdbc(@RequestParam(name = "Object") Object Object){
  return icommondao.findForJdbc(Object);
}


@GetMapping
("/findForJdbcParam")
public Object findForJdbcParam(@RequestParam(name = "Object") Object Object){
  return icommondao.findForJdbcParam(Object);
}


@GetMapping
("/findObjForJdbc")
public Object findObjForJdbc(@RequestParam(name = "Object") Object Object){
  return icommondao.findObjForJdbc(Object);
}


@GetMapping
("/findOneForJdbc")
public Object findOneForJdbc(@RequestParam(name = "Object") Object Object){
  return icommondao.findOneForJdbc(Object);
}


@GetMapping
("/getCountForJdbc")
public Object getCountForJdbc(@RequestParam(name = "Object") Object Object){
  return icommondao.getCountForJdbc(Object);
}


@GetMapping
("/getCountForJdbcParam")
public Object getCountForJdbcParam(@RequestParam(name = "Object") Object Object){
  return icommondao.getCountForJdbcParam(Object);
}


@GetMapping
("/batchSave")
public Object batchSave(@RequestParam(name = "Object") Object Object){
  return icommondao.batchSave(Object);
}


@GetMapping
("/findHql")
public Object findHql(@RequestParam(name = "Object") Object Object){
  return icommondao.findHql(Object);
}


@GetMapping
("/pageList")
public Object pageList(@RequestParam(name = "Object") Object Object){
  return icommondao.pageList(Object);
}


@GetMapping
("/findByDetached")
public Object findByDetached(@RequestParam(name = "Object") Object Object){
  return icommondao.findByDetached(Object);
}


@GetMapping
("/executeProcedure")
public Object executeProcedure(@RequestParam(name = "Object") Object Object){
  return icommondao.executeProcedure(Object);
}


}