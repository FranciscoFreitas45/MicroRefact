package com.vino.scaffold.shiro.service;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vino.scaffold.entity.Constants;
import com.vino.scaffold.shiro.entity.User;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
@Service("userExcelService")
public class UserExcelService {

@Autowired
 private  HttpSession session;

@Autowired
 private  UserService userService;


public User getCurrentUser(){
    return (User) session.getAttribute(Constants.CURRENT_USER);
}


public void saveToExcel(String path,Long ids) throws FileNotFoundException{
    List<User> users = userService.find(ids);
    File file = new File(path);
    createExcel(new FileOutputStream(file), users);
}


public List<User> readExcel(File file) throws BiffException{
    List<User> userlist = new ArrayList<User>();
    Workbook rwb = null;
    String cellStr = null;
    User user = null;
    // ����������
    // ��ȡExcel�ļ�����
    if (file == null || !file.exists()) {
        return null;
    }
    InputStream stream = new FileInputStream(file);
    rwb = Workbook.getWorkbook(stream);
    // ��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
    Sheet sheet = rwb.getSheet(0);
    // ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
    for (int i = 1; i < sheet.getRows(); i++) {
        // ����һ��user�����Ӧһ�У� �����洢ÿһ�е�ֵ
        user = new User();
        // ����
        // ��һ�У�ҵ������
        cellStr = sheet.getCell(0, i).getContents().trim();
        user.setUsername(cellStr);
        // ��Ԫ��
        cellStr = sheet.getCell(1, i).getContents().trim();
        user.setUserAlias(cellStr);
        // ����Ĭ��ֵ
        user.setPassword(Constants.DEFAULT_PASSWORD);
        user.setCreateTime(new Date());
        user.setCreatorName(getCurrentUser().getUsername());
        // �Ѹջ�ȡ���д���userlist
        userlist.add(user);
    }
    return userlist;
}


public List<User> getFromExcel(File file) throws BiffException{
    return readExcel(file);
}


public void setUserService(UserService userService){
    this.userService = userService;
}


public void setSession(HttpSession session){
    this.session = session;
}


public void createExcel(OutputStream os,List<User> list){
    String[] heads = { "�û���", "����" };
    // ����������
    WritableWorkbook workbook = null;
    try {
        workbook = Workbook.createWorkbook(os);
        // �����µ�һҳ��sheetֻ���ڹ�������ʹ��
        WritableSheet sheet = workbook.createSheet("user sheet1", 0);
        // ������Ԫ�񼴾���Ҫ��ʾ�����ݣ�new Label(0,0,"�û�") ��һ��������column �ڶ���������row
        // ������������content�����ĸ������ǿ�ѡ��,ΪLabel���������ʽ
        // ͨ��sheet��addCell�������Label��ע��һ��cell/labelֻ��ʹ��һ��addCell
        // ��һ������Ϊ�У��ڶ���Ϊ�У��������ı�����
        // ����ֶ���
        for (int i = 0; i < heads.length; i++) {
            sheet.addCell(new Label(i, 0, heads[i]));
        }
        // ����ֶ�����
        for (int i = 0; i < list.size(); i++) {
            sheet.addCell(new Label(0, i + 1, list.get(i).getUsername()));
            sheet.addCell(new Label(1, i + 1, list.get(i).getUserAlias()));
        }
        workbook.write();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        // ������д��������У�Ȼ��رչ����������ر������
        try {
            if (workbook != null)
                workbook.close();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (os != null)
                os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


public HttpSession getSession(){
    return session;
}


public UserService getUserService(){
    return userService;
}


}