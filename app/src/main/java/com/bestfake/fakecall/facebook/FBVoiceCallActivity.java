package com.bestfake.fakecall.facebook;

import static com.bestfake.fakecall.adapter.FakeAdapter.gambar;
import static com.bestfake.fakecall.adapter.FakeAdapter.judul;
import static com.bestfake.fakecall.adapter.FakeAdapter.voice;

import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.bestfake.fakecall.config.SettingsAlien.INTERVAL;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class FBVoiceCallActivity extends AppCompatActivity {
    CircleImageView gambrH;
    ImageView gambrB, imgback;
    MediaPlayer mp;
    RelativeLayout terima, tolak, tolak2;
    LinearLayout atas, bawah;
    int Seconds, Minutes, MilliSeconds, hours;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    TextView calling;
    public Runnable runnable = new Runnable() {

        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            hours = Minutes / 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            calling.setText(String.format("%02d", hours) + ":" + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds));

            handler.postDelayed(this, 0);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().setStatusBarColor(1140850688);
        int currentApiVersion = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
        setContentView(R.layout.activity_f_b_voice_call);

        handler = new Handler();
        atas = findViewById(R.id.laybawah1);
        bawah = findViewById(R.id.laybawah2);
        calling = findViewById(R.id.txtwaktu);

        mp = MediaPlayer.create(this, R.raw.facebook);
        mp.start();
        mp.setLooping(true);

        tolak = findViewById(R.id.laytolak);
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FBVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                showIntertitial();

            }
        });

        imgback = findViewById(R.id.imgback2);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FBVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                showIntertitial();
            }
        });
        tolak2 = findViewById(R.id.laytolak2);
        tolak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FBVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                showIntertitial();
            }
        });

        terima = findViewById(R.id.layterima);
        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                atas.setVisibility(View.GONE);
                bawah.setVisibility(View.VISIBLE);
                String url = voice;
                mp.stop();
                try {
                    mp = new MediaPlayer();
                    if (url.startsWith("http")) {
                        mp.setDataSource(url);
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    } else {
                        AssetFileDescriptor descriptor;
                        descriptor = getAssets().openFd(url);
                        mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    }
                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        TextView judulH = findViewById(R.id.txtfbname);
        judulH.setText(judul);

        gambrH = findViewById(R.id.fbimguser);
        gambrB = findViewById(R.id.imgback);

        Picasso.get()
                .load(gambar)
                .into(gambrH);
        Picasso.get()
                .load(gambar)
                .into(gambrB);
    }

    private void showIntertitial() {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.ShowIntertitialApplovinDis(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.ShowIntertitialApplovinMax(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.ShowIntertitialGoogleAds(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(FBVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
        }
    }

    public void onBackPressed() {
        mp.stop();
        Intent intent = new Intent(FBVoiceCallActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}