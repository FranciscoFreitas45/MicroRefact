package kr.ac.sejong.api.service;
 import kr.ac.sejong.api.domain.Upload;
import kr.ac.sejong.api.domain.User;
import kr.ac.sejong.api.repository.UploadRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.ac.sejong.api.Interface.UploadRepository;
@Service
@Transactional
public class FileService {

 private  UploadRepository uploadRepository;

public FileService(UploadRepository uploadRepository) {
    this.uploadRepository = uploadRepository;
}
public List<Map<String,Object>> getFileListByUser(User user){
    long no = 1;
    List<Upload> uploadList = uploadRepository.findByUser(user);
    List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
    String uploading, processing;
    for (Upload i : uploadList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uploadId", i.getUpId());
        map.put("no", no++);
        // 이름 가져올 수 있어야함
        map.put("imageFileName", i.getUploadImg().getUpImgName());
        map.put("videoFileName", i.getUploadVid().getUpVidName());
        if (i.getUploading() == 1)
            uploading = "upload completed";
        else
            uploading = "uploading...";
        map.put("upload", uploading);
        if (i.getProcessing() == 1)
            processing = "process completed";
        else
            processing = "processing...";
        map.put("process", processing);
        map.put("imgSavedName", i.getUploadImg().getUpImgSavedName());
        map.put("vidSavedName", i.getUploadVid().getUpVidSavedName());
        if (i.getProcessing() == 1)
            map.put("result", i.getProcessing());
        fileList.add(map);
    }
    return fileList;
}


}