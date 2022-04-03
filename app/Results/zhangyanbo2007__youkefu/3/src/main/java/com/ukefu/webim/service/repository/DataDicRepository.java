package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.DataDic;
public interface DataDicRepository extends JpaRepository<DataDic, String>{


public List<DataDic> findByOrgiAndProjectid(String orgi,String projectid)
;

public List<DataDic> findByOrgi(String orgi)
;

public List<DataDic> findByOrgiAndName(String orgi,String name)
;

public DataDic findByIdAndOrgi(String id,String orgi)
;

public List<DataDic> findByOrgiAndCode(String orgi,String code)
;

public List<DataDic> findByOrgiAndNameAndIdNot(String orgi,String name,String id)
;

}