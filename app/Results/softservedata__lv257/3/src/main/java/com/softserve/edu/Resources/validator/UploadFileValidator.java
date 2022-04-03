package com.softserve.edu.Resources.validator;
 import com.softserve.edu.Resources.entity.Avatar;
import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
public class UploadFileValidator implements Validator{


@Override
public boolean supports(Class<?> clazz){
    return Document.class.equals(clazz);
}


@Override
public void validate(Object target,Errors errors){
    Document document = (Document) target;
    if (document.getFile() == null || document.getFile().getOriginalFilename().equals("")) {
        errors.rejectValue("file", null, "Please select an file to upload!");
        return;
    }
    if (!(document.getFile().getContentType().equals("application/pdf") || document.getFile().getContentType().equals("image/jpeg") || document.getFile().getContentType().equals("image/png"))) {
        errors.rejectValue("file", null, "Please choose only pdf, jpeg or png files for upload!");
        return;
    }
}


}