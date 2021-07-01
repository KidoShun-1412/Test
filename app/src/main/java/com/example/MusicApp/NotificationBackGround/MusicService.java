package com.example.MusicApp.NotificationBackGround;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private IBinder mBinder = new MyBinder();
    public static final String ACTION_NEXT  = "NEXT";
    public static final String ACTION_PLAY = "PLAY";
    public static final String ACTION_PREV = "PREV";
    ActionPlaying actionPlaying;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class MyBinder extends Binder
    {
        public MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String actionname=intent.getStringExtra("myActionName");
        if (actionname!=null)
        {
            switch (actionname)
            {
                case ACTION_PLAY:
                    if(actionPlaying!=null){
                        actionPlaying.playClicked();
                    }
                    break;
                case ACTION_NEXT:
                    if(actionPlaying!=null){
                        actionPlaying.nextClicked();
                    }
                    break;
                case ACTION_PREV:
                    if(actionPlaying!=null){
                        actionPlaying.prevClicked();
                    }
                    break;
            }
        }
        return START_STICKY;
    }

    public void setCallBack(ActionPlaying actionPlaying){
        this.actionPlaying=actionPlaying;
    }
}

