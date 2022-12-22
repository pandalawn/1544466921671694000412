package com.bestfake.fakecall.adapter;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliendroid.alienads.AliendroidIntertitial;
import com.bestfake.fakecall.activity.DetailFakeActivity;
import com.bestfake.fakecall.R;
import com.bestfake.fakecall.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;


public class FakeAdapter extends RecyclerView.Adapter {


    public static String judul ;
    public static String gambar ;
    public static String voice;
    public static String video;
    public static List<Item> webLists;
    public Context context;

    public FakeAdapter(List<Item> webLists, Context context) {
        FakeAdapter.webLists = webLists;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView avatar_url;


        public ViewHolder(View itemView) {
            super(itemView);
            avatar_url =  itemView.findViewById(R.id.img_fake);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fake_list, parent, false);
                return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

          if (holder instanceof ViewHolder) {
                    final Item webList = webLists.get(position);
              final int random = new Random().nextInt(150) + 20;
              int height = (int) (10 + random + 200);
              ((ViewHolder)holder).avatar_url.getLayoutParams().height = height;
                    Picasso.get()
                            .load(webList.getImage_url())
                            .into( ((ViewHolder)holder).avatar_url);

                    ((ViewHolder)holder).avatar_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            judul = webList.getNamefake();
                            gambar = webList.getImage_url();
                            voice = webList.getVoice_url();
                            video = webList.getViode_url();
                            Intent intent = new Intent(context, DetailFakeActivity.class);
                            context.startActivity(intent);
                            switch (SELECT_MAIN_ADS) {
                                case "ADMOB":
                                    AliendroidIntertitial.ShowIntertitialAdmob((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL,
                                            HIGH_PAYING_KEYWORD1, HIGH_PAYING_KEYWORD2, HIGH_PAYING_KEYWORD3, HIGH_PAYING_KEYWORD4, HIGH_PAYING_KEYWORD5);
                                    break;
                                case "APPLOVIN-D":
                                case "APPLOVIN-D-NB":
                                    AliendroidIntertitial.ShowIntertitialApplovinDis((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "APPLOVIN-M":
                                case "APPLOVIN-M-NB":
                                    AliendroidIntertitial.ShowIntertitialApplovinMax((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "IRON":
                                    AliendroidIntertitial.ShowIntertitialIron((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "MOPUB":
                                    AliendroidIntertitial.ShowIntertitialMopub((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "STARTAPP":
                                    AliendroidIntertitial.ShowIntertitialSartApp((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "FACEBOOK":
                                    AliendroidIntertitial.ShowIntertitialFAN((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "GOOGLE-ADS":
                                    AliendroidIntertitial.ShowIntertitialGoogleAds((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "UNITY":
                                    AliendroidIntertitial.ShowIntertitialUnity((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "ALIEN-V":
                                    AliendroidIntertitial.ShowIntertitialAlienView((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                                case "ALIEN-M":
                                    AliendroidIntertitial.ShowIntertitialAlienMediation((Activity) context, SELECT_BACKUP_ADS, MAIN_ADS_INTERTITIAL, BACKUP_ADS_INTERTITIAL, INTERVAL);
                                    break;
                            }
                        }
                    });

        }

    }

    public int getItemCount() {
        return webLists.size();
    }

}
