package main.model.response.results;
 import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class Error extends ResultResponse{

 private  Object errors;


}