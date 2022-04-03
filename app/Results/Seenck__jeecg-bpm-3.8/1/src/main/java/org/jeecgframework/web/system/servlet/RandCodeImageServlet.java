package org.jeecgframework.web.system.servlet;
 import org.apache.commons.lang.CommonRandomUtil;
import org.jeecgframework.core.util.ResourceUtil;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
public class RandCodeImageServlet extends HttpServlet{

 private  long serialVersionUID;

 private  String SESSION_KEY_OF_RAND_CODE;

 private  int count;

 private  int width;

 private  int height;

 private  int lineWidth;

 private  boolean generateRandCode;


public Color getRandColor(int fc,int bc){
    // 取得给定范围随机颜色
    final Random random = new Random();
    if (fc > 255) {
        fc = 255;
    }
    if (bc > 255) {
        bc = 255;
    }
    final int r = fc + random.nextInt(bc - fc);
    final int g = fc + random.nextInt(bc - fc);
    final int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
}


@Override
public void doPost(HttpServletRequest request,HttpServletResponse response){
    doGet(request, response);
}


@Override
public void doGet(HttpServletRequest request,HttpServletResponse response){
    // 设置页面不缓存
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    // response.setContentType("image/png");
    // 在内存中创建图象
    final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // 获取图形上下文
    final Graphics2D graphics = (Graphics2D) image.getGraphics();
    // 设定背景颜色
    // ---1
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
    // 设定边框颜色
    // graphics.setColor(getRandColor(100, 200)); // ---2
    graphics.drawRect(0, 0, width - 1, height - 1);
    final Random random = new Random();
    // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
    for (int i = 0; i < count; i++) {
        // ---3
        graphics.setColor(getRandColor(150, 200));
        // 保证画在边框之内
        final int x = random.nextInt(width - lineWidth - 1) + 1;
        final int y = random.nextInt(height - lineWidth - 1) + 1;
        final int xl = random.nextInt(lineWidth);
        final int yl = random.nextInt(lineWidth);
        graphics.drawLine(x, y, x + xl, y + yl);
    }
    // 取随机产生的认证码(4位数字)
    final String resultCode = exctractRandCode();
    for (int i = 0; i < resultCode.length(); i++) {
        // 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
        // graphics.setColor(new Color(20 + random.nextInt(130), 20 + random
        // .nextInt(130), 20 + random.nextInt(130)));
        // 设置字体颜色
        graphics.setColor(Color.BLACK);
        // 设置字体样式
        // graphics.setFont(new Font("Arial Black", Font.ITALIC, 18));
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 24));
        // 设置字符，字符间距，上边距
        graphics.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
    }
    // 将认证码存入SESSION
    request.getSession().setAttribute(SESSION_KEY_OF_RAND_CODE, resultCode);
    // 图象生效
    graphics.dispose();
    // 输出图象到页面
    ImageIO.write(image, "JPEG", response.getOutputStream());
}


public String exctractRandCode(){
    final String randCodeType = ResourceUtil.getRandCodeType();
    int randCodeLength = Integer.parseInt(ResourceUtil.getRandCodeLength());
    if (randCodeType == null) {
        return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
    } else {
        switch(randCodeType.charAt(0)) {
            case '1':
                return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
            case '2':
                return RandCodeImageEnum.LOWER_CHAR.generateStr(randCodeLength);
            case '3':
                return RandCodeImageEnum.UPPER_CHAR.generateStr(randCodeLength);
            case '4':
                return RandCodeImageEnum.LETTER_CHAR.generateStr(randCodeLength);
            case '5':
                return RandCodeImageEnum.ALL_CHAR.generateStr(randCodeLength);
            default:
                return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
        }
    }
}


}