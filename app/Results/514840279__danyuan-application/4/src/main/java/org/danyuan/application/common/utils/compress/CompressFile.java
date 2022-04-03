package org.danyuan.application.common.utils.compress;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class CompressFile {

 private  int BUFFER_SIZE;


public void toZip(List<File> srcFiles,OutputStream out) throws RuntimeException{
    long start = System.currentTimeMillis();
    ZipOutputStream zos = null;
    try {
        zos = new ZipOutputStream(out);
        for (File srcFile : srcFiles) {
            byte[] buf = new byte[BUFFER_SIZE];
            zos.putNextEntry(new ZipEntry(srcFile.getName()));
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("压缩完成，耗时：" + (end - start) + " ms");
    } catch (Exception e) {
        throw new RuntimeException("zip error from ZipUtils", e);
    } finally {
        if (zos != null) {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


public void compress(File sourceFile,ZipOutputStream zos,String name,boolean KeepDirStructure) throws Exception{
    byte[] buf = new byte[BUFFER_SIZE];
    if (sourceFile.isFile()) {
        // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
        zos.putNextEntry(new ZipEntry(name));
        // copy文件到zip输出流中
        int len;
        FileInputStream in = new FileInputStream(sourceFile);
        while ((len = in.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        // Complete the entry
        zos.closeEntry();
        in.close();
    } else {
        File[] listFiles = sourceFile.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            // 需要保留原来的文件结构时,需要对空文件夹进行处理
            if (KeepDirStructure) {
                // 空文件夹的处理
                zos.putNextEntry(new ZipEntry(name + "/"));
                // 没有文件，不需要文件的copy
                zos.closeEntry();
            }
        } else {
            for (File file : listFiles) {
                // 判断是否需要保留原来的文件结构
                if (KeepDirStructure) {
                    // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                    // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                    compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                } else {
                    compress(file, zos, file.getName(), KeepDirStructure);
                }
            }
        }
    }
}


public void generalRARFile(String file,String password){
    // 根据类型，进行相应的解压缩
    String type = file.substring(file.lastIndexOf(".") + 1).toLowerCase();
    String dir = file.replace(file.substring(file.lastIndexOf("/") + 1), "");
    if (type.equals("rar")) {
        System.out.println("解压文件：" + file);
        // 安装的winrar路径
        StringBuffer cmd = new StringBuffer();
        cmd.append("  unrar x -o+ -p");
        // 压缩密码
        cmd.append(password);
        // 
        cmd.append(" ");
        // 原文件的路径
        cmd.append(file);
        cmd.append(" ");
        // 目标路径
        cmd.append(dir);
        System.out.println(cmd.toString());
        try {
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            if (proc.waitFor() != 0) {
                // waitFor将返回exitValue的值0表示正常结束 {
                System.out.println("出错了");
                return;
            } else {
            // OldRarFile.update(file,"2");
            // 解压完的删除rar文件
            // File files = new File(file);
            // files.delete();
            }
            proc.destroy();
        } catch (Exception e) {
            System.out.println(file + " ====解压失败" + e);
        }
    }
    return;
}


}