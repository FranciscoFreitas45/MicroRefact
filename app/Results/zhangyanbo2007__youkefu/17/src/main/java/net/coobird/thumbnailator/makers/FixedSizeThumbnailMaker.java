package net.coobird.thumbnailator.makers;
 import java.awt.image.BufferedImage;
public class FixedSizeThumbnailMaker extends ThumbnailMaker{

 private  String PARAM_SIZE;

 private  String PARAM_KEEP_RATIO;

 private  String PARAM_FIT_WITHIN;

 private  int width;

 private  int height;

 private  boolean keepRatio;

 private  boolean fitWithinDimensions;

/**
 * Creates a {@link FixedSizeThumbnailMaker}.
 * <p>
 * The size of the resulting thumbnail, and whether or not the aspect ratio
 * of the original image should be maintained in the thumbnail must be
 * set before this instance is able to produce thumbnails.
 */
public FixedSizeThumbnailMaker() {
    super();
    ready.unset(PARAM_SIZE);
    ready.unset(PARAM_KEEP_RATIO);
    ready.unset(PARAM_FIT_WITHIN);
}/**
 * Creates a {@link FixedSizeThumbnailMaker} which creates thumbnails
 * with the specified size.
 * <p>
 * Before this instance is able to produce thumbnails, whether or not the
 * aspect ratio of the original image should be maintained in the thumbnail
 * must be specified by calling the {@link #keepAspectRatio(boolean)}
 * method.
 *
 * @param width			The width of the thumbnail to produce.
 * @param height		The height of the thumbnails to produce.
 */
public FixedSizeThumbnailMaker(int width, int height) {
    this();
    size(width, height);
}/**
 * Creates a {@link FixedSizeThumbnailMaker} which creates thumbnails
 * with the specified size. Whether or not the aspect ratio of the original
 * image should be preserved by the thumbnail is also specified at
 * instantiation.
 *
 * @param width			The width of the thumbnail to produce.
 * @param height		The height of the thumbnails to produce.
 * @param aspectRatio	Whether or not to maintain the aspect ratio in the
 * 						thumbnail the same as the original image.
 * 						<p>
 * 						If {@code true} is specified, then the
 * 						thumbnail image will have the same aspect ratio
 * 						as the original image.
 */
public FixedSizeThumbnailMaker(int width, int height, boolean aspectRatio) {
    this();
    size(width, height);
    keepAspectRatio(aspectRatio);
}/**
 * Creates a {@link FixedSizeThumbnailMaker} which creates thumbnails
 * with the specified size. Whether or not the aspect ratio of the original
 * image should be preserved by the thumbnail, and whether to fit the
 * thumbnail within the given dimensions is also specified at
 * instantiation.
 *
 * @param width			The width of the thumbnail to produce.
 * @param height		The height of the thumbnails to produce.
 * @param aspectRatio	Whether or not to maintain the aspect ratio in the
 * 						thumbnail the same as the original image.
 * 						<p>
 * 						If {@code true} is specified, then the
 * 						thumbnail image will have the same aspect ratio
 * 						as the original image.
 * @param fit			Whether or not to fit the thumbnail within the
 * 						specified dimensions.
 * 						<p>
 * 						If {@code true} is specified, then the thumbnail
 * 						will be sized to fit within the specified
 * 						{@code width} and {@code height}.
 */
public FixedSizeThumbnailMaker(int width, int height, boolean aspectRatio, boolean fit) {
    this();
    size(width, height);
    keepAspectRatio(aspectRatio);
    fitWithinDimensions(fit);
}
public FixedSizeThumbnailMaker size(int width,int height){
    if (ready.isSet(PARAM_SIZE)) {
        throw new IllegalStateException("The size has already been set.");
    }
    if (width <= 0) {
        throw new IllegalArgumentException("Width must be greater than zero.");
    }
    if (height <= 0) {
        throw new IllegalArgumentException("Height must be greater than zero.");
    }
    this.width = width;
    this.height = height;
    ready.set(PARAM_SIZE);
    return this;
}


public FixedSizeThumbnailMaker fitWithinDimensions(boolean fit){
    if (ready.isSet(PARAM_FIT_WITHIN)) {
        throw new IllegalStateException("Whether to fit within dimensions has already been set.");
    }
    this.fitWithinDimensions = fit;
    ready.set(PARAM_FIT_WITHIN);
    return this;
}


public FixedSizeThumbnailMaker keepAspectRatio(boolean keep){
    if (ready.isSet(PARAM_KEEP_RATIO)) {
        throw new IllegalStateException("Whether to keep the aspect ratio has already been set.");
    }
    this.keepRatio = keep;
    ready.set(PARAM_KEEP_RATIO);
    return this;
}


@Override
public BufferedImage make(BufferedImage img){
    int targetWidth = this.width;
    int targetHeight = this.height;
    if (keepRatio) {
        int sourceWidth = img.getWidth();
        int sourceHeight = img.getHeight();
        double sourceRatio = (double) sourceWidth / (double) sourceHeight;
        double targetRatio = (double) targetWidth / (double) targetHeight;
        /*
			 * If the ratios are not the same, then the appropriate
			 * width and height must be picked.
			 */
        if (Double.compare(sourceRatio, targetRatio) != 0) {
            if (fitWithinDimensions) {
                if (sourceRatio > targetRatio) {
                    targetWidth = width;
                    targetHeight = (int) Math.round(targetWidth / sourceRatio);
                } else {
                    targetWidth = (int) Math.round(targetHeight * sourceRatio);
                    targetHeight = height;
                }
            } else {
                if (sourceRatio > targetRatio) {
                    targetWidth = (int) Math.round(targetHeight * sourceRatio);
                    targetHeight = height;
                } else {
                    targetWidth = width;
                    targetHeight = (int) Math.round(targetWidth / sourceRatio);
                }
            }
        }
    }
    targetWidth = (targetWidth == 0) ? 1 : targetWidth;
    targetHeight = (targetHeight == 0) ? 1 : targetHeight;
    return super.makeThumbnail(img, targetWidth, targetHeight);
}


}