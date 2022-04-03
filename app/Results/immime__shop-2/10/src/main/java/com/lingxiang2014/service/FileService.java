package com.lingxiang2014.service;
 import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.lingxiang2014.FileInfo;
import com.lingxiang2014.FileInfo.FileType;
import com.lingxiang2014.FileInfo.OrderType;
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