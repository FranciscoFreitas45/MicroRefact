package com.example.steam.utils;
 import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.FileKey;
import com.example.steam.service.GameService;
import com.example.steam.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.RedisService;
import com.example.steam.DTO.ResultMsg;
@Service
public class FileUploadUtil {

 private Logger log;

 private  int MAX_IMAGE_LENGTH;

@Value("${imageAddress}")
 private  String imageServer;

@Value("${imageUrl}")
 private  String imageUrl;

 private  int MAX_IMAGE_SIZE;

 private  String[] allImageType;

@Autowired
 private ImageService imageService;

@Autowired
 private GameService gameService;

@Autowired
 private RedisService redisService;


public String handleMultipartFile(MultipartFile file){
    String origName = file.getOriginalFilename();
    File imgFile = new File(imageServer, origName);
    while (imgFile.exists()) {
        origName = NamedDuplicateUtil.nameConversion(origName);
        imgFile = new File(imageServer, origName);
    }
    if (!imgFile.getParentFile().exists()) {
        imgFile.getParentFile().mkdirs();
    }
    try {
        OutputStream outputStream = new FileOutputStream(imgFile);
        IOUtils.copy(file.getInputStream(), outputStream);
        outputStream.flush();
    } catch (IOException e) {
        log.error("I/O异常");
    }
    return imageUrl + origName;
}


public ResultMsg handleFileUpload(MultipartFile file){
    log.info("图片大小：" + file.getSize());
    log.info("图片类型" + file.getContentType());
    if (file.getSize() > MAX_IMAGE_SIZE) {
        return ResultMsg.IMAGE_OVERSIZE;
    }
    if (!isImageType(file.getContentType())) {
        return ResultMsg.IMAGE_TYPE_ERROR;
    }
    String imageId = handleMultipartFile(file);
    return ResultMsg.SUCCESS(imageId);
}


public ResultMsg handleMultipleAttributrUpload(HttpServletRequest request){
    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    MultipartFile file = multipartHttpServletRequest.getFile("file");
    String gameId = multipartHttpServletRequest.getParameter("gameId");
    String imageUrl = handleMultipartFile(file);
    handleImageDao(imageUrl, Long.parseLong(gameId));
    return ResultMsg.SUCCESS;
}


@Transactional(rollbackFor = Exception.class)
public void handleImageDao(String imageUrl,long gameId){
    Image image = new Image(imageUrl, "", "");
    long newImageId = imageService.addImage(image);
    GameImage gameImage = imageService.findGameImageByGameId(gameId);
    redisService.lpush(FileKey.IMAGE_LIST, gameId + "", newImageId);
    long imageListSize = redisService.llength(FileKey.IMAGE_LIST, gameId + "");
    if (imageListSize == MAX_IMAGE_LENGTH) {
        List<Long> imageIdList = new LinkedList<>();
        for (int i = 0; i < MAX_IMAGE_LENGTH; i++) {
            long imageId = redisService.rpop(FileKey.IMAGE_LIST, gameId + "", Long.class);
            imageIdList.add(imageId);
        }
        if (gameImage == null) {
            /**
             *  1.  加入集合存入
             *  2. 当集合中满了6条的时候
             *  3. 取第一条做海报，其它插入做介绍
             */
            GameImage newGameImage = new GameImage(gameId, imageIdList.get(1), imageIdList.get(2), imageIdList.get(3), imageIdList.get(4), imageIdList.get(5));
            imageService.addImageToGame(newGameImage);
            gameService.updateGamePosterImage(gameId, imageIdList.get(0));
            redisService.del(FileKey.IMAGE_LIST, gameId + "");
        } else {
            GameImage newGameImage = new GameImage(gameId, imageIdList.get(1), imageIdList.get(2), imageIdList.get(3), imageIdList.get(4), imageIdList.get(5));
            imageService.updateImageToGame(newGameImage);
            gameService.updateGamePosterImage(gameId, imageIdList.get(0));
            redisService.del(FileKey.IMAGE_LIST, gameId + "");
        }
    /**
     * 插入数据库
     */
    }
}


public boolean isImageType(String imageType){
    for (int i = 0; i < allImageType.length; i++) {
        if (imageType.contains(allImageType[i])) {
            return true;
        }
    }
    return false;
}


}