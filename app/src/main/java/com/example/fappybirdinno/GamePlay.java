package com.example.fappybirdinno;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import androidx.annotation.NonNull;

public class GamePlay extends SurfaceView implements SurfaceHolder.Callback{

    MainThread mainThread;
    public GamePlay(Context context){
        super(context);
        SurfaceHolder myHolder = getHolder();
        myHolder.addCallback(this);
        mainThread = new MainThread(myHolder);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mainThread.setIsRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while(retry){
            try{
            mainThread.setIsRunning(false);
            mainThread.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
