package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Form;
public interface FormRequest {

   public Form getActionOnObj(Long idVMYL);
   public void setActionOnObj(Form actionOnObj,Long idVMYL);
}