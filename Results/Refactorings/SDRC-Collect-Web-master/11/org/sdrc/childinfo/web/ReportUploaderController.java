import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.sdrc.childinfo.model.UploadedFile;
import org.sdrc.childinfo.util.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class ReportUploaderController {

@Autowired
 private FileValidator fileValidator;

@Autowired
 private  ResourceLoader resourceLoader;

 private List<String> errMssg;

 private List<String> successMssg;

 private  Logger logger;


@SuppressWarnings("resource")
@RequestMapping("/toolsUpload")
public ModelAndView toolsUploaded(UploadedFile uploadedfile,String tools,BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributes){
    errMssg = new ArrayList<String>();
    successMssg = new ArrayList<String>();
    String path = null;
    if (tools.equals("assessment-tools")) {
        path = request.getServletContext().getRealPath("/resources/TOOLS/ASSESSMENT TOOLS");
    } else if (tools.equals("userguides")) {
        path = request.getServletContext().getRealPath("/resources/TOOLS/USER GUIDES");
    }
    InputStream inputStream = null;
    OutputStream outputStream = null;
    List<MultipartFile> file = new ArrayList<MultipartFile>();
    file = uploadedfile.getFile();
    if (logger.isDebugEnabled()) {
        logger.info("ReportUploaderController : File path : " + path);
        logger.info("ReportUploaderController : File Size : " + file.size());
    }
    List<MultipartFile> validreportFiles = validateReport(file, result);
    if (validreportFiles != null)
        for (MultipartFile toUpload : validreportFiles) {
            try {
                String filename = toUpload.getOriginalFilename();
                inputStream = toUpload.getInputStream();
                File newFile = new File(path + "/" + filename);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                outputStream = new FileOutputStream(newFile);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                successMssg.add("File " + toUpload.getOriginalFilename() + " uploaded successfully.  \n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    redirectAttributes.addFlashAttribute("formErrors", errMssg);
    redirectAttributes.addFlashAttribute("formSuccess", successMssg);
    return new ModelAndView("redirect:tools");
}


@SuppressWarnings("resource")
@RequestMapping("/sopsUpload")
public ModelAndView sopUploaded(UploadedFile uploadedfile,BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributes){
    errMssg = new ArrayList<String>();
    successMssg = new ArrayList<String>();
    String path = request.getServletContext().getRealPath("/resources/SOP");
    System.out.println(path);
    InputStream inputStream = null;
    OutputStream outputStream = null;
    List<MultipartFile> file = new ArrayList<MultipartFile>();
    file = uploadedfile.getFile();
    if (logger.isDebugEnabled()) {
        logger.info("ReportUploaderController : File path : " + path);
        logger.info("ReportUploaderController : File Size : " + file.size());
    }
    List<MultipartFile> validreportFiles = validateReport(file, result);
    if (validreportFiles != null)
        for (MultipartFile toUpload : validreportFiles) {
            try {
                String filename = toUpload.getOriginalFilename();
                inputStream = toUpload.getInputStream();
                File newFile = new File(path + "/" + filename);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                outputStream = new FileOutputStream(newFile);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                successMssg.add("File " + toUpload.getOriginalFilename() + " uploaded successfully.  \n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    redirectAttributes.addFlashAttribute("formErrors", errMssg);
    redirectAttributes.addFlashAttribute("formSuccess", successMssg);
    return new ModelAndView("redirect:sops");
}


public List<MultipartFile> validateReport(List<MultipartFile> uploadedFile,Errors errors){
    List<MultipartFile> validreportFiles = new ArrayList<MultipartFile>();
    for (int i = 0; i < uploadedFile.size(); i++) {
        MultipartFile eachfile = uploadedFile.get(i);
        String fileName = eachfile.getOriginalFilename();
        String[] fileNameArray = fileName.split("\\.");
        String fileExtension = fileNameArray != null && fileNameArray.length > 0 ? fileNameArray[fileNameArray.length - 1] : null;
        if (logger.isDebugEnabled()) {
            logger.info("ReportUploaderController validatePresFile : File name : " + fileName);
            logger.info("ReportUploaderController validatePresFile : File fileExtension : " + fileExtension);
        }
        if (fileExtension == null || !(fileExtension.equalsIgnoreCase("xls") || fileExtension.equalsIgnoreCase("xlsx") || fileExtension.equalsIgnoreCase("pdf") || fileExtension.equalsIgnoreCase("doc") || fileExtension.equalsIgnoreCase("docx"))) {
            errMssg.add("File '" + fileName + "' , is invalid file type.");
        } else {
            validreportFiles.add(uploadedFile.get(i));
        }
    }
    if (uploadedFile == null || uploadedFile.size() == 0) {
        errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
    }
    return validreportFiles;
}


@SuppressWarnings("resource")
@RequestMapping("/resourceUpload")
public ModelAndView fileUploaded(UploadedFile uploadedfile,BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributes){
    errMssg = new ArrayList<String>();
    successMssg = new ArrayList<String>();
    String path = request.getServletContext().getRealPath("/resources/RESOURCES");
    System.out.println(path);
    InputStream inputStream = null;
    OutputStream outputStream = null;
    List<MultipartFile> file = new ArrayList<MultipartFile>();
    file = uploadedfile.getFile();
    if (logger.isDebugEnabled()) {
        logger.info("ReportUploaderController : File path : " + path);
        logger.info("ReportUploaderController : File Size : " + file.size());
    }
    List<MultipartFile> validreportFiles = validateReport(file, result);
    if (validreportFiles != null)
        for (MultipartFile toUpload : validreportFiles) {
            try {
                String filename = toUpload.getOriginalFilename();
                inputStream = toUpload.getInputStream();
                File newFile = new File(path + "/" + filename);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                outputStream = new FileOutputStream(newFile);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                successMssg.add("File " + toUpload.getOriginalFilename() + " uploaded successfully.  \n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    redirectAttributes.addFlashAttribute("formErrors", errMssg);
    redirectAttributes.addFlashAttribute("formSuccess", successMssg);
    return new ModelAndView("redirect:resource");
}


}