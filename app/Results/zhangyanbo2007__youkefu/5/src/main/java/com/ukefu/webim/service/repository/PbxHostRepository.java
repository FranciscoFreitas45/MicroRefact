package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.PbxHost;
public interface PbxHostRepository extends JpaRepository<PbxHost, String>{


public List<PbxHost> findByHostnameOrIpaddr(String hostname,String ip)
;

public List<PbxHost> findByOrgi(String orgi)
;

public PbxHost findById(String id)
;

public PbxHost findByIdAndOrgi(String id,String orgi)
;

public int countByHostnameAndOrgi(String hostname,String orgi)
;

}