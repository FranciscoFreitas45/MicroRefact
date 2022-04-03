package com.gbcom.update.common.util;
 import java.io.File;
import java.io.FileFilter;
import java.util.List;
import org.apache.log4j.Logger;
public class AutoUpdateFileFilter implements FileFilter{

 private  Logger LOG;

 private  List<File> includeFiles;

 private  List<File> excludeFiles;


public void setIncludeFiles(List<File> includeFiles){
    this.includeFiles = includeFiles;
}


public List<File> getIncludeFiles(){
    return includeFiles;
}


public List<File> getExcludeFiles(){
    return excludeFiles;
}


public List<File> filterFile(File file,FileFilter filter,List<File> list){
    if (file.isDirectory()) {
        File[] fileArray = file.listFiles(filter);
        for (int i = 0; i < fileArray.length; i++) {
            file = fileArray[i];
            if (file.isDirectory()) {
                filterFile(file, filter, list);
            } else {
                list.add(file);
            }
        }
    } else {
        list.add(file);
    }
    return list;
}


public void setExcludeFiles(List<File> excludeFiles){
    this.excludeFiles = excludeFiles;
}


@Override
public boolean accept(File pathname){
    if (includeFiles == null) {
        if (excludeFiles == null) {
            return false;
        } else {
            if (excludeFiles != null) {
                for (File exf : excludeFiles) {
                    if (exf.getName().equals(pathname.getName())) {
                        return false;
                    }
                }
            }
        }
    } else {
        for (File inf : includeFiles) {
            if (pathname.isDirectory()) {
                return true;
            }
            if (inf.getName().equals(pathname.getName())) {
                LOG.info("no filter file :" + pathname.getAbsolutePath());
                return true;
            }
        }
        if (excludeFiles != null) {
            for (File exf : excludeFiles) {
                if (exf.getName().equals(pathname.getName())) {
                    return false;
                }
            }
        }
    }
    return false;
}


}