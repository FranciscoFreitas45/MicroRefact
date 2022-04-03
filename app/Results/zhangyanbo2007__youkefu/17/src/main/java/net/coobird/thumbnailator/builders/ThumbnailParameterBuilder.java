package net.coobird.thumbnailator.builders;
 import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.FixedResizerFactory;
import net.coobird.thumbnailator.geometry.Region;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.resizers.ResizerFactory;
public class ThumbnailParameterBuilder {

 private  int UNINITIALIZED;

 private  int width;

 private  int height;

 private  double widthScalingFactor;

 private  double heightScalingFactor;

 private  int imageType;

 private  boolean keepAspectRatio;

 private  float thumbnailQuality;

 private  String thumbnailFormat;

 private  String thumbnailFormatType;

 private  List<ImageFilter> filters;

 private  ResizerFactory resizerFactory;

 private  Region sourceRegion;

 private  boolean fitWithinDimensions;

 private  boolean useExifOrientation;

/**
 * Creates an instance of a {@link ThumbnailParameterBuilder}.
 */
public ThumbnailParameterBuilder() {
}
public ThumbnailParameterBuilder format(String format){
    this.thumbnailFormat = format;
    return this;
}


public ThumbnailParameterBuilder scale(double widthScalingFactor,double heightScalingFactor){
    if (widthScalingFactor <= 0.0 || heightScalingFactor <= 0.0) {
        throw new IllegalArgumentException("Scaling factor is less than or equal to 0.");
    } else if (Double.isNaN(widthScalingFactor) || Double.isInfinite(widthScalingFactor)) {
        throw new IllegalArgumentException("Scaling factor must be a rational number.");
    } else if (Double.isNaN(heightScalingFactor) || Double.isInfinite(heightScalingFactor)) {
        throw new IllegalArgumentException("Scaling factor must be a rational number.");
    }
    this.widthScalingFactor = widthScalingFactor;
    this.heightScalingFactor = heightScalingFactor;
    return this;
}


public ThumbnailParameterBuilder filters(List<ImageFilter> filters){
    if (filters == null) {
        throw new NullPointerException("Filters is null.");
    }
    this.filters = filters;
    return this;
}


public ThumbnailParameterBuilder fitWithinDimensions(boolean fit){
    this.fitWithinDimensions = fit;
    return this;
}


public ThumbnailParameterBuilder keepAspectRatio(boolean keep){
    this.keepAspectRatio = keep;
    return this;
}


public ThumbnailParameterBuilder quality(float quality){
    this.thumbnailQuality = quality;
    return this;
}


public ThumbnailParameterBuilder size(int width,int height){
    if (width < 0) {
        throw new IllegalArgumentException("Width must be greater than 0.");
    }
    if (height < 0) {
        throw new IllegalArgumentException("Height must be greater than 0.");
    }
    this.width = width;
    this.height = height;
    return this;
}


public ThumbnailParameterBuilder resizerFactory(ResizerFactory resizerFactory){
    if (resizerFactory == null) {
        throw new NullPointerException("Resizer is null.");
    }
    this.resizerFactory = resizerFactory;
    return this;
}


public ThumbnailParameter build(){
    if (!Double.isNaN(widthScalingFactor)) {
        // If scaling factor has been set.
        return new ThumbnailParameter(widthScalingFactor, heightScalingFactor, sourceRegion, keepAspectRatio, thumbnailFormat, thumbnailFormatType, thumbnailQuality, imageType, filters, resizerFactory, fitWithinDimensions, useExifOrientation);
    } else if (width != UNINITIALIZED && height != UNINITIALIZED) {
        return new ThumbnailParameter(new Dimension(width, height), sourceRegion, keepAspectRatio, thumbnailFormat, thumbnailFormatType, thumbnailQuality, imageType, filters, resizerFactory, fitWithinDimensions, useExifOrientation);
    } else {
        throw new IllegalStateException("The size nor the scaling factor has been set.");
    }
}


public ThumbnailParameterBuilder useExifOrientation(boolean use){
    this.useExifOrientation = use;
    return this;
}


public ThumbnailParameterBuilder resizer(Resizer resizer){
    if (resizer == null) {
        throw new NullPointerException("Resizer is null.");
    }
    this.resizerFactory = new FixedResizerFactory(resizer);
    return this;
}


public ThumbnailParameterBuilder region(Region sourceRegion){
    this.sourceRegion = sourceRegion;
    return this;
}


public ThumbnailParameterBuilder formatType(String formatType){
    this.thumbnailFormatType = formatType;
    return this;
}


public ThumbnailParameterBuilder imageType(int type){
    imageType = type;
    return this;
}


}