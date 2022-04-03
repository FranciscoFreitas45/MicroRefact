package switchtwentytwenty.project.dto.indto.PaymentInDTO;
 import lombok.Getter;
import lombok.Setter;
public class PaymentDTOBuilder {

 private  PaymentInDTO dto;

public PaymentDTOBuilder() {
    this.dto = new PaymentInDTO();
}
public PaymentInDTO.PaymentDTOBuilder withDate(String date){
    dto.setDate(date);
    return this;
}


public PaymentInDTO build(){
    return this.dto;
}


public PaymentInDTO.PaymentDTOBuilder withDesignation(String designation){
    dto.setDesignation(designation);
    return this;
}


public PaymentInDTO.PaymentDTOBuilder withCategoryID(String categoryID){
    dto.setCategoryID(categoryID);
    return this;
}


public PaymentInDTO.PaymentDTOBuilder withAmount(double amount){
    dto.setAmount(amount);
    return this;
}


public PaymentInDTO.PaymentDTOBuilder withPersonID(String id){
    dto.setPersonID(id);
    return this;
}


}