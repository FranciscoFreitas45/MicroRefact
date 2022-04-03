package cn.com.cnc.fcc.service;
 import cn.com.cnc.fcc.domain.QmsProcess;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface QmsProcessService {


public JSONObject uploadData(MultipartFile files)
;

public Page<QmsProcess> qmsProcessFindAll(String bianMa,String gongName,Pageable pageable)
;

}