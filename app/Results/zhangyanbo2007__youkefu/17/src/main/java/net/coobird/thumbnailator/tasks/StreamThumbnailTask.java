package net.coobird.thumbnailator.tasks;
 import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.tasks.io.InputStreamImageSource;
import net.coobird.thumbnailator.tasks.io.OutputStreamImageSink;
public class StreamThumbnailTask extends ThumbnailTask<InputStream, OutputStream>{

 private  SourceSinkThumbnailTask<InputStream,OutputStream> task;

/**
 * Creates a {@link ThumbnailTask} in which streamed image data from the
 * specified {@link InputStream} is output to a specified
 * {@link OutputStream}, using the parameters provided in the specified
 * {@link ThumbnailParameter}.
 *
 * @param param		The parameters to use to create the thumbnail.
 * @param is		The {@link InputStream} from which to obtain image data.
 * @param os		The {@link OutputStream} to send thumbnail data to.
 * @throws NullPointerException		If the parameter is {@code null}.
 */
public StreamThumbnailTask(ThumbnailParameter param, InputStream is, OutputStream os) {
    super(param);
    this.task = new SourceSinkThumbnailTask<InputStream, OutputStream>(param, new InputStreamImageSource(is), new OutputStreamImageSink(os));
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
public OutputStream getDestination(){
    return task.getDestination();
}


@Override
public InputStream getSource(){
    return task.getSource();
}


@Override
public void write(BufferedImage img){
    task.write(img);
}


}