package switchtwentytwenty.project.dto.indto;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class TransferInDTO {

@Setter
@Getter
 private  String senderId;

@Setter
@Getter
 private  String receiverId;

@Setter
@Getter
 private  String originAccountId;

@Setter
@Getter
 private  String destinationAccountId;

@Setter
@Getter
 private  double amount;

@Setter
@Getter
 private  String categoryId;

@Setter
@Getter
 private  String description;

@Setter
@Getter
 private  String date;


}