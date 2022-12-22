package com.bestfake.fakecall.facebook;

import static com.bestfake.fakecall.adapter.FakeAdapter.gambar;
import static com.bestfake.fakecall.adapter.FakeAdapter.judul;
import static com.bestfake.fakecall.adapter.FakeAdapter.video;
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

import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaPlayer;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FBVideoCallActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    CircleImageView gambrH;
    ImageView gambrB, imgback;
    MediaPlayer mp;
    RelativeLayout terima, tolak, tolak2;
    LinearLayout atas, bawah;
    Handler handler;
    TextView calling;
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_b_video_call);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setVisibility(View.GONE);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.OPAQUE);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(null);

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

        handler = new Handler();
        atas = findViewById(R.id.layutama);
        bawah = findViewById(R.id.laybawah2);
        calling = findViewById(R.id.txtwaktu);

        mp = MediaPlayer.create(this, R.raw.facebook);
        mp.start();
        mp.setLooping(true);

        tolak = findViewById(R.id.laytolak);
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FBVideoCallActivity.this, MainActivity.class);
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
                Intent intent = new Intent(FBVideoCallActivity.this, MainActivity.class);
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
                Intent intent = new Intent(FBVideoCallActivity.this, MainActivity.class);
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
                surfaceView.setVisibility(View.VISIBLE);
                atas.setVisibility(View.GONE);
                bawah.setVisibility(View.VISIBLE);
                gambrB.setVisibility(View.GONE);
                videoView.start();
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
                AliendroidIntertitial.ShowIntertitialAdmob(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0,
                        HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.ShowIntertitialApplovinDis(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.ShowIntertitialApplovinMax(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "IRON":
                AliendroidIntertitial.ShowIntertitialIron(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "MOPUB":
                AliendroidIntertitial.ShowIntertitialMopub(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "STARTAPP":
                AliendroidIntertitial.ShowIntertitialSartApp(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "FACEBOOK":
                AliendroidIntertitial.ShowIntertitialFAN(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "GOOGLE-ADS":
                AliendroidIntertitial.ShowIntertitialGoogleAds(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "UNITY":
                AliendroidIntertitial.ShowIntertitialUnity(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "ALIEN-V":
                AliendroidIntertitial.ShowIntertitialAlienView(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
            case "ALIEN-M":
                AliendroidIntertitial.ShowIntertitialAlienMediation(FBVideoCallActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL,0);
                break;
        }
    }

    public void onBackPressed() {
        mp.stop();
        Intent intent = new Intent(FBVideoCallActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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

}