package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsProductController {

 private QmsProductRepository qmsproductrepository;


@PutMapping
("/setMaterielId/{id}")
public void setMaterielId(@PathVariable(name = "id") Long id,@RequestParam(name = "materielId") Integer materielId){
 qmsproductrepository.setMaterielId(id,materielId);
}


@PutMapping
("/setMakeUser/{id}")
public void setMakeUser(@PathVariable(name = "id") Long id,@RequestParam(name = "makeUser") String makeUser){
 qmsproductrepository.setMakeUser(id,makeUser);
}


@PutMapping
("/setModifyTime/{id}")
public void setModifyTime(@PathVariable(name = "id") Long id,@RequestParam(name = "modifyTime") ZonedDateTime modifyTime){
 qmsproductrepository.setModifyTime(id,modifyTime);
}


}