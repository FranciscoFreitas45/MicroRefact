package services;
 import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.FloatRepository;
import domain.Brotherhood;
import domain.Parade;
import forms.FormObjectParadeFloat;
import forms.FormObjectParadeFloatCheckbox;
import DTO.Float;
@Service
@Transactional
public class FloatService {

@Autowired
 private  FloatRepository floatRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  Validator validator;


public List<domain.Float> showAssignedFloats(Parade parade){
    List<domain.Float> floatts = new ArrayList<domain.Float>();
    floatts = parade.getFloats();
    return floatts;
}


public List<domain.Float> reconstructList(FormObjectParadeFloatCheckbox formObjectParadeFloatCheckbox){
    List<Integer> ids = formObjectParadeFloatCheckbox.getFloats();
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floats = new ArrayList<>();
    for (Integer id : ids) {
        domain.Float floatt = this.floatRepository.findOne(id);
        Assert.notNull(floatt);
        Assert.isTrue(loggedBrotherhood.getFloats().contains(floatt));
        floats.add(floatt);
    }
    return floats;
}


public void deleteAllFloatsBrotherhood(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brother = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatsToDelete = new ArrayList<domain.Float>();
    floatsToDelete = brother.getFloats();
    List<domain.Float> emptyFloats = new ArrayList<domain.Float>();
    brother.setFloats(emptyFloats);
    this.brotherhoodService.save(brother);
    for (domain.Float f : floatsToDelete) this.floatRepository.delete(f);
}


public domain.Float findOne(int id){
    return this.floatRepository.findOne(id);
}


public domain.Float save(domain.Float floatt){
    // Obtener float list
    // quitar float antiguo y a�adir el nuevo
    // Hacer set del float list modificado
    // Save parade
    // Obtener loggedBrotherhood
    // A PARTIR DE AQUI PUEDE QUE SEA OPCIONAL
    // Quitar parade antigua y a�adir nueva
    // Obt
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = new Brotherhood();
    domain.Float floattSaved = new domain.Float();
    loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    if (floatt.getId() > 0)
        Assert.isTrue(loggedBrotherhood.getFloats().contains(floatt));
    Assert.notNull(loggedBrotherhood.getArea());
    List<domain.Float> floatFinalMode = new ArrayList<domain.Float>();
    floatFinalMode = this.floatsInParadeInFinalMode();
    Assert.isTrue(!floatFinalMode.contains(floatt));
    floattSaved = this.floatRepository.save(floatt);
    loggedBrotherhood.getFloats().remove(floatt);
    loggedBrotherhood.getFloats().add(floattSaved);
    this.brotherhoodService.save(loggedBrotherhood);
    return floattSaved;
}


public domain.Float reconstruct(domain.Float floatt,BindingResult binding){
    domain.Float result = new domain.Float();
    if (floatt.getId() == 0)
        result = floatt;
    else {
        domain.Float copy = this.floatRepository.findOne(floatt.getId());
        result.setId(copy.getId());
        result.setVersion(copy.getVersion());
        result.setPictures(copy.getPictures());
        result.setDescription(floatt.getDescription());
        result.setTitle(floatt.getTitle());
    // result = floatt;
    // result.setPictures(floatt.getPictures());
    }
    this.validator.validate(result, binding);
    return result;
}


public void AssingFloatToParade(domain.Float floatt,Parade parade){
    Assert.isTrue(parade.getIsDraftMode() == true);
    if (!(parade.getFloats().contains(floatt)))
        parade.getFloats().add(floatt);
    this.paradeService.save(parade);
}


public List<domain.Float> showBrotherhoodFloats(){
    Brotherhood bro = new Brotherhood();
    this.brotherhoodService.loggedAsBrotherhood();
    bro = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatts = new ArrayList<domain.Float>();
    floatts = bro.getFloats();
    return floatts;
}


public List<domain.Float> floatsInParadeInFinalMode(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood bro = new Brotherhood();
    bro = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatt = new ArrayList<domain.Float>();
    floatt = this.floatRepository.getFloatsInParadeInFinalMode(bro.getId());
    return floatt;
}


public List<domain.Float> findAll(){
    return this.floatRepository.findAll();
}


public void remove(domain.Float floatt){
    // No se pueden eliminar pasos asignados a desfiles en final mode
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood bro = new Brotherhood();
    bro = this.brotherhoodService.loggedBrotherhood();
    List<Parade> pro = new ArrayList<Parade>();
    Assert.isTrue(bro.getFloats().contains(floatt));
    pro = this.brotherhoodService.getParadesByBrotherhood(bro);
    Assert.isTrue(this.allParadesDraftMode(pro));
    for (final Parade p : pro) if (p.getFloats().contains(floatt)) {
        Assert.isTrue(!p.getIsDraftMode());
        List<domain.Float> floatss = p.getFloats();
        floatss.remove(floatt);
        p.setFloats(floatss);
        this.paradeService.save(p);
    }
    List<domain.Float> floatsBro = bro.getFloats();
    floatsBro.remove(floatt);
    bro.setFloats(floatsBro);
    this.brotherhoodService.save(bro);
    this.floatRepository.delete(floatt);
}


public void UnAssingFloatToParade(domain.Float floatt,Parade parade){
    Assert.isTrue(parade.getIsDraftMode() == true);
    if (parade.getFloats().contains(floatt))
        parade.getFloats().remove(floatt);
    this.paradeService.save(parade);
}


public Map<Integer,String> getMapAvailableFloats(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floats = loggedBrotherhood.getFloats();
    Map<Integer, String> map = new HashMap<>();
    for (domain.Float floatt : floats) map.put(floatt.getId(), floatt.getTitle());
    return map;
}


public domain.Float addPicture(String picture,domain.Float floatt){
    this.brotherhoodService.loggedAsBrotherhood();
    if (floatt.getId() > 0)
        Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getFloats().contains(floatt));
    Assert.isTrue(!picture.trim().isEmpty() && this.isUrl(picture));
    floatt.getPictures().add(picture);
    return this.save(floatt);
}


public List<String> getPicturesOfFloat(int floatId,boolean parade){
    this.brotherhoodService.loggedAsBrotherhood();
    Assert.isTrue(parade);
    domain.Float floatt;
    List<String> pictures;
    floatt = this.findOne(floatId);
    Assert.notNull(floatt);
    Brotherhood bro = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(bro.getFloats().contains(floatt));
    pictures = floatt.getPictures();
    return pictures;
}


public void flush(){
    this.floatRepository.flush();
}


public List<domain.Float> showAllFloats(){
    List<domain.Float> floatts = new ArrayList<domain.Float>();
    floatts = this.floatRepository.findAll();
    return floatts;
}


public domain.Float reconstructForm(FormObjectParadeFloat formObjectParadeFloat,BindingResult binding){
    domain.Float result = new domain.Float();
    result.setTitle(formObjectParadeFloat.getTitle());
    result.setDescription(formObjectParadeFloat.getDescription());
    // this.validator.validate(result, binding);
    return result;
}


public Boolean isUrl(String url){
    try {
        new URL(url).toURI();
        return true;
    } catch (Exception e) {
        return false;
    }
}


public domain.Float create(){
    final domain.Float floatt = new domain.Float();
    final List<String> pictures = new ArrayList<String>();
    floatt.setPictures(pictures);
    floatt.setTitle("");
    floatt.setDescription("");
    return floatt;
}


public Boolean allParadesDraftMode(List<Parade> pro){
    final Boolean res = true;
    for (final Parade p : pro) if (p.getIsDraftMode() == false)
        return true;
    return res;
}


}