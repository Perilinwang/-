package com.tedu.cn;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Random;

/**
 * 地面的实体类
 * 1.img  w，h,,,x,,y,(不变)
 * 移动  绘制  方法
 *
 */
public class Ground {

   //地面的图片对象   大家练习一下：30分钟  到4:50 上课
    private BufferedImage bufferedImage;
    //坐标
    private  int x,y;
    //地面的大小
    private int w,h;

    //获取类的属性
    public int getY() {
        return y;
    }

    //参数的初始化
    public Ground(){
        try{
            bufferedImage = ImageIO.read(new FileInputStream("ground.png"));
            //获取地面的wh
            w = bufferedImage.getWidth();
            h = bufferedImage.getHeight();
            x = 0;
            y = 500;
        }catch (Exception e){
        }
    }

    /**
     * 移动
     */
 public void move(){
     x--;
     if(x<=-366){//当地面移除屏幕时，地面消失，地面重新初始化   432-800
         //地面初始化
         x = 0;
     }
 }

    /**
     * 绘制地面
     * @param g
     */
  public void draw(Graphics g){

     g.drawImage(bufferedImage,x,y,w,h,null);
  }




}
