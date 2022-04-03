package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.PictureInformation;
import org.jugbd.mnet.domain.enums.PictureInformationType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public interface PictureInformationService {


public ResponseEntity<byte[]> getUploadedFileAsResponseEntity(Long fileId)
;

public void upload(Long registerId,MultipartFile file,PictureInformationType pictureInformationType,String fileName,String comment)
;

public PictureInformation save(PictureInformation pictureInformation)
;

public PictureInformation findOne(Long id)
;

public PictureInformation findPictureInformationByRegistrationId(Long registerId)
;

}