package com.bestfake.fakecall.whatapp;

import static com.bestfake.fakecall.adapter.FakeAdapter.gambar;
import static com.bestfake.fakecall.adapter.FakeAdapter.judul;
import static com.bestfake.fakecall.adapter.FakeAdapter.voice;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
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
import com.bestfake.fakecall.facebook.FBVideoCallActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class WAVoiceCallActivity extends AppCompatActivity {
    MediaPlayer mp;
    int Seconds, Minutes, MilliSeconds, hours;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    CircleImageView gambrH;
    private RelativeLayout cancel, terima, pesan, tolak;
    private LinearLayout atas, bawah;
    private TextView calling;
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
    private ImageView imguser2, adduser;

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
        setContentView(R.layout.activity_voice_call);

        atas = findViewById(R.id.atas);
        bawah = findViewById(R.id.bawah);
        calling = findViewById(R.id.txtcall);
        imguser2 = findViewById(R.id.imguser2);
        adduser = findViewById(R.id.adduser);
        adduser.setVisibility(View.INVISIBLE);
        cancel = findViewById(R.id.layclose2);
        tolak = findViewById(R.id.layclose);
        pesan = findViewById(R.id.laypesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(WAVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        handler = new Handler();
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(WAVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                showIntertitial();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(WAVoiceCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                showIntertitial();
            }
        });

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
        mp.setLooping(true);
        TextView judulH = findViewById(R.id.txtname);
        judulH.setText(judul);

        gambrH = findViewById(R.id.imguser);
        Picasso.get()
                .load(gambar)
                .into(gambrH);

        terima = findViewById(R.id.layterima);
        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atas.setVisibility(View.GONE);
                tolak.setVisibility(View.VISIBLE);
                bawah.setVisibility(View.VISIBLE);
                imguser2.setVisibility(View.VISIBLE);
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                Picasso.get()
                        .load(gambar)
                        .into(imguser2);

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


    }

    private void showIntertitial() {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.ShowIntertitialApplovinDis(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.ShowIntertitialApplovinMax(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.ShowIntertitialGoogleAds(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(WAVoiceCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
        }
    }

    public void onBackPressed() {
        mp.stop();
        Intent intent = new Intent(WAVoiceCallActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}