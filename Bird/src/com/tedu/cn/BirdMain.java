package com.tedu.cn;

import javax.swing.*;

public class BirdMain {
     public static void main(String[] args) {
        System.out.println("欢迎你使用飞扬小鸟");
        JFrame jf = new JFrame();
        jf.setSize(440,660);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameJpanel game = new GameJpanel();
        jf.add(game);
        jf.setVisible(true);


    }
}
