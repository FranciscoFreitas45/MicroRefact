package com.evolvingreality.onleave.web.filter.gzip;
 import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
public class GZipServletOutputStream extends ServletOutputStream{

 private  OutputStream stream;

public GZipServletOutputStream(OutputStream output) throws IOException {
    super();
    this.stream = output;
}
@Override
public void flush(){
    this.stream.flush();
}


@Override
public void setWriteListener(WriteListener listener){
}


@Override
public boolean isReady(){
    return true;
}


@Override
public void close(){
    this.stream.close();
}


@Override
public void write(int b){
    this.stream.write(b);
}


}