package services;
 import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Area;
import domain.Brotherhood;
import repositories.AreaRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import DTO.Brotherhood;
import DTO.UserAccount;
import DTO.Authority;
@Service
@Transactional
public class AreaService {

@Autowired
 private  AreaRepository areaRepository;

@Autowired
 private  Validator validator;


public Area updateArea(Area area){
    this.loggedAsAdmin();
    Assert.isTrue(area.getId() != 0);
    return this.areaRepository.save(area);
}


public Boolean hasArea(Brotherhood brotherhood){
    try {
        Assert.notNull(brotherhood.getArea());
        return true;
    } catch (Throwable oops) {
        return false;
    }
}


public List<String> listUrlsArea(List<String> list){
    List<String> pic = new ArrayList<String>();
    if (list.size() == 1) {
        String picture = list.get(0).trim();
        List<String> pictures = Arrays.asList(picture.split(","));
        for (String s : pictures) if (!s.isEmpty() && !pic.contains(s.trim()) && this.isUrl(s))
            pic.add(s.trim());
    }
    return pic;
}


public Area findOne(int id){
    return this.areaRepository.findOne(id);
}


public Area save(Area area){
    return this.areaRepository.save(area);
}


public List<Brotherhood> brotherhoodOfAnArea(int areaId){
    return this.areaRepository.brotherhoodsOfAnArea(areaId);
}


public Area reconstructArea(Area a,BindingResult binding,String s){
    this.loggedAsAdmin();
    Area result;
    List<String> pictures = new ArrayList<String>();
    pictures.add(s);
    result = a;
    result.setPictures(this.listUrlsArea(pictures));
    this.validator.validate(result, binding);
    return result;
}


public List<Area> findAll(){
    return this.areaRepository.findAll();
}


public void delete(Area area){
    this.areaRepository.delete(area);
}


public Area addArea(Area area){
    this.loggedAsAdmin();
    Assert.isTrue(area.getId() == 0);
    return this.areaRepository.save(area);
}


public void loggedAsAdmin(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("ADMIN"));
}


public void flush(){
    this.areaRepository.flush();
}


public Boolean isUrl(String url){
    try {
        new URL(url).toURI();
        return true;
    } catch (Exception e) {
        return false;
    }
}


public void deleteArea(Area a){
    this.loggedAsAdmin();
    Assert.isTrue(this.areaRepository.brotherhoodsOfAnArea(a.getId()).isEmpty());
    this.areaRepository.delete(a);
}


public Area createArea(){
    this.loggedAsAdmin();
    List<String> l = new ArrayList<String>();
    Area area = new Area();
    area.setName("");
    area.setPictures(l);
    return area;
}


}