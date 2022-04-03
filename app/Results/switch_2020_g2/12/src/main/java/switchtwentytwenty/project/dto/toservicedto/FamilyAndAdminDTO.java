package switchtwentytwenty.project.dto.toservicedto;
 import lombok;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class FamilyAndAdminDTO {

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String personName;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String birthDate;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String vat;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String houseNumber;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String street;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String city;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String country;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String zipCode;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  List<String> phoneNumbers;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String email;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String familyName;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String username;

@Setter(AccessLevel.PROTECTED)
@Getter
 private  String password;


}