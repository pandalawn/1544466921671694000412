package com.bestfake.fakecall.activity;

import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_REWARDS;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_REWARDS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;
import com.bestfake.fakecall.BuildConfig;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.config.SettingsAlien;
import com.bumptech.glide.Glide;

public class RewardActivity extends AppCompatActivity {
    TextView txtCoins;
    CardView cdSurvei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        TextView txtVideo = findViewById(R.id.txtVideo);
        TextView txtSurvei = findViewById(R.id.txtSurvey);
        txtVideo.setText("Video ("+ SettingsAlien.REWARD_COINS_FOR_VIDEO_ADS+") Coins");
        txtSurvei.setText("Survey ("+SettingsAlien.REWARD_COINS_FOR_SURVEY_ADS+") Coins");
        ImageView getCoins = findViewById(R.id.imgHover4);
        getCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GETCOIN(view);
            }
        });

        ImageView imgTicket = findViewById(R.id.imgCoins);
        Glide.with(RewardActivity.this).load("file:///android_asset/ticket.gif").into(imgTicket);

        txtCoins = findViewById(R.id.txtCoins);
        txtCoins.setText(""+SettingsAlien.COINS);
        cdSurvei = findViewById(R.id.cdSurvey);
        cdSurvei.setVisibility(View.GONE);

        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidReward.LoadRewardAdmob(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
            case "FACEBOOK":
                AliendroidReward.LoadRewardApplovinMax(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-D":
                AliendroidReward.LoadRewardApplovinDis(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-D-NB":
                AliendroidReward.LoadRewardApplovinDis(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "MOPUB":
                AliendroidIntertitial.LoadIntertitialMopub(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "STARTAPP":
                AliendroidReward.LoadRewardStartApp(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "IRON":
                AliendroidReward.LoadRewardIron(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "UNITY":
                AliendroidReward.LoadRewardUnity(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "GOOGLE-ADS":
                AliendroidReward.LoadRewardGoogleAds(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "ALIEN-M":
                AliendroidReward.LoadRewardAlienMediation(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "ALIEN-V":
                AliendroidReward.LoadRewardAlienView(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
        }


    }

    public void GETCOIN(View view) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidReward.ShowRewardAdmob(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
            case "FACEBOOK":
                AliendroidReward.ShowRewardApplovinMax(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidReward.ShowRewardApplovinDis(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "MOPUB":
                break;
            case "STARTAPP":
                AliendroidReward.ShowRewardStartApp(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "IRON":
                AliendroidReward.ShowRewardIron(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "UNITY":
                AliendroidReward.ShowRewardUnity(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "GOOGLE-ADS":
                AliendroidReward.ShowRewardGoogleAds(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "ALIEN-M":
                AliendroidReward.ShowRewardAlienMediation(RewardActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;

        }
    }

    public void onResume() {
        if (AliendroidReward.unlockreward){
            AliendroidReward.unlockreward=false;
            SettingsAlien.COINS = SettingsAlien.COINS+SettingsAlien.REWARD_COINS_FOR_VIDEO_ADS;
            SharedPreferences mSettings = RewardActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt("id", SettingsAlien.COINS);
            editor.apply();
            txtCoins.setText(""+SettingsAlien.COINS);
        }
        super.onResume();
    }


    public void COINFORALIENVIEW() {
        if (SELECT_BACKUP_ADS.equals("ALIEN-V")){
            SettingsAlien.COINS = SettingsAlien.COINS+SettingsAlien.REWARD_COINS_FOR_VIDEO_ADS;
            SharedPreferences mSettings = RewardActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt("id", SettingsAlien.COINS);
            editor.apply();
            txtCoins.setText(""+SettingsAlien.COINS);
        }
    }

}