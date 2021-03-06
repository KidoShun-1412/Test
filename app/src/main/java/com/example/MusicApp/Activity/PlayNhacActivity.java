package com.example.MusicApp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.support.v4.media.session.MediaSessionCompat;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.MusicApp.Adapter.ViewPagerDiaNhac;
import com.example.MusicApp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.MusicApp.Fragment.Fragment_dia_nhac;
import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.NotificationBackGround.MusicService;
import com.example.MusicApp.NotificationBackGround.NotificationReceiver;
import com.example.MusicApp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import static com.example.MusicApp.NotificationBackGround.ApplicationClass.ACTION_NEXT;
import static com.example.MusicApp.NotificationBackGround.ApplicationClass.ACTION_PLAY;
import static com.example.MusicApp.NotificationBackGround.ApplicationClass.ACTION_PREV;
import static com.example.MusicApp.NotificationBackGround.ApplicationClass.CHANNEL_ID_2;

public class PlayNhacActivity extends AppCompatActivity {
    //private CircleLineVisualizer mVisualizer;
    private MediaPlayer mediaPlayer;
    androidx.appcompat.widget.Toolbar toolbarplaynhac;
    SeekBar seekBarnhac;
    ImageView imageViewtim;
    TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
    imageButtonlapnhac;
    ViewPager viewPagerplaynhac;
    int dem = 0;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<BaiHat>();
    MusicService musicService;
    MediaSessionCompat mediaSessionCompat;

    Fragment_dia_nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    public static ViewPagerDiaNhac adapternhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        GetDataFromIntent();
        AnhXa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        enventClick();
//        imageViewtim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (dem == 0){
//                    Animation animation = AnimationUtils.loadAnimation(PlayNhacActivity.this, R.anim.anim_timclick);
//                    imageViewtim.setImageResource(R.drawable.iconloved);
//                    view.startAnimation(animation);
//                    dem++;
//                }else {
//                    imageViewtim.setImageResource(R.drawable.iconlove);
//                    dem--;
//                }
//            }
//        });
    }

    private void enventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mangbaihat.size() > 0){
                    fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                    handler.removeCallbacks(this);
                }else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
        imageButtonplaypausenhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                }else {
                    mediaPlayer.start();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                }
            }
        });
        imageButtonlapnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                        repeat = true;
                    }else {
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        repeat = true;
                    }
                }else {
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtontronnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                        checkrandom = true;
                    }else {
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        checkrandom = true;
                    }
                }else {
                    imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnexnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1){
                            position = 0;
                        }
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonpreviewnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpreviewnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }
                }, 3000);
            }
        });
        imageButtonpreviewnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())) {
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position--;
                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonpreviewnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpreviewnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }
                }, 3000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayList;

            }
        }
    }

    private void AnhXa() {
        //mVisualizer = findViewById(R.id.blob);
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        //imageViewtim = findViewById(R.id.imageViewtimplaynhac);
        imageButtontronnhac = findViewById(R.id.imageButtontron);
        imageButtonpreviewnhac = findViewById(R.id.imageButtonpreview);
        imageButtonplaypausenhac = findViewById(R.id.imageButtonplaypause);
        imageButtonnexnhac = findViewById(R.id.imageButtonnext);
        imageButtonlapnhac = findViewById(R.id.imageButtonlap);
        textViewtatoltime = findViewById(R.id.textViewtimetotal);
        textViewcasi = findViewById(R.id.textViewtencasiplaynhac);
        textViewtennhac = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewrunrime = findViewById(R.id.textViewruntime);

        fragment_dia_nhac = new Fragment_dia_nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerDiaNhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment( fragment_play_danh_sach_cac_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(position);
        if (mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
            new playMP3().onPostExecute(mangbaihat.get(position).getLinkBaiHat());
            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        }
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mangbaihat.clear();
                finish();
            }
        });

    }
    class playMP3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
/*            try {
                mVisualizer.setAudioSessionId(mediaPlayer.getAudioSessionId());
            }catch (Exception e){

            }*/
        }
    }
    private void TimeSong(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarnhac.setMax(mediaPlayer.getDuration());
        textViewtennhac.setText(mangbaihat.get(position).getTenBaiHat());
        textViewcasi.setText(mangbaihat.get(position).getTenCaSi());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarnhac.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewrunrime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (mangbaihat.size())) {
                        //imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            position --;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }
                        try {
                            fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                            new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    imageButtonpreviewnhac.setClickable(false);
                    imageButtonnexnhac.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonpreviewnhac.setClickable(true);
                            imageButtonnexnhac.setClickable(true);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
    private void showNotification(int playPauseBtn)
    {

        Intent intent = new Intent(this,PlayNhacActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,intent,0);
        Intent prevIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PREV);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this,0,prevIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Intent playIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this,0,playIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Intent nextIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this,0,nextIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        Bitmap picture1= getBitmapFromURL(mangbaihat.get(position).getHinhBaiHat());

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID_2)
                .setSmallIcon(R.drawable.musicfile2)
                .setLargeIcon(picture1)
                .setContentTitle(mangbaihat.get(position).getTenBaiHat())
                .setContentText(mangbaihat.get(position).getTenCaSi())
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(R.drawable.ic_baseline_skip_previous_36,"previous",prevPendingIntent)
                .addAction(playPauseBtn,"play",playPendingIntent)
                .addAction(R.drawable.ic_baseline_skip_next_36,"next",nextPendingIntent)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .build();
        NotificationManager notificationManager =(NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
