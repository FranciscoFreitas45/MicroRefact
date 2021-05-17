import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.sdrc.childinfo.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class FileValidator implements Validator{

@Autowired
 private  StateManager stateManager;

@Autowired
 private ResourceBundleMessageSource workspaceMessageSource;


@Override
public boolean supports(Class<?> arg0){
    // TODO Auto-generated method stub
    return false;
}


@Override
public void validate(Object object,Errors errors){
    Calendar calendar = Calendar.getInstance();
    // Validate User
    // if(Boolean.valueOf(workspaceMessageSource.getMessage(Constants.VALIDATE_USER, null, null))){
    // //			ServletRequestAttributes attr=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    // //			session=attr.getRequest().getSession();
    // //			String userName=(String)session.getAttribute("user");
    // //			System.out.println("my session user "+userName);
    // User user= (User) stateManager.getValue(Constants.USER_PRINCIPAL);
    // if(user!=null){
    // String userName=user.getUserName();
    // System.out.println("User name..."+userName);
    // }else{
    // //errors.rejectValue("file", "uploadForm.selectFile","Invalid User!");
    // }
    // }
    Workspace workspace = (Workspace) object;
    if (workspace.getFile().getSize() == 0) {
        errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_UNSELECTED, null, null));
    } else if (workspace.getFile().getSize() != 0) {
        try {
            Workbook wb = WorkbookFactory.create(new ByteArrayInputStream(workspace.getFile().getBytes()));
            CellReference cellReference = null;
            /*
				 * Validate area code
				 */
            if (Boolean.valueOf(workspaceMessageSource.getMessage(Constants.VALIDATE_FORMCODE, null, null))) {
                cellReference = new CellReference(workspaceMessageSource.getMessage(Constants.FILE_BLOCK_CELL, null, null));
                String areaName = wb.getSheetAt(0).getRow(cellReference.getRow()).getCell(cellReference.getCol()).getStringCellValue();
                if (!areaName.equals(workspace.getAreaName())) {
                    errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_WRONGAREA, null, null));
                    return;
                }
            }
            // Validate Form code
            if (Boolean.valueOf(workspaceMessageSource.getMessage(Constants.VALIDATE_FORMCODE, null, null))) {
                cellReference = new CellReference(workspaceMessageSource.getMessage(Constants.FILE_FORMTYPE_CELL, null, null));
                String code = wb.getSheetAt(0).getRow(cellReference.getRow()).getCell(cellReference.getCol()).getStringCellValue();
                if (!code.equals(workspace.getFormName())) {
                    errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_WRONGCODE, null, null));
                    return;
                }
            }
            // Validate month year
            if (Boolean.valueOf(workspaceMessageSource.getMessage(Constants.VALIDATE_DATE, null, null))) {
                cellReference = new CellReference(workspaceMessageSource.getMessage(Constants.FILE_MONTH_CELL, null, null));
                String month = wb.getSheetAt(0).getRow(cellReference.getRow()).getCell(cellReference.getCol()).getStringCellValue();
                cellReference = new CellReference(workspaceMessageSource.getMessage(Constants.FILE_YEAR_CELL, null, null));
                double year = wb.getSheetAt(0).getRow(cellReference.getRow()).getCell(cellReference.getCol()).getNumericCellValue();
                if (!month.endsWith(Months.values()[calendar.get(Calendar.MONTH)].toString())) {
                    errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_WRONGMONTH, null, null));
                    return;
                }
                if ((int) year != calendar.get(Calendar.YEAR)) {
                    errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_WRONGYEAR, null, null));
                    return;
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_WRONGFILE, null, null));
        } catch (IOException e) {
            errors.rejectValue("file", "uploadForm.selectFile", "IO Failed!");
        } catch (Exception e) {
            e.printStackTrace();
            errors.rejectValue("file", "uploadForm.selectFile", workspaceMessageSource.getMessage(Constants.FILE_INVALIDFILE, null, null));
        }
    }
}


}