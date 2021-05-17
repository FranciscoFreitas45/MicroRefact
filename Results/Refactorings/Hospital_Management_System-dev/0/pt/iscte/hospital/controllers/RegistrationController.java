import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.hospital.entities.Nationality;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.exceptions.ImageSizeException;
import pt.iscte.hospital.exceptions.ImageTypeException;
import pt.iscte.hospital.services;
import pt.iscte.hospital.services.user.UserService;
import pt.iscte.hospital.services.validation.UserValidationService;
import java.io.IOException;
import java.util.List;
@Controller
public class RegistrationController {

@Autowired
 private  UserService userService;

@Autowired
 private  ImageUploadService imageUploadService;

@Autowired
 private  NationalityService nationalityService;

@Autowired
 private  UserValidationService userValidationService;

@Autowired
 private  RegistrationService registrationService;


@PostMapping(value = "/public/register-user")
public String registerUser(Patient user,MultipartFile file,ModelMap mpError,String confirmarPassword2){
    List<Nationality> nationalities = nationalityService.findAll();
    mpError.put("nationalities", nationalities);
    userValidationService.clear().setUser(user).validName().validPassword().samePassword(confirmarPassword2).validPhone().validPostCode().validSex().validEmail().validEmail2().validUsername().validDocumentType().validDocumentNumber().validDocumentNumberUnique().validPatientNumber().validPatientNumberUnique().validNif().validNifUnique().validCity().validBirthday().validNationality().validAddress().validAccount();
    if (file != null && !file.isEmpty() && !file.getContentType().equals("application/octet-stream")) {
        try {
            String photoURL = imageUploadService.uploadImage(file, user.getUsername());
            user.setPhotoURL(photoURL);
        } catch (IOException e) {
            userValidationService.notValidPhotoUpload();
        } catch (ImageTypeException e) {
            userValidationService.notValidImageType();
        } catch (ImageSizeException e) {
            userValidationService.notValidImageSize();
        }
    } else {
        if (user.getSex().equals("Masculino")) {
            user.setPhotoURL("user-male.jpg");
        } else {
            user.setPhotoURL("user-female.jpg");
        }
    }
    if (!userValidationService.isValid()) {
        mpError.addAllAttributes(userValidationService.getErrorModelMap());
        mpError.put("user", user);
        return "public/registration";
    }
    // Add user to database
    registrationService.encryptPassword(user);
    userService.addUser(user);
    return "redirect:/public/login";
}


@GetMapping(value = "/public/registration")
public String showRegistrationPage(ModelMap modelMap){
    List<Nationality> nationalities = nationalityService.findAll();
    modelMap.put("nationalities", nationalities);
    modelMap.put("user", new Patient());
    return "public/registration";
}


}