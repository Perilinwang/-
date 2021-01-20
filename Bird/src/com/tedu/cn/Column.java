package com.tedu.cn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Random;

/**
 * 管道的实体类
 */
public class Column {
    private BufferedImage bufferedImage;
    //坐标
    private  int x,y;
    //管道的大小
    private int w,h;
    //管道中间的缝隙
    private int gap;
    //两个管道间的距离
    private int distance;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }

    public void draw(Graphics g){
        g.drawImage(bufferedImage,x,y,w,h,null);
    }
    public Column(int n)
    {
        try{
            bufferedImage = ImageIO.read(new FileInputStream("column.png"));
            //获取管道的wh
            w = bufferedImage.getWidth();
            h = bufferedImage.getHeight();
            //设置缝隙、间距
            gap = 144;
            distance = 245;//所有的管道  5个属性是一样的。。。。

            x = 500+ ( n-1 )*245;

            //因为高度是随机创建的，所以  管道的形状补一下  自己去思考啊 -220   -440
            y =  -20 * ( new Random().nextInt(12)+11);  //y坐标的取值范围在  -440----   -200之间



        }catch (Exception e){

        }

    }
    //管道的移动方法
    public void move(){
        x--;
        if(x<-w){//当管道移除屏幕时，管道消失，管道重新初始化
            //管道初始化
            x = 690;//500+245+245
            y =  -20 * ( new Random().nextInt(12)+11);  //y坐标的取值范围在  -440----   -200之间

        }

    }

}
