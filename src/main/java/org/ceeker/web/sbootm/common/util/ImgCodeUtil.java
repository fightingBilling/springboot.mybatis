package org.ceeker.web.sbootm.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码工具
 * @author zhangxiaoling01
 * @date  2016年7月4日 下午3:54:53
 * @see
 */
public class ImgCodeUtil {

    private static final String STR = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";

    private ImgCodeUtil() {
    }

    private static Color randomColor(int lower, int upper) {
        Random random = new Random();
        if (upper > 255)
            upper = 255;
        if (upper < 1)
            upper = 1;
        if (lower < 1)
            lower = 1;
        if (lower > 255)
            lower = 255;
        int r = lower + random.nextInt(upper - lower);
        int g = lower + random.nextInt(upper - lower);
        int b = lower + random.nextInt(upper - lower);
        return new Color(r, g, b);
    }

    public static BufferedImage getImage(String code) {
        Random random = new Random();
        int width = 120;// 验证码图片宽度
        int height = 40;// 验证码图片高度
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(randomColor(200, 220));// 背景颜色要偏淡
        g.fillRect(0, 0, width, height);// 画背景
        g.setColor(randomColor(80, 255));// 边框颜色
        g.drawRect(0, 0, width - 1, height - 1);// 画边框
        g.setColor(randomColor(80, 250));// 随机产生10条干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            g.drawLine(x, y, x1, y1);
        }
        g.setColor(randomColor(10, 250));// 随机产生100点，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawLine(x, y, x, y);
        }
        Font font = new Font("Times New Roman", Font.ITALIC, 25); // 创建字体，字体的大小应该根据图片的高度来定。
        g.setFont(font);// 设置字体
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        for (int i = 0; i < code.length(); i++) {
            g.drawString(String.valueOf(code.charAt(i)), 20 * i + 12, (random.nextInt(5) - 2) * i + 25);
        }
        g.dispose();// 图像生效
        return image;
    }

    public static BufferedImage getImage(int length) {
        return getImage(getRandomStr(length));
    }

    public static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(STR.charAt(r.nextInt(STR.length())));
        }
        return sb.toString();
    }

}
