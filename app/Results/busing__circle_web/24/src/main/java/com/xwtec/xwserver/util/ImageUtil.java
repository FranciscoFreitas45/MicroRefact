package com.xwtec.xwserver.util;
 import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import com.xwtec.xwserver.pojo.util.ImagePojo;
public class ImageUtil {


public boolean createMark(String filePath,String destPath,String markContent,int left,int top,int r,int g,int b,int fontSize){
    Color markContentColor = new Color(r, g, b);
    ImageIcon imgIcon = new ImageIcon(filePath);
    Image theImg = imgIcon.getImage();
    int width = theImg.getWidth(null);
    int height = theImg.getHeight(null);
    BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = bimage.createGraphics();
    graphics2D.setColor(markContentColor);
    graphics2D.setBackground(Color.white);
    graphics2D.drawImage(theImg, 0, 0, null);
    AttributedString ats = new AttributedString(markContent);
    Font f = new Font("微软雅黑", 0, fontSize);
    ats.addAttribute(TextAttribute.FONT, f, 0, markContent.length());
    AttributedCharacterIterator iter = ats.getIterator();
    graphics2D.drawString(iter, left, top);
    // 画笔结束
    graphics2D.dispose();
    try {
        String formatName = destPath.substring(destPath.lastIndexOf(".") + 1);
        ImageIO.write(bimage, /*"GIF"*/
        formatName, /* format desired */
        new File(destPath));
    // 输出 文件 到指定的路径
    // FileOutputStream out = new FileOutputStream(destPath);
    // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    // JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
    // param.setQuality(1 , true);
    // encoder.encode(bimage, param);
    // out.close();
    } catch (Exception e) {
        return false;
    }
    return true;
}


public String doCompress(int width,int height,String oldFile){
    return doCompress(oldFile, width, height, (float) 0.5, "", true);
}


public ImagePojo getImagePojo(String pathFile){
    ImagePojo imagePojo = new ImagePojo();
    File file = new File(pathFile);
    /*读取图片信息*/
    Image srcFile = ImageIO.read(file);
    // 图片的高
    imagePojo.setHeight(srcFile.getHeight(null));
    // 图片的宽
    imagePojo.setWidth(srcFile.getWidth(null));
    return imagePojo;
}


}