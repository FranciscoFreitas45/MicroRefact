package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.DataModel;
public interface DataModelRepository extends JpaRepository<DataModel, String>{


public List<DataModel> findByProidAndOrgi(String proid,String orgi)
;

public DataModel findByIdAndOrgi(String id,String orgi)
;

}