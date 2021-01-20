package com.tedu.cn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Bird {
    //图片对象
    private BufferedImage bufferedImage;
    //坐标
    private  int x,y;
    //大小
    private int w,h;
    //速度变化的间隔时间
    private double t = 0.1;
    //小鸟向上的速度
    public   double v = 10;
    //小鸟的图片集   帧数
    private BufferedImage[] images = new BufferedImage[8];
    private int index;//当前图片在集合中位置
    //重力加速度
    private double g = 4;
    int page = 0;
    private double alpha = 45;//图片旋转的焦点
    public int getX() {
        return x;
    }
    public Bird(){
        try{
            bufferedImage = ImageIO.read(new FileInputStream("0.png"));
            w = bufferedImage.getWidth();
            h = bufferedImage.getHeight();
            //设置小鸟的位置
            x = 50;
            y = 150;
            //初始化
            for (int i = 0; i < 8; i++) {//01234567
                images[i] = ImageIO.read(new FileInputStream
                        (i+".png"));
            }
            index = 0;

        }catch (Exception e){
        }

    }
    public void draw(Graphics g){
        //旋转图片角度，进行绘制
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.rotate(alpha,x,y);
        g.drawImage(bufferedImage,x-w/2,y-h/2,w,h,null);
    }
    public void fly(){
        page++;
        if(page%10==0){//执行36次，对修改一张小鸟的图片
            index ++;
            if(index>=8){
                index = 0;
            }
            bufferedImage = images[index];
        }
//休息10分钟，做5分钟，完成小鸟的绘制和飞翔
    }
    public void moveY(){
        //计算向下的速度
        v = v - g*t;

        y = (int) (y - v);//最简单的移动方法  向下做加速运行
        if(y>660)
        {
            y = 100;
            v = 15;
        }
        //  思考小鸟的运行  向下，向下旋转一下小鸟
        if(v>0)//向上
        {
            alpha = -45;
        }else {
            alpha = 45;
        }
    }
    public boolean hit(Ground ground){
        boolean is = y - 50  > ground.getY();
        //小鸟不再旋转
        if(is)
        {
            alpha = 0;
        }
        return is ;
    }
    public boolean hit(Column column){
        //检测小鸟是否在管道范围内
        if(column.getX()-x > -column.getW()-20  && column.getX() - x < 20  )
        {
            //小鸟的大小
            if(column.getY()+525-50 <y && column.getY()+525+144 > y){//判断 y  坐标   休息10分钟   10:35上课  思考y坐标怎么判断
                System.out.println("y = "+y+"y2 = "+(column.getY()+525));
                return false;
            }else{
                return true;
            }

        }
        return false; //做一做  小鸟与管道之间碰撞

    }


}
