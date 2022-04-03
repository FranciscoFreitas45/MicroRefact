package utilities.internal;
 import java.io.IOException;
import java.io.OutputStream;
public class EclipseStream extends OutputStream{

 private  OutputStream target;

 private  OutputStream lastStream;

// Constructors -----------------------------------------------------------
public EclipseStream(final OutputStream originalStream) {
    assert originalStream != null;
    this.target = originalStream;
}
@Override
public void flush(){
    this.target.flush();
}


public void swap(){
    if (EclipseStream.lastStream != this && EclipseStream.lastStream != null) {
        EclipseStream.lastStream.flush();
        try {
            Thread.sleep(250);
        } catch (final InterruptedException oops) {
        }
    }
    EclipseStream.lastStream = this;
}


@Override
public void close(){
    this.target.close();
}


@Override
public void write(int datum){
    this.swap();
    this.target.write(datum);
}


}