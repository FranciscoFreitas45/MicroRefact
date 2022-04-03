package net.coobird.thumbnailator.makers;
 import java.awt.image.BufferedImage;
public class ScaledThumbnailMaker extends ThumbnailMaker{

 private  String PARAM_SCALE;

 private  double widthFactor;

 private  double heightFactor;

/**
 * <p>
 * Creates an instance of {@code ScaledThumbnailMaker} without the
 * scaling factor specified.
 * </p>
 * <p>
 * To use this {@code ScaledThumbnailMaker}, one must specify the
 * scaling factor to use by calling the {@link #scale(double)} method
 * before generating a thumbnail.
 * </p>
 */
public ScaledThumbnailMaker() {
    super();
    ready.unset(PARAM_SCALE);
}/**
 * Creates an instance of {@code ScaledThumbnailMaker} with the specified
 * scaling factor.
 *
 * @param factor			The scaling factor to apply when resizing an
 * 							image to create a thumbnail.
 */
public ScaledThumbnailMaker(double factor) {
    this();
    scale(factor);
}/**
 * Creates an instance of {@code ScaledThumbnailMaker} with the specified
 * scaling factors for the width and height.
 *
 * @param widthFactor		The scaling factor to apply to the width when
 * 							resizing an image to create a thumbnail.
 * @param heightFactor		The scaling factor to apply to the height when
 * 							resizing an image to create a thumbnail.
 * @since	0.3.10
 */
public ScaledThumbnailMaker(double widthFactor, double heightFactor) {
    this();
    scale(widthFactor, heightFactor);
}
public ScaledThumbnailMaker scale(double widthFactor,double heightFactor){
    if (ready.isSet(PARAM_SCALE)) {
        throw new IllegalStateException("The scaling factor has already been set.");
    }
    if (widthFactor <= 0 || heightFactor <= 0) {
        throw new IllegalArgumentException("The scaling factor must be greater than zero.");
    }
    this.widthFactor = widthFactor;
    this.heightFactor = heightFactor;
    ready.set(PARAM_SCALE);
    return this;
}


@Override
public BufferedImage make(BufferedImage img){
    int width = (int) Math.round(img.getWidth() * widthFactor);
    int height = (int) Math.round(img.getHeight() * heightFactor);
    width = (width == 0) ? 1 : width;
    height = (height == 0) ? 1 : height;
    return super.makeThumbnail(img, width, height);
}


}