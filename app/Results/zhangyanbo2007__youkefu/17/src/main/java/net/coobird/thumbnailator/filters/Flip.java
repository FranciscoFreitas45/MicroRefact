package net.coobird.thumbnailator.filters;
 import java.awt.Graphics;
import java.awt.image.BufferedImage;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
public class Flip {

 public  ImageFilter HORIZONTAL;

 public  ImageFilter VERTICAL;


public BufferedImage apply(BufferedImage img){
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage newImage = new BufferedImageBuilder(width, height).build();
    Graphics g = newImage.getGraphics();
    g.drawImage(img, 0, height, width, 0, 0, 0, width, height, null);
    g.dispose();
    return newImage;
}


}