package com.example.steam.service;
 import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ImageDao;
import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.RedisService;
import com.example.steam.DTO.GameDetail;
@Service
public class ImageService {

 private  String DEFAULT_IMAGE;

@Autowired
 private ImageDao imageDao;

@Autowired
 private GameService gameService;

@Autowired
 private RedisService redisService;

@Autowired
 private ApplicationContext applicationContext;


public String findImageUrlById(long id,String dataSource){
    DynamicDataSourceHolder.putDataSource(dataSource);
    return imageDao.findImageUrlById(id);
}


public List<Image> findGameImagesByGameId(long gameId){
    GameImage gameImage = imageDao.findImagesByGameId(gameId);
    List<Image> images = new LinkedList<>();
    images.add(imageDao.findImageById(gameImage.getImage1()));
    images.add(imageDao.findImageById(gameImage.getImage2()));
    images.add(imageDao.findImageById(gameImage.getImage3()));
    images.add(imageDao.findImageById(gameImage.getImage4()));
    images.add(imageDao.findImageById(gameImage.getImage5()));
    return images;
}


public Long addImage(Image image){
    imageDao.addImage(image);
    return image.getId();
}


public long updateImageToGame(GameImage gameImage){
    long result = imageDao.updateImageToGame(gameImage);
    GameDetail gameDetail = gameService.findGameById(gameImage.getGameId());
    List<Image> imageList = ((ImageService) applicationContext.getBean("imageService")).findGameImagesByGameId(gameImage.getGameId());
    gameDetail.setImageIntro1(imageList.get(0).getUrl());
    gameDetail.setImageIntro2(imageList.get(1).getUrl());
    gameDetail.setImageIntro3(imageList.get(2).getUrl());
    gameDetail.setImageIntro4(imageList.get(3).getUrl());
    gameDetail.setImageIntro5(imageList.get(4).getUrl());
    redisService.set(GameKey.GAME_ID, gameImage.getGameId() + "", gameDetail);
    return result;
}


public List<String> findGameImageUrlsByGameId(long gameId){
    GameImage gameImage = imageDao.findImagesByGameId(gameId);
    List<String> images = new LinkedList<>();
    if (gameImage == null) {
        images.add(DEFAULT_IMAGE);
        images.add(DEFAULT_IMAGE);
        images.add(DEFAULT_IMAGE);
        images.add(DEFAULT_IMAGE);
        images.add(DEFAULT_IMAGE);
        return images;
    }
    String image1 = imageDao.findImageUrlById(gameImage.getImage1());
    String image2 = imageDao.findImageUrlById(gameImage.getImage2());
    String image3 = imageDao.findImageUrlById(gameImage.getImage3());
    String image4 = imageDao.findImageUrlById(gameImage.getImage4());
    String image5 = imageDao.findImageUrlById(gameImage.getImage5());
    images.add(image1);
    images.add(image2);
    images.add(image3);
    images.add(image4);
    images.add(image5);
    return images;
}


public Image findImageById(long id){
    return imageDao.findImageById(id);
}


@Transactional(rollbackFor = Exception.class)
public long addImageToGame(GameImage gameImage){
    long result = imageDao.addImageToGame(gameImage);
    GameDetail gameDetail = gameService.findGameById(gameImage.getGameId());
    List<Image> imageList = ((ImageService) applicationContext.getBean("imageService")).findGameImagesByGameId(gameImage.getGameId());
    gameDetail.setImageIntro1(imageList.get(0).getUrl());
    gameDetail.setImageIntro2(imageList.get(1).getUrl());
    gameDetail.setImageIntro3(imageList.get(2).getUrl());
    gameDetail.setImageIntro4(imageList.get(3).getUrl());
    gameDetail.setImageIntro5(imageList.get(4).getUrl());
    redisService.set(GameKey.GAME_ID, gameImage.getGameId() + "", gameDetail);
    return result;
}


public GameImage findGameImageByGameId(long gameId){
    return imageDao.findImagesByGameId(gameId);
}


}