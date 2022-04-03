package com.ushahidi.swiftriver.core.api.controller;
 import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.CreateFormDTO;
import com.ushahidi.swiftriver.core.api.dto.CreateFormFieldDTO;
import com.ushahidi.swiftriver.core.api.dto.GetFormDTO;
import com.ushahidi.swiftriver.core.api.dto.GetFormFieldDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyFormDTO;
import com.ushahidi.swiftriver.core.api.dto.ModifyFormFieldDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.exception.NotFoundException;
import com.ushahidi.swiftriver.core.api.service.FormService;
@Controller
@RequestMapping("/v1/forms")
public class FormsController extends AbstractController{

 final  Logger logger;

 final  List<String> validTypes;

@Autowired
 private  FormService formService;


@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
@ResponseBody
public GetFormDTO modifyForm(Principal principal,Long id,ModifyFormDTO modifyFormTo){
    return formService.modifyForm(id, modifyFormTo, principal.getName());
}


@RequestMapping(value = "/{formId}/fields/{fieldId}", method = RequestMethod.PUT)
@ResponseBody
public GetFormFieldDTO modifyField(Principal principal,Long formId,Long fieldId,ModifyFormFieldDTO modifyFieldTO){
    // Validation
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (modifyFieldTO.getType() != null && !validTypes.contains(modifyFieldTO.getType())) {
        errors.add(new ErrorField("type", "invalid"));
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return formService.modifyField(formId, fieldId, modifyFieldTO, principal.getName());
}


@RequestMapping(value = "/{id}/fields", method = RequestMethod.POST)
@ResponseBody
public GetFormFieldDTO createField(CreateFormFieldDTO fieldTo,Long id,Principal principal){
    // Validation
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (fieldTo.getTitle() == null) {
        errors.add(new ErrorField("title", "missing"));
    }
    if (fieldTo.getType() == null) {
        errors.add(new ErrorField("type", "missing"));
    } else if (!validTypes.contains(fieldTo.getType())) {
        errors.add(new ErrorField("type", "invalid"));
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return formService.createField(id, fieldTo, principal.getName());
}


@RequestMapping(value = "/{formId}/fields/{fieldId}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteField(Principal principal,Long formId,Long fieldId){
    formService.deleteField(formId, fieldId, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public GetFormDTO getForm(Long id,Principal principal) throws NotFoundException{
    return formService.getForm(id, principal.getName());
}


@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public GetFormDTO createForm(Principal principal,CreateFormDTO formTo){
    // Validate a form name is provided
    List<ErrorField> errors = new ArrayList<ErrorField>();
    if (formTo.getName() == null) {
        errors.add(new ErrorField("name", "missing"));
    }
    // Validate form fields are provided
    if (formTo.getFields() != null) {
        List<CreateFormFieldDTO> fields = formTo.getFields();
        for (int i = 0; i < fields.size(); i++) {
            CreateFormFieldDTO field = fields.get(i);
            if (field.getTitle() == null) {
                errors.add(new ErrorField("fields[" + i + "].title", "missing"));
            }
            if (field.getType() == null) {
                errors.add(new ErrorField("fields[" + i + "].type", "missing"));
            } else if (!validTypes.contains(field.getType())) {
                errors.add(new ErrorField("fields[" + i + "].type", "invalid"));
            }
        }
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    return formService.createForm(formTo, principal.getName());
}


@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
@ResponseBody
public void deleteForm(Long id,Principal principal){
    formService.deleteForm(id, principal.getName());
}


}