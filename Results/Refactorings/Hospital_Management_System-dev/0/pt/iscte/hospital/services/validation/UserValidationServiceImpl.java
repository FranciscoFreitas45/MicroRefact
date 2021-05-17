import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.Doctor;
import pt.iscte.hospital.entities.Nationality;
import pt.iscte.hospital.entities.Speciality;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.repositories.NationalityRepository;
import pt.iscte.hospital.repositories.user.UserRepository;
import pt.iscte.hospital.repositories.SpecialityRepository;
import pt.iscte.hospital.services.ErrorMessage;
import pt.iscte.hospital.services.ImageUploadService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
@Service
public class UserValidationServiceImpl implements UserValidationService,pt.iscte.hospital.services.validation.UserValidationService{

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  NationalityRepository nationalityRepository;

@Autowired
 private  ImageUploadService imageUploadService;

@Autowired
 private  SpecialityRepository specialityRepository;

 private  User user;

 private  boolean isValid;

 private  ModelMap errorModelMap;


@Override
public UserValidationService validSex(){
    if (user.getSex().matches("Feminino") || user.getSex().matches("Masculino")) {
    } else {
        isValid = false;
        errorModelMap.put("errorMsgSex", ErrorMessage.ERROR_MESSAGE_SEX.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService validAddress(){
    String[] palavras = user.getName().split(" ");
    for (int i = 0; i < palavras.length; i++) {
        if (!palavras[i].matches("[A-Za-zÀ-ÿ'/-]{1,}")) {
            isValid = false;
            errorModelMap.put("errorMsgAddress", ErrorMessage.ERROR_MESSAGE_ADDRESS.getErrorMsg());
            return this;
        }
    }
    return this;
}


@Override
public UserValidationService validPatientNumber(){
    if (user.getPatientNumber() == null) {
        return this;
    }
    String patientNumber = String.valueOf(user.getPatientNumber());
    if (patientNumber.matches("[0-9]{9}")) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgPatientNumber", ErrorMessage.ERROR_MESSAGE_PATIENT_NUMBER.getErrorMsg());
    return this;
}


@Override
public UserValidationService validAccount(){
    if (user.getAccount().matches("Utente") || user.getAccount().matches("Médico") || user.getAccount().matches("Recepcionista")) {
        return this;
    } else {
        isValid = false;
        errorModelMap.put("errorMsgAccount", ErrorMessage.ERROR_MESSAGE_ACCOUNT.getErrorMsg());
        return this;
    }
}


@Override
public UserValidationService notValidImageSize(){
    errorModelMap.put("errorMsgPhotoUpload", String.format(ErrorMessage.IMAGE_SIZE.getErrorMsg(), imageUploadService.getImageMaxSize()));
    isValid = false;
    return this;
}


@Override
public UserValidationService validCity(){
    if (user.getCity().matches("^[A-Za-zÀ-ÿ'][a-zA-ZÀ-ÿ'\\s-]+[a-zA-ZÀ-ÿ']$")) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgCity", ErrorMessage.ERROR_MESSAGE_CITY.getErrorMsg());
    return this;
}


@Override
public UserValidationService validSpeciality(String speciality){
    Speciality userSpeciality = specialityRepository.findByName(speciality);
    if (userSpeciality != null) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgSpeciality", ErrorMessage.ERROR_MESSAGE_SPECIALITY.getErrorMsg());
    return this;
}


@Override
public UserValidationService validDocumentType(){
    if (user.getDocumentType().matches("Bilhete de Identidade") || user.getDocumentType().matches("Cartão de Cidadão") || user.getDocumentType().matches("Passaporte")) {
        return this;
    } else {
        isValid = false;
        errorModelMap.put("errorMsgDocumentType", ErrorMessage.ERROR_MESSAGE_DOCUMENT_TYPE.getErrorMsg());
        return this;
    }
}


@Override
public UserValidationService notValidImageType(){
    errorModelMap.put("errorMsgPhotoUpload", ErrorMessage.IMAGE_TYPE.getErrorMsg());
    isValid = false;
    return this;
}


@Override
public UserValidationService validPassword(){
    if (!user.getPassword().matches(".{1,15}")) {
        isValid = false;
        errorModelMap.put("errorMsgPassword", ErrorMessage.ERROR_MESSAGE_PASSWORD.getErrorMsg());
    }
    return this;
}


@Override
public UserValidationService validEmail(){
    User userUnique = userRepository.findByEmail(user.getEmail());
    if (userUnique != null) {
        isValid = false;
        errorModelMap.put("errorMsgEmail", ErrorMessage.ERROR_MESSAGE_EMAIL.getErrorMsg());
    }
    return this;
}


@Override
public UserValidationService validLicenseNumber(){
    String licenseNumber = String.valueOf(((Doctor) user).getLicenseNumber());
    if (licenseNumber.matches("[0-9]{16}") || licenseNumber.matches("")) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgLicenseNumber", ErrorMessage.ERROR_MESSAGE_LICENSE_NUMBER.getErrorMsg());
    return this;
}


@Override
public UserValidationService setUser(User user){
    this.user = user;
    return this;
}


@Override
public UserValidationService validPhone(){
    String phone = String.valueOf(user.getPhone());
    if (!phone.matches("^9[1236][0-9]{7}$|^2[3-9][1-9][0-9]{6}$|^2[12][0-9]{7}$")) {
        isValid = false;
        errorModelMap.put("errorMsgPhone", ErrorMessage.ERROR_MESSAGE_PHONE.getErrorMsg());
    }
    return this;
}


@Override
public UserValidationService validEmail2(){
    if (user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,3}$")) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgEmail", ErrorMessage.ERROR_MESSAGE_EMAIL2.getErrorMsg());
    return this;
}


@Override
public UserValidationService validNationality(){
    Nationality userNationality = nationalityRepository.findByName(user.getNationality());
    if (userNationality != null) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgNationality", ErrorMessage.ERROR_MESSAGE_NATIONALITY.getErrorMsg());
    return this;
}


@Override
public UserValidationService validNif(){
    String nif = String.valueOf(user.getNif());
    if (nif.matches("[0-9]{9}")) {
        return this;
    }
    isValid = false;
    errorModelMap.put("errorMsgNif", ErrorMessage.ERROR_MESSAGE_NIF.getErrorMsg());
    return this;
}


@Override
public boolean isValid(){
    return isValid;
}


@Override
public UserValidationService clear(){
    user = null;
    isValid = true;
    errorModelMap = new ModelMap();
    return this;
}


@Override
public UserValidationService validDocumentNumber(){
    if (user.getDocumentType().equals("Cartão de Cidadão")) {
        String cc = String.valueOf(user.getDocumentNumber());
        if (cc.matches("[0-9]{8}")) {
            return this;
        }
    }
    if (user.getDocumentType().equals("Bilhete de Identidade")) {
        String bi = String.valueOf(user.getDocumentNumber());
        if (bi.matches("[0-9]{8}")) {
            return this;
        }
    }
    if (user.getDocumentType().equals("Passaporte")) {
        String passaporte = String.valueOf(user.getDocumentNumber());
        if (passaporte.matches("[0-9]{8}")) {
            return this;
        }
    }
    isValid = false;
    errorModelMap.put("errorMsgDocumentNumber", ErrorMessage.ERROR_MESSAGE_DOCUMENT_NUMBER.getErrorMsg());
    return this;
}


@Override
public UserValidationService validNifUnique(){
    User nifUnique = userRepository.findByNifAndAccount(user.getNif(), user.getAccount());
    if (nifUnique != null) {
        isValid = false;
        errorModelMap.put("errorMsgNif", ErrorMessage.ERROR_MESSAGE_NIF2.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService notValidPhotoUpload(){
    errorModelMap.put("errorMsgPhotoUpload", ErrorMessage.PHOTO_UPLOAD.getErrorMsg());
    isValid = false;
    return this;
}


@Override
public ModelMap getErrorModelMap(){
    return errorModelMap;
}


@Override
public UserValidationService validPostCode(){
    if (user.getPostCode().matches("[0-9]{4}[-][0-9]{3}") || user.getPostCode().matches("")) {
    } else {
        isValid = false;
        errorModelMap.put("errorMsgPostCode", ErrorMessage.ERROR_MESSAGE_POST_CODE.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService validPatientNumberUnique(){
    if (user.getPatientNumber() == null) {
        return this;
    }
    User patientNumberUnique = userRepository.findByPatientNumberAndAccount(user.getPatientNumber(), user.getAccount());
    if (patientNumberUnique != null) {
        isValid = false;
        errorModelMap.put("errorMsgPatientNumber", ErrorMessage.ERROR_MESSAGE_PATIENT_NUMBER2.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService validName(){
    String[] names = user.getName().split(" ");
    for (int i = 0; i < names.length; i++) {
        if (!names[i].matches("[A-Za-zÀ-ÿ']{2,}||[e]{1}")) {
            isValid = false;
            errorModelMap.put("errorMsgName", ErrorMessage.ERROR_MESSAGE_NAME.getErrorMsg());
            return this;
        }
    }
    return this;
}


@Override
public UserValidationService validDocumentNumberUnique(){
    User documentNumberUnique = userRepository.findByDocumentNumberAndAccount(user.getDocumentNumber(), user.getAccount());
    if (documentNumberUnique != null) {
        isValid = false;
        errorModelMap.put("errorMsgDocumentNumber", ErrorMessage.ERROR_MESSAGE_DOCUMENT_NUMBER2.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService validBirthday(){
    String pattern = "uuuu-MM-dd";
    DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
    try {
        LocalDate date = LocalDate.parse(user.getBirthdayStr(), df);
        if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.of(1900, 1, 1))) {
            isValid = false;
            errorModelMap.put("errorMsgBirthday", ErrorMessage.ERROR_MESSAGE_BIRTHDAY.getErrorMsg());
            return this;
        }
        return this;
    } catch (DateTimeParseException e) {
        isValid = false;
        errorModelMap.put("errorMsgBirthday", ErrorMessage.ERROR_MESSAGE_BIRTHDAY.getErrorMsg());
        return this;
    }
}


@Override
public UserValidationService validUsername(){
    User userUnique = userRepository.findByUsername(user.getUsername());
    if (userUnique != null) {
        isValid = false;
        errorModelMap.put("errorMsgUsername", ErrorMessage.ERROR_MESSAGE_USERNAME.getErrorMsg());
        return this;
    }
    return this;
}


@Override
public UserValidationService samePassword(String passwordRetyped){
    if (!user.getPassword().equals(passwordRetyped)) {
        isValid = false;
        errorModelMap.put("errorMsgPassword2", ErrorMessage.ERROR_MESSAGE_PASSWORD2.getErrorMsg());
    }
    return this;
}


}