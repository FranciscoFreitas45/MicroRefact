package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Form;
public interface FormRequest {

   public void setForms(List<Form> forms,long id);
   public List<Form> getForms(long id);
}