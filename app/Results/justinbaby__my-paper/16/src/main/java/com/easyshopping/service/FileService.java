package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.FileInfo;
import com.easyshopping.FileInfo.FileType;
import com.easyshopping.FileInfo.OrderType;
import org.springframework.web.multipart.MultipartFile;
public interface FileService {


public String upload(FileType fileType,MultipartFile multipartFile)
;

public String uploadLocal(FileType fileType,MultipartFile multipartFile)
;

public boolean isValid(FileType fileType,MultipartFile multipartFile)
;

public List<FileInfo> browser(String path,FileType fileType,OrderType orderType)
;

}