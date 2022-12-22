package com.bestfake.fakecall.activity;


import static com.bestfake.fakecall.config.SettingsAlien.INITIALIZE_MAIN_SDK;
import static com.bestfake.fakecall.config.SettingsAlien.INITIALIZE_SDK_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.ON_OFF_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_BACKUP_ADS;
import static com.bestfake.fakecall.config.SettingsAlien.SELECT_MAIN_ADS;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.alienads.AlienOpenAds;
import com.aliendroid.alienads.AliendroidInitialize;
import com.aliendroid.alienads.interfaces.open.OnLoadOpenAppAdmob;
import com.aliendroid.alienads.interfaces.open.OnShowOpenAppAdmob;
import com.aliendroid.sdkads.config.AppPromote;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.config.SettingsAlien;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        AppPromote.initializeAppPromote(SplashActivity.this);
        if (ON_OFF_ADS.equals("1")) {
            if (checkConnectivity()) {
                loadUrlData();
            } else {
                nointernetp();
            }
        } else {
            switch (SELECT_MAIN_ADS) {
                case "ADMOB":
                    AliendroidInitialize.SelectAdsAdmob(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "GOOGLE-ADS":
                    AliendroidInitialize.SelectAdsGoogleAds(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "APPLOVIN-D":
                    AliendroidInitialize.SelectAdsApplovinDis(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "APPLOVIN-D-NB":
                    AliendroidInitialize.SelectAdsApplovinDis(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "APPLOVIN-M":
                    AliendroidInitialize.SelectAdsApplovinMax(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "APPLOVIN-M-NB":
                    AliendroidInitialize.SelectAdsApplovinMax(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "MOPUB":
                    AliendroidInitialize.SelectAdsMopub(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "IRON":
                    AliendroidInitialize.SelectAdsIron(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "STARTAPP":
                    AliendroidInitialize.SelectAdsStartApp(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "UNITY":
                    AliendroidInitialize.SelectAdsUnity(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "FACEBOOK":
                    AliendroidInitialize.SelectAdsFAN(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "ALIEN-M":
                    AliendroidInitialize.SelectAdsAlienMediation(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                    break;
                case "ALIEN-V":
                    AliendroidInitialize.SelectAdsAlienView(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                    break;
            }
            AppPromote.initializeAppPromote(SplashActivity.this);
            if (SettingsAlien.SWITCH_OPEN_ADS.equals("1")) {
                openAds();
            } else {
                openMain();

            }
        }

    }

    private void loadUrlData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                SettingsAlien.URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray contacts = jsonObj.getJSONArray("Ads");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        SettingsAlien.STATUS_APP = c.getString("status_app");
                        SettingsAlien.LINK_REDIRECT = c.getString("link_redirect");
                        SettingsAlien.SELECT_MAIN_ADS = c.getString("select_main_ads");
                        SettingsAlien.SELECT_BACKUP_ADS = c.getString("select_backup_ads");
                        SettingsAlien.MAIN_ADS_BANNER = c.getString("main_ads_banner");
                        SettingsAlien.MAIN_ADS_INTERTITIAL = c.getString("main_ads_intertitial");
                        SettingsAlien.MAIN_ADS_REWARDS = c.getString("main_ads_rewards");
                        SettingsAlien.MAIN_ADS_NATIVES = c.getString("main_ads_natives");
                        SettingsAlien.BACKUP_ADS_BANNER = c.getString("backup_ads_banner");
                        SettingsAlien.BACKUP_ADS_INTERTITIAL = c.getString("backup_ads_intertitial");
                        SettingsAlien.BACKUP_ADS_NATIVES = c.getString("backup_ads_natives");
                        SettingsAlien.BACKUP_ADS_REWARDS = c.getString("backup_ads_rewards");
                        SettingsAlien.ADMOB_OPENADS = c.getString("open_ads_admob");
                        SettingsAlien.ALIEN_OPENADS = c.getString("open_ads_alien");
                        SettingsAlien.SWITCH_OPEN_ADS = c.getString("switch_open_ads");
                        SettingsAlien.SWITCH_BANNER_NATIVES = c.getString("switch_banner_natives_ads");
                        SettingsAlien.INITIALIZE_MAIN_SDK = c.getString("initialize_sdk");
                        SettingsAlien.INITIALIZE_SDK_BACKUP_ADS = c.getString("initialize_sdk_backup_ads");
                        SettingsAlien.INTERVAL = c.getInt("interval_intertitial");
                        SettingsAlien.HIGH_PAYING_KEYWORD1 = c.getString("high_paying_keyword_1");
                        SettingsAlien.HIGH_PAYING_KEYWORD2 = c.getString("high_paying_keyword_2");
                        SettingsAlien.HIGH_PAYING_KEYWORD3 = c.getString("high_paying_keyword_3");
                        SettingsAlien.HIGH_PAYING_KEYWORD4 = c.getString("high_paying_keyword_4");
                        SettingsAlien.HIGH_PAYING_KEYWORD5 = c.getString("high_paying_keyword_5");
                        switch (SELECT_MAIN_ADS) {
                            case "ADMOB":
                                AliendroidInitialize.SelectAdsAdmob(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "GOOGLE-ADS":
                                AliendroidInitialize.SelectAdsGoogleAds(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "APPLOVIN-D":
                                AliendroidInitialize.SelectAdsApplovinDis(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "APPLOVIN-D-NB":
                                AliendroidInitialize.SelectAdsApplovinDis(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "APPLOVIN-M":
                                AliendroidInitialize.SelectAdsApplovinMax(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "APPLOVIN-M-NB":
                                AliendroidInitialize.SelectAdsApplovinMax(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "MOPUB":
                                AliendroidInitialize.SelectAdsMopub(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "IRON":
                                AliendroidInitialize.SelectAdsIron(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "STARTAPP":
                                AliendroidInitialize.SelectAdsStartApp(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "UNITY":
                                AliendroidInitialize.SelectAdsUnity(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "FACEBOOK":
                                AliendroidInitialize.SelectAdsFAN(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "ALIEN-M":
                                AliendroidInitialize.SelectAdsAlienMediation(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_MAIN_SDK, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                            case "ALIEN-V":
                                AliendroidInitialize.SelectAdsAlienView(SplashActivity.this, SELECT_BACKUP_ADS, INITIALIZE_SDK_BACKUP_ADS);
                                break;
                        }
                        openAds();

                    }
                } catch (JSONException e) {
                    openMain();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                openMain();
                Toast.makeText(SplashActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        requestQueue.add(stringRequest);

    }


    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    public void openMain() {
        new CountDownTimer(2000, 1000) {
            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }

    private void openAds() {
        if (SettingsAlien.SWITCH_OPEN_ADS.equals("1")) {
            AlienOpenAds.LoadOpenAds(SettingsAlien.ADMOB_OPENADS, true);
            AlienOpenAds.onLoadOpenAppAdmob = new OnLoadOpenAppAdmob() {
                @Override
                public void onAdLoaded() {
                    AlienOpenAds.onShowOpenAppAdmob = new OnShowOpenAppAdmob() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            openMain();
                            AlienOpenAds.LOADADS = false;
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent() {
                            openMain();
                            AlienOpenAds.LOADADS = false;
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            AlienOpenAds.LOADADS = false;
                        }
                    };
                }

                @Override
                public void onAdFailedToLoad() {
                    AlienOpenAds.LOADADS = false;
                    openMain();
                }
            };


        } else {
            openMain();
        }
    }

    private void nointernetp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_baseline_network_check_24);
        builder.setTitle("Bad Connection");
        builder.setMessage("No internet access, please activate the internet to use the app!");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Reload", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
