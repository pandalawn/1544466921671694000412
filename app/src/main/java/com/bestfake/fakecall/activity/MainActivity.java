package com.bestfake.fakecall.activity;

import static com.bestfake.fakecall.config.SettingsAlien.ALIEN_OPENADS;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_BANNER;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.BACKUP_ADS_REWARDS;
import static com.bestfake.fakecall.config.SettingsAlien.BASE_64_LICENSE_KEY;
import static com.bestfake.fakecall.config.SettingsAlien.CHILD_DIRECT_GDPR;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD1;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD2;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD3;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD4;
import static com.bestfake.fakecall.config.SettingsAlien.HIGH_PAYING_KEYWORD5;
import static com.bestfake.fakecall.config.SettingsAlien.INITIALIZE_MAIN_SDK;
import static com.bestfake.fakecall.config.SettingsAlien.INITIALIZE_SDK_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.LINK_REDIRECT;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_BANNER;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_INTERTITIAL;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.MAIN_ADS_REWARDS;
import static com.bestfake.fakecall.config.SettingsAlien.ON_OFF_DATA;
import static com.bestfake.fakecall.config.SettingsAlien.PROTECT_APP;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.STATUS_APP;
import static com.bestfake.fakecall.config.SettingsAlien.SWITCH_BANNER_NATIVES;
import static com.bestfake.fakecall.config.SettingsAlien.URL_DATA;
import static com.bestfake.fakecall.config.SettingsAlien.VISIBLE_GONE_MOREAPP;
import static com.google.android.play.core.install.model.ActivityResult.RESULT_IN_APP_UPDATE_FAILED;
import static com.google.android.play.core.install.model.AppUpdateType.FLEXIBLE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aliendroid.alienads.AlienGDPR;
import com.aliendroid.alienads.AliendroidBanner;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.AliendroidIntertitial;
import com.aliendroid.alienads.AliendroidNative;
import com.aliendroid.alienads.AliendroidReward;
import com.aliendroid.sdkads.type.view.AlienViewAds;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestfake.fakecall.BuildConfig;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.adapter.FakeAdapter;
import com.bestfake.fakecall.adapter.MoreAdapter;
import com.bestfake.fakecall.config.SettingsAlien;
import com.bestfake.fakecall.model.Item;
import com.bestfake.fakecall.model.MoreList;
import com.bumptech.glide.Glide;
import com.github.javiersantos.piracychecker.PiracyChecker;
import com.github.javiersantos.piracychecker.enums.Display;
import com.github.javiersantos.piracychecker.enums.InstallerID;
import com.github.javiersantos.piracychecker.utils.LibraryUtilsKt;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 17326;
    public static Activity fa;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    List<MoreList> webLists;
    ReviewInfo reviewInfo;
    ReviewManager manager;
    List<Item> fakeLists;
    /*
       In App Update
        */
    AppUpdateManager appUpdateManager;
    com.google.android.play.core.tasks.Task<AppUpdateInfo> appUpdateInfoTask;
    InstallStateUpdatedListener listener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                Log.d("InstallDownloded", "InstallStatus sucsses");
                notifyUser();
            }
        }
    };
    private RecyclerView recyclerView;
    private MoreAdapter adapter;
    private RecyclerView recFake;
    private FakeAdapter adapterFake;
    private LinearLayout laymore;
    TextView txtCoins;
    CardView cdSurvei;

    public static LinearLayout layOne, layTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        {
            fa = this;
        }
        Review();
        layOne = findViewById(R.id.layOne);
        layTwo = findViewById(R.id.layTwo);
        SharedPreferences mSettings = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SettingsAlien.COINS = mSettings.getInt("id", SettingsAlien.COINS);
        for (String signature : LibraryUtilsKt.getApkSignatures(this)) {
            Log.e("Signature", signature);
        }
        if (PROTECT_APP) {
            new PiracyChecker(this)
                    .enableGooglePlayLicensing(BASE_64_LICENSE_KEY)
                    .enableUnauthorizedAppsCheck()
                    .display(Display.DIALOG)
                    .enableInstallerId(InstallerID.GOOGLE_PLAY, InstallerID.AMAZON_APP_STORE, InstallerID.GALAXY_APPS)
                    .saveResultToSharedPreferences("my_app_preferences", "valid_license")
                    .start();
        }
        if (STATUS_APP.equals("1")) {
            String str = LINK_REDIRECT;
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(str)));
            finish();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                if (SettingsAlien.SWITCH_OPEN_ADS.equals("2")) {
                    AlienViewAds.OpenApp(MainActivity.this, ALIEN_OPENADS);
                }
            }
        }
        setTitle("");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (!android.provider.Settings.canDrawOverlays(this)) {
                checkPermission();

            }
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            int LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
            WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    LAYOUT_FLAG, // Overlay over the other apps.
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE    // This flag will enable the back key press.
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, // make the window to deliver the focus to the BG window.
                    PixelFormat.TRANSPARENT);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recmore);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);
        webLists = new ArrayList<>();
        laymore = findViewById(R.id.laymore);
        if (VISIBLE_GONE_MOREAPP.equals("1")){
            laymore.setVisibility(View.VISIBLE);
        } else {
            laymore.setVisibility(View.GONE);
        }

        recFake = (RecyclerView) findViewById(R.id.recfake);
        recFake.setHasFixedSize(true);
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recFake.setLayoutManager(sglm);
        fakeLists = new ArrayList<>();
        if (ON_OFF_DATA.equals("1")) {
            if (checkConnectivity()) {
                loadUrlDataFake();
                loadUrlDataMore();
            }
        } else {
            datamore();
            dataFake();
        }
        AlienGDPR.loadGdpr(this, SELECT_MAIN_ADS, CHILD_DIRECT_GDPR);
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidInitialize.SelectAdsAdmob(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "GOOGLE-ADS":
                AliendroidInitialize.SelectAdsGoogleAds(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D":
                AliendroidInitialize.SelectAdsApplovinDis(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-D-NB":
                AliendroidInitialize.SelectAdsApplovinDis(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M":
                AliendroidInitialize.SelectAdsApplovinMax(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "APPLOVIN-M-NB":
                AliendroidInitialize.SelectAdsApplovinMax(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "MOPUB":
                AliendroidInitialize.SelectAdsMopub(this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "IRON":
                AliendroidInitialize.SelectAdsIron(this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "STARTAPP":
                AliendroidInitialize.SelectAdsStartApp(this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "UNITY":
                AliendroidInitialize.SelectAdsUnity(this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "FACEBOOK":
                AliendroidInitialize.SelectAdsFAN(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "ALIEN-M":
                AliendroidInitialize.SelectAdsAlienMediation(this, SELECT_BACKUP_ADS,INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                break;
            case "ALIEN-V":
                AliendroidInitialize.SelectAdsAlienView(this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                break;
        }
        RelativeLayout layAdsbanner = findViewById(R.id.layNative);
        RelativeLayout layAdsbanner2 = findViewById(R.id.mainLayout);
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerAdmob(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER, HIGH_PAYING_KEYWORD1,
                            HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                } else {
                    AliendroidNative.SmallNativeAdmob(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES, HIGH_PAYING_KEYWORD1,
                            HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);

                }
                AliendroidIntertitial.LoadIntertitialAdmob(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, HIGH_PAYING_KEYWORD1,
                        HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                AliendroidReward.LoadRewardAdmob(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-M":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerApplovinMax(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.SmallNativeMax(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                AliendroidIntertitial.LoadIntertitialApplovinMax(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardApplovinMax(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-M-NB":
                AliendroidIntertitial.LoadIntertitialApplovinMax(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardApplovinMax(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-D":
                AliendroidBanner.SmallBannerApplovinDis(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialApplovinDis(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardApplovinDis(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "APPLOVIN-D-NB":
                AliendroidIntertitial.LoadIntertitialApplovinDis(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardApplovinDis(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "MOPUB":
                AliendroidBanner.SmallBannerMopub(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialMopub(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                break;
            case "STARTAPP":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerStartApp(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.SmallNativeStartApp(this, layAdsbanner, SELECT_BACKUP_ADS, MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                AliendroidIntertitial.LoadIntertitialStartApp(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardStartApp(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "IRON":
                AliendroidBanner.SmallBannerIron(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialIron(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardIron(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "FACEBOOK":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerFAN(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.SmallNativeFan(this, layAdsbanner, SELECT_BACKUP_ADS,MAIN_ADS_NATIVES, BACKUP_ADS_NATIVES);
                }
                AliendroidIntertitial.LoadIntertitialFAN(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardApplovinMax(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "UNITY":
                AliendroidBanner.SmallBannerUnity(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialUnity(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardUnity(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "GOOGLE-ADS":
                AliendroidBanner.SmallBannerGoogleAds(this, layAdsbanner2, SELECT_BACKUP_ADS, MAIN_ADS_BANNER, BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialGoogleAds(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardGoogleAds(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "ALIEN-M":
                if (SWITCH_BANNER_NATIVES.equals("1")){
                    AliendroidBanner.SmallBannerAlienMediation(this,layAdsbanner2,SELECT_BACKUP_ADS,MAIN_ADS_BANNER,BACKUP_ADS_BANNER);
                } else {
                    AliendroidNative.SmallNativeAlien(this,layAdsbanner,SELECT_BACKUP_ADS,MAIN_ADS_NATIVES,BACKUP_ADS_NATIVES);
                }
                AliendroidIntertitial.LoadIntertitialAlienMediation(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardAlienMediation(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
            case "ALIEN-V":
                AliendroidBanner.SmallBannerAlienView(this,layAdsbanner2,SELECT_BACKUP_ADS,MAIN_ADS_BANNER,BACKUP_ADS_BANNER);
                AliendroidIntertitial.LoadIntertitialAlienView(this, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL);
                AliendroidReward.LoadRewardAlienView(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                break;
        }


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

        txtCoins = findViewById(R.id.txtCoins);
        txtCoins.setText(""+SettingsAlien.COINS);
        cdSurvei = findViewById(R.id.cdSurvey);
        cdSurvei.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_share) {
            String shareLink = "https://play.google.com/store/apps/details?id="
                    + BuildConfig.APPLICATION_ID;
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    getResources().getString(R.string.shareit) + " "
                            + shareLink);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        } else if (id == R.id.menu_rate) {
            String str = "https://play.google.com/store/apps/details?id="
                    + BuildConfig.APPLICATION_ID;
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(str)));
            finish();
            return true;
        } else if (id == R.id.menu_seting) {
            try {
                Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter",
                        "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", getPackageName());
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        } else if (id == R.id.menu_update) {
            checkUpdate();
            return true;
        } else if (id == R.id.menu_coins) {
            layOne.setVisibility(View.GONE);
            layTwo.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("fake_call.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void datamore() {
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("More");
            // Extract data from json and store into ArrayList as class objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                MoreList dataUrl = new MoreList();
                dataUrl.id = jsonData.getInt("id");
                dataUrl.name = jsonData.getString("title");
                dataUrl.image_url = jsonData.getString("image");
                dataUrl.link_url = jsonData.getString("link");
                webLists.add(dataUrl);
            }

            adapter = new MoreAdapter(webLists, MainActivity.this);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void dataFake() {
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("Item");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                Item dataUrl = new Item();
                dataUrl.Id = jsonData.getInt("id");
                dataUrl.namefake = jsonData.getString("name");
                dataUrl.image_url = jsonData.getString("image_url");
                dataUrl.viode_url = jsonData.getString("video_url");
                dataUrl.voice_url = jsonData.getString("voice_url");
                fakeLists.add(dataUrl);
            }
            adapterFake = new FakeAdapter(fakeLists, MainActivity.this);
            recFake.setAdapter(adapterFake);

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    private void exitapp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_baseline_cancel_24);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
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

    @Override
    public void onBackPressed() {
        exitapp();
    }

    private void loadUrlDataFake() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Item");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonData = array.getJSONObject(i);
                        Item dataUrl = new Item();
                        dataUrl.Id = jsonData.getInt("id");
                        dataUrl.namefake = jsonData.getString("name");
                        dataUrl.image_url = jsonData.getString("image_url");
                        dataUrl.viode_url = jsonData.getString("video_url");
                        dataUrl.voice_url = jsonData.getString("voice_url");
                        fakeLists.add(dataUrl);
                    }
                    adapterFake = new FakeAdapter(fakeLists, MainActivity.this);
                    recFake.setAdapter(adapterFake);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

     /*
    In app review
     */

    private void loadUrlDataMore() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("More");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonData = array.getJSONObject(i);
                        MoreList dataUrl = new MoreList();
                        dataUrl.id = jsonData.getInt("id");
                        dataUrl.name = jsonData.getString("title");
                        dataUrl.image_url = jsonData.getString("image");
                        dataUrl.link_url = jsonData.getString("link");
                        webLists.add(dataUrl);
                    }
                    adapter = new MoreAdapter(webLists, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!android.provider.Settings.canDrawOverlays(this)) {
                checkPermission();
            } else {

            }
        }

        if (requestCode == MY_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (resultCode != RESULT_OK) {
                        Log.d("RESULT_OK  :", "" + resultCode);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    if (resultCode != RESULT_CANCELED) {
                         Log.d("RESULT_CANCELED  :", "" + resultCode);
                    }
                    break;
                case RESULT_IN_APP_UPDATE_FAILED:
                    if (resultCode != RESULT_IN_APP_UPDATE_FAILED) {
                         Log.d("RESULT_IN_APP_FAILED:", "" + resultCode);
                    }
            }
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!android.provider.Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void Review() {
        manager = ReviewManagerFactory.create(this);
        manager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    reviewInfo = task.getResult();
                    manager.launchReviewFlow(MainActivity.this, reviewInfo).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            //Toast.makeText(MainActivity.this, "Rating Failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // Toast.makeText(MainActivity.this, "Review Completed, Thank You!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                 }
        });
    }

    private void checkUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(listener);

        appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                Log.d("appUpdateInfo :", "packageName :" + appUpdateInfo.packageName() + ", " + "availableVersionCode :" + appUpdateInfo.availableVersionCode() + ", " + "updateAvailability :" + appUpdateInfo.updateAvailability() + ", " + "installStatus :" + appUpdateInfo.installStatus());

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(FLEXIBLE)) {
                    requestUpdate(appUpdateInfo);
                    Log.d("UpdateAvailable", "update is there ");
                } else if (appUpdateInfo.updateAvailability() == 3) {
                    Log.d("Update", "3");
                    notifyUser();
                } else {
                     Log.d("NoUpdateAvailable", "update is not there ");
                }
            }
        });
    }

    private void requestUpdate(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this, MY_REQUEST_CODE);
            resume();
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void notifyUser() {
        Snackbar snackbar =
                Snackbar.make(findViewById(R.id.frame_container),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("RESTART", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    private void resume() {
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    notifyUser();
                }

            }
        });
    }

    public void GETCOIN(View view) {
        switch (SELECT_MAIN_ADS) {
            case "ADMOB":
                AliendroidReward.ShowRewardAdmob(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "APPLOVIN-M":
            case "APPLOVIN-M-NB":
            case "FACEBOOK":
                AliendroidReward.ShowRewardApplovinMax(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "APPLOVIN-D":
            case "APPLOVIN-D-NB":
                AliendroidReward.ShowRewardApplovinDis(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "MOPUB":
                break;
            case "STARTAPP":
                AliendroidReward.ShowRewardStartApp(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "IRON":
                AliendroidReward.ShowRewardIron(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "UNITY":
                AliendroidReward.ShowRewardUnity(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "GOOGLE-ADS":
                AliendroidReward.ShowRewardGoogleAds(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;
            case "ALIEN-M":
                AliendroidReward.ShowRewardAlienMediation(MainActivity.this, SELECT_BACKUP_ADS, MAIN_ADS_REWARDS, BACKUP_ADS_REWARDS);
                COINFORALIENVIEW();
                break;

        }
    }

    public void onResume() {
        if (AliendroidReward.unlockreward){
            AliendroidReward.unlockreward=false;
            SettingsAlien.COINS = SettingsAlien.COINS+SettingsAlien.REWARD_COINS_FOR_VIDEO_ADS;
            SharedPreferences mSettings = MainActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt("id", SettingsAlien.COINS);
            editor.apply();
            txtCoins.setText(""+SettingsAlien.COINS);
        }

        super.onResume();
    }

    public void CLOSECOINS (View view){
        layTwo.setVisibility(View.GONE);
        layOne.setVisibility(View.VISIBLE);
    }

    public void COINFORALIENVIEW() {
        if (SELECT_BACKUP_ADS.equals("ALIEN-V")){
            SettingsAlien.COINS = SettingsAlien.COINS+SettingsAlien.REWARD_COINS_FOR_VIDEO_ADS;
            SharedPreferences mSettings = MainActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt("id", SettingsAlien.COINS);
            editor.apply();
            txtCoins.setText(""+SettingsAlien.COINS);
        }
    }

}
