package com.bestfake.fakecall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bestfake.fakecall.model.MoreList;
import com.bestfake.fakecall.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MoreAdapter extends RecyclerView.Adapter {

    public static List<MoreList> webLists;
    public Context context;
    public MoreAdapter(List<MoreList> webLists, Context context) {
        this.webLists = webLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView name;
        public CircleImageView avatar_url;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtid);
            avatar_url = (CircleImageView) itemView.findViewById(R.id.img_more);
            linearLayout = itemView.findViewById(R.id.klik_more);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.more_list, parent, false);
                return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

          if (holder instanceof ViewHolder) {
                    final MoreList webList = webLists.get(position);

                    ((ViewHolder)holder).name.setText(webList.getName());
                    Picasso.get()
                            .load(webList.getImage_url())
                            .into( ((ViewHolder)holder).avatar_url);

                    ((ViewHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         String buka_more = webList.getLink_url();
                            String str = buka_more;
                            context.startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(str)));
                        }
                    });
        }

    }

    public int getItemCount() {
        return webLists.size();
    }

}
