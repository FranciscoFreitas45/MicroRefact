package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.PictureInformationDao;
import org.jugbd.mnet.domain.PictureInformation;
@Service
public class PictureInformationRegisterService {

@Autowired
 private PictureInformationDao pictureinformationdao;


public void setPictureInformation(Long id,PictureInformation pictureInformation){
pictureinformationdao.setPictureInformation(id,pictureInformation);
}


public PictureInformation getPictureInformation(Long id){
return pictureinformationdao.getPictureInformation(id);
}


}