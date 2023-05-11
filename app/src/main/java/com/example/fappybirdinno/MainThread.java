package com.example.fappybirdinno;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

public class MainThread extends Thread{
    SurfaceHolder mySurfaceHolder;
    long timespent;
    long kickOffTime;
    long Wait = 31;
    boolean Running;
    private static Canvas canvas;
    public MainThread(SurfaceHolder surfaceHolder) {
        this.mySurfaceHolder = surfaceHolder;
        Running = true;
    }
    public void run(){
        while (Running){
            kickOffTime = SystemClock.uptimeMillis();
            canvas = null;
            try{
                canvas = mySurfaceHolder.lockCanvas();
                synchronized (mySurfaceHolder) {
                    AppHolder.getGameManager().backgroundAnimation(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if (canvas!=null){
                    try {
                        mySurfaceHolder.unlockCanvasAndPost(canvas);
                }catch (Exception e){
                        e.printStackTrace();
                }
            }
            }
            timespent = SystemClock.uptimeMillis() - kickOffTime;
            if (timespent < Wait){
                try {
                    Thread.sleep(Wait - timespent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
        }}

    }
    public boolean isRunning(){
        return Running;
    }
    public void setIsRunning(boolean state){
        Running = state;
    }
}
