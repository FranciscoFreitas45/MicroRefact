import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.sdrc.childinfo.model.DataEntryModel;
import org.sdrc.childinfo.model.DataModel;
import org.sdrc.childinfo.model.IUSModel;
import org.sdrc.childinfo.model.UserDetailsModel;
import org.sdrc.childinfo.model.ValueObject;
import org.sdrc.childinfo.service.DataEntryService;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.CustomErrorMessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class DataEntryController {

@Autowired
 private  DataEntryService dataEntryService;

 private  Logger LOGGER;

 private  SimpleDateFormat fullDateFormat;


@ResponseBody
@RequestMapping(value = "/getSubsector", method = RequestMethod.GET)
public List<ValueObject> getSubsector(){
    return dataEntryService.getSubsector();
}


@ResponseBody
@RequestMapping(value = "/getTimePeriod", method = RequestMethod.GET)
public List<ValueObject> getTimePeriod(){
    return dataEntryService.getTimePeriod();
}


@RequestMapping(value = "/downloadSheet", method = RequestMethod.POST)
public void downLoad(String name,HttpServletResponse response,HttpServletRequest request){
    UserDetailsModel user = (UserDetailsModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
    if (user == null && !name.startsWith(Constants.FACTSHEET_NAME)) {
        LOGGER.error("Error description : " + fullDateFormat.format(new Date()) + " : " + "User session expired.");
        throw new AccessDeniedException("User session expired.");
    }
    InputStream inputStream;
    try {
        String fileName = name.replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%2C", ",").replaceAll("\\+", " ").replaceAll("%20", " ").replaceAll("%26", "&").replaceAll("%5C", "/");
        inputStream = new FileInputStream(fileName);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", new java.io.File(fileName).getName());
        response.setHeader(headerKey, headerValue);
        response.setContentType("pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(inputStream, outputStream);
        outputStream.close();
        new File(fileName).delete();
        LOGGER.info(user != null ? user.getUserName() : "no user login required for this action" + " : " + fullDateFormat.format(new Date()) + " : " + fileName.split("/")[fileName.split("/").length - 1] + " : file download success");
    } catch (IOException e) {
        LOGGER.error("Error description : " + fullDateFormat.format(new Date()) + " : " + user.getUserName() + "File not available : " + e);
    }
}


@ResponseBody
@RequestMapping(value = "/downloadExcelFile", method = RequestMethod.POST)
public ValueObject downloadExcelFile(DataEntryModel dataEntryModel,HttpServletRequest request){
    UserDetailsModel user = (UserDetailsModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
    ValueObject object = new ValueObject();
    if (user != null) {
        String path = dataEntryService.downloadExcelFile(dataEntryModel);
        object.setKey(path);
    } else {
        object.setKey("sessionExpired");
    }
    return object;
}


@ResponseBody
@RequestMapping(value = "/downloadFactsheet", method = RequestMethod.POST)
public ValueObject downloadFactsheet(DataEntryModel dataEntryModel){
    String path = dataEntryService.downloadFactsheet(dataEntryModel);
    ValueObject object = new ValueObject();
    object.setKey(path);
    return object;
}


@ResponseBody
@RequestMapping(value = "/getIUS", method = RequestMethod.GET)
public List<IUSModel> getIUS(int id){
    return dataEntryService.getIUS(id);
}


@ResponseBody
@RequestMapping(value = "/getRawDataReport", method = RequestMethod.POST)
public void generateRawDataExcel(ValueObject timeperiod,HttpServletResponse httpServletResponse){
    File file = dataEntryService.generateRawDataExcel(timeperiod);
    try {
        String mimeType;
        mimeType = "application/octet-stream";
        httpServletResponse.setContentType(mimeType);
        httpServletResponse.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        httpServletResponse.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, httpServletResponse.getOutputStream());
    } finally {
        httpServletResponse.getOutputStream().close();
        if (file != null) {
            file.delete();
        }
    }
}


@ResponseBody
@RequestMapping(value = "/excelFileUpload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
public CustomErrorMessageModel excelFileUpload(MultipartFile file,HttpServletRequest request){
    try {
        UserDetailsModel user = (UserDetailsModel) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
        if (user != null) {
            if (("xlsx").equals(FilenameUtils.getExtension(file.getOriginalFilename())))
                return dataEntryService.excelFileUpload(file.getBytes());
            else {
                LOGGER.error("Wrong file format.");
                CustomErrorMessageModel customErrorMessageModel = new CustomErrorMessageModel();
                customErrorMessageModel.setMessage("Wrong file format.");
                customErrorMessageModel.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return customErrorMessageModel;
            }
        } else {
            LOGGER.error("Session out.");
            CustomErrorMessageModel customErrorMessageModel = new CustomErrorMessageModel();
            customErrorMessageModel.setMessage("Your session has expired. Please login again to upload template.");
            customErrorMessageModel.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            return customErrorMessageModel;
        }
    } catch (IOException e) {
        LOGGER.error("Some error occured.");
        CustomErrorMessageModel customErrorMessageModel = new CustomErrorMessageModel();
        customErrorMessageModel.setMessage("Some error occured.");
        customErrorMessageModel.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return customErrorMessageModel;
    }
}


@ResponseBody
@RequestMapping(value = "/getArea", method = RequestMethod.GET)
public List<DataModel> getArea(){
    return dataEntryService.getArea();
}


}