package cn.com.cnc.fcc.service;
 import cn.com.cnc.fcc.domain.QmsUnit;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface QmsUnitService {


public JSONObject uploadData(MultipartFile files)
;

public Page<QmsUnit> qmsUnitFindAll(String bianMa,String gongName,Pageable pageable)
;

}