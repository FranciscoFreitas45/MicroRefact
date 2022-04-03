package net.coobird.thumbnailator;
 import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.filters.Pipeline;
import net.coobird.thumbnailator.filters.Rotation;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.AbsoluteSize;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.geometry.Region;
import net.coobird.thumbnailator.geometry.Size;
import net.coobird.thumbnailator.name.Rename;
import net.coobird.thumbnailator.resizers.BicubicResizer;
import net.coobird.thumbnailator.resizers.BilinearResizer;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.FixedResizerFactory;
import net.coobird.thumbnailator.resizers.ProgressiveBilinearResizer;
import net.coobird.thumbnailator.resizers.Resizer;
import net.coobird.thumbnailator.resizers.ResizerFactory;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
import net.coobird.thumbnailator.resizers.configurations.Dithering;
import net.coobird.thumbnailator.resizers.configurations.Rendering;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import net.coobird.thumbnailator.tasks.SourceSinkThumbnailTask;
import net.coobird.thumbnailator.tasks.io.BufferedImageSink;
import net.coobird.thumbnailator.tasks.io.BufferedImageSource;
import net.coobird.thumbnailator.tasks.io.FileImageSink;
import net.coobird.thumbnailator.tasks.io.FileImageSource;
import net.coobird.thumbnailator.tasks.io.ImageSource;
import net.coobird.thumbnailator.tasks.io.InputStreamImageSource;
import net.coobird.thumbnailator.tasks.io.OutputStreamImageSink;
import net.coobird.thumbnailator.tasks.io.URLImageSource;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
public class Thumbnails {

 private  Iterable<ImageSource<T>> sources;

 private  Iterable<String> filenames;

 private Iterator<String> iter;

 private  Iterable<File> files;

 private Iterator<File> iter;

 private  Iterable<URL> urls;

 private Iterator<URL> iter;

 private  Iterable<? extends InputStream> inputStreams;

 private Iterator<? extends InputStream> iter;

 private  Iterable<BufferedImage> image;

 private Iterator<BufferedImage> iter;

 private Iterator<ImageSource<T>> sourceIter;

 private  String name;

 private  Map<Properties,Status> statusMap;

 private  int IMAGE_TYPE_UNSPECIFIED;

 private  int DIMENSION_NOT_SPECIFIED;

 private  int width;

 private  int height;

 private  double scaleWidth;

 private  double scaleHeight;

 private  Region sourceRegion;

 private  int imageType;

 private  boolean keepAspectRatio;

 private  String outputFormat;

 private  String outputFormatType;

 private  float outputQuality;

 private  ScalingMode scalingMode;

 private  AlphaInterpolation alphaInterpolation;

 private  Dithering dithering;

 private  Antialiasing antialiasing;

 private  Rendering rendering;

 private  ResizerFactory resizerFactory;

 private  boolean allowOverwrite;

 private  boolean fitWithinDimenions;

 private  boolean useExifOrientation;

 private  Position croppingPosition;

 private  Pipeline filterPipeline;

/**
 * This class is not intended to be instantiated.
 */
private Thumbnails() {
}
public Builder<T> rotate(double angle){
    filterPipeline.add(Rotation.newRotator(angle));
    return this;
}


public String getName(){
    return name;
}


public Builder<File> fromFiles(Iterable<File> files){
    checkForNull(files, "Cannot specify null for input files.");
    checkForEmpty(files, "Cannot specify an empty collection for input files.");
    return Builder.ofFiles(files);
}


public Builder<T> alphaInterpolation(AlphaInterpolation config){
    checkForNull(config, "Alpha interpolation is null.");
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    updateStatus(Properties.ALPHA_INTERPOLATION, Status.ALREADY_SET);
    alphaInterpolation = config;
    return this;
}


public Builder<InputStream> ofInputStreams(Iterable<? extends InputStream> inputStreams){
    Iterable<ImageSource<InputStream>> iter = new InputStreamImageSourceIterator(inputStreams);
    return new Builder<InputStream>(iter);
}


public Builder<File> ofFiles(Iterable<File> files){
    Iterable<ImageSource<File>> iter = new FileImageSourceIterator(files);
    return new Builder<File>(iter);
}


public Iterator<BufferedImage> iterator(){
    return new Iterator<BufferedImage>() {

        Iterator<ImageSource<T>> sourceIter = sources.iterator();

        public boolean hasNext() {
            return sourceIter.hasNext();
        }

        public BufferedImage next() {
            ImageSource<T> source = sourceIter.next();
            BufferedImageSink destination = new BufferedImageSink();
            try {
                Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, BufferedImage>(makeParam(), source, destination));
            } catch (IOException e) {
                return null;
            }
            return destination.getSink();
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove elements from this iterator.");
        }
    };
}


public Builder<T> resizerFactory(ResizerFactory resizerFactory){
    checkForNull(resizerFactory, "ResizerFactory is null.");
    updateStatus(Properties.RESIZER_FACTORY, Status.ALREADY_SET);
    updateStatus(Properties.RESIZER, Status.CANNOT_SET);
    // disable the methods which set parameters for the Resizer
    updateStatus(Properties.SCALING_MODE, Status.CANNOT_SET);
    updateStatus(Properties.ALPHA_INTERPOLATION, Status.CANNOT_SET);
    updateStatus(Properties.DITHERING, Status.CANNOT_SET);
    updateStatus(Properties.ANTIALIASING, Status.CANNOT_SET);
    updateStatus(Properties.RENDERING, Status.CANNOT_SET);
    this.resizerFactory = resizerFactory;
    return this;
}


public void checkReadiness(){
    for (Map.Entry<Properties, Status> s : statusMap.entrySet()) {
        if (s.getValue() == Status.NOT_READY) {
            throw new IllegalStateException(s.getKey().getName() + " is not set.");
        }
    }
}


public Builder<T> imageType(int type){
    updateStatus(Properties.IMAGE_TYPE, Status.ALREADY_SET);
    imageType = type;
    return this;
}


public Builder<T> outputFormat(String format){
    if (!ThumbnailatorUtils.isSupportedOutputFormat(format)) {
        throw new IllegalArgumentException("Specified format is not supported: " + format);
    }
    updateStatus(Properties.OUTPUT_FORMAT, Status.ALREADY_SET);
    outputFormat = format;
    return this;
}


public Builder<T> height(int height){
    if (statusMap.get(Properties.SIZE) != Status.CANNOT_SET) {
        updateStatus(Properties.SIZE, Status.CANNOT_SET);
    }
    if (statusMap.get(Properties.SCALE) != Status.CANNOT_SET) {
        updateStatus(Properties.SCALE, Status.CANNOT_SET);
    }
    updateStatus(Properties.HEIGHT, Status.ALREADY_SET);
    validateDimensions(Integer.MAX_VALUE, height);
    this.height = height;
    return this;
}


public Builder<T> forceSize(int width,int height){
    updateStatus(Properties.SIZE, Status.ALREADY_SET);
    updateStatus(Properties.KEEP_ASPECT_RATIO, Status.ALREADY_SET);
    updateStatus(Properties.SCALE, Status.CANNOT_SET);
    validateDimensions(width, height);
    this.width = width;
    this.height = height;
    this.keepAspectRatio = false;
    return this;
}


public Builder<InputStream> fromInputStreams(Iterable<? extends InputStream> inputStreams){
    checkForNull(inputStreams, "Cannot specify null for InputStreams.");
    checkForEmpty(inputStreams, "Cannot specify an empty collection for InputStreams.");
    return Builder.ofInputStreams(inputStreams);
}


public ThumbnailParameter makeParam(){
    prepareResizerFactory();
    int imageTypeToUse = imageType;
    if (imageType == IMAGE_TYPE_UNSPECIFIED) {
        imageTypeToUse = ThumbnailParameter.ORIGINAL_IMAGE_TYPE;
    }
    /*
			 * croppingPosition being non-null means that a crop should
			 * take place.
			 */
    if (croppingPosition != null) {
        filterPipeline.addFirst(new Canvas(width, height, croppingPosition));
    }
    if (Double.isNaN(scaleWidth)) {
        // If the dimensions were specified, do the following.
        // Check that at least one dimension is specified.
        // If it's not, it's a bug.
        if (width == DIMENSION_NOT_SPECIFIED && height == DIMENSION_NOT_SPECIFIED) {
            throw new IllegalStateException("The width or height must be specified. If this " + "exception is thrown, it is due to a bug in the " + "Thumbnailator library.");
        }
        // Set the unspecified dimension to a default value.
        if (width == DIMENSION_NOT_SPECIFIED) {
            width = Integer.MAX_VALUE;
        }
        if (height == DIMENSION_NOT_SPECIFIED) {
            height = Integer.MAX_VALUE;
        }
        return new ThumbnailParameter(new Dimension(width, height), sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageTypeToUse, filterPipeline.getFilters(), resizerFactory, fitWithinDimenions, useExifOrientation);
    } else {
        // If the scaling factor was specified
        return new ThumbnailParameter(scaleWidth, scaleHeight, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, imageTypeToUse, filterPipeline.getFilters(), resizerFactory, fitWithinDimenions, useExifOrientation);
    }
}


public Builder<T> watermark(Position position,BufferedImage image,float opacity){
    filterPipeline.add(new Watermark(position, image, opacity));
    return this;
}


public void toOutputStreams(Iterable<? extends OutputStream> iterable){
    checkReadiness();
    if (iterable == null) {
        throw new NullPointerException("OutputStream iterable is null.");
    }
    Iterator<? extends OutputStream> osIter = iterable.iterator();
    for (ImageSource<T> source : sources) {
        /*
				 * if the image is from a BufferedImage, then we require that the
				 * output format be set. (or else, we can't tell what format to
				 * output as!)
				 */
        if (source instanceof BufferedImageSource) {
            if (isOutputFormatNotSet()) {
                throw new IllegalStateException("Output format not specified.");
            }
        }
        if (!osIter.hasNext()) {
            throw new IndexOutOfBoundsException("Not enough file names provided by iterator.");
        }
        OutputStreamImageSink destination = new OutputStreamImageSink(osIter.next());
        Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, OutputStream>(makeParam(), source, destination));
    }
}


public Builder<T> outputQuality(double quality){
    if (quality < 0.0d || quality > 1.0d) {
        throw new IllegalArgumentException("The quality setting must be in the range 0.0d and " + "1.0d, inclusive.");
    }
    updateStatus(Properties.OUTPUT_QUALITY, Status.ALREADY_SET);
    outputQuality = (float) quality;
    if (outputQuality < 0.0f) {
        outputQuality = 0.0f;
    } else if (outputQuality > 1.0f) {
        outputQuality = 1.0f;
    }
    return this;
}


public void toOutputStream(OutputStream os){
    checkReadiness();
    Iterator<ImageSource<T>> iter = sources.iterator();
    ImageSource<T> source = iter.next();
    if (iter.hasNext()) {
        throw new IllegalArgumentException("Cannot output multiple thumbnails to a single OutputStream.");
    }
    /*
			 * if the image is from a BufferedImage, then we require that the
			 * output format be set. (or else, we can't tell what format to
			 * output as!)
			 */
    if (source instanceof BufferedImageSource) {
        if (isOutputFormatNotSet()) {
            throw new IllegalStateException("Output format not specified.");
        }
    }
    OutputStreamImageSink destination = new OutputStreamImageSink(os);
    Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, OutputStream>(makeParam(), source, destination));
}


public void validateDimensions(int width,int height){
    if (width <= 0 && height <= 0) {
        throw new IllegalArgumentException("Destination image dimensions must not be less than " + "0 pixels.");
    } else if (width <= 0 || height <= 0) {
        String dimension = width == 0 ? "width" : "height";
        throw new IllegalArgumentException("Destination image " + dimension + " must not be " + "less than or equal to 0 pixels.");
    }
}


public boolean hasNext(){
    return sourceIter.hasNext();
}


public Builder<T> rendering(Rendering config){
    checkForNull(config, "Rendering is null.");
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    updateStatus(Properties.RENDERING, Status.ALREADY_SET);
    rendering = config;
    return this;
}


public void checkForEmpty(Iterable<?> o,String message){
    if (!o.iterator().hasNext()) {
        throw new IllegalArgumentException(message);
    }
}


public Builder<T> useOriginalFormat(){
    updateStatus(Properties.OUTPUT_FORMAT, Status.ALREADY_SET);
    outputFormat = ThumbnailParameter.ORIGINAL_FORMAT;
    return this;
}


public Resizer makeResizer(ScalingMode mode){
    Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
    hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, alphaInterpolation.getValue());
    hints.put(RenderingHints.KEY_DITHERING, dithering.getValue());
    hints.put(RenderingHints.KEY_ANTIALIASING, antialiasing.getValue());
    hints.put(RenderingHints.KEY_RENDERING, rendering.getValue());
    if (mode == ScalingMode.BILINEAR) {
        return new BilinearResizer(hints);
    } else if (mode == ScalingMode.BICUBIC) {
        return new BicubicResizer(hints);
    } else if (mode == ScalingMode.PROGRESSIVE_BILINEAR) {
        return new ProgressiveBilinearResizer(hints);
    } else {
        return new ProgressiveBilinearResizer(hints);
    }
}


public Builder<T> size(int width,int height){
    updateStatus(Properties.SIZE, Status.ALREADY_SET);
    updateStatus(Properties.SCALE, Status.CANNOT_SET);
    validateDimensions(width, height);
    this.width = width;
    this.height = height;
    return this;
}


public void toFile(String outFilepath){
    checkReadiness();
    Iterator<ImageSource<T>> iter = sources.iterator();
    ImageSource<T> source = iter.next();
    if (iter.hasNext()) {
        throw new IllegalArgumentException("Cannot output multiple thumbnails to one file.");
    }
    FileImageSink destination = new FileImageSink(outFilepath, allowOverwrite);
    Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, File>(makeParam(), source, destination));
}


public Builder<T> addFilter(ImageFilter filter){
    if (filter == null) {
        throw new NullPointerException("Filter is null.");
    }
    filterPipeline.add(filter);
    return this;
}


public void checkForNull(Object o,String message){
    if (o == null) {
        throw new NullPointerException(message);
    }
}


public BufferedImage asBufferedImage(){
    checkReadiness();
    Iterator<ImageSource<T>> iter = sources.iterator();
    ImageSource<T> source = iter.next();
    if (iter.hasNext()) {
        throw new IllegalArgumentException("Cannot create one thumbnail from multiple original images.");
    }
    BufferedImageSink destination = new BufferedImageSink();
    Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, BufferedImage>(makeParam(), source, destination));
    return destination.getSink();
}


public Builder<T> crop(Position position){
    checkForNull(position, "Position cannot be null.");
    updateStatus(Properties.CROP, Status.ALREADY_SET);
    updateStatus(Properties.SCALE, Status.CANNOT_SET);
    croppingPosition = position;
    fitWithinDimenions = false;
    return this;
}


public BufferedImage next(){
    ImageSource<T> source = sourceIter.next();
    BufferedImageSink destination = new BufferedImageSink();
    try {
        Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, BufferedImage>(makeParam(), source, destination));
    } catch (IOException e) {
        return null;
    }
    return destination.getSink();
}


public Builder<File> fromFilenames(Iterable<String> files){
    checkForNull(files, "Cannot specify null for input files.");
    checkForEmpty(files, "Cannot specify an empty collection for input files.");
    return Builder.ofStrings(files);
}


public Builder<T> scale(double scaleWidth,double scaleHeight){
    updateStatus(Properties.SCALE, Status.ALREADY_SET);
    updateStatus(Properties.SIZE, Status.CANNOT_SET);
    updateStatus(Properties.KEEP_ASPECT_RATIO, Status.CANNOT_SET);
    if (scaleWidth <= 0.0 || scaleHeight <= 0.0) {
        throw new IllegalArgumentException("The scaling factor is equal to or less than 0.");
    }
    if (Double.isNaN(scaleWidth) || Double.isNaN(scaleHeight)) {
        throw new IllegalArgumentException("The scaling factor is not a number.");
    }
    if (Double.isInfinite(scaleWidth) || Double.isInfinite(scaleHeight)) {
        throw new IllegalArgumentException("The scaling factor cannot be infinity.");
    }
    this.scaleWidth = scaleWidth;
    this.scaleHeight = scaleHeight;
    return this;
}


public Builder<T> scalingMode(ScalingMode config){
    checkForNull(config, "Scaling mode is null.");
    updateStatus(Properties.SCALING_MODE, Status.ALREADY_SET);
    updateStatus(Properties.RESIZER, Status.CANNOT_SET);
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    scalingMode = config;
    return this;
}


public List<BufferedImage> asBufferedImages(){
    checkReadiness();
    List<BufferedImage> thumbnails = new ArrayList<BufferedImage>();
    // Create thumbnails
    for (ImageSource<T> source : sources) {
        BufferedImageSink destination = new BufferedImageSink();
        Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, BufferedImage>(makeParam(), source, destination));
        thumbnails.add(destination.getSink());
    }
    return thumbnails;
}


public void remove(){
    throw new UnsupportedOperationException("Cannot remove elements from this iterator.");
}


public Builder<BufferedImage> of(BufferedImage images){
    checkForNull(images, "Cannot specify null for images.");
    checkForEmpty(images, "Cannot specify an empty array for images.");
    return Builder.ofBufferedImages(Arrays.asList(images));
}


public Builder<T> addFilters(List<ImageFilter> filters){
    if (filters == null) {
        throw new NullPointerException("Filters is null.");
    }
    filterPipeline.addAll(filters);
    return this;
}


public Builder<File> ofStrings(Iterable<String> filenames){
    Iterable<ImageSource<File>> iter = new StringImageSourceIterator(filenames);
    return new Builder<File>(iter);
}


public Builder<T> allowOverwrite(boolean allowOverwrite){
    updateStatus(Properties.ALLOW_OVERWRITE, Status.ALREADY_SET);
    this.allowOverwrite = allowOverwrite;
    return this;
}


public Builder<T> dithering(Dithering config){
    checkForNull(config, "Dithering is null.");
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    updateStatus(Properties.DITHERING, Status.ALREADY_SET);
    dithering = config;
    return this;
}


public Builder<T> useExifOrientation(boolean useExifOrientation){
    updateStatus(Properties.USE_EXIF_ORIENTATION, Status.ALREADY_SET);
    this.useExifOrientation = useExifOrientation;
    return this;
}


public Builder<T> resizer(Resizer resizer){
    checkForNull(resizer, "Resizer is null.");
    updateStatus(Properties.RESIZER, Status.ALREADY_SET);
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    updateStatus(Properties.SCALING_MODE, Status.CANNOT_SET);
    this.resizerFactory = new FixedResizerFactory(resizer);
    return this;
}


public boolean isOutputFormatNotSet(){
    return outputFormat == null || ThumbnailParameter.DETERMINE_FORMAT.equals(outputFormat);
}


public List<File> asFiles(File destinationDir,Rename rename){
    checkReadiness();
    if (rename == null) {
        throw new NullPointerException("Rename is null.");
    }
    if (destinationDir != null && !destinationDir.isDirectory()) {
        throw new IllegalArgumentException("Given destination is not a directory.");
    }
    List<File> destinationFiles = new ArrayList<File>();
    for (ImageSource<T> source : sources) {
        if (!(source instanceof FileImageSource)) {
            throw new IllegalStateException("Cannot create thumbnails to files if original images are not from files.");
        }
        ThumbnailParameter param = makeParam();
        File f = ((FileImageSource) source).getSource();
        File actualDestDir = destinationDir == null ? f.getParentFile() : destinationDir;
        File destinationFile = new File(actualDestDir, rename.apply(f.getName(), param));
        FileImageSink destination = new FileImageSink(destinationFile, allowOverwrite);
        try {
            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask<T, File>(param, source, destination));
            destinationFiles.add(destination.getSink());
        } catch (IllegalArgumentException e) {
        /*
					 * Handle the IllegalArgumentException which is thrown when
					 * the destination file already exists by not adding the
					 * current file to the destinationFiles list.
					 */
        }
    }
    return destinationFiles;
}


public Builder<T> determineOutputFormat(){
    updateStatus(Properties.OUTPUT_FORMAT, Status.ALREADY_SET);
    outputFormat = ThumbnailParameter.DETERMINE_FORMAT;
    return this;
}


public Builder<BufferedImage> fromImages(Iterable<BufferedImage> images){
    checkForNull(images, "Cannot specify null for images.");
    checkForEmpty(images, "Cannot specify an empty collection for images.");
    return Builder.ofBufferedImages(images);
}


public Builder<T> keepAspectRatio(boolean keep){
    if (statusMap.get(Properties.SCALE) == Status.ALREADY_SET) {
        throw new IllegalStateException("Cannot specify whether to " + "keep the aspect ratio if the scaling factor has " + "already been specified.");
    }
    if (statusMap.get(Properties.SIZE) == Status.NOT_READY) {
        throw new IllegalStateException("Cannot specify whether to " + "keep the aspect ratio unless the size parameter has " + "already been specified.");
    }
    if ((statusMap.get(Properties.WIDTH) == Status.ALREADY_SET || statusMap.get(Properties.HEIGHT) == Status.ALREADY_SET) && !keep) {
        throw new IllegalStateException("The aspect ratio must be " + "preserved when the width and/or height parameter " + "has already been specified.");
    }
    updateStatus(Properties.KEEP_ASPECT_RATIO, Status.ALREADY_SET);
    keepAspectRatio = keep;
    return this;
}


public Builder<T> antialiasing(Antialiasing config){
    checkForNull(config, "Antialiasing is null.");
    updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
    updateStatus(Properties.ANTIALIASING, Status.ALREADY_SET);
    antialiasing = config;
    return this;
}


public Iterable<BufferedImage> iterableBufferedImages(){
    checkReadiness();
    /*
			 * TODO To get the precise error information, there would have to
			 * be an event notification mechanism.
			 */
    return new BufferedImageIterable();
}


public Builder<URL> ofUrls(Iterable<URL> urls){
    Iterable<ImageSource<URL>> iter = new URLImageSourceIterator(urls);
    return new Builder<URL>(iter);
}


public Builder<BufferedImage> ofBufferedImages(Iterable<BufferedImage> images){
    Iterable<ImageSource<BufferedImage>> iter = new BufferedImageImageSourceIterator(images);
    return new Builder<BufferedImage>(iter);
}


public void toFiles(File destinationDir,Rename rename){
    asFiles(destinationDir, rename);
}


public void updateStatus(Properties property,Status newStatus){
    if (statusMap.get(property) == Status.ALREADY_SET) {
        throw new IllegalStateException(property.getName() + " is already set.");
    }
    /*
			 * The `newStatus != Status.CANNOT_SET` condition will allow the
			 * status to be set to CANNOT_SET to be set multiple times.
			 */
    if (newStatus != Status.CANNOT_SET && statusMap.get(property) == Status.CANNOT_SET) {
        throw new IllegalStateException(property.getName() + " cannot be set.");
    }
    statusMap.put(property, newStatus);
}


public Builder<T> width(int width){
    if (statusMap.get(Properties.SIZE) != Status.CANNOT_SET) {
        updateStatus(Properties.SIZE, Status.CANNOT_SET);
    }
    if (statusMap.get(Properties.SCALE) != Status.CANNOT_SET) {
        updateStatus(Properties.SCALE, Status.CANNOT_SET);
    }
    updateStatus(Properties.WIDTH, Status.ALREADY_SET);
    validateDimensions(width, Integer.MAX_VALUE);
    this.width = width;
    return this;
}


public Builder<T> sourceRegion(Rectangle region){
    if (region == null) {
        throw new NullPointerException("Region cannot be null.");
    }
    return sourceRegion(new Coordinate(region.x, region.y), new AbsoluteSize(region.getSize()));
}


public void prepareResizerFactory(){
    /*
			 * If the scalingMode has been set, then use scalingMode to obtain
			 * a resizer, else, use the resizer field.
			 */
    if (statusMap.get(Properties.SCALING_MODE) == Status.ALREADY_SET) {
        this.resizerFactory = new FixedResizerFactory(makeResizer(scalingMode));
    }
}


public Builder<T> outputFormatType(String formatType){
    /*
			 * If the output format is the original format, and the format type
			 * is being specified, it's going to be likely that the specified
			 * type will not be present in all the formats, so we'll disallow
			 * it. (e.g. setting type to "JPEG", and if the original formats
			 * were JPEG and PNG, then we'd have a problem.
			 */
    if (formatType != ThumbnailParameter.DEFAULT_FORMAT_TYPE && isOutputFormatNotSet()) {
        throw new IllegalArgumentException("Cannot set the format type if a specific output " + "format has not been specified.");
    }
    if (!ThumbnailatorUtils.isSupportedOutputFormatType(outputFormat, formatType)) {
        throw new IllegalArgumentException("Specified format type (" + formatType + ") is not " + " supported for the format: " + outputFormat);
    }
    /*
			 * If the output format type is set, then we'd better make the
			 * output format unchangeable, or else we'd risk having a type
			 * that is not part of the output format.
			 */
    updateStatus(Properties.OUTPUT_FORMAT_TYPE, Status.ALREADY_SET);
    if (!statusMap.containsKey(Properties.OUTPUT_FORMAT)) {
        updateStatus(Properties.OUTPUT_FORMAT, Status.CANNOT_SET);
    }
    outputFormatType = formatType;
    return this;
}


public Builder<URL> fromURLs(Iterable<URL> urls){
    checkForNull(urls, "Cannot specify null for input URLs.");
    checkForEmpty(urls, "Cannot specify an empty collection for input URLs.");
    return Builder.ofUrls(urls);
}


}