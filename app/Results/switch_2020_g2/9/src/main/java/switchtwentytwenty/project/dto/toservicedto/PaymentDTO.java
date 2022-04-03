package switchtwentytwenty.project.dto.toservicedto;
 import lombok;
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String personID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String designation;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String accountID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String categoryID;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  double amount;

@Getter
@Setter(AccessLevel.PROTECTED)
 private  String date;


}