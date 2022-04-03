package switchtwentytwenty.project.dto.toservicedto.TransferDTO;
 import java.util.Objects;
public class TransferDTOBuilder {

 private  String senderId;

 private  String receiverId;

 private  String originAccountId;

 private  String destinyAccountId;

 private  double amount;

 private  String categoryId;

 private  String description;

 private  String date;


public TransferDTOBuilder date(String date){
    this.date = date;
    return this;
}


public TransferDTOBuilder senderId(String senderId){
    this.senderId = senderId;
    return this;
}


public TransferDTOBuilder amount(double amount){
    this.amount = amount;
    return this;
}


public TransferDTOBuilder receiverId(String receiverId){
    this.receiverId = receiverId;
    return this;
}


public TransferDTO build(){
    return new TransferDTO(this);
}


public TransferDTOBuilder originAccountId(String originAccountId){
    this.originAccountId = originAccountId;
    return this;
}


public TransferDTOBuilder description(String description){
    this.description = description;
    return this;
}


public TransferDTOBuilder destinationAccountId(String destinyAccountId){
    this.destinyAccountId = destinyAccountId;
    return this;
}


public TransferDTOBuilder categoryId(String categoryId){
    this.categoryId = categoryId;
    return this;
}


}