package net.coobird.thumbnailator.tasks;
 import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.tasks.io.FileImageSink;
import net.coobird.thumbnailator.tasks.io.FileImageSource;
public class FileThumbnailTask extends ThumbnailTask<File, File>{

 private  SourceSinkThumbnailTask<File,File> task;

/**
 * Creates a {@link ThumbnailTask} in which image data is read from the
 * specified {@link File} and is output to a specified {@link File}, using
 * the parameters provided in the specified {@link ThumbnailParameter}.
 *
 * @param param				The parameters to use to create the thumbnail.
 * @param sourceFile		The {@link File} from which image data is read.
 * @param destinationFile	The {@link File} to which thumbnail is written.
 * @throws NullPointerException		If the parameter is {@code null}.
 */
public FileThumbnailTask(ThumbnailParameter param, File sourceFile, File destinationFile) {
    super(param);
    this.task = new SourceSinkThumbnailTask<File, File>(param, new FileImageSource(sourceFile), new FileImageSink(destinationFile));
}
@Override
public ThumbnailParameter getParam(){
    return task.getParam();
}


@Override
public BufferedImage read(){
    return task.read();
}


@Override
public File getDestination(){
    return task.getDestination();
}


@Override
public File getSource(){
    return task.getSource();
}


@Override
public void write(BufferedImage img){
    task.write(img);
}


}