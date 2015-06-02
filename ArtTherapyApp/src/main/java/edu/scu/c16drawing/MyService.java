package edu.scu.c16drawing;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * Created by Rushitaa on 2/12/2015.
 */
public class MyService extends IntentService {
    public MyService()
    {
        super("MyService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final MediaPlayer media = MediaPlayer.create(this,R.raw.eraser);
        media.start();


    }

}
