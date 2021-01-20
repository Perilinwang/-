package com.tedu.cn;

import javax.sound.sampled.*;
import java.io.File;
public class Sound {

    AudioFormat format;
    byte[] data;
    int length;
    public Sound(String string){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(string));
            format = in.getFormat();
            length = format.getFrameSize();
            data = new byte[length];
            in.read(data);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Clip clip = AudioSystem.getClip();
                    //加载音频文件
                    clip.open(format,data,0,length);
                    clip.start();
                    clip.drain();
                    clip.stop();
                    clip.close();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();


    }


}
