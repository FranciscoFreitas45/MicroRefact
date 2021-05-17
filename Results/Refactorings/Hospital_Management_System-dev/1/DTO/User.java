import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.objects.utils.HospitalFormatter;
import javax.persistence;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
public class User {

 private  Long userId;

 private  String name;

 private  String username;

 private  String sex;

 private  LocalDate birthday;

 private  String address;

 private  String postCode;

 private  String city;

 private  String account;

 private  String nationality;

 private  String documentType;

 private  Long documentNumber;

 private  Long nif;

 private  Long patientNumber;

 private  Long phone;

 private  String email;

 private  String password;

 private  String photoURL;

 private  Set<Message> messages;

 private MessageRequestImpl messagerequestimpl;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Long getPhone(){
    return phone;
}


public int getAge(){
    LocalDate todayDate = LocalDate.now();
    return Period.between(birthday, todayDate).getYears();
}


public String getBirthdayStr(){
    if (birthday == null) {
        return "";
    }
    return birthday.format(Calendar.FORMATTER);
}


public String getName(){
    return name;
}


public String getDocumentType(){
    return documentType;
}


public List<GrantedAuthority> getAuthorities()


public String getUsername(){
    return username;
}


public String getPostCode(){
    return postCode;
}


public String getFirstAndLastName(){
    String[] names = name.split(" ");
    if (names.length > 1) {
        return names[0] + " " + names[names.length - 1];
    } else {
        return names[0];
    }
}


public String getPhoneStr(){
    return HospitalFormatter.formatValue(phone, HospitalFormatter.PHONE);
}


public String getAccount(){
    return account;
}


public String getNationality(){
    return nationality;
}


public String getAddress(){
    return address;
}


public Long getNif(){
    return nif;
}


public String getCity(){
    return city;
}


public Long getDocumentNumber(){
    return documentNumber;
}


public LocalDate getBirthday(){
    return birthday;
}


public Long getPatientNumber(){
    return patientNumber;
}


public String getPassword(){
    return password;
}


public String getPhotoURL(){
    return photoURL;
}


public String getSex(){
    return sex;
}


public String getEmail(){
    return email;
}


public Long getUserId(){
    return userId;
}


public void setPhotoURL(String photoURL){
    this.photoURL = photoURL;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ userId).concat("/setPhotoURL"));

.queryParam("photoURL",photoURL);
restTemplate.put(builder.toUriString(),null);
}


}