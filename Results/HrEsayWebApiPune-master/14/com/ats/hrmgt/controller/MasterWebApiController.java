import com.ats.hrmgt.claim.repository.GetClaimApplyAuthwiseRepo;
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.RandomString;
import com.ats.hrmgt.model;
import com.ats.hrmgt.model.claim.GetClaimApplyAuthwise;
import com.ats.hrmgt.repository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
public class MasterWebApiController {

@Autowired
 private UserRepo userRepo;

@Autowired
 private SettingRepo settingRepo;

@Autowired
 private LocationRepository locationRepository;

@Autowired
 private EmpTypeRepository empTypeRepository;

@Autowired
 private LeaveSummaryRepository leaveSummaryRepository;

@Autowired
 private LoginResponseRepo loginResponseRepo;

@Autowired
 private CalculateYearRepository calculateYearRepository;

@Autowired
 private DashboardRepo dashboardRepo;

@Autowired
 private LeaveDetailRepo leaveDetailRepo;

@Autowired
 private GetLeaveStatusRepo getLeaveStatusRepo;

@Autowired
 private EmployeeInfoRepository employeeInfoRepository;

@Autowired
 private LoginResponseRepository loginResponseRepository;

@Autowired
 private MailByUsernameRepo mailByUsernameRepo;

@Autowired
 private GetClaimApplyAuthwiseRepo getClaimApplyAuthwiseRepo;

 static  String senderEmail;

 static  String senderPassword;

 static  String mailsubject;

 private  String DOC_URL;

 public  String leaveDocSaveUrl;


@RequestMapping(value = { "/updateEmpProfPicForApp" }, method = RequestMethod.POST)
@ResponseBody
public Info updateEmpProfPicForApp(int empId,MultipartFile profilePic){
    Info info = new Info();
    try {
        // String imageSaveUrl = "/home/lenovo/Downloads/myUploads/";
        String imageSaveUrl = "/opt/tomcat/webapps/hr/";
        String getImageSaveUrl = "http://ifbthrms.infrabeat.com:8181/hr/";
        String[] allowExt = { "jpg", "jpeg", "gif", "png" };
        int isResize = 0;
        int width = 0;
        int hieght = 0;
        int isCheckSize = 0;
        int imageSizeMax = 0;
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Boolean ret = false;
        if (ret == false) {
            if (profilePic.getOriginalFilename() != "") {
                System.out.println("before split " + profilePic.getOriginalFilename());
                String tempImg = profilePic.getOriginalFilename().split("\\.")[0];
                System.out.println("After split " + tempImg);
                tempImg = tempImg.concat(".").concat("png");
                System.out.println("final split " + tempImg);
                String imageName = "";
                imageName = dateTimeInGMT.format(date) + "_" + tempImg;
                try {
                    // start
                    String extension = FilenameUtils.getExtension(tempImg);
                    if (ArrayUtils.contains(allowExt, extension.toLowerCase())) {
                        Path path = Paths.get(imageSaveUrl + imageName);
                        byte[] bytes = profilePic.getBytes();
                        Files.write(path, bytes);
                        if (isResize == 1) {
                            Image img = null;
                            BufferedImage tempPNG = null;
                            File newFilePNG = null;
                            // System.out.println("File " + imageName);
                            img = ImageIO.read(new File(imageSaveUrl + imageName));
                            tempPNG = EmailUtility.resizeImage(img, width, hieght);
                            newFilePNG = new File(imageSaveUrl + "thumbnail" + imageName);
                            ImageIO.write(tempPNG, extension, newFilePNG);
                        // System.out.println("DONE");
                        }
                        info.setError(false);
                        info.setMsg("Upload Successfully ");
                        // System.err.println("imageName " + imageName);
                        // int up = employeeInfoRepository.updateEmpProfPic(empId, imageName);
                        int up = 0;
                        if (up > 0) {
                            info.setError(false);
                            info.setMsg(imageName);
                        } else {
                            info.setError(true);
                            info.setMsg("failed");
                        }
                    } else {
                        info.setError(true);
                        info.setMsg("Error While Uploading Image");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return info;
}


@RequestMapping(value = { "/getDashboardCount" }, method = RequestMethod.POST)
@ResponseBody
public DashboardCount getDashboardCount(int empId){
    DashboardCount dashboardCount = new DashboardCount();
    try {
        CalenderYear calendearYear = new CalenderYear();
        calendearYear = calculateYearRepository.findByIsCurrent(1);
        int curYrId = 0;
        if (calendearYear != null) {
            curYrId = calendearYear.getCalYrId();
        }
        dashboardCount = dashboardRepo.getDashboardCount(empId, curYrId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dashboardCount;
}


@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
@ResponseBody
public LoginResponse loginUser(String userName,String pass){
    LoginResponse loginResponse = new LoginResponse();
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(pass.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        loginResponse = loginResponseRepository.loginProcess(userName, hashtext);
        if (loginResponse == null) {
            loginResponse = new LoginResponse();
            loginResponse.setError(true);
        // loginResponse.setMsg("record Not found");
        } else {
            loginResponse.setError(false);
        // loginResponse.setMsg("Record Found");
        }
    } catch (Exception e) {
        e.printStackTrace();
        loginResponse = new LoginResponse();
        loginResponse.setError(true);
    // loginResponse.setMsg("record Not found");
    }
    return loginResponse;
}


@RequestMapping(value = { "/updateIsVistStatus" }, method = RequestMethod.POST)
@ResponseBody
public Info updateIsVistStatus(int empId,String password){
    Info info = new Info();
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        int update = userRepo.updateIsVistStatus(empId, hashtext);
        if (update > 0) {
            info.setError(false);
            info.setMsg("successfully password changed");
        } else {
            info.setError(true);
            info.setMsg("failed password changed");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return info;
}


@RequestMapping(value = { "/getClaimStatusList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetClaimApplyAuthwise> getClaimStatusList(int empId){
    List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
    try {
        list = getClaimApplyAuthwiseRepo.getClaimStatusList(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.POST)
@ResponseBody
public List<GetLeaveStatus> getLeaveTrailList(int leaveId){
    List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
    try {
        leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(leaveId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return leaveStatus;
}


@RequestMapping(value = { "/getEmployeeListByEmpId" }, method = RequestMethod.POST)
@ResponseBody
public List<EmployeeInfo> getEmployeeListByEmpId(int empId){
    List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
    try {
        list = employeeInfoRepository.getEmployeeListByEmpId(empId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
@ResponseBody
public Info photoUpload(MultipartFile[] uploadfile,List<String> imageName,String type){
    System.out.println("HELLO------------------------");
    System.err.println(" no  of files to push " + uploadfile.length);
    Info info = new Info();
    // System.out.println("File Name " + imageName.toString());
    try {
        saveUploadedFiles(uploadfile, imageName, type);
        info.setError(false);
        info.setMsg("File uploaded successfully");
    } catch (IOException e) {
        e.printStackTrace();
        info.setError(true);
        info.setMsg("File upload failed");
    }
    return info;
}


public void saveUploadedFiles(MultipartFile[] files,List<String> imageName,String type){
    try {
        for (int i = 0; i < files.length; i++) {
            Path path = null;
            if (type.equalsIgnoreCase("1")) {
                String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);
                path = Paths.get(DOC_URL + name);
            } else if (type.equalsIgnoreCase("3")) {
                String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);
                path = Paths.get(leaveDocSaveUrl + name);
            }
            byte[] bytes = files[i].getBytes();
            Files.write(path, bytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping(value = { "/checkUserName" }, method = RequestMethod.POST)
@ResponseBody
public Info checkUserName(String inputValue){
    Info emailRes = new Info();
    try {
        MailByUsername mailByUsername = mailByUsernameRepo.getUserByEmailId(inputValue);
        RandomString randomString = new RandomString();
        String password = randomString.nextString();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        String finalmsg = "Your Password is:" + password + "\n DO NOT REPLY to this EMAIL-ID - contact HR.PUNE@infrabeat.com.";
        emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", mailByUsername.getEmail(), " HRMS Password Recovery", mailByUsername.getEmail(), finalmsg);
        int update = userRepo.updateIsVistStatus(mailByUsername.getEmpId(), hashtext);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return emailRes;
}


@RequestMapping(value = { "/getLeaveStatusList" }, method = RequestMethod.POST)
@ResponseBody
public List<LeaveDetail> getLeaveStatuslist(int empId,List<Integer> status){
    List<LeaveDetail> resList = new ArrayList<LeaveDetail>();
    try {
        CalenderYear calendearYear = new CalenderYear();
        calendearYear = calculateYearRepository.findByIsCurrent(1);
        int curYrId = 0;
        if (calendearYear != null) {
            curYrId = calendearYear.getCalYrId();
        }
        // System.err.println("list "+leaveDetailRepo.getLeaveStatus1(empId, status,
        // curYrId));
        resList = leaveDetailRepo.getLeaveStatus1(empId, status, curYrId);
        if (resList != null) {
            for (int i = 0; i < resList.size(); i++) {
                List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
                leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(resList.get(i).getLeaveId());
                resList.get(i).setGetLeaveStatusList(leaveStatus);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resList;
}


}