import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.objects.utils.HospitalFormatter;
import javax.persistence;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
 private  Long userId;

 private  String name;

 private  String username;

 private  String sex;

@DateTimeFormat(pattern = "yyyy-MM-dd")
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

@Transient
 private  Set<Message> messages;

@Transient
 private MessageRequestImpl messagerequestimpl;


public void setName(String name){
    this.name = name;
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


public void setPassword(String password){
    this.password = password;
}


public void setPhotoURL(String photoURL){
    this.photoURL = photoURL;
}


public String getName(){
    return name;
}


public void setPostCode(String postCode){
    this.postCode = postCode;
}


public void setPatientNumber(Long patientNumber){
    this.patientNumber = patientNumber;
}


public String getDocumentType(){
    return documentType;
}


public void setNif(Long nif){
    this.nif = nif;
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


public void setDocumentNumber(Long documentNumber){
    this.documentNumber = documentNumber;
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


public void setDocumentType(String documentType){
    this.documentType = documentType;
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


public void setUsername(String username){
    this.username = username;
}


public LocalDate getBirthday(){
    return birthday;
}


public void setAddress(String address){
    this.address = address;
}


public void setCity(String city){
    this.city = city;
}


public void setSex(String sex){
    this.sex = sex;
}


public void setPhone(Long phone){
    this.phone = phone;
}


public Long getPatientNumber(){
    return patientNumber;
}


public void setBirthday(LocalDate birthday){
    this.birthday = birthday;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public String getPhotoURL(){
    return photoURL;
}


public String getSex(){
    return sex;
}


public void setAccount(String account){
    this.account = account;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User{" + "userId=" + userId + ", name='" + name + '\'' + ", username='" + username + '\'' + ", sex='" + sex + '\'' + ", birthday=" + birthday + ", address='" + address + '\'' + ", postCode='" + postCode + '\'' + ", city='" + city + '\'' + ", account='" + account + '\'' + ", nationality='" + nationality + '\'' + ", documentType='" + documentType + '\'' + ", documentNumber=" + documentNumber + ", nif=" + nif + ", patientNumber=" + patientNumber + ", phone=" + phone + ", email='" + email + '\'' + ", password='" + password + '\'' + ", photoURL='" + photoURL + '\'' + '}';
}


public Long getUserId(){
    return userId;
}


public void setNationality(String nationality){
    this.nationality = nationality;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}