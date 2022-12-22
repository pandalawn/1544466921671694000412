package com.bestfake.fakecall.activity;

import static com.bestfake.fakecall.activity.MainActivity.layOne;
import static com.bestfake.fakecall.activity.MainActivity.layTwo;
import static com.bestfake.fakecall.adapter.FakeAdapter.gambar;
import static com.bestfake.fakecall.adapter.FakeAdapter.judul;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_BANNER;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_BANNER;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SWITCH_BANNER_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.TIMER_A;
import static com.bestfake.fakecall.config.SettingsAlien.TIMER_B;
import static com.bestfake.fakecall.config.SettingsAlien.TIMER_C;
import static com.bestfake.fakecall.config.SettingsAlien.TIMER_D;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.sdkads.type.view.AlienViewAds;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.config.SettingsAlien;
import com.bestfake.fakecall.facebook.FBVideoCallActivity;
import com.bestfake.fakecall.facebook.FBVoiceCallActivity;
import com.bestfake.fakecall.telegram.TeleVideoCallActivity;
import com.bestfake.fakecall.telegram.TeleVoiceCallActivity;
import com.bestfake.fakecall.util.AppReceiver;
import com.bestfake.fakecall.whatapp.WAVideoCallActivity;
import com.bestfake.fakecall.whatapp.WAVoiceCallActivity;
import com.squareup.picasso.Picasso;

import java.util.Calendar;


public class DetailFakeActivity extends AppCompatActivity {
    protected static final String TAG = DetailFakeActivity.class.getSimpleName();
    private static final int ALARM_REQUEST_CODE = 134;
    public static int rd_vid = 1;
    public static int rd_form = 1;
    public static int rd_time = 1;
    public static String status_time = "Wait for 2 seconds";
    private final int NOTIFICATION_ID = 1;
    private PendingIntent pendingIntent;
    private RadioGroup list_action, list_form, list_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fake_activity);

        TextView judulH = findViewById(R.id.txtjudul);
        if (AliendroidIntertitial.SHOW_ALIEN_VIEW) {
            if (SettingsAlien.SELECT_MAIN_ADS.equals("ALIEN-V")){
                AlienViewAds.Interstitial(this,MAIN_ADS_INTERTITIAL);
            } else {
                AlienViewAds.Interstitial(this,BACKUP_ADS_INTERTITIAL);
            }
            AlienViewAds.ShowIntertitial();
            AliendroidIntertitial.SHOW_ALIEN_VIEW = false;
        }

        list_action = findViewById(R.id.list_action);
        list_action.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rd_vid:
                        rd_vid = 1;
                        break;
                    case R.id.rd_voic:
                        rd_vid = 2;
                        break;

                }
            }
        });

        list_form = findViewById(R.id.lict_form);
        list_form.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rdwa:
                        rd_form = 1;
                        break;
                    case R.id.rdfb:
                        rd_form = 2;
                        break;
                    case R.id.rdduo:
                        rd_form = 3;
                        break;
                }
            }
        });

        list_time = findViewById(R.id.list_time);
        list_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rd1:
                        rd_time = 1;
                        status_time = "Wait for 2 seconds";
                        break;
                    case R.id.rd10:
                        rd_time = TIMER_A;
                        status_time = "Wait for 10 seconds";
                        break;
                    case R.id.rd30:
                        rd_time = TIMER_B;
                        status_time = "Wait for 30 seconds";
                        break;
                    case R.id.rd60:
                        rd_time = TIMER_C;
                        status_time = "Wait for 1 minutes";
                        break;
                    case R.id.rd300:
                        rd_time = TIMER_D;
                        status_time = "Wait for 5 minutes";
                        break;
                }
            }
        });

        judulH.setText(judul);
        ImageView gambrH = findViewById(R.id.imageheader);
        Picasso.get()
                .load(gambar)
                .into(gambrH);


        Intent alarmIntent = new Intent(this, AppReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, alarmIntent,  PendingIntent.FLAG_IMMUTABLE );

        Button tbtutor = findViewById(R.id.tblstart);
        tbtutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rd_time == 1) {
                    if (rd_form == 1) {
                        if (rd_vid == 2) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, WAVoiceCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else if (rd_vid == 1) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, WAVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else {
                            Intent intent2 = new Intent(DetailFakeActivity.this, WAVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        }
                    } else if (rd_form == 2) {
                        if (rd_vid == 2) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, FBVoiceCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else if (rd_vid == 1) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, FBVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else {
                            Intent intent2 = new Intent(DetailFakeActivity.this, FBVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        }
                    } else if (rd_form == 3) {
                        if (rd_vid == 2) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, TeleVoiceCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else if (rd_vid == 1) {
                            Intent intent2 = new Intent(DetailFakeActivity.this, TeleVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        } else {
                            Intent intent2 = new Intent(DetailFakeActivity.this, TeleVideoCallActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            finish();
                        }
                    }
                } else {
                    if (SettingsAlien.COINS>=SettingsAlien.USE_COINS_FOR_TIMER){
                        SettingsAlien.COINS = SettingsAlien.COINS-SettingsAlien.USE_COINS_FOR_TIMER;
                        SharedPreferences mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putInt("id", SettingsAlien.COINS);
                        editor.apply();
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, rd_time);
                        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                        Toast.makeText(DetailFakeActivity.this, status_time, Toast.LENGTH_SHORT).show();
                        finish();
                        MainActivity.fa.finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailFakeActivity.this);
                        builder.setCancelable(true);
                        builder.setIcon(R.drawable.ic_baseline_local_play_24);
                        builder.setTitle("No coins");
                        builder.setMessage(getString(R.string.enough_coins));
                        builder.setInverseBackgroundForced(true);
                        builder.setPositiveButton("Get Coins", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                layTwo.setVisibility(View.VISIBLE);
                                layOne.setVisibility(View.GONE);
                                finish();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }

                }
            }
        });


        RelativeLayout layAdsbanner = findViewById(R.id.layNative);
        RelativeLayout layAdsbanner2 = findViewById(R.id.mainLayout);
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerAdmob(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                            HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                } else {
                    AliendroidNative.MediumNativeAdmob(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES, HIGH_PAYING_KEYWORD1,
                            HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);

                }
                break;
            case "APPLOVIN-M":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerApplovinMax(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.MediumNativeMax(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                break;
            case "APPLOVIN-M-NB":
                //AliendroidIntertitial.LoadIntertitialApplovinMax(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "APPLOVIN-D-NB":
               // AliendroidIntertitial.LoadIntertitialApplovinDis(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "MOPUB":
                AliendroidBanner.SmallBannerMopub(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "STARTAPP":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerStartApp(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.MediumNativeStartApp(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "FACEBOOK":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerFAN(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.MediumNativeFan(this, layAdsbanner, SELECT_BACKUP_ADS,MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "GOOGLE-ADS":
                AliendroidBanner.SmallBannerGoogleAds(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                break;
            case "ALIEN-M":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerAlienMediation(this,layAdsbanner2,SELECT_BACKUP_ADS,MAIN_ADS_BANNER,BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.MediumNativeAlien(this,layAdsbanner,SELECT_BACKUP_ADS,MAIN_ADS_NATIVES,BACKUP_ADS_NATIVES);
                }
                break;
            case "ALIEN-V":
                AliendroidBanner.SmallBannerAlienView(this,layAdsbanner2,SELECT_BACKUP_ADS,MAIN_ADS_BANNER,BACKUP_ADS_BANNER);
                break;
        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}