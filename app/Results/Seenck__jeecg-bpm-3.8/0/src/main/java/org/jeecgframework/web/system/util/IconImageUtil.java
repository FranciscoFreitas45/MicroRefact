package org.jeecgframework.web.system.util;
 import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.core.common.model.json.DataGrid;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io;
import java.util.List;
import java.util.UUID;
public class IconImageUtil {


public boolean delAllFile(String path){
    boolean flag = false;
    File file = new File(path);
    if (!file.exists()) {
        return flag;
    }
    if (!file.isDirectory()) {
        return flag;
    }
    String[] tempList = file.list();
    File temp = null;
    for (int i = 0; i < tempList.length; i++) {
        if (path.endsWith(File.separator)) {
            temp = new File(path + tempList[i]);
        } else {
            temp = new File(path + File.separator + tempList[i]);
        }
        if (temp.isFile()) {
            temp.delete();
        }
        if (temp.isDirectory()) {
            // 先删除文件夹里面的文件
            delAllFile(path + "/" + tempList[i]);
            // 再删除空文件夹
            delFolder(path + "/" + tempList[i]);
            flag = true;
        }
    }
    return flag;
}


public void byte2image(byte[] data,File file){
    if (data.length < 3)
        return;
    FileImageOutputStream imageOutput = null;
    String fileName = null;
    try {
        imageOutput = new FileImageOutputStream(file);
        imageOutput.write(data, 0, data.length);
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            imageOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public void delFolder(String folderPath){
    try {
        // 删除完里面所有内容
        delAllFile(folderPath);
        File folder = new File(folderPath);
        // 删除空文件夹
        folder.delete();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void convertDataGrid(DataGrid dataGrid,HttpServletRequest request){
    String fileDirName = request.getSession().getServletContext().getRealPath("") + File.separator + "temp";
    delFolder(fileDirName);
    File fileDir = new File(fileDirName);
    if (!fileDir.exists()) {
        fileDir.mkdirs();
    }
    try {
        List list = dataGrid.getResults();
        for (Object obj : list) {
            TSIcon icon = (TSIcon) obj;
            String fileName = "icon" + UUID.randomUUID() + "." + icon.getExtend();
            File tempFile = new File(fileDirName + File.separator + fileName);
            if (icon.getIconContent() != null) {
                byte2image(icon.getIconContent(), tempFile);
                icon.setIconPath("temp/" + fileName);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}