package net.coobird.thumbnailator.builders;
 import java.awt.Dimension;
import java.awt.image.BufferedImage;
public class BufferedImageBuilder {

 private  int DEFAULT_TYPE;

 private  int imageType;

 private  int width;

 private  int height;

/**
 * Instantiates a {@code BufferedImageBuilder} with the specified size, and
 * the default image type.
 *
 * @param size			The size of the {@link BufferedImage} to build.
 */
public BufferedImageBuilder(Dimension size) {
    this(size.width, size.height);
}/**
 * Instantiates a {@code BufferedImageBuilder} with the specified size and
 * image type.
 *
 * @param size			The size of the {@link BufferedImage} to build.
 * @param imageType		The image type of the {@link BufferedImage} to build.
 */
public BufferedImageBuilder(Dimension size, int imageType) {
    this(size.width, size.height, imageType);
}/**
 * Instantiates a {@code BufferedImageBuilder} with the specified size, and
 * the default image type.
 *
 * @param width			The width of the {@link BufferedImage} to build.
 * @param height		The height of the {@link BufferedImage} to build.
 */
public BufferedImageBuilder(int width, int height) {
    this(width, height, DEFAULT_TYPE);
}/**
 * Instantiates a {@code BufferedImageBuilder} with the specified size and
 * image type.
 *
 * @param width			The width of the {@link BufferedImage} to build.
 * @param height		The height of the {@link BufferedImage} to build.
 * @param imageType		The image type of the {@link BufferedImage} to build.
 */
public BufferedImageBuilder(int width, int height, int imageType) {
    size(width, height);
    imageType(imageType);
}
public BufferedImageBuilder size(int width,int height){
    width(width);
    height(height);
    return this;
}


public BufferedImage build(){
    return new BufferedImage(width, height, imageType);
}


public BufferedImageBuilder width(int width){
    if (width <= 0) {
        throw new IllegalArgumentException("Width must be greater than 0.");
    }
    this.width = width;
    return this;
}


public BufferedImageBuilder imageType(int imageType){
    this.imageType = imageType;
    return this;
}


public BufferedImageBuilder height(int height){
    if (height <= 0) {
        throw new IllegalArgumentException("Height must be greater than 0.");
    }
    this.height = height;
    return this;
}


}