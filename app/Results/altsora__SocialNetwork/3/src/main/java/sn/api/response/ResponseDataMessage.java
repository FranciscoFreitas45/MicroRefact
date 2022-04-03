package sn.api.response;
 import lombok.Data;
@Data
public class ResponseDataMessage extends AbstractResponse{

 private  String message;

public ResponseDataMessage(String message) {
    this.message = message;
}
public ResponseDataMessage ok(){
    return new ResponseDataMessage("Ok");
}


}