package switchtwentytwenty.project.dto.toservicedto;
 import java.util.Objects;
public class TransferDTO {

 private  String senderId;

 private  String receiverId;

 private  String originAccountId;

 private  String destinationAccountId;

 private  double amount;

 private  String categoryId;

 private  String description;

 private  String date;

 private  String senderId;

 private  String receiverId;

 private  String originAccountId;

 private  String destinyAccountId;

 private  double amount;

 private  String categoryId;

 private  String description;

 private  String date;

// Constructor
/**
 * Sole constructor
 */
private TransferDTO(TransferDTOBuilder builder) {
    this.senderId = builder.senderId;
    this.receiverId = builder.receiverId;
    this.originAccountId = builder.originAccountId;
    this.destinationAccountId = builder.destinyAccountId;
    this.amount = builder.amount;
    this.categoryId = builder.categoryId;
    this.description = builder.description;
    this.date = builder.date;
}
public TransferDTOBuilder date(String date){
    this.date = date;
    return this;
}


public String getCategoryID(){
    return this.categoryId;
}


public TransferDTOBuilder description(String description){
    this.description = description;
    return this;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return this.description;
}


public void setReceiverID(String receiverId){
    this.receiverId = receiverId;
}


public TransferDTOBuilder senderId(String senderId){
    this.senderId = senderId;
    return this;
}


public TransferDTOBuilder receiverId(String receiverId){
    this.receiverId = receiverId;
    return this;
}


@Override
public int hashCode(){
    return Objects.hash(senderId, receiverId, originAccountId, destinationAccountId, amount, categoryId, description, date);
}


public String getDate(){
    return this.date;
}


public void setAmount(double amount){
    this.amount = amount;
}


public double getAmount(){
    return this.amount;
}


public TransferDTOBuilder amount(double amount){
    this.amount = amount;
    return this;
}


public void setSenderID(String senderId){
    this.senderId = senderId;
}


public TransferDTOBuilder originAccountId(String originAccountId){
    this.originAccountId = originAccountId;
    return this;
}


public void setDestinationAccountID(String destinyAccountId){
    this.destinationAccountId = destinyAccountId;
}


public String getOriginAccountID(){
    return this.originAccountId;
}


public TransferDTOBuilder destinationAccountId(String destinyAccountId){
    this.destinyAccountId = destinyAccountId;
    return this;
}


public String getDestinationAccountID(){
    return this.destinationAccountId;
}


public TransferDTO build(){
    return new TransferDTO(this);
}


public void setCategoryID(String categoryId){
    this.categoryId = categoryId;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof TransferDTO))
        return false;
    TransferDTO that = (TransferDTO) o;
    return Double.compare(that.amount, amount) == 0 && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(originAccountId, that.originAccountId) && Objects.equals(destinationAccountId, that.destinationAccountId) && Objects.equals(categoryId, that.categoryId) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
}


public String getReceiverID(){
    return this.receiverId;
}


public void setDate(String date){
    this.date = date;
}


public String getSenderID(){
    return this.senderId;
}


public void setOriginAccountID(String originAccountId){
    this.originAccountId = originAccountId;
}


public TransferDTOBuilder categoryId(String categoryId){
    this.categoryId = categoryId;
    return this;
}


}