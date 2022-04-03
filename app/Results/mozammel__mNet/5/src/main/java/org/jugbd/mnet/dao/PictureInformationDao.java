package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.PictureInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PictureInformationDao extends JpaRepository<PictureInformation, Long>{


public PictureInformation findPictureInformationByRegister_Id(Long id)
;

public void setPictureInformation(Long id,PictureInformation pictureInformation);

public PictureInformation getPictureInformation(Long id);

public PictureInformation getPictureInformation(Long id);

public OutdoorRegister setPictureInformation(Long id,PictureInformation pictureInformation);

}