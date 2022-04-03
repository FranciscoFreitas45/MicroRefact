package com.ec.survey.tools;
 import javax.imageio.ImageIO;
import java.awt;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
public class Captcha {

private Captcha() {
}
public String generateText(){
    return new StringTokenizer(UUID.randomUUID().toString(), "-").nextToken();
}


public byte[] generateImage(String text){
    int w = 180, h = 40;
    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.setColor(Color.white);
    graphics.fillRect(0, 0, w, h);
    graphics.setFont(new Font("Serif", Font.PLAIN, 26));
    graphics.setColor(Color.blue);
    int start = 10;
    byte[] bytes = text.getBytes();
    Random random = new Random();
    for (int i = 0; i < bytes.length; i++) {
        graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        graphics.drawString(new String(new byte[] { bytes[i] }), start + (i * 20), (int) (Math.random() * 20 + 20));
    }
    graphics.setColor(Color.white);
    for (int i = 0; i < 8; i++) {
        graphics.drawOval((int) (Math.random() * 160), (int) (Math.random() * 10), 30, 30);
    }
    graphics.dispose();
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    try {
        ImageIO.write(image, "png", bout);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return bout.toByteArray();
}


}