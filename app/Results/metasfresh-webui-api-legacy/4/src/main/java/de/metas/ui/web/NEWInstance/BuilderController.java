package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BuilderController {

 private Builder builder;


@GetMapping
("/getLookupTableName")
public Optional<String> getLookupTableName(){
  return builder.getLookupTableName();
}


@GetMapping
("/isSpecialFieldToExcludeFromLayout")
public boolean isSpecialFieldToExcludeFromLayout(){
  return builder.isSpecialFieldToExcludeFromLayout();
}


@GetMapping
("/getWidgetType")
public DocumentFieldWidgetType getWidgetType(){
  return builder.getWidgetType();
}


@GetMapping
("/getFieldMaxLength")
public int getFieldMaxLength(){
  return builder.getFieldMaxLength();
}


@GetMapping
("/getWidgetSize")
public WidgetSize getWidgetSize(){
  return builder.getWidgetSize();
}


@GetMapping
("/getButtonActionDescriptor")
public ButtonFieldActionDescriptor getButtonActionDescriptor(){
  return builder.getButtonActionDescriptor();
}


@GetMapping
("/isQueryIncludedTabOnActivate")
public boolean isQueryIncludedTabOnActivate(){
  return builder.isQueryIncludedTabOnActivate();
}


@GetMapping
("/getFieldName")
public String getFieldName(){
  return builder.getFieldName();
}


@GetMapping
("/hasCharacteristic")
public boolean hasCharacteristic(@RequestParam(name = "c") Characteristic c){
  return builder.hasCharacteristic(c);
}


@GetMapping
("/isPossiblePublicField")
public boolean isPossiblePublicField(){
  return builder.isPossiblePublicField();
}


@GetMapping
("/addCharacteristic")
public Builder addCharacteristic(@RequestParam(name = "c") Characteristic c){
  return builder.addCharacteristic(c);
}


@GetMapping
("/isSupportZoomInto")
public boolean isSupportZoomInto(){
  return builder.isSupportZoomInto();
}


@GetMapping
("/getLookupDescriptor")
public Optional<LookupDescriptor> getLookupDescriptor(){
  return builder.getLookupDescriptor();
}


@GetMapping
("/getReadonlyLogicEffective")
public ILogicExpression getReadonlyLogicEffective(){
  return builder.getReadonlyLogicEffective();
}


@GetMapping
("/addField")
public Builder addField(@RequestParam(name = "fieldBuilder") DocumentFieldDescriptor.Builder fieldBuilder){
  return builder.addField(fieldBuilder);
}


@GetMapping
("/build")
public DocumentEntityDescriptor build(){
  return builder.build();
}


@GetMapping
("/addIncludedEntity")
public Builder addIncludedEntity(@RequestParam(name = "includedEntity") DocumentEntityDescriptor includedEntity){
  return builder.addIncludedEntity(includedEntity);
}


@GetMapping
("/disableDefaultTableCallouts")
public Builder disableDefaultTableCallouts(){
  return builder.disableDefaultTableCallouts();
}


@GetMapping
("/setCaption")
public Builder setCaption(@RequestParam(name = "caption") String caption){
  return builder.setCaption(caption);
}


@GetMapping
("/setValueClass")
public Builder setValueClass(@RequestParam(name = "valueClass") Class<?> valueClass){
  return builder.setValueClass(valueClass);
}


@GetMapping
("/addCallout")
public Builder addCallout(@RequestParam(name = "lambdaCallout") ILambdaDocumentFieldCallout lambdaCallout){
  return builder.addCallout(lambdaCallout);
}


@GetMapping
("/setIsSOTrx")
public Builder setIsSOTrx(@RequestParam(name = "soTrx") Optional<SOTrx> soTrx){
  return builder.setIsSOTrx(soTrx);
}


@GetMapping
("/setDetailId")
public Builder setDetailId(@RequestParam(name = "detailId") DetailId detailId){
  return builder.setDetailId(detailId);
}


@GetMapping
("/setTableName")
public Builder setTableName(@RequestParam(name = "tableName") Optional<String> tableName){
  return builder.setTableName(tableName);
}


}