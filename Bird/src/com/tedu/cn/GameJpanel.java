package com.tedu.cn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 游戏界面   游戏背景
 */
public class GameJpanel extends JPanel {

    private BufferedImage background;
    private boolean isGameOver = false;
    ArrayList<Column> columns = new ArrayList<>();
    private Ground ground = new Ground();
    private Bird bird = new Bird();
    private int score = 0;
    private boolean isgame = false;
    GameJpanel(){
        //背景图片
        try{
           InputStream inputStream = new FileInputStream("bg.png");
            background = ImageIO.read(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        //暂时  开始游戏，，，监听用户点击事件--开始游戏
  //      startGame();  //只调用一次


        //创建管道
        for(int i=0;i<3;i++){
            Column column = new Column(i);
            columns.add(column);
        }
        // 对游戏做鼠标监听事件  在构造方法中，添加鼠标监听事件
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //按下  小鸟向上运行
                bird.v = 5;
                if( !isGameOver &&(e.getX()>86&&e.getY()>230&&e.getY()<260&&e.getX()<310))
                {
                    System.out.println("startGame");
                    isGameOver = true;
                    startGame();
                    Sound sound = new Sound("start.wav");
                    sound.play();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    /**
     * 当背景发送变化时，jvm 会自动调用该方法，绘制游戏元素
     * @param g 绘制对象，jdk 提供
     */
    int x = 0;
    @Override
    //不能新建任何对象
    protected void paintComponent(Graphics g) {//  建议  使用idea自动生成

        //完成背景图片的绘制
        g.drawImage(background,x+430,0,null);
        //绘制两张背景
        g.drawImage(background,x,0,null);
        x = x - 1;
        if(x<-430){
            x = 0;
        }

        for (Column c:columns){
            //直接绘制管道
            c.draw(g);
            //管道的移动方法
            c.move();
            //判断小鸟与管道
            if(bird.hit(c)){
                isGameOver = false;
                isgame = true;
            }
            //判断小鸟是否飞过管道
            if(bird.getX() == c.getX()  )
            {
                score ++ ;
            }

        }

        Font f = new Font(Font.SANS_SERIF,Font.BOLD,30);
        g.setFont(f);
        g.setColor(Color.CYAN);
        //绘制分数
        g.drawString("得分："+score,10,40);
//显示游戏开始
        if(!isGameOver &&  !isgame )
        {
            g.drawString("请点击  开始游戏",80,260);
        }

        if(isgame)
        {
            g.drawString("游戏结束，请重新开始",80,260);
        }

        //绘制 移动地面
        ground.draw(g);
        ground.move();

        //移动绘制小鸟
        bird.draw(g);
        bird.fly();
        bird.moveY();

        //判断一下小鸟是否与地面接触   小鸟是否摔死了。。
        if(bird.hit(ground)){
            isGameOver = false;//游戏结束
            isgame = true;
        }
    }

    /**
     * 开始游戏
     */
    public  void startGame(){
        //对数据做初始化
        int x = 0;
        //游戏是否结束的标识
        //使用数组  集合绘制多个管道
        columns = new ArrayList<>();
        //声明 地面，
        ground = new Ground();
        //声明游戏对象  初始化
       bird = new Bird();
        //声明 并实例化  分数
        score = 0;
        //游戏结束的标识符
        isgame = false;
        //创建管道
        for(int i=0;i<3;i++){
            Column column = new Column(i);
            columns.add(column);
        }

        //开一个线程
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(isGameOver){
                            try {
                                Thread.sleep(40); //游戏的帧数，3600/40==90
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            repaint();
                        }
                    }
                }
        ).start();
    }

}
