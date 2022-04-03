package org.gliderwiki.framework.util;
 import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.gliderwiki.framework.exception.FilePermitMsgException;
import org.gliderwiki.framework.util.constant.FtpConstant;
import org.gliderwiki.web.vo.TempUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
public class FileUploader {

 static  Logger logger;


public boolean copyTo(File fromFile,File toFile){
    boolean result = false;
    File[] files = fromFile.listFiles();
    File in = null;
    File out = null;
    File dest = null;
    String fromDir = "";
    String toDir = "";
    if (!toFile.exists() || !toFile.isDirectory()) {
        toFile.mkdirs();
    } else {
        logger.debug(toFile + "위치에 디렉토리가 존재합니다.");
    }
    for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
            logger.debug("파일 일 경우는 파일을 카피 하자!");
            in = new File(fromFile, files[i].getName());
            out = new File(toFile, files[i].getName());
            try {
                if (!files[i].getName().equals("Thumbs.db")) {
                    FileCopyUtils.copy(in, out);
                    result = true;
                }
            } catch (IOException e) {
                result = false;
                e.printStackTrace();
            }
        } else {
            logger.debug("디렉토리인 경우 디렉토리 만들자");
            logger.debug("fileName : " + files[i].getName());
            logger.debug("getAbsoluteFile : " + files[i].getAbsoluteFile());
            logger.debug("getAbsolutePath : " + files[i].getAbsolutePath());
            logger.debug("isDirectory : " + files[i].isDirectory());
            fromDir = fromFile + "/" + files[i].getName() + "/";
            toDir = toFile + "/" + files[i].getName() + "/";
            logger.debug("fromDir : " + fromDir);
            logger.debug("toDir : " + toDir);
            if (files[i].isDirectory()) {
                logger.debug("타겟 디렉토리 만들자 - 새로 생성되는 폴더이므로 걍 만들면 됨");
                dest = new File(toDir);
                dest.mkdir();
                logger.debug("타겟 디렉토리 완료");
            }
            logger.debug("**fromDir : " + fromDir);
            File cronFile = new File(fromDir);
            File[] dirFiles = cronFile.listFiles();
            logger.debug("파일 갯수 : " + dirFiles.length);
            for (int idx = 0; idx < dirFiles.length; idx++) {
                logger.debug("** 1 : " + dirFiles[idx].getName());
                logger.debug("** 1 : " + cronFile);
                logger.debug("** 1 : " + toDir);
                if (!dirFiles[idx].getName().equals("Thumbs.db")) {
                    in = new File(cronFile, dirFiles[idx].getName());
                    out = new File(toDir, dirFiles[idx].getName());
                    try {
                        logger.debug("** 2");
                        FileCopyUtils.copy(in, out);
                        result = true;
                    } catch (IOException e) {
                        result = false;
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * 파일을 복사한 후 삭제합니다.
     */
    for (int del = 0; del < files.length; del++) {
        logger.debug("파일 삭제");
        files[del].delete();
    }
    FileSystemUtils.deleteRecursively(fromFile);
    return result;
}


public TempUploadVo uploadThumbFile(MultipartFile file,String svcPath,String weUserIdx,String toDay,double maxSize,String width,String height){
    // 폼파일이 null이거나, 파일크기가 0,
    // 파일명이 없는 경우, 파일확장자가 없는 경우,
    // 파일크기가 제한크기를 초과하는 경우,
    // 업로드 허용된 파일확장자가 아닌 경우 예외를 던진다.
    logger.debug("maxSize : " + maxSize);
    logger.debug("svcPath : " + svcPath);
    logger.debug("file.getContentType() : " + file.getContentType());
    // pathKey를 통해 업로드 경로를 얻는다.
    String originFileName = file.getOriginalFilename();
    double fileSize = file.getSize();
    logger.debug("########## double fileSize  : " + fileSize);
    logger.debug("########## originFileName : " + originFileName);
    if (file == null || fileSize <= 0) {
        throw new FilePermitMsgException("빈파일입니다.");
    } else if (originFileName.length() <= 0) {
        throw new FilePermitMsgException("파일명이 없습니다.");
    } else if (originFileName.lastIndexOf('.') < 0 || originFileName.endsWith(".")) {
        throw new FilePermitMsgException("파일확장자가 없습니다.");
    } else if (!FtpConstant.isPermittedExt(originFileName)) {
        throw new FilePermitMsgException("업로드가 허용된 파일이 아닙니다.");
    } else if (fileSize > (maxSize * 1024 * 1024)) {
        throw new FilePermitMsgException("파일 크기가 " + maxSize + "MB를 초과합니다.");
    }
    // 파일 이름을 나노세컨으로 변경한다. ex)201202103244103268990.png
    String saveFileName = makeFileByPolicy(file, originFileName, toDay);
    // 저장할 폴더를 생성한다.
    String createPath = createPathByPolicy(svcPath, weUserIdx, toDay);
    logger.debug("#####createPath  : " + createPath);
    // 실제 파일을 저장 한다.
    TempUploadVo fileBean = writeFile(file, svcPath, weUserIdx, toDay, saveFileName);
    if (fileBean.isUploaded() == false) {
        // 업로드가 안됐을 경우 원본파일 이름 저장
        fileBean.setFileRealName(originFileName);
        return fileBean;
    }
    logger.debug(" 업로드 파일 타입 확인  file.getContentType() : " + file.getContentType());
    // 업로드 파일 타입 확인
    String[] typeArr = null;
    String type = null;
    typeArr = file.getContentType().split("/");
    if (typeArr != null) {
        type = typeArr[0];
    }
    logger.debug("파일의 컨텐츠 type : " + type);
    // 이미지일 경우 썸네일 작업
    if (type.equals("image")) {
        // 썸네일 이름
        String saveThumbFileName = "thumb_" + saveFileName;
        // 저장할 썸네일 폴더를 생성한다.
        String thumbCreatePath = createPathByPolicy(svcPath, weUserIdx, toDay, "thumb");
        logger.debug("thumbCreatePath : " + thumbCreatePath);
        logger.debug("createPath : " + createPath);
        // 원본 파일의 경로
        String originDesc = createPath + "/" + saveFileName;
        // 썸네일의 저장 경로
        String targetDesc = svcPath + "/" + thumbCreatePath + saveThumbFileName;
        // 원본 파일
        File orgFile = new File(originDesc);
        // 썸네일 파일 생성
        File thumbFile = new File(targetDesc);
        createThumbFileWithSize(orgFile, thumbFile, fileBean, "/" + thumbCreatePath, saveThumbFileName, width, height);
        logger.debug("originDesc : " + originDesc);
        logger.debug("targetDesc : " + targetDesc);
    }
    return fileBean;
}


public String makeFileByPolicy(MultipartFile file,String originFileName,String toDay){
    // 실제파일명
    String realFileName = originFileName;
    logger.debug("realFileName : " + realFileName);
    // 업로드 파일 확장자 취득
    int dotIndex = originFileName.lastIndexOf('.');
    String extendName = originFileName.substring(dotIndex + 1);
    logger.debug("extendName : " + extendName);
    String temp = String.valueOf(System.nanoTime());
    // 저장 파일명
    String saveFileName = toDay + temp + "." + extendName.toLowerCase();
    logger.debug("saveFileName : " + saveFileName);
    return saveFileName;
}


public void createThumbFileWithSize(File orgFile,File thumbFile,TempUploadVo fileBean,String thumbCreatePath,String saveThumbFileName,String width,String height){
    // 썸네일 변환
    GetThumbImage getThumbImage = new GetThumbImage();
    Integer intWidth = Integer.parseInt("width");
    Integer intHeight = Integer.parseInt("height");
    getThumbImage.resizeImage(orgFile, thumbFile, intWidth, intHeight);
    fileBean.setThumbPath(thumbCreatePath);
    fileBean.setThumbName(saveThumbFileName);
    fileBean.setThumbYn("Y");
}


public String createPathByPolicy(String svcPath,String weUserIdx,String toDay){
    // 베이스 업로드 디렉토리 확인
    File baseDir = new File(svcPath);
    if (!baseDir.exists() || !baseDir.isDirectory()) {
        baseDir.mkdirs();
    } else {
        logger.debug(svcPath + "위치에 디렉토리가 존재합니다.");
    }
    File userPath = new File(svcPath + "/" + weUserIdx);
    if (!userPath.exists() || !userPath.isDirectory()) {
        userPath.mkdirs();
    } else {
        logger.debug(svcPath + "/" + weUserIdx + "위치에 디렉토리가 존재합니다.");
    }
    String destPath = svcPath + "/" + weUserIdx + "/" + toDay + "/";
    File repDir = new File(destPath);
    if (!repDir.exists() || !repDir.isDirectory()) {
        repDir.mkdirs();
        logger.debug("repDir.getAbsolutePath() : " + repDir.getAbsolutePath());
    } else {
        logger.debug(repDir + "위치에 디렉토리가 존재합니다.");
    }
    logger.debug("################### repDir.getAbsolutePath(); " + repDir.getAbsolutePath());
    logger.debug("################### destPath ; " + destPath);
    return repDir.getAbsolutePath();
}


public TempUploadVo writeFile(MultipartFile file,String svcPath,String weUserIdx,String toDay,String saveFileName){
    // 업로드된 파일정보
    TempUploadVo fileBean = new TempUploadVo();
    // 사이즈
    int size = 0;
    double fileSize = file.getSize();
    // 입출력 스트림
    InputStream is = null;
    OutputStream os = null;
    try {
        // 입출력 스트림을 연다.
        is = new BufferedInputStream(file.getInputStream());
        os = new BufferedOutputStream(new FileOutputStream(svcPath + "/" + weUserIdx + "/" + toDay + "/" + saveFileName));
        int b = 0;
        while ((b = is.read()) != -1) {
            os.write(b);
            size = size + b;
        }
    } finally {
        // 입출력 스트림은 항상 닫는다.
        if (is != null) {
            try {
                is.close();
            } finally {
            }
        }
        if (os != null) {
            try {
                os.flush();
            } finally {
            }
            try {
                os.close();
            } finally {
            }
        }
    }
    int dotIndex = file.getOriginalFilename().lastIndexOf('.');
    String extendName = file.getOriginalFilename().substring(dotIndex + 1);
    fileBean.setFilePath("/" + weUserIdx + "/" + toDay + "/");
    fileBean.setFileSaveName(saveFileName);
    fileBean.setFileRealName(file.getOriginalFilename());
    fileBean.setFileType(extendName);
    fileBean.setThumbYn("N");
    fileBean.setFileSize(fileSize);
    fileBean.setIsUploaded(true);
    return fileBean;
}


public void createThumbFile(File orgFile,File thumbFile,TempUploadVo fileBean,String thumbCreatePath,String saveThumbFileName){
    // 썸네일 변환
    // BufferedInputStream streamImg = new BufferedInputStream(new FileInputStream(orgFile));
    BufferedImage biImg = ImageIO.read(orgFile);
    int width = biImg.getWidth();
    int height = biImg.getHeight();
    logger.debug("### 이미지의 원본 크기 : " + width + "/ " + height);
    int maxWidth = 788;
    if (width > maxWidth) {
        int y = (height * maxWidth) / width;
        width = maxWidth;
        height = y;
        logger.debug("##이미지리사이징 후 : " + width + " / " + height);
    }
    GetThumbImage getThumbImage = new GetThumbImage();
    getThumbImage.resizeImage(orgFile, thumbFile, width, height);
    fileBean.setThumbPath(thumbCreatePath);
    fileBean.setThumbName(saveThumbFileName);
    fileBean.setThumbYn("Y");
}


public TempUploadVo uploadTempFile(MultipartFile file,String svcPath,String weUserIdx,String toDay,double maxSize){
    // 폼파일이 null이거나, 파일크기가 0,
    // 파일명이 없는 경우, 파일확장자가 없는 경우,
    // 파일크기가 제한크기를 초과하는 경우,
    // 업로드 허용된 파일확장자가 아닌 경우 예외를 던진다.
    logger.debug("maxSize : " + maxSize);
    logger.debug("svcPath : " + svcPath);
    logger.debug("file.getContentType() : " + file.getContentType());
    // pathKey를 통해 업로드 경로를 얻는다.
    String originFileName = file.getOriginalFilename();
    double fileSize = file.getSize();
    logger.debug("########## double fileSize  : " + fileSize);
    logger.debug("########## originFileName : " + originFileName);
    if (file == null || fileSize <= 0) {
        throw new FilePermitMsgException("빈파일입니다.");
    } else if (originFileName.length() <= 0) {
        throw new FilePermitMsgException("파일명이 없습니다.");
    } else if (originFileName.lastIndexOf('.') < 0 || originFileName.endsWith(".")) {
        throw new FilePermitMsgException("파일확장자가 없습니다.");
    } else if (!FtpConstant.isPermittedExt(originFileName)) {
        throw new FilePermitMsgException("업로드가 허용된 파일이 아닙니다.");
    } else if (fileSize > (maxSize * 1024 * 1024)) {
        throw new FilePermitMsgException("파일 크기가 " + maxSize + "MB를 초과합니다.");
    }
    // 파일 이름을 나노세컨으로 변경한다. ex)201202103244103268990.png
    String saveFileName = makeFileByPolicy(file, originFileName, toDay);
    // 저장할 폴더를 생성한다.
    String createPath = createPathByPolicy(svcPath, weUserIdx, toDay);
    logger.debug("#####createPath  : " + createPath);
    // 실제 파일을 저장 한다.
    TempUploadVo fileBean = writeFile(file, svcPath, weUserIdx, toDay, saveFileName);
    if (fileBean.isUploaded() == false) {
        // 업로드가 안됐을 경우 원본파일 이름 저장
        fileBean.setFileRealName(originFileName);
        return fileBean;
    }
    logger.debug(" 업로드 파일 타입 확인  file.getContentType() : " + file.getContentType());
    // 업로드 파일 타입 확인
    String[] typeArr = null;
    String type = null;
    typeArr = file.getContentType().split("/");
    if (typeArr != null) {
        type = typeArr[0];
    }
    logger.debug("파일의 컨텐츠 type : " + type);
    // 이미지일 경우 썸네일 작업
    if (type.equals("image")) {
        // 썸네일 이름
        String saveThumbFileName = "thumb_" + saveFileName;
        // 저장할 썸네일 폴더를 생성한다.
        String thumbCreatePath = createPathByPolicy(svcPath, weUserIdx, toDay, "thumb");
        logger.debug("thumbCreatePath : " + thumbCreatePath);
        logger.debug("createPath : " + createPath);
        // 원본 파일의 경로
        String originDesc = createPath + "/" + saveFileName;
        // 썸네일의 저장 경로
        String targetDesc = svcPath + "/" + thumbCreatePath + saveThumbFileName;
        // 원본 파일
        File orgFile = new File(originDesc);
        // 썸네일 파일 생성
        File thumbFile = new File(targetDesc);
        createThumbFile(orgFile, thumbFile, fileBean, "/" + thumbCreatePath, saveThumbFileName);
        logger.debug("originDesc : " + originDesc);
        logger.debug("targetDesc : " + targetDesc);
    }
    return fileBean;
}


}