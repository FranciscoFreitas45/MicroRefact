package kr.ac.sejong.api.service;
 import kr.ac.sejong.api.domain.ResultSection;
import kr.ac.sejong.api.domain.Upload;
import kr.ac.sejong.api.repository.ResultSectionRepository;
import kr.ac.sejong.api.repository.UploadRepository;
import org.springframework.stereotype.Service;
import java.util;
@Service
public class ResultService {

 private  ResultSectionRepository resultSectionRepository;

 private  UploadRepository uploadRepository;

 private int count;

public ResultService(ResultSectionRepository resultSectionRepository, UploadRepository uploadRepository) {
    this.resultSectionRepository = resultSectionRepository;
    this.uploadRepository = uploadRepository;
}
public List<Map<String,Object>> getResultByUploadId(String uploadId){
    long id = Long.parseLong(uploadId);
    Upload upload = uploadRepository.findByUpId(id);
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    // List<ResultSection> result = resultSectionRepository.findByUpload(id);
    List<ResultSection> result = resultSectionRepository.findByUpload(upload);
    System.out.println("----------------------------------");
    System.out.println(result.size());
    count = result.size();
    for (ResultSection i : result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", i.getStart());
        System.out.println(i.getStart());
        map.put("end", i.getEnd());
        map.put("path", i.getImgPath());
        resultList.add(map);
    }
    System.out.println("----------------------------------");
    return resultList;
}


public Map<String,Object> getFileByUploadId(String uploadId){
    long id = Long.parseLong(uploadId);
    Upload upload = uploadRepository.findByUpId(id);
    Map<String, Object> map = new HashMap<String, Object>();
    // List<ResultSection> resultList = resultSectionRepository.findByUpload(id);
    // List<ResultSection> resultList = resultSectionRepository.findByUpload(upload);
    // Upload upload = resultList.get(0).getUpload();
    map.put("imgName", upload.getUploadImg().getUpImgName());
    map.put("imgSavedName", upload.getUploadImg().getUpImgSavedName());
    map.put("vidName", upload.getUploadVid().getUpVidName());
    map.put("vidSavedName", upload.getUploadVid().getUpVidSavedName());
    map.put("resultCount", count);
    return map;
}


}