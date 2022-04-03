package Interface;
public interface GridTabVOBasedDocumentEntityDescriptorFactory {

   public ILogicExpression getTabDisplayLogic();
   public void addFieldsCharacteristic(Set<String> fieldNames,Characteristic characteristic);
   public DocumentFieldDescriptor.Builder documentFieldByAD_Field_ID(int adFieldId);
   public String getLabelsFieldName(I_AD_UI_Element uiElement);
   public DocumentFieldDescriptor.Builder documentField(String fieldName);
   public DocumentFieldDescriptor.Builder documentFieldByAD_UI_ElementField(I_AD_UI_ElementField elementFieldRecord);
   public DocumentEntityDescriptor.Builder documentEntity();
   public DocumentFieldDescriptor.Builder getSpecialField_DocumentSummary();
   public Map<Characteristic,DocumentFieldDescriptor.Builder> getSpecialField_DocSatusAndDocAction();
}