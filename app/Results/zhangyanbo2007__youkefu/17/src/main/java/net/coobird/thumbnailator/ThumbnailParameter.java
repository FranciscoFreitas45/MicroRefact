package net.coobird.thumbnailator;
 import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.resizers.FixedResizerFactory;
import net.coobird.thumbnailator.geometry.Region;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.resizers.ResizerFactory;
public class ThumbnailParameter {

 public  String ORIGINAL_FORMAT;

 public  String DETERMINE_FORMAT;

 public  String DEFAULT_FORMAT_TYPE;

 public  float DEFAULT_QUALITY;

 public  int ORIGINAL_IMAGE_TYPE;

 public  int DEFAULT_IMAGE_TYPE;

 private  Dimension thumbnailSize;

 private  double widthScalingFactor;

 private  double heightScalingFactor;

 private  boolean keepAspectRatio;

 private  String outputFormat;

 private  String outputFormatType;

 private  float outputQuality;

 private  int imageType;

 private  List<ImageFilter> filters;

 private  ResizerFactory resizerFactory;

 private  Region sourceRegion;

 private  boolean fitWithinDimensions;

 private  boolean useExifOrientation;

/**
 *  Private constructor which sets all the required fields, and performs
 *  validation of the given arguments.
 *  <p>
 *  This constructor is to be called from all the public constructors.
 *
 *  @param thumbnailSize		The size of the thumbnail to generate.
 *  @param widthScalingFactor	The scaling factor to apply to the width
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param heightScalingFactor	The scaling factor to apply to the height
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param sourceRegion		The region of the source image to use when
 *  							creating a thumbnail.
 *  							A value of {@code null} indicates that the
 *  							entire source image should be used to create
 *  							the thumbnail.
 *  @param keepAspectRatio	Indicates whether or not the thumbnail should
 *  							maintain the aspect ratio of the original image.
 *  @param outputFormat		A string indicating the compression format
 *  							that should be applied on the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#ORIGINAL_FORMAT}
 *  							should be provided if the same image format as
 *  							the original should	be used for the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DETERMINE_FORMAT}
 *  							should be provided if the output format of the
 *  							thumbnail should be the determined from the
 *  							information available, such as the output file
 *  							name of the thumbnail.
 *  @param outputFormatType	A string indicating the compression type that
 *  							should be used when writing the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_FORMAT_TYPE}
 *  							should be provided if the thumbnail should be
 *  							written using the default compression type of
 *  							the codec specified in {@code outputFormat}.
 *  @param outputQuality		A value from {@code 0.0f} to {@code 1.0f} which
 *  							indicates the quality setting to use for the
 *  							compression of the thumbnail. {@code 0.0f}
 *  							indicates the lowest quality, {@code 1.0f}
 *  							indicates the highest quality setting for the
 *  							compression.
 *  							{@link ThumbnailParameter#DEFAULT_QUALITY}
 *  							should be specified when the codec's default
 *  							compression quality settings should be used.
 *  @param imageType 		The {@link BufferedImage} image type of the
 *  							thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_IMAGE_TYPE}
 * 							should be specified when the default image
 * 							type should be used when creating the thumbnail.
 *  @param filters			The {@link ImageFilter}s to apply to the
 *  							thumbnail.
 *  							A value of {@code null} will be recognized as
 *  							no filters are to be applied.
 *  							The filters are applied after the original
 *  							image has been resized.
 *  @param resizerFactory	The {@link ResizerFactory} for obtaining a
 *  							{@link Resizer} that is to be used when
 *  							performing an image resizing operation.
 *  @param fitWithinDimensions	Whether or not to fit the thumbnail within
 *  								the specified dimensions.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								thumbnail will be sized to fit within the
 *  								specified dimensions, if the thumbnail is
 *  								going to exceed those dimensions.
 *  @param useExifOrientation	Whether or not to use the Exif metadata to
 *  								determine the orientation of the thumbnail.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								Exif metadata will be used to determine
 *  								the orientation of the thumbnail.
 *
 *  @throws IllegalArgumentException 	If the scaling factor is not a
 *  										rational number or is less than or
 *  										equal to 0, or if the
 *  										{@link ResizerFactory} is null.
 */
private ThumbnailParameter(Dimension thumbnailSize, double widthScalingFactor, double heightScalingFactor, Region sourceRegion, boolean keepAspectRatio, String outputFormat, String outputFormatType, float outputQuality, int imageType, List<ImageFilter> filters, ResizerFactory resizerFactory, boolean fitWithinDimensions, boolean useExifOrientation) {
    // The following 2 fields are set by the public constructors.
    this.thumbnailSize = thumbnailSize;
    this.widthScalingFactor = widthScalingFactor;
    this.heightScalingFactor = heightScalingFactor;
    this.keepAspectRatio = keepAspectRatio;
    this.sourceRegion = sourceRegion;
    this.outputFormat = outputFormat;
    this.outputFormatType = outputFormatType;
    /*
		 * Note:
		 * The value of DEFAULT_QUALITY is Float.NaN which cannot be compared
		 * by using the regular == operator. Therefore, to check that NaN is
		 * being used, one must use the Float.NaN method.
		 */
    if ((outputQuality < 0.0f || outputQuality > 1.0f) && !Float.isNaN(outputQuality)) {
        throw new IllegalArgumentException("The output quality must be " + "between 0.0f and 1.0f, or Float.NaN to use the default " + "compression quality of codec being used.");
    }
    this.outputQuality = outputQuality;
    this.imageType = imageType;
    // Creating a new ArrayList, as `filters` should be mutable as of 0.4.3.
    if (filters == null) {
        this.filters = new ArrayList<ImageFilter>();
    } else {
        this.filters = new ArrayList<ImageFilter>(filters);
    }
    if (resizerFactory == null) {
        throw new IllegalArgumentException("Resizer cannot be null");
    }
    this.resizerFactory = resizerFactory;
    this.fitWithinDimensions = fitWithinDimensions;
    this.useExifOrientation = useExifOrientation;
}/**
 *  Creates an object holding the parameters needed in order to make a
 *  thumbnail.
 *
 *  @param thumbnailSize		The size of the thumbnail to generate.
 *  @param sourceRegion		The region of the source image to use when
 *  							creating a thumbnail.
 *  							A value of {@code null} indicates that the
 *  							entire source image should be used to create
 *  							the thumbnail.
 *  @param keepAspectRatio	Indicates whether or not the thumbnail should
 *  							maintain the aspect ratio of the original image.
 *  @param outputFormat		A string indicating the compression format
 *  							that should be applied on the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#ORIGINAL_FORMAT}
 *  							should be provided if the same image format as
 *  							the original should	be used for the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DETERMINE_FORMAT}
 *  							should be provided if the output format of the
 *  							thumbnail should be the determined from the
 *  							information available, such as the output file
 *  							name of the thumbnail.
 *  @param outputFormatType	A string indicating the compression type that
 *  							should be used when writing the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_FORMAT_TYPE}
 *  							should be provided if the thumbnail should be
 *  							written using the default compression type of
 *  							the codec specified in {@code outputFormat}.
 *  @param outputQuality		A value from {@code 0.0f} to {@code 1.0f} which
 *  							indicates the quality setting to use for the
 *  							compression of the thumbnail. {@code 0.0f}
 *  							indicates the lowest quality, {@code 1.0f}
 *  							indicates the highest quality setting for the
 *  							compression.
 *  							{@link ThumbnailParameter#DEFAULT_QUALITY}
 *  							should be specified when the codec's default
 *  							compression quality settings should be used.
 *  @param imageType 		The {@link BufferedImage} image type of the
 *  							thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_IMAGE_TYPE}
 * 							should be specified when the default image
 * 							type should be used when creating the thumbnail.
 *  @param filters			The {@link ImageFilter}s to apply to the
 *  							thumbnail.
 *  							A value of {@code null} will be recognized as
 *  							no filters are to be applied.
 *  							The filters are applied after the original
 *  							image has been resized.
 *  @param resizer			The {@link Resizer} to use when performing the
 *  							resizing operation to create a thumbnail.
 *  @param fitWithinDimensions	Whether or not to fit the thumbnail within
 *  								the specified dimensions.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								thumbnail will be sized to fit within the
 *  								specified dimensions, if the thumbnail is
 *  								going to exceed those dimensions.
 *  @param useExifOrientation	Whether or not to use the Exif metadata to
 *  								determine the orientation of the thumbnail.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								Exif metadata will be used to determine
 *  								the orientation of the thumbnail.
 *
 *  @throws IllegalArgumentException 	If size is {@code null} or if the
 *  										dimensions are negative, or if the
 *  										{@link Resizer} is null.
 *  @since	0.4.3
 */
public ThumbnailParameter(Dimension thumbnailSize, Region sourceRegion, boolean keepAspectRatio, String outputFormat, String outputFormatType, float outputQuality, int imageType, List<ImageFilter> filters, Resizer resizer, boolean fitWithinDimensions, boolean useExifOrientation) {
    this(thumbnailSize, Double.NaN, Double.NaN, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageType, filters, new FixedResizerFactory(resizer), fitWithinDimensions, useExifOrientation);
    validateThumbnailSize();
}/**
 *  Creates an object holding the parameters needed in order to make a
 *  thumbnail.
 *
 *  @param widthScalingFactor	The scaling factor to apply to the width
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param heightScalingFactor	The scaling factor to apply to the height
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param sourceRegion		The region of the source image to use when
 *  							creating a thumbnail.
 *  							A value of {@code null} indicates that the
 *  							entire source image should be used to create
 *  							the thumbnail.
 *  @param keepAspectRatio	Indicates whether or not the thumbnail should
 *  							maintain the aspect ratio of the original image.
 *  @param outputFormat		A string indicating the compression format
 *  							that should be applied on the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#ORIGINAL_FORMAT}
 *  							should be provided if the same image format as
 *  							the original should	be used for the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DETERMINE_FORMAT}
 *  							should be provided if the output format of the
 *  							thumbnail should be the determined from the
 *  							information available, such as the output file
 *  							name of the thumbnail.
 *  @param outputFormatType	A string indicating the compression type that
 *  							should be used when writing the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_FORMAT_TYPE}
 *  							should be provided if the thumbnail should be
 *  							written using the default compression type of
 *  							the codec specified in {@code outputFormat}.
 *  @param outputQuality		A value from {@code 0.0f} to {@code 1.0f} which
 *  							indicates the quality setting to use for the
 *  							compression of the thumbnail. {@code 0.0f}
 *  							indicates the lowest quality, {@code 1.0f}
 *  							indicates the highest quality setting for the
 *  							compression.
 *  							{@link ThumbnailParameter#DEFAULT_QUALITY}
 *  							should be specified when the codec's default
 *  							compression quality settings should be used.
 *  @param imageType 		The {@link BufferedImage} image type of the
 *  							thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_IMAGE_TYPE}
 * 							should be specified when the default image
 * 							type should be used when creating the thumbnail.
 *  @param filters			The {@link ImageFilter}s to apply to the
 *  							thumbnail.
 *  							A value of {@code null} will be recognized as
 *  							no filters are to be applied.
 *  							The filters are applied after the original
 *  							image has been resized.
 *  @param resizer			The {@link Resizer} to use when performing the
 *  							resizing operation to create a thumbnail.
 *  @param fitWithinDimensions	Whether or not to fit the thumbnail within
 *  								the specified dimensions.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								thumbnail will be sized to fit within the
 *  								specified dimensions, if the thumbnail is
 *  								going to exceed those dimensions.
 *  @param useExifOrientation	Whether or not to use the Exif metadata to
 *  								determine the orientation of the thumbnail.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								Exif metadata will be used to determine
 *  								the orientation of the thumbnail.
 *
 *  @throws IllegalArgumentException 	If the scaling factor is not a
 *  										rational number or is less than or
 *  										equal to 0, or if the
 *  										{@link Resizer} is null.
 *  @since	0.4.3
 */
public ThumbnailParameter(double widthScalingFactor, double heightScalingFactor, Region sourceRegion, boolean keepAspectRatio, String outputFormat, String outputFormatType, float outputQuality, int imageType, List<ImageFilter> filters, Resizer resizer, boolean fitWithinDimensions, boolean useExifOrientation) {
    this(null, widthScalingFactor, heightScalingFactor, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageType, filters, new FixedResizerFactory(resizer), fitWithinDimensions, useExifOrientation);
    validateScalingFactor();
}/**
 *  Creates an object holding the parameters needed in order to make a
 *  thumbnail.
 *
 *  @param thumbnailSize		The size of the thumbnail to generate.
 *  @param sourceRegion		The region of the source image to use when
 *  							creating a thumbnail.
 *  							A value of {@code null} indicates that the
 *  							entire source image should be used to create
 *  							the thumbnail.
 *  @param keepAspectRatio	Indicates whether or not the thumbnail should
 *  							maintain the aspect ratio of the original image.
 *  @param outputFormat		A string indicating the compression format
 *  							that should be applied on the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#ORIGINAL_FORMAT}
 *  							should be provided if the same image format as
 *  							the original should	be used for the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DETERMINE_FORMAT}
 *  							should be provided if the output format of the
 *  							thumbnail should be the determined from the
 *  							information available, such as the output file
 *  							name of the thumbnail.
 *  @param outputFormatType	A string indicating the compression type that
 *  							should be used when writing the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_FORMAT_TYPE}
 *  							should be provided if the thumbnail should be
 *  							written using the default compression type of
 *  							the codec specified in {@code outputFormat}.
 *  @param outputQuality		A value from {@code 0.0f} to {@code 1.0f} which
 *  							indicates the quality setting to use for the
 *  							compression of the thumbnail. {@code 0.0f}
 *  							indicates the lowest quality, {@code 1.0f}
 *  							indicates the highest quality setting for the
 *  							compression.
 *  							{@link ThumbnailParameter#DEFAULT_QUALITY}
 *  							should be specified when the codec's default
 *  							compression quality settings should be used.
 *  @param imageType 		The {@link BufferedImage} image type of the
 *  							thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_IMAGE_TYPE}
 * 							should be specified when the default image
 * 							type should be used when creating the thumbnail.
 *  @param filters			The {@link ImageFilter}s to apply to the
 *  							thumbnail.
 *  							A value of {@code null} will be recognized as
 *  							no filters are to be applied.
 *  							The filters are applied after the original
 *  							image has been resized.
 *  @param resizerFactory	The {@link ResizerFactory} for obtaining a
 *  							{@link Resizer} that is to be used when
 *  							performing an image resizing operation.
 *  @param fitWithinDimensions	Whether or not to fit the thumbnail within
 *  								the specified dimensions.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								thumbnail will be sized to fit within the
 *  								specified dimensions, if the thumbnail is
 *  								going to exceed those dimensions.
 *  @param useExifOrientation	Whether or not to use the Exif metadata to
 *  								determine the orientation of the thumbnail.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								Exif metadata will be used to determine
 *  								the orientation of the thumbnail.
 *
 *  @throws IllegalArgumentException 	If size is {@code null} or if the
 *  										dimensions are negative, or if the
 *  										{@link ResizerFactory} is null.
 *  @since	0.4.3
 */
public ThumbnailParameter(Dimension thumbnailSize, Region sourceRegion, boolean keepAspectRatio, String outputFormat, String outputFormatType, float outputQuality, int imageType, List<ImageFilter> filters, ResizerFactory resizerFactory, boolean fitWithinDimensions, boolean useExifOrientation) {
    this(thumbnailSize, Double.NaN, Double.NaN, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageType, filters, resizerFactory, fitWithinDimensions, useExifOrientation);
    validateThumbnailSize();
}/**
 *  Creates an object holding the parameters needed in order to make a
 *  thumbnail.
 *
 *  @param widthScalingFactor	The scaling factor to apply to the width
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param heightScalingFactor	The scaling factor to apply to the height
 *  								when creating a	thumbnail from the original
 *  								image.
 *  @param sourceRegion		The region of the source image to use when
 *  							creating a thumbnail.
 *  							A value of {@code null} indicates that the
 *  							entire source image should be used to create
 *  							the thumbnail.
 *  @param keepAspectRatio	Indicates whether or not the thumbnail should
 *  							maintain the aspect ratio of the original image.
 *  @param outputFormat		A string indicating the compression format
 *  							that should be applied on the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#ORIGINAL_FORMAT}
 *  							should be provided if the same image format as
 *  							the original should	be used for the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DETERMINE_FORMAT}
 *  							should be provided if the output format of the
 *  							thumbnail should be the determined from the
 *  							information available, such as the output file
 *  							name of the thumbnail.
 *  @param outputFormatType	A string indicating the compression type that
 *  							should be used when writing the thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_FORMAT_TYPE}
 *  							should be provided if the thumbnail should be
 *  							written using the default compression type of
 *  							the codec specified in {@code outputFormat}.
 *  @param outputQuality		A value from {@code 0.0f} to {@code 1.0f} which
 *  							indicates the quality setting to use for the
 *  							compression of the thumbnail. {@code 0.0f}
 *  							indicates the lowest quality, {@code 1.0f}
 *  							indicates the highest quality setting for the
 *  							compression.
 *  							{@link ThumbnailParameter#DEFAULT_QUALITY}
 *  							should be specified when the codec's default
 *  							compression quality settings should be used.
 *  @param imageType 		The {@link BufferedImage} image type of the
 *  							thumbnail.
 *  							A value of
 *  							{@link ThumbnailParameter#DEFAULT_IMAGE_TYPE}
 * 							should be specified when the default image
 * 							type should be used when creating the thumbnail.
 *  @param filters			The {@link ImageFilter}s to apply to the
 *  							thumbnail.
 *  							A value of {@code null} will be recognized as
 *  							no filters are to be applied.
 *  							The filters are applied after the original
 *  							image has been resized.
 *  @param resizerFactory	The {@link ResizerFactory} for obtaining a
 *  							{@link Resizer} that is to be used when
 *  							performing an image resizing operation.
 *  @param fitWithinDimensions	Whether or not to fit the thumbnail within
 *  								the specified dimensions.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								thumbnail will be sized to fit within the
 *  								specified dimensions, if the thumbnail is
 *  								going to exceed those dimensions.
 *  @param useExifOrientation	Whether or not to use the Exif metadata to
 *  								determine the orientation of the thumbnail.
 *  								<p>
 *  								If {@code true} is specified, then the
 *  								Exif metadata will be used to determine
 *  								the orientation of the thumbnail.
 *
 *  @throws IllegalArgumentException 	If the scaling factor is not a
 *  										rational number or is less than or
 *  										equal to 0, or if the
 *  										{@link ResizerFactory} is null.
 *  @since	0.4.3
 */
public ThumbnailParameter(double widthScalingFactor, double heightScalingFactor, Region sourceRegion, boolean keepAspectRatio, String outputFormat, String outputFormatType, float outputQuality, int imageType, List<ImageFilter> filters, ResizerFactory resizerFactory, boolean fitWithinDimensions, boolean useExifOrientation) {
    this(null, widthScalingFactor, heightScalingFactor, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageType, filters, resizerFactory, fitWithinDimensions, useExifOrientation);
    validateScalingFactor();
}
public boolean useOriginalImageType(){
    return imageType == ORIGINAL_IMAGE_TYPE;
}


public String getOutputFormatType(){
    return outputFormatType;
}


public double getHeightScalingFactor(){
    return heightScalingFactor;
}


public boolean isKeepAspectRatio(){
    return keepAspectRatio;
}


public Region getSourceRegion(){
    return sourceRegion;
}


public Dimension getSize(){
    if (thumbnailSize != null) {
        return (Dimension) thumbnailSize.clone();
    } else {
        return null;
    }
}


public float getOutputQuality(){
    return outputQuality;
}


public ResizerFactory getResizerFactory(){
    return resizerFactory;
}


public void validateScalingFactor(){
    if (widthScalingFactor <= 0.0 || heightScalingFactor <= 0.0) {
        throw new IllegalArgumentException("Scaling factor is less than or equal to 0.");
    } else if (Double.isNaN(widthScalingFactor) || Double.isInfinite(widthScalingFactor)) {
        throw new IllegalArgumentException("Scaling factor must be a rational number.");
    } else if (Double.isNaN(heightScalingFactor) || Double.isInfinite(heightScalingFactor)) {
        throw new IllegalArgumentException("Scaling factor must be a rational number.");
    }
}


public int getType(){
    return imageType;
}


public void validateThumbnailSize(){
    if (thumbnailSize == null) {
        throw new IllegalArgumentException("Thumbnail size cannot be null.");
    } else if (thumbnailSize.width < 0 || thumbnailSize.height < 0) {
        throw new IllegalArgumentException("Thumbnail dimensions must be greater than 0.");
    }
}


public String getOutputFormat(){
    return outputFormat;
}


public boolean useExifOrientation(){
    return useExifOrientation;
}


public Resizer getResizer(){
    return resizerFactory.getResizer();
}


public boolean fitWithinDimenions(){
    return fitWithinDimensions;
}


public List<ImageFilter> getImageFilters(){
    return filters;
}


public double getWidthScalingFactor(){
    return widthScalingFactor;
}


}