package com.fosun.fc.projects.creepers.DTO;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersConfigDao;
import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;
public class RedisConfigServiceImpl extends AbstractRedisCacheService<String, CreepersConfigDTO>{

 private  CreepersConfigDao creepersConfigDao;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://18";


@Override
public void refresh(){
    // 刷新全部缓存
    List<TCreepersConfig> entityList = (List<TCreepersConfig>) creepersConfigDao.findAll();
    List<CreepersConfigDTO> dtoList = BeanMapper.mapList(entityList, CreepersConfigDTO.class);
    for (CreepersConfigDTO creepersConfigDTO : dtoList) {
        set(creepersConfigDTO.getRequestType(), creepersConfigDTO);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/refresh"))

;
restTemplate.put(builder.toUriString(),null);
}


}