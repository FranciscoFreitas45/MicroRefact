package pt.iscte.hospital.DTO;
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

// Constructors
public User() {
}public User(Long userId, String name, String username, String sex, LocalDate birthday, String address, String postCode, String city, String nationality, String documentType, Long documentNumber, Long nif, Long patientNumber, Long phone, String email, String password, String photoURL) {
    this.userId = userId;
    this.name = name;
    this.username = username;
    this.sex = sex;
    this.birthday = birthday;
    this.address = address;
    this.postCode = postCode;
    this.city = city;
    this.nationality = nationality;
    this.documentType = documentType;
    this.documentNumber = documentNumber;
    this.nif = nif;
    this.patientNumber = patientNumber;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.photoURL = photoURL;
}
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


}