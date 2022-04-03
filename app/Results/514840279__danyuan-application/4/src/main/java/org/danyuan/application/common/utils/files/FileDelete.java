package org.danyuan.application.common.utils.files;
 import java.io.File;
public class FileDelete {


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
    for (String element : tempList) {
        if (path.endsWith(File.separator)) {
            temp = new File(path + element);
        } else {
            temp = new File(path + File.separator + element);
        }
        if (temp.isFile()) {
            temp.delete();
        }
        if (temp.isDirectory()) {
            // 先删除文件夹里面的文件
            delAllFile(path + "/" + element);
            // 再删除空文件夹
            delFolder(path + "/" + element);
            flag = true;
        }
    }
    return flag;
}


public void delFolder(String folderPath){
    try {
        // 删除完里面所有内容
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        java.io.File myFilePath = new java.io.File(filePath);
        // 删除空文件夹
        myFilePath.delete();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}