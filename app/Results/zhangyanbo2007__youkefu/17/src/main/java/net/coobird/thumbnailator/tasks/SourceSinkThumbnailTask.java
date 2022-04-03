package net.coobird.thumbnailator.tasks;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.tasks.io.ImageSink;
import net.coobird.thumbnailator.tasks.io.ImageSource;
public class SourceSinkThumbnailTask extends ThumbnailTask<S, D>{

 private  ImageSource<S> source;

 private  ImageSink<D> destination;

/**
 * Creates a {@link ThumbnailTask} in which an image is retrived from the
 * specified {@link ImageSource} and written to the specified
 * {@link ImageSink}, using the parameters provided in the specified
 * {@link ThumbnailParameter}.
 *
 * @param param				The parameters to use to create the thumbnail.
 * @param source			The source from which the image is retrieved
 * 							or read from.
 * @param destination		The destination to which the thumbnail is
 * 							stored or written to.
 * @throws NullPointerException		If either the parameter,
 * 									{@link ImageSource} or {@link ImageSink}
 * 									is {@code null}.
 */
public SourceSinkThumbnailTask(ThumbnailParameter param, ImageSource<S> source, ImageSink<D> destination) {
    super(param);
    if (source == null) {
        throw new NullPointerException("ImageSource cannot be null.");
    }
    if (destination == null) {
        throw new NullPointerException("ImageSink cannot be null.");
    }
    source.setThumbnailParameter(param);
    this.source = source;
    destination.setThumbnailParameter(param);
    this.destination = destination;
}
@Override
public BufferedImage read(){
    BufferedImage img = source.read();
    inputFormatName = source.getInputFormatName();
    return img;
}


@Override
public D getDestination(){
    return destination.getSink();
}


@Override
public S getSource(){
    return source.getSource();
}


@Override
public void write(BufferedImage img){
    String paramOutputFormat = param.getOutputFormat();
    String formatName = null;
    if (ThumbnailParameter.DETERMINE_FORMAT.equals(paramOutputFormat)) {
        paramOutputFormat = destination.preferredOutputFormatName();
    }
    if (paramOutputFormat == ThumbnailParameter.ORIGINAL_FORMAT) {
        formatName = inputFormatName;
    } else {
        formatName = paramOutputFormat;
    }
    destination.setOutputFormatName(formatName);
    destination.write(img);
}


}