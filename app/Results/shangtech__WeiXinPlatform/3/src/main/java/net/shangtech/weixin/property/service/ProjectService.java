package net.shangtech.weixin.property.service;
 import java.util.Date;
import java.util.List;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.property.dao.HouseInfoDao;
import net.shangtech.weixin.property.dao.HousePanoramaDao;
import net.shangtech.weixin.property.dao.PanoramaDao;
import net.shangtech.weixin.property.dao.ProjectImageDao;
import net.shangtech.weixin.property.dao.ProjectTypeDao;
import net.shangtech.weixin.property.dao.SubProjectDao;
import net.shangtech.weixin.property.entity.HouseInfo;
import net.shangtech.weixin.property.entity.HousePanorama;
import net.shangtech.weixin.property.entity.Panorama;
import net.shangtech.weixin.property.entity.ProjectImage;
import net.shangtech.weixin.property.entity.ProjectType;
import net.shangtech.weixin.property.entity.SubProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.SubProjectDao;
import net.shangtech.Interface.ProjectTypeDao;
import net.shangtech.Interface.ProjectImageDao;
import net.shangtech.Interface.PanoramaDao;
import net.shangtech.Interface.HousePanoramaDao;
@Service
@Transactional
public class ProjectService extends BaseService<SubProject>{

@Autowired
 private  SubProjectDao dao;

@Autowired
 private  ProjectTypeDao typeDao;

@Autowired
 private  ProjectImageDao imageDao;

@Autowired
 private  HouseInfoDao houseDao;

@Autowired
 private  PanoramaDao panoramaDao;

@Autowired
 private  HousePanoramaDao housePanoramaDao;


public void saveType(ProjectType type){
    if (type.getId() != null) {
        ProjectType o = typeDao.find(type.getId());
        o.setName(type.getName());
        o.setNameEn(type.getNameEn());
        typeDao.update(o);
    } else {
        typeDao.insert(type);
    }
}


public List<HouseInfo> findHousesByProject(int projectId){
    return houseDao.find("where projectId=? order by sort", projectId);
}


public void saveProject(SubProject project,List<ProjectImage> imageList){
    if (project.getId() != null) {
        SubProject o = dao.find(project.getId());
        o.setDescription(project.getDescription());
        if (project.getImage() != null)
            o.setImage(project.getImage());
        if (project.getImageDescription() != null)
            o.setImageDescription(project.getImageDescription());
        if (project.getImagePeripheral() != null)
            o.setImagePeripheral(project.getImagePeripheral());
        if (project.getImageTraffic() != null)
            o.setImageTraffic(project.getImageTraffic());
        o.setLatitude(project.getLatitude());
        o.setLongitude(project.getLongitude());
        o.setNameEn(project.getNameEn());
        o.setCustom1(project.getCustom1());
        o.setPeripheral(project.getPeripheral());
        o.setPriceAvg(project.getPriceAvg());
        o.setProjectName(project.getProjectName());
        o.setTraffic(project.getTraffic());
        dao.update(o);
    } else {
        project.setCreateTime(new Date());
        dao.insert(project);
    }
    for (ProjectImage image : imageList) {
        image.setProjectId(project.getId());
        imageDao.insert(image);
    }
}


public void deletePanorama(int id){
    housePanoramaDao.delete(id);
}


public void saveHouse(HouseInfo house){
    if (house.getId() != null) {
        HouseInfo o = houseDao.find(house.getId());
        o.setDescription(house.getDescription());
        o.setFloorNum(house.getFloorNum());
        o.setHallNum(house.getHallNum());
        o.setHouseArea(house.getHouseArea());
        o.setHouseName(house.getHouseName());
        if (house.getImage() != null)
            o.setImage(house.getImage());
        o.setRoomNum(house.getRoomNum());
        o.setSort(house.getSort());
        houseDao.update(o);
    } else {
        house.setCreateTime(new Date());
        houseDao.insert(house);
    }
}


public List<ProjectImage> findImagesByProject(int projectId){
    return imageDao.find("where projectId=?", projectId);
}


public int countTypeByUser(Integer sysUserId){
    return typeDao.count("where sysUserId=?", sysUserId);
}


public List<SubProject> findByProjectType(int type){
    return dao.find("where type=?", type);
}


@Override
public BaseDao<SubProject> dao(){
    return dao;
}


public Panorama findPanoramaById(int id){
    return panoramaDao.find(id);
}


public List<ProjectType> findProjectTypesByUser(int sysUserId){
    return typeDao.find("where sysUserId=? order by sort", sysUserId);
}


public void deleteProjectType(int id){
    typeDao.delete(id);
}


public void savePanorama(HousePanorama housePanorama){
    Panorama panorama = housePanorama.getPanorama();
    panoramaDao.insert(panorama);
    housePanorama.setPanoramaId(panorama.getId());
    housePanoramaDao.insert(housePanorama);
}


public List<HousePanorama> findPanoramasByHouse(int houseId){
    List<HousePanorama> list = housePanoramaDao.find("where houseId=?", houseId);
    for (HousePanorama panorama : list) {
        panorama.setPanorama(panoramaDao.find(panorama.getPanoramaId()));
    }
    return list;
}


}