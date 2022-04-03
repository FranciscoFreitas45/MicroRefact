package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InwarehousePositionController {

 private InwarehousePositionDao inwarehousepositiondao;


@PutMapping
("/setInwarehouseId/{id}")
public void setInwarehouseId(@PathVariable(name = "id") Integer id,@RequestParam(name = "inwarehouseId") Integer inwarehouseId){
 inwarehousepositiondao.setInwarehouseId(id,inwarehouseId);
}


@PutMapping
("/setPositionLabel/{id}")
public void setPositionLabel(@PathVariable(name = "id") Integer id,@RequestParam(name = "positionLabel") String positionLabel){
 inwarehousepositiondao.setPositionLabel(id,positionLabel);
}


@PutMapping
("/setCurrentAmount/{id}")
public void setCurrentAmount(@PathVariable(name = "id") Integer id,@RequestParam(name = "currentAmount") Integer currentAmount){
 inwarehousepositiondao.setCurrentAmount(id,currentAmount);
}


@PutMapping
("/setGmtCreate/{id}")
public void setGmtCreate(@PathVariable(name = "id") Integer id,@RequestParam(name = "gmtCreate") Date gmtCreate){
 inwarehousepositiondao.setGmtCreate(id,gmtCreate);
}


@PutMapping
("/setGmtModify/{id}")
public void setGmtModify(@PathVariable(name = "id") Integer id,@RequestParam(name = "gmtModify") Date gmtModify){
 inwarehousepositiondao.setGmtModify(id,gmtModify);
}


}