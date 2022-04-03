package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.PictureInformationDao;
import org.jugbd.mnet.domain.PictureInformation;
@Service
public class PictureInformationOutdoorRegisterService {

@Autowired
 private PictureInformationDao pictureinformationdao;


public PictureInformation getPictureInformation(Long id){
return pictureinformationdao.getPictureInformation(id);
}


public OutdoorRegister setPictureInformation(Long id,PictureInformation pictureInformation){
return pictureinformationdao.setPictureInformation(id,pictureInformation);
}


}