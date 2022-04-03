package switchtwentytwenty.project.dto.toservicedto;
 import java.util.Objects;
public class CreatePaymentDTO {

 private  String familyID;

 private  String personID;

 private  String accountID;

 private  double amount;

 private  String description;

 private  String date;

 private  String categoryID;

 private  String familyID;

 private  String personID;

 private  String accountID;

 private  double amount;

 private  String description;

 private  String date;

 private  String categoryID;

// Constructor Method
/**
 * sole constructor
 * @param builder
 */
private CreatePaymentDTO(PaymentBuilder builder) {
    this.familyID = builder.familyID;
    this.personID = builder.personID;
    this.accountID = builder.accountID;
    this.amount = builder.amount;
    this.description = builder.description;
    this.date = builder.date;
    this.categoryID = builder.categoryID;
}
public PaymentBuilder date(String date){
    this.date = date;
    return this;
}


public String getPersonID(){
    return personID;
}


public PaymentBuilder amount(double amount){
    this.amount = amount;
    return this;
}


public String getCategoryID(){
    return categoryID;
}


public PaymentBuilder description(String description){
    this.description = description;
    return this;
}


public String getDescription(){
    return description;
}


public PaymentBuilder familyID(String familyID){
    this.familyID = familyID;
    return this;
}


public PaymentBuilder accountID(String accountID){
    this.accountID = accountID;
    return this;
}


public String getAccountID(){
    return accountID;
}


public CreatePaymentDTO build(){
    return new CreatePaymentDTO(this);
}


@Override
public int hashCode(){
    return Objects.hash(familyID, personID, accountID, amount, description, date, categoryID);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof CreatePaymentDTO))
        return false;
    CreatePaymentDTO that = (CreatePaymentDTO) o;
    return Double.compare(that.amount, amount) == 0 && Objects.equals(familyID, that.familyID) && Objects.equals(personID, that.personID) && Objects.equals(accountID, that.accountID) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(categoryID, that.categoryID);
}


public String getDate(){
    return date;
}


public PaymentBuilder personID(String personID){
    this.personID = personID;
    return this;
}


public String getFamilyID(){
    return familyID;
}


public PaymentBuilder categoryID(String categoryID){
    this.categoryID = categoryID;
    return this;
}


public double getAmount(){
    return amount;
}


}