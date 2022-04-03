package com.tech.services.interfaces;
 import com.tech.models.entities.ImagesMod;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
public interface IImagesService {


@Transactional
public ImagesMod getImageByHashtag(Long tag)
;

@Transactional
public List<ImagesMod> getImageByUserID(long id)
;

@Transactional
public void addImage(ImagesMod newImg)
;

@Transactional
public List<ImagesMod> getAllImages()
;

@Transactional
public void deleteImage(ImagesMod images)
;

@Transactional
public long getCount()
;

@Transactional
public boolean checkImagesByHashtag(Long hashtag)
;

}