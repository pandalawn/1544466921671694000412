package com.bestfake.fakecall.telegram;

import static com.bestfake.fakecall.adapter.FakeAdapter.gambar;
import static com.bestfake.fakecall.adapter.FakeAdapter.judul;
import static com.bestfake.fakecall.adapter.FakeAdapter.video;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.bestfake.fakecall.BuildConfig;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.activity.MainActivity;
import com.bestfake.fakecall.facebook.FBVideoCallActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class TeleVideoCallActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    MediaPlayer mp;
    Camera camera;
    SurfaceView surfaceView, surfaceView2;
    SurfaceHolder surfaceHolder;
    VideoView videoView;
    Handler handler;
    private TextView calling, nameuser;
    private ImageView adduser;
    private CircleImageView imguser;
    private RelativeLayout cancel, terima, pesan, tolak;
    private LinearLayout atas, bawah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_video_call);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
        mp.setLooping(true);

        atas = findViewById(R.id.atas);
        bawah = findViewById(R.id.bawah);
        videoView = findViewById(R.id.videoView);

        String uriPath = video;
        if (video.startsWith("https://")) {
            Uri uri = Uri.parse(uriPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
        } else if (video.startsWith("http://")) {
            Uri uri = Uri.parse(uriPath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
        } else {
            String fileName = "android.resource://" + BuildConfig.APPLICATION_ID + "/raw/" + video;
            videoView.setVideoURI(Uri.parse(fileName));
            videoView.requestFocus();
        }


        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setVisibility(View.GONE);
        surfaceView2 = findViewById(R.id.surfaceView2);
        surfaceView2.setVisibility(View.GONE);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.OPAQUE);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        handler = new Handler();
        calling = findViewById(R.id.txtcall);
        nameuser = findViewById(R.id.txtname);
        imguser = findViewById(R.id.imguser);
        adduser = findViewById(R.id.adduser);
        adduser.setVisibility(View.INVISIBLE);
        cancel = findViewById(R.id.layclose2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeleVideoCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                showIntertitial();
            }
        });

        pesan = findViewById(R.id.laypesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeleVideoCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                mp.stop();
                showIntertitial();
            }
        });

        tolak = findViewById(R.id.layclose);
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeleVideoCallActivity.this, MainActivity.class);
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
                mp.stop();
                mp.stop();
                calling.setVisibility(View.GONE);
                nameuser.setVisibility(View.GONE);
                imguser.setVisibility(View.GONE);
                adduser.setVisibility(View.VISIBLE);
                surfaceView.setVisibility(View.GONE);
                surfaceView2.setVisibility(View.VISIBLE);
                surfaceHolder = surfaceView2.getHolder();
                surfaceHolder.addCallback(TeleVideoCallActivity.this);
                surfaceHolder.setFormat(PixelFormat.OPAQUE);
                surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

                videoView.start();
                atas.setVisibility(View.GONE);
                bawah.setVisibility(View.VISIBLE);
                tolak.setVisibility(View.VISIBLE);

            }
        });

        imguser = findViewById(R.id.imguser);
        Picasso.get()
                .load(gambar)
                .into(imguser);
        nameuser.setText(judul);

    }

    private void showIntertitial() {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidIntertitial.ShowIntertitialAdmob(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.ShowIntertitialApplovinDis(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.ShowIntertitialApplovinMax(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.ShowIntertitialGoogleAds(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(TeleVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, 0);
                break;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open(1);
        Camera.Parameters parameters;
        parameters = camera.getParameters();
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (Exception e) {
        }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;

    }

    public void onBackPressed() {
        mp.stop();
        Intent intent = new Intent(TeleVideoCallActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}