package net.coobird.thumbnailator.util;
 import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class BufferedImages {

/**
 * This class is not intended to be instantiated.
 */
private BufferedImages() {
}
public BufferedImage copy(BufferedImage img,int imageType){
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage newImage = new BufferedImage(width, height, imageType);
    Graphics g = newImage.createGraphics();
    g.drawImage(img, 0, 0, null);
    g.dispose();
    return newImage;
}


}