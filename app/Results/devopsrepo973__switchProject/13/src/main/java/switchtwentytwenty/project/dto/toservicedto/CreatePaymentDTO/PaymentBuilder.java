package switchtwentytwenty.project.dto.toservicedto.CreatePaymentDTO;
 import java.util.Objects;
public class PaymentBuilder {

 private  String familyID;

 private  String personID;

 private  String accountID;

 private  double amount;

 private  String description;

 private  String date;

 private  String categoryID;


public PaymentBuilder familyID(String familyID){
    this.familyID = familyID;
    return this;
}


public PaymentBuilder date(String date){
    this.date = date;
    return this;
}


public PaymentBuilder accountID(String accountID){
    this.accountID = accountID;
    return this;
}


public PaymentBuilder amount(double amount){
    this.amount = amount;
    return this;
}


public CreatePaymentDTO build(){
    return new CreatePaymentDTO(this);
}


public PaymentBuilder description(String description){
    this.description = description;
    return this;
}


public PaymentBuilder personID(String personID){
    this.personID = personID;
    return this;
}


public PaymentBuilder categoryID(String categoryID){
    this.categoryID = categoryID;
    return this;
}


}