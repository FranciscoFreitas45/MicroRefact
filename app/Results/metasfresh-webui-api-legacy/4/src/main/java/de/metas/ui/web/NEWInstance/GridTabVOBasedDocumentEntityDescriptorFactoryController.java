package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GridTabVOBasedDocumentEntityDescriptorFactoryController {

 private GridTabVOBasedDocumentEntityDescriptorFactory gridtabvobaseddocumententitydescriptorfactory;


@GetMapping
("/getTabDisplayLogic")
public ILogicExpression getTabDisplayLogic(){
  return gridtabvobaseddocumententitydescriptorfactory.getTabDisplayLogic();
}


@PutMapping
("/addFieldsCharacteristic")
public void addFieldsCharacteristic(@RequestParam(name = "fieldNames") Set<String> fieldNames,@RequestParam(name = "characteristic") Characteristic characteristic){
gridtabvobaseddocumententitydescriptorfactory.addFieldsCharacteristic(fieldNames,characteristic);
}


@GetMapping
("/documentFieldByAD_Field_ID")
public DocumentFieldDescriptor.Builder documentFieldByAD_Field_ID(@RequestParam(name = "adFieldId") int adFieldId){
  return gridtabvobaseddocumententitydescriptorfactory.documentFieldByAD_Field_ID(adFieldId);
}


@GetMapping
("/getLabelsFieldName")
public String getLabelsFieldName(@RequestParam(name = "uiElement") I_AD_UI_Element uiElement){
  return gridtabvobaseddocumententitydescriptorfactory.getLabelsFieldName(uiElement);
}


@GetMapping
("/documentField")
public DocumentFieldDescriptor.Builder documentField(@RequestParam(name = "fieldName") String fieldName){
  return gridtabvobaseddocumententitydescriptorfactory.documentField(fieldName);
}


@GetMapping
("/documentFieldByAD_UI_ElementField")
public DocumentFieldDescriptor.Builder documentFieldByAD_UI_ElementField(@RequestParam(name = "elementFieldRecord") I_AD_UI_ElementField elementFieldRecord){
  return gridtabvobaseddocumententitydescriptorfactory.documentFieldByAD_UI_ElementField(elementFieldRecord);
}


@GetMapping
("/documentEntity")
public DocumentEntityDescriptor.Builder documentEntity(){
  return gridtabvobaseddocumententitydescriptorfactory.documentEntity();
}


@GetMapping
("/getSpecialField_DocumentSummary")
public DocumentFieldDescriptor.Builder getSpecialField_DocumentSummary(){
  return gridtabvobaseddocumententitydescriptorfactory.getSpecialField_DocumentSummary();
}


@GetMapping
("/getSpecialField_DocSatusAndDocAction")
public Map<Characteristic,DocumentFieldDescriptor.Builder> getSpecialField_DocSatusAndDocAction(){
  return gridtabvobaseddocumententitydescriptorfactory.getSpecialField_DocSatusAndDocAction();
}


}