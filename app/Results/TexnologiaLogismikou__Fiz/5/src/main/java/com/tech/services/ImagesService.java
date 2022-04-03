package com.tech.services;
 import com.tech.models.entities.ImagesMod;
import com.tech.repositories.IImagesRepository;
import java.util.List;
import com.tech.services.interfaces.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ImagesService implements IImagesService{

@Autowired
 private  IImagesRepository repository;


@Override
@Transactional
public ImagesMod getImageByHashtag(Long tag){
    return repository.findByHashtag(tag);
}


@Transactional
@Override
public List<ImagesMod> getImageByUserID(long id){
    return repository.findByUserid(id);
}


@Transactional
@Override
public void addImage(ImagesMod newImg){
    repository.save(newImg);
}


@Override
@Transactional
public List<ImagesMod> getAllImages(){
    return repository.findAll();
}


@Override
@Transactional
public void deleteImage(ImagesMod images){
    repository.delete(images);
}


@Override
public long getCount(){
    return repository.count();
}


@Override
public boolean checkImagesByHashtag(Long hashtag){
    ImagesMod tstImg = repository.findByHashtag(hashtag);
    return tstImg != null;
}


}