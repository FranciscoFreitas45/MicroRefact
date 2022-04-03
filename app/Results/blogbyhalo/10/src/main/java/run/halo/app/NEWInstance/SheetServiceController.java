package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SheetServiceController {

 private SheetService sheetservice;


@GetMapping
("/getById")
public Object getById(@RequestParam(name = "Object") Object Object){
  return sheetservice.getById(Object);
}


@GetMapping
("/convertToMinimal")
public Object convertToMinimal(@RequestParam(name = "Object") Object Object){
  return sheetservice.convertToMinimal(Object);
}


@GetMapping
("/pageBy")
public Object pageBy(@RequestParam(name = "Object") Object Object){
  return sheetservice.pageBy(Object);
}


@GetMapping
("/convertToListVo")
public Page<SheetListVO> convertToListVo(@RequestParam(name = "sheetPage") Page<Sheet> sheetPage){
  return sheetservice.convertToListVo(sheetPage);
}


@GetMapping
("/convertToDetailVo")
public SheetDetailVO convertToDetailVo(@RequestParam(name = "sheet") Sheet sheet){
  return sheetservice.convertToDetailVo(sheet);
}


@PutMapping
("/publishVisitEvent")
public void publishVisitEvent(@RequestParam(name = "sheetId") Integer sheetId){
sheetservice.publishVisitEvent(sheetId);
}


@GetMapping
("/getBySlug")
public Object getBySlug(@RequestParam(name = "Object") Object Object){
  return sheetservice.getBySlug(Object);
}


@GetMapping
("/countByStatus")
public Object countByStatus(@RequestParam(name = "Object") Object Object){
  return sheetservice.countByStatus(Object);
}


@GetMapping
("/createBy")
public Sheet createBy(@RequestParam(name = "sheet") Sheet sheet,@RequestParam(name = "metas") Set<SheetMeta> metas,@RequestParam(name = "autoSave") boolean autoSave){
  return sheetservice.createBy(sheet,metas,autoSave);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return sheetservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return sheetservice.createInBatch(Object);
}


@GetMapping
("/countVisit")
public Object countVisit(@RequestParam(name = "Object") Object Object){
  return sheetservice.countVisit(Object);
}


@GetMapping
("/countLike")
public Object countLike(@RequestParam(name = "Object") Object Object){
  return sheetservice.countLike(Object);
}


}