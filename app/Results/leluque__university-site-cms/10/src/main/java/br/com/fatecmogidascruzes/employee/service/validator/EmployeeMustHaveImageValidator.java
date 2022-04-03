package br.com.fatecmogidascruzes.employee.service.validator;
 import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import br.com.fatecmogidascruzes.employee.service.web.EmployeeEditDTO;
public class EmployeeMustHaveImageValidator implements ConstraintValidator<EmployeeMustHaveImage, EmployeeEditDTO>{


@Override
public boolean isValid(EmployeeEditDTO employeeEditDTO,ConstraintValidatorContext context){
    if ((employeeEditDTO.getHashString() == null || employeeEditDTO.getHashString().trim().isEmpty()) && (null == employeeEditDTO.getImage() || employeeEditDTO.getImage().isEmpty())) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("image").addConstraintViolation();
        return false;
    }
    return true;
}


@Override
public void initialize(EmployeeMustHaveImage employeeMustHaveImage){
}


}