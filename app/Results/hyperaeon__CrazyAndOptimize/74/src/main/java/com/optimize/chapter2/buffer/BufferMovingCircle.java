package com.optimize.chapter2.buffer;
 import java.awt.Color;
import java.awt.Graphics;
public class BufferMovingCircle extends NoBufferMovingCircle{

 private  long serialVersionUID;

 private Graphics doubleBuffer;


public void init(){
    super.init();
    doubleBuffer = image.getGraphics();
}


public void paint(Graphics g){
    doubleBuffer.setColor(Color.white);
    doubleBuffer.fillRect(0, 0, 200, 100);
    drawCircle(doubleBuffer);
    g.drawImage(image, 0, 0, this);
}


}