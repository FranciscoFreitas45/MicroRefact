package com.sobey.cmop.mvc.service.basicdata;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.dao.LocationDao;
import com.sobey.cmop.mvc.entity.Location;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
@Service
@Transactional(readOnly = true)
public class LocationService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  LocationDao locationDao;


@Transactional(readOnly = false)
public boolean deleteLocation(Integer id){
    Location location = this.getLocation(id);
    for (Vlan vlan : location.getVlans()) {
        if (!vlan.getIpPools().isEmpty()) {
            return false;
        }
    }
    for (Vlan vlan : location.getVlans()) {
        comm.vlanService.deleteVlan(vlan.getId());
    }
    comm.oneCmdbUtilService.deleteLocationToOneCMDB(location);
    locationDao.delete(id);
    return true;
}


public Page<Location> getLocationPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<Location> spec = DynamicSpecifications.bySearchFilter(filters.values(), Location.class);
    return locationDao.findAll(spec, pageRequest);
}


public Location getLocation(Integer id){
    return locationDao.findOne(id);
}


public Location findLocationByName(String name){
    return locationDao.findByName(name);
}


@Transactional(readOnly = false)
public Location saveLocation(Location location){
    comm.oneCmdbUtilService.saveLocationToOneCMDB(location);
    return locationDao.save(location);
}


public List<Location> getLocationList(){
    return (List<Location>) locationDao.findAll();
}


public Location findLocationByAlias(String alias){
    return locationDao.findByAlias(alias);
}


}